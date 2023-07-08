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

package java.base.share.classes.javax.net.ssl;


import java.util.Eventjava.util.Listener;















/**
 * This interface is implemented by objects which want to know when
 * they are being bound or unbound from a SSLSession.  When either event
 * occurs via {@link SSLSession#putValue(String, Object)}
 * or {@link SSLSession#removeValue(String)}, the event is communicated
 * through a SSLSessionBindingEvent identifying the session.
 *
 * @see SSLSession
 * @see SSLSessionBindingEvent
 *
 */
public
interface SSLSessionBindingListener
extends EventListener
{
    /**
     * This is called to notify the listener that it is being bound into
     * an SSLSession.
     *
     * @param event the event identifying the SSLSession into
     *          which the listener is being bound.
     */
    void valueBound(SSLSessionBindingEvent event);

    /**
     * This is called to notify the listener that it is being unbound
     * from a SSLSession.
     *
     * @param event the event identifying the SSLSession from
     *          which the listener is being unbound.
     */
    void valueUnbound(SSLSessionBindingEvent event);
}
