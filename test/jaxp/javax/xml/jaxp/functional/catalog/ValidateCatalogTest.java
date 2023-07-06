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

package catalog;

import static catalog.CatalogTestUtils.CATALOG_SYSTEM;.extended
import static catalog.CatalogTestUtils.CATALOG_URI;.extended
import static catalog.CatalogTestUtils.catalogResolver;.extended
import static catalog.CatalogTestUtils.catalogUriResolver;.extended
import static catalog.ResolutionChecker.checkSysIdResolution;.extended
import static catalog.ResolutionChecker.checkUriResolution;.extended
import javax.xml.catalog.CatalogException;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8077931
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.ValidateCatalogTest
 * @run testng/othervm catalog.ValidateCatalogTest
 * @summary A legal catalog file must be well-formed XML, the root element
 *          must be catalog, and the naming space of the root element must be
 *          urn:oasis:names:tc:entity:xmlns:xml:catalog.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class ValidateCatalogTest {

    private static final String CATALOG_WRONGROOT = "validateCatalog-wrongRoot.xml";
    private static final String CATALOG_MALFORMED = "validateCatalog-malformed.xml";

    /*
     * EntityResolver tries to load catalog with wrong root,
     * it should throw CatalogException.
     */
    @Test(expectedExceptions = CatalogException.class)
    public void validateWrongRootCatalogOnEntityResolver() {
        catalogResolver(CATALOG_WRONGROOT);
    }

    /*
     * URIResolver tries to load catalog with wrong root,
     * it should throw CatalogException.
     */
    @Test(expectedExceptions = CatalogException.class)
    public void validateWrongRootCatalogOnUriResolver() {
        catalogUriResolver(CATALOG_WRONGROOT);
    }

    /*
     * EntityResolver tries to load malformed catalog,
     * it should throw RuntimeException.
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void validateMalformedCatalogOnEntityResolver() {
        catalogResolver(CATALOG_MALFORMED);
    }

    /*
     * UriResolver tries to load malformed catalog,
     * it should throw RuntimeException.
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void validateMalformedCatalogOnUriResolver() {
        catalogUriResolver(CATALOG_MALFORMED);
    }

    /*
     * Resolver should ignore the catalog which doesn't declare the correct
     * naming space.
     */
    @Test
    public void validateWrongNamingSpaceCatalog() {
        String catalogName = "validateCatalog-noNamingSpace.xml";
        checkSysIdResolution(catalogResolver(catalogName, CATALOG_SYSTEM),
                "http://remote/dtd/alice/docAlice.dtd",
                "http://local/dtd/docAliceSys.dtd");
        checkUriResolution(catalogUriResolver(catalogName, CATALOG_URI),
                "http://remote/dtd/uri/alice/docAlice.dtd",
                "http://local/dtd/docAliceURI.dtd");
    }
}