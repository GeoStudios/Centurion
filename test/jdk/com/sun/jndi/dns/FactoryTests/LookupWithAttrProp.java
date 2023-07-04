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

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;

/*
 * @test
 * @bug 8208483
 * @summary Tests that we can look up a DNS node
 *          and use an DirObjectFactory to return an object from it.
 *          Specify the "A" attribute to retrieve from the DNS node by using
 *          the com.sun.jndi.dns.lookup.attr property.
 * @library ../lib/
 * @modules java.base/sun.security.util
 * @build TestDnsObject DirAFactory
 * @run main/othervm LookupWithAttrProp
 */

public class LookupWithAttrProp extends LookupFactoryBase {

    public static void main(String[] args) throws Exception {
        new LookupWithAttrProp().run(args);
    }

    /*
     * Tests that we can look up a DNS node
     * and use an DirObjectFactory to return an object from it.
     * Specify the "A" attribute to retrieve from the DNS node by using
     * the com.sun.jndi.dns.lookup.attr property.
     */
    @Override
    public void runTest() throws Exception {
        // initial context with object factory and lookup attr
        env().put(Context.OBJECT_FACTORIES, "DirAFactory");
        env().put(DNS_LOOKUP_ATTR, "A");
        setContext(new InitialDirContext(env()));

        Object obj = context().lookup(getKey());
        verifyLookupObjectAndValue(obj, ATTRIBUTE_VALUE);
    }
}
