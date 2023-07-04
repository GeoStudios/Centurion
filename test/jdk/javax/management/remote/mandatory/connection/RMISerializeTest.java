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
 * @summary Tests to serialize RMIConnector
 * @bug 5032052
 * @author Shanliang JIANG
 *
 * @run clean RMISerializeTest
 * @run build RMISerializeTest
 * @run main RMISerializeTest
 */

// java imports
//
import java.io.*;

// JMX imports
//
import javax.management.remote.*;
import javax.management.remote.rmi.*;

public class RMISerializeTest {
    public static void main(String[] args) throws Exception {
        System.out.println(">>> Tests to serialize RMIConnector.");

        JMXServiceURL url = new JMXServiceURL("rmi", null, 1111);
        RMIConnector rc1 = new RMIConnector(url, null);

        // serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rc1);

        byte[] bs = baos.toByteArray();

        // deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(bs);
        ObjectInputStream ois = new ObjectInputStream(bais);
        RMIConnector rc2 = (RMIConnector)ois.readObject();

        try {
            rc2.close();
        } catch (NullPointerException npe) {
            System.out.println(">>> Test failed.");
            npe.printStackTrace(System.out);
            System.exit(1);
        } catch (Exception e) {
            // OK.
        }
    }
}
