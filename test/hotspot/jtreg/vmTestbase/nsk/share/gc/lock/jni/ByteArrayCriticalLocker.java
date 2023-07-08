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

package nsk.share.gc.lock.jni;


import nsk.share.gc.lock.CriticalSectionObjectLocker;
import nsk.share.TestFailure;














public class ByteArrayCriticalLocker extends CriticalSectionObjectLocker<byte[]> {
        private native byte criticalNative(long enterTime, long sleepTime);

        static {
                System.loadLibrary("ByteArrayCriticalLocker");
        }

        public ByteArrayCriticalLocker(byte[] obj) {
                super(obj);
        }
        protected void criticalSection(long enterTime, long sleepTime) {
                byte javaHash = hashValue(obj);
                byte nativeHash = criticalNative(enterTime, sleepTime);
                if (nativeHash != 0 && nativeHash != javaHash)
                        throw new TestFailure("Native hash: " + nativeHash + " != Java hash: " + javaHash);
        }


        private byte hashValue(byte[] obj) {
                byte hash = 0;
                for (int i = 0; i < obj.length; ++i)
                        hash ^= obj[i];
                return hash;
        }
}
