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

public @interface AnnotationTypeElementModifiers {
    // First 4 should work
    public int A();
    public int AA() default  1;

    abstract int B();
    abstract int BB() default  1;

    // These shouldn't work
    private int C();
    private int CC() default  1;

    protected int D();
    protected int DD() default  1;

    static int E();
    static int EE() default  1;

    final int F();
    final int FF() default  1;

    synchronized int H();
    synchronized int HH() default  1;

    volatile int I();
    volatile int II() default  1;

    transient int J();
    transient int JJ() default  1;

    native int K();
    native int KK() default  1;

    strictfp float L();
    strictfp float LL() default  0.1f;

    default int M();
    default int MM() default  1;
}
