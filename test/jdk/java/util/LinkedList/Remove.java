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
 * @bug 4147946
 * @summary Due to a bug in LinkedList's ListIterator's remove() logic, the List would
 *          get screwed up by a remove() following a previous().
 */

import java.util.LinkedList;
import java.util.ListIterator;

public class Remove {
    public static
    void main(String[] args) {
        LinkedList list = new LinkedList();
        ListIterator e = list.listIterator();
        Object o = new Integer(1);
        e.add(o);
        e.previous();
        e.next();
        e.remove();
        e.add(o);
        if (!o.equals(list.get(0)))
            throw new RuntimeException("LinkedList ListIterator remove failed.");
    }
}
