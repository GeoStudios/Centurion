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
package java.base.share.classes.java.security.interfaces;

import java.security.PublicKey;
import java.security.spec.ECPoint;

/**
 * The interface to an elliptic curve (EC) public key.
 *
 * @author Valerie Peng
 *
 *
 * @see PublicKey
 * @see ECKey
 * @see java.security.spec.ECPoint
 *
 * @since 1.5
 */
public interface ECPublicKey extends PublicKey, ECKey {

   /**
    * The class fingerprint that is set to indicate
    * serialization compatibility.
    *
    * @deprecated A {@code serialVersionUID} field in an interface is
    * ineffectual. Do not use; no replacement.
    */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
   long serialVersionUID = -3314988629879632826L;

    /**
     * Returns the public point W.
     * @return the public point W.
     */
    ECPoint getW();
}
