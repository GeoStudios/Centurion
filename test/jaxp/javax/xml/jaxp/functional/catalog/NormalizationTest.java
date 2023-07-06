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

import static catalog.CatalogTestUtils.catalogResolver;.extended
import static catalog.CatalogTestUtils.catalogUriResolver;.extended
import static catalog.ResolutionChecker.checkPubIdResolution;.extended
import static catalog.ResolutionChecker.checkSysIdResolution;.extended
import static catalog.ResolutionChecker.checkUriResolution;.extended
import javax.xml.catalog.CatalogResolver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8077931
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.NormalizationTest
 * @run testng/othervm catalog.NormalizationTest
 * @summary Before matching identifiers and URI references, it has to normalize
 *          the passed identifiers and URI references. And then the catalog
 *          resolver uses the normalized stuff to search the counterparts in
 *          catalog files.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class NormalizationTest {

    private static final String CATALOG_NORMALIZATION = "normalization.xml";

    @Test(dataProvider = "systemId_uri-matchedUri")
    public void testNormalizationOnSysId(String sytemId, String matchedUri) {
        checkSysIdResolution(createEntityResolver(), sytemId, matchedUri);
    }

    @Test(dataProvider = "publicId-matchedUri")
    public void testNormalizationOnPubId(String publicId, String matchedUri) {
        checkPubIdResolution(createEntityResolver(), publicId, matchedUri);
    }

    @Test(dataProvider = "systemId_uri-matchedUri")
    public void testNormalizationOnUri(String uri, String matchedUri) {
        checkUriResolution(createUriResolver(), uri, matchedUri);
    }

    @DataProvider(name = "systemId_uri-matchedUri")
    public Object[][] dataOnSysIdAndUri() {
        return new Object[][] {
                // The specified system id/URI reference contains spaces. And
                // the counterparts in system/uri entries also contain spaces.
                { "  http://remote/dtd/alice/docAlice.dtd  ",
                        "http://local/base/dtd/docAliceSys.dtd" },

                // The specified system id/URI reference doesn't contain space.
                // But the counterparts in system/uri entries contain spaces.
                { "http://remote/dtd/alice/docAlice.dtd",
                        "http://local/base/dtd/docAliceSys.dtd" },

                // The specified system id/URI reference contains special chars.
                { "http://remote/dtd/bob/docBob<>\\^`{|}.dtd",
                        "http://local/base/dtd/docBobSys.dtd" },

                // The specified system identifier/uri contains normalized chars.
                { "http://remote/dtd/bob/docBob%3C%3E%5C%5E%60%7B%7C%7D.dtd",
                        "http://local/base/dtd/docBobSys.dtd" } };
    }

    @DataProvider(name = "publicId-matchedUri")
    public Object[][] dataOnPubId() {
        return new Object[][] {
                // The specified public id contains spaces. And the counterparts
                // in public entry also contains spaces.
                { "   -//REMOTE//DTD    ALICE DOCALICE   XML//EN  ",
                        "http://local/base/dtd/docAlicePub.dtd" },

                // The specified public id doesn't contain space. But the
                // counterpart in public entry contains spaces.
                { "-//REMOTE//DTD ALICE DOCALICE XML//EN",
                        "http://local/base/dtd/docAlicePub.dtd" },

                // The specified public id contains spaces.
                { "  -//REMOTE//DTD   BOB  DOCBOB  XML//EN",
                        "http://local/base/dtd/docBobPub.dtd" } };
    }

    private CatalogResolver createEntityResolver() {
        return catalogResolver(CATALOG_NORMALIZATION);
    }

    private CatalogResolver createUriResolver() {
        return catalogUriResolver(CATALOG_NORMALIZATION);
    }
}