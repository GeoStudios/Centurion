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

package java.net.http.share.classes.jdk.internal.net.http.frame;

















public class MalformedFrame extends Http2Frame {

    private final int errorCode;
    // if errorStream == 0 means Connection Error; RFC 7540 5.4.1
    // if errorStream != 0 means Stream Error; RFC 7540 5.4.2
    private final int errorStream;
    private final String msg;

    /**
     * Creates Connection Error malformed frame
     * @param errorCode - error code, as specified by RFC 7540
     * @param msg - internal debug message
     */
    public MalformedFrame(int errorCode, String msg) {
        this(errorCode, 0 , msg);
    }

    /**
     * Creates Stream Error malformed frame
     * @param errorCode - error code, as specified by RFC 7540
     * @param errorStream - id of error stream (RST_FRAME will be send for this stream)
     * @param msg - internal debug message
     */
    public MalformedFrame(int errorCode, int errorStream, String msg) {
        super(0, 0);
        this.errorCode = errorCode;
        this.errorStream = errorStream;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return super.toString() + " MalformedFrame, Error: " + ErrorFrame.stringForCode(errorCode)
                + " streamid: " + streamid + " reason: " + msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return msg;
    }
}
