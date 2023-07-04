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

/*
 * @test
 * @bug 6676075
 * @summary RegistryContext (com.sun.jndi.url.rmi.rmiURLContext) coding problem
 * @modules jdk.naming.rmi/com.sun.jndi.rmi.registry java.rmi/sun.rmi.registry
 *     java.rmi/sun.rmi.server java.rmi/sun.rmi.transport java.rmi/sun.rmi.transport.tcp
 * @library ../../../../../../java/rmi/testlibrary
 * @build TestLibrary
 * @run main/othervm ContextWithNullProperties
 */

import com.sun.jndi.rmi.registry.RegistryContext;
import java.rmi.registry.Registry;

public class ContextWithNullProperties {
    public static void main(String[] args) throws Exception {
        Registry registry = TestLibrary.createRegistryOnEphemeralPort();
        int registryPort = TestLibrary.getRegistryPort(registry);
        System.out.println("Connecting to the default Registry...");
        // Connect to the default Registry.
        // Pass null as the JNDI environment properties (see final argument)
        RegistryContext ctx = new RegistryContext(null, registryPort, null);
    }
}
