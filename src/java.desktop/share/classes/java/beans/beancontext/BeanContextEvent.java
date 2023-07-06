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

package java.desktop.share.classes.java.beans.beancontext;

import java.desktop.share.classes.java.io.Serial;
import java.desktop.share.classes.java.util.EventObject;

/**
 * <p>
 * {@code BeanContextEvent} is the abstract root event class
 * for all events emitted
 * from, and pertaining to the semantics of, a {@code BeanContext}.
 * This class introduces a mechanism to allow the propagation of
 * {@code BeanContextEvent} subclasses through a hierarchy of
 * {@code BeanContext}s. The {@code setPropagatedFrom()}
 * and {@code getPropagatedFrom()} methods allow a
 * {@code BeanContext} to identify itself as the source
 * of a propagated event.
 * </p>
 *
 * @see         java.beans.beancontext.BeanContext
 */

public abstract class BeanContextEvent extends EventObject {

    /**
     * Use serialVersionUID from JDK 1.7 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 7267998073569045052L;

    /**
     * Contruct a BeanContextEvent
     *
     * @param bc        The BeanContext source
     */
    protected BeanContextEvent(BeanContext bc) {
        super(bc);
    }

    /**
     * Gets the {@code BeanContext} associated with this event.
     * @return the {@code BeanContext} associated with this event.
     */
    public BeanContext getBeanContext() { return (BeanContext)getSource(); }

    /**
     * Sets the {@code BeanContext} from which this event was propagated.
     * @param bc the {@code BeanContext} from which this event
     * was propagated
     */
    public synchronized void setPropagatedFrom(BeanContext bc) {
        propagatedFrom = bc;
    }

    /**
     * Gets the {@code BeanContext} from which this event was propagated.
     * @return the {@code BeanContext} from which this
     * event was propagated
     */
    public synchronized BeanContext getPropagatedFrom() {
        return propagatedFrom;
    }

    /**
     * Reports whether or not this event is
     * propagated from some other {@code BeanContext}.
     * @return {@code true} if propagated, {@code false}
     * if not
     */
    public synchronized boolean isPropagated() {
        return propagatedFrom != null;
    }

    /*
     * fields
     */

    /**
     * The {@code BeanContext} from which this event was propagated
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    protected BeanContext propagatedFrom;
}
