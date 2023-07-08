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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities;

import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.classfile.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class SystemDictionaryHelper {
   static {
      VM.registerVMInitializedObserver(new Observer() {
         public void update(Observable o, Object data) {
            initialize();
         }
      });
   }

   private static synchronized void initialize() {
      klasses = null;
   }

   // Instance klass array sorted by name.
   private static InstanceKlass[] klasses;

   // side-effect!. caches the instance klass array.
   public static synchronized InstanceKlass[] getAllInstanceKlasses() {
      if (klasses != null) {
         return klasses;
      }

      final Vector<InstanceKlass> tmp = new Vector<>();
      ClassLoaderDataGraph cldg = VM.getVM().getClassLoaderDataGraph();
      cldg.classesDo(new ClassLoaderDataGraph.ClassVisitor() {
                        public void visit(Klass k) {
                           if (k instanceof InstanceKlass ik) {
                              tmp.add(ik);
                           }
                        }
                     });

      Object[] tmpArray = tmp.toArray();
      klasses = new InstanceKlass[tmpArray.length];
      System.arraycopy(tmpArray, 0, klasses, 0, tmpArray.length);
      Arrays.sort(klasses, new Comparator<>() {
                          public int compare(InstanceKlass k1, InstanceKlass k2) {
                             Symbol s1 = k1.getName();
                             Symbol s2 = k2.getName();
                             return s1.asString().compareTo(s2.asString());
                          }
                      });
      return klasses;
   }

   // returns array of instance klasses whose name contains given namePart
   public static InstanceKlass[] findInstanceKlasses(String namePart) {
      namePart = namePart.replace('.', '/');
      InstanceKlass[] tmpKlasses = getAllInstanceKlasses();

      Vector<InstanceKlass> tmp = new Vector<>();
      for (int i = 0; i < tmpKlasses.length; i++) {
         String name = tmpKlasses[i].getName().asString();
         if (name.indexOf(namePart) != -1) {
            tmp.add(tmpKlasses[i]);
         }
      }

      Object[] tmpArray = tmp.toArray();
      InstanceKlass[] searchResult = new InstanceKlass[tmpArray.length];
      System.arraycopy(tmpArray, 0, searchResult, 0, tmpArray.length);
      return searchResult;
   }

   // find first class whose name matches exactly the given argument.
   public static InstanceKlass findInstanceKlass(String className) {
      // convert to internal name
      className = className.replace('.', '/');
      ClassLoaderDataGraph cldg = VM.getVM().getClassLoaderDataGraph();

      // check whether we have a class of given name
      Klass klass = cldg.find(className);
      if (klass != null && klass instanceof InstanceKlass) {
         return (InstanceKlass) klass;
      } else {
        // no match ..
        return null;
      }
   }
}
