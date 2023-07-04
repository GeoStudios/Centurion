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

/**/

import java.io.*;
import java.util.Random;


class RandomLineSource implements Runnable {

    DataOutputStream uo;
    BufferedWriter to;
    LineGenerator lg;
    PrintWriter log;

    public RandomLineSource(OutputStream us, BufferedWriter ts, int limit,
                            PrintWriter log)
    {
        if (us != null)
            uo = new DataOutputStream(us);
        to = ts;
        IntGenerator ig = new IntGenerator();
        lg = new LineGenerator(ig, new StringGenerator(ig), limit);
        this.log = log;
    }

    public RandomLineSource(OutputStream us, BufferedWriter ts, int limit) {
        this(us, ts, limit, null);
    }

    public RandomLineSource(BufferedWriter ts, int limit) {
        this(null, ts, limit);
    }

    private void flush() throws IOException {
        if (uo != null) {
            uo.flush();
            Thread.currentThread().yield();
        }
        to.flush();
        for (int i = 0; i < 10; i++)
            Thread.currentThread().yield();
    }

    private int count = 0;

    public void run() {
        try {
            String s;

            while ((s = lg.next()) != null) {
                if (uo != null)
                    uo.writeUTF(s);
                to.write(s + lg.lineTerminator);
                flush();
                count++;
            }

            if (uo != null) {
                uo.close();
                Thread.currentThread().yield();
            }
            to.close();
            Thread.currentThread().yield();
        } catch (IOException x) {
            return;             /* Probably pipe broken */
        }
    }
}
