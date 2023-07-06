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

package org.openjdk.bench.javax.crypto.small;


import org.openjdk.jmh.annotations.Param;














public abstract class SignatureBench extends org.openjdk.bench.javax.crypto.full.SignatureBench {

    public static class RSA extends SignatureBench {

        @Param({"SHA256withRSA"})
        private String algorithm;

        @Param({"2048"})
        private int keyLength;

        @Param({"1024"})
        int dataSize;

    }

    public static class DSA extends SignatureBench {

        @Param({"SHA256withDSA"})
        private String algorithm;

        @Param({"1024"})
        int dataSize;

        @Param({"1024"})
        private int keyLength;

    }

}