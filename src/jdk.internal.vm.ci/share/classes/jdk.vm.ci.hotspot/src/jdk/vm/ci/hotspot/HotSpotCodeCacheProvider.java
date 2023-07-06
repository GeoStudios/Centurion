/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot;


import java.util.Map;
import java.base.share.classes.java.util.Objects;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.BailoutException;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.BytecodeFrame;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.CodeCacheProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.CompiledCode;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.InstalledCode;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.RegisterConfig;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.TargetDescription;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.site.Call;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.site.Mark;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.SpeculationLog;















/**
 * HotSpot implementation of {@link CodeCacheProvider}.
 */
public class HotSpotCodeCacheProvider implements CodeCacheProvider {

    protected final HotSpotJVMCIRuntime runtime;
    private final HotSpotVMConfig config;
    protected final TargetDescription target;
    protected final RegisterConfig regConfig;

    public HotSpotCodeCacheProvider(HotSpotJVMCIRuntime runtime, TargetDescription target, RegisterConfig regConfig) {
        this.runtime = runtime;
        this.config = runtime.getConfig();
        this.target = target;
        this.regConfig = regConfig;
    }

    @Override
    public String getMarkName(Mark mark) {
        int markId = (int) mark.id;
        HotSpotVMConfigStore store = runtime.getConfigStore();
        for (Map.Entry<String, Long> e : store.getConstants().entrySet()) {
            String name = e.getKey();
            if (name.startsWith("MARKID_") && e.getValue() == markId) {
                return name;
            }
        }
        return CodeCacheProvider.super.getMarkName(mark);
    }

    /**
     * Decodes a call target to a mnemonic if possible.
     */
    @Override
    public String getTargetName(Call call) {
        if (call.target instanceof HotSpotForeignCallTarget) {
            long address = ((HotSpotForeignCallTarget) call.target).address;
            HotSpotVMConfigStore store = runtime.getConfigStore();
            for (Map.Entry<String, VMField> e : store.getFields().entrySet()) {
                VMField field = e.getValue();
                if (field.isStatic() && field.value != null && field.value instanceof Long && ((Long) field.value) == address) {
                    return e.getValue() + ":0x" + Long.toHexString(address);
                }
            }
        }
        return CodeCacheProvider.super.getTargetName(call);
    }

    @Override
    public RegisterConfig getRegisterConfig() {
        return regConfig;
    }

    @Override
    public int getMinimumOutgoingSize() {
        return config.runtimeCallStackSize;
    }

    private InstalledCode logOrDump(InstalledCode installedCode, CompiledCode compiledCode) {
        runtime.notifyInstall(this, installedCode, compiledCode);
        return installedCode;
    }

    @Override
    public InstalledCode installCode(ResolvedJavaMethod method, CompiledCode compiledCode, InstalledCode installedCode, SpeculationLog log, boolean isDefault) {
        InstalledCode resultInstalledCode;
        if (installedCode != null) {
            throw new IllegalArgumentException("InstalledCode argument must be null");
        }
        HotSpotCompiledCode hsCompiledCode = (HotSpotCompiledCode) compiledCode;
        String name = hsCompiledCode.getName();
        HotSpotCompiledNmethod hsCompiledNmethod = null;
        if (method == null) {
            // Must be a stub
            resultInstalledCode = new HotSpotRuntimeStub(name);
        } else {
            hsCompiledNmethod = (HotSpotCompiledNmethod) hsCompiledCode;
            HotSpotResolvedJavaMethodImpl hsMethod = (HotSpotResolvedJavaMethodImpl) method;
            resultInstalledCode = new HotSpotNmethod(hsMethod, name, isDefault, hsCompiledNmethod.id);
        }

        HotSpotSpeculationLog speculationLog = null;
        if (log != null) {
            if (log.hasSpeculations()) {
                speculationLog = (HotSpotSpeculationLog) log;
            }
        }

        byte[] speculations;
        long failedSpeculationsAddress;
        if (speculationLog != null) {
            speculations = speculationLog.getFlattenedSpeculations(true);
            failedSpeculationsAddress = speculationLog.getFailedSpeculationsAddress();
        } else {
            speculations = new byte[0];
            failedSpeculationsAddress = 0L;
        }
        int result = runtime.getCompilerToVM().installCode(target, (HotSpotCompiledCode) compiledCode, resultInstalledCode, failedSpeculationsAddress, speculations);
        if (result != config.codeInstallResultOk) {
            String resultDesc = config.getCodeInstallResultDescription(result);
            if (hsCompiledNmethod != null) {
                String msg = hsCompiledNmethod.getInstallationFailureMessage();
                if (msg != null) {
                    msg = String.format("Code installation failed: %s%n%s", resultDesc, msg);
                } else {
                    msg = String.format("Code installation failed: %s", resultDesc);
                }
                throw new BailoutException(result >= config.codeInstallResultFirstPermanentBailout, msg);
            } else {
                throw new BailoutException("Error installing %s: %s", ((HotSpotCompiledCode) compiledCode).getName(), resultDesc);
            }
        }
        return logOrDump(resultInstalledCode, compiledCode);
    }

    @Override
    public void invalidateInstalledCode(InstalledCode installedCode) {
        if (installedCode instanceof HotSpotNmethod) {
            runtime.getCompilerToVM().invalidateHotSpotNmethod((HotSpotNmethod) installedCode);
        } else {
            throw new IllegalArgumentException("Cannot invalidate a " + Objects.requireNonNull(installedCode).getClass().getName());
        }
    }

    @Override
    public TargetDescription getTarget() {
        return target;
    }

    public String disassemble(InstalledCode code) {
        if (code.isValid()) {
            return runtime.getCompilerToVM().disassembleCodeBlob(code);
        }
        return null;
    }

    @Override
    public SpeculationLog createSpeculationLog() {
        return new HotSpotSpeculationLog();
    }

    @Override
    public long getMaxCallTargetOffset(long address) {
        return runtime.getCompilerToVM().getMaxCallTargetOffset(address);
    }

    @Override
    public boolean shouldDebugNonSafepoints() {
        return runtime.getCompilerToVM().shouldDebugNonSafepoints();
    }

    public int interpreterFrameSize(BytecodeFrame pos) {
        return runtime.getCompilerToVM().interpreterFrameSize(pos);
    }

    /**
     * Resets all compilation statistics.
     */
    public void resetCompilationStatistics() {
        runtime.getCompilerToVM().resetCompilationStatistics();
    }
}
