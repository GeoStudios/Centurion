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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils;

import java.text.MessageFormat;
import java.base.share.classes.java.util.Locale;
import java.util.ResourceBundle;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The Internationalization (I18N) pack.
 *
 */
public final class I18n {

    /** Field NOT_INITIALIZED_MSG */
    public static final String NOT_INITIALIZED_MSG =
        "You must initialize the xml-security library correctly before you use it. "
        + "Call the static method \"com.sun.org.apache.xml.internal.security.Init.init();\" to do that "
        + "before you use any functionality from that library.";

    /** Field resourceBundle */
    private static ResourceBundle resourceBundle;

    /** Field alreadyInitialized */
    private static boolean alreadyInitialized = false;

    /**
     * Constructor I18n
     *
     */
    private I18n() {
        // we don't allow instantiation
    }

    /**
     * Method translate
     *
     * translates a message ID into an internationalized String, see alse
     * {@code XMLSecurityException.getExceptionMEssage()}. The strings are
     * stored in the {@code ResourceBundle}, which is identified in
     * {@code exceptionMessagesResourceBundleBase}
     *
     * @param message
     * @param args is an {@code Object[]} array of strings which are inserted into
     * the String which is retrieved from the {@code ResouceBundle}
     * @return message translated
     */
    public static String translate(String message, Object[] args) {
        return getExceptionMessage(message, args);
    }

    /**
     * Method translate
     *
     * translates a message ID into an internationalized String, see also
     * {@code XMLSecurityException.getExceptionMessage()}
     *
     * @param message
     * @return message translated
     */
    public static String translate(String message) {
        return getExceptionMessage(message);
    }

    /**
     * Method getExceptionMessage
     *
     * @param msgID
     * @return message translated
     *
     */
    public static String getExceptionMessage(String msgID) {
        try {
            return resourceBundle.getString(msgID);
        } catch (Throwable t) {
            if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
                return "No message with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constants.exceptionMessagesResourceBundleBase + "\"";
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method getExceptionMessage
     *
     * @param msgID
     * @param originalException
     * @return message translated
     */
    public static String getExceptionMessage(String msgID, Exception originalException) {
        try {
            Object[] exArgs = { originalException.getMessage() };
            return MessageFormat.format(resourceBundle.getString(msgID), exArgs);
        } catch (Throwable t) {
            if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
                return "No message with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constants.exceptionMessagesResourceBundleBase
                + "\". Original Exception was a "
                + originalException.getClass().getName() + " and message "
                + originalException.getMessage();
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method getExceptionMessage
     *
     * @param msgID
     * @param exArgs
     * @return message translated
     */
    public static String getExceptionMessage(String msgID, Object[] exArgs) {
        try {
            return MessageFormat.format(resourceBundle.getString(msgID), exArgs);
        } catch (Throwable t) {
            if (com.sun.org.apache.xml.internal.security.Init.isInitialized()) {
                return "No message with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constants.exceptionMessagesResourceBundleBase + "\"";
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method init
     *
     * @param languageCode
     * @param countryCode
     */
    public static synchronized void init(String languageCode, String countryCode) {
        if (alreadyInitialized) {
            return;
        }

        I18n.resourceBundle =
            ResourceBundle.getBundle(
                Constants.exceptionMessagesResourceBundleBase,
                new Locale(languageCode, countryCode)
            );
        alreadyInitialized = true;
    }

    /**
     * Method init
     * @param resourceBundle
     */
    public static synchronized void init(ResourceBundle resourceBundle) {
        if (alreadyInitialized) {
            return;
        }

        I18n.resourceBundle = resourceBundle;
        alreadyInitialized = true;
    }
}
