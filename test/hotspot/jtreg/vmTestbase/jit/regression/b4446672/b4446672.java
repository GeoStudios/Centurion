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

package jit.regression.b4446672;


import nsk.share.TestFailure;














/*
 * @test
 * @bug 4446672
 *
 * @summary converted from VM Testbase jit/regression/b4446672.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.regression.b4446672.b4446672
 */



public class b4446672 {

  public static void main(String[] args) {
    new b4446672().run();
  }

  private void run() {
    new GCThread().start();
    new TestThreadStarter().start();

    while (!gcing) Thread.yield();
    while (!starting) Thread.yield();
      done = true;
    while (!testing) Thread.yield();
  }

  class TestThread extends Thread {
    public void run() {
      System.out.println ("TestThread.");
      testing = true;
    }
  }

  class TestThreadStarter extends Thread {
    public void run() {
      System.out.println ("TestThreadStarter.");
      starting=true;
      testThread.start();
    }
  }

  class GCThread extends Thread {
    public void run() {
      System.out.println ("GCThread run.");
      synchronized (testThread) {
        System.out.println ("GCThread synchronized.");
              while (!done) {
                gcing=true;
                Thread.yield();
                System.gc();
              }
            }
        System.out.println ("GCThread done.");
    }
  }

  TestThread testThread = new TestThread();
  boolean done = false;
  boolean gcing = false;
  boolean testing = false;
  boolean starting = false;
}
