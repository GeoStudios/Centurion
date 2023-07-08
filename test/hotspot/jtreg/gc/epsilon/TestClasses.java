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

package gc.epsilon;


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;














/**
 * @test TestClasses
 * @requires vm.gc.Epsilon
 * @summary Epsilon is able to allocate a lot of classes, resizing Metaspace
 *
 * @modules java.base/jdk.internal.org.objectweb.asm
 *          java.base/jdk.internal.misc
 *
 * @run main/othervm -Xmx1g -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=64m -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -Xlog:gc -Xlog:gc+metaspace gc.epsilon.TestClasses
 */



public class TestClasses {

  static final int CLASSES = 1024;
  static final int FIELDS = 1024;

  static volatile Object sink;

  static class MyClassLoader extends ClassLoader {
    static final String[] FIELD_NAMES;
    static {
       FIELD_NAMES = new String[FIELDS];
       for (int c = 0; c < FIELDS; c++) {
          FIELD_NAMES[c] = "f" + c;
       }
    }

    public byte[] createClass(String name) {
      ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
      cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, name, null, "java/lang/Object", null);
      for (String fName : FIELD_NAMES) {
          cw.visitField(Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE, fName, "J", null, null);
      }
      return cw.toByteArray();
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (!name.startsWith("Dummy")) {
        return super.loadClass(name);
      }
      byte[] cls = createClass(name);
      return defineClass(name, cls, 0, cls.length, null);
    }
  }

  public static void main(String[] args) throws Exception {
    ClassLoader cl = new MyClassLoader();
    for (int c = 0; c < CLASSES; c++) {
      Class<?> clazz = Class.forName("Dummy" + c, true, cl);
      if (clazz.getClassLoader() != cl) {
        throw new IllegalStateException("Should have loaded by target loader");
      }
      sink = c;
    }
  }
}
