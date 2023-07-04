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

import java.util.Set;
import java.util.HashSet;

/**
 * The debuggee to be launched by the test
 * Sends out the info about the loaded modules
 * then stays to respond to the JDWP commands
 */
public class AllModulesCommandTestDebuggee {

    public static void main(String[] args) throws InterruptedException {

        int modCount = ModuleLayer.boot().modules().size();

        // Send all modules names via the process output
        for (Module mod : ModuleLayer.boot().modules()) {
            String info = String.format("module %s", mod.getName());
            write(info);
        }
        // Signal that the sending is done
        write("ready");
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void write(String s) {
        System.out.println(s);
        System.out.flush();
    }

}
