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
 * @summary The characters FFFE and FFFF should not be in a UnicodeBlock. They
 *          should be in a null block.
 * @bug 4404588
 * @author John O'Conner
 */

 public class Bug4404588 {

    public Bug4404588() {
        // do nothing
    }

    public static void main(String[] args) {
        Bug4404588 test = new Bug4404588();
        test.run();
    }

    /**
     * Test the correct data against what Character reports.
     */
    void run() {
        Character ch;
        Character.UnicodeBlock block;

        for(int x=0; x < charData.length; x++) {
            ch = (Character)charData[x][0];
            block = (Character.UnicodeBlock)charData[x][1];

            if (Character.UnicodeBlock.of(ch.charValue()) != block) {
                System.err.println("Error: block = " + block);
                System.err.println("Character.UnicodeBlock.of(" +
                    Integer.toHexString(ch.charValue()) +") = " +
                    Character.UnicodeBlock.of(ch.charValue()));
                throw new RuntimeException("Blocks aren't equal.");
            }
        }
        System.out.println("Passed.");
    }

    /**
     * Contains the character data to test. The first object is the character.
     * The next object is the UnicodeBlock to which it should belong.
     */
    Object[][] charData = {
        { new Character('\uFFFE'), Character.UnicodeBlock.SPECIALS },
        { new Character('\uFFFF'), Character.UnicodeBlock.SPECIALS },
    };
 }
