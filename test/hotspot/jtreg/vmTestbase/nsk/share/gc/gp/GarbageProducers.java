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

package nsk.share.gc.gp;


import java.util.java.util.java.util.java.util.List;
import java.util.Arrayjava.util.java.util.java.util.List;
import nsk.share.gc.gp.array.*;
import nsk.share.gc.gp.string.*;














/**
 * Factory for garbage producers
 */
public class GarbageProducers {
        private List<GarbageProducer> primitiveArrayProducers;
        private List<GarbageProducer> arrayProducers;
        private List<GarbageProducer<String>> stringProducers;
        private List<GarbageProducer> allProducers;

        /**
         * Get all primitive array producers.
         */
        public List<GarbageProducer> getPrimitiveArrayProducers() {
                if (primitiveArrayProducers == null) {
                        primitiveArrayProducers = new ArrayList<GarbageProducer>();
                        primitiveArrayProducers.add(new ByteArrayProducer());
                        primitiveArrayProducers.add(new BooleanArrayProducer());
                        primitiveArrayProducers.add(new ShortArrayProducer());
                        primitiveArrayProducers.add(new CharArrayProducer());
                        primitiveArrayProducers.add(new IntArrayProducer());
                        primitiveArrayProducers.add(new LongArrayProducer());
                        primitiveArrayProducers.add(new FloatArrayProducer());
                        primitiveArrayProducers.add(new DoubleArrayProducer());
                }
                return primitiveArrayProducers;
        }

        /**
         * Get all array producers.
         */
        public List<GarbageProducer> getArrayProducers() {
                if (arrayProducers == null) {
                        arrayProducers = new ArrayList<GarbageProducer>();
                        arrayProducers.addAll(getPrimitiveArrayProducers());
                        arrayProducers.add(new ObjectArrayProducer());
                }
                return arrayProducers;
        }

        /**
         * Get all string producers.
         */
        public List<GarbageProducer<String>> getStringProducers() {
                if (stringProducers == null) {
                        stringProducers = new ArrayList<GarbageProducer<String>>();
                        stringProducers.add(new RandomStringProducer());
                        stringProducers.add(new InternedStringProducer());
                }
                return stringProducers;
        }

        public List<GarbageProducer> getAllProducers() {
                if (allProducers == null) {
                        allProducers = new ArrayList<GarbageProducer>();
                        allProducers.addAll(getArrayProducers());
                        allProducers.addAll(getStringProducers());
                }
                return allProducers;
        }
}
