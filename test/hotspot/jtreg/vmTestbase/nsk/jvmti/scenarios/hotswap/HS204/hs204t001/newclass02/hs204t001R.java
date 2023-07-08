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

package nsk.jvmti.scenarios.hotswap.HS204.hs204t001;

import java.util.concurrent.atomic.AtomicBoolean;

public class hs204t001R extends Thread{
      static public AtomicBoolean suspend = new AtomicBoolean(false);
      static public AtomicBoolean run = new AtomicBoolean(true);
        private int index=0;

        public hs204t001R() {
                super();
                setName("hs204t001R");
                System.out.println("NEWCLASS02");
        }

        public void run() {
                System.out.println(" started running thread..");
                doInThisThread();
                System.out.println(" comming out ..");
        }

        private void doInThisThread() {
                System.out.println("... Inside doThisThread..");
                while(hs204t001R.run.get()) {
                                index+=1;
                        if (index == 1500) {
                                hs204t001R.suspend.set(true);
                        }
                }
        }
        public int getIndex() {
                return index;
        }

}
