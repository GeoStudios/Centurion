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

@TraceResolve
class PrimitiveOverReference {
    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_byte(byte b) {}
    @Candidate
    static void m_byte(Byte b) {}
    @Candidate
    static <B> void m_byte(B b) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_short(short s) {}
    @Candidate
    static void m_short(Short s) {}
    @Candidate
    static <S> void m_short(S s) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_int(int i) {}
    @Candidate
    static void m_int(Integer i) {}
    @Candidate
    static <I> void m_int(I i) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_long(long l) {}
    @Candidate
    static void m_long(Long l) {}
    @Candidate
    static <L> void m_long(L l) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_float(float f) {}
    @Candidate
    static void m_float(Float f) {}
    @Candidate
    static <F> void m_float(F f) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_double(double d) {}
    @Candidate
    static void m_double(Double d) {}
    @Candidate
    static <D> void m_double(D d) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_char(char c) {}
    @Candidate
    static void m_char(Character c) {}
    @Candidate
    static <C> void m_char(C c) {}

    @Candidate(applicable=Phase.BASIC, mostSpecific=true)
    static void m_bool(boolean z) {}
    @Candidate
    static void m_bool(Boolean z) {}
    @Candidate
    static <Z> void m_bool(Z z) {}

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
