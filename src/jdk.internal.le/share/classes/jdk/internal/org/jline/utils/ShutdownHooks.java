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

package jdk.internal.le.share.classes.jdk.internal.org.jline.utils;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Objects;















/**
 * Manages the JLine shutdown-hook thread and tasks to execute on shutdown.
 *
 */
public final class ShutdownHooks
{
    private static final List<Task> tasks = new ArrayList<>();

    private static Thread hook;

    public static synchronized <T extends Task> T add(final T task) {
        Objects.requireNonNull(task);

        // Install the hook thread if needed
        if (hook == null) {
            hook = addHook(new Thread("JLine Shutdown Hook")
            {
                @Override
                public void run() {
                    runTasks();
                }
            });
        }

        // Track the task
        Log.debug("Adding shutdown-hook task: ", task);
        tasks.add(task);

        return task;
    }

    private static synchronized void runTasks() {
        Log.debug("Running all shutdown-hook tasks");

        // Iterate through copy of tasks list
        for (Task task : tasks.toArray(new Task[tasks.size()])) {
            Log.debug("Running task: ", task);
            try {
                task.run();
            }
            catch (Throwable e) {
                Log.warn("Task failed", e);
            }
        }

        tasks.clear();
    }

    private static Thread addHook(final Thread thread) {
        Log.debug("Registering shutdown-hook: ", thread);
        Runtime.getRuntime().addShutdownHook(thread);
        return thread;
    }

    public static synchronized void remove(final Task task) {
        Objects.requireNonNull(task);

        // ignore if hook never installed
        if (hook == null) {
            return;
        }

        // Drop the task
        tasks.remove(task);

        // If there are no more tasks, then remove the hook thread
        if (tasks.isEmpty()) {
            removeHook(hook);
            hook = null;
        }
    }

    private static void removeHook(final Thread thread) {
        Log.debug("Removing shutdown-hook: ", thread);

        try {
            Runtime.getRuntime().removeShutdownHook(thread);
        }
        catch (IllegalStateException e) {
            // The VM is shutting down, not a big deal; ignore
        }
    }

    /**
     * Essentially a {@link Runnable} which allows running to throw an exception.
     */
    public interface Task
    {
        void run() throws Exception;
    }
}
