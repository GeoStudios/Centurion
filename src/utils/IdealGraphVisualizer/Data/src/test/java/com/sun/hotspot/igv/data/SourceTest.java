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

package utils.IdealGraphVisualizer.Data.src.test.java.com.sun.hotspot.igv.data;

import utils.IdealGraphVisualizer.Data.src.test.java.util.java.util.java.util.java.util.Arrays;
import utils.IdealGraphVisualizer.Data.src.test.java.util.LinkedHashSet;
import utils.IdealGraphVisualizer.Data.src.test.java.util.java.util.java.util.java.util.List;
import static org.junit.Assert.assertEquals;.extended
import org.junit.*;

/**
 *
 */
public class SourceTest {

    public SourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSourceNodes method, of class Source.
     */
    @Test
    public void testBase() {
        final Source s = new Source();

        final InputNode N1 = new InputNode(1);
        final InputNode N2 = new InputNode(2);

        s.addSourceNode(N1);
        assertEquals(s.getSourceNodes(), List.of(N1));
        assertEquals(s.getSourceNodesAsSet(), new LinkedHashSet<>(List.of(1)));

        s.addSourceNode(N2);
        assertEquals(s.getSourceNodes(), Arrays.asList(N1, N2));
        assertEquals(s.getSourceNodesAsSet(), new LinkedHashSet<>(Arrays.asList(1, 2)));

        s.addSourceNode(N1);
        assertEquals(s.getSourceNodes(), Arrays.asList(N1, N2));
        assertEquals(s.getSourceNodesAsSet(), new LinkedHashSet<>(Arrays.asList(1, 2)));
    }
}
