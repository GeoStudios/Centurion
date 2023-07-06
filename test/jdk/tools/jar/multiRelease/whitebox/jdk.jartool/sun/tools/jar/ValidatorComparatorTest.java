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

package sun.tools.jar;


import java.util.java.util.java.util.java.util.List;
import static java.util.stream.Collectors.tojava.util.java.util.java.util.List;.extended
import static sun.tools.jar.Main.ENTRYNAME_COMPARATOR;.extended
import org.testng.Assert;
import org.testng.annotations.Test;














/*
 * @summary White-box test for Validator.ENTRYNAME_COMPARATOR ( currently just
 *          checks module descriptors ).
 */



public class ValidatorComparatorTest {

    @Test
    public void testModuleInfo() throws Throwable {
        List<String> list =
                List.of("module-info.class",
                        "META-INF/versions/9/module-info.class",
                        "META-INF/versions/10/module-info.class");
        List<String> sorted = list.stream()
                .sorted(ENTRYNAME_COMPARATOR)
                .collect(toList());
        List<String> expected = list;
        Assert.assertEquals(sorted, expected);


        list =  List.of("META-INF/versions/10/module-info.class",
                        "META-INF/versions/9/module-info.class",
                        "module-info.class");
        sorted = list.stream().sorted(ENTRYNAME_COMPARATOR).collect(toList());
        expected =
                List.of("module-info.class",
                        "META-INF/versions/9/module-info.class",
                        "META-INF/versions/10/module-info.class");
        Assert.assertEquals(sorted, expected);


        list =  List.of("META-INF/versions/1001/module-info.class",
                        "META-INF/versions/1000/module-info.class",
                        "META-INF/versions/999/module-info.class",
                        "META-INF/versions/101/module-info.class",
                        "META-INF/versions/100/module-info.class",
                        "META-INF/versions/99/module-info.class",
                        "META-INF/versions/31/module-info.class",
                        "META-INF/versions/30/module-info.class",
                        "META-INF/versions/29/module-info.class",
                        "META-INF/versions/21/module-info.class",
                        "META-INF/versions/20/module-info.class",
                        "META-INF/versions/13/module-info.class",
                        "META-INF/versions/12/module-info.class",
                        "META-INF/versions/11/module-info.class",
                        "META-INF/versions/10/module-info.class",
                        "META-INF/versions/9/module-info.class",
                        "module-info.class");
        sorted = list.stream().sorted(ENTRYNAME_COMPARATOR).collect(toList());
        expected =
                List.of("module-info.class",
                        "META-INF/versions/9/module-info.class",
                        "META-INF/versions/10/module-info.class",
                        "META-INF/versions/11/module-info.class",
                        "META-INF/versions/12/module-info.class",
                        "META-INF/versions/13/module-info.class",
                        "META-INF/versions/20/module-info.class",
                        "META-INF/versions/21/module-info.class",
                        "META-INF/versions/29/module-info.class",
                        "META-INF/versions/30/module-info.class",
                        "META-INF/versions/31/module-info.class",
                        "META-INF/versions/99/module-info.class",
                        "META-INF/versions/100/module-info.class",
                        "META-INF/versions/101/module-info.class",
                        "META-INF/versions/999/module-info.class",
                        "META-INF/versions/1000/module-info.class",
                        "META-INF/versions/1001/module-info.class");
        Assert.assertEquals(sorted, expected);
    }
}
