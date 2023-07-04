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

public class BindingsTest1Merging {
    public static boolean Ktrue() { return true; }
    public static void main(String[] args) {
        Object o1 = "hello";
        Integer i = 42;
        Object o2 = i;
        Object o3 = "there";

        // Test for e1 && e2.F = intersect(e1.F, e2.F)
        if (!(o1 instanceof String s) && !(o1 instanceof String s)) {

        } else {
            s.length();
        }

        // Test for (e1 || e2).T = intersect(e1.T, e2.T)
        if (o1 instanceof String s || o3 instanceof String s){
            System.out.println(s); // ?
        }

        // Test for (e1 ? e2 : e3).T contains intersect(e2.T, e3.T)
        if (Ktrue() ? o2 instanceof Integer x : o2 instanceof Integer x) {
            x.intValue();
        }

        // Test for (e1 ? e2 : e3).T contains intersect(e1.T, e3.T)
        if (o1 instanceof String s ? true : o1 instanceof String s) {
            s.length();
        }

        // Test for (e1 ? e2 : e3).T contains intersect(e1.F, e2.T)
        if (!(o1 instanceof String s) ? (o1 instanceof String s) : true) {
            s.length();
        }

        // Test for (e1 ? e2 : e3).F contains intersect(e2.F, e3.F)
        if (Ktrue() ? !(o2 instanceof Integer x) : !(o2 instanceof Integer x)){
        } else {
            x.intValue();
        }

        // Test for (e1 ? e2 : e3).F contains intersect(e1.T, e3.F)
        if (o1 instanceof String s ? true : !(o1 instanceof String s)){
        } else {
            s.length();
        }

        // Test for (e1 ? e2 : e3).F contains intersect(e1.F, e2.F)
        if (!(o1 instanceof String s) ? !(o1 instanceof String s) : true){
        } else {
            s.length();
        }

        L3: {
            if ((o1 instanceof String s) || (o3 instanceof String s)) {
                s.length();
            } else {
                break L3;
            }
            s.length();
        }

        System.out.println("BindingsTest1Merging complete");
    }
}
