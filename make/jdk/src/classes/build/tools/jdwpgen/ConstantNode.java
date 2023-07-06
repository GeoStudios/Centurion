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

package build.tools.jdwpgen;

class ConstantNode extends AbstractCommandNode {

    ConstantNode() {
        this(new ArrayList<Node>());
    }

    ConstantNode(List<Node> components) {
        this.kind = "Constant";
        this.components = components;
        this.lineno = 0;
    }

    void constrain(Context ctx) {
        if (components.size() != 0) {
            error("Constants have no internal structure");
        }
        super.constrain(ctx);
    }

    void genJava(PrintWriter writer, int depth) {
        indent(writer, depth);
        writer.println("static final int " + name + " = " +
                       nameNode.value() + ";");
    }

    void document(PrintWriter writer) {
        //Add anchor to each constant with format <constant table name>_<constant name>
        if (!(parent instanceof AbstractNamedNode)) {
            error("Parent must be ConstantSetNode, but it's " + parent.getClass().getSimpleName());
        }
        String tableName = ((AbstractNamedNode)parent).name;
        writer.println("<tr>"
                        + "<th scope=\"row\">"
                            + "<span id=\"" + tableName + "_" + name + "\"></span>"
                            + name
                        + "<td class=\"centered\">" + nameNode.value()
                        + "<td>" + comment() + "&nbsp;"
                    + "</tr>");
    }

    public String getName(){

        if (name == null || name.length() == 0) {
            prune();
        }
        return name;
    }
}