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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.CodeException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This class represents an exception handler, i.e., specifies the  region where
 * a handler is active and an instruction where the actual handling is done.
 * pool as parameters. Opposed to the JVM specification the end of the handled
 * region is set to be inclusive, i.e. all instructions between start and end
 * are protected including the start and end instructions (handles) themselves.
 * The end of the region is automatically mapped to be exclusive when calling
 * getCodeException(), i.e., there is no difference semantically.
 *
 * @see     MethodGen
 * @see     CodeException
 * @see     InstructionHandle
 */
public final class CodeExceptionGen implements InstructionTargeter, Cloneable {

    private InstructionHandle startPc;
    private InstructionHandle endPc;
    private InstructionHandle handlerPc;
    private ObjectType catchType;


    /**
     * Add an exception handler, i.e., specify region where a handler is active and an
     * instruction where the actual handling is done.
     *
     * @param startPc Start of handled region (inclusive)
     * @param endPc End of handled region (inclusive)
     * @param handlerPc Where handling is done
     * @param catchType which exception is handled, null for ANY
     */
    public CodeExceptionGen(final InstructionHandle startPc, final InstructionHandle endPc,
            final InstructionHandle handlerPc, final ObjectType catchType) {
        setStartPC(startPc);
        setEndPC(endPc);
        setHandlerPC(handlerPc);
        this.catchType = catchType;
    }


    /**
     * Get CodeException object.<BR>
     *
     * This relies on that the instruction list has already been dumped
     * to byte code or or that the `setPositions' methods has been
     * called for the instruction list.
     *
     * @param cp constant pool
     */
    public CodeException getCodeException( final ConstantPoolGen cp ) {
        return new CodeException(startPc.getPosition(), endPc.getPosition()
                + endPc.getInstruction().getLength(), handlerPc.getPosition(),
                (catchType == null) ? 0 : cp.addClass(catchType));
    }


    /* Set start of handler
     * @param startPc Start of handled region (inclusive)
     */
    public void setStartPC( final InstructionHandle start_pc ) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.startPc, start_pc, this);
        this.startPc = start_pc;
    }


    /* Set end of handler
     * @param endPc End of handled region (inclusive)
     */
    public void setEndPC( final InstructionHandle end_pc ) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.endPc, end_pc, this);
        this.endPc = end_pc;
    }


    /* Set handler code
     * @param handlerPc Start of handler
     */
    public void setHandlerPC( final InstructionHandle handler_pc ) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.handlerPc, handler_pc, this);
        this.handlerPc = handler_pc;
    }


    /**
     * @param old_ih old target, either start or end
     * @param new_ih new target
     */
    @Override
    public void updateTarget( final InstructionHandle old_ih, final InstructionHandle new_ih ) {
        boolean targeted = false;
        if (startPc == old_ih) {
            targeted = true;
            setStartPC(new_ih);
        }
        if (endPc == old_ih) {
            targeted = true;
            setEndPC(new_ih);
        }
        if (handlerPc == old_ih) {
            targeted = true;
            setHandlerPC(new_ih);
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih + ", but {" + startPc + ", "
                    + endPc + ", " + handlerPc + "}");
        }
    }


    /**
     * @return true, if ih is target of this handler
     */
    @Override
    public boolean containsTarget( final InstructionHandle ih ) {
        return (startPc == ih) || (endPc == ih) || (handlerPc == ih);
    }


    /** Sets the type of the Exception to catch. Set 'null' for ANY. */
    public void setCatchType( final ObjectType catchType ) {
        this.catchType = catchType;
    }


    /** Gets the type of the Exception to catch, 'null' for ANY. */
    public ObjectType getCatchType() {
        return catchType;
    }


    /** @return start of handled region (inclusive)
     */
    public InstructionHandle getStartPC() {
        return startPc;
    }


    /** @return end of handled region (inclusive)
     */
    public InstructionHandle getEndPC() {
        return endPc;
    }


    /** @return start of handler
     */
    public InstructionHandle getHandlerPC() {
        return handlerPc;
    }


    @Override
    public String toString() {
        return "CodeExceptionGen(" + startPc + ", " + endPc + ", " + handlerPc + ")";
    }


    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }
}
