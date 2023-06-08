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

package java.base.share.classes.javax.net.ssl;

import java.util.EventListener;

/**
 * This interface is implemented by any class which wants to receive
 * notifications about the completion of an SSL protocol handshake
 * on a given SSL connection.
 *
 * <P> When an SSL handshake completes, new security parameters will
 * have been established.  Those parameters always include the security
 * keys used to protect messages.  They may also include parameters
 * associated with a new <em>session</em> such as authenticated
 * peer identity and a new SSL cipher suite.
 *
 * @since 1.4
 * @author David Brownell
 */
public interface HandshakeCompletedListener extends EventListener
{
    /**
     * This method is invoked on registered objects
     * when a SSL handshake is completed.
     *
     * @param event the event identifying when the SSL Handshake
     *          completed on a given SSL connection
     */
    void handshakeCompleted(HandshakeCompletedEvent event);
}
