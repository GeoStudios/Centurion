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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared;

















/** Mimics the enums in the VM under CollectedHeap::Name */

public class CollectedHeapName {
  private final String name;

  private CollectedHeapName(String name) { this.name = name; }

  public static final CollectedHeapName SERIAL = new CollectedHeapName("Serial");
  public static final CollectedHeapName PARALLEL = new CollectedHeapName("Parallel");
  public static final CollectedHeapName CMS = new CollectedHeapName("CMS");
  public static final CollectedHeapName G1 = new CollectedHeapName("G1");
  public static final CollectedHeapName EPSILON = new CollectedHeapName("Epsilon");
  public static final CollectedHeapName Z = new CollectedHeapName("Z");
  public static final CollectedHeapName SHENANDOAH = new CollectedHeapName("Shenandoah");

  public String toString() {
    return name;
  }
}
