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

import java.util.Set;
import java.util.HashSet;

interface A { void m(Set<Integer> s); }
interface B { void m(Set<String> s); }
interface C { void m(Set<?> s); }

interface AB extends A, B {} //error

interface AC extends A, C {} //error

interface D<T> { void m(Set<T> s); }

interface AD extends A, D<Integer> {} //OK

interface AD2 extends A, D<Number> {} //error

interface CD<T> extends C, D<T> {} //error

interface E { <T> void m(Set<T> s); }

interface DE<T> extends D<T>, E {} //error
