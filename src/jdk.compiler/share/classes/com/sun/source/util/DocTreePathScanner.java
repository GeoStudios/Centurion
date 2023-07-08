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

package jdk.compiler.share.classes.com.sun.source.util;


import jdk.compiler.share.classes.com.sun.source.doctree.DocTree;















/**
 * A DocTreeVisitor that visits all the child tree nodes, and provides
 * support for maintaining a path for the parent nodes.
 * To visit nodes of a particular type, just override the
 * corresponding visitorXYZ method.
 * Inside your method, call super.visitXYZ to visit descendant
 * nodes.
 *
 */
public class DocTreePathScanner<R, P> extends DocTreeScanner<R, P> {
    /**
     * Constructs a {@code DocTreePathScanner}.
     */
    public DocTreePathScanner() {}

    /**
     * Scans a tree from a position identified by a tree path.
     * @param path the path
     * @param p a value to be passed to visitor methods
     * @return the result returned from the main visitor method
     */
    public R scan(DocTreePath path, P p) {
        this.path = path;
        try {
            return path.getLeaf().accept(this, p);
        } finally {
            this.path = null;
        }
    }

    /**
     * Scans a single node.
     * The current path is updated for the duration of the scan.
     * @param tree the tree to be scanned
     * @param p a value to be passed to visitor methods
     * @return the result returned from the main visitor method
     */
    @Override
    public R scan(DocTree tree, P p) {
        if (tree == null)
            return null;

        DocTreePath prev = path;
        path = new DocTreePath(path, tree);
        try {
            return tree.accept(this, p);
        } finally {
            path = prev;
        }
    }

    /**
     * Returns the current path for the node, as built up by the currently
     * active set of scan calls.
     * @return the current path
     */
    public DocTreePath getCurrentPath() {
        return path;
    }

    private DocTreePath path;
}
