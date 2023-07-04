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

public class RedefineSubclassWithTwoInterfacesRemote {
    // This class is acting in the role of:
    // wlstest.unit.diagnostics.common.apps.echoejb.EchoBean4_nh7k_EchoRemoteImpl
    // since it is calling the echo() method via an interface.
    private RedefineSubclassWithTwoInterfacesIntf1 intf1;
    private RedefineSubclassWithTwoInterfacesIntf2 intf2;

    RedefineSubclassWithTwoInterfacesRemote(
            RedefineSubclassWithTwoInterfacesIntf1 intf1,
            RedefineSubclassWithTwoInterfacesIntf2 intf2) {
        this.intf1 = intf1;
        this.intf2 = intf2;
    }

    // There is actually a bit more logic in the call stack from
    // EchoBean4_nh7k_EchoRemoteImpl.echo() to EchoBean4_nh7k_Intf.echo()
    public String echo1(String s) {
        return "intf1<" + intf1.echo(s) + ">";
    }

    public String echo2(String s) {
        return "intf2<" + intf2.echo(s) + ">";
    }
}
