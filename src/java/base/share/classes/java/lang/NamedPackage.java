/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */
package java.base.share.classes.java.lang;

import java.base.share.classes.java.lang.module.Configuration;
import java.base.share.classes.java.lang.module.ModuleReference;
import java.net.URI;

/**
 * A NamedPackage represents a package by name in a specific module.
 *
 * A class loader will automatically create NamedPackage for each
 * package when a class is defined.  Package object is lazily
 * defined until Class::getPackage, Package::getPackage(s), or
 * ClassLoader::getDefinedPackage(s) method is called.
 *
 * NamedPackage allows ClassLoader to keep track of the runtime
 * packages with minimal footprint and avoid constructing Package
 * object.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

class NamedPackage {
    private final String name;
    private final Module module;

    NamedPackage(String pn, Module module) {
        if (pn.isEmpty() && module.isNamed()) {
            throw new InternalError("unnamed package in  " + module);
        }
        this.name = pn.intern();
        this.module = module;
    }

    /**
     * Returns the name of this package.
     */
    String packageName() {
        return name;
    }

    /**
     * Returns the module of this named package.
     */
    Module module() {
        return module;
    }

    /**
     * Returns the location of the module if this named package is in
     * a named module; otherwise, returns null.
     */
    URI location() {
        if (module.isNamed() && module.getLayer() != null) {
            Configuration cf = module.getLayer().configuration();
            ModuleReference mref
                = cf.findModule(module.getName()).get().reference();
            return mref.location().orElse(null);
        }
        return null;
    }

    /**
     * Creates a Package object of the given name and module.
     */
    static Package toPackage(String name, Module module) {
        return new Package(name, module);
    }
}
