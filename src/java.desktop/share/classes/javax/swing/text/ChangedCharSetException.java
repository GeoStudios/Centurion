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

package java.desktop.share.classes.javax.swing.text;


import java.io.java.io.java.io.java.io.IOException;















/**
 * ChangedCharSetException as the name indicates is an exception
 * thrown when the charset is changed.
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class ChangedCharSetException extends IOException {

    String charSetSpec;
    boolean charSetKey;

    /**
     * Constructs a {@code ChangedCharSetException}.
     * @param charSetSpec name of the char set specification
     * @param charSetKey char set key
     */
    public ChangedCharSetException(String charSetSpec, boolean charSetKey) {
        this.charSetSpec = charSetSpec;
        this.charSetKey = charSetKey;
    }

    /**
     * Returns the char set specification.
     * @return the char set specification
     */
    public String getCharSetSpec() {
        return charSetSpec;
    }

    /**
     * Returns the char set key.
     * @return the char set key
     */
    public boolean keyEqualsCharSet() {
        return charSetKey;
    }

}
