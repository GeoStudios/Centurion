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
 * @summary test Finnish Collation
 * @modules jdk.localedata
 */
import java.util.Locale;
import java.text.Collator;

// Quick dummy program for printing out test results
public class FinnishTest extends CollatorTest {

    public static void main(String[] args) throws Exception {
        new FinnishTest().run(args);
    }

    /*
     * Data for TestPrimary()
     */
    private static final String[] primarySourceData = {
        "L\u00E5vi",
        "wat"
    };

    private static final String[] primaryTargetData = {
        "L\u00E4we",
        "vat"
    };

    private static final int[] primaryResults = {
        -1,  0
    };

    /*
     * Data for TestTertiary()
     */
    private static final String tertiarySourceData[] = {
        "wat",
        "vat",
        "a\u00FCbeck"
    };

    private static final String tertiaryTargetData[] = {
        "vat",
        "way",
        "axbeck"
    };

    private static final int[] tertiaryResults = {
         1, -1,  1
    };

    public void TestPrimary() {
        doTest(myCollation, Collator.PRIMARY,
               primarySourceData, primaryTargetData, primaryResults);
    }

    public void TestTertiary() {
        doTest(myCollation, Collator.TERTIARY,
              tertiarySourceData, tertiaryTargetData, tertiaryResults);
    }

    private final Collator myCollation = Collator.getInstance(new Locale("fi", "FI", ""));
}
