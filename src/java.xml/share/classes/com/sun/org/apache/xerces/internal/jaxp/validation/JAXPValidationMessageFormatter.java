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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;


import java.base.share.classes.java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;















/**
 * <p>Used to format JAXP Validation API error messages using a specified locale.</p>
 *
 * @LastModified: Sep 2017
 */
final class JAXPValidationMessageFormatter {

    /**
     * Formats a message with the specified arguments using the given
     * locale information.
     *
     * @param locale    The locale of the message.
     * @param key       The message key.
     * @param arguments The message replacement text arguments. The order
     *                  of the arguments must match that of the placeholders
     *                  in the actual message.
     *
     * @return          the formatted message.
     *
     * @throws MissingResourceException Thrown if the message with the
     *                                  specified key cannot be found.
     */
    public static String formatMessage(Locale locale,
        String key, Object[] arguments)
        throws MissingResourceException {

        ResourceBundle resourceBundle = null;
        if (locale != null) {
            resourceBundle =
                SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages", locale);
        }
        else {
            resourceBundle =
                SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages");
        }

        // format message
        String msg;
        try {
            msg = resourceBundle.getString(key);
            if (arguments != null) {
                try {
                    msg = java.text.MessageFormat.format(msg, arguments);
                }
                catch (Exception e) {
                    msg = resourceBundle.getString("FormatFailed");
                    msg += " " + resourceBundle.getString(key);
                }
            }
        }

        // error
        catch (MissingResourceException e) {
            msg = resourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(key, msg, key);
        }

        // no message
        if (msg == null) {
            msg = key;
            if (arguments.length > 0) {
                StringBuffer str = new StringBuffer(msg);
                str.append('?');
                for (int i = 0; i < arguments.length; i++) {
                    if (i > 0) {
                        str.append('&');
                    }
                    str.append(arguments[i]);
                }
            }
        }
        return msg;
    }
}
