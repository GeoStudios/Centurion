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

import java.util.Deque;
import nsk.share.gc.gp.GarbageProducer;
import nsk.share.gc.gp.MemoryStrategy;

/*
 * The dequeue container update remove a first or last
 * elements from queue and add the same number of elements.
 *
 */
class DequeueContainer extends TypicalContainer {

    Deque queue;
    boolean isLIFO;
    int threadsCount;

    public DequeueContainer(Deque queue, long maximumSize,
            GarbageProducer garbageProducer, MemoryStrategy memoryStrategy, Speed speed,
            int threadsCount) {
        super(maximumSize, garbageProducer, memoryStrategy, speed);
        this.threadsCount = threadsCount;
        this.queue = queue;
    }

    public void initialize() {
        for (int i = 0; i < count; i++) {
            if (!stresser.continueExecution()) {
                return;
            }
            queue.add(garbageProducer.create(size));
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < count * speed.getValue() / (threadsCount * 100); i++) {
            if (!stresser.continueExecution()) {
                return;
            }
            Object obj = null;
            if (isLIFO) {
                obj = queue.removeFirst();
            } else {
                obj = queue.removeLast();
            }
            garbageProducer.validate(obj);
        }
        for (int i = 0; i < count * speed.getValue() / (threadsCount * 100); i++) {
            if (!stresser.continueExecution()) {
                return;
            }
            queue.addFirst(garbageProducer.create(size));
        }
        isLIFO = !isLIFO;
    }
}
