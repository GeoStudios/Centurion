/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.org.objectweb.asm.tree;

import java.util.List;
import jdk.internal.org.objectweb.asm.ModuleVisitor;

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

