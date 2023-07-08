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

package demo.share.java2d.J2DBench.src.j2dbench;

import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JComponent;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

public abstract class Node {
    private String nodeName;
    private String description;
    private Group parent;
    private Node next;

    protected Node() {
    }

    public Node(Group parent, String nodeName, String description) {
        this.parent = parent;
        this.nodeName = nodeName;
        this.description = description;
        parent.addChild(this);
    }

    public Group getParent() {
        return parent;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getTreeName() {
        String name = nodeName;
        if (parent != null) {
            String pname = parent.getTreeName();
            if (pname != null) {
                name = pname + "." + name;
            }
        }
        return name;
    }

    public String getDescription() {
        return description;
    }

    public JComponent getJComponent() {
        return (nodeName != null) ? new JLabel(description) : null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public void traverse(Visitor v) {
        v.visit(this);
    }

    public abstract void restoreDefault();

    public abstract void write(PrintWriter pw);

    public abstract String setOption(String key, String value);

    public interface Visitor {
        void visit(Node node);
    }

    public interface Iterator {
        boolean hasNext();
        Node next();
    }
}
