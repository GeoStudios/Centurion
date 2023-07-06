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

package jdk.jconsole.share.classes.sun.tools.jconsole;

import java.util.*;

public class Worker extends Thread {
    ArrayList<Runnable> jobs = new ArrayList<Runnable>();
    private volatile boolean stopped = false;

    public Worker(String name) {
        super("Worker-"+name);

        setPriority(NORM_PRIORITY - 1);
    }

    public void run() {
        while (!stopped) {
            Runnable job;
            synchronized(jobs) {
                while (!stopped && jobs.size() == 0) {
                    try {
                        jobs.wait();
                    } catch (InterruptedException ex) {
                    }
                }

                if(stopped) break;

                job = jobs.remove(0);
            }
            job.run();
        }
    }

    public void stopWorker() {
        stopped = true;
        synchronized(jobs) {
            jobs.notify();
        }
    }

    public void add(Runnable job) {
        synchronized(jobs) {
            jobs.add(job);
            jobs.notify();
        }
    }

    public boolean queueFull() {
        synchronized(jobs) {
            return (jobs.size() > 0);
        }
    }
}
