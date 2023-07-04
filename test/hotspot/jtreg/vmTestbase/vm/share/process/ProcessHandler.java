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
package vm.share.process;

import java.util.List;

public class ProcessHandler implements MessageInput, MessageOutput {
        private StreamMessageInput stdout = new StreamMessageInput();
        private StreamMessageInput stderr = new StreamMessageInput();
        private StreamMessageOutput stdin = new StreamMessageOutput();

        public ProcessHandler() {
        }

        public ProcessHandler(ProcessExecutor exec) {
                bind(exec);
        }

        public void bind(ProcessExecutor exec) {
                exec.addStdOutListener(stdout.createListener());
                exec.addStdErrListener(stderr.createListener());
                exec.start();
                stdin.bind(exec.getStdIn());
        }

        public boolean waitForStart(long timeout) throws InterruptedException {
                return stdout.waitForStart(timeout) && stderr.waitForStart(timeout);
        }

        public boolean waitForMessage(long timeout) throws InterruptedException {
                return stdout.waitForMessage(timeout);
        }

        public boolean waitForMessage(String msg, long timeout) throws InterruptedException {
                return stdout.waitForMessage(msg, timeout);
        }

        public String getMessage() {
                return stdout.getMessage();
        }

        public List<String> getMessages() {
                return stdout.getMessages();
        }

        public List<String> getMessages(int to) {
                return stdout.getMessages(to);
        }

        public List<String> getMessages(int from, int to) {
                return stdout.getMessages(from, to);
        }

        public boolean waitForStdErrMessage(String msg, long timeout) throws InterruptedException {
                return stderr.waitForMessage(msg, timeout);
        }

        public String getStdErrMessage() {
                return stderr.getMessage();
        }

        public boolean waitForFinish(long timeout) throws InterruptedException {
                return stdout.waitForFinish(timeout) && stderr.waitForFinish(timeout);
        }

        public void start() {
                stdin.start();
        }

        public void send(String msg) {
                stdin.send(msg);
        }

        public void finish() {
                stdin.finish();
        }

        public void reset() {
                stdout.reset();
                stderr.reset();
        }
}
