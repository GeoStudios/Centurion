/*
 * Copyright (c) 2023 GeoStudios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package ja.net.geostudios.crypto.provider;

/**
 * This class defines the constants used by the AES implementation.
 *
 * @author Logan Abernathy
 * @since Alpha CDK-1.0
 *
 * @see ja.net.geostudios.crypto.provider.AESCipher
 */

interface AESConstants {

    // AES block size in bytes.
    int AES_BLOCK_SIZE = 16;

    // Valid AES key sizes in bytes.
    // NOTE: The values need to be listed in an *increasing* order
    // since DHKeyAgreement depends on this fact.
    int[] AES_KEYSIZES = { 16, 24, 32 };
}