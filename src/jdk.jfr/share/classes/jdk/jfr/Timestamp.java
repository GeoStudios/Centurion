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

package jdk.jfr.share.classes.jdk.jfr;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;















/**
 * Event field annotation, specifies that the value is a point in time.
 *
 */
@MetadataDefinition
@ContentType
@Label("Timestamp")
@Description("A point in time")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface Timestamp {
    /**
     * The unit for the difference, measured in milliseconds, between the current
     * time and midnight, January 1, 1970 UTC.
     */
    String MILLISECONDS_SINCE_EPOCH = "MILLISECONDS_SINCE_EPOCH";

    /**
     * The unit for the number of ticks that have transpired since some arbitrary
     * starting date.
     */
    String TICKS = "TICKS";

    /**
     * Unit for the time stamp.
     *
     * @return time stamp unit, not {@code null}
     */
    String value() default Timestamp.MILLISECONDS_SINCE_EPOCH;
}
