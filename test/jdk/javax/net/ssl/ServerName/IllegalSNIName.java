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

/*
 * @test
 * @bug 8020842 8261969
 * @summary SNIHostName does not throw IAE when hostname doesn't conform to
 *          RFC 3490 or ends with a trailing dot
 */

import javax.net.ssl.SNIHostName;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class IllegalSNIName {

    private static void checkHostname(String hostname) throws Exception {
        try {
            new SNIHostName(hostname);
            throw new RuntimeException("Expected to get IllegalArgumentException for "
                    + hostname);
        } catch (IllegalArgumentException iae) {
            // That's the right behavior.
        }
    }

    private static void checkHostname(byte[] encodedHostname) throws Exception {
        try {
            new SNIHostName(encodedHostname);
            throw new RuntimeException("Expected to get IllegalArgumentException for "
                    + HexFormat.ofDelimiter(":").formatHex(encodedHostname));
        } catch (IllegalArgumentException iae) {
            // That's the right behavior.
        }
    }

    public static void main(String[] args) throws Exception {
        String[] illegalNames = {
                "example\u3002\u3002com",
                "example..com",
                "com\u3002",
                "com.",
                ".",
                "example^com"
        };

        for (String name : illegalNames) {
            checkHostname(name);
            checkHostname(name.getBytes(StandardCharsets.UTF_8));
        }
    }
}
