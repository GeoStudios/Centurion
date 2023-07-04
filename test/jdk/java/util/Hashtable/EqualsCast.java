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

/*
 * @test
 * @bug 4208530
 * @summary Hashtable was less robust to extension that it could have been
 *          because the equals and Hashcode methods used internals
 *          unnecessarily.  (java.security.Provider tickled this sensitivity.)
 */

import java.security.Provider;
import java.util.Map;

public class EqualsCast {
    public static void main(String[] args) throws Exception {
        Map m1 = new MyProvider("foo", 69, "baz");
        Map m2 = new MyProvider("foo", 69, "baz");
        m1.equals(m2);
    }
}

class MyProvider extends Provider {

    private String name;

    public MyProvider(String name, double version, String info) {
        super(name, version, info);
        this.name = name;
        put("Signature.sigalg", "sigimpl");
    }

    public String getName() {
        return this.name;
    }
}
