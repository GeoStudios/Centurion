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
 * @bug 4131126
 * @summary read throws io exception: Write end dead when there still
 *          is data available in the pipe.
 *
 */

import java.io.*;

public class FasterWriter implements Runnable {
    static PipedInputStream is;
    static PipedOutputStream os;

    public void run() {
        try {
            os.write(0);
            os.write(0);
            os.write(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        is = new PipedInputStream();
        os = new PipedOutputStream(is);

        Thread t = new Thread(new FasterWriter());
        t.start();
        t.join();

        try {
            is.read();
        } catch (IOException e) {
            throw new Exception("Cannot read remaining data in the pipe");
        }
    }
}
