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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.g1;

import java.io.PrintStream;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.OopHandle;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.CompactibleSpace;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.LiveRegionsProvider;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.MemRegion;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for HeapRegion. Currently we don't actually include
// any of its fields but only iterate over it.

public class HeapRegion extends CompactibleSpace implements LiveRegionsProvider {
    private static AddressField bottomField;
    static private AddressField topField;
    private static AddressField endField;
    private static AddressField compactionTopField;

    static private CIntegerField grainBytesField;
    private static long typeFieldOffset;
    private static long pointerSize;

    private final HeapRegionType type;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegion");

        bottomField = type.getAddressField("_bottom");
        topField = type.getAddressField("_top");
        endField = type.getAddressField("_end");
        compactionTopField = type.getAddressField("_compaction_top");

        grainBytesField = type.getCIntegerField("GrainBytes");
        typeFieldOffset = type.getField("_type").getOffset();

        pointerSize = db.lookupType("HeapRegion*").getSize();
    }

    static public long grainBytes() {
        return grainBytesField.getValue();
    }

    public HeapRegion(Address addr) {
        super(addr);
        Address typeAddr = (addr instanceof OopHandle) ? addr.addOffsetToAsOopHandle(typeFieldOffset)
                                                       : addr.addOffsetTo(typeFieldOffset);
        type = VMObjectFactory.newObject(HeapRegionType.class, typeAddr);
    }

    public Address bottom()        { return bottomField.getValue(addr); }
    public Address top()           { return topField.getValue(addr); }
    public Address end()           { return endField.getValue(addr); }

    public Address compactionTop() { return compactionTopField.getValue(addr); }

    @Override
    public List<MemRegion> getLiveRegions() {
        List<MemRegion> res = new ArrayList<>();
        res.add(new MemRegion(bottom(), top()));
        return res;
    }

    /** Returns a subregion of the space containing all the objects in
        the space. */
    public MemRegion usedRegion() {
        return new MemRegion(bottom(), end());
    }

    public long used() {
        return top().minus(bottom());
    }

    public long free() {
        return end().minus(top());
    }

    public boolean isFree() {
        return type.isFree();
    }

    public boolean isYoung() {
        return type.isYoung();
    }

    public boolean isHumongous() {
        return type.isHumongous();
    }

    public boolean isPinned() {
        return type.isPinned();
    }

    public boolean isOld() {
        return type.isOld();
    }

    public static long getPointerSize() {
        return pointerSize;
    }

    public void printOn(PrintStream tty) {
        tty.print("Region: " + bottom() + "," + top() + "," + end());
        tty.println(":" + type.typeAnnotation());
    }
}
