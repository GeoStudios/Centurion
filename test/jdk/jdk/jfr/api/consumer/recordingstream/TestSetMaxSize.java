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

package jdk.jfr.api.consumer.recordingstream;


import jdk.jfr.consumer.RecordingStream;
import jdk.test.lib.jfr.EventNames;














/**
* @test
* @summary Tests RecordingStrream::setMaxSize
* @key jfr
* @requires vm.hasJFR
* @library /test/lib
* @run main/othervm jdk.jfr.api.consumer.recordingstream.TestSetMaxSize
*/
public class TestSetMaxSize {

   public static void main(String... args) throws Exception {
       long testSize = 123456789;
       try (RecordingStream r = new RecordingStream()) {
           r.setMaxSize(123456789);
           r.enable(EventNames.ActiveRecording);
           r.onEvent(e -> {
               System.out.println(e);
               long size= e.getLong("maxSize");
               if (size == testSize) {
                   r.close();
                   return;
               }
               System.out.println("Max size not set, was " + size + ", but expected " + testSize);
           });
           r.start();
       }
   }
}