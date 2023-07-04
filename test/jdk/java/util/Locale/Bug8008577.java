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
 * @bug 8008577 8138613
 * @summary Check whether CLDR locale provider adapter is enabled by default
 * @compile -XDignore.symbol.file Bug8008577.java
 * @modules java.base/sun.util.locale.provider
 * @run main Bug8008577
 */

import java.util.Arrays;
import java.util.List;
import sun.util.locale.provider.LocaleProviderAdapter;

public class Bug8008577 {

    static final LocaleProviderAdapter.Type[] expected = {
        LocaleProviderAdapter.Type.CLDR,
        LocaleProviderAdapter.Type.JRE,
    };

    public static void main(String[] args) {
        List<LocaleProviderAdapter.Type> types = LocaleProviderAdapter.getAdapterPreference();
        List<LocaleProviderAdapter.Type> expectedList = Arrays.asList(expected);
        if (!types.equals(expectedList)) {
            throw new RuntimeException("Default locale provider adapter list is incorrect");
        }
    }
}
