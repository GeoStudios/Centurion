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

package java.base.share.classes.java.time.temporal;

import java.base.share.classes.java.time.DateTimeException;

/**
 * Strategy for adjusting a temporal object.
 * <p>
 * Adjusters are a key tool for modifying temporal objects.
 * They exist to externalize the process of adjustment, permitting different
 * approaches, as per the strategy design pattern.
 * Examples might be an adjuster that sets the date avoiding weekends, or one that
 * sets the date to the last day of the month.
 * <p>
 * There are two equivalent ways of using a {@code TemporalAdjuster}.
 * The first is to invoke the method on this interface directly.
 * The second is to use {@link Temporal#with(TemporalAdjuster)}:
 * <pre>
 *   // these two lines are equivalent, but the second approach is recommended
 *   temporal = thisAdjuster.adjustInto(temporal);
 *   temporal = temporal.with(thisAdjuster);
 * </pre>
 * It is recommended to use the second approach, {@code with(TemporalAdjuster)},
 * as it is a lot clearer to read in code.
 * <p>
 * The {@link TemporalAdjusters} class contains a standard set of adjusters,
 * available as static methods.
 * These include:
 * <ul>
 * <li>finding the first or last day of the month
 * <li>finding the first day of next month
 * <li>finding the first or last day of the year
 * <li>finding the first day of next year
 * <li>finding the first or last day-of-week within a month, such as "first Wednesday in June"
 * <li>finding the next or previous day-of-week, such as "next Thursday"
 * </ul>
 *
 * @implSpec
 * This interface places no restrictions on the mutability of implementations,
 * however immutability is strongly recommended.
 *
 * @see TemporalAdjusters
 */
@FunctionalInterface
public interface TemporalAdjuster {

    /**
     * Adjusts the specified temporal object.
     * <p>
     * This adjusts the specified temporal object using the logic
     * encapsulated in the implementing class.
     * Examples might be an adjuster that sets the date avoiding weekends, or one that
     * sets the date to the last day of the month.
     * <p>
     * There are two equivalent ways of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link Temporal#with(TemporalAdjuster)}:
     * <pre>
     *   // these two lines are equivalent, but the second approach is recommended
     *   temporal = thisAdjuster.adjustInto(temporal);
     *   temporal = temporal.with(thisAdjuster);
     * </pre>
     * It is recommended to use the second approach, {@code with(TemporalAdjuster)},
     * as it is a lot clearer to read in code.
     *
     * @implSpec
     * The implementation must take the input object and adjust it.
     * The implementation defines the logic of the adjustment and is responsible for
     * documenting that logic. It may use any method on {@code Temporal} to
     * query the temporal object and perform the adjustment.
     * The returned object must have the same observable type as the input object
     * <p>
     * The input object must not be altered.
     * Instead, an adjusted copy of the original must be returned.
     * This provides equivalent, safe behavior for immutable and mutable temporal objects.
     * <p>
     * The input temporal object may be in a calendar system other than ISO.
     * Implementations may choose to document compatibility with other calendar systems,
     * or reject non-ISO temporal objects by {@link TemporalQueries#chronology() querying the chronology}.
     * <p>
     * This method may be called from multiple threads in parallel.
     * It must be thread-safe when invoked.
     *
     * @param temporal  the temporal object to adjust, not null
     * @return an object of the same observable type with the adjustment made, not null
     * @throws DateTimeException if unable to make the adjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporal adjustInto(Temporal temporal);

}
