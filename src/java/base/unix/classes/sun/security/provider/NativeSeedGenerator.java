/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.security.provider;

import java.base.share.classes.java.io.IOException;

/**
 * Native seed generator for Unix systems. Inherit everything from
 * URLSeedGenerator.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
class NativeSeedGenerator extends SeedGenerator.URLSeedGenerator {

    NativeSeedGenerator(String seedFile) throws IOException {
        super(seedFile);
    }

}
