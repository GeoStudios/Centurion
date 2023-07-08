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

package utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data;

/**
 *
 */
public class InputBytecode {

    private final int bci;
    private final String name;
    private final String operands;
    private final String comment;
    private InputMethod inlined;

    public InputBytecode(int bci, String name, String operands, String comment) {
        this.bci = bci;
        this.name = name;
        this.operands = operands;
        this.comment = comment;
    }

    public InputMethod getInlined() {
        return inlined;
    }

    public void setInlined(InputMethod inlined) {
        this.inlined = inlined;
    }

    public int getBci() {
        return bci;
    }

    public String getName() {
        return name;
    }

    public String getOperands() {
        return operands;
    }

    public String getComment() {
        return comment;
    }
}
