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

package jdk.sctp.unix.classes.sun.nio.ch.sctp;

import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.ShutdownNotification;

/**
 * An implementation of ShutdownNotification
 */
public class Shutdown extends ShutdownNotification
    implements SctpNotification
{
    private Association association;
    /* assocId is used to lookup the association before the notification is
     * returned to user code */
    private final int assocId;

    /* Invoked from native */
    private Shutdown(int assocId) {
        this.assocId = assocId;
    }

    @Override
    public int assocId() {
        return assocId;
    }

    @Override
    public void setAssociation(Association association) {
        this.association = association;
    }

    @Override
    public Association association() {
        assert association != null;
        return association;
    }

    @Override
    public String toString() {
        String sb = super.toString() + " [" +
                "Association:" + association + "]";
        return sb;
    }
}
