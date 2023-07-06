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

class ConstantSetNode extends AbstractNamedNode {

    /**
     * The mapping between a constant and its value.
     */
    protected static final Map<String, String> constantMap = new HashMap<>();

    void prune() {
        List<Node> addons = new ArrayList<>();

        if (!addons.isEmpty()) {
            components.addAll(addons);
        }
        super.prune();
    }

    void constrainComponent(Context ctx, Node node) {
        if (node instanceof ConstantNode) {
            node.constrain(ctx);
            constantMap.put(name + "_" + ((ConstantNode) node).getName(), node.comment());
        } else {
            error("Expected 'Constant', got: " + node);
        }
    }

    void document(PrintWriter writer) {
        writer.println("<h2 id=\"" + context.whereC + "\">" + name + " Constants</h2>");
        writer.println(comment());
        writer.println("<table><tr>");
        writer.println("<th class=\"bold\" style=\"width: 30%\" scope=\"col\">Name");
        writer.println("<th class=\"centered bold\" style=\"width: 5%\" scope=\"col\">Value");
        writer.println("<th class=\"bold\" style=\"width: 65%\" scope=\"col\">Description");
        writer.println("</tr>");
        for (Node node : components) {
            node.document(writer);
        }
        writer.println("</table>");
    }

    void documentIndex(PrintWriter writer) {
        writer.print("<li><a href=\"#" + context.whereC + "\">");
        writer.println(name() + "</a> Constants");
//        writer.println("<ul>");
//        for (Iterator it = components.iterator(); it.hasNext();) {
//            ((Node)it.next()).documentIndex(writer);
//        }
//        writer.println("</ul>");
    }

    void genJavaClassSpecifics(PrintWriter writer, int depth) {
    }

    void genJava(PrintWriter writer, int depth) {
        genJavaClass(writer, depth);
    }

    public static String getConstant(String key){
        String com = constantMap.get(key);
        if(com == null){
            return "";
        } else {
            return com;
        }
    }

}