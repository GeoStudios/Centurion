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

package java.desktop.share.classes.javax.swing.tree;


import java.awt.Component;
import java.desktop.share.classes.javax.swing.CellEditor;
import java.desktop.share.classes.javax.swing.JTree;















/**
  * Adds to CellEditor the extensions necessary to configure an editor
  * in a tree.
  *
  * @see javax.swing.JTree
  *
  */

public interface TreeCellEditor extends CellEditor
{
    /**
     * Sets an initial <I>value</I> for the editor.  This will cause
     * the editor to stopEditing and lose any partially edited value
     * if the editor is editing when this method is called. <p>
     *
     * Returns the component that should be added to the client's
     * Component hierarchy.  Once installed in the client's hierarchy
     * this component will then be able to draw and receive user input.
     *
     * @param   tree            the JTree that is asking the editor to edit;
     *                          this parameter can be null
     * @param   value           the value of the cell to be edited
     * @param   isSelected      true if the cell is to be rendered with
     *                          selection highlighting
     * @param   expanded        true if the node is expanded
     * @param   leaf            true if the node is a leaf node
     * @param   row             the row index of the node being edited
     * @return  the component for editing
     */
    Component getTreeCellEditorComponent(JTree tree, Object value,
                                         boolean isSelected, boolean expanded,
                                         boolean leaf, int row);
}
