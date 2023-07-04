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

/**
 * Complex example for the "access constructor tags" issue.
 * This test causes Lower to evaluate all classes defined in installNext
 * before they are lowered, thus preventing the use of Lower.classdefs
 * as a way of determining whether a class has been translated or not.
 */
class GraphicalInstaller  {
    private BackgroundInstaller backgroundInstaller;

    private void installNext() {
        final Integer x = 0;
        class X {
          Object o = new Object() { int y = x; };
        };
        new X();
        if (false) {
            new Runnable(){
                public void run() {
                }
            };
        }
    }

    private void installSuiteCommon() {
        backgroundInstaller = new BackgroundInstaller();
    }

    private static class BackgroundInstaller {
        private BackgroundInstaller() {
        }
    }
}
