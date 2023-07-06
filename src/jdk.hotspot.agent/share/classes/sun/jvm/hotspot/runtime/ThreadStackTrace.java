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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;

import java.util.*;

public class ThreadStackTrace {
    private final JavaThread                        thread;
    private int                               depth;  // number of stack frames added
    private final ArrayList<StackFrameInfo>         frames;

    public ThreadStackTrace(JavaThread t) {
        this.thread = t;
        this.depth = 0;
        this.frames = new ArrayList<StackFrameInfo>();
    }

    public int getStackDepth() {
        return depth;
    }

    public StackFrameInfo stackFrameAt(int index) {
        return frames.get(index);
    }

    public void dumpStack(int maxDepth) {
        if (!thread.isJavaThread()) {
            return;
        }
        try {
            for (JavaVFrame vf = thread.getLastJavaVFrameDbg(); vf != null; vf = vf.javaSender()) {
                StackFrameInfo frame = new StackFrameInfo(vf);
                frames.add(frame);
                depth++;

                if (maxDepth > 0 && depth == maxDepth) {
                    // Skip frames if more than maxDepth
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred during stack walking:");
            e.printStackTrace();
        }
    }
}
