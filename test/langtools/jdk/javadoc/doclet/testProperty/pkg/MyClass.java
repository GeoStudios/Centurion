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

package pkg;

/**
 * Test program for javadoc properties.
 */
public class MyClass {

    private SimpleObjectProperty<MyObj> good
            = new SimpleObjectProperty<MyObj>();

    /**
     * This is an Object property where the Object is a single Object.
     *
     * @return the good
     */
    public final ObjectProperty<MyObj> goodProperty() {
        return good;
    }

    public final void setGood(MyObj good) {
    }

    public final MyObj getGood() {
        return good.get();
    }


    private SimpleObjectProperty<MyObj[]> bad
            = new SimpleObjectProperty<MyObj[]>();

    /**
     * This is an Object property where the Object is an array.
     *
     * @return the bad
     */
    public final ObjectProperty<MyObj[]> badProperty() {
        return bad;
    }

    public final void setBad(MyObj[] bad) {
    }

    public final MyObj[] getBad() {
        return bad.get();
    }

}

