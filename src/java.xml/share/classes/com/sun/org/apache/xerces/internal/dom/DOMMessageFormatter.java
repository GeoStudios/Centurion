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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.base.share.classes.java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/**
 * Used to format DOM error messages, using the system locale.
 *
 * @xerces.internal
 *
 * @LastModified: Sep 2017
 */
public class DOMMessageFormatter {
    public static final String DOM_DOMAIN = "http://www.w3.org/dom/DOMTR";
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    public static final String SERIALIZER_DOMAIN = "http://apache.org/xml/serializer";

    private static ResourceBundle domResourceBundle = null;
    private static ResourceBundle xmlResourceBundle = null;
    private static ResourceBundle serResourceBundle = null;
    private static Locale locale = null;

    DOMMessageFormatter(){
        locale = Locale.getDefault();
    }
    /**
     * Formats a message with the specified arguments using the given
     * locale information.
     *
     * @param domain    domain from which error string is to come.
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
    public static String formatMessage(String domain,
    String key, Object[] arguments)
    throws MissingResourceException {
        ResourceBundle resourceBundle = getResourceBundle(domain);
        if(resourceBundle == null){
            init();
            resourceBundle = getResourceBundle(domain);
            if(resourceBundle == null)
                throw new MissingResourceException("Unknown domain" + domain, null, key);
        }
        // format message
        String msg;
        try {
            msg = key + ": " + resourceBundle.getString(key);
            if (arguments != null) {
                try {
                    msg = java.text.MessageFormat.format(msg, arguments);
                }
                catch (Exception e) {
                    msg = resourceBundle.getString("FormatFailed");
                    msg += " " + resourceBundle.getString(key);
                }
            }
        } // error
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

    static ResourceBundle getResourceBundle(String domain){
        if(domain == DOM_DOMAIN || domain.equals(DOM_DOMAIN))
            return domResourceBundle;
        else if( domain == XML_DOMAIN || domain.equals(XML_DOMAIN))
            return xmlResourceBundle;
        else if(domain == SERIALIZER_DOMAIN || domain.equals(SERIALIZER_DOMAIN))
            return serResourceBundle;
        return null;
    }
    /**
     * Initialize Message Formatter.
     */
    public static void init(){
        if (locale != null) {
            domResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.DOMMessages", locale);
            serResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLSerializerMessages", locale);
            xmlResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLMessages", locale);
        }else{
            domResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.DOMMessages");
            serResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLSerializerMessages");
            xmlResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLMessages");
        }
    }

    /**
     * setLocale to be used by the formatter.
     * @param locale
     */
    public static void setLocale(Locale dlocale){
        locale = dlocale;
    }
}
