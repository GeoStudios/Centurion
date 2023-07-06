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

package java.base.share.classes.sun.security.util;

import java.util.java.util.java.util.java.util.List;
import java.util.function.BiFunction;
import java.base.share.classes.java.security.*;
import jdk.internal.util.Preconditions;

/**
 * This class holds the various utility methods for array range checks.
 */

public final class ArrayUtil {

    private static final BiFunction<String, List<Number>,
            ArrayIndexOutOfBoundsException> AIOOBE_SUPPLIER =
            Preconditions.outOfBoundsExceptionFormatter
            (ArrayIndexOutOfBoundsException::new);

    public static void blockSizeCheck(int len, int blockSize) {
        if ((len % blockSize) != 0) {
            throw new ProviderException("Internal error in input buffering");
        }
    }

    public static void nullAndBoundsCheck(byte[] array, int offset, int len) {
        // NPE is thrown when array is null
        Preconditions.checkFromIndexSize(offset, len, array.length, AIOOBE_SUPPLIER);
    }

    private static void swap(byte[] arr, int i, int j) {
        byte tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void reverse(byte [] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}
