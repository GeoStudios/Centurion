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

package nsk.share.gc.lock;

import nsk.share.TestBug;
import nsk.share.gc.lock.jni.JNILockers;
import nsk.share.gc.lock.malloc.MallocLockers;
import nsk.share.gc.lock.jvmti.JVMTIAllocLockers;
import nsk.share.gc.lock.jniref.*;

/**
 * Utility methods for lockers.
 */
public class LockerUtils {
        private LockerUtils() {
        }

        /**
         * Obtain Lockers by id.
         *
         * @param id identifier of Lockers
         */
        public static Lockers getLockers(String id) {
                if (id == null || id.equals("jni"))
                        return new JNILockers();
                else if (id.equals("jniGlobalRef"))
                        return new JNIGlobalRefLockers();
                else if (id.equals("jniLocalRef"))
                        return new JNILocalRefLockers();
                else if (id.equals("jniRef"))
                        return new JNIRefLockers();
                else if (id.equals("jniWeakGlobalRef"))
                        return new JNIWeakGlobalRefLockers();
                else if (id.equals("malloc"))
                        return new MallocLockers();
                else if (id.equals("jvmtiAlloc"))
                        return new JVMTIAllocLockers();
                else
                        throw new TestBug("Invalid lockers id: " + id);
        }
}
