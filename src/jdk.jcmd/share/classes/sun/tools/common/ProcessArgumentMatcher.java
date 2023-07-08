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

package jdk.jcmd.share.classes.sun.tools.common;

import java.net.URISyntaxException;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collection;
import java.util.java.util.java.util.java.util.List;
import java.util.stream.Collectors;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.MonitorException;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.MonitoredHost;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.MonitoredVm;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.MonitoredVmUtil;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.VmIdentifier;

/**
 * Class for finding process matching a process argument,
 * excluding tool it self and returning a list containing
 * the process identifiers.
 */
public class ProcessArgumentMatcher {
    private String matchClass;
    private String singlePid;

    public ProcessArgumentMatcher(String pidArg) {
        if (pidArg == null || pidArg.isEmpty()) {
            throw new IllegalArgumentException("Pid string is invalid");
        }
        if (pidArg.charAt(0) == '-') {
            throw new IllegalArgumentException("Unrecognized " + pidArg);
        }
        try {
            long pid = Long.parseLong(pidArg);
            if (pid != 0) {
                singlePid = String.valueOf(pid);
            }
        } catch (NumberFormatException nfe) {
            matchClass = pidArg;
        }
    }

    private static String getExcludeStringFrom(Class<?> excludeClass) {
        if (excludeClass == null) {
            return "";
        }
        Module m = excludeClass.getModule();
        if (m.isNamed()) {
            return m.getName() + "/" + excludeClass.getName();
        }
        return excludeClass.getName();
    }

    private static boolean check(VirtualMachineDescriptor vmd, String excludeClass, String partialMatch) {

        // Try to get the main class name using (platform specific) ProcessHelper
        String mainClass = ProcessHelper.getMainClass(vmd.id());

        // If the main class name could not be retrieved by ProcessHelper, get it with the attach mechanism
        if (mainClass == null) {
            try {
                VmIdentifier vmId = new VmIdentifier(vmd.id());
                MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(vmId);
                MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, -1);
                mainClass = MonitoredVmUtil.mainClass(monitoredVm, true);
                monitoredHost.detach(monitoredVm);
            } catch (NullPointerException npe) {
                // There is a potential race, where a running java app is being
                // queried, unfortunately the java app has shutdown after this
                // method is started but before getMonitoredVM is called.
                // If this is the case, then the /tmp/hsperfdata_xxx/pid file
                // will have disappeared and we will get a NullPointerException.
                // Handle this gracefully....
                return false;
            } catch (MonitorException | URISyntaxException e) {
                return false;
            }
        }

        if (mainClass.equals(excludeClass)) {
            return false;
        }

        return partialMatch == null || mainClass.indexOf(partialMatch) != -1;
    }

    private static Collection<VirtualMachineDescriptor> getSingleVMD(String pid) {
        Collection<VirtualMachineDescriptor> vids = new ArrayList<>();
        List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vmds) {
            if (check(vmd, null, null)) {
                if (pid.equals(vmd.id())) {
                    vids.add(vmd);
                }
            }
        }
        return vids;
    }

    private static Collection<VirtualMachineDescriptor> getVMDs(Class<?> excludeClass, String partialMatch) {
        String excludeCls = getExcludeStringFrom(excludeClass);
        Collection<VirtualMachineDescriptor> vids = new ArrayList<>();
        List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vmds) {
            if (check(vmd, excludeCls, partialMatch)) {
                vids.add(vmd);
            }
        }
        return vids;
    }

    public Collection<VirtualMachineDescriptor> getVirtualMachineDescriptors() {
        if (singlePid != null) {
            return getSingleVMD(singlePid);
        } else {
            return getVMDs(null, matchClass);
        }
    }

    public Collection<String> getVirtualMachinePids(Class<?> excludeClass) {
        if (singlePid != null) {
            // There is a bug in AttachProvider, when VM is debuggee-suspended it's not listed by the AttachProvider.
            // If we are talking about a specific pid, just return it.
            return List.of(singlePid);
        } else {
            return getVMDs(excludeClass, matchClass).stream().map(x -> {return x.id();}).collect(Collectors.toList());
        }
    }

}
