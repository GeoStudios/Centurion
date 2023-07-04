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

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;

/**
 * @test
 * @bug 5042429
 * @summary This test verifies if ICC_profile instances for standard ColorSpace
 *          types are created without security exceptions if access to file
 *          system is prohibited.
 * @run main/othervm/policy=StandardProfileTest.policy StandardProfileTest
 */
public final class StandardProfileTest {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            throw new RuntimeException("SecurityManager is null");
        }

        int[] types = {
            ColorSpace.CS_CIEXYZ,
            ColorSpace.CS_GRAY,
            ColorSpace.CS_LINEAR_RGB,
            ColorSpace.CS_PYCC,
            ColorSpace.CS_sRGB } ;

        for (int t = 0; t<types.length; t++) {
            System.out.println("type " + t);
            ICC_Profile p = ICC_Profile.getInstance(types[t]);
            p.getPCSType();
        }
    }
}
