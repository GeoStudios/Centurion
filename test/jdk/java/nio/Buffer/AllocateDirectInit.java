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

/**
 * @test
 * @bug 4490253 6535542
 * @summary Verify that newly allocated direct buffers are initialized.
 */

import java.nio.ByteBuffer;

public class AllocateDirectInit {
    public static void main(String [] args){
        for (int i = 0; i < 1024; i++) {
            ByteBuffer bb = ByteBuffer.allocateDirect(1024);
//          printByteBuffer(bb);
            for (bb.position(0); bb.position() < bb.limit(); ) {
                if ((bb.get() & 0xff) != 0)
                    throw new RuntimeException("uninitialized buffer, position = "
                                               + bb.position());
            }
        }
    }

    private static void printByteBuffer(ByteBuffer bb) {
        System.out.print("byte [");
        for (bb.position(0); bb.position() < bb.limit(); )
            System.out.print(" " + Integer.toHexString(bb.get() & 0xff));
        System.out.println(" ]");
    }
}
