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

import java.util.EventObject;


/**
 * This event is propagated to a SSLSessionBindingListener.
 * When a listener object is bound or unbound to an SSLSession by
 * {@link SSLSession#putValue(String, Object)}
 * or {@link SSLSession#removeValue(String)}, objects which
 * implement the SSLSessionBindingListener will receive an
 * event of this type.  The event's <code>name</code> field is the
 * key in which the listener is being bound or unbound.
 *
 * @see SSLSession
 * @see SSLSessionBindingListener
 *
 * @since 1.4
 * @author Nathan Abramson
 * @author David Brownell
 */
public
class SSLSessionBindingEvent
extends EventObject
{
    @java.io.Serial
    private static final long serialVersionUID = 3989172637106345L;

    /**
     * @serial The name to which the object is being bound or unbound
     */
    private final String name;

    /**
     * Constructs a new SSLSessionBindingEvent.
     *
     * @param session the SSLSession acting as the source of the event
     * @param name the name to which the object is being bound or unbound
     * @exception  IllegalArgumentException  if <code>session</code> is null.
     */
    public SSLSessionBindingEvent(SSLSession session, String name)
    {
        super(session);
        this.name = name;
    }

    /**
     * Returns the name to which the object is being bound, or the name
     * from which the object is being unbound.
     *
     * @return the name to which the object is being bound or unbound
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the SSLSession into which the listener is being bound or
     * from which the listener is being unbound.
     *
     * @return the <code>SSLSession</code>
     */
    public SSLSession getSession()
    {
        return (SSLSession) getSource();
    }
}
