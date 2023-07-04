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
package vm.gc.containers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nsk.share.gc.gp.GarbageProducer;
import nsk.share.gc.gp.MemoryStrategy;
import nsk.share.test.LocalRandom;

/*
 * The MapContainer select a certain amout of random elements and remove
 * them. After this it put the same number of elements with random keys.
 */
class MapContainer extends TypicalContainer {

    Map map;

    public MapContainer(Map map, long maximumSize, GarbageProducer garbageProducer,
            MemoryStrategy memoryStrategy, Speed speed) {
        super(maximumSize, garbageProducer, memoryStrategy, speed);
        this.map = map;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < count; i++) {
            if (!stresser.continueExecution()) {
                return;
            }
            map.put(i, garbageProducer.create(size));
        }
    }

    @Override
    public void update() {
        Set<Integer> updated = new HashSet();
        for (int i = 0; i < count * speed.getValue() / 100; i++) {
            updated.add(LocalRandom.nextInt((int) count));
        }
        for (Integer i : updated) {
            if (!stresser.continueExecution()) {
                return;
            }
            Object obj = map.remove(i);
            if (obj != null) {
                garbageProducer.validate(obj);
            }
        }
        for (Integer i : updated) {
            if (!stresser.continueExecution()) {
                return;
            }
            map.put(i, garbageProducer.create(size));
        }
    }
}
