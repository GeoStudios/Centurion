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

package nsk.jdi.PlugConnectors.MultiConnectors.plugMultiConnect002.connectors;


import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import java.util.*;














/*
 * A Simple LaunchingConnector without default arguments used by
 * nsk/jdi/PlugConnectors/MultiConnectors/plugMultiConnect002 test
 */



public class PlugLaunchConnector002_01 extends PlugConnectors implements LaunchingConnector {

    static String plugLaunchConnectorName
        = "PlugLaunchConnector002_01_Name";
    static String plugLaunchConnectorDescription
        = "PlugLaunchConnector002_01_Description";
    static Transport plugLaunchConnectorTransport
        = new PlugConnectorsTransport("PlugLaunchConnector002_01_Transport");
    static Map<String, Connector.Argument> plugLaunchConnectorDefaultArguments
        = new HashMap<String, Connector.Argument>();


    public PlugLaunchConnector002_01() {

        super(plugLaunchConnectorName,
            plugLaunchConnectorDescription,
            plugLaunchConnectorTransport,
            plugLaunchConnectorDefaultArguments);
    }

} // end of PlugLaunchConnector002_01 class
