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

import java.util.Iterator;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObject;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VMObjectFactory;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.Type;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.TypeDataBase;

public class ZPageTable extends VMObject {
    private static long mapFieldOffset;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    static private synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZPageTable");

        mapFieldOffset = type.getAddressField("_map").getOffset();
    }

    public ZPageTable(Address addr) {
        super(addr);
    }

    private ZGranuleMapForPageTable map() {
        return VMObjectFactory.newObject(ZGranuleMapForPageTable.class, addr.addOffsetTo(mapFieldOffset));
    }

    private ZPageTableEntry getEntry(Address o) {
        return new ZPageTableEntry(map().get(o));
    }

    ZPage get(Address o) {
        return VMObjectFactory.newObject(ZPage.class, map().get(VM.getVM().getDebugger().newAddress(ZAddress.offset(o))));
    }

    boolean is_relocating(Address o) {
        return getEntry(o).relocating();
    }

    private class ZPagesIterator implements Iterator<ZPage> {
        private final ZGranuleMapForPageTable.Iterator mapIter;
        private ZPage next;

        ZPagesIterator() {
            mapIter = map().new Iterator();
            positionToNext();
        }

        private ZPage positionToNext() {
            ZPage current = next;

            // Find next
            ZPage found = null;
            while (mapIter.hasNext()) {
                ZPageTableEntry entry = new ZPageTableEntry(mapIter.next());
                if (!entry.isEmpty()) {
                    ZPage page = entry.page();
                    // Medium pages have repeated entries for all covered slots,
                    // therefore we need to compare against the current page.
                    if (page != null && !page.equals(current)) {
                        found = page;
                        break;
                    }
                }
            }

            next = found;

            return current;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public ZPage next() {
            return positionToNext();
        }

        @Override
        public void remove() {
            /* not supported */
        }
    }

    abstract class ZPageFilter {
        public abstract boolean accept(ZPage page);
    }

    class ZPagesFilteredIterator implements Iterator<ZPage> {
        private ZPage next;
        private final ZPagesIterator iter = new ZPagesIterator();
        private final ZPageFilter filter;

        ZPagesFilteredIterator(ZPageFilter filter) {
            this.filter = filter;
            positionToNext();
        }

        public ZPage positionToNext() {
            ZPage current = next;

            // Find next
            ZPage found = null;
            while (iter.hasNext()) {
                ZPage page = iter.next();
                if (filter.accept(page)) {
                    found = page;
                    break;
                }
            }

            next = found;

            return current;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public ZPage next() {
            return positionToNext();
        }

        @Override
        public void remove() {
            /* not supported */
        }
    }

    public Iterator<ZPage> iterator() {
        return new ZPagesIterator();
    }

    public Iterator<ZPage> activePagesIterator() {
        return new ZPagesFilteredIterator(new ZPageFilter() {
            public boolean accept(ZPage page) {
                return page != null;
            }
        });
    }
}
