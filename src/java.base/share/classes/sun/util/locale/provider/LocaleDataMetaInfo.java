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

package sun.util.locale.provider;

import java.util.Map;

/**
 * LocaleData meta info SPI
 *
 */
public interface LocaleDataMetaInfo {

    /**
     * Returns the type of LocaleProviderAdapter for which this LocaleData
     * provides the data.
     * @return type The type of the adapter.
     */
    LocaleProviderAdapter.Type getType();

    /**
     * Returns the string concatenation of the supported language tags in
     * this LocaleData instance
     * @param category category of the locale data.
     * @return concatenated language tags, separated by a space.
     */
    String availableLanguageTags(String category);

    /**
     * Returns a map for time zone ids to their canonical ids.
     * The map key is either an LDML's short id, or a valid
     * TZDB zone id.
     * @return map of ids to their canonical ids.
     */
    default Map<String, String>  tzCanonicalIDs() {
        return null;
    }

    /**
     * Returns a map for  language aliases which specifies mapping from source language
     * to from which it should be replaced.
     * @return map of source language to replacement language, separated by a space.
     */
   default Map<String, String> getLanguageAliasMap(){
       return null;
   }
}
