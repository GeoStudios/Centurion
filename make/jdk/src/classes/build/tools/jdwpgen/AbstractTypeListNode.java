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



















































abstract class AbstractTypeListNode extends AbstractNamedNode {

    void constrainComponent(Context ctx, Node node) {
        if (node instanceof TypeNode) {
            node.constrain(ctx);
        } else {
            error("Expected type descriptor item, got: " + node);
        }
    }

    void document(PrintWriter writer) {
        writer.println("<dt>" + name() + " Data");
        writer.println("<dd>");
        if (components.isEmpty()) {
            writer.println("(None)");
        } else {
            writer.println("<table><tr>");
            writer.println("<th class=\"bold\" style=\"width: 20%\" scope=\"col\">Type");
            writer.println("<th class=\"bold\" style=\"width: 15%\" scope=\"col\">Name");
            writer.println("<th class=\"bold\" style=\"width: 65%\" scope=\"col\">Description");
            writer.println("</tr>");
            for (Node node : components) {
                node.document(writer);
            }
            writer.println("</table>");
        }
        writer.println("</dd>");
    }

    void genJavaClassBodyComponents(PrintWriter writer, int depth) {
        for (Node node : components) {
            TypeNode tn = (TypeNode)node;

            tn.genJavaDeclaration(writer, depth);
        }
    }

    void genJavaReads(PrintWriter writer, int depth) {
        for (Node node : components) {
            TypeNode tn = (TypeNode)node;
            tn.genJavaRead(writer, depth, tn.name());
        }
    }

    void genJavaReadingClassBody(PrintWriter writer, int depth,
                                 String className) {
        genJavaClassBodyComponents(writer, depth);
        writer.println();
        indent(writer, depth);
        if (!context.inEvent()) {
            writer.print("private ");
        }
        writer.println(className +
                       "(VirtualMachineImpl vm, PacketStream ps) {");
        genJavaReads(writer, depth+1);
        indent(writer, depth);
        writer.println("}");
    }

    String javaParams() {
        StringBuffer sb = new StringBuffer();
        for (Iterator<Node> it = components.iterator(); it.hasNext();) {
            TypeNode tn = (TypeNode)it.next();
            sb.append(tn.javaParam());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    void genJavaWrites(PrintWriter writer, int depth) {
        for (Node node : components) {
            TypeNode tn = (TypeNode)node;
            tn.genJavaWrite(writer, depth, tn.name());
        }
    }

    void genJavaWritingClassBody(PrintWriter writer, int depth,
                                 String className) {
        genJavaClassBodyComponents(writer, depth);
        writer.println();
        indent(writer, depth);
        writer.println(className + "(" + javaParams() + ") {");
        for (Node node : components) {
            TypeNode tn = (TypeNode)node;
            indent(writer, depth+1);
            writer.println("this." + tn.name() + " = " + tn.name() + ";");
        }
        indent(writer, depth);
        writer.println("}");
    }
}
