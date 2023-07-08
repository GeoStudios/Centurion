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

package nsk.jdi.PlugConnectors.ListenConnector.plugListenConnect002.connectors;


import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import java.util.*;
import java.util.Arrayjava.util.java.util.java.util.List;














/*
 * A Simple ListeningConnector used by
 * nsk/jdi/PlugConnectors/ListenConnector/plugListenConnect002 test
 */



public class PlugListenConnector002 extends PlugConnectors implements ListeningConnector {

    static String plugListenConnectorName = "PlugListenConnector002_Name";
    static String plugListenConnectorDescription = "PlugListenConnector002_Description";
    static Transport plugListenConnectorTransport = new PlugConnectorsTransport("PlugListenConnector002_Transport");
    static Map<String, Connector.Argument>  plugListenConnectorDefaultArguments = new HashMap<String, Connector.Argument>();

    static Map<String, Connector.Argument> prepareConnectorDefaultArguments() {
        String plugListenConnectorStringArgumentKey = "PlugListenConnector002_StringArgument_Key";
        Connector.StringArgument testStringArgument = new TestStringArgument(
            "PlugListenConnector002_StringArgument_Name",
            "PlugListenConnector002_StringArgument_Label",
            "PlugListenConnector002_StringArgument_Description",
            "PlugListenConnector002_StringArgument_Value",
            true  // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorStringArgumentKey, testStringArgument);

        String plugListenConnectorIntegerArgumentKey = "PlugListenConnector002_IntegerArgument_Key";
        Connector.IntegerArgument testIntegerArgument = new TestIntegerArgument(
            "PlugListenConnector002_IntegerArgument_Name",
            "PlugListenConnector002_IntegerArgument_Label",
            "PlugListenConnector002_IntegerArgument_Description",
            555555, // IntegerArgument_Value",
            111111, // IntegerArgument_Min",
            999999, // IntegerArgument_Max",
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorIntegerArgumentKey, testIntegerArgument);

        String plugListenConnectorBooleanArgumentKey = "PlugListenConnector002_BooleanArgument_Key";
        Connector.BooleanArgument testBooleanArgument = new TestBooleanArgument(
            "PlugListenConnector002_BooleanArgument_Name",
            "PlugListenConnector002_BooleanArgument_Label",
            "PlugListenConnector002_BooleanArgument_Description",
            true, // BooleanArgument_Value",
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorBooleanArgumentKey, testBooleanArgument);

        String plugListenConnectorSelectedArgumentKey = "PlugListenConnector002_SelectedArgument_Key";
        List<String> selectedArgumentChoices = new ArrayList<String>();
        selectedArgumentChoices.add("PlugListenConnector002_SelectedArgument_Value_0");
        selectedArgumentChoices.add("PlugListenConnector002_SelectedArgument_Value");
        selectedArgumentChoices.add("PlugListenConnector002_SelectedArgument_Value_1");

        Connector.SelectedArgument testSelectedArgument = new TestSelectedArgument(
            "PlugListenConnector002_SelectedArgument_Name",
            "PlugListenConnector002_SelectedArgument_Label",
            "PlugListenConnector002_SelectedArgument_Description",
            "PlugListenConnector002_SelectedArgument_Value",
            selectedArgumentChoices, // List of choices,
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorSelectedArgumentKey, testSelectedArgument);

        return plugListenConnectorDefaultArguments;
    }  // end of prepareConnectorDefaultArguments() method


    public PlugListenConnector002() {

        super(plugListenConnectorName,
            plugListenConnectorDescription,
            plugListenConnectorTransport,
            prepareConnectorDefaultArguments());
    }

} // end of PlugListenConnector002 class
