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

import jdk.jdi.share.classes.com.sun.jdi.VirtualMachineManager;
import jdk.jdi.share.classes.com.sun.jdi.connect.Connector;
import jdk.jdi.share.classes.com.sun.jdi.connect.LaunchingConnector;

/**
 * VirtualMachineManager SPI
 */
public interface VirtualMachineManagerService extends VirtualMachineManager {

    /**
     * Replaces the default connector.
     *
     * @param connector the new default connector
     *
     * @throws java.lang.IllegalArgumentException if the given
     * connector is not a member of the list returned by
     * {@link #launchingConnectors}
     */
    void setDefaultConnector(LaunchingConnector connector);

    /**
     * Adds a connector to the list of known connectors.
     *
     * @param connector the connector to be added
     */
    void addConnector(Connector connector);

    /**
     * Removes a connector from the list of known connectors.
     *
     * @param connector the connector to be removed
     */
    void removeConnector(Connector connector);
}
