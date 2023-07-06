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

package java.xml.share.classes.javax.xml.stream.events;

import java.util.java.util.java.util.java.util.List;

/**
 * This is the top level interface for events dealing with DTDs
 *
 * @version 1.0
 */
public interface DTD extends XMLEvent {

    /**
     * Returns the entire Document Type Declaration as a string, including the
     * internal DTD subset. This may be null if there is not an internal subset.
     * If it is not null it must return the entire Document Type Declaration
     * which matches the doctypedecl production in the XML 1.0 specification
     *
     * @return the Document Type Declaration
     */
    String getDocumentTypeDeclaration();

    /**
     * Returns an implementation defined representation of the DTD. This method
     * may return null if no representation is available.
     *
     * @return the representation of the DTD
     */
    Object getProcessedDTD();

    /**
     * Return a List containing the notations declared in the DTD. This list
     * must contain NotationDeclaration events.
     *
     * @see NotationDeclaration
     * @return an unordered list of NotationDeclaration events
     */
    List<NotationDeclaration> getNotations();

    /**
     * Return a List containing the general entities, both external and
     * internal, declared in the DTD. This list must contain EntityDeclaration
     * events.
     *
     * @see EntityDeclaration
     * @return an unordered list of EntityDeclaration events
     */
    List<EntityDeclaration> getEntities();
}
