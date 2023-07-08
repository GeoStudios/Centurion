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

package nsk.share.gc.gp.string;

import nsk.share.test.*;
import nsk.share.gc.Memory;
import nsk.share.gc.gp.GarbageProducer;

/**
 * Garbage producer that creates random strings.
 */
public class RandomStringProducer implements GarbageProducer<String> {

    private int stringLengthLowerBound = 10;

    public RandomStringProducer() {
    }

    public RandomStringProducer(int stringLengthLowerBound) {
        this.stringLengthLowerBound = stringLengthLowerBound;
    }

    public String create(long memory) {
        int stringLengthUpperBound = (int) Math.min(memory / 2 - Memory.getObjectExtraSize(), Integer.MAX_VALUE);
        if (stringLengthUpperBound < stringLengthLowerBound) {
                stringLengthLowerBound = stringLengthUpperBound;
        }
        int length = stringLengthLowerBound + LocalRandom.nextInt(stringLengthUpperBound - stringLengthLowerBound);
        char[] arr = new char[length];
        for (int i = 0; i < length; ++i) {
            arr[i] = (char) LocalRandom.nextInt();
        }
        return new String(arr);
    }

    public void setStringLengthLowerBound(int stringLengthLowerBound) {
        this.stringLengthLowerBound = stringLengthLowerBound;
    }

    public void validate(String s) {
    }
}
