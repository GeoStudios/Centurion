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
package jdk.vm.ci.code.site;

import java.util.Objects;

/**
 * Associates arbitrary information with a position in machine code. For example, HotSpot specific
 * code in a compiler backend may use this to denote the position of a safepoint, exception handler
 * entry point, verified entry point etc.
 */
public final class Mark extends Site {

    /**
     * An object denoting extra semantic information about the machine code position of this mark.
     */
    public final Object id;

    /**
     * Creates a mark that associates {@code id} with the machine code position {@code pcOffset}.
     *
     * @param pcOffset
     * @param id
     */
    public Mark(int pcOffset, Object id) {
        super(pcOffset);
        this.id = id;
    }

    @Override
    public String toString() {
        if (id == null) {
            return String.format("%d[<mark>]", pcOffset);
        } else if (id instanceof Integer) {
            return String.format("%d[<mark with id %s>]", pcOffset, Integer.toHexString((Integer) id));
        } else {
            return String.format("%d[<mark with id %s>]", pcOffset, id);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Mark that) {
            return this.pcOffset == that.pcOffset && Objects.equals(this.id, that.id);
        }
        return false;
    }
}
