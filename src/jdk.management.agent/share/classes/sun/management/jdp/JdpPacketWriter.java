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

package jdk.management.agent.share.classes.sun.management.jdp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;

/**
 * JdpPacketWriter responsible for writing a packet
 * <p>This class assembles a set of key/value pairs to valid JDP packet,
 * ready to be sent across a Net</p>
 */
public final class JdpPacketWriter {

    private final ByteArrayOutputStream baos;
    private final DataOutputStream pkt;

    /**
     * Create a JDP packet, add mandatory magic and version headers
     *
     * @throws IOException
     */
    public JdpPacketWriter()
            throws IOException {
        baos = new ByteArrayOutputStream();
        pkt = new DataOutputStream(baos);

        pkt.writeInt(JdpGenericPacket.getMagic());
        pkt.writeShort(JdpGenericPacket.getVersion());
    }

    /**
     * Put string entry to packet
     *
     * @param entry - string to put (utf-8 encoded)
     * @throws IOException
     */
    public void addEntry(String entry)
            throws IOException {
        /* DataOutputStream.writeUTF() do essentially
         *  the same as:
         *    pkt.writeShort(entry.getBytes("UTF-8").length);
         *    pkt.write(entry.getBytes("UTF-8"));
         */
        pkt.writeUTF(entry);
    }

    /**
     * Put key/value pair to packet
     *
     * @param key - key to put (utf-8 encoded)
     * @param val - value to put (utf-8 encoded)
     * @throws IOException
     */
    public void addEntry(String key, String val)
            throws IOException {
        /* Silently skip key if value is null.
         * We don't need to distinguish between key missing
         * and key has no value cases
         */
        if (val != null) {
            addEntry(key);
            addEntry(val);
        }
    }

    /**
     * Return assembled packet as a byte array
     *
     * @return packet bytes
     */
    public byte[] getPacketBytes() {
        return baos.toByteArray();
    }
}