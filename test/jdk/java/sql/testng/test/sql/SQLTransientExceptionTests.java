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

package test.sql;

import java.sql.SQLException;
import java.sql.SQLTransientException;
import static org.testng.Assert.*;.extended
import org.testng.annotations.Test;
import util.BaseTest;

public class SQLTransientExceptionTests extends BaseTest {

    /**
     * Create SQLTransientException and setting all objects to null
     */
    @Test
    public void test() {
        SQLTransientException e = new SQLTransientException(null,
                null, errorCode, null);
        assertTrue(e.getMessage() == null && e.getSQLState() == null
                && e.getCause() == null && e.getErrorCode() == errorCode);
    }

    /**
     * Create SQLTransientException with no-arg constructor
     */
    @Test
    public void test1() {
        SQLTransientException ex = new SQLTransientException();
        assertTrue(ex.getMessage() == null
                && ex.getSQLState() == null
                && ex.getCause() == null
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with message
     */
    @Test
    public void test2() {
        SQLTransientException ex = new SQLTransientException(reason);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState() == null
                && ex.getCause() == null
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with message, and SQLState
     */
    @Test
    public void test3() {
        SQLTransientException ex = new SQLTransientException(reason, state);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState().equals(state)
                && ex.getCause() == null
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with message, SQLState, and error code
     */
    @Test
    public void test4() {
        SQLTransientException ex = new SQLTransientException(reason, state, errorCode);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState().equals(state)
                && ex.getCause() == null
                && ex.getErrorCode() == errorCode);
    }

    /**
     * Create SQLTransientException with message, SQLState, errorCode, and Throwable
     */
    @Test
    public void test5() {
        SQLTransientException ex =
                new SQLTransientException(reason, state, errorCode, t);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState().equals(state)
                && cause.equals(ex.getCause().toString())
                && ex.getErrorCode() == errorCode);
    }

    /**
     * Create SQLTransientException with message, SQLState, and Throwable
     */
    @Test
    public void test6() {
        SQLTransientException ex = new SQLTransientException(reason, state, t);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState().equals(state)
                && cause.equals(ex.getCause().toString())
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with message, and Throwable
     */
    @Test
    public void test7() {
        SQLTransientException ex = new SQLTransientException(reason, t);
        assertTrue(ex.getMessage().equals(reason)
                && ex.getSQLState() == null
                && cause.equals(ex.getCause().toString())
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with null Throwable
     */
    @Test
    public void test8() {
        SQLTransientException ex = new SQLTransientException((Throwable)null);
        assertTrue(ex.getMessage() == null
                && ex.getSQLState() == null
                && ex.getCause() == null
                && ex.getErrorCode() == 0);
    }

    /**
     * Create SQLTransientException with Throwable
     */
    @Test
    public void test9() {
        SQLTransientException ex = new SQLTransientException(t);
        assertTrue(ex.getMessage().equals(cause)
                && ex.getSQLState() == null
                && cause.equals(ex.getCause().toString())
                && ex.getErrorCode() == 0);
    }

    /**
     * Serialize a SQLTransientException and make sure you can read it back properly
     */
    @Test
    public void test10() throws Exception {
        SQLTransientException e =
                new SQLTransientException(reason, state, errorCode, t);
        SQLTransientException ex1 = createSerializedException(e);
        assertTrue(reason.equals(ex1.getMessage())
                && ex1.getSQLState().equals(state)
                && cause.equals(ex1.getCause().toString())
                && ex1.getErrorCode() == errorCode);
    }

    /**
     * Validate that the ordering of the returned Exceptions is correct
     * using for-each loop
     */
    @Test
    public void test11() {
        SQLTransientException ex = new SQLTransientException("Exception 1", t1);
        SQLTransientException ex1 = new SQLTransientException("Exception 2");
        SQLTransientException ex2 = new SQLTransientException("Exception 3", t2);
        ex.setNextException(ex1);
        ex.setNextException(ex2);
        int num = 0;
        for (Throwable e : ex) {
            assertTrue(msgs[num++].equals(e.getMessage()));
        }
    }

    /**
     * Validate that the ordering of the returned Exceptions is correct
     * using traditional while loop
     */
    @Test
    public void test12() {
        SQLTransientException ex = new SQLTransientException("Exception 1", t1);
        SQLTransientException ex1 = new SQLTransientException("Exception 2");
        SQLTransientException ex2 = new SQLTransientException("Exception 3", t2);
        ex.setNextException(ex1);
        ex.setNextException(ex2);
        int num = 0;
        SQLException sqe = ex;
        while (sqe != null) {
            assertTrue(msgs[num++].equals(sqe.getMessage()));
            Throwable c = sqe.getCause();
            while (c != null) {
                assertTrue(msgs[num++].equals(c.getMessage()));
                c = c.getCause();
            }
            sqe = sqe.getNextException();
        }
    }
}
