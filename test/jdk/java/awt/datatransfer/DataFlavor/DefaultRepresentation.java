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

import java.awt.datatransfer.DataFlavor;
import java.io.InputStream;

/**
 * @test
 * @bug 8259519
 * @summary InputStream should be used as a default data flavor representation
 */
public final class DefaultRepresentation extends InputStream {

    public static void main(String[] args) {
        DataFlavor df = new DataFlavor(DefaultRepresentation.class, "stream");
        if (df.getDefaultRepresentationClass() != InputStream.class) {
            // default representation class is not specified!
            // this check just defends against accidental changes
            throw new RuntimeException("InputStream class is expected");
        }
        if (!df.isRepresentationClassInputStream()) {
            throw new RuntimeException("true is expected");
        }
    }

    @Override
    public int read() {
        return 0;
    }
}
