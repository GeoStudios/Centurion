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
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.AddressField;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;

public class ZRelocate  extends VMObject {

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZRelocate");
    }

    public ZRelocate(Address addr) {
        super(addr);
    }

    private long forwardingIndex(ZForwarding forwarding, Address from) {
        long fromOffset = ZAddress.offset(from);
        return (fromOffset - forwarding.start()) >>> forwarding.objectAlignmentShift();
    }

    private Address forwardingFind(ZForwarding forwarding, Address from) {
        long fromIndex = forwardingIndex(forwarding, from);
        ZForwardingEntry entry = forwarding.find(fromIndex);
        return entry.populated() ? ZAddress.good(VM.getVM().getDebugger().newAddress(entry.toOffset())) : null;
    }

    public Address forwardObject(ZForwarding forwarding, Address from) {
        return forwardingFind(forwarding, from);
    }

    public Address relocateObject(ZForwarding forwarding, Address o) {
        Address toAddr = forwardingFind(forwarding, o);
        if (toAddr != null) {
            // Already relocated.
            return toAddr;
        } else {
            // Return original address because it is not yet relocated.
            return o;
        }
    }
}
