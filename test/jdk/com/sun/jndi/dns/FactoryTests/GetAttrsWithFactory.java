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
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

/*
 * @test
 * @bug 8208483
 * @summary Tests that setting the object factory and
 *          com.sun.jndi.dns.lookup.attr properties does not affect how
 *          we can get the attributes of a DNS entry.
 *          Use getAttributes form that has no attrIds parameter.
 * @library ../lib/ ../AttributeTests/
 * @modules java.base/sun.security.util
 * @build TestDnsObject TxtFactory
 * @run main/othervm GetAttrsWithFactory
 */

public class GetAttrsWithFactory extends GetAttrsBase {

    public static void main(String[] args) throws Exception {
        new GetAttrsWithFactory().run(args);
    }

    /*
     * Tests that setting the object factory and
     * com.sun.jndi.dns.lookup.attr properties does not affect how
     * we can get the attributes of a DNS entry.
     */
    @Override
    public void runTest() throws Exception {
        // initial context with object factory and lookup attr
        env().put(Context.OBJECT_FACTORIES, "TxtFactory");
        env().put(LookupFactoryBase.DNS_LOOKUP_ATTR, "A");
        setContext(new InitialDirContext(env()));

        Attributes retAttrs = getAttributes();
        verifyAttributes(retAttrs);
    }

    /*
     * Use getAttributes form that has no attrIds parameter.
     */
    @Override
    public Attributes getAttributes() throws NamingException {
        return context().getAttributes(getKey());
    }
}
