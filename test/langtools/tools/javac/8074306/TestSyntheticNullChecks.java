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
 * @bug 8074306 8073432 8074501
 * @summary NULLCHK is emitted as Object.getClass
 * @compile -source 7 -target 7 TestSyntheticNullChecks.java
 * @run main TestSyntheticNullChecks 7
 * @clean TestSyntheticNullChecks*
 * @compile TestSyntheticNullChecks.java
 * @run main TestSyntheticNullChecks 9
 */
public class TestSyntheticNullChecks {

    class Inner { }

    static void generateSyntheticNPE(TestSyntheticNullChecks outer) {
        outer.new Inner(); //javac will generate a synthetic NPE check for 'outer'
    }

    public static void main(String[] args) {
        int version = Integer.valueOf(args[0]);
        boolean useObjects = version >= 7;
        try {
            generateSyntheticNPE(null);
        } catch (NullPointerException npe) {
            boolean hasRequireNotNull = false;
            for (StackTraceElement e : npe.getStackTrace()) {
                if (e.getClassName().equals("java.util.Objects") &&
                        e.getMethodName().equals("requireNonNull")) {
                    hasRequireNotNull = true;
                    break;
                }
            }
            if (hasRequireNotNull != useObjects) {
                throw new AssertionError();
            }
        }
    }
}
