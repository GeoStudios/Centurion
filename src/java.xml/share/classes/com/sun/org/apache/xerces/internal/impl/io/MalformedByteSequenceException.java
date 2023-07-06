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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.io;

import java.io.CharConversionException;
import java.base.share.classes.java.util.Locale;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.MessageFormatter;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>Signals that a malformed byte sequence was detected
 * by a <code>java.io.Reader</code> that decodes bytes
 * of a given encoding into characters.</p>
 *
 * @xerces.internal
 *
 *
 */
public class MalformedByteSequenceException extends CharConversionException {

    /** Serialization version. */
    static final long serialVersionUID = 8436382245048328739L;

    //
    // Data
    //

    /** message formatter **/
    private MessageFormatter fFormatter;

    /** locale for error message **/
    private Locale fLocale;

    /** error domain **/
    private final String fDomain;

    /** key for the error message **/
    private final String fKey;

    /** replacement arguements for the error message **/
    private final Object[] fArguments;

    /** message text for this message, initially null **/
    private String fMessage;

    //
    // Constructors
    //

    /**
     * Constructs a MalformedByteSequenceException with the given
     * parameters which may be passed to an error reporter to
     * generate a localized string for this exception.
     *
     * @param formatter The MessageFormatter used for building the
     *                  message text for this exception.
     * @param locale    The Locale for which messages are to be reported.
     * @param domain    The error domain.
     * @param key       The key of the error message.
     * @param arguments The replacement arguments for the error message,
     *                  if needed.
     */
    public MalformedByteSequenceException(MessageFormatter formatter,
        Locale locale, String domain, String key, Object[] arguments) {
        fFormatter = formatter;
        fLocale = locale;
        fDomain = domain;
        fKey = key;
        fArguments = arguments;
    } // <init>(MessageFormatter, Locale, String, String, Object[])

    //
    // Public methods
    //

    /**
     * <p>Returns the error domain of the error message.</p>
     *
     * @return the error domain
     */
    public String getDomain () {
        return fDomain;
    } // getDomain

    /**
     * <p>Returns the key of the error message.</p>
     *
     * @return the error key of the error message
     */
    public String getKey () {
        return fKey;
    } // getKey()

    /**
     * <p>Returns the replacement arguments for the error
     * message or <code>null</code> if none exist.</p>
     *
     * @return the replacement arguments for the error message
     * or <code>null</code> if none exist
     */
    public Object[] getArguments () {
        return fArguments;
    } // getArguments();

    /**
     * <p>Returns the localized message for this exception.</p>
     *
     * @return the localized message for this exception.
     */
    public String getMessage() {
        if (fMessage == null) {
            fMessage = fFormatter.formatMessage(fLocale, fKey, fArguments);
            // The references to the message formatter and locale
            // aren't needed anymore so null them.
            fFormatter = null;
            fLocale = null;
        }
        return fMessage;
     } // getMessage()

} // MalformedByteSequenceException