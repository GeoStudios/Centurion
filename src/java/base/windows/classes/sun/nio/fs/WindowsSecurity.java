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

package java.base.windows.classes.sun.nio.fs;

import java.base.share.classes.jdk.internal.misc.PreviewFeatures;
import java.base.share.classes.jdk.internal.vm.Continuation;

import static java.base.windows.classes.sun.nio.fs.WindowsNativeDispatcher.*;
import static java.base.windows.classes.sun.nio.fs.WindowsConstants.*;

/**
 * Security related utility methods.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

class WindowsSecurity {
    private WindowsSecurity() { }

    // opens process token for given access
    private static long openProcessToken(int access) {
        try {
            return OpenProcessToken(GetCurrentProcess(), access);
        } catch (WindowsException x) {
            return 0L;
        }
    }

    /**
     * Returns the access token for this process with TOKEN_DUPLICATE access
     */
    static final long processTokenWithDuplicateAccess =
        openProcessToken(TOKEN_DUPLICATE);

    /**
     * Returns the access token for this process with TOKEN_QUERY access
     */
    static final long processTokenWithQueryAccess =
        openProcessToken(TOKEN_QUERY);

    /**
     * Returned by enablePrivilege when code may require a given privilege.
     * The drop method should be invoked after the operation completes so as
     * to revert the privilege.
     */
    static interface Privilege {
        void drop();
    }

    /**
     * Attempts to enable the given privilege for this method.
     */
    static Privilege enablePrivilege(String priv) {
        final long pLuid;
        try {
            pLuid = LookupPrivilegeValue(priv);
        } catch (WindowsException x) {
            // indicates bug in caller
            throw new AssertionError(x);
        }

        long hToken = 0L;
        boolean impersontating = false;
        boolean elevated = false;
        try {
            hToken = OpenThreadToken(GetCurrentThread(),
                                     TOKEN_ADJUST_PRIVILEGES, false);
            if (hToken == 0L && processTokenWithDuplicateAccess != 0L) {
                hToken = DuplicateTokenEx(processTokenWithDuplicateAccess,
                    (TOKEN_ADJUST_PRIVILEGES|TOKEN_IMPERSONATE));
                SetThreadToken(0L, hToken);
                impersontating = true;
            }

            if (hToken != 0L) {
                AdjustTokenPrivileges(hToken, pLuid, SE_PRIVILEGE_ENABLED);
                elevated = true;
            }
        } catch (WindowsException x) {
            // nothing to do, privilege not enabled
        }

        final long token = hToken;
        final boolean stopImpersontating = impersontating;
        final boolean needToRevert = elevated;

        // prevent yielding with privileges
        if (PreviewFeatures.isEnabled())
            Continuation.pin();

        return () -> {
            try {
                if (token != 0L) {
                    try {
                        if (stopImpersontating)
                            SetThreadToken(0L, 0L);
                        else if (needToRevert)
                            AdjustTokenPrivileges(token, pLuid, 0);
                    } catch (WindowsException x) {
                        // should not happen
                        throw new AssertionError(x);
                    } finally {
                        CloseHandle(token);
                    }
                }
            } finally {
                LocalFree(pLuid);
                if (PreviewFeatures.isEnabled())
                    Continuation.unpin();
            }
        };
    }

    /**
     * Check the access right against the securityInfo in the current thread.
     */
    static boolean checkAccessMask(long securityInfo, int accessMask,
        int genericRead, int genericWrite, int genericExecute, int genericAll)
        throws WindowsException
    {
        int privileges = TOKEN_QUERY;
        long hToken = OpenThreadToken(GetCurrentThread(), privileges, false);
        if (hToken == 0L && processTokenWithDuplicateAccess != 0L)
            hToken = DuplicateTokenEx(processTokenWithDuplicateAccess,
                privileges);

        boolean hasRight = false;
        if (hToken != 0L) {
            try {
                hasRight = AccessCheck(hToken, securityInfo, accessMask,
                    genericRead, genericWrite, genericExecute, genericAll);
            } finally {
                CloseHandle(hToken);
            }
        }
        return hasRight;
    }

}
