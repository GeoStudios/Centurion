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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.xs;


import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Validator for &lt;gYearMonth&gt; datatype (W3C Schema Datatypes)
 *
 * @xerces.internal
 *
 *
 */
public class YearMonthDV extends AbstractDateTimeDV{

    /**
     * Convert a string to a compiled form
     *
     * @param  content The lexical representation of gYearMonth
     * @return a valid and normalized gYearMonth object
     */
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException{
        try{
            return parse(content);
        } catch(Exception ex){
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "gYearMonth"});
        }
    }

    /**
     * Parses, validates and computes normalized version of gYearMonth object
     *
     * @param str    The lexical representation of gYearMonth object CCYY-MM
     *               with possible time zone Z or (-),(+)hh:mm
     * @return normalized date representation
     * @exception SchemaDateTimeException Invalid lexical representation
     */
    protected DateTimeData parse(String str) throws SchemaDateTimeException{
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();

        // get date
        int end = getYearMonth(str, 0, len, date);
        date.day = DAY;
        parseTimeZone (str, end, len, date);

        //validate and normalize

        validateDateTime(date);

        //save unnormalized values
        saveUnnormalized(date);

        if ( date.utc!=0 && date.utc!='Z' ) {
            normalize(date);
        }
        date.position = 0;
        return date;
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(25);
        append(message, date.year, 4);
        message.append('-');
        append(message, date.month, 2);
        append(message, (char)date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        return datatypeFactory.newXMLGregorianCalendar(date.unNormYear, date.unNormMonth, DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
                date.hasTimeZone() ? date.timezoneHr * 60 + date.timezoneMin : DatatypeConstants.FIELD_UNDEFINED);
    }
}
