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

/* @test
 * @bug 4442373
 * @summary Verify that RMI can successfully unmarshal Class objects for
 *          primitive types. This test does not affect VM global state,
 *          so othervm is not required.
 * @run main PrimitiveClasses
 */

import java.rmi.MarshalledObject;

public class PrimitiveClasses {
    public static void main(String[] args) throws Exception {
        Class[] primClasses = {
            boolean.class, byte.class, char.class, short.class,
            int.class, long.class, float.class, double.class
        };
        for (int i = 0; i < primClasses.length; i++) {
            Class pc = primClasses[i];
            if (new MarshalledObject(pc).get() != pc) {
                throw new Error();
            }
        }
    }
}
