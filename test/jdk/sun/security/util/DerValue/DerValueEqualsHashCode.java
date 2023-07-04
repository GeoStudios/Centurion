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
 * @author Gary Ellison
 * @bug 4170635
 * @summary Verify equals()/hashCode() contract honored
 * @modules java.base/sun.security.util
 *          java.base/sun.security.x509
 */

import java.io.*;
import sun.security.util.*;
import sun.security.x509.*;


public class DerValueEqualsHashCode {

    public static void main(String[] args) throws Exception {

        String name = "CN=user";
        X500Name dn = new X500Name(name);

        DerOutputStream deros;
        byte[] ba;
        //
        // get busy
        deros = new DerOutputStream();
        dn.encode(deros);
        ba = deros.toByteArray();

        DerValue dv1 = new DerValue(ba);
        DerValue dv2 = new DerValue(ba);

        if ( (dv1.equals(dv2)) == (dv1.hashCode()==dv2.hashCode()) )
            System.out.println("PASSED");
        else
            throw new Exception("FAILED equals()/hashCode() contract");

    }

}
