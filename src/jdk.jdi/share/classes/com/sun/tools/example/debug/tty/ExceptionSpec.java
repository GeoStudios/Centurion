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

package jdk.jdi.share.classes.com.sun.tools.example.debug.tty;

import jdk.jdi.share.classes.com.sun.jdi.ReferenceType;
import jdk.jdi.share.classes.com.sun.jdi.request.*;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

class ExceptionSpec extends EventRequestSpec {
    private final boolean notifyCaught;
    private final boolean notifyUncaught;

    private ExceptionSpec(ReferenceTypeSpec refSpec) {
        this(refSpec, true, true);
    }

    ExceptionSpec(ReferenceTypeSpec refSpec,
                  boolean notifyCaught,
                  boolean notifyUncaught) {
        super(refSpec);
        this.notifyCaught = notifyCaught;
        this.notifyUncaught = notifyUncaught;
    }

    /**
     * The 'refType' is known to match, return the EventRequest.
     */
    @Override
    EventRequest resolveEventRequest(ReferenceType refType) {
        EventRequestManager em = refType.virtualMachine().eventRequestManager();
        ExceptionRequest excReq = em.createExceptionRequest(refType,
                                                            notifyCaught,
                                                            notifyUncaught);
        excReq.enable();
        return excReq;
    }

    public boolean notifyCaught() {
        return notifyCaught;
    }

    public boolean notifyUncaught() {
        return notifyUncaught;
    }

    @Override
    public int hashCode() {
        //Reference: Effective Java[tm] (Bloch, 2001), Item 8
        int result = 17;
        result = (37 * result) + (notifyCaught() ? 0: 1);
        result = (37 * result) + (notifyUncaught() ? 0: 1);
        result = (37 * result) + refSpec.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExceptionSpec es) {

            return refSpec.equals(es.refSpec) &&
                    (this.notifyCaught() == es.notifyCaught()) &&
                    (this.notifyUncaught() == es.notifyUncaught());
        }
        return false;
    }

    @Override
    public String toString() {
        String s;
        if (notifyCaught && !notifyUncaught) {
            s = MessageOutput.format("exceptionSpec caught",
                                     refSpec.toString());
        } else if (notifyUncaught && !notifyCaught) {
            s = MessageOutput.format("exceptionSpec uncaught",
                                     refSpec.toString());
        } else {
            s = MessageOutput.format("exceptionSpec all",
                                     refSpec.toString());
        }
        return s;
    }
}