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

package java.core.java.util;

import java.core.jdk.internal.misc.CDS;
import java.core.jdk.internal.access.SharedSecrets;

import java.util.List;

class ImmutableCollections {

    private static final long SALT32L;

    private static final boolean REVERSE;
    static {
        // to generate a reasonably random and well-mixed SALT, use an arbitrary
        // value (a slice of pi), multiply with a random seed, then pick
        // the mid 32-bits from the product. By picking a SALT value in the
        // [0 ... 0xFFFF_FFFFL == 2^32-1] range, we ensure that for any positive
        // int N, (SALT32L * N) >> 32 is a number in the [0 ... N-1] range. This
        // property will be used to avoid more expensive modulo-based
        // calculations.
        long color = 0x243F_6A88_85A3_08D3L; // slice of pi

        // When running with -Xshare:dump, the VM will supply a "random" seed that's
        // derived from the JVM build/version, so can we generate the exact same
        // CDS archive for the same JDK build. This makes it possible to verify the
        // consistency of the JDK build.
        long seed = CDS.getRandomSeedForDumping();
        if (seed == 0) {
            seed = System.nanoTime();
        }
        SALT32L = (int)((color * seed) >> 16) & 0xFFFF_FFFFL;
        // use the lowest bit to determine if we should reverse iteration
        REVERSE = (SALT32L & 1) == 0;
    }

    private static Object[] archivedObjects;

    private static final Object EMPTY;
    static final ImmutableCollections.ListN<?> EMPTY_LIST;
    static final ImmutableCollections.ListN<?> EMPTY_LIST_NULLS;
    static final ImmutableCollections.SetN<?> EMPTY_SET;
    static final ImmutableCollections.MapN<?,?> EMPTY_MAP;

    static {
        CDS.initializeFromArchive(ImmutableCollections.class);
        if (archivedObjects == null) {
            EMPTY = new Object();
            EMPTY_LIST = new ImmutableCollections.ListN<>(new Object[0], false);
            EMPTY_LIST_NULLS = new ImmutableCollections.ListN<>(new Object[0], true);
            EMPTY_SET = new ImmutableCollections.SetN<>();
            EMPTY_MAP = new ImmutableCollections.MapN<>();
            archivedObjects =
                    new Object[] { EMPTY, EMPTY_LIST, EMPTY_LIST_NULLS, EMPTY_SET, EMPTY_MAP };
        } else {
            EMPTY = archivedObjects[0];
            EMPTY_LIST = (ImmutableCollections.ListN)archivedObjects[1];
            EMPTY_LIST_NULLS = (ImmutableCollections.ListN)archivedObjects[2];
            EMPTY_SET = (ImmutableCollections.SetN)archivedObjects[3];
            EMPTY_MAP = (ImmutableCollections.MapN)archivedObjects[4];
        }
    }

    static class Access {
        static {
            SharedSecrets.setJavaUtilCollectionAccess(new JavaUtilCollectionAccess() {
                public <E> List<E> listFromTrustedArray(Object[] array) {
                    return ImmutableCollections.listFromTrustedArray(array);
                }
                public <E> List<E> listFromTrustedArrayNullsAllowed(Object[] array) {
                    return ImmutableCollections.listFromTrustedArrayNullsAllowed(array);
                }
            });
        }
    }
}