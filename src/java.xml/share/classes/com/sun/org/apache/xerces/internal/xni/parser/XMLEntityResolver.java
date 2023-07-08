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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser;

import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This interface is used to resolve external parsed entities. The
 * application can register an object that implements this interface
 * with the parser configuration in order to intercept entities and
 * resolve them explicitly. If the registered entity resolver cannot
 * resolve the entity, it should return <code>null</code> so that the
 * parser will try to resolve the entity using a default mechanism.
 *
 * @see XMLParserConfiguration
 *
 *
 */
public interface XMLEntityResolver {

    //
    // XMLEntityResolver methods
    //

    /**
     * Resolves an external parsed entity. If the entity cannot be
     * resolved, this method should return null.
     *
     * @param resourceIdentifier location of the XML resource to resolve
     *
     * @throws XNIException Thrown on general error.
     * @throws IOException  Thrown if resolved entity stream cannot be
     *                      opened or some other i/o error occurs.
     * @see com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
     */
    XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier)
        throws XNIException, IOException;

} // interface XMLEntityResolver
