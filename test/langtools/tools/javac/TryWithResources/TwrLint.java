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

class TwrLint implements AutoCloseable {
    private static void test1() {
        try(TwrLint r1 = new TwrLint();
            TwrLint r2 = new TwrLint();
            TwrLint r3 = new TwrLint()) {
            r1.close();   // The resource's close
            r2.close(42); // *Not* the resource's close
            // r3 not referenced
        }

    }

    @SuppressWarnings("try")
    private static void test2() {
        try(@SuppressWarnings("deprecation") AutoCloseable r4 =
            new DeprecatedAutoCloseable()) {
            // r4 not referenced - but no warning is generated because of @SuppressWarnings
        } catch(Exception e) {
            ;
        }
    }

    /**
     * The AutoCloseable method of a resource.
     */
    @Override
    public void close () {
        return;
    }

    /**
     * <em>Not</em> the AutoCloseable method of a resource.
     */
    public void close (int arg) {
        return;
    }
}

@Deprecated
class DeprecatedAutoCloseable implements AutoCloseable {
    public DeprecatedAutoCloseable(){super();}

    @Override
    public void close () {
        return;
    }
}
