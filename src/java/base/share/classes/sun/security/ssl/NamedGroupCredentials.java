/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.security.PublicKey;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface NamedGroupCredentials extends SSLCredentials {

    PublicKey getPublicKey();

    NamedGroup getNamedGroup();

}
