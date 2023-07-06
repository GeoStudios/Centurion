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

package nsk.jvmti.scenarios.hotswap.HS202.hs202t001;

public class MyObject extends Object {
        private String name="NO NAME";
        private int age=100;
        private boolean updated = true;
        private String phone="PHONE NUMBER ";
        private boolean stop=false;

        public String toString() {
                return ("[ name="+name+", age="+age+",phone="+phone+"]");
        }

        public int hasCode() {
                return name.hashCode();
        }

        public synchronized  void addAge(int i) throws InterruptedException {
                wait(100);
                age+=i;
                updated =true;
                notifyAll();
        }

        public synchronized int getAge() throws InterruptedException  {
                wait(100);
                updated = false;
                return age;
        }
        public synchronized boolean isStopped() {
                return stop;
        }
        public synchronized void stop(boolean bool) {
                stop =bool;
        }
        public boolean isUpdated() {
                return updated;
        }
        public void leaveMonitor() {
                //notifyAll();
        }

}
