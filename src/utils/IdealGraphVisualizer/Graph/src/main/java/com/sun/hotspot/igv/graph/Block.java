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
package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.data.InputBlock;
import com.sun.hotspot.igv.layout.Cluster;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Block implements Cluster {

    private final InputBlock inputBlock;
    private Rectangle bounds;
    private final Diagram diagram;

    public Block(InputBlock inputBlock, Diagram diagram) {
        this.inputBlock = inputBlock;
        this.diagram = diagram;
    }

    public Cluster getOuter() {
        return null;
    }

    public InputBlock getInputBlock() {
        return inputBlock;
    }

    public Set<? extends Cluster> getSuccessors() {
        Set<Block> succs = new HashSet<Block>();
        for (InputBlock b : inputBlock.getSuccessors()) {
            succs.add(diagram.getBlock(b));
        }
        return succs;
    }

    public void setBounds(Rectangle r) {
        this.bounds = r;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int compareTo(Cluster o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return inputBlock.getName();
    }
}

