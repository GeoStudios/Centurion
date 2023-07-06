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

package java.desktop.share.classes.javax.print.event;

/**
 * Implementations of this listener interface are attached to a
 * {@link javax.print.PrintService PrintService} to monitor the status of the
 * print service.
 * <p>
 * To monitor a particular job see {@link PrintJobListener} and
 * {@link PrintJobAttributeListener}.
 */
public interface PrintServiceAttributeListener {

    /**
     * Called to notify a listener of an event in the print service. The service
     * will call this method on an event notification thread. The client should
     * not perform lengthy processing in this callback or subsequent event
     * notifications may be blocked.
     *
     * @param  psae the event being notified
     */
    void attributeUpdate(PrintServiceAttributeEvent psae) ;
}