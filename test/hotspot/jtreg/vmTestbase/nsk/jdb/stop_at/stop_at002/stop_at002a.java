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

package nsk.jdb.stop_at.stop_at002;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;
import java.io.*;

/* This is debuggee aplication */
public class stop_at002a {
    public static void main(String args[]) {
       stop_at002a _stop_at002a = new stop_at002a();
       lastBreak();
       System.exit(stop_at002.JCK_STATUS_BASE + _stop_at002a.runIt(args, System.out));
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);

        new Nested(true).new DeeperNested().new DeepestNested().foo(false);
        Immersible var = new Inner().new MoreInner();
        var.foo("whatever");

        log.display("Debuggee PASSED");
        return stop_at002.PASSED;
    }

    class Nested {
        boolean flag;
        Nested (boolean b) {
            flag = b;
        }
        class DeeperNested {
            class  DeepestNested {
                public void foo(boolean input) {
                    flag = input; /* <--------  This is line number 43 */
                }
            }
        }
    }

    class Inner {
        class MoreInner implements Immersible {
            String content;

            public MoreInner() {
                content = "";
            }
            public void foo(String input) {
                content += input; /* <--------  This is line number 57 */
            }
        }
    }
}

interface Immersible {
    public void foo(String input);
}
