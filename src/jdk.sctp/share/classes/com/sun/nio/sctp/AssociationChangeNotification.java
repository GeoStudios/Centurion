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
package com.sun.nio.sctp;

/**
 * Notification emitted when an association has either opened or closed.
 *
 */
public abstract class AssociationChangeNotification
    implements Notification
{
    /**
     * Defines the type of change event that happened to the association.
     *
     */
    public enum AssocChangeEvent
    {
        /**
         * A new association is now ready and data may be exchanged with this peer.
         */
        COMM_UP,

        /**
         * The association has failed. A series of SCTP send failed notifications
         * will follow this notification, one for each outstanding message.
         */
       COMM_LOST,

        /**
         * SCTP has detected that the peer has restarted.
         */
       RESTART,

        /**
         * The association has gracefully closed.
         */
       SHUTDOWN,

        /**
         * The association failed to setup. If a message was sent on a {@link
         * SctpMultiChannel} in non-blocking mode, an
         * SCTP send failed notification will follow this notification for the
         * outstanding message.
         */
       CANT_START
    }

    /**
     * Initializes a new instance of this class.
     */
    protected AssociationChangeNotification() {}

    /**
     * Returns the association that this notification is applicable to.
     *
     * @return  The association whose state has changed, or {@code null} if
     *          there is no association, that is {@linkplain
     *          AssocChangeEvent#CANT_START CANT_START}
     */
    public abstract Association association();

    /**
     * Returns the type of change event.
     *
     * @return  The event
     */
    public abstract AssocChangeEvent event();
}
