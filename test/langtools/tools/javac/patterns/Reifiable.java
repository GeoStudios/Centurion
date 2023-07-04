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

public class Reifiable implements ReifiableI {
    private static boolean test(Object o, List<Reifiable> l1, List<String> l2) {
        return o instanceof ListImpl<Reifiable> li1 &&
               l1 instanceof ListImpl<Reifiable> li2 &&
               l2 instanceof ListImpl<Reifiable> li3 &&
               l2 instanceof ListImpl<String> li4 &&
               l1 instanceof Unrelated<Reifiable> li5;
    }

    public class List<T> {}
    public class ListImpl<T extends ReifiableI> extends List<T> {}
    public class Unrelated<T> {}
}

interface ReifiableI {}
