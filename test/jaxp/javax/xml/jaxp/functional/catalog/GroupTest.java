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
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.GroupTest
 * @run testng/othervm catalog.GroupTest
 * @summary Get matched URIs from system, public and uri entries respectively,
 *          and some of the entries are enclosed by group entries.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class GroupTest {

    private static final String CATALOG_GROUP = "group.xml";

    @Test(dataProvider = "systemId-matchedUri")
    public void testMatchOnSysId(String uri, String matchedUri) {
        checkSysIdResolution(createResolver(), uri, matchedUri);
    }

    @DataProvider(name = "systemId-matchedUri")
    public Object[][] dataOnSysId() {
        return new Object[][] {
                // The matched URI of the specified system id is enclosed by a
                // group entry.
                { "http://remote/dtd/sys/alice/docAlice.dtd",
                        "http://local/base/dtd/docAliceSys.dtd" },

                // The matched URI of the specified system id is enclosed by a
                // group entry, which defines another base.
                { "http://remote/dtd/sys/bob/docBob.dtd",
                        "http://local/bobBase/dtd/docBobSys.dtd" },

                // The catalog file defines a set of system entries. Some of
                // them are enclosed by a group entry, and others are out of the
                // group entry. All of them can match the specified system id.
                // But the returned matched URI should be in the first one.
                { "http://remote/dtd/sys/carl/docCarl.dtd",
                        "http://local/base/dtd/docCarlSys1.dtd" } };
    }

    @Test(dataProvider = "publicId-matchedUri")
    public void testMatchOnPubId(String uri, String matchedUri) {
        checkPubIdResolution(createResolver(), uri, matchedUri);
    }

    @DataProvider(name = "publicId-matchedUri")
    public Object[][] dataOnPubId() {
        return new Object[][] {
                // The matched URI of the specified public id is enclosed by a
                // group entry.
                { "-//REMOTE//DTD ALICE DOCALICE XML//EN",
                        "http://local/base/dtd/docAlicePub.dtd" },

                // The matched URI of the specified public id is enclosed by a
                // group entry, which defines another base.
                { "-//REMOTE//DTD BOB DOCBOB XML//EN",
                        "http://local/bobBase/dtd/docBobPub.dtd" },

                // The catalog file defines a set of public entries. Some of
                // them are enclosed by a group entry, and others are out of the
                // group entry. All of them can match the specified public id.
                // But the returned matched URI should be in the first one.
                { "-//REMOTE//DTD CARL DOCCARL XML//EN",
                        "http://local/base/dtd/docCarlPub1.dtd" } };
    }

    @Test(dataProvider = "uri-matchedUri")
    public void testMatchOnUri(String uri, String matchedUri) {
        checkUriResolution(catalogUriResolver(CATALOG_GROUP), uri, matchedUri);
    }

    @DataProvider(name = "uri-matchedUri")
    public Object[][] dataOnUri() {
        return new Object[][] {
                // The matched URI of the specified URI reference is enclosed by
                // a group entry.
                { "http://remote/dtd/uri/alice/docAlice.dtd",
                        "http://local/base/dtd/docAliceURI.dtd" },

                // The matched URI of the specified URI reference is enclosed by
                // a group entry, which defines another base.
                { "http://remote/dtd/uri/bob/docBob.dtd",
                        "http://local/bobBase/dtd/docBobURI.dtd" },

                // The catalog file defines a set of uri entries. Some of
                // them are enclosed by a group entry, and others are out of the
                // group entry. All of them can match the specified URI reference.
                // But the returned matched URI should be in the first one.
                { "http://remote/dtd/uri/alice/docAlice.dtd",
                        "http://local/base/dtd/docAliceURI.dtd" } };
    }

    private CatalogResolver createResolver() {
        return catalogResolver(CATALOG_GROUP);
    }
}
