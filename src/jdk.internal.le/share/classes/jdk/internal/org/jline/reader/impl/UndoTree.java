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
package jdk.internal.org.jline.reader.impl;

import java.util.function.Consumer;

/**
 * Simple undo tree.
 * Note that the first added state can't be undone
 */
public class UndoTree<T> {

    private final Consumer<T> state;
    private final Node parent;
    private Node current;

    public UndoTree(Consumer<T> s) {
        state = s;
        parent = new Node(null);
        parent.left = parent;
        clear();
    }

    public void clear() {
        current = parent;
    }

    public void newState(T state) {
        Node node = new Node(state);
        current.right = node;
        node.left = current;
        current = node;
    }

    public boolean canUndo() {
        return current.left != parent;
    }

    public boolean canRedo() {
        return current.right != null;
    }

    public void undo() {
        if (!canUndo()) {
            throw new IllegalStateException("Cannot undo.");
        }
        current = current.left;
        state.accept(current.state);
    }

    public void redo() {
        if (!canRedo()) {
            throw new IllegalStateException("Cannot redo.");
        }
        current = current.right;
        state.accept(current.state);
    }

    private class Node {
        private final T state;
        private Node left = null;
        private Node right = null;

        public Node(T s) {
            state = s;
        }
    }

}
