/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface SSLKeyAgreementGenerator {
    SSLKeyDerivation createKeyDerivation(
            HandshakeContext context) throws IOException;
}
