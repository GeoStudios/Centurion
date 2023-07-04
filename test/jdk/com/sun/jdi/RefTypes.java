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

abstract class AllAbstract {
    abstract void a();
    abstract void b();
    abstract void c();
}

class AllNative {
    native void a();
    native void b();
    native void c();
}

abstract class Abstract {
    abstract void a();
    void b() {
        int x = 1;
        int y = 2;
        System.out.println("x + y = " + x + y);
        return;
    }
}

class Native {
    native void a();
    void b() {
        int x = 1;
        int y = 2;
        System.out.println("x + y = " + x + y);
        return;
    }
}

abstract class AbstractAndNative {
    abstract void a();
    native void b();
    void c() {
        int x = 1;
        int y = 2;
        System.out.println("x + y = " + x + y);
        return;
    }
}

interface Interface {
    void a();
    void b();
    void c();
}

interface InterfaceWithCode {
    String foo = new String("foo");
}

public class RefTypes {
    static void loadClasses() throws ClassNotFoundException {
        Class.forName("AllAbstract");
        Class.forName("AllNative");
        Class.forName("Abstract");
        Class.forName("Native");
        Class.forName("AbstractAndNative");
        Class.forName("Interface");
        Class.forName("InterfaceWithCode");
    }

    public static void main(String args[]) throws Exception {
        loadClasses();
    }
}
