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

package nsk.share.gc.tree;

public class Tree {
        private final long nodeSize;
        private TreeNode root;

        public Tree(long nodeSize) {
                this.nodeSize = nodeSize;
                root = new TreeNode(nodeSize);
        }

        public Tree(TreeNode root) {
                this.nodeSize = root.getSize();
                setRoot(root);
        }

        public TreeNode follow(int path, int depth) {
                return root.follow(path, depth);
        }

        public int getHeight() {
                return root.getHeight();
        }

        public TreeNode newNode() {
                return new TreeNode(nodeSize);
        }

        public final TreeNode getRoot() {
                return root;
        }

        public final void setRoot(TreeNode root) {
                this.root = root;
        }
}
