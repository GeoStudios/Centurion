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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;

import java.util.Properties;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class contains some static methods that act as helpers when parsing a
 * Java Property object.
 *
 * This class is not a public API.
 * It is only public because it is used outside of this package.
 *
 * @see java.util.Properties
 * @xsl.usage internal
 */
public final class OutputPropertyUtils
{
    /**
      * Searches for the boolean property with the specified key in the property list.
      * If the key is not found in this property list, the default property list,
      * and its defaults, recursively, are then checked. The method returns
      * <code>false</code> if the property is not found, or if the value is other
      * than "yes".
      *
      * @param   key   the property key.
      * @param   props   the list of properties that will be searched.
      * @return  the value in this property list as a boolean value, or false
      * if null or not "yes".
      */
    public static boolean getBooleanProperty(String key, Properties props)
    {

        String s = props.getProperty(key);

        return null != s && s.equals("yes");
    }

    /**
     * Searches for the int property with the specified key in the property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>false</code> if the property is not found, or if the value is other
     * than "yes".
     *
     * @param   key   the property key.
     * @param   props   the list of properties that will be searched.
     * @return  the value in this property list as a int value, or 0
     * if null or not a number.
     */
    public static int getIntProperty(String key, Properties props)
    {

        String s = props.getProperty(key);

        if (null == s)
            return 0;
        else
            return Integer.parseInt(s);
    }

}
