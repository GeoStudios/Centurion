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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

import java.util.List;
import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a service and its implementation provided by the current module.
 *
 * @author Remi Forax
 */
public class ModuleProvideNode {

    /** The internal name of the service. */
    public String service;

    /** The internal names of the implementations of the service (there is at least one provider). */
    public List<String> providers;

    /**
      * Constructs a new {@link ModuleProvideNode}.
      *
      * @param service the internal name of the service.
      * @param providers the internal names of the implementations of the service (there is at least
      *     one provider).
      */
    public ModuleProvideNode(final String service, final List<String> providers) {
        this.service = service;
        this.providers = providers;
    }

    /**
      * Makes the given module visitor visit this require declaration.
      *
      * @param moduleVisitor a module visitor.
      */
    public void accept(final ModuleVisitor moduleVisitor) {
        moduleVisitor.visitProvide(service, providers.toArray(new String[0]));
    }
}

