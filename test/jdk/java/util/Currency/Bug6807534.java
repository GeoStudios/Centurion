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
 * @bug 6807534
 * @summary check whether the default implementation of
 *    CurrencNameProvider.getDisplayName(String, Locale) throws appropriate
 *    exceptions when necessary.
 */

import java.util.Locale;
import java.util.spi.CurrencyNameProvider;

public class Bug6807534 {

    static final CurrencyNameProvider cnp = new CurrencyNameProviderImpl();

    public static void main(String[] args) throws Exception {
        // test for NullPointerException (currencyCode)
        try {
            cnp.getDisplayName(null, Locale.US);
            throwException("NPE was not thrown with null currencyCode");
        } catch (NullPointerException npe) {}

        // test for NullPointerException (locale)
        try {
            cnp.getDisplayName("USD", null);
            throwException("NPE was not thrown with null locale");
        } catch (NullPointerException npe) {}

        // test for IllegalArgumentException (illegal currencyCode)
        try {
            cnp.getDisplayName("INVALID", Locale.US);
            throwException("IllegalArgumentException was not thrown with invalid currency code");
        } catch (IllegalArgumentException iae) {}
        try {
            cnp.getDisplayName("inv", Locale.US);
            throwException("IllegalArgumentException was not thrown with invalid currency code");
        } catch (IllegalArgumentException iae) {}

        // test for IllegalArgumentException (non-supported locale)
        try {
            cnp.getDisplayName("USD", Locale.JAPAN);
            throwException("IllegalArgumentException was not thrown with non-supported locale");
        } catch (IllegalArgumentException iae) {}
    }

    static void throwException(String msg) {
        throw new RuntimeException("test failed. "+msg);
    }

    static class CurrencyNameProviderImpl extends CurrencyNameProvider {
        // dummy implementation
        public String getSymbol(String currencyCode, Locale locale) {
            return "";
        }

        public Locale[] getAvailableLocales() {
            Locale[] avail = new Locale[1];
            avail[0] = Locale.US;
            return avail;
        }
    }
}
