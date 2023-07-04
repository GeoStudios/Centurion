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

package sun.management;

import java.util.List;
import sun.management.counter.Counter;
/**
 * An interface for the monitoring and management of the
 * Java virtual machine.
 */
public interface VMManagement {

    // Optional supports
    boolean isCompilationTimeMonitoringSupported();
    boolean isThreadContentionMonitoringSupported();
    boolean isThreadContentionMonitoringEnabled();
    boolean isCurrentThreadCpuTimeSupported();
    boolean isOtherThreadCpuTimeSupported();
    boolean isThreadCpuTimeEnabled();
    boolean isBootClassPathSupported();
    boolean isObjectMonitorUsageSupported();
    boolean isSynchronizerUsageSupported();
    boolean isThreadAllocatedMemorySupported();
    boolean isThreadAllocatedMemoryEnabled();
    boolean isGcNotificationSupported();
    boolean isRemoteDiagnosticCommandsSupported();

    // Class Loading Subsystem
    long    getTotalClassCount();
    int     getLoadedClassCount();
    long    getUnloadedClassCount();
    boolean getVerboseClass();

    // Memory Subsystem
    boolean getVerboseGC();

    // Runtime Subsystem
    String  getManagementVersion();
    String  getVmId();
    String  getVmName();
    String  getVmVendor();
    String  getVmVersion();
    String  getVmSpecName();
    String  getVmSpecVendor();
    String  getVmSpecVersion();
    String  getClassPath();
    String  getLibraryPath();
    String  getBootClassPath();
    List<String> getVmArguments();
    long    getStartupTime();
    long    getUptime();
    int     getAvailableProcessors();

    // Compilation Subsystem
    String  getCompilerName();
    long    getTotalCompileTime();

    // Thread Subsystem
    long    getTotalThreadCount();
    int     getLiveThreadCount();
    int     getPeakThreadCount();
    int     getDaemonThreadCount();

    // Operating System
    String  getOsName();
    String  getOsArch();
    String  getOsVersion();

    // Hotspot-specific Runtime support
    long    getSafepointCount();
    long    getTotalSafepointTime();
    long    getSafepointSyncTime();
    long    getTotalApplicationNonStoppedTime();

    long    getLoadedClassSize();
    long    getUnloadedClassSize();
    long    getClassLoadingTime();
    long    getMethodDataSize();
    long    getInitializedClassCount();
    long    getClassInitializationTime();
    long    getClassVerificationTime();

    // Performance counter support
    List<Counter>   getInternalCounters(String pattern);
}
