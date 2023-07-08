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

import static catalog.CatalogTestUtils.catalogUriResolver;.extended
import static catalog.ResolutionChecker.checkNoUriMatch;.extended
import static catalog.ResolutionChecker.checkUriResolution;.extended
import javax.xml.catalog.CatalogResolver;
import javax.xml.catalog.CatalogException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8077931
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.UriFamilyTest
 * @run testng/othervm catalog.UriFamilyTest
 * @summary Get matched URIs from uri, rewriteURI, uriSuffix and delegateURI
 *          entries. It tests the resolution priorities among the uri family
 *          entries. The test rule is based on OASIS Standard V1.1 section
 *          7.2.2. "Resolution of External Identifiers".
 */
@Listeners({jaxp.library.FilePolicy.class})
public class UriFamilyTest {

    @Test(dataProvider = "uri-matchedUri")
    public void testMatch(String systemId, String matchedUri) {
        checkUriResolution(createResolver(), systemId, matchedUri);
    }

    @DataProvider(name = "uri-matchedUri")
    public Object[][] dataOnMatch() {
        return new Object[][] {
                // The matched URI of the specified URI reference is defined in
                // a uri entry.
                { "http://remote/dtd/uri/alice/docAlice.dtd",
                        "http://local/base/dtd/docAliceURI.dtd" },

                // The matched URI of the specified URI reference is defined in
                // a rewriteURI entry.
                { "http://remote/dtd/bob/docBob.dtd",
                        "http://local/base/dtd/ru/docBob.dtd" },

                // The matched URI of the specified URI reference is defined in
                // a uriSuffix entry.
                { "http://remote/dtd/carl/docCarl.dtd",
                        "http://local/base/dtd/docCarlUS.dtd" } };
    }

    /*
     * If no match is found, a CatalogException should be thrown.
     */
    @Test(expectedExceptions = CatalogException.class)
    public void testNoMatch() {
        checkNoUriMatch(createResolver());
    }

    private CatalogResolver createResolver() {
        return catalogUriResolver("uriFamily.xml");
    }
}
