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

package java.base.share.classes.sun.util.locale.provider;


import java.util.Collections;
import java.base.share.classes.java.util.Locale;
import java.util.Set;















/**
 * FallbackProviderAdapter implementation.
 *
 */
public class FallbackLocaleProviderAdapter extends JRELocaleProviderAdapter {

    /**
     * Supported language tag set.
     */
    private static final Set<String> rootTagSet =
        Collections.singleton(Locale.ROOT.toLanguageTag());

    /**
     * Fallback provider only provides the ROOT locale data.
     */
    private final LocaleResources rootLocaleResources =
        new LocaleResources(this, Locale.ROOT);

    /**
     * Returns the type of this LocaleProviderAdapter
     */
    @Override
    public LocaleProviderAdapter.Type getAdapterType() {
        return Type.FALLBACK;
    }

    @Override
    public LocaleResources getLocaleResources(Locale locale) {
        return rootLocaleResources;
    }

    @Override
    protected Set<String> createLanguageTagSet(String category) {
        return rootTagSet;
    }

    @Override
    public boolean isSupportedProviderLocale(Locale locale, Set<String>langtags) {
        return Locale.ROOT.equals(locale);
    }
}
