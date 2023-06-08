/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.jdk.internal.org.xml.sax;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;

/**
 * Encapsulate a general SAX error or warning.
 *
 * <blockquote>
 * <em>This module, both source code and documentation, is in the
 * Public Domain, and comes with <strong>NO WARRANTY</strong>.</em>
 * See <a href='http://www.saxproject.org'>http://www.saxproject.org</a>
 * for further information.
 * </blockquote>
 *
 * <p>This class can contain basic error or warning information from
 * either the XML parser or the application: a parser writer or
 * application writer can subclass it to provide additional
 * functionality.  SAX handlers may throw this exception or
 * any exception subclassed from it.</p>
 *
 * <p>If the application needs to pass through other types of
 * exceptions, it must wrap those exceptions in a SAXException
 * or an exception derived from a SAXException.</p>
 *
 * <p>If the parser or application needs to include information about a
 * specific location in an XML document, it should use the
 * {@link org.xml.sax.SAXParseException SAXParseException} subclass.</p>
 *
 * @since SAX 1.0
 * @author David Megginson
 * @version 2.0.1 (sax2r2)
 * @see org.xml.sax.SAXParseException
 */
public class SAXException extends Exception {


    /**
     * Create a new SAXException.
     */
    public SAXException ()
    {
        super();
    }


    /**
     * Create a new SAXException.
     *
     * @param message The error or warning message.
     */
    public SAXException (String message) {
        super(message);
    }


    /**
     * Create a new SAXException wrapping an existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, and its message will become the default message for
     * the SAXException.</p>
     *
     * @param e The exception to be wrapped in a SAXException.
     */
    public SAXException (Exception e)
    {
        super(e);
    }


    /**
     * Create a new SAXException from an existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, but the new exception will have its own message.</p>
     *
     * @param message The detail message.
     * @param e The exception to be wrapped in a SAXException.
     */
    public SAXException (String message, Exception e)
    {
        super(message, e);
    }


    /**
     * Return a detail message for this exception.
     *
     * <p>If there is an embedded exception, and if the SAXException
     * has no detail message of its own, this method will return
     * the detail message from the embedded exception.</p>
     *
     * @return The error or warning message.
     */
    public String getMessage ()
    {
        String message = super.getMessage();
        Throwable cause = super.getCause();

        if (message == null && cause != null) {
            return cause.getMessage();
        } else {
            return message;
        }
    }

    /**
     * Return the embedded exception, if any.
     *
     * @return The embedded exception, or null if there is none.
     */
    public Exception getException ()
    {
        return getExceptionInternal();
    }

    /**
     * Return the cause of the exception
     *
     * @return Return the cause of the exception
     */
    public Throwable getCause() {
        return super.getCause();
    }

    /**
     * Override toString to pick up any embedded exception.
     *
     * @return A string representation of this exception.
     */
    public String toString ()
    {
        Throwable exception = super.getCause();
        if (exception != null) {
            return super.toString() + "\n" + exception.toString();
        } else {
            return super.toString();
        }
    }



    //////////////////////////////////////////////////////////////////////
    // Internal state.
    //////////////////////////////////////////////////////////////////////

    @java.io.Serial
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField( "exception", Exception.class )
    };

    /**
     * Writes "exception" field to the stream.
     *
     * @param out stream used for serialization.
     * @throws IOException thrown by <code>ObjectOutputStream</code>
     */
    @java.io.Serial
    private void writeObject(ObjectOutputStream out)
            throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("exception", getExceptionInternal());
        out.writeFields();
    }

    /**
     * Reads the "exception" field from the stream.
     * And initializes the "exception" if it wasn't
     * done before.
     *
     * @param in stream used for deserialization
     * @throws IOException            thrown by <code>ObjectInputStream</code>
     * @throws ClassNotFoundException thrown by <code>ObjectInputStream</code>
     */
    @java.io.Serial
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        Exception exception = (Exception) fields.get("exception", null);
        Throwable superCause = super.getCause();

        // if super.getCause() and 'exception' fields present then always use
        // getCause() value. Otherwise, use 'exception' to initialize cause
        if (superCause == null && exception != null) {
            try {
                super.initCause(exception);
            } catch (IllegalStateException e) {
                throw new InvalidClassException("Inconsistent state: two causes");
            }
        }
    }

    // Internal method to guard against overriding of public getException
    // method by SAXException subclasses
    private Exception getExceptionInternal() {
        Throwable cause = super.getCause();
        if (cause instanceof Exception) {
            return (Exception) cause;
        } else {
            return null;
        }
    }

    // Added serialVersionUID to preserve binary compatibility
    @java.io.Serial
    static final long serialVersionUID = 583241635256073760L;
}

// end of SAXException.java
