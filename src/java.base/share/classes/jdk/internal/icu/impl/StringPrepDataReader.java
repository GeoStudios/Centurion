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

package java.base.share.classes.jdk.internal.icu.impl;


import java.io.DataInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.base.share.classes.jdk.internal.icu.impl.ICUBinary;















// CHANGELOG
//      2005-05-19 Edward Wang
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/impl/StringPrepDataReader.java
//          - move from package com.ibm.icu.impl to package sun.net.idn
//





/**
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public final class StringPrepDataReader implements ICUBinary.Authenticate {

   /**
    * <p>private constructor.</p>
    * @param inputStream ICU uprop.dat file input stream
    * @exception IOException throw if data file fails authentication
    * @draft 2.1
    */
    public StringPrepDataReader(InputStream inputStream)
                                        throws IOException{

        unicodeVersion = ICUBinary.readHeader(inputStream, DATA_FORMAT_ID, this);


        dataInputStream = new DataInputStream(inputStream);

    }

    public void read(byte[] idnaBytes,
                        char[] mappingTable)
                        throws IOException{

        // Read the bytes that make up the idnaTrie
        dataInputStream.read(idnaBytes);

        // Read the extra data
        for(int i=0;i<mappingTable.length;i++){
            mappingTable[i]=dataInputStream.readChar();
        }
    }

    public byte[] getDataFormatVersion(){
        return DATA_FORMAT_VERSION;
    }

    public boolean isDataVersionAcceptable(byte[] version){
        return version[0] == DATA_FORMAT_VERSION[0]
               && version[2] == DATA_FORMAT_VERSION[2]
               && version[3] == DATA_FORMAT_VERSION[3];
    }
    public int[] readIndexes(int length) throws IOException{
        int[] indexes = new int[length];
        // Read the indexes
        for (int i = 0; i <length ; i++) {
             indexes[i] = dataInputStream.readInt();
        }
        return indexes;
    }

    public byte[] getUnicodeVersion(){
        return unicodeVersion;
    }
    // private data members -------------------------------------------------


    /**
     * ICU data file input stream
     */
    private final DataInputStream dataInputStream;
    private final byte[] unicodeVersion;
    /**
     * File format version that this class understands.
     * No guarantees are made if a older version is used
     * see store.c of gennorm for more information and values
     */
    ///* dataFormat="SPRP" 0x53, 0x50, 0x52, 0x50  */
    private static final byte[] DATA_FORMAT_ID = {(byte)0x53, (byte)0x50,
                                                    (byte)0x52, (byte)0x50};
    private static final byte[] DATA_FORMAT_VERSION = {(byte)0x3, (byte)0x2,
                                                        (byte)0x5, (byte)0x2};

}
