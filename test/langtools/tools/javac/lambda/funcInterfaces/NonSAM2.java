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

import java.util.List;

interface Foo1 { int getAge(String s);}
interface Bar1 { Integer getAge(String s);}
interface Foo1Bar1 extends Foo1, Bar1 {} //types Bar1 and Foo1 are incompatible; both define getAge(String), but with unrelated return types

interface AC extends A, C {} //name clash: getOldest(List<?>) in C and getOldest(List<Number>) in A have the same erasure, yet neither overrides the other
interface ABC extends A, B, C {} //ok - raw override
interface AD extends A, D {} //name clash: getOldest(List<Integer>) in D and getOldest(List<Number>) in A have the same erasure, yet neither overrides the other

interface Foo2<T> { void m(T arg);}
interface Bar2<S> { void m(S arg);}
interface Foo2Bar2<T1, T2> extends Foo2<T1>, Bar2<T2> {} //name clash: m(S) in Bar and m(T) in Foo have the same erasure, yet neither overrides the other
