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

package org.netbeans.jemmy;
















/**
 *
 * Defines an action to be executed by {@code ActionProducer} instance.
 *
 * @see org.netbeans.jemmy.ActionProducer
 *
 * @author Alexandre Iline (alexandre.iline@oracle.com)
 */
public interface Action<R, P> {

    /**
     * Executes this action.
     *
     * @param obj action argument. This argument might be the method parameter
     * in an invocation of {@code ActionProducer.produceAction(Object)}.
     * @return action result.
     */
    public R launch(P obj);

    /**
     * Returns the description value.
     *
     * @return Action description.
     */
    public String getDescription();
}