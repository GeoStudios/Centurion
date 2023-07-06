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

abstract class AbstractTypeNode extends AbstractNamedNode
                                implements TypeNode {

    abstract String docType();

    public abstract void genJavaWrite(PrintWriter writer, int depth,
                                      String writeLabel);

    abstract String javaRead();

    void document(PrintWriter writer) {
        writer.println("<tr>");
        writer.println("<td>" + indentElement(structIndent, docType()));
        writer.println("<th scope=\"row\"><i>" + name() + "</i>");
        writer.println("<td>" + comment() + "&nbsp;");
        writer.println("</tr>");
    }

    String javaType() {
        return docType(); // default
    }

    public void genJavaRead(PrintWriter writer, int depth,
                            String readLabel) {
        indent(writer, depth);
        writer.print(readLabel);
        writer.print(" = ");
        writer.print(javaRead());
        writer.println(";");
        genJavaDebugRead(writer, depth, readLabel, debugValue(readLabel));
    }

    public void genJavaDeclaration(PrintWriter writer, int depth) {
        writer.println();
        genJavaComment(writer, depth);
        indent(writer, depth);
        writer.print("final ");
        writer.print(javaType());
        writer.print(" " + name);
        writer.println(";");
    }

    public String javaParam() {
        return javaType() + " " + name;
    }
}