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
 * @bug 4321350
 * @bug 4516522
 * @summary Check that InetAddress.getByName() throws UHE with dotted
 *          IP address with octets out of range (Windows specific bug)
 *         or when bad IPv6 Litterals addresses are passed.
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BadDottedIPAddress {

    public static void main(String args[]) throws Exception {

        String host = "999.999.999.999";

        boolean exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        }

        if (!exc_thrown) {
            throw new Exception("UnknownHostException was not thrown for: "
                + host);
        }

        host = "[]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }

        if (!exc_thrown) {
            throw new Exception("UnknownHostException was not thrown for: "
                + host);
        }

        host = "[127.0.0.1]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }

        if (!exc_thrown) {
            throw new Exception("UnknownHostException was not thrown for: "
                + host);
        }

        host = "[localhost]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }

        if (!exc_thrown) {
            throw new Exception("UnknownHostException was not thrown for: "
                + host);
        }
    }
}
