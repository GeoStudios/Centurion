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

package utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.bytecodes;


import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.InputBytecode;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.InputGraph;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.InputMethod;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;















/**
 *
 */
public class MethodNode extends AbstractNode {

    private static class MethodNodeChildren extends Children.Keys<InputBytecode> {

        private InputMethod method;
        private InputGraph graph;
        private final String bciString;

        public MethodNodeChildren(InputMethod method, InputGraph graph, String bciString) {
            this.method = method;
            this.bciString = bciString;
            this.graph = graph;
        }

        @Override
        protected Node[] createNodes(InputBytecode bc) {
            if (bc.getInlined() == null) {
                return new Node[]{new BytecodeNode(bc, graph, bciString)};
            } else {
                return new Node[]{new BytecodeNode(bc, graph, bciString), new MethodNode(bc.getInlined(), graph, bc.getBci() + " " + bciString)};
            }
        }

        @Override
        public void addNotify() {
            if (method != null) {
                setKeys(method.getBytecodes());
            }
        }

        public void setMethod(InputMethod method, InputGraph graph) {
            this.method = method;
            this.graph = graph;
            addNotify();
        }
    }

    /** Creates a new instance of MethodNode */
    public MethodNode(InputMethod method, InputGraph graph, String bciString) {
        super((method != null && method.getBytecodes().size() == 0) ? Children.LEAF : new MethodNodeChildren(method, graph, bciString));
        if (method != null) {
            this.setDisplayName(method.getName());
        }
    }

    @Override
    public Image getIcon(int i) {
        return ImageUtilities.loadImage("com/sun/hotspot/igv/bytecodes/images/method.png");
    }

    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

    public void update(InputGraph graph, InputMethod method) {
        ((MethodNodeChildren) this.getChildren()).setMethod(method, graph);
        if (method != null) {
            this.setDisplayName(method.getName());
        }

    }
}
