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

package java.naming.share.classes.javax.naming.ldap;

















/**
 * This class represents an event fired in response to an unsolicited
 * notification sent by the LDAP server.
 *
 *
 * @see UnsolicitedNotification
 * @see UnsolicitedNotificationListener
 * @see javax.naming.event.EventContext#addNamingListener
 * @see javax.naming.event.EventDirContext#addNamingListener
 * @see javax.naming.event.EventContext#removeNamingListener
 */

public class UnsolicitedNotificationEvent extends java.util.EventObject {
    /**
     * The notification that caused this event to be fired.
     * @serial
     */
    private final UnsolicitedNotification notice;

    /**
     * Constructs a new instance of {@code UnsolicitedNotificationEvent}.
     *
     * @param src The non-null source that fired the event.
     * @param notice The non-null unsolicited notification.
     */
    public UnsolicitedNotificationEvent(Object src,
        UnsolicitedNotification notice) {
        super(src);
        this.notice = notice;
    }


    /**
     * Returns the unsolicited notification.
     * @return The non-null unsolicited notification that caused this
     * event to be fired.
     */
    public UnsolicitedNotification getNotification() {
        return notice;
    }

    /**
     * Invokes the {@code notificationReceived()} method on
     * a listener using this event.
     * @param listener The non-null listener on which to invoke
     *        {@code notificationReceived}.
     */
    public void dispatch(UnsolicitedNotificationListener listener) {
        listener.notificationReceived(this);
    }

    private static final long serialVersionUID = -2382603380799883705L;
}
