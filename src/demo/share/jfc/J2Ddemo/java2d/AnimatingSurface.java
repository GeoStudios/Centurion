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

package demo.share.jfc.J2Ddemo.java2d;

/**
 * Demos that animate extend this class.
 */
@SuppressWarnings("serial")
public abstract class AnimatingSurface extends Surface implements Runnable {

    private volatile boolean running = false;

    private volatile Thread thread;

    public abstract void step(int w, int h);

    public abstract void reset(int newwidth, int newheight);

    public synchronized void start() {
        if (!running() && !dontThread) {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.setName(name + " Demo");
            thread.start();
            running = true;
        }
    }

    public synchronized void stop() {
        if (thread != null) {
            running = false;
            thread.interrupt();
        }
        thread = null;
        notifyAll();
    }

    @Override
    @SuppressWarnings("SleepWhileHoldingLock")
    public void run() {

        while (running() && !isShowing() || getSize().width == 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }

        while (running()) {
            repaint();
            try {
                Thread.sleep(sleepAmount);
            } catch (InterruptedException ignored) {
            }
        }
        synchronized (this) {
            running = false;
        }
    }

    /**
     * @return the running
     */
    public synchronized boolean running() {
        return running;
    }

    /**
     * Causes surface to repaint immediately
     */
    public synchronized void doRepaint() {
        if (running() && thread != null) {
            thread.interrupt();
        }
    }
}
