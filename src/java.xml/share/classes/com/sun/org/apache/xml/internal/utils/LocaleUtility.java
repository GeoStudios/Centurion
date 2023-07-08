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

package java.xml.share.classes.com.sun.org.apache.xml.internal.utils;


import java.base.share.classes.java.util.Locale;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public class LocaleUtility {
    /**
     * IETF RFC 1766 tag separator
     */
    public final static char IETF_SEPARATOR = '-';
    public final static String EMPTY_STRING = "";


 public static Locale langToLocale(String lang) {
       if((lang == null) || lang.equals(EMPTY_STRING)){ // not specified => getDefault
            return Locale.getDefault();
       }
        String language = EMPTY_STRING;
        String country =  EMPTY_STRING;
        String variant =  EMPTY_STRING;

        int i1 = lang.indexOf(IETF_SEPARATOR);
        if (i1 < 0) {
            language = lang;
        } else {
            language = lang.substring(0, i1);
            ++i1;
            int i2 = lang.indexOf(IETF_SEPARATOR, i1);
            if (i2 < 0) {
                country = lang.substring(i1);
            } else {
                country = lang.substring(i1, i2);
                variant = lang.substring(i2+1);
            }
        }

        if(language.length() == 2){
           language = language.toLowerCase();
        }else {
          language = EMPTY_STRING;
        }

        if(country.length() == 2){
           country = country.toUpperCase();
        }else {
          country = EMPTY_STRING;
        }

        if((variant.length() > 0) &&
        ((language.length() == 2) ||(country.length() == 2))){
           variant = variant.toUpperCase();
        }else{
            variant = EMPTY_STRING;
        }

        return new Locale(language, country, variant );
    }



 }
