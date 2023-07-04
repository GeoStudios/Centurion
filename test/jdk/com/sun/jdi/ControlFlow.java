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
 */ // hard coded linenumbers in other tests - DO NOT CHANGE
/*
 * Debuggee which exercises various types of control flow
 */

class ControlFlow {
    boolean b = true;
    int n = 22;

    public static void main(String args[]) throws Exception {
        (new ControlFlow()).go();
    }

    void go() throws Exception {
        if (b) {
            System.out.println("if, no else");
        }

        if (b) {
            System.out.println("if branch");
        } else {
            throw new Exception("Wrong branch!?");
        }

        if (!b) {
            throw new Exception("Wrong branch!?");
        } else {
            System.out.println("else branch");
        }

        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("caught exception");
        } finally {
            System.out.println("finally");
        }

        // This isn't control flow at the source level,  but it is at the bytecode level
        synchronized (this) {
            System.out.println("synchronized");
        }


        for (int i = 0; i < n; i++) {
            System.out.println("Loop iteration: " + (i+1) + "/" + n);
        }

        switch (n) {
            case 0:
                throw new Exception("Wrong branch!?");
            case 1:
                throw new Exception("Wrong branch!?");
            case 2:
                throw new Exception("Wrong branch!?");
            case 3:
                throw new Exception("Wrong branch!?");
            case 22:
                System.out.println("switch case");
                break;
            default:
                throw new Exception("Wrong branch!?");
        }

        switch (n) {
            case 0:
                throw new Exception("Wrong branch!?");
            case 1:
                throw new Exception("Wrong branch!?");
            case 2:
                throw new Exception("Wrong branch!?");
            case 3:
                throw new Exception("Wrong branch!?");
            default:
                System.out.println("switch default");
                break;
        }
    }
}
