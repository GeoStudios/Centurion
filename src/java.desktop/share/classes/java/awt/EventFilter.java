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

package java.desktop.share.classes.java.awt;

















interface EventFilter {

    /**
     * Enumeration for possible values for {@code acceptEvent(AWTEvent ev)} method.
     * @see EventDispatchThread#pumpEventsForFilter
     */
    enum FilterAction {
        /**
         * ACCEPT means that this filter do not filter the event and allows other
         * active filters to proceed it. If all the active filters accept the event, it
         * is dispatched by the {@code EventDispatchThread}
         * @see EventDispatchThread#pumpEventsForFilter
         */
        ACCEPT,
        /**
         * REJECT means that this filter filter the event. No other filters are queried,
         * and the event is not dispatched by the {@code EventDispatchedThread}
         * @see EventDispatchThread#pumpEventsForFilter
         */
        REJECT,
        /**
         * ACCEPT_IMMEDIATELY means that this filter do not filter the event, no other
         * filters are queried and to proceed it, and it is dispatched by the
         * {@code EventDispatchThread}
         * It is not recommended to use ACCEPT_IMMEDIATELY as there may be some active
         * filters not queried yet that do not accept this event. It is primarily used
         * by modal filters.
         * @see EventDispatchThread#pumpEventsForFilter
         * @see ModalEventFilter
         */
        ACCEPT_IMMEDIATELY
    }

    FilterAction acceptEvent(AWTEvent ev);
}
