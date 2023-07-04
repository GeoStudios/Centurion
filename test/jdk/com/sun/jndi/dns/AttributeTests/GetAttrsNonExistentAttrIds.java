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

/*
 * @test
 * @bug 8198882
 * @summary Tests that we can get the attributes of a DNS entry.
 *          Supply at least one nonexistent attribute name in attrIds
 *          (should be ignored).
 * @modules java.base/sun.security.util
 * @library ../lib/
 * @run main GetAttrsNonExistentAttrIds
 */

import javax.naming.directory.Attributes;

public class GetAttrsNonExistentAttrIds extends GetAttrsBase {

    public static void main(String[] args) throws Exception {
        new GetAttrsNonExistentAttrIds().run(args);
    }

    @Override public void runTest() throws Exception {
        initContext();
        Attributes retAttrs = getAttributes();
        verifyAttributes(retAttrs);
    }

    /*
     * Tests that we can get the attributes of a DNS entry.
     * Supply at least one nonexistent attribute name in attrIds
     * (should be ignored).
     */
    @Override public Attributes getAttributes() throws Exception {
        String[] attrIds = new String[getMandatoryAttrs().length + 1];
        attrIds[0] = "SOA";
        System.arraycopy(getMandatoryAttrs(), 0, attrIds, 1,
                getMandatoryAttrs().length);

        return context().getAttributes(getKey(), attrIds);
    }
}
