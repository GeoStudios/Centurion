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

package java.xml.share.classes.org.w3c.dom;

/**
 * This interface represents a notation declared in the DTD. A notation either
 * declares, by name, the format of an unparsed entity (see <a href='http://www.w3.org/TR/2004/REC-xml-20040204#Notations'>section 4.7</a> of the XML 1.0 specification [<a href='http://www.w3.org/TR/2004/REC-xml-20040204'>XML 1.0</a>]), or is
 * used for formal declaration of processing instruction targets (see <a href='http://www.w3.org/TR/2004/REC-xml-20040204#sec-pi'>section 2.6</a> of the XML 1.0 specification [<a href='http://www.w3.org/TR/2004/REC-xml-20040204'>XML 1.0</a>]). The
 * <code>nodeName</code> attribute inherited from <code>Node</code> is set
 * to the declared name of the notation.
 * <p>The DOM Core does not support editing <code>Notation</code> nodes; they
 * are therefore readonly.
 * <p>A <code>Notation</code> node does not have any parent.
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407'>Document Object Model (DOM) Level 3 Core Specification</a>.
 */
public interface Notation extends Node {
    /**
     * The public identifier of this notation. If the public identifier was
     * not specified, this is <code>null</code>.
     */
    String getPublicId();

    /**
     * The system identifier of this notation. If the system identifier was
     * not specified, this is <code>null</code>. This may be an absolute URI
     * or not.
     */
    String getSystemId();

}
