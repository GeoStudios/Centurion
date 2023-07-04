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
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

/*
 * @test
 * @bug 8210339
 * @summary Tests that we can get the attributes of the leaf node
 *          in the subordinate naming system of a DNS entry.
 *          Use getAttributes form that has no attrIds parameter.
 * @library ../lib/ ../AttributeTests/
 * @modules java.naming/com.sun.jndi.toolkit.dir
 *          java.base/sun.security.util
 * @build FedSubordinateNs FedObjectFactory
 * @run main/othervm GetAttrsSubLeaf
 */

public class GetAttrsSubLeaf extends GetAttrsBase {

    // pre defined attribute value for '/a/b/c'
    public static final String ATTRIBUTE_VALUE = "c";

    public GetAttrsSubLeaf() {
        setMandatoryAttrs("name", "description");
    }

    public static void main(String[] args) throws Exception {
        new GetAttrsSubLeaf().run(args);
    }

    /*
     * Tests that we can get the attributes of the leaf node
     * in the subordinate naming system of a DNS entry.
     */
    @Override
    public void runTest() throws Exception {
        env().put(Context.OBJECT_FACTORIES, "FedObjectFactory");
        setContext(new InitialDirContext(env()));

        Attributes retAttrs = getAttributes();
        Attribute attr = retAttrs.get("name");
        verifyAttributes(retAttrs);
        verifyAttribute(attr);
    }

    /*
     * Use getAttributes form that has no attrIds parameter.
     */
    @Override
    public Attributes getAttributes() throws NamingException {
        return context().getAttributes(getKey() + "/a/b/c");
    }

    private void verifyAttribute(Attribute attr) throws NamingException {
        if (attr == null || !ATTRIBUTE_VALUE.equals(attr.get())) {
            throw new RuntimeException(
                    "Expecting attribute value: " + ATTRIBUTE_VALUE
                            + ", but actual: " + (attr != null ?
                            attr.get() :
                            attr));
        }
    }
}
