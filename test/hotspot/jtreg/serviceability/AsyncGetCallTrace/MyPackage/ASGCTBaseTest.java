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

package MyPackage;
















/**
 * @test
 * @summary Verifies that AsyncGetCallTrace is call-able and provides sane information.
 * @compile ASGCTBaseTest.java
 * @requires os.family == "linux"
 * @requires os.arch=="x86" | os.arch=="i386" | os.arch=="amd64" | os.arch=="x86_64" | os.arch=="arm" | os.arch=="aarch64" | os.arch=="ppc64" | os.arch=="s390"
 * @requires vm.jvmti
 * @run main/othervm/native -agentlib:AsyncGetCallTraceTest MyPackage.ASGCTBaseTest
 */

public class ASGCTBaseTest {
  static {
    try {
      System.loadLibrary("AsyncGetCallTraceTest");
    } catch (UnsatisfiedLinkError ule) {
      System.err.println("Could not load AsyncGetCallTrace library");
      System.err.println("java.library.path: " + System.getProperty("java.library.path"));
      throw ule;
    }
  }

  private static native boolean checkAsyncGetCallTraceCall();

  public static void main(String[] args) {
    if (!checkAsyncGetCallTraceCall()) {
      throw new RuntimeException("AsyncGetCallTrace call failed.");
    }
  }
}
