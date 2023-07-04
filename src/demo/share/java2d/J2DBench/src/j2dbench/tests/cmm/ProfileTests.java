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

import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;

import j2dbench.Group;
import j2dbench.Result;
import j2dbench.TestEnvironment;

public class ProfileTests extends CMMTests {

    protected static Group profileRoot;

    public static void init() {
        profileRoot = new Group(cmmRoot, "profiles", "Profile Handling Benchmarks");

        new ReadHeaderTest();
        new GetNumComponentsTest();
    }

    protected ProfileTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
    }

    protected static class Context {

        ICC_Profile profile;
        TestEnvironment env;
        Result res;

        public Context(ICC_Profile profile, TestEnvironment env, Result res) {
            this.profile = profile;
            this.env = env;
            this.res = res;
        }
    }

    public Object initTest(TestEnvironment env, Result res) {
        ICC_ColorSpace cs = (ICC_ColorSpace) getColorSpace(env);
        return new Context(cs.getProfile(), env, res);
    }

    public void cleanupTest(TestEnvironment env, Object o) {
    }

    private static class ReadHeaderTest extends ProfileTests {

        public ReadHeaderTest() {
            super(profileRoot,
                    "getHeader",
                    "getData(icSigHead)");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ICC_Profile profile = ictx.profile;

            byte[] data = null;
            do {
                try {
                    data = profile.getData(ICC_Profile.icSigHead);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }

    private static class GetNumComponentsTest extends ProfileTests {

        public GetNumComponentsTest() {
            super(profileRoot,
                    "getNumComponents",
                    "getNumComponents");
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context) ctx;
            final ICC_Profile profile = ictx.profile;

            do {
                try {
                    int num = profile.getNumComponents();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }
}
