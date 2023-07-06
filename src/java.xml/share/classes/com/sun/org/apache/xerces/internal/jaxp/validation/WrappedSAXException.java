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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;


import java.xml.share.classes.com.sun.org.xml.sax.SAXException;















/**
 * Wraps {@link SAXException} and make it an unchecked exception.
 * <p>
 * Xerces XNI doesn't allow {@link SAXException} to be thrown.
 * So when the user-supplied error handler throws it,
 * it needs to be tunneled through Xerces.
 *
 * <p>
 * It is a bug if this exception "leaks" to the application.
 *
 * FIXME: use XNIException for this purpose. It's already doing this
 * kind of SAXException tunneling.
 *
 *     Kohsuke Kawaguchi
 *
 * @LastModified: Oct 2017
 */
public class WrappedSAXException extends RuntimeException {
    private static final long serialVersionUID = -3201986204982729962L;

    public final SAXException exception;

    WrappedSAXException( SAXException e ) {
        this.exception = e;
    }
}
