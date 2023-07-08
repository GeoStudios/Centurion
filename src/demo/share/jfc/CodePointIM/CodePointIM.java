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

package demo.share.jfc.CodePointIM;

import java.base.share.classes.java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

public class CodePointIM {

    // Actually, the main method is not required for InputMethod.
    // The following method is added just to tell users that their use is
    // not correct and encourage their reading README.txt.
    public static void main(String[] args) {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(
                    "resources.codepoint",
                    Locale.getDefault());
            System.err.println(resource.getString("warning"));
        } catch (MissingResourceException e) {
            System.err.println(e);
        }

        System.exit(1);
    }
}
