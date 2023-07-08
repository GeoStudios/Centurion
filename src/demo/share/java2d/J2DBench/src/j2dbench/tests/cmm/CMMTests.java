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

package demo.share.java2d.J2DBench.src.j2dbench.tests.cmm;


import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import demo.share.java2d.J2DBench.src.j2dbench.Group;
import demo.share.java2d.J2DBench.src.j2dbench.Option;
import demo.share.java2d.J2DBench.src.j2dbench.Result;
import demo.share.java2d.J2DBench.src.j2dbench.Test;
import demo.share.java2d.J2DBench.src.j2dbench.TestEnvironment;















/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */





public class CMMTests extends Test {

    protected static Group cmmRoot;
    protected static Group cmmOptRoot;
    protected static Option csList;
    protected static Option usePlatformProfiles;

    public static void init() {
        cmmRoot = new Group("cmm", "Color Management Benchmarks");
        cmmRoot.setTabbed();

        cmmOptRoot = new Group(cmmRoot, "opts", "General Options");

        /*
        usePlatformProfiles =
                new Option.Enable(cmmOptRoot, "csPlatform",
                        "Use Platform Profiles", false);
        */
        int[] colorspaces = new int[] {
            ColorSpace.CS_sRGB,
            ColorSpace.CS_GRAY,
            ColorSpace.CS_LINEAR_RGB,
            ColorSpace.CS_CIEXYZ,
            ColorSpace.CS_PYCC
        };

        String[] csNames = new String[]{
            "CS_sRGB",
            "CS_GRAY",
            "CS_LINEAR_RGB",
            "CS_CIEXYZ",
            "CS_PYCC"
        };

        csList = new Option.IntList(cmmOptRoot,
                "profiles", "Color Profiles",
                colorspaces, csNames, csNames, 0x8);

        ColorConversionTests.init();
        ProfileTests.init();
    }

    protected static ColorSpace getColorSpace(TestEnvironment env) {
        ColorSpace cs;
        boolean usePlatform = true; //(Boolean)env.getModifier(usePlatformProfiles);

        int cs_code = env.getIntValue(csList);
        if (usePlatform) {
            cs = ColorSpace.getInstance(cs_code);
        } else {
            String resource = "profiles/";
            switch (cs_code) {
                case ColorSpace.CS_CIEXYZ:
                    resource += "CIEXYZ.pf";
                    break;
                case ColorSpace.CS_GRAY:
                    resource += "GRAY.pf";
                    break;
                case ColorSpace.CS_LINEAR_RGB:
                    resource += "LINEAR_RGB.pf";
                    break;
                case ColorSpace.CS_PYCC:
                    resource += "PYCC.pf";
                    break;
                case ColorSpace.CS_sRGB:
                    resource += "sRGB.pf";
                    break;
                default:
                    throw new RuntimeException("Unknown color space: " + cs_code);
            }

            try {
                InputStream is = CMMTests.class.getResourceAsStream(resource);
                ICC_Profile p = ICC_Profile.getInstance(is);

                cs = new ICC_ColorSpace(p);
            } catch (IOException e) {
                throw new RuntimeException("Unable load profile from resource " + resource, e);
            }
        }
        return cs;
    }

    protected CMMTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
        addDependencies(cmmOptRoot, true);
    }

    public Object initTest(TestEnvironment te, Result result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void runTest(Object o, int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cleanupTest(TestEnvironment te, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
