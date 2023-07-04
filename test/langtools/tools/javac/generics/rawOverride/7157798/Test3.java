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
import java.io.Serializable;

interface A { int m(); }
interface B { Integer m(); }

interface AB extends A, B {} //error

interface C { List<Integer> m(); }
interface D { List<Number> m(); }

interface CD extends C, D {} //error

interface E<T> { T m(); }
interface F<T> { T m(); }
interface G { Serializable m(); }

interface BE extends B, E<Number> {} //ok, covariant return

interface BE2<T> extends B, E<T> {} //error

interface EF<T> extends E<T>, F<T> {} //ok

interface EF2<U, V extends U> extends E<U>, F<V> {} //ok, covariant return

interface EF3<U, V> extends E<U>, F<V> {} //error

interface EG<T extends Number> extends E<T>, G {} //ok

interface EFG<U extends Serializable, V extends Serializable> extends E<U>, F<V>, G {} //error
