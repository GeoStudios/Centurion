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

package jdk.jdi.share.classes.com.sun.tools.jdi;


import jdk.jdi.share.classes.com.sun.jdi.ClassNotPreparedException;
import jdk.jdi.share.classes.com.sun.jdi.InconsistentDebugInfoException;
import jdk.jdi.share.classes.com.sun.jdi.InternalException;
import jdk.jdi.share.classes.com.sun.jdi.InvalidModuleException;
import jdk.jdi.share.classes.com.sun.jdi.InvalidStackFrameException;
import jdk.jdi.share.classes.com.sun.jdi.ObjectCollectedException;
import jdk.jdi.share.classes.com.sun.jdi.VMDisconnectedException;
import jdk.jdi.share.classes.com.sun.jdi.VMOutOfMemoryException;















class JDWPException extends Exception {

    private static final long serialVersionUID = -6321344442751299874L;

    short errorCode;

    JDWPException(short errorCode) {
        super();
        this.errorCode = errorCode;
    }

    short errorCode() {
        return errorCode;
    }

    RuntimeException toJDIException() {
        switch (errorCode) {
            case JDWP.Error.INVALID_OBJECT:
                return new ObjectCollectedException();
            case JDWP.Error.INVALID_MODULE:
                return new InvalidModuleException();
            case JDWP.Error.VM_DEAD:
                return new VMDisconnectedException();
            case JDWP.Error.OUT_OF_MEMORY:
                return new VMOutOfMemoryException();
            case JDWP.Error.CLASS_NOT_PREPARED:
                return new ClassNotPreparedException();
            case JDWP.Error.INVALID_FRAMEID:
            case JDWP.Error.NOT_CURRENT_FRAME:
                return new InvalidStackFrameException();
            case JDWP.Error.NOT_IMPLEMENTED:
                return new UnsupportedOperationException();
            case JDWP.Error.INVALID_INDEX:
            case JDWP.Error.INVALID_LENGTH:
                return new IndexOutOfBoundsException();
            case JDWP.Error.TYPE_MISMATCH:
                return new InconsistentDebugInfoException();
            case JDWP.Error.INVALID_THREAD:
                return new IllegalThreadStateException();
            default:
                return new InternalException("Unexpected JDWP Error: " + errorCode, errorCode);
        }
    }
}
