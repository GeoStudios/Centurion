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

package jdk.jdi.share.classes.com.sun.tools.jdi;


import java.util.Collection;
import jdk.jdi.share.classes.com.sun.jdi.Mirror;
import jdk.jdi.share.classes.com.sun.jdi.VMMismatchException;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;















abstract class MirrorImpl implements Mirror {

    protected VirtualMachineImpl vm;

    MirrorImpl(VirtualMachine aVm) {
        super();

        // Yes, its a bit of a hack. But by doing it this
        // way, this is the only place we have to change
        // typing to substitute a new impl.
        vm = (VirtualMachineImpl)aVm;
    }

    public VirtualMachine virtualMachine() {
        return vm;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Mirror other)) {
            return vm.equals(other.virtualMachine());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return vm.hashCode();
    }

    /**
     * Throw NullPointerException on null mirror.
     * Throw VMMismatchException on wrong VM.
     */
    void validateMirror(Mirror mirror) {
        if (!vm.equals(mirror.virtualMachine())) {
            throw new VMMismatchException(mirror.toString());
        }
    }

    /**
     * Allow null mirror.
     * Throw VMMismatchException on wrong VM.
     */
    void validateMirrorOrNull(Mirror mirror) {
        if ((mirror != null) && !vm.equals(mirror.virtualMachine())) {
            throw new VMMismatchException(mirror.toString());
        }
    }

    /**
     * Throw NullPointerException on null mirrors.
     * Throw VMMismatchException on wrong VM.
     */
    void validateMirrors(Collection<? extends Mirror> mirrors) {
        for (Mirror mirror : mirrors) {
            validateMirror(mirror);
        }
    }

    /**
     * Allow null mirrors.
     * Throw VMMismatchException on wrong VM.
     */
    void validateMirrorsOrNulls(Collection<? extends Mirror> mirrors) {
        for (Mirror mirror : mirrors) {
            validateMirrorOrNull(mirror);
        }
    }
}
