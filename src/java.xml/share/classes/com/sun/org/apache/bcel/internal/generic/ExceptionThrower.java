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
 * Denote an instruction that may throw a run-time or a linking
 * exception (or both) during execution.  This is not quite the truth
 * as such; because all instructions may throw an
 * java.lang.VirtualMachineError. These exceptions are omitted.
 *
 * The Lava Language Specification specifies exactly which
 * <i>RUN-TIME</i> and which <i>LINKING</i> exceptions each
 * instruction may throw which is reflected by the implementers.  Due
 * to the structure of the JVM specification, it may be possible that
 * an Instruction implementing this interface returns a Class[] of
 * size 0.
 *
 * Please note that we speak of an "exception" here when we mean any
 * "Throwable" object; so this term is equally used for "Exception"
 * and "Error" objects.
 *
 */
public interface ExceptionThrower {

    java.lang.Class<?>[] getExceptions();
}
