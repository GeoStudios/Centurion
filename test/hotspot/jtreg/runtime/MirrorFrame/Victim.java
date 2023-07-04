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

public class Victim implements Test8003720.CallMe {
    public void callme() {
        // note: Victim.this is dead here
        Test8003720.println("executing in loader=" + Victim.class.getClassLoader());

        long now = System.currentTimeMillis();

        while ((System.currentTimeMillis() - now) < Test8003720.DURATION) {
            long count = VictimClassLoader.counter++;
            if (count %  1000000 == 0)  System.gc();
            if (count % 16180000 == 0)  blurb();
            new Object[1].clone();
        }
    }
    static void blurb() {
        Test8003720.println("count=" + VictimClassLoader.counter);
    }
    static {
        blather();
    }
    static void blather() {
        new java.util.ArrayList<Object>(1000000);
        Class<Victim> c = Victim.class;
        Test8003720.println("initializing " + c + "#" + System.identityHashCode(c) + " in " + c.getClassLoader());
    }
}
