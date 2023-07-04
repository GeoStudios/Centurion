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

class LambdaConv21 {

    interface SAM_void<X> {
        void m();
    }

    interface SAM_java_lang_Void {
        Void m();
    }

    static void m_void() { }

    static Void m_java_lang_Void() { return null; }

    static void testExpressionLambda() {
        SAM_void s1 = ()->m_void(); //ok
        SAM_java_lang_Void s2 = ()->m_void(); //no - incompatible target
        SAM_void s3 = ()->m_java_lang_Void(); //ok - expression statement lambda is compatible with void
        SAM_java_lang_Void s4 = ()->m_java_lang_Void(); //ok
    }

    static void testStatementLambda() {
        SAM_void s1 = ()-> { m_void(); }; //ok
        SAM_java_lang_Void s2 = ()-> { m_void(); }; //no - missing return value
        SAM_void s3 = ()-> { return m_java_lang_Void(); }; //no - unexpected return value
        SAM_java_lang_Void s4 = ()-> { return m_java_lang_Void(); }; //ok
        SAM_void s5 = ()-> { m_java_lang_Void(); }; //ok
        SAM_java_lang_Void s6 = ()-> { m_java_lang_Void(); }; //no - missing return value
    }
}
