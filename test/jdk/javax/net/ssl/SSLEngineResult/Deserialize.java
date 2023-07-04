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
 * @bug 5013482
 * @summary Deserialization of enums in javax.net.ssl.SSLEngineResult fails
 *
 * @author A Russian Test Team member (Yuri Gayevski?)
 * @author Brad Wetmore made modifications to fit in reg test suite.
 */

import java.io.*;
import javax.net.ssl.*;

public class Deserialize {

    public static void main(String[] args) throws Exception {

        System.out.println("Serialize to file foo ...");

        SSLEngineResult.Status obj = SSLEngineResult.Status.OK;

        File file = new File("deserial-test-file");

        ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file));
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        System.out.println("Done");

        System.out.println("Deserialize from file foo...");
        ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(file));

        obj = (SSLEngineResult.Status) ois.readObject();

        ois.close();
        System.out.println("Passed.");
    }
}
