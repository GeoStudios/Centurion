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

package java.naming.share.classes.com.sun.jndi.toolkit.ctx;


import javax.naming.Name;















/**
  * A class for returning the result of p_parseComponent();
  *
  */
public class HeadTail {
    private int status;
    private final Name head;
    private final Name tail;

    public HeadTail(Name head, Name tail) {
        this(head, tail, 0);
    }

    public HeadTail(Name head, Name tail, int status) {
        this.status = status;
        this.head = head;
        this.tail = tail;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Name getHead() {
        return this.head;
    }

    public Name getTail() {
        return this.tail;
    }

    public int getStatus() {
        return this.status;
    }
}
