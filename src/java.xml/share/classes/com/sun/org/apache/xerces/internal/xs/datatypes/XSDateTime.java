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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.datatypes;


import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>Interface to expose the values for all date-time related types. The following
 * table shows the methods defined for various XML Schema 1.0 built-in types. 'X'
 * marks whether a particular method is defined for a particular type. Accessing undefined
 * methods may return unexpected values.
 *
 * <table border="1">
 * <br/>
 * <tr>
 * <td> XML Schema Datatype </td>
 * <td> getYears() </td>
 * <td> getMonths() </td>
 * <td> getDays() </td>
 * <td> getHours() </td>
 * <td> getMinutes() </td>
 * <td> getSeconds() </td>
 * <td> getTimeZoneHours() </td>
 * <td> getTimeZoneMinutes() </td>
 * <td> getXMLGregorianCalendar() </td>
 * <td> getDuration() </td>
 * <td> hasTimeZone() </td>
 * <td> normalize() </td>
 * <td> isNormalized() </td>
 * <td> getLexicalValue() </td>
 * </tr>
 * <tr>
 * <td> gYear </td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonth </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gDay </td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gYearMonth </td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonthDay </td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> date </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> time </td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> datetime </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> duration </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * </tr>
 * </table>
 * </p>
 *
 *
 */
public interface XSDateTime {

    /**
     * @return years - can be negative for date-time related types;
     *
     */
    int getYears();

    /**
     * @return months - can be negative only for duration types;
     *                  For duration types, it returns years*12 + months
     */
    int getMonths();

    /**
     * @return days - cannot be negative;
     *
     */
    int getDays();

    /**
     * @return hours - cannot be negative;
     *
     */
    int getHours();

    /**
     * @return minutes - cannot be negative;
     *
     */
    int getMinutes();

    /**
     * @return seconds - can be negative only for durations;
     *                   For duration types, it returns days*24*3600 + hours*3600
     *                                                  + minutes*60 + seconds
     */
    double getSeconds();

    /**
     * @return boolean (true when timezone is specified in the original lexical value)
     *
     */
    boolean hasTimeZone();

    /**
     * @return timezone hours (for GMT-xx:xx this will be negative),
     *
     */
    int getTimeZoneHours();

    /**
     * @return timezone minutes (for GMT-xx:xx this will be negative),
     *
     */
    int getTimeZoneMinutes();

    /**
     * @return the original lexical value
     */
    String getLexicalValue();

    /**
     * @return a new date-time related object with normalized values
     *         (has no effect on objects already
     *          normalized)
     */
    XSDateTime normalize();

    /**
     * @return whether a date-time related object is normalized or not
     *         (value is not useful for types where timezone is not specified)
     */
    boolean isNormalized();

    /**
     * @return an un-normalized XMLGregorianCalendar (if applicable otherwise null)
     */
    XMLGregorianCalendar getXMLGregorianCalendar();

    /**
     * @return a Duration (if applicable otherwise null)
     */
    Duration getDuration();
}
