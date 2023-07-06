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

package org.openjdk.bench.javax.xml;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.Map;














public class STAX extends AbstractXMLMicro {

    /** Live data */
    public Map<String, LinkedList<String>> liveData;

    @Setup(Level.Iteration)
    public void setupLiveData() {
        Map<String, LinkedList<String>> map = new HashMap<>();
        // Somewhere around 100 MB live, but fragmented to start with.
        for (int i = 0; i < 1000; i++) {
            LinkedList<String> list = new LinkedList<>();
            String key = "Dummy linked list " + i;
            list.add(key);
            for (int j = 0; j < 1000; j++) {
                list.add("Dummy string " + i + "." + j);
            }
            map.put(key, list);
        }
        // thread safe, will only be one
        liveData = map;
    }

    @TearDown(Level.Iteration)
    public void teardownLiveData() {
        liveData = null;
    }

    @Benchmark
    public int testParse() throws Exception {
        int intDummy = 0;
        byte[] bytes = getFileBytesFromResource(doc);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLStreamReader parser = factory.createXMLStreamReader(bais);
        int acc;
        do {
            acc = parser.next();
            intDummy += acc;
        } while (acc != XMLStreamConstants.END_DOCUMENT);
        return intDummy;
    }

}