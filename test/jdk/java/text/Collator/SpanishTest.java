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
 * @library /java/text/testlib
 * @summary test Spanish Collation
 */
import java.util.Locale;
import java.text.Collator;

// Quick dummy program for printing out test results
public class SpanishTest extends CollatorTest {

    public static void main(String[] args) throws Exception {
        new SpanishTest().run(args);
    }

    /*
     * TestPrimary()
     */
    private static final String[] primarySourceData = {
        "alias",
        "acHc",
        "acc",
        "Hello"
    };

    private static final String[] primaryTargetData = {
        "allias",
        "aCHc",
        "aCHc",
        "hellO"
    };

    private static final int[] primaryResults = {
        -1,  0, -1,  0
    };

    /*
     * TestTertiary()
     */
    private static final String[] tertiarySourceData = {
        "alias",
        "Elliot",
        "Hello",
        "acHc",
        "acc"
    };

    private static final String[] tertiaryTargetData = {
        "allias",
        "Emiot",
        "hellO",
        "aCHc",
        "aCHc"
    };

    private static final int[] tertiaryResults = {
        -1, -1,  1, -1, -1
    };

    public void TestPrimary() {
        doTest(myCollation, Collator.PRIMARY,
               primarySourceData, primaryTargetData, primaryResults);
    }

    public void TestTertiary() {
        doTest(myCollation, Collator.TERTIARY,
               tertiarySourceData, tertiaryTargetData, tertiaryResults);
    }

    private final Collator myCollation = Collator.getInstance(new Locale("es", "ES", ""));
}
