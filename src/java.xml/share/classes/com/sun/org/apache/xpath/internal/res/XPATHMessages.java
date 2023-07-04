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

package com.sun.org.apache.xpath.internal.res;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/**
 * A utility class for issuing XPath error messages.
 *
 * @xsl.usage internal
 */
public class XPATHMessages extends XMLMessages {

    /**
     * The language specific resource object for XPath messages.
     */
    private static ResourceBundle XPATHBundle = null;
    /**
     * The class name of the XPath error message string table.
     */
    private static final String XPATH_ERROR_RESOURCES =
            "com.sun.org.apache.xpath.internal.res.XPATHErrorResources";

    /**
     * Creates a message from the specified key and replacement arguments,
     * localized to the given locale.
     *
     * @param msgKey The key for the message text.
     * @param args The arguments to be used as replacement text in the message
     * created.
     *
     * @return The formatted message string.
     */
    public static final String createXPATHMessage(String msgKey, Object[] args) //throws Exception
    {
        if (XPATHBundle == null) {
            XPATHBundle = SecuritySupport.getResourceBundle(XPATH_ERROR_RESOURCES);
        }

        if (XPATHBundle != null) {
            return createXPATHMsg(XPATHBundle, msgKey, args);
        } else {
            return "Could not load any resource bundles.";
        }
    }

    /**
     * Creates a message from the specified key and replacement arguments,
     * localized to the given locale.
     *
     * @param msgKey The key for the message text.
     * @param args The arguments to be used as replacement text in the message
     * created.
     *
     * @return The formatted warning string.
     */
    public static final String createXPATHWarning(String msgKey, Object[] args) //throws Exception
    {
        if (XPATHBundle == null) {
            XPATHBundle = SecuritySupport.getResourceBundle(XPATH_ERROR_RESOURCES);
        }

        if (XPATHBundle != null) {
            return createXPATHMsg(XPATHBundle, msgKey, args);
        } else {
            return "Could not load any resource bundles.";
        }
    }

    /**
     * Creates a message from the specified key and replacement arguments,
     * localized to the given locale.
     *
     * @param fResourceBundle The resource bundle to use.
     * @param msgKey The message key to use.
     * @param args The arguments to be used as replacement text in the message
     * created.
     *
     * @return The formatted message string.
     */
    private static final String createXPATHMsg(ResourceBundle fResourceBundle,
                                               String msgKey, Object[] args) //throws Exception
    {

        String fmsg = null;
        boolean throwex = false;
        String msg = null;

        if (msgKey != null) {
            msg = fResourceBundle.getString(msgKey);
        }

        if (msg == null) {
            msg = fResourceBundle.getString(XPATHErrorResources.BAD_CODE);
            throwex = true;
        }

        if (args != null) {
            try {

                // Do this to keep format from crying.
                // This is better than making a bunch of conditional
                // code all over the place.
                int n = args.length;

                for (int i = 0; i < n; i++) {
                    if (null == args[i]) {
                        args[i] = "";
                    }
                }

                fmsg = java.text.MessageFormat.format(msg, args);
            } catch (Exception e) {
                fmsg = fResourceBundle.getString(XPATHErrorResources.FORMAT_FAILED);
                fmsg += " " + msg;
            }
        } else {
            fmsg = msg;
        }

        if (throwex) {
            throw new RuntimeException(fmsg);
        }

        return fmsg;
    }
}
