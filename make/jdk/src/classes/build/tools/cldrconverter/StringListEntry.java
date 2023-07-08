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

package build.tools.cldrconverter;



















































class StringListEntry extends Entry<List<String>> {

    private List<String> value;

    StringListEntry(String qName, Container parent, String key) {
        super(qName, parent, key);
        value = new ArrayList<>();
    }

    void addCharacters(int index, String count, char[] characters, int start, int length) {
        int size = value.size();
        String elem = count + ":" + new String(characters, start, length);

        // quote embedded spaces, if any
        elem = elem.replaceAll(" ", "' '");

        if (size < index) {
            // fill with empty strings when the patterns start from index > size
            IntStream.range(size, index).forEach(i -> value.add(i, ""));
            value.add(index, elem);
        } else if (size == index) {
            value.add(index, elem);
        } else {
            // concatenate the pattern with the delimiter ' '
            value.set(index, value.get(index) + " " + elem);
        }
    }

    @Override
    List<String> getValue() {
        for (String element : value) {
            if (element != null) {
                return value;
            }
        }
        return null;
    }

}
