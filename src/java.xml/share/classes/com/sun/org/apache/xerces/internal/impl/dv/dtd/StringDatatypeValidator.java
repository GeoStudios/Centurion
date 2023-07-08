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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.dtd;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.*;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <P>StringValidator validates that XML content is a W3C string type.</P>
 * <P>The string datatype represents character strings in XML. The
 * value space of string is the set of finite-length sequences
 * of characters (as defined in [XML 1.0 Recommendation
 * (Second Edition)]) that match the Char production
 * from [XML 1.0 Recommendation (Second Edition)].
 * A character is an atomic unit of communication; it
 * is not further specified except to note that every
 * character has a corresponding Universal Code Set
 * code point ([ISO 10646],[Unicode] and [Unicode3]),
 * which is an integer.</P>
 *
 * @xerces.internal
 *
 */
public class StringDatatypeValidator implements DatatypeValidator {

    // construct a string datatype validator
    public StringDatatypeValidator() {
    }

    /**
     * Checks that "content" string is valid string value.
     * If invalid a Datatype validation exception is thrown.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @throws InvalidDatatypeException if the content is
     *         invalid according to the rules for the validators
     * @see InvalidDatatypeValueException
     */
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
    }

}
