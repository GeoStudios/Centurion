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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Event annotation, specifies the default duration below which an event is not
 * recorded (for example, {@code "20 ms"}).
 *
 */
@MetadataDefinition
@Target({ ElementType.TYPE })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Threshold {
    /**
     * Setting name {@code "threshold"} for configuring event thresholds.
     */
    String NAME = "threshold";

    /**
     * The threshold (for example, {@code "20 ms"}).
     * <p>
     * A {@code String} representation of a positive {@code Long} value followed by an
     * empty space and one of the following units:<br>
     * <br>
     * {@code "ns"} (nanoseconds)<br>
     * {@code "us"} (microseconds)<br>
     * {@code "ms"} (milliseconds)<br>
     * {@code "s"} (seconds)<br>
     * {@code "m"} (minutes)<br>
     * {@code "h"} (hours)<br>
     * {@code "d"} (days)<br>
     * <p>
     * Example values are {@code "0 ns"}, {@code "10 ms"}, and {@code "1 s"}.
     *
     * @return the threshold, default {@code "0 ns"}, not {@code null}
     */
    String value() default "0 ns";
}
