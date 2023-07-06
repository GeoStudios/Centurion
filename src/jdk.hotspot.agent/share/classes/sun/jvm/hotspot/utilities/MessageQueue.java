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

/** <p> A two-way unbounded-length message queue useful for
    communication between threads. Messages written on one side become
    readable on the other in first-in, first-out order. This is an
    interface to one of two "sides" of an underlying backend, for
    example, the MessageQueueBackend. </p> */

public interface MessageQueue {
  /** This blocks until a message is available. Even if the thread is
      interrupted while it is waiting, this will not return until a
      message is written by the entity on the other side of the
      queue. */
  Object readMessage();

  /** This blocks for up to <code>millis</code> milliseconds until a
      message is available. If no message becomes available within
      this time period, or the thread is interrupted during the wait,
      returns null. (This implies that passing the value null back and
      forth is not distinguishable with this method.) Passing a value
      of 0 for the <code>millis</code> argument causes this method to
      return without blocking. The millis argument must be greater
      than or equal to zero. */
  Object readMessageWithTimeout(long millis);

  /** Write a message to the queue */
  void writeMessage(Object obj);
}