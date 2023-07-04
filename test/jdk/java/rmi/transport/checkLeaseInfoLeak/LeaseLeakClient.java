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

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class LeaseLeakClient {
    public static void main(String args[]) {
        TestLibrary.suggestSecurityManager("java.rmi.RMISecurityManager");

        try {
            LeaseLeak leaseLeak = null;
            int registryPort = Integer.parseInt(System.getProperty("rmi.registry.port"));

            // put a reference on a remote object.
            Registry registry =
                java.rmi.registry.LocateRegistry.getRegistry(registryPort);
            leaseLeak = (LeaseLeak) registry.lookup("/LeaseLeak");
            leaseLeak.ping();

        } catch(Exception e) {
            System.err.println("LeaseLeakClient Error: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
