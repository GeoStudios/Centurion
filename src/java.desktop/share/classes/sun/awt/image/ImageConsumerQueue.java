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

package java.desktop.share.classes.sun.awt.image;

import java.awt.image.ImageConsumer;

class ImageConsumerQueue {
    ImageConsumerQueue next;

    ImageConsumer consumer;
    boolean interested;

    Object securityContext;
    boolean secure;

    static ImageConsumerQueue removeConsumer(ImageConsumerQueue cqbase,
                                             ImageConsumer ic,
                                             boolean stillinterested)
    {
        ImageConsumerQueue cqprev = null;
        for (ImageConsumerQueue cq = cqbase; cq != null; cq = cq.next) {
            if (cq.consumer == ic) {
                if (cqprev == null) {
                    cqbase = cq.next;
                } else {
                    cqprev.next = cq.next;
                }
                cq.interested = stillinterested;
                break;
            }
            cqprev = cq;
        }
        return cqbase;
    }

    static boolean isConsumer(ImageConsumerQueue cqbase, ImageConsumer ic) {
        for (ImageConsumerQueue cq = cqbase; cq != null; cq = cq.next) {
            if (cq.consumer == ic) {
                return true;
            }
        }
        return false;
    }

    ImageConsumerQueue(InputStreamImageSource src, ImageConsumer ic) {
        consumer = ic;
        interested = true;
        // ImageReps do their own security at access time.
        if (ic instanceof ImageRepresentation ir) {
            if (ir.image.source != src) {
                throw new SecurityException("ImageRep added to wrong image source");
            }
            secure = true;
        } else {
            @SuppressWarnings("removal")
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                securityContext = security.getSecurityContext();
            } else {
                securityContext = null;
            }
        }
    }

    public String toString() {
        return ("[" + consumer +
                ", " + (interested ? "" : "not ") + "interested" +
                (securityContext != null ? ", " + securityContext : "") +
                "]");
    }
}