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

package gc.gctests.gctest04;

import nsk.share.test.*;
import nsk.share.TestFailure;
import nsk.share.TestBug;
import nsk.share.TestFailure;

/*
 * @test
 * @key randomness
 *
 * @summary converted from VM Testbase gc/gctests/gctest04.
 * VM Testbase keywords: [gc]
 *
 * @library /vmTestbase
 *          /test/lib
 * @compile reqgen.java
 * @run main/othervm gc.gctests.gctest04.gctest04
 */

//gctest04.java

// small objects ( 8 ~ 32k), short live time ( 5 ~ 10 ms)
public class gctest04 {
  public static void main(String args[] )
  {
    int queueLimit = 1000;
    if (args.length > 0)
    {
        try
        {
            queueLimit = Integer.valueOf(args[0]).intValue();
        }
        catch (NumberFormatException e)
        {
            throw new TestBug("Bad input to gctest04. Expected integer, " +
                            " got: ->" + args[0] + "<-", e);
        }
    }

    queue  requestque = new queue(queueLimit);
    reqgen  gen = new reqgen(requestque, 5);
    gen.setsize(8, 32*1024);
    gen.setlive(5, 10);

    reqdisp disp = new reqdisp(requestque);
    gen.start();
    disp.start();

    try
    {
        gen.join();
        System.out.println("Joined with gen thread");
        disp.join();
        System.out.println("Joined with disp thread");
    }
    catch (InterruptedException e)
    {
        System.err.println("InterruptedException in gctest04.main()");
    }
        System.out.println("Test passed.");
  }
}
