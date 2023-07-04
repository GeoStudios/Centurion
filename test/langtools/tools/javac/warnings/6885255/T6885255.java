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

class T6885255 {

    static class Test<X, Y> {}

    Class<Test> ct; //no warn - outer Class w/ raw param
    Class<Test<Test, Test>> ctt; //warn - outer Class w/o raw param (2)

    Class<Class<Test>> cct; //warn - outer Class w/o raw param
    Class<Class<Test<Test, Test>>> cctt; //warn - outer Class w/o raw param (2)

    Object o1 = (Test)null; //no warn - outer raw and cast
    Object o2 = (Test<Test, Test>)null; //warn - inner raw (2)

    Object o3 = (Class)null; //no warn - outer raw and cast
    Object o4 = (Class<Test>)null; //no warn - outer Class w/ raw param

    Object o5 = (Class<Test<Test, Test>>)null; //warn - outer Class w/ non raw param (2)
    Object o6 = (Class<Class<Test<Test, Test>>>)null; //warn - outer Class w/ non raw param (2)

    Object o7 = (Test<Class, Class>)null; //warn - inner raw (2)
    Object o8 = (Test<Class<Test>, Class<Test>>)null; //warn - inner Class (2)

    boolean b = null instanceof Test; //no warn - raw and instanceof
}
