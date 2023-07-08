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

import java.util.Iterator;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.CIntegerField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for HeapRegionManager.

public class HeapRegionManager extends VMObject {
    // G1HeapRegionTable _regions
    static private long regionsFieldOffset;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegionManager");

        regionsFieldOffset = type.getField("_regions").getOffset();
    }

    private G1HeapRegionTable regions() {
        Address regionsAddr = addr.addOffsetTo(regionsFieldOffset);
        return VMObjectFactory.newObject(G1HeapRegionTable.class,
                                                             regionsAddr);
    }

    public long capacity() {
        return length() * HeapRegion.grainBytes();
    }

    public long length() {
        return regions().length();
    }

    public Iterator<HeapRegion> heapRegionIterator() {
        return regions().heapRegionIterator(length());
    }

    public HeapRegionManager(Address addr) {
        super(addr);
    }

    public HeapRegion getByAddress(Address addr) {
      return regions().getByAddress(addr);
    }
}
