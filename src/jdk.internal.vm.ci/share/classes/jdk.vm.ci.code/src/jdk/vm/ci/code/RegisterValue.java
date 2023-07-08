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

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.code.src.jdk.vm.ci.code;


import jdk.internal.vm.ci.share.classes.jdk.vm.ci.code.src.jdk.vm.ci.meta.AllocatableValue;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.code.src.jdk.vm.ci.meta.ValueKind;















/**
 * Denotes a register that stores a value of a fixed kind.
 */
public final class RegisterValue extends AllocatableValue {

    private final Register reg;

    protected RegisterValue(ValueKind<?> kind, Register register) {
        super(kind);
        this.reg = register;
    }

    @Override
    public String toString() {
        return getRegister().name + getKindSuffix();
    }

    /**
     * @return the register that contains the value
     */
    public Register getRegister() {
        return reg;
    }

    @Override
    public int hashCode() {
        return 29 * super.hashCode() + reg.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RegisterValue other) {
            return super.equals(obj) && reg.equals(other.reg);
        }
        return false;
    }
}
