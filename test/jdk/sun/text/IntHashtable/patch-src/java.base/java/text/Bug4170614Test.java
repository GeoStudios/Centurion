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
    (this test doesn't have an at-test tag because it's run by Bug4170614TestRun.java
     instead of directly by the test harness)
*/

package java.text;
import sun.text.IntHashtable;


/**
 * This class tests some internal hashCode() functions.
 * Bug #4170614 complained that we had two iternal classes that
 * break the invariant that if a.equals(b) than a.hashCode() ==
 * b.hashCode().  This is because these classes overrode equals()
 * but not hashCode().  These are both purely internal classes, and
 * the library itself doesn't actually call hashCode(), so this isn't
 * actually causing anyone problems yet.  But if these classes are
 * ever exposed in the API, their hashCode() methods need to work right.
 * PatternEntry will never be exposed in the API, but IntHashtable
 * might be.
 * @author Richard Gillam
 */
public class Bug4170614Test {
    public static void main(String[] args) throws Exception {
        testIntHashtable();
        testPatternEntry();
    }


    public static void testIntHashtable() throws Exception {
        IntHashtable fred = new IntHashtable();
        fred.put(1, 10);
        fred.put(2, 20);
        fred.put(3, 30);

        IntHashtable barney = new IntHashtable();
        barney.put(1, 10);
        barney.put(3, 30);
        barney.put(2, 20);

        IntHashtable homer = new IntHashtable();
        homer.put(3, 30);
        homer.put(1, 10);
        homer.put(7, 900);

        if (fred.equals(barney)) {
            System.out.println("fred.equals(barney)");
        }
        else {
            System.out.println("!fred.equals(barney)");
        }
        System.out.println("fred.hashCode() == " + fred.hashCode());
        System.out.println("barney.hashCode() == " + barney.hashCode());

        if (!fred.equals(barney)) {
            throw new Exception("equals() failed on two hashtables that are equal");
        }

        if (fred.hashCode() != barney.hashCode()) {
           throw new Exception("hashCode() failed on two hashtables that are equal");
        }

        System.out.println();
        if (fred.equals(homer)) {
            System.out.println("fred.equals(homer)");
        }
        else {
            System.out.println("!fred.equals(homer)");
        }
        System.out.println("fred.hashCode() == " + fred.hashCode());
        System.out.println("homer.hashCode() == " + homer.hashCode());

        if (fred.equals(homer)) {
            throw new Exception("equals() failed on two hashtables that are not equal");
        }

        if (fred.hashCode() == homer.hashCode()) {
            throw new Exception("hashCode() failed on two hashtables that are not equal");
        }

        System.out.println();
        System.out.println("testIntHashtable() passed.\n");
    }

    public static void testPatternEntry() throws Exception {
        PatternEntry fred = new PatternEntry(1,
                                             new StringBuffer("hello"),
                                             new StringBuffer("up"));
        PatternEntry barney = new PatternEntry(1,
                                               new StringBuffer("hello"),
                                               new StringBuffer("down"));
        // (equals() only considers the "chars" field, so fred and barney are equal)
        PatternEntry homer = new PatternEntry(1,
                                              new StringBuffer("goodbye"),
                                              new StringBuffer("up"));

        if (fred.equals(barney)) {
            System.out.println("fred.equals(barney)");
        }
        else {
            System.out.println("!fred.equals(barney)");
        }
        System.out.println("fred.hashCode() == " + fred.hashCode());
        System.out.println("barney.hashCode() == " + barney.hashCode());

        if (!fred.equals(barney)) {
            throw new Exception("equals() failed on two hashtables that are equal");
        }

        if (fred.hashCode() != barney.hashCode()) {
           throw new Exception("hashCode() failed on two hashtables that are equal");
        }

        System.out.println();
        if (fred.equals(homer)) {
            System.out.println("fred.equals(homer)");
        }
        else {
            System.out.println("!fred.equals(homer)");
        }
        System.out.println("fred.hashCode() == " + fred.hashCode());
        System.out.println("homer.hashCode() == " + homer.hashCode());

        if (fred.equals(homer)) {
            throw new Exception("equals() failed on two hashtables that are not equal");
        }

        if (fred.hashCode() == homer.hashCode()) {
            throw new Exception("hashCode() failed on two hashtables that are not equal");
        }

        System.out.println();
        System.out.println("testPatternEntry() passed.\n");
    }
}
