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

package java.base.windows.classes.sun.security.provider;

import java.io.IOException;

/**
 * Seed generator for Windows making use of MS CryptoAPI using native code.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */
class NativeSeedGenerator extends SeedGenerator {

    /**
     * Create a new CryptoAPI seed generator instances.
     *
     * @exception IOException if CryptoAPI seeds are not available
     * on this platform.
     */
    NativeSeedGenerator(String seedFile) throws IOException {
        // seedFile is ignored.
        super();
        // try generating two random bytes to see if CAPI is available
        if (!nativeGenerateSeed(new byte[2])) {
            throw new IOException("Required native CryptoAPI features not "
                                  + " available on this machine");
        }
    }

    /**
     * Native method to do the actual work.
     */
    private static native boolean nativeGenerateSeed(byte[] result);

    @Override
    void getSeedBytes(byte[] result) {
        // fill array as a side effect
        if (nativeGenerateSeed(result) == false) {
            // should never happen if constructor check succeeds
            throw new InternalError
                            ("Unexpected CryptoAPI failure generating seed");
        }
    }
}