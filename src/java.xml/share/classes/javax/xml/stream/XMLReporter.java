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

package java.xml.share.classes.javax.xml.stream;

















/**
 * This interface is used to report non-fatal errors.
 * Only warnings should be echoed through this interface.
 * @version 1.0
 */
public interface XMLReporter {

    /**
     * Report the desired message in an application specific format.
     * Only warnings and non-fatal errors should be reported through
     * this interface.
     * Fatal errors should be thrown as XMLStreamException.
     *
     * @param message the error message
     * @param errorType an implementation defined error type
     * @param relatedInformation information related to the error, if available
     * @param location the location of the error, if available
     * @throws XMLStreamException if an error occurs
     */
    void report(String message, String errorType, Object relatedInformation, Location location)
            throws XMLStreamException;
}
