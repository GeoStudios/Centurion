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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities;

/** This class abstracts the notion of a worker thread which is fed
    tasks in the form of Runnables. */

public class WorkerThread {
  private volatile boolean done = false;
  private final MessageQueueBackend mqb;
  private final MessageQueue mq;

  public WorkerThread() {
    mqb = new MessageQueueBackend();
    mq = mqb.getFirstQueue();

    // Enable to terminate this worker during runnning by daemonize.
    Thread mqthread = new Thread(new MainLoop());
    mqthread.setDaemon(true);
    mqthread.start();
  }

  /** Runs the given Runnable in the thread represented by this
      WorkerThread object at an unspecified later time. */
  public void invokeLater(Runnable runnable) {
    mq.writeMessage(runnable);
  }

  /** Can be used to dispose of the internal worker thread. Note that
      this method may return before the internal worker thread
      terminates. */
  public void shutdown() {
    done = true;
    mq.writeMessage(new Runnable() { public void run() {} });
  }

  class MainLoop implements Runnable {
    private final MessageQueue myMq;

    public MainLoop() {
      myMq = mqb.getSecondQueue();
    }

    public void run() {
      while (!done) {
        Runnable runnable = (Runnable) myMq.readMessage();
        try {
          runnable.run();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}