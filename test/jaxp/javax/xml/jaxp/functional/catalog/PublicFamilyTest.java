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
import static catalog.ResolutionChecker.checkPubIdResolution;.extended
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogResolver;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 8077931
 * @library /javax/xml/jaxp/libs
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow catalog.PublicFamilyTest
 * @run testng/othervm catalog.PublicFamilyTest
 * @summary Get matched URIs from public and delegatePublic entries.
 *          It tests the resolution priorities among the public family entries.
 *          The test rule is based on OASIS Standard V1.1 section 7.1.2.
 *          "Resolution of External Identifiers".
 */
@Listeners({jaxp.library.FilePolicy.class})
public class PublicFamilyTest {

    /*
     * Gets the best match from public and delegatePublic entries.
     * The match in public entry is prior to the match in delegatePublic entry.
     */
    @Test
    public void testMatch() {
        checkPubIdResolution(createResolver(),
                "-//REMOTE//DTD ALICE DOCALICE XML//EN",
                "http://local/base/dtd/docAlicePub.dtd");
    }

    /*
     * If no match is found, a CatalogException should be thrown.
     */
    @Test(expectedExceptions = CatalogException.class)
    public void testNoMatched() {
        checkNoMatch(createResolver());
    }

    private CatalogResolver createResolver() {
        return catalogResolver("publicFamily.xml");
    }
}
