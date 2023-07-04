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
 * @test
 * @bug 4422738
 * @compile InvalidParameters.java
 * @run main InvalidParameters
 * @summary Make sure PKIXBuilderParameters(Set) detects invalid
 *          parameters and throws correct exceptions
 */
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.util.Collections;
import java.util.Set;

public class InvalidParameters {

    public static void main(String[] args) throws Exception {

        // make sure empty Set of anchors throws InvAlgParamExc
        try {
            PKIXBuilderParameters p =
                new PKIXBuilderParameters(Collections.EMPTY_SET, null);
            throw new Exception("should have thrown InvalidAlgorithmParameterExc");
        } catch (InvalidAlgorithmParameterException iape) { }

        // make sure null Set of anchors throws NullPointerException
        try {
            PKIXBuilderParameters p = new PKIXBuilderParameters((Set) null, null);
            throw new Exception("should have thrown NullPointerException");
        } catch (NullPointerException npe) { }

        // make sure Set of invalid objects throws ClassCastException
        try {
            @SuppressWarnings("unchecked") // Knowingly do something bad
            Set<TrustAnchor> badSet = (Set<TrustAnchor>) (Set) Collections.singleton(new String());
            PKIXBuilderParameters p =
                new PKIXBuilderParameters(badSet, null);
            throw new Exception("should have thrown ClassCastException");
        } catch (ClassCastException cce) { }
    }
}
