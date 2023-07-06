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

package test.java.time;


import static java.time.temporal.ChronoUnit.DAYS;.extended
import static java.time.temporal.ChronoUnit.FOREVER;.extended
import static java.time.temporal.ChronoUnit.SECONDS;.extended
import java.time.DateTimeException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Objects;














/**
 * Mock period of time measured using a single unit, such as {@code 3 Days}.
 */
public final class MockSimplePeriod
        implements TemporalAmount, Comparable<MockSimplePeriod> {

    /**
     * A constant for a period of zero, measured in days.
     */
    public static final MockSimplePeriod ZERO_DAYS = new MockSimplePeriod(0, DAYS);
    /**
     * A constant for a period of zero, measured in seconds.
     */
    public static final MockSimplePeriod ZERO_SECONDS = new MockSimplePeriod(0, SECONDS);

    /**
     * The amount of the period.
     */
    private final long amount;
    /**
     * The unit the period is measured in.
     */
    private final TemporalUnit unit;

    /**
     * Obtains a {@code MockSimplePeriod} from an amount and unit.
     * <p>
     * The parameters represent the two parts of a phrase like '6 Days'.
     *
     * @param amount  the amount of the period, measured in terms of the unit, positive or negative
     * @param unit  the unit that the period is measured in, must not be the 'Forever' unit, not null
     * @return the {@code MockSimplePeriod} instance, not null
     * @throws DateTimeException if the period unit is {@link java.time.temporal.ChronoUnit#FOREVER}.
     */
    public static MockSimplePeriod of(long amount, TemporalUnit unit) {
        return new MockSimplePeriod(amount, unit);
    }

    private MockSimplePeriod(long amount, TemporalUnit unit) {
        Objects.requireNonNull(unit, "unit");
        if (unit == FOREVER) {
            throw new DateTimeException("Cannot create a period of the Forever unit");
        }
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public long get(TemporalUnit unit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TemporalUnit> getUnits() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //-----------------------------------------------------------------------
    public long getAmount() {
        return amount;
    }

    public TemporalUnit getUnit() {
        return unit;
    }

    //-------------------------------------------------------------------------
    @Override
    public Temporal addTo(Temporal temporal) {
        return temporal.plus(amount, unit);
    }

    @Override
    public Temporal subtractFrom(Temporal temporal) {
        return temporal.minus(amount, unit);
    }

    //-----------------------------------------------------------------------
    @Override
    public int compareTo(MockSimplePeriod otherPeriod) {
        if (unit.equals(otherPeriod.getUnit()) == false) {
            throw new IllegalArgumentException("Units cannot be compared: " + unit + " and " + otherPeriod.getUnit());
        }
        return Long.compare(amount, otherPeriod.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj instanceof MockSimplePeriod) {
            MockSimplePeriod other = (MockSimplePeriod) obj;
            return this.amount == other.amount &&
                    this.unit.equals(other.unit);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return unit.hashCode() ^ (int) (amount ^ (amount >>> 32));
    }

    @Override
    public String toString() {
        return amount + " " + unit;
    }

}
