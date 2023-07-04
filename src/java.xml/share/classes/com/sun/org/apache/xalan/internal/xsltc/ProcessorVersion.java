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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc;


/**
 * Admin class that assigns a version number to the XSLTC software.
 * The version number is made up from three fields as in:
 * MAJOR.MINOR[.DELTA]. Fields are incremented based on the following:
 * DELTA field: changes for each bug fix, developer fixing the bug should
 *              increment this field.
 * MINOR field: API changes or a milestone culminating from several
 *              bug fixes. DELTA field goes to zero and MINOR is
 *              incremented such as: {1.0,1.0.1,1.0.2,1.0.3,...1.0.18,1.1}
 * MAJOR field: milestone culminating in fundamental API changes or
 *              architectural changes.  MINOR field goes to zero
 *              and MAJOR is incremented such as: {...,1.1.14,1.2,2.0}
 * Stability of a release follows: X.0 > X.X > X.X.X
 */
public class ProcessorVersion {
    private static final int MAJOR = 1;
    private static final int MINOR = 0;
    private static final int DELTA = 0;

    public static void main(String[] args) {
        System.out.println("XSLTC version " + MAJOR + "." + MINOR +
            ((DELTA > 0) ? ("."+DELTA) : ("")));
    }
}
