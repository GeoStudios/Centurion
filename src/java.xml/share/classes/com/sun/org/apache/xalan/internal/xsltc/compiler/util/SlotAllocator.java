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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Type;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
final class SlotAllocator {

    private int   _firstAvailableSlot;
    private int   _size = 8;
    private int   _free = 0;
    private int[] _slotsTaken = new int[_size];

    public void initialize(LocalVariableGen[] vars) {
        final int length = vars.length;
        int slot = 0, size, index;

        for (int i = 0; i < length; i++) {
            size  = vars[i].getType().getSize();
            index = vars[i].getIndex();
            slot  = Math.max(slot, index + size);
        }
        _firstAvailableSlot = slot;
    }

    public int allocateSlot(Type type) {
        final int size = type.getSize();
        final int limit = _free;
        int slot = _firstAvailableSlot, where = 0;

        if (_free + size > _size) {
            final int[] array = new int[_size *= 2];
            if (limit >= 0) System.arraycopy(_slotsTaken, 0, array, 0, limit);
            _slotsTaken = array;
        }

        while (where < limit) {
            if (slot + size <= _slotsTaken[where]) {
                // insert
                for (int j = limit - 1; j >= where; j--)
                    _slotsTaken[j + size] = _slotsTaken[j];
                break;
            }
            else {
                slot = _slotsTaken[where++] + 1;
            }
        }

        for (int j = 0; j < size; j++)
            _slotsTaken[where + j] = slot + j;

        _free += size;
        return slot;
    }

    public void releaseSlot(LocalVariableGen lvg) {
        final int size = lvg.getType().getSize();
        final int slot = lvg.getIndex();
        final int limit = _free;

        for (int i = 0; i < limit; i++) {
            if (_slotsTaken[i] == slot) {
                int j = i + size;
                while (j < limit) {
                    _slotsTaken[i++] = _slotsTaken[j++];
                }
                _free -= size;
                return;
            }
        }
        String state = "Variable slot allocation error"+
                       "(size="+size+", slot="+slot+", limit="+limit+")";
        ErrorMsg err = new ErrorMsg(ErrorMsg.INTERNAL_ERR, state);
        throw new Error(err.toString());
    }
}
