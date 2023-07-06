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

package test;


import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.base.share.classes.java.util.Locale;














public class NumberFormatProviderImpl extends NumberFormatProvider {

    private static final Locale[] locales = {Locale.US, Locale.FRENCH,
            Locale.GERMAN, new Locale("fi", "FI")};

    @Override
    public NumberFormat getCurrencyInstance(Locale locale) {
        return null;
    }

    @Override
    public NumberFormat getIntegerInstance(Locale locale) {
        return null;
    }

    @Override
    public NumberFormat getNumberInstance(Locale locale) {
        return new NumberFormatImpl();
    }

    @Override
    public NumberFormat getPercentInstance(Locale locale) {
        return null;
    }

    @Override
    public Locale[] getAvailableLocales() {
        return locales;
    }
}

