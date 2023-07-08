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

package jdk.crypto.cryptoki.share.classes.sun.security.pkcs11.wrapper;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * class .<p>
 * <B>PKCS#11 structure:</B>
 * <PRE>
 * typedef struct CK_DATE {&nbsp;&nbsp;
 *   CK_CHAR year[4];&nbsp;&nbsp;
 *   CK_CHAR month[2];&nbsp;&nbsp;
 *   CK_CHAR day[2];&nbsp;&nbsp;
 * } CK_DATE;
 * </PRE>
 *
 */
public class CK_DATE implements Cloneable {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR year[4];   - the year ("1900" - "9999")
     * </PRE>
     */
    public char[] year;    /* the year ("1900" - "9999") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR month[2];  - the month ("01" - "12")
     * </PRE>
     */
    public char[] month;   /* the month ("01" - "12") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR day[2];    - the day ("01" - "31")
     * </PRE>
     */
    public char[] day;     /* the day ("01" - "31") */

    public CK_DATE(char[] year, char[] month, char[] day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Create a (deep) clone of this object.
     *
     * @return A clone of this object.
     */
    public Object clone() {
        CK_DATE copy = null;
        try {
            copy = (CK_DATE) super.clone();
        } catch (CloneNotSupportedException cnse) {
            // re-throw as RuntimeException
            throw new RuntimeException("Clone error", cnse);
        }
        copy.year = this.year.clone();
        copy.month = this.month.clone();
        copy.day =  this.day.clone();

        return copy;
    }

    /**
     * Returns the string representation of CK_DATE.
     *
     * @return the string representation of CK_DATE
     */
    public String toString() {

        String sb = new String(day) +
                '.' +
                new String(month) +
                '.' +
                new String(year) +
                " (DD.MM.YYYY)";

        return sb;
    }

}
