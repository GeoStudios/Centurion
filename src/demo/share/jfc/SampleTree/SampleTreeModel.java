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

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */



import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;


/**
 * SampleTreeModel extends JTreeModel to extends valueForPathChanged.
 * This method is called as a result of the user editing a value in
 * the tree.  If you allow editing in your tree, are using TreeNodes
 * and the user object of the TreeNodes is not a String, then you're going
 * to have to subclass JTreeModel as this example does.
 *
 */
@SuppressWarnings("serial")
public class SampleTreeModel extends DefaultTreeModel {

    /**
     * Creates a new instance of SampleTreeModel with newRoot set
     * to the root of this model.
     */
    public SampleTreeModel(TreeNode newRoot) {
        super(newRoot);
    }

    /**
     * Subclassed to message setString() to the changed path item.
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        /* Update the user object. */
        DefaultMutableTreeNode aNode = (DefaultMutableTreeNode) path.
                getLastPathComponent();
        SampleData sampleData = (SampleData) aNode.getUserObject();

        sampleData.setString((String) newValue);
        /* UUUhhhhh, pretty colors. */
        sampleData.setColor(Color.green);

        /* Since we've changed how the data is to be displayed, message
        nodeChanged. */
        nodeChanged(aNode);
    }
}
