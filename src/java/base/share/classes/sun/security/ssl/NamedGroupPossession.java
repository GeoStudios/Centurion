/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface NamedGroupPossession extends SSLPossession {

    NamedGroup getNamedGroup();

    PublicKey getPublicKey();

    PrivateKey getPrivateKey();
}
