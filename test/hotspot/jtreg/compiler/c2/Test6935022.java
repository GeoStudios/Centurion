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

package compiler.c2;

/**
 * @test
 * @bug 6935022
 * @summary Server VM incorrectly breaks out of while loop
 *
 * @run main compiler.c2.Test6935022
 */

public class Test6935022 {
    public static final void main(String[] args) throws Exception {
        Test6935022 test = new Test6935022();

        int cnt = 0;

        while (cnt < 10000) {
            try {
                ++cnt;
                if ((cnt&1023) == 0)
                  System.out.println("Thread="+Thread.currentThread().getName() + " iteration: " + cnt);
                test.loop(2147483647, (cnt&1023));
            }

            catch (Exception e) {
                System.out.println("Caught on iteration " + cnt);
                e.printStackTrace();
                System.exit(97);
            }
        }
    }

    private void loop(int endingRow, int mask) throws Exception {
        int rows = 1;
        boolean next = true;

        while(rows <= endingRow && next) {
            rows++;
            if (rows == mask)
              System.out.println("Rows="+rows+", end="+endingRow+", next="+next);
            next = next(rows);
        }

        if (next)
            throw new Exception("Ended on rows(no rs): " + rows);
    }

    private boolean next(int rows) {
        return rows < 12;
    }
}

