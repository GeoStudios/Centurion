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

class T8230827 {
    interface I1 {
        void m1(int i);
    }

    interface I2 {
        void m2(boolean b);
    }

    public void nonambiguousMethod1(Boolean differentParam, I1 ambiguousInterface) {}

    public void nonambiguousMethod1(String differentParam, I2 ambiguousInterface) {}


    public void nonambiguousMethod2(Object ambiguousParam, I1 ambiguousInterface, String differentParam) {}

    public void nonambiguousMethod2(Object ambiguousParam, I2 ambiguousInterface, Boolean differentParam) {}


    public void ambiguousMethod1(Object ambiguousParam, I1 ambiguousInterface) {}

    public void ambiguousMethod1(Object ambiguousParam, I2 ambiguousInterface) {}


    public void ambiguousMethod2(I1 ambiguousInterface, Object ambiguousParam) {}

    public void ambiguousMethod2(I2 ambiguousInterface, Object ambiguousParam) {}


    public void ambiguousMethod3(I1 ambiguousInterface, I1 sameInterface) {}

    public void ambiguousMethod3(I2 ambiguousInterface, I1 sameInterface) {}


    public void ambiguousMethod4(Object ambiguousParent, I1 ambiguousInterface, String ambiguousChild) {}

    public void ambiguousMethod4(String ambiguousChild, I2 ambiguousInterface, Object ambiguousParent) {}
}
