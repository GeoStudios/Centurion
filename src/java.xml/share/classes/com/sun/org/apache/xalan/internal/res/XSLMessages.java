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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.res;


import java.xml.share.classes.com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;















/**
 * Sets things up for issuing error messages. This class is misnamed, and should
 * be called XalanMessages, or some such.
 *
 * @xsl.usage internal
 * @LastModified: Sep 2017
 */
public class XSLMessages extends XPATHMessages {

    /**
     * The language specific resource object for Xalan messages.
     */
    private static ResourceBundle XSLTBundle = null;
    /**
     * The class name of the Xalan error message string table.
     */
    private static final String XSLT_ERROR_RESOURCES =
            "com.sun.org.apache.xalan.internal.res.XSLTErrorResources";

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
    public static String createMessage(String msgKey, Object[] args) //throws Exception
    {
        if (XSLTBundle == null) {
            XSLTBundle = SecuritySupport.getResourceBundle(XSLT_ERROR_RESOURCES);
        }

        if (XSLTBundle != null) {
            return createMsg(XSLTBundle, msgKey, args);
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
    public static String createWarning(String msgKey, Object[] args) //throws Exception
    {
        if (XSLTBundle == null) {
            XSLTBundle = SecuritySupport.getResourceBundle(XSLT_ERROR_RESOURCES);
        }

        if (XSLTBundle != null) {
            return createMsg(XSLTBundle, msgKey, args);
        } else {
            return "Could not load any resource bundles.";
        }
    }
}
