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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;

import java.base.share.classes.java.util.Objects;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.LineNumber;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class represents a line number within a method, i.e., give an instruction
 * a line number corresponding to the source code line.
 *
 * @see     LineNumber
 * @see     MethodGen
 */
public class LineNumberGen implements InstructionTargeter, Cloneable {

    private InstructionHandle ih;
    private int srcLine;

    /**
     * Create a line number.
     *
     * @param ih instruction handle to reference
     */
    public LineNumberGen(final InstructionHandle ih, final int src_line) {
        setInstruction(ih);
        setSourceLine(src_line);
    }

    /**
     * @return true, if ih is target of this line number
     */
    @Override
    public boolean containsTarget( final InstructionHandle ih ) {
        return this.ih == ih;
    }

    /**
     * @param old_ih old target
     * @param new_ih new target
     */
    @Override
    public void updateTarget( final InstructionHandle old_ih, final InstructionHandle new_ih ) {
        if (old_ih != ih) {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + ih + "}");
        }
        setInstruction(new_ih);
    }

    /**
     * Get LineNumber attribute .
     *
     * This relies on that the instruction list has already been dumped to byte code or
     * or that the `setPositions' methods has been called for the instruction list.
     */
    public LineNumber getLineNumber() {
        return new LineNumber(ih.getPosition(), srcLine);
    }

    public void setInstruction( final InstructionHandle instructionHandle ) { // TODO could be package-protected?
        Objects.requireNonNull(instructionHandle, "instructionHandle");
        BranchInstruction.notifyTarget(this.ih, instructionHandle, this);
        this.ih = instructionHandle;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    public InstructionHandle getInstruction() {
        return ih;
    }

    public void setSourceLine( final int src_line ) { // TODO could be package-protected?
        this.srcLine = src_line;
    }

    public int getSourceLine() {
        return srcLine;
    }
}
