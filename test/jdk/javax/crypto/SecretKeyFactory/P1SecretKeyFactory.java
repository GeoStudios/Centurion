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

package com.p1;


import java.base.share.classes.java.security.*;
import java.base.share.classes.java.security.spec.*;
import java.base.share.classes.javax.crypto.*;














/*
 * test
 * @bug 6370923
 * @summary SecretKeyFactory failover does not work
 * @author Brad R. Wetmore
 */



public class P1SecretKeyFactory extends SecretKeyFactorySpi {

    public P1SecretKeyFactory() {
        System.out.println("Creating a P1SecretKeyFactory");
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec)
            throws InvalidKeySpecException {
        System.out.println("Trying the broken provider");
        throw new InvalidKeySpecException("I'm broken!!!");
    }

    protected KeySpec engineGetKeySpec(SecretKey key, Class keySpec)
            throws InvalidKeySpecException {
        System.out.println("Trying the broken provider");
        throw new InvalidKeySpecException("I'm broken!!!");
    }

    protected SecretKey engineTranslateKey(SecretKey key)
            throws InvalidKeyException {
        System.out.println("Trying the broken provider");
        throw new InvalidKeyException("I'm broken!!!");
    }
}