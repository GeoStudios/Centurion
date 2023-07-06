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

package xp2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

public class DatatypeFactoryImpl extends DatatypeFactory {

    @Override
    public Duration newDuration(String lexicalRepresentation) {
        return null;
    }

    @Override
    public Duration newDuration(long durationInMilliSeconds) {
        return null;
    }

    @Override
    public Duration newDuration(boolean isPositive, BigInteger years, BigInteger months, BigInteger days,
            BigInteger hours, BigInteger minutes, BigDecimal seconds) {
        return null;
    }

    @Override
    public XMLGregorianCalendar newXMLGregorianCalendar() {
        return null;
    }

    @Override
    public XMLGregorianCalendar newXMLGregorianCalendar(String lexicalRepresentation) {
        return null;
    }

    @Override
    public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar cal) {
        return null;
    }

    @Override
    public XMLGregorianCalendar newXMLGregorianCalendar(BigInteger year, int month, int day, int hour,
            int minute, int second, BigDecimal fractionalSecond, int timezone) {
        return null;
    }

}