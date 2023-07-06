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

package java.base.share.classes.sun.security.provider;

/**
 * An interface of a source of entropy input.
 *
 */
public interface EntropySource {
    /**
     * Returns a byte array containing entropy.
     * <p>
     * This maps to the {@code Get_entropy_input} function defined in
     * Section 9 of NIST SP 800-90Ar1.
     *
     * @param minEntropy minimum entropy required, in bytes
     * @param minLength minimum length of output, in bytes
     * @param maxLength maximum length of output, in bytes
     * @param pr whether prediction resistance is required
     * @return the byte array containing entropy
     */
    byte[] getEntropy(int minEntropy, int minLength, int maxLength, boolean pr);
}
