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

// WARNING: This file needs to be compiled with the -deprecation flag on.
// DeprecatedDocCommentTest2.java in test/tools/javac/depDocComment/
// should be compiled first before this file can be compiled. This is because
// the compiler *does not* issue deprecation warnings for a file currently
// being compiled.

// The test passes iff the compile issues deprecation warnings for
// deprecatedTest 1, 5, and 6; and fails with an unclosed comment error
// The test does not need to be run.

//import depDocComment.*;

public class DeprecatedDocComment {

    public static void main(String argv[]) {
      DeprecatedDocComment2.deprecatedTest1();
      DeprecatedDocComment2.deprecatedTest2();
      DeprecatedDocComment2.deprecatedTest3();
      DeprecatedDocComment2.deprecatedTest4();
      DeprecatedDocComment2.deprecatedTest5();
      DeprecatedDocComment2.deprecatedTest6();
      DeprecatedDocComment2.deprecatedTest7();
      DeprecatedDocComment2.deprecatedTest8();
    }

}
