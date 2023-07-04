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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.AccessControlException;
import java.util.spi.ToolProvider;

/*
 * @test
 * @modules jdk.jlink
 * @build JLinkToolProviderTest
 * @run main/othervm/java.security.policy=toolprovider.policy JLinkToolProviderTest
 */
public class JLinkToolProviderTest {
    static final ToolProvider JLINK_TOOL = ToolProvider.findFirst("jlink")
        .orElseThrow(() ->
            new RuntimeException("jlink tool not found")
        );

    private static void checkJlinkOptions(String... options) {
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);

        try {
            JLINK_TOOL.run(pw, pw, options);
            throw new AssertionError("SecurityException should have been thrown!");
        } catch (AccessControlException ace) {
            if (! ace.getPermission().getClass().getName().contains("JlinkPermission")) {
                throw new AssertionError("expected JlinkPermission check failure");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        checkJlinkOptions("--help");
        checkJlinkOptions("--list-plugins");
    }
}
