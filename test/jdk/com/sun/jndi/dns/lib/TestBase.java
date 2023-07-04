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

/**
 * The test base class for JNDI related tests.
 *
 * run() will be the entry to launch whole tests, base test sequence is
 * initRes(), initTest(), setupTest() and launch().
 *
 * launch() will call real test logic runTest() which required to be implemented
 * in test class.
 *
 * exception handling logic should be placed in handleException().
 *
 * cleanup related should be placed in cleanupTest which been called in finally
 * block.
 */
public abstract class TestBase {

    /**
     * The entry to the test.
     *
     * @param args given input arguments
     * @throws Exception if any exception
     */
    public void run(String[] args) throws Exception {
        initRes();
        initTest(args);
        setupTest();
        launch();
    }

    /**
     * Initial local resources, such as socket.
     *
     * @throws Exception if any exception
     */
    public void initRes() throws Exception {
    }

    /**
     * Initial test with given arguments.
     *
     * @param args given arguments
     */
    public void initTest(String[] args) {
    }

    /**
     * Setup test.
     */
    public void setupTest() {
    }

    /**
     * Launch test.
     *
     * @throws Exception if any exception
     */
    public void launch() throws Exception {
        try {
            runTest();
        } catch (Exception e) {
            if (!handleException(e)) {
                throw e;
            }
        } finally {
            cleanupTest();
        }
    }

    /**
     * The real test logic to run, this required to be implemented in test class.
     *
     * @throws Exception if any exception
     */
    public abstract void runTest() throws Exception;

    /**
     * Handle test exception.
     *
     * @param e exception which been thrown during test runTest()
     * @return <tt>true</tt> if given exception is expected
     */
    public boolean handleException(Exception e) {
        return false;
    }

    /**
     * Cleanup test.
     */
    public abstract void cleanupTest();
}
