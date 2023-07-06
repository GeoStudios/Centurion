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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shenandoah;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.CollectedHeap;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.CollectedHeapName;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.shared.LiveRegionsClosure;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.MemRegion;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.BitMapInterface;
import java.io.PrintStream;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;

public class ShenandoahHeap extends CollectedHeap {
    static private CIntegerField numRegions;
    static private CIntegerField used;
    static private CIntegerField committed;
    static private AddressField  regions;
    static private CIntegerField logMinObjAlignmentInBytes;

    static private long regionPtrFieldSize;
    static {
        VM.registerVMInitializedObserver(new Observer() {
            public void update(Observable o, Object data) {
                initialize(VM.getVM().getTypeDataBase());
            }
        });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ShenandoahHeap");
        numRegions = type.getCIntegerField("_num_regions");
        used = type.getCIntegerField("_used");
        committed = type.getCIntegerField("_committed");
        regions = type.getAddressField("_regions");
        logMinObjAlignmentInBytes = type.getCIntegerField("_log_min_obj_alignment_in_bytes");

        Type regionPtrType = db.lookupType("ShenandoahHeapRegion*");
        regionPtrFieldSize = regionPtrType.getSize();
    }

    public ShenandoahHeap(Address addr) {
        super(addr);
    }

    @Override
    public CollectedHeapName kind() {
        return CollectedHeapName.SHENANDOAH;
    }

    public long numOfRegions() {
        return numRegions.getValue(addr);
    }

    @Override
    public long capacity() {
        return numOfRegions() * ShenandoahHeapRegion.regionSizeBytes();
    }

    @Override
    public long used() {
        return used.getValue(addr);
    }

    public long committed() {
        return committed.getValue(addr);
    }

    public int getLogMinObjAlignmentInBytes() {
        return logMinObjAlignmentInBytes.getJInt(addr);
    }

    public ShenandoahHeapRegion getRegion(long index) {
        if (index < numOfRegions()) {
            Address arrayAddr = regions.getValue(addr);
            Address regAddr = arrayAddr.getAddressAt(index * regionPtrFieldSize);
            ShenandoahHeapRegion region = VMObjectFactory.newObject(ShenandoahHeapRegion.class, regAddr);
            region.setHeap(this);
            return region;
        }
        return null;
    }

    public ShenandoahHeapRegion regionAtOffset(long offset) {
        long index = offset >>> ShenandoahHeapRegion.regionSizeBytesShift();
        if (index < 0 || index >= numOfRegions()) {
            throw new RuntimeException("Invalid offset: " + offset);
        }
        return getRegion(index);
    }

    @Override
    public void liveRegionsIterate(LiveRegionsClosure closure) {
        for (long index = 0; index < numOfRegions(); index ++) {
            ShenandoahHeapRegion region = getRegion(index);
            closure.doLiveRegions(region);
        }
    }

    @Override
    public void printOn(PrintStream tty) {
        MemRegion mr = reservedRegion();
        tty.print("Shenandoah heap");
        tty.print(" [" + mr.start() + ", " + mr.end() + "]");
        tty.println(" region size " + ShenandoahHeapRegion.regionSizeBytes() / 1024 + " K");
    }

    @Override
    public BitMapInterface createBitMap(long bits) {
        return new ShenandoahBitMap(this);
    }

}
