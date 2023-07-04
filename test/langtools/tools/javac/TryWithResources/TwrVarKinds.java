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

public class TwrVarKinds implements AutoCloseable {

    final static TwrVarKinds r1 = new TwrVarKinds();
    final TwrVarKinds r2 = new TwrVarKinds();
    static TwrVarKinds r3 = new TwrVarKinds();
    TwrVarKinds r4 = new TwrVarKinds();

    public static void main(String... args) {

        TwrVarKinds r5 = new TwrVarKinds();

        /* static final field - ok */
        try (r1) {
        }

        /* non-static final field - ok */
        try (r1.r2) {
        }

        /* static non-final field - wrong */
        try (r3) {
            fail("Static non-final field is not allowed");
        }

        /* non-static non-final field - wrong */
        try (r1.r4) {
            fail("Non-static non-final field is not allowed");
        }

        /* local variable - covered by TwrForVariable1 test */

        /* array components - covered by TwrForVariable2 test */

        /* method parameter - ok */
        method(r5);

        /* constructor parameter - ok */
        TwrVarKinds r6 = new TwrVarKinds(r5);

        /* lambda parameter - covered by TwrAndLambda */

        /* exception parameter - ok */
        try {
            throw new ResourceException();
        } catch (ResourceException e) {
            try (e) {
            }
        }
    }

    public TwrVarKinds() {
    }

    public TwrVarKinds(TwrVarKinds r) {
        try (r) {
        }
    }

    static void method(TwrVarKinds r) {
        /* parameter */
        try (r) {
        }
    }

    static void fail(String reason) {
        throw new RuntimeException(reason);
    }

    public void close() {}

    static class ResourceException extends Exception implements AutoCloseable {
        public void close() {}
    }
}
