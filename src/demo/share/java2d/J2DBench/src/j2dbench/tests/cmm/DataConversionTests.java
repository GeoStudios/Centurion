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
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

package j2dbench.tests.cmm;

import java.awt.color.ColorSpace;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

public class DataConversionTests extends ColorConversionTests {

    protected static Group dataConvRoot;

    public static void init() {
        dataConvRoot = new Group(colorConvRoot, "data", "Data Conversoion Tests");

        new FromRGBTest();
        new ToRGBTest();
        new FromCIEXYZTest();
        new ToCIEXYZTest();
    }

    public DataConversionTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
    }

    protected static class Context {

        ColorSpace cs;
        int numComponents;
        float[] val;
        float[] rgb;
        float[] cie;
        TestEnvironment env;
        Result res;

        public Context(TestEnvironment env, Result result, ColorSpace cs) {
            this.cs = cs;
            this.env = env;
            this.res = result;

            numComponents = cs.getNumComponents();

            val = new float[numComponents];

            for (int i = 0; i < numComponents; i++) {
                float min = cs.getMinValue(i);
                float max = cs.getMaxValue(i);

                val[i] = 0.5f * (max - min);
            }

            rgb = new float[]{0.5f, 0.5f, 0.5f};
            cie = new float[]{0.5f, 0.5f, 0.5f};
        }
    }

    public Object initTest(TestEnvironment env, Result result) {
        ColorSpace cs = getColorSpace(env);
        return new Context(env, result, cs);
    }

    public void cleanupTest(TestEnvironment te, Object o) {
    }

    private static class FromRGBTest extends DataConversionTests {

        public FromRGBTest() {
            super(dataConvRoot,
                    "fromRGB",
                    "ColorSpace.fromRGB()");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ColorSpace cs = ictx.cs;

            final float[] rgb = ictx.rgb;
            do {
                try {
                    cs.fromRGB(rgb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }

    private static class FromCIEXYZTest extends DataConversionTests {

        public FromCIEXYZTest() {
            super(dataConvRoot,
                    "fromCIEXYZ",
                    "ColorSpace.fromCIEXYZ()");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ColorSpace cs = ictx.cs;

            final float[] val = ictx.cie;
            do {
                try {
                    cs.fromCIEXYZ(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }

    private static class ToCIEXYZTest extends DataConversionTests {

        public ToCIEXYZTest() {
            super(dataConvRoot,
                    "toCIEXYZ",
                    "ColorSpace.toCIEXYZ()");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ColorSpace cs = ictx.cs;

            final float[] val = ictx.val;

            do {
                try {
                    cs.toCIEXYZ(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }

    private static class ToRGBTest extends DataConversionTests {

        public ToRGBTest() {
            super(dataConvRoot,
                    "toRGB",
                    "ColorSpace.toRGB()");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ColorSpace cs = ictx.cs;

            final float[] val = ictx.val;

            do {
                try {
                    cs.toRGB(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }
}
