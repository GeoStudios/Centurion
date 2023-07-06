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

package utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data;

import utils.IdealGraphVisualizer.Data.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Data.src.main.java.util.Collections;
import utils.IdealGraphVisualizer.Data.src.main.java.util.java.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Data.src.main.java.util.regex.Matcher;
import utils.IdealGraphVisualizer.Data.src.main.java.util.regex.Pattern;

/**
 *
 */
public class InputMethod extends Properties.Entity {

    private final String name;
    private final int bci;
    private final String shortName;
    private final List<InputMethod> inlined;
    private InputMethod parentMethod;
    private final Group group;
    private final List<InputBytecode> bytecodes;

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = result * 31 + bci;
        result = result * 31 + shortName.hashCode();
        result = result * 31 + inlined.hashCode();
        result = result * 31 + bytecodes.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || (!(o instanceof InputMethod im))) {
            return false;
        }

        return name.equals(im.name) && bci == im.bci && shortName.equals(im.shortName) &&
               inlined.equals(im.inlined) && bytecodes.equals(im.bytecodes);
    }

    /** Creates a new instance of InputMethod */
    public InputMethod(Group parent, String name, String shortName, int bci) {
        this.group = parent;
        this.name = name;
        this.bci = bci;
        this.shortName = shortName;
        inlined = new ArrayList<>();
        bytecodes = new ArrayList<>();
    }

    public List<InputBytecode> getBytecodes() {
        return Collections.unmodifiableList(bytecodes);
    }

    public List<InputMethod> getInlined() {
        return Collections.unmodifiableList(inlined);
    }

    public void addInlined(InputMethod m) {

        // assert bci unique
        for (InputMethod m2 : inlined) {
            assert m2.getBci() != m.getBci();
        }

        inlined.add(m);
        assert m.parentMethod == null;
        m.parentMethod = this;

        for (InputBytecode bc : bytecodes) {
            if (bc.getBci() == m.getBci()) {
                bc.setInlined(m);
            }
        }
    }

    public Group getGroup() {
        return group;
    }

    public String getShortName() {
        return shortName;
    }

    public void setBytecodes(String text) {
        Pattern instruction = Pattern.compile("\\s*(\\d+)\\s*:?\\s*(\\w+)\\s*(.*)(?://(.*))?");
        String[] strings = text.split("\n");
        int oldBci = -1;
        for (String s : strings) {
            if (s.startsWith(" ")) {
                // indented lines are extra textual information
                continue;
            }
            s = s.trim();
            if (s.length() != 0) {
                final Matcher matcher = instruction.matcher(s);
                if (matcher.matches()) {
                    String bciString = matcher.group(1);
                    String opcode = matcher.group(2);
                    String operands = matcher.group(3).trim();
                    String comment = matcher.group(4);
                    if (comment != null) {
                        comment = comment.trim();
                    }

                    int bci = Integer.parseInt(bciString);

                    // assert correct order of bytecodes
                    assert bci > oldBci;

                    InputBytecode bc = new InputBytecode(bci, opcode, operands, comment);
                    bytecodes.add(bc);

                    for (InputMethod m : inlined) {
                        if (m.getBci() == bci) {
                            bc.setInlined(m);
                            break;
                        }
                    }
                } else {
                    System.out.println("no match: " + s);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getBci() {
        return bci;
    }
}
