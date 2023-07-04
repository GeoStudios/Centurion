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

@TraceResolve(keys={"compiler.err.ref.ambiguous"})
class PrimitiveOverReferenceVarargsAmbiguous {
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_byte(byte... b) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_byte(Byte... b) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_short(short... s) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_short(Short... s) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_int(int... i) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_int(Integer... i) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_long(long... l) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_long(Long... l) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_float(float... f) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_float(Float... f) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_double(double... d) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_double(Double... d) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_char(char... c) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_char(Character... c) {}

    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_bool(boolean... z) {}
    @Candidate(applicable=Phase.VARARGS, mostSpecific=false)
    static void m_bool(Boolean... z) {}

    {
        m_byte((byte)0);
        m_short((short)0);
        m_int(0);
        m_long(0L);
        m_float(0.0f);
        m_double(0.0);
        m_char('?');
        m_bool(false);
    }
}
