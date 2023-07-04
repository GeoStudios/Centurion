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
 * The classfile does not fail verification but would fail when
 * getMethod
 */
public class HiddenCantReflect implements HiddenTest {

    HiddenCantReflect other = null;

    private String realTest() {
        Object o = other;
        HiddenCantReflect local = this;
        local = other;
        local = (HiddenCantReflect) o;
        local = new HiddenCantReflect();

        set_other(null);

        local = getThis();

        set_other_maybe(new Object());
        set_other_maybe(this);
        return "HiddenCantReflect";
    }

    private HiddenCantReflect getThis() {
        return null;
    }

    private void set_other(HiddenCantReflect t) {
        other = t;
    }

    private void set_other_maybe(Object o) {
        if (o instanceof HiddenCantReflect) {
        }
    }

    public void test() {
        String result = realTest();
        // Make sure that the Utf8 constant pool entry for "HiddenCantReflect" is okay.
        if (!result.substring(0, 7).equals("HiddenC") ||
            !result.substring(7).equals("antReflect")) {
            throw new RuntimeException("'HiddenCantReflect string is bad: " + result);
        }

    }
}
