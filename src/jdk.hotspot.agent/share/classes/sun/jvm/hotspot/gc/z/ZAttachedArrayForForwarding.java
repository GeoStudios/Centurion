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















public class ZAttachedArrayForForwarding extends VMObject {
    private static CIntegerField lengthField;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZAttachedArrayForForwarding");

        lengthField = type.getCIntegerField("_length");
    }

    public ZAttachedArrayForForwarding(Address addr) {
        super(addr);
    }

    public long length() {
        return lengthField.getValue(addr);
    }

    // ObjectT: ZForwarding
    //  ArrayT: ZForwardingEntry
    //
    // template <typename ObjectT, typename ArrayT>
    // inline size_t ZAttachedArray<ObjectT, ArrayT>::object_size()
    private long objectSize() {
        return ZUtils.alignUp(ZForwarding.getSize(), ZForwardingEntry.getSize());
    }

    // ArrayT* operator()(const ObjectT* obj) const
    public ZForwardingEntry get(ZForwarding obj) {
        Address o = obj.getAddress().addOffsetTo(objectSize());
        return VMObjectFactory.newObject(ZForwardingEntry.class, o);
    }
}
