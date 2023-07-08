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

package jdk.jfr.share.classes.jdk.jfr.events;


import jdk.jfr.share.classes.jdk.jfr.Category;
import jdk.jfr.share.classes.jdk.jfr.Description;
import jdk.jfr.share.classes.jdk.jfr.Label;
import jdk.jfr.share.classes.jdk.jfr.Name;
import jdk.jfr.share.classes.jdk.jfr.internal.MirrorEvent;















@Category({"Java Development Kit", "Serialization"})
@Label("Deserialization")
@Name("jdk.Deserialization")
@Description("Results of deserialization and ObjectInputFilter checks")
@MirrorEvent(className = "jdk.internal.event.DeserializationEvent")
public final class DeserializationEvent extends AbstractJDKEvent {

    @Label("Filter Configured")
    public boolean filterConfigured;

    @Label("Filter Status")
    public String filterStatus;

    @Label ("Type")
    public Class<?> type;

    @Label ("Array Length")
    public int arrayLength;

    @Label ("Object References")
    public long objectReferences;

    @Label ("Depth")
    public long depth;

    @Label ("Bytes Read")
    public long bytesRead;

    @Label ("Exception Type")
    public Class<?> exceptionType;

    @Label ("Exception Message")
    public String exceptionMessage;
}
