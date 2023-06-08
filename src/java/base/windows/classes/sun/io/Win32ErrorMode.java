/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.windows.classes.sun.io;

/**
 * Used to set the Windows error mode at VM initialization time.
 * <p>
 * The error mode decides whether the system will handle specific types of serious errors
 * or whether the process will handle them.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */
public class Win32ErrorMode {

    // The system does not display the critical-error-handler message box. Instead,
    // the system sends the error to the calling process.
    private static final long SEM_FAILCRITICALERRORS     = 0x0001;

    // The system does not display the general-protection-fault message box. This flag should
    // only be set by debugging applications that handle general protection (GP) faults themselves
    // with an exception handler.
    private static final long SEM_NOGPFAULTERRORBOX      = 0x0002;

    // The system automatically fixes memory alignment faults and makes them invisible
    // to the application. It does this for the calling process and any descendant processes.
    private static final long SEM_NOALIGNMENTFAULTEXCEPT = 0x0004;

    // The system does not display a message box when it fails to find a file. Instead,
    // the error is returned to the calling process.
    private static final long SEM_NOOPENFILEERRORBOX     = 0x8000;

    private Win32ErrorMode() {
    }

    /**
     * Invoke at VM initialization time to disable the critical error message box.
     * <p>
     * The critial error message box is disabled unless the system property
     * {@code sun.io.allowCriticalErrorMessageBox} is set to something other than
     * {@code false}. This includes the empty string.
     * <p>
     * This method does nothing if invoked after VM and class library initialization
     * has completed.
     */
    public static void initialize() {
        if (!jdk.internal.misc.VM.isBooted()) {
            String s = System.getProperty("sun.io.allowCriticalErrorMessageBox");
            if (s == null || s.equals(Boolean.FALSE.toString())) {
                long mode = setErrorMode(0);
                mode |= SEM_FAILCRITICALERRORS;
                setErrorMode(mode);
            }
        }
    }

    // Win32 SetErrorMode
    private static native long setErrorMode(long mode);
}