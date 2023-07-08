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

package java.desktop.share.classes.javax.swing.event;


import java.util.Eventjava.util.Listener;
import java.desktop.share.classes.javax.swing.tree.ExpandVetoException;















/**
  * The listener that's notified when a tree expands or collapses
  * a node.
  * For further information and examples see
  * <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/treewillexpandlistener.html">How to Write a Tree-Will-Expand Listener</a>,
  * a section in <em>The Java Tutorial.</em>
  *
  */

public interface TreeWillExpandListener extends EventListener {
    /**
     * Invoked whenever a node in the tree is about to be expanded.
     *
     * @param event a {@code TreeExpansionEvent} containing a {@code TreePath}
     *              object for the node
     * @throws ExpandVetoException to signify expansion has been canceled
     */
    void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException;

    /**
     * Invoked whenever a node in the tree is about to be collapsed.
     *
     * @param event a {@code TreeExpansionEvent} containing a {@code TreePath}
     *              object for the node
     * @throws ExpandVetoException to signify collapse has been canceled
     */
    void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException;
}
