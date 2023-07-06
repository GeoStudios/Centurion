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

package jdk.jdi.share.classes.com.sun.jdi.event;

import jdk.jdi.share.classes.com.sun.jdi.request.StepRequest;

/**
 * Notification of step completion in the target VM.
 * The step event is generated immediately before the code at its location
 * is executed. Thus, if the step is entering a new method (as might occur
 * with {@link StepRequest#STEP_INTO StepRequest.STEP_INTO})
 * the location of the event is the first instruction of the method.
 * When a step leaves a method, the location of the event will be the
 * first instruction after the call in the calling method; note that
 * this location may not be at a line boundary, even if
 * {@link StepRequest#STEP_LINE StepRequest.STEP_LINE} was used.
 *
 * @see StepRequest
 * @see EventQueue
 *
 */
public interface StepEvent extends LocatableEvent {
}
