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
 * A Simple ListeningConnector throwing RuntimeException during instantiating used by
 * nsk/jdi/PlugConnectors/MultiConnectors/plugMultiConnect002 test
 */

public class PlugListenConnector002_03 extends PlugConnectors implements ListeningConnector {

    static String plugListenConnectorName = "PlugListenConnector002_03_Name";
    static String plugListenConnectorDescription = "PlugListenConnector002_03_Description";
    static Transport plugListenConnectorTransport = new PlugConnectorsTransport("PlugListenConnector002_03_Transport");
    static Map<String, Connector.Argument> plugListenConnectorDefaultArguments = new HashMap<String, Connector.Argument>();

    static Map<String, Connector.Argument> prepareConnectorDefaultArguments() {
        String plugListenConnectorStringArgumentKey = "PlugListenConnector002_03_StringArgument_Key";
        Connector.StringArgument testStringArgument = new TestStringArgument(
            "PlugListenConnector002_03_StringArgument_Name",
            "PlugListenConnector002_03_StringArgument_Label",
            "PlugListenConnector002_03_StringArgument_Description",
            "PlugListenConnector002_03_StringArgument_Value",
            true  // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorStringArgumentKey, testStringArgument);

        String plugListenConnectorIntegerArgumentKey = "PlugListenConnector002_03_IntegerArgument_Key";
        Connector.IntegerArgument testIntegerArgument = new TestIntegerArgument(
            "PlugListenConnector002_03_IntegerArgument_Name",
            "PlugListenConnector002_03_IntegerArgument_Label",
            "PlugListenConnector002_03_IntegerArgument_Description",
            555555, // IntegerArgument_Value",
            111111, // IntegerArgument_Min",
            999999, // IntegerArgument_Max",
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorIntegerArgumentKey, testIntegerArgument);

        String plugListenConnectorBooleanArgumentKey = "PlugListenConnector002_03_BooleanArgument_Key";
        Connector.BooleanArgument testBooleanArgument = new TestBooleanArgument(
            "PlugListenConnector002_03_BooleanArgument_Name",
            "PlugListenConnector002_03_BooleanArgument_Label",
            "PlugListenConnector002_03_BooleanArgument_Description",
            true, // BooleanArgument_Value",
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorBooleanArgumentKey, testBooleanArgument);

        String plugListenConnectorSelectedArgumentKey = "PlugListenConnector002_03_SelectedArgument_Key";
        List<String> selectedArgumentChoices = new ArrayList<String>();
        selectedArgumentChoices.add("PlugListenConnector002_03_SelectedArgument_Value_0");
        selectedArgumentChoices.add("PlugListenConnector002_03_SelectedArgument_Value");
        selectedArgumentChoices.add("PlugListenConnector002_03_SelectedArgument_Value_1");

        Connector.SelectedArgument testSelectedArgument = new TestSelectedArgument(
            "PlugListenConnector002_03_SelectedArgument_Name",
            "PlugListenConnector002_03_SelectedArgument_Label",
            "PlugListenConnector002_03_SelectedArgument_Description",
            "PlugListenConnector002_03_SelectedArgument_Value",
            selectedArgumentChoices, // List of choices,
            true    // mustSpecify
            );
        plugListenConnectorDefaultArguments.put(plugListenConnectorSelectedArgumentKey, testSelectedArgument);

        return plugListenConnectorDefaultArguments;
    }  // end of prepareConnectorDefaultArguments() method

    public PlugListenConnector002_03() {

        super(plugListenConnectorName,
            plugListenConnectorDescription,
            plugListenConnectorTransport,
            prepareConnectorDefaultArguments());

        String exceptionMessage =
            "<## PlugListenConnector002_03: This RuntimeException is thrown intentionally by ListeningConnector "
            + "constructor to check creating of pluggable connectors on base of such ListeningConnector. ##>";

        throw new RuntimeException(exceptionMessage);
    }

} // end of PlugListenConnector002_03 class