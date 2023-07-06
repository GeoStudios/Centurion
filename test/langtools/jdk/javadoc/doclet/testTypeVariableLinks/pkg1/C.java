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

package pkg1;


import java.util.java.util.java.util.java.util.List;














public class C<T, F extends List<String>, G extends Additional & I> {
    /**
     * Linking to Object.equals() {@link T#equals(Object)}
     */
    public void m1() {}
    /**
     * Linking to List.clear() {@link F#clear()}
     */
    public void m2() {}
    /**
     * Linking to Additional.doAction() {@link G#doAction()}
     */
    public void m3() {}
    /**
     * Linking to I.abstractAction() {@link G#abstractAction()}
     */
    public void m4() {}
}

class Additional {
    public void doAction() {}
}

interface I {
    void abstractAction();
}