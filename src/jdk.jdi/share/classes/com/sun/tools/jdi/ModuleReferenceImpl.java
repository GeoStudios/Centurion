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

import jdk.jdi.share.classes.com.sun.jdi.ClassLoaderReference;
import jdk.jdi.share.classes.com.sun.jdi.ModuleReference;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;

class ModuleReferenceImpl extends ObjectReferenceImpl implements ModuleReference {

    protected ModuleReferenceImpl(VirtualMachine aVm, long aRef) {
        super(aVm, aRef);
    }

    protected String description() {
        return "ModuleReference " + ref();
    }

    private String name;
    private ClassLoaderReference classLoader;

    private boolean cachedName = false;
    private boolean cachedClassLoader = false;

    public synchronized String name() {
        if (cachedName) {
            return name;
        }
        try {
            name = JDWP.ModuleReference.Name.process(this.vm, this).name;
            if (name != null && name.length() == 0) {
                // The JDWP returns empty name for unnamed modules
                name = null;
            }
            cachedName = true;
        } catch (JDWPException ex) {
            throw ex.toJDIException();
        }
        return name;
    }

    public synchronized ClassLoaderReference classLoader() {
        if (cachedClassLoader) {
            return classLoader;
        }
        try {
            classLoader = JDWP.ModuleReference.ClassLoader.
                process(this.vm, this).classLoader;
            cachedClassLoader = true;
        } catch (JDWPException ex) {
            throw ex.toJDIException();
        }
        return classLoader;
    }
}
