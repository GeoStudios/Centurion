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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;















/**
 * @LastModified: Oct 2017
 */
public final class FlowList {
    private List<InstructionHandle> _elements;

    public FlowList() {
        _elements = null;
    }

    public FlowList(InstructionHandle bh) {
        _elements = new ArrayList<>();
        _elements.add(bh);
    }

    public FlowList(FlowList list) {
        _elements = list._elements;
    }

    public FlowList add(InstructionHandle bh) {
        if (_elements == null) {
            _elements = new ArrayList<>();
        }
        _elements.add(bh);
        return this;
    }

    public FlowList append(FlowList right) {
        if (_elements == null) {
            _elements = right._elements;
        }
        else {
            final List<InstructionHandle> temp = right._elements;
            if (temp != null) {
                final int n = temp.size();
                for (int i = 0; i < n; i++) {
                    _elements.add(temp.get(i));
                }
            }
        }
        return this;
    }

    /**
     * Back patch a flow list. All instruction handles must be branch handles.
     */
    public void backPatch(InstructionHandle target) {
        if (_elements != null) {
            final int n = _elements.size();
            for (int i = 0; i < n; i++) {
                BranchHandle bh = (BranchHandle)_elements.get(i);
                bh.setTarget(target);
            }
            _elements.clear();          // avoid backpatching more than once
        }
    }

    /**
     * Redirect the handles from oldList to newList. "This" flow list
     * is assumed to be relative to oldList.
     */
    public FlowList copyAndRedirect(InstructionList oldList,
        InstructionList newList)
    {
        final FlowList result = new FlowList();
        if (_elements == null) {
            return result;
        }

        final int n = _elements.size();
        final Iterator<InstructionHandle> oldIter = oldList.iterator();
        final Iterator<InstructionHandle> newIter = newList.iterator();

        while (oldIter.hasNext()) {
            final InstructionHandle oldIh = oldIter.next();
            final InstructionHandle newIh = newIter.next();

            for (int i = 0; i < n; i++) {
                if (_elements.get(i) == oldIh) {
                    result.add(newIh);
                }
            }
        }
        return result;
    }
}
