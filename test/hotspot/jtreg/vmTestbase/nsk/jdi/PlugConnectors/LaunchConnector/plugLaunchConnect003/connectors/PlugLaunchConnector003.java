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

package nsk.jdi.PlugConnectors.LaunchConnector.plugLaunchConnect003.connectors;


import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import java.util.*;
import java.util.Arrayjava.util.java.util.java.util.List;














/*
 * A Simple LaunchingConnector used by
 * nsk/jdi/PlugConnectors/LaunchConnector/plugLaunchConnect003 test
 */



public class PlugLaunchConnector003 extends PlugConnectors implements LaunchingConnector {

    static String plugLaunchConnectorName = "PlugLaunchConnector003_Name";
    static String plugLaunchConnectorDescription = "PlugLaunchConnector003_Description";
    static Transport plugLaunchConnectorTransport = new PlugConnectorsTransport("PlugLaunchConnector003_Transport");
    static Map<String, Connector.Argument>  plugLaunchConnectorDefaultArguments = new HashMap<String, Connector.Argument>();

    static Map<String, Connector.Argument> prepareConnectorDefaultArguments() {
        String plugLaunchConnectorStringArgumentKey = "PlugLaunchConnector003_StringArgument_Key";
        Connector.StringArgument testStringArgument = new TestStringArgument(
            "PlugLaunchConnector003_StringArgument_Name",
            "PlugLaunchConnector003_StringArgument_Label",
            "PlugLaunchConnector003_StringArgument_Description",
            "PlugLaunchConnector003_StringArgument_Value",
            true  // mustSpecify
            );
        plugLaunchConnectorDefaultArguments.put(plugLaunchConnectorStringArgumentKey, testStringArgument);

        String plugLaunchConnectorIntegerArgumentKey = "PlugLaunchConnector003_IntegerArgument_Key";
        Connector.IntegerArgument testIntegerArgument = new TestIntegerArgument(
            "PlugLaunchConnector003_IntegerArgument_Name",
            "PlugLaunchConnector003_IntegerArgument_Label",
            "PlugLaunchConnector003_IntegerArgument_Description",
            555555, // IntegerArgument_Value",
            111111, // IntegerArgument_Min",
            999999, // IntegerArgument_Max",
            true    // mustSpecify
            );
        plugLaunchConnectorDefaultArguments.put(plugLaunchConnectorIntegerArgumentKey, testIntegerArgument);

        String plugLaunchConnectorBooleanArgumentKey = "PlugLaunchConnector003_BooleanArgument_Key";
        Connector.BooleanArgument testBooleanArgument = new TestBooleanArgument(
            "PlugLaunchConnector003_BooleanArgument_Name",
            "PlugLaunchConnector003_BooleanArgument_Label",
            "PlugLaunchConnector003_BooleanArgument_Description",
            true, // BooleanArgument_Value",
            true    // mustSpecify
            );
        plugLaunchConnectorDefaultArguments.put(plugLaunchConnectorBooleanArgumentKey, testBooleanArgument);

        String plugLaunchConnectorSelectedArgumentKey = "PlugLaunchConnector003_SelectedArgument_Key";
        List<String> selectedArgumentChoices = new ArrayList<String>();
        selectedArgumentChoices.add("PlugLaunchConnector003_SelectedArgument_Value_0");
        selectedArgumentChoices.add("PlugLaunchConnector003_SelectedArgument_Value");
        selectedArgumentChoices.add("PlugLaunchConnector003_SelectedArgument_Value_1");

        Connector.SelectedArgument testSelectedArgument = new TestSelectedArgument(
            "PlugLaunchConnector003_SelectedArgument_Name",
            "PlugLaunchConnector003_SelectedArgument_Label",
            "PlugLaunchConnector003_SelectedArgument_Description",
            "PlugLaunchConnector003_SelectedArgument_Value",
            selectedArgumentChoices, // List of choices,
            true    // mustSpecify
            );
        plugLaunchConnectorDefaultArguments.put(plugLaunchConnectorSelectedArgumentKey, testSelectedArgument);

        return plugLaunchConnectorDefaultArguments;
    }  // end of prepareConnectorDefaultArguments() method


    public PlugLaunchConnector003() {

        super(plugLaunchConnectorName,
            plugLaunchConnectorDescription,
            plugLaunchConnectorTransport,
            prepareConnectorDefaultArguments());

        String exceptionMessage =
            "<## PlugLaunchConnector003: This RuntimeException is thrown intentionally by LaunchingConnector "
            + "constructor to check creating of pluggable connectors on base of such LaunchingConnector. ##>";

        throw new RuntimeException(exceptionMessage);
    }

} // end of PlugLaunchConnector003 class
