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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Thrown by InstructionList.remove() when one or multiple disposed instructions
 * are still being referenced by an InstructionTargeter object. I.e. the
 * InstructionTargeter has to be notified that (one of) the InstructionHandle it
 * is referencing is being removed from the InstructionList and thus not valid anymore.
 *
 * <p>Making this an exception instead of a return value forces the user to handle
 * these case explicitely in a try { ... } catch. The following code illustrates
 * how this may be done:</p>
 *
 * <PRE>
 *     ...
 *     try {
 *         il.delete(start_ih, end_ih);
 *     } catch(TargetLostException e) {
 *         for (InstructionHandle target : e.getTargets()) {
 *             for (InstructionTargeter targeter : target.getTargeters()) {
 *                 targeter.updateTarget(target, new_target);
 *             }
 *         }
 *     }
 * </PRE>
 *
 * @see InstructionHandle
 * @see InstructionList
 * @see InstructionTargeter
 */
public final class TargetLostException extends Exception {

    private static final long serialVersionUID = -6857272667645328384L;
    private final InstructionHandle[] targets;

    TargetLostException(final InstructionHandle[] t, final String mesg) {
        super(mesg);
        targets = t;
    }

    /**
     * @return list of instructions still being targeted.
     */
    public InstructionHandle[] getTargets() {
        return targets;
    }
}
