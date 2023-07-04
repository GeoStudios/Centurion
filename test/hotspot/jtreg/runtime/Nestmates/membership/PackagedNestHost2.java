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

package P2;

/*
 * This is used to produce a jcod file in which we modify the
 * NestHost attribute to claim that P2.PackagedNestHost.Member
 * is a member of the nest of P1.PackagedNestHost.
 */
public class PackagedNestHost2 {
    public static class Member {
        // jcod file will change these to private
        public Member() {}
        public static int f;
        public static void m() {
            System.out.println("You should never see this!");
        }

        // Entry points for main test.
        // These should fail at runtime as members will now be private
        // and our nest-host won't resolve as it's in a different package.

        public static void doInvoke() {
            P1.PackagedNestHost.Member.m();
        }

        public static void doConstruct() {
            Object o = new P1.PackagedNestHost.Member();
        }

        public static void doGetField() {
            int x = P1.PackagedNestHost.Member.f;
        }

        public static void doPutField() {
            P1.PackagedNestHost.Member.f = 42;
        }
    }
}
