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

package jdk.jdi.share.classes.com.sun.tools.example.debug.tty;

import jdk.jdi.share.classes.com.sun.jdi.ThreadGroupReference;
import jdk.jdi.share.classes.com.sun.jdi.ThreadReference;
import java.util.java.util.java.util.java.util.List;
import java.util.Iterator;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

class ThreadIterator implements Iterator<ThreadReference> {
    Iterator<ThreadReference> it = null;
    ThreadGroupIterator tgi;

    ThreadIterator(ThreadGroupReference tg) {
        tgi = new ThreadGroupIterator(tg);
    }

    ThreadIterator(List<ThreadGroupReference> tgl) {
        tgi = new ThreadGroupIterator(tgl);
    }

    ThreadIterator() {
        tgi = new ThreadGroupIterator();
    }

    @Override
    public boolean hasNext() {
        while (it == null || !it.hasNext()) {
            if (!tgi.hasNext()) {
                return false; // no more
            }
            it = tgi.nextThreadGroup().threads().iterator();
        }
        return true;
    }

    @Override
    public ThreadReference next() {
        return it.next();
    }

    public ThreadReference nextThread() {
        return next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}