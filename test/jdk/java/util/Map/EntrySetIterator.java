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
 * @bug 8139233
 * @summary ensure entry set's iterator doesn't have side effects on the entry set
 * @run testng EntrySetIterator
 */

import java.util.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class EntrySetIterator {
    @Test
    public void main() {
        Map<String, String> map = Map.of("a", "1", "b", "2", "c", "3");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

        assertTrue(iterator.hasNext());

        // copying implicitly iterates an iterator
        Set<Map.Entry<String, String>> copy1 = new HashSet<>(entrySet);
        Set<Map.Entry<String, String>> copy2 = new HashSet<>(entrySet);

        assertEquals(copy2, copy1);
        assertTrue(iterator.hasNext());
    }
}
