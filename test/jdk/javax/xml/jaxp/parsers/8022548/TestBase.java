
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

import java.security.Policy;

/**
 *
 *
 * @author huizhe.wang@oracle.com
 */
public class TestBase {
    public String filePath;
    boolean hasSM;
    String curdir;
    Policy origPolicy;

    String testName;
    String errMsg;

    int passed = 0, failed = 0;

    /**
     * Creates a new instance of StreamReader
     */
    public TestBase(String name) {
        testName = name;
    }

    //junit @Override
    protected void setUp() {
        if (System.getSecurityManager() != null) {
            hasSM = true;
            System.setSecurityManager(null);
        }

        filePath = System.getProperty("test.src");
        if (filePath == null) {
            //current directory
            filePath = System.getProperty("user.dir");
        }
        origPolicy = Policy.getPolicy();

    }

    //junit @Override
    public void tearDown() {
        // turn off security manager and restore policy
        System.setSecurityManager(null);
        Policy.setPolicy(origPolicy);
        if (hasSM) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("\nNumber of tests passed: " + passed);
        System.out.println("Number of tests failed: " + failed + "\n");

        if (errMsg != null ) {
            throw new RuntimeException(errMsg);
        }
    }

    void fail(String msg) {
        if (errMsg == null) {
            errMsg = msg;
        } else {
            errMsg = errMsg + "\n" + msg;
        }
        failed++;
    }

    void success(String msg) {
        passed++;
        System.out.println(msg);
    }

}
