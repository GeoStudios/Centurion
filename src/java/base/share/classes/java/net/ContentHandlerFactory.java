/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * This interface defines a factory for content handlers. An
 * implementation of this interface should map a MIME type into an
 * instance of {@code ContentHandler}.
 * <p>
 * This interface is used by the {@code URLStreamHandler} class
 * to create a {@code ContentHandler} for a MIME type.
 *
 * @see     java.base.share.classes.java.net.ContentHandler
 * @see     java.base.share.classes.java.net.URLStreamHandler
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface ContentHandlerFactory {

    /**
     * Creates a new {@code ContentHandler} to read an object from
     * a {@code URLStreamHandler}.
     *
     * @param   mimetype   the MIME type for which a content handler is desired.
     *
     * @return  a new {@code ContentHandler} to read an object from a
     *          {@code URLStreamHandler}.
     * @see     java.base.share.classes.java.net.ContentHandler
     * @see     java.base.share.classes.java.net.URLStreamHandler
     */
    ContentHandler createContentHandler(String mimetype);
}
