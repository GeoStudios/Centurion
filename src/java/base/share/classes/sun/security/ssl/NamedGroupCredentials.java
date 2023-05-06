/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.security.PublicKey;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface NamedGroupCredentials extends SSLCredentials {

    PublicKey getPublicKey();

    NamedGroup getNamedGroup();

}
