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

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.OopHandle;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.LiveRegionsProvider;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.MemRegion;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Oop;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.UnknownOopException;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;

public class ZPage extends VMObject implements LiveRegionsProvider {
    private static CIntegerField typeField;
    private static CIntegerField seqnumField;
    private static long virtualFieldOffset;
    private static AddressField topField;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZPage");

        typeField = type.getCIntegerField("_type");
        seqnumField = type.getCIntegerField("_seqnum");
        virtualFieldOffset = type.getField("_virtual").getOffset();
        topField = type.getAddressField("_top");
    }

    public ZPage(Address addr) {
        super(addr);
    }

    private byte type() {
        return typeField.getJByte(addr);
    }

    private int seqnum() {
        return seqnumField.getJInt(addr);
    }

    private ZVirtualMemory virtual() {
        return VMObjectFactory.newObject(ZVirtualMemory.class, addr.addOffsetTo(virtualFieldOffset));
    }

    private Address top() {
        return topField.getValue(addr);
    }

    private boolean is_relocatable() {
        return seqnum() < ZGlobals.ZGlobalSeqNum();
    }

    long start() {
        return virtual().start();
    }

    long size() {
        return virtual().end() - virtual().start();
    }

    long object_alignment_shift() {
        if (type() == ZGlobals.ZPageTypeSmall) {
            return ZGlobals.ZObjectAlignmentSmallShift();
        } else if (type() == ZGlobals.ZPageTypeMedium) {
            return ZGlobals.ZObjectAlignmentMediumShift;
        } else {
            assert(type() == ZGlobals.ZPageTypeLarge);
            return ZGlobals.ZObjectAlignmentLargeShift;
        }
    }

    long objectAlignmentSize() {
        return 1L << object_alignment_shift();
    }

    public boolean isIn(Address addr) {
        long offset = ZAddress.offset(addr);
        // FIXME: it does not consider the sign.
        return (offset >= start()) && (offset < top().asLongValue());
    }

    private long getObjectSize(Address good) {
        OopHandle handle = good.addOffsetToAsOopHandle(0);
        Oop obj = null;

        try {
           obj = VM.getVM().getObjectHeap().newOop(handle);
        } catch (UnknownOopException exp) {
          throw new RuntimeException(" UnknownOopException  " + exp);
        }

        return VM.getVM().alignUp(obj.getObjectSize(), objectAlignmentSize());
    }

    public List<MemRegion> getLiveRegions() {
        Address start = ZAddress.good(ZUtils.longToAddress(start()));

        // Can't convert top() to a "good" address because it might
        // be at the top of the "offset" range, and therefore also
        // looks like one of the color bits. Instead use the "good"
        // address and add the size.
        long size = top().asLongValue() - start();
        Address end = start.addOffsetTo(size);

        return List.of(new MemRegion(start, end));
    }
}
