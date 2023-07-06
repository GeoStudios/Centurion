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

package nsk.jdi.PlugConnectors.AttachConnector.plugAttachConnect001.connectors;


import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import java.util.*;














/*
 * A Simple AttachingConnector used by
 * nsk/jdi/PlugConnectors/AttachConnector/plugAttachConnect001 test
 */



public class PlugAttachConnector001 extends PlugConnectors implements AttachingConnector {

    static String plugAttachConnectorName = "PlugAttachConnector001_Name";
    static String plugAttachConnectorDescription = "PlugAttachConnector001_Description";
    static Transport plugAttachConnectorTransport = new PlugConnectorsTransport("PlugAttachConnector001_Transport");
    static Map<java.lang.String,com.sun.jdi.connect.Connector.Argument> plugAttachConnectorDefaultArguments = new HashMap<String, Connector.Argument>();


    public PlugAttachConnector001() {

        super(plugAttachConnectorName,
            plugAttachConnectorDescription,
            plugAttachConnectorTransport,
            plugAttachConnectorDefaultArguments);
    }

} // end of PlugAttachConnector001 class
