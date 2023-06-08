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
 * @since Alpha cdk-1.1
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
