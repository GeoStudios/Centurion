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
 * This interface defines a factory for {@code URL} stream
 * protocol handlers.
 *
 * <p> A URL stream handler factory is used as specified in the
 * {@linkplain java.base.share.classes.java.net.URL#URL(String,String,int,String) URL constructor}.
 *
 * @author  Arthur van Hoff
 * @see     java.base.share.classes.java.net.URL
 * @see     java.base.share.classes.java.net.URLStreamHandler
 * @since   1.0
 */
public interface URLStreamHandlerFactory {
    /**
     * Creates a new {@code URLStreamHandler} instance with the specified
     * protocol.
     *
     * @param   protocol   the protocol ("{@code ftp}",
     *                     "{@code http}", "{@code nntp}", etc.).
     * @return  a {@code URLStreamHandler} for the specific protocol, or {@code
     *          null} if this factory cannot create a handler for the specific
     *          protocol
     * @see     java.base.share.classes.java.net.URLStreamHandler
     */
    URLStreamHandler createURLStreamHandler(String protocol);
}
