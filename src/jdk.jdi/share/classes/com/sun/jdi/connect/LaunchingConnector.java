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

package jdk.jdi.share.classes.com.sun.jdi.connect;


import java.io.java.io.java.io.java.io.IOException;
import java.util.Map;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;
import jdk.jdi.share.classes.com.sun.jdi.event.VMStartEvent;















/**
 * A connector which can launch a target VM before connecting to it.
 *
 */
public interface LaunchingConnector extends Connector {

    /**
     * Launches an application and connects to its VM. Properties
     * of the launch (possibly including options,
     * main class, and arguments) are specified in
     * <code>arguments</code>.
     * The argument map associates argument name strings to instances
     * of {@link Connector.Argument}. The default argument map for a
     * connector can be obtained through {@link Connector#defaultArguments}.
     * Argument map values can be changed, but map entries should not be
     * added or deleted.
     * <p>A target VM launched by a launching connector is not
     * guaranteed to be stable until after the {@link VMStartEvent} has been
     * received.
     * <p>
     * <b>Important note:</b> If a target VM is launched through this
     * funcctions, its output and error streams must be read as it
     * executes. These streams are available through the
     * {@link java.lang.Process Process} object returned by
     * {@link VirtualMachine#process}. If the streams are not periodically
     * read, the target VM will stop executing when the buffers for these
     * streams are filled.
     *
     * @param arguments the argument map to be used in launching the VM.
     * @return the {@link VirtualMachine} mirror of the target VM.
     * @throws java.io.IOException when unable to launch.
     * Specific exceptions are dependent on the Connector implementation
     * in use.
     * @throws IllegalConnectorArgumentsException when one of the
     * connector arguments is invalid.
     * @throws VMStartException when the VM was successfully launched, but
     * terminated with an error before a connection could be established.
     */
    VirtualMachine launch(Map<String,? extends Connector.Argument> arguments)
        throws IOException, IllegalConnectorArgumentsException,
               VMStartException;
}
