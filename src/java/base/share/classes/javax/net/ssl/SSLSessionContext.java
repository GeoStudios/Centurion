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

import java.util.Enumeration;


/**
 * A <code>SSLSessionContext</code> represents a set of
 * <code>SSLSession</code>s associated with a single entity. For example,
 * it could be associated with a server or client who participates in many
 * sessions concurrently.
 * <p>
 * Not all environments will contain session contexts.  For example, stateless
 * session resumption.
 * <p>
 * Session contexts may not contain all sessions. For example, stateless
 * sessions are not stored in the session context.
 * <p>
 * There are <code>SSLSessionContext</code> parameters that affect how
 * sessions are stored:
 * <UL>
 *      <LI>Sessions can be set to expire after a specified
 *      time limit.
 *      <LI>The number of sessions that can be stored in context
 *      can be limited.
 * </UL>
 * A session can be retrieved based on its session id, and all session id's
 * in a <code>SSLSessionContext</code> can be listed.
 *
 * @see SSLSession
 *
 * @since 1.4
 * @author Nathan Abramson
 * @author David Brownell
 */
public interface SSLSessionContext {

    /**
     * Returns the <code>SSLSession</code> bound to the specified session id.
     *
     * @param sessionId the Session identifier
     * @return the <code>SSLSession</code> or null if
     * the specified session id does not refer to a valid SSLSession.
     *
     * @throws NullPointerException if <code>sessionId</code> is null.
     */
    SSLSession getSession(byte[] sessionId);

    /**
     * Returns an Enumeration of all known session id's grouped under this
     * <code>SSLSessionContext</code>.
     * <p>Session contexts may not contain all sessions. For example,
     * stateless sessions are not stored in the session context.
     *
     * @return an enumeration of all the Session id's
     */
    Enumeration<byte[]> getIds();

    /**
     * Sets the timeout limit for <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, a session exceeds the
     * timeout limit 't' seconds after its creation time.
     * When the timeout limit is exceeded for a session, the
     * <code>SSLSession</code> object is invalidated and future connections
     * cannot resume or rejoin the session.
     * A check for sessions exceeding the timeout is made immediately whenever
     * the timeout limit is changed for this <code>SSLSessionContext</code>.
     *
     * @apiNote Note that the JDK Implementation uses default values for both
     *          the session cache size and timeout.  See
     *          {@code getSessionCacheSize} and {@code getSessionTimeout} for
     *          more information.  Applications should consider their
     *          performance requirements and override the defaults if necessary.
     *
     * @param seconds the new session timeout limit in seconds; zero means
     *        there is no limit.
     *
     * @throws IllegalArgumentException if the timeout specified is {@code < 0}.
     *
     * @see #getSessionTimeout
     */
    void setSessionTimeout(int seconds);

    /**
     * Returns the timeout limit of <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, a session exceeds the
     * timeout limit 't' seconds after its creation time.
     * When the timeout limit is exceeded for a session, the
     * <code>SSLSession</code> object is invalidated and future connections
     * cannot resume or rejoin the session.
     * A check for sessions exceeding the timeout limit is made immediately
     * whenever the timeout limit is changed for this
     * <code>SSLSessionContext</code>.
     *
     * @implNote The JDK implementation returns the session timeout as set by
     *           the {@code setSessionTimeout} method, or if not set, a default
     *           value of 86400 seconds (24 hours).
     *
     * @return the session timeout limit in seconds; zero means there is no
     *         limit.
     *
     * @see #setSessionTimeout
     */
    int getSessionTimeout();

    /**
     * Sets the size of the cache used for storing <code>SSLSession</code>
     * objects grouped under this <code>SSLSessionContext</code>.
     *
     * @apiNote Note that the JDK Implementation uses default values for both
     *          the session cache size and timeout.  See
     *          {@code getSessionCacheSize} and {@code getSessionTimeout} for
     *          more information.  Applications should consider their
     *          performance requirements and override the defaults if necessary.
     *
     * @param size the new session cache size limit; zero means there is no
     *        limit.
     *
     * @throws IllegalArgumentException if the specified size is {@code < 0}.
     *
     * @see #getSessionCacheSize
     */
    void setSessionCacheSize(int size);

    /**
     * Returns the size of the cache used for storing <code>SSLSession</code>
     * objects grouped under this <code>SSLSessionContext</code>.
     *
     * @implNote The JDK implementation returns the cache size as set by
     *           the {@code setSessionCacheSize} method, or if not set, the
     *           value of the {@systemProperty java.base.share.classes.javax.net.ssl.sessionCacheSize}
     *           system property.  If neither is set, it returns a default
     *           value of 20480.
     *
     * @return size of the session cache; zero means there is no size limit.
     *
     * @see #setSessionCacheSize
     */
    int getSessionCacheSize();
}
