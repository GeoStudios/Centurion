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
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.InputNode;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.Properties;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.com.sun.hotspot.igv.data.Properties.StringPropertyMatcher;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.awt.Image;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.util.LinkedHashSet;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.util.java.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Bytecodes.src.main.java.util.Set;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;

/**
 *
 */
public class BytecodeNode extends AbstractNode {

    private Set<InputNode> nodes;

    public BytecodeNode(InputBytecode bytecode, InputGraph graph, String bciValue) {

        super(Children.LEAF);
        String displayName = bytecode.getBci() + " " + bytecode.getName() + " " + bytecode.getOperands();

        bciValue = bytecode.getBci() + " " + bciValue;
        bciValue = bciValue.trim();

        Properties.PropertySelector<InputNode> selector = new Properties.PropertySelector<>(graph.getNodes());
        StringPropertyMatcher matcher = new StringPropertyMatcher("bci", bciValue);
        List<InputNode> nodeList = selector.selectMultiple(matcher);
        if (nodeList.size() > 0) {
            nodes = new LinkedHashSet<>();
            for (InputNode n : nodeList) {
                nodes.add(n);
            }
            displayName += " (" + nodes.size() + " nodes)";
        }

        if (bytecode.getComment() != null) {
            displayName += " // " + bytecode.getComment();
        }

        this.setDisplayName(displayName);
    }

    @Override
    public Image getIcon(int i) {
        if (nodes != null) {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/bytecodes/images/link.png");
        } else {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/bytecodes/images/bytecode.png");
        }
    }

    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

    @Override
    public Action[] getActions(boolean b) {
        return new Action[]{(Action) SelectBytecodesAction.findObject(SelectBytecodesAction.class, true)};
    }

    @Override
    public Action getPreferredAction() {
        return (Action) SelectBytecodesAction.findObject(SelectBytecodesAction.class, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node.Cookie> T getCookie(Class<T> aClass) {
        if (aClass == SelectBytecodesCookie.class && nodes != null) {
            return (T) (new SelectBytecodesCookie(nodes));
        }
        return super.getCookie(aClass);
    }
}
