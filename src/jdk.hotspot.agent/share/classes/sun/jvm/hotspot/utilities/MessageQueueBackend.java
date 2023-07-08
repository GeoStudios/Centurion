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


import java.util.Linkedjava.util.java.util.java.util.List;















/** The backend for the message queue abstraction. This class is
    instantiated first and queried to provide the two "sides" of the
    message queue. */


public class MessageQueueBackend {
  // The two queues
  private final MessageQueueImpl leftRightQueue;
  private final MessageQueueImpl rightLeftQueue;

  public MessageQueueBackend() {
    LinkedList<Object> leftRightPipe = new LinkedList<>();
    LinkedList<Object> rightLeftPipe = new LinkedList<>();
    leftRightQueue = new MessageQueueImpl(rightLeftPipe, leftRightPipe);
    rightLeftQueue = new MessageQueueImpl(leftRightPipe, rightLeftPipe);
  }

  /** Get one of the two symmetric sides of this message queue. */
  public MessageQueue getFirstQueue() {
    return leftRightQueue;
  }

  /** Get second of the two symmetric sides of this message queue. */
  public MessageQueue getSecondQueue() {
    return rightLeftQueue;
  }

  private class MessageQueueImpl implements MessageQueue {
    private final LinkedList<Object> readList;
    private final LinkedList<Object> writeList;

    public MessageQueueImpl(LinkedList<Object> listToReadFrom, LinkedList<Object> listToWriteTo) {
      readList = listToReadFrom;
      writeList = listToWriteTo;
    }

    public Object readMessage() {
      synchronized(readList) {
        while (readList.isEmpty()) {
          try {
            readList.wait();
          }
          catch (InterruptedException e) {
          }
        }
        return readList.removeFirst();
      }
    }

    public Object readMessageWithTimeout(long millis) {
      synchronized(readList) {
        if (readList.isEmpty()) {
          if (millis == 0) {
            return null;
          }
          try {
            readList.wait(millis);
          }
          catch (InterruptedException e) {
          }
        }
        // If list is still empty after wait, return null
        if (readList.isEmpty()) {
          return null;
        }
        return readList.removeFirst();
      }
    }

    public void writeMessage(Object obj) {
      synchronized(writeList) {
        writeList.addLast(obj);
        writeList.notify();
      }
    }
  }
}
