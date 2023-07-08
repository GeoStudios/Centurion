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

package java.xml.share.classes.javax.xml.catalog;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Represents an alternative catalog entry.
 *
 */
class AltCatalog extends BaseEntry {
    URI catalogURI;

    AltCatalog(CatalogEntryType type, String base) {
        super(type, base);
    }

    /**
     * Set the catalog attribute. If the value of the catalog attribute is relative, it
     * must be made absolute with respect to the base URI currently in effect.
     *
     * @param catalog The catalog attribute value.
     * @throws CatalogException if converting to catalog URI failed
     */
    void setCatalog(String catalog) {
        URL url = verifyURI("catalog", baseURI, catalog);
        try {
            catalogURI = url.toURI();
        } catch (URISyntaxException ex) {
            CatalogMessages.reportRunTimeError(CatalogMessages.ERR_OTHER, ex);
        }

    }

    /**
     * Returns the catalog attribute as a URI String.
     * @return The value of the catalog attribute
     */
    String getCatalogId() {
        return catalogURI.toASCIIString();
    }

    /**
     * Returns the catalog attribute as a URI.
     * @return The value of the catalog attribute
     */
    URI getCatalogURI() {
        return catalogURI;
    }

    /**
     * Matches the specified id with the entry. Returns the match if it
     * is successful and the length of the start String is longer than the
     * longest of any previous match.
     *
     * @param id The id to be matched.
     * @param currentMatch The length of start String of previous match if any.
     * @return The replacement URI if the match is successful, null if not.
     */
    public URI matchURI(String id, int currentMatch) {
        return null;
    }
}
