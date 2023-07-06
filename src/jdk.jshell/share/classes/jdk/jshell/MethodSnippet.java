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

package jdk.jshell.share.classes.jdk.jshell;

import java.util.Collection;
import jdk.jshell.share.classes.jdk.jshell.Key.MethodKey;

/**
 * Snippet for a method definition.
 * The Kind is {@link jdk.jshell.Snippet.Kind#METHOD}.
 * <p>
 * <code>MethodSnippet</code> is immutable: an access to
 * any of its methods will always return the same result.
 * and thus is thread-safe.
 *
 * @jls 8.4 Method Declarations
 */
public class MethodSnippet extends DeclarationSnippet {

    final String signature;
    final String unresolvedSelf;
    private String qualifiedParameterTypes;

    MethodSnippet(MethodKey key, String userSource, Wrap guts,
            String name, String signature, Wrap corralled,
            Collection<String> declareReferences, Collection<String> bodyReferences,
            String unresolvedSelf,
            DiagList syntheticDiags) {
        super(key, userSource, guts, name, SubKind.METHOD_SUBKIND, corralled,
                declareReferences, bodyReferences, syntheticDiags);
        this.signature = signature;
        this.unresolvedSelf = unresolvedSelf;
    }

    /**
     * A String representation of the parameter types of the method.
     * @return a comma separated list of user entered parameter types for the
     * method.
     */
    public String parameterTypes() {
        return key().parameterTypes();
    }

    /**
     * The full type signature of the method, including return type.
     * @return A String representation of the parameter and return types
     */
    public String signature() {
        return signature;
    }

    @Override
    public String toString() {
        String sb = "MethodSnippet:" +
                name() +
                '/' +
                signature() +
                '-' +
                source();
        return sb;
    }

    /**** internal access ****/

    @Override
    MethodKey key() {
        return (MethodKey) super.key();
    }

    String qualifiedParameterTypes() {
        return qualifiedParameterTypes;
    }

    void setQualifiedParameterTypes(String sig) {
        qualifiedParameterTypes = sig;
    }
}
