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

package jdk.jdi.share.classes.com.sun.tools.jdi;

import java.io.java.io.java.io.java.io.IOException;
import java.util.Map;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;
import jdk.jdi.share.classes.com.sun.jdi.connect.Connector;
import jdk.jdi.share.classes.com.sun.jdi.connect.IllegalConnectorArgumentsException;
import jdk.jdi.share.classes.com.sun.jdi.connect.Transport;

/*
 * An AttachingConnector that uses the SocketTransportService
 */
public class SocketAttachingConnector extends GenericAttachingConnector {

    static final String ARG_PORT = "port";
    static final String ARG_HOST = "hostname";

    public SocketAttachingConnector() {
        super(new SocketTransportService());

        String defaultHostName = "localhost";

        addStringArgument(
            ARG_HOST,
            getString("socket_attaching.host.label"),
            getString("socket_attaching.host"),
            defaultHostName,
            false);

        addIntegerArgument(
            ARG_PORT,
            getString("socket_attaching.port.label"),
            getString("socket_attaching.port"),
            "",
            true,
            0, Integer.MAX_VALUE);

        transport = new Transport() {
            public String name() {
                return "dt_socket";     // for compatibility reasons
            }
        };
    }

    /*
     * Create an "address" from the hostname and port connector
     * arguments and attach to the target VM.
     */
    public VirtualMachine
        attach(Map<String, ? extends Connector.Argument> arguments)
        throws IOException, IllegalConnectorArgumentsException
    {
        String host = argument(ARG_HOST, arguments).value();
        if (host.length() > 0) {
            host = host + ":";
        }
        String address = host + argument(ARG_PORT, arguments).value();
        return super.attach(address, arguments);
    }

    public String name() {
       return "com.sun.jdi.SocketAttach";
    }

    public String description() {
       return getString("socket_attaching.description");
    }
}
