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
 * @bug 6299235 8210408
 * @summary test Bug 6299235 to make sure the third-party provided sun resources
 *          could be picked up.
 * @modules java.desktop
 * @library patches
 * @build java.desktop/sun.awt.resources.awt_ru_RU
 * @run main Bug6299235Test
 */

import java.awt.Toolkit;
import java.util.Locale;

/*
 * After introducing CoreResourceBundleControl for Awt/Swing resources
 * loading, non-existent resources won't be actually searched from
 * bootclasspath and extension directory. But we should still fallback
 * to the current behavior which allows the third-part to provide their
 * own version of awt resources, for example even though we never claim
 * we support it yet.
 * Look into bug 6299235 for more details.
 */

public class Bug6299235Test {
    private static final Locale ru_RU = new Locale("ru", "RU");

    public static void main(String args[]) {
        Locale locale = Locale.getDefault();
        try {
            Locale.setDefault(ru_RU);
            // Get the value for the test key "foo"
            String value = Toolkit.getProperty("foo", "undefined");
            if (!value.equals("bar")) {
                throw new RuntimeException("key = foo, value = " + value);
            }
            // Get the value for a valid key "AWT.enter"
            value = Toolkit.getProperty("AWT.enter", "DO NOT ENTER");
            if (value.equals("DO NOT ENTER")) {
                throw new RuntimeException("AWT.enter undefined.");
            }
        } finally {
            // Restore the default Locale
            Locale.setDefault(locale);
        }
        System.out.println("Bug6299235Test passed");
    }
}
