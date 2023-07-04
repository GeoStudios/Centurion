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
 * @bug 0000000
 * @summary RC2ParameterSpecEquals
 * @author Jan Luehe
 */
import javax.crypto.spec.*;

public class RC2ParameterSpecEquals {

    public static void main(String[] args) throws Exception {

        byte[] iv_1 = {
            (byte)0x11,(byte)0x11,(byte)0x11,(byte)0x11,
            (byte)0x11,(byte)0x11,(byte)0x11,(byte)0x11,
            (byte)0x33,(byte)0x33
        };
        byte[] iv_2 = {
            (byte)0x22,(byte)0x22,(byte)0x22,(byte)0x22,
            (byte)0x22,(byte)0x22,(byte)0x22,(byte)0x22,
        };

        RC2ParameterSpec rc_1 = new RC2ParameterSpec(2);
        RC2ParameterSpec rc_2 = new RC2ParameterSpec(2);
        if (!(rc_1.equals(rc_2)))
            throw new Exception("Should be equal");

        RC2ParameterSpec rc_3 = new RC2ParameterSpec(2, iv_1);
        RC2ParameterSpec rc_4 = new RC2ParameterSpec(2, iv_1);
        if (!(rc_3.equals(rc_4)))
            throw new Exception("Should be equal");

        RC2ParameterSpec rc_5 = new RC2ParameterSpec(2, iv_2);
        if (rc_3.equals(rc_5))
            throw new Exception("Should be different");

        RC2ParameterSpec rc_6 = new RC2ParameterSpec(2, iv_1, 2);
        if (rc_3.equals(rc_6))
            throw new Exception("Should be different");
    }
}
