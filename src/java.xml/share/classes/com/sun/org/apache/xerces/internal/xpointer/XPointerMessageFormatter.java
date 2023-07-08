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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xpointer;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.MessageFormatter;
import java.base.share.classes.java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/**
 * XPointerMessageFormatter provides error messages for the XPointer Framework
 * and element() Scheme Recommendations.
 *
 * @xerces.internal
 *
 * @LastModified: Sep 2017
 */
final class XPointerMessageFormatter implements MessageFormatter {

    public static final String XPOINTER_DOMAIN = "http://www.w3.org/TR/XPTR";

    // private objects to cache the locale and resource bundle
    private Locale fLocale = null;

    private ResourceBundle fResourceBundle = null;

    /**
     * Formats a message with the specified arguments using the given locale
     * information.
     *
     * @param locale
     *            The locale of the message.
     * @param key
     *            The message key.
     * @param arguments
     *            The message replacement text arguments. The order of the
     *            arguments must match that of the placeholders in the actual
     *            message.
     *
     * @return Returns the formatted message.
     *
     * @throws MissingResourceException
     *             Thrown if the message with the specified key cannot be found.
     */
    public String formatMessage(Locale locale, String key, Object[] arguments)
            throws MissingResourceException {

        if (fResourceBundle == null || locale != fLocale) {
            if (locale != null) {
                fResourceBundle = SecuritySupport.getResourceBundle(
                        "com.sun.org.apache.xerces.internal.impl.msg.XPointerMessages", locale);
                // memorize the most-recent locale
                fLocale = locale;
            }
            if (fResourceBundle == null)
                fResourceBundle = SecuritySupport.getResourceBundle(
                        "com.sun.org.apache.xerces.internal.impl.msg.XPointerMessages");
        }

        String msg = fResourceBundle.getString(key);
        if (arguments != null) {
            try {
                msg = java.text.MessageFormat.format(msg, arguments);
            } catch (Exception e) {
                msg = fResourceBundle.getString("FormatFailed");
                msg += " " + fResourceBundle.getString(key);
            }
        }

        if (msg == null) {
            msg = fResourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg,
                    "com.sun.org.apache.xerces.internal.impl.msg.XPointerMessages", key);
        }

        return msg;
    }
}
