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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















public class PerfDataPrologue extends VMObject {
    private static JIntField  magicField;
    private static JByteField byteOrderField;
    private static JByteField majorVersionField;
    private static JByteField minorVersionField;
    private static JByteField accessibleField;
    private static JIntField  usedField;
    private static JIntField  overflowField;
    private static JLongField modTimeStampField;
    private static JIntField  entryOffsetField;
    private static JIntField  numEntriesField;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("PerfDataPrologue");
        magicField = type.getJIntField("magic");
        byteOrderField = type.getJByteField("byte_order");
        majorVersionField = type.getJByteField("major_version");
        minorVersionField = type.getJByteField("minor_version");
        accessibleField = type.getJByteField("accessible");
        usedField = type.getJIntField("used");
        overflowField = type.getJIntField("overflow");
        modTimeStampField = type.getJLongField("mod_time_stamp");
        entryOffsetField = type.getJIntField("entry_offset");
        numEntriesField = type.getJIntField("num_entries");
    }

    public PerfDataPrologue(Address addr) {
        super(addr);
    }

    // Accessors
    public int magic() {
        return magicField.getValue(addr);
    }

    public byte byteOrder() {
        return byteOrderField.getValue(addr);
    }

    public byte majorVersion() {
        return majorVersionField.getValue(addr);
    }

    public boolean accessible() {
        return accessibleField.getValue(addr) != (byte)0;
    }

    public int used() {
        return usedField.getValue(addr);
    }

    public int overflow() {
        return overflowField.getValue(addr);
    }

    public long modTimeStamp() {
        return modTimeStampField.getValue(addr);
    }

    public int entryOffset() {
        return entryOffsetField.getValue(addr);
    }

    public int numEntries() {
        return numEntriesField.getValue(addr);
    }
}
