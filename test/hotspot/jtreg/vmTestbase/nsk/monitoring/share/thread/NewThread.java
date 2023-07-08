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

package nsk.monitoring.share.thread;


import nsk.share.log.Log;
import java.lang.management.ThreadInfo;
import nsk.share.TestFailure;
import nsk.share.TestBug;














/**
 * New Thread is dummy RecursiveMonitoringThread that is not getting
 * started.
 */
public class NewThread extends RecursiveMonitoringThread {
        public NewThread(Log log, RunType recursionType, int maxDepth) {
                super(log, recursionType, maxDepth);
        }

        public void begin() {
                // We don't run this thread.
                runner = new Thread(this);
        }

        public void waitState() {
        }

        public void finish() {
        }

        protected void runInside() {
                throw new TestBug("Should not reach here");
        }

        protected boolean isStackTraceElementExpected(StackTraceElement element) {
                return super.isStackTraceElementExpected(element);
        }

        public void checkThreadInfo(ThreadInfo info) {
                verify(info == null, "ThreadInfo != null");
        }
}
