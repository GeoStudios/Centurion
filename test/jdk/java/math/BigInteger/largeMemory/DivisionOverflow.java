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
 * @bug 8022780
 * @summary Test division of large values
 * @requires (sun.arch.data.model == "64" & os.maxMemory > 8g)
 * @run main/othervm -Xshare:off -Xmx8g DivisionOverflow
 * @author Dmitry Nadezhin
 */
import java.math.BigInteger;

public class DivisionOverflow {

    public static void main(String[] args) {
        try {
            BigInteger a = BigInteger.ONE.shiftLeft(2147483646);
            BigInteger b = BigInteger.ONE.shiftLeft(1568);
            BigInteger[] qr = a.divideAndRemainder(b);
            BigInteger q = qr[0];
            BigInteger r = qr[1];
            if (!r.equals(BigInteger.ZERO)) {
                throw new RuntimeException("Incorrect signum() of remainder " + r.signum());
            }
            if (q.bitLength() != 2147482079) {
                throw new RuntimeException("Incorrect bitLength() of quotient " + q.bitLength());
            }
            System.out.println("Division of large values passed without overflow.");
        } catch (OutOfMemoryError e) {
            // possible
            System.err.println("DivisionOverflow skipped: OutOfMemoryError");
            System.err.println("Run jtreg with -javaoption:-Xmx8g");
        }
    }
}
