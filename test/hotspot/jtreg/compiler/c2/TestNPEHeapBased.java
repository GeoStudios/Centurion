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
 * @test
 * @bug 8176518
 * @summary Invalid ImplicitNullChecks when heap base not protected
 *
 * @run main/othervm -XX:ObjectAlignmentInBytes=16 -XX:HeapBaseMinAddress=64g
 *      -XX:-TieredCompilation -Xbatch
 *      compiler.c2.TestNPEHeapBased
 * @requires vm.bits == "64"
 */

package compiler.c2;
public class TestNPEHeapBased {

    TestNPEHeapBased instance = null;
    int i = 0;

    public void set_i(int value) {
        instance.i = value;
    }


    static final int loop_cnt = 200000;

    public static void main(String args[]){
        TestNPEHeapBased xyz = new TestNPEHeapBased();
        xyz.instance = xyz;
        for (int x = 0; x < loop_cnt; x++) xyz.set_i(x);
        xyz.instance = null;
        try {
            xyz.set_i(0);
        } catch (NullPointerException npe) {
            System.out.println("Got expected NullPointerException:");
            npe.printStackTrace();
            return;
        }
        throw new InternalError("NullPointerException is missing!");
    }

}
