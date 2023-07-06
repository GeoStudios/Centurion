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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.z;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;















public class ZForwardingEntry extends VMObject {
    private static Type type;
    private static CIntegerField entryField;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    static private synchronized void initialize(TypeDataBase db) {
        type = db.lookupType("ZForwardingEntry");

        entryField = type.getCIntegerField("_entry");
    }

    public static long getSize() {
        return type.getSize();
    }

    public ZForwardingEntry(Address addr) {
        super(addr);
    }

    public long entry() {
        return entryField.getValue(addr);
    }

    // typedef ZBitField<uint64_t, bool,   0,   1> field_populated
    private boolean fieldPopulatedDecode(long value) {
        long FieldMask = (1L << 1) - 1;
        int FieldShift = 1;
        int ValueShift = 0;
        return (((value >>> FieldShift) & FieldMask) << ValueShift) != 0L;
    }

    // typedef ZBitField<uint64_t, size_t, 1,  45> field_to_offset;
    private long fieldToOffsetDecode(long value) {
        long FieldMask = (1L << 45) - 1;
        int FieldShift = 1;
        int ValueShift = 0;
        return ((value >>> FieldShift) & FieldMask) << ValueShift;
    }

    // typedef ZBitField<uint64_t, size_t, 46, 18> field_from_index;
    private long fieldFromIndexDecode(long value) {
        long FieldMask = (1L << 18) - 1;
        int FieldShift = 46;
        int ValueShift = 0;
        return ((value >>> FieldShift) & FieldMask) << ValueShift;
    }

    public boolean populated() {
        return fieldPopulatedDecode(entry());
    }

    public long toOffset() {
        return fieldToOffsetDecode(entry());
    }

    public long fromIndex() {
        return fieldFromIndexDecode(entry());
    }
}
