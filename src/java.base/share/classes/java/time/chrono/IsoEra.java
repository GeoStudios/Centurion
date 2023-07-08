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

package java.base.share.classes.java.time.chrono;


import java.base.share.classes.java.time.DateTimeException;















/**
 * An era in the ISO calendar system.
 * <p>
 * The ISO-8601 standard does not define eras.
 * A definition has therefore been created with two eras - 'Current era' (CE) for
 * years on or after 0001-01-01 (ISO), and 'Before current era' (BCE) for years before that.
 *
 * <table class="striped" style="text-align:left">
 * <caption style="display:none">ISO years and eras</caption>
 * <thead>
 * <tr>
 * <th scope="col">year-of-era</th>
 * <th scope="col">era</th>
 * <th scope="col">proleptic-year</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <td>2</td><td>CE</td><th scope="row">2</th>
 * </tr>
 * <tr>
 * <td>1</td><td>CE</td><th scope="row">1</th>
 * </tr>
 * <tr>
 * <td>1</td><td>BCE</td><th scope="row">0</th>
 * </tr>
 * <tr>
 * <td>2</td><td>BCE</td><th scope="row">-1</th>
 * </tr>
 * </tbody>
 * </table>
 * <p>
 * <b>Do not use {@code ordinal()} to obtain the numeric representation of {@code IsoEra}.
 * Use {@code getValue()} instead.</b>
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 */
public enum IsoEra implements Era {

    /**
     * The singleton instance for the era before the current one, 'Before Current Era',
     * which has the numeric value 0.
     */
    BCE,
    /**
     * The singleton instance for the current era, 'Current Era',
     * which has the numeric value 1.
     */
    CE;

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of {@code IsoEra} from an {@code int} value.
     * <p>
     * {@code IsoEra} is an enum representing the ISO eras of BCE/CE.
     * This factory allows the enum to be obtained from the {@code int} value.
     *
     * @param isoEra  the BCE/CE value to represent, from 0 (BCE) to 1 (CE)
     * @return the era singleton, not null
     * @throws DateTimeException if the value is invalid
     */
    public static IsoEra of(int isoEra) {
        switch (isoEra) {
            case 0:
                return BCE;
            case 1:
                return CE;
            default:
                throw new DateTimeException("Invalid era: " + isoEra);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the numeric era {@code int} value.
     * <p>
     * The era BCE has the value 0, while the era CE has the value 1.
     *
     * @return the era value, from 0 (BCE) to 1 (CE)
     */
    @Override
    public int getValue() {
        return ordinal();
    }

}
