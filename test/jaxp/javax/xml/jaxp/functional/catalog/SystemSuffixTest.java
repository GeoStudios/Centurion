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
import static catalog.ResolutionChecker.checkNoMatch;.extended
import static catalog.ResolutionChecker.checkSysIdResolution;.extended
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogResolver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8077931
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.SystemSuffixTest
 * @run testng/othervm catalog.SystemSuffixTest
 * @summary Get matched URIs from systemSuffix entries.
 */
@Listeners({jaxp.library.FilePolicy.class})
public class SystemSuffixTest {

    @Test(dataProvider = "systemId-matchedUri")
    public void testMatch(String systemId, String matchedUri) {
        checkSysIdResolution(createResolver(), systemId, matchedUri);
    }

    @DataProvider(name = "systemId-matchedUri")
    public Object[][] dataOnMatch() {
        return new Object[][] {
                // The matched URI of the specified system id is defined in a
                // systemIdSuffix entry. The match is an absolute path.
                { "http://remote/dtd/alice/docAlice.dtd",
                        "http://local/dtd/docAliceSS.dtd" },

                // The matched URI of the specified system id is defined in a
                // systemIdSuffix entry. The match isn't an absolute path.
                { "http://remote/dtd/bob/docBob.dtd",
                        "http://local/base/dtd/docBobSS.dtd" },

                // The matched URI of the specified system id is defined in a
                // systemIdSuffix entry. The match isn't an absolute path, and
                // the systemIdSuffix entry defines alternative base. So the
                // returned URI should include the alternative base.
                { "http://remote/dtd/carl/cdocCarl.dtd",
                        "http://local/carlBase/dtd/docCarlSS.dtd" },

                // The catalog file defines two systemIdSuffix entries, and both
                // of them match the specified system id. But the first matched
                // URI should be returned.
                { "http://remote/dtd/david/docDavid.dtd",
                        "http://local/base/dtd/docDavidSS1.dtd" },

                // The catalog file defines two systemIdSuffix entries, and both
                // of them match the specified system id. But the longest match
                // should be used.
                { "http://remote/dtd/ella/docElla.dtd",
                        "http://local/base/dtd/docEllaSS.dtd" } };
    }

    /*
     * If no match is found, a CatalogException should be thrown.
     */
    @Test(expectedExceptions = CatalogException.class)
    public void testNoMatch() {
        checkNoMatch(createResolver());
    }

    private CatalogResolver createResolver() {
        return catalogResolver("systemSuffix.xml");
    }
}