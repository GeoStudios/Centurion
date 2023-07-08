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

package nsk.share.gc.gp.misc;

import nsk.share.gc.gp.DerivedProducer;
import nsk.share.gc.gp.GarbageProducer;

/*
 * Garbage producer that also calls {@link java.lang.System#identityHashCode(java.lang.Object)} on produced object.
 */

/*
     The description is misleading.  I looked at some old email, and the
     goal is to stress the code that deals with displaced mark words, so
     the description should be more like "Stress tests for displaced mark
     words."  In hotspot, each object has a mark word that stores several
     things about the object including its hash code (if it has one) and
     lock state.  Most objects never have a hash code and are never locked,
     so the mark word is empty.

     Most of our garbage collectors use the mark word temporarily during GC
     to store a 'forwarding pointer.'  It's not important what that is, but
     it means that objects that have a hash code or that are locked have to
     have the mark word saved during GC and then restored at the end of GC.
     We want to exercise this saving/restoring code.  So a test case should
     have a large percentage (40-50%) of objects that either have a hash
     code or are locked.

 */
public class HashedGarbageProducer<T> extends DerivedProducer<T, T> {
        public HashedGarbageProducer(GarbageProducer<T> parent) {
                super(parent);
        }

        public T create(long memory) {
                T obj = createParent(memory);
                System.identityHashCode(obj);
                return obj;
        }

        public void validate(T obj) {
                validateParent(obj);
        }
}
