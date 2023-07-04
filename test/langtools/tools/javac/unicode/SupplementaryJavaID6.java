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

/*
 * @test
 * @bug 4914724 4973116 5014511
 * @summary Ensure that a supplementary character can be used as part/whole of a
 * class name on platforms that have Unicode aware filesystems.
 * @modules jdk.compiler
 * @build Wrapper
 * @run main Wrapper SupplementaryJavaID6
 */

public class SupplementaryJavaID6 {
    public static void main(String[] s) {
        new SupplementaryJavaID6().test();
    }

    void test() {
        \ud801\udc00 instance = new \ud801\udc00();
        instance.\ud801\udc01();
    }

    class \ud801\udc00 {
        void \ud801\udc01() {
            // If Java can create the strangely named class file,
            // then Java can delete it, while `rm' might be unable to.
            new java.io.File(this.getClass().getName() + ".class")
                .deleteOnExit();
            System.out.println("success");
        }
    }
}
