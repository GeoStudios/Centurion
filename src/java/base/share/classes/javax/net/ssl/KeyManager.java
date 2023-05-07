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

/**
 * This is the base interface for JSSE key managers.
 * <P>
 * <code>KeyManager</code>s are responsible for managing the
 * key material which is used to authenticate the local SSLSocket
 * to its peer.  If no key material is available, the socket will
 * be unable to present authentication credentials.
 * <P>
 * <code>KeyManager</code>s are created by either
 * using a <code>KeyManagerFactory</code>,
 * or by implementing one of the <code>KeyManager</code> subclasses.
 *
 * @since 1.4
 * @see KeyManagerFactory
 */
public interface KeyManager {
}
