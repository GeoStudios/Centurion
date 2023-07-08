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



















































class ErrorNode extends AbstractCommandNode {

    protected static final String NAME_OF_ERROR_TABLE = "Error";

    ErrorNode() {
        this(new ArrayList<Node>());
    }

    ErrorNode(List<Node> components) {
        this.kind = "Error";
        this.components = components;
        this.lineno = 0;
    }

    void constrain(Context ctx) {
        if (components.size() != 0) {
            error("Errors have no internal structure");
        }
        super.constrain(ctx);
    }

    void document(PrintWriter writer) {

        String com = comment();
        if (com == null || com.length() == 0) {
            com = ConstantSetNode.getConstant("Error_" + name);
        }
        writer.println("<tr>"
                + "<th scope=\"row\">" + "<a href=\"#" + NAME_OF_ERROR_TABLE + "_" + name + "\">" + name + "</a>"
                + "<td>" + com + "&nbsp;"
                + "</tr>");
    }

    void genJavaComment(PrintWriter writer, int depth) {}

    void genJava(PrintWriter writer, int depth) {}

    void genCInclude(PrintWriter writer) {}

    void genJavaDebugWrite(PrintWriter writer, int depth,
                           String writeLabel) {}

    void genJavaDebugWrite(PrintWriter writer, int depth,
                           String writeLabel, String displayValue) {}

    public void genJavaRead(PrintWriter writer, int depth,
                            String readLabel) {}

    void genJavaDebugRead(PrintWriter writer, int depth,
                          String readLabel, String displayValue) {}

    void genJavaPreDef(PrintWriter writer, int depth) {}
}
