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
package jdk.internal.org.jline.reader.impl;

import jdk.internal.org.jline.reader.MaskingCallback;

import java.util.Objects;

/**
 * Simple {@link MaskingCallback} that will replace all the characters in the line with the given mask.
 * If the given mask is equal to {@link LineReaderImpl#NULL_MASK} then the line will be replaced with an empty String.
 */
public final class SimpleMaskingCallback implements MaskingCallback {
    private final Character mask;

    public SimpleMaskingCallback(Character mask) {
        this.mask = Objects.requireNonNull(mask, "mask must be a non null character");
    }

    @Override
    public String display(String line) {
        if (mask.equals(LineReaderImpl.NULL_MASK)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(line.length());
            for (int i = line.length(); i-- > 0;) {
                sb.append((char) mask);
            }
            return sb.toString();
        }
    }

    @Override
    public String history(String line) {
        return null;
    }

}
