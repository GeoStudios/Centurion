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
 * Event field annotation, specifies that the value is a duration.
 *
 */
@MetadataDefinition
@ContentType
@Label("Timespan")
@Description("A duration, measured in nanoseconds by default")
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface Timespan {
    /**
     * Unit for ticks.
     */
    String TICKS = "TICKS";
    /**
     * Unit for seconds.
     */
    String SECONDS = "SECONDS";
    /**
     * Unit for milliseconds.
     */
    String MILLISECONDS = "MILLISECONDS";
    /**
     * Unit for nanoseconds.
     */
    String NANOSECONDS = "NANOSECONDS";

    /**
     * Unit for microseconds.
     */
    String MICROSECONDS = "MICROSECONDS";

    /**
     * Returns the unit of measure for the time span.
     * <p>
     * By default, the unit is nanoseconds.
     *
     * @return the time span unit, default {@link #NANOSECONDS}, not {@code null}
     */
    String value() default NANOSECONDS;
}
