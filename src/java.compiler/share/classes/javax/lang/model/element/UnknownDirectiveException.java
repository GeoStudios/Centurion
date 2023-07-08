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

package java.compiler.share.classes.javax.lang.model.element;


import java.compiler.share.classes.javax.lang.model.UnknownEntityException;















/**
 * Indicates that an unknown kind of module directive was encountered.
 * This can occur if the language evolves and new kinds of directives are
 * added to the {@code Directive} hierarchy.  May be thrown by a
 * {@linkplain ModuleElement.DirectiveVisitor directive visitor} to
 * indicate that the visitor was created for a prior version of the language.
 *
 * @see ModuleElement.DirectiveVisitor#visitUnknown
 */
public class UnknownDirectiveException extends UnknownEntityException {

    private static final long serialVersionUID = 269L;

    private final transient ModuleElement.Directive directive;
    private final transient Object parameter;

    /**
     * Creates a new {@code UnknownElementException}.  The {@code p}
     * parameter may be used to pass in an additional argument with
     * information about the context in which the unknown directive was
     * encountered; for example, the visit methods of {@link
     * ModuleElement.DirectiveVisitor DirectiveVisitor} may pass in
     * their additional parameter.
     *
     * @param d the unknown directive, may be {@code null}
     * @param p an additional parameter, may be {@code null}
     */
    public UnknownDirectiveException(ModuleElement.Directive d, Object p) {
        super("Unknown directive: " + d);
        directive = d;
        parameter = p;
    }

    /**
     * Returns the unknown directive.
     * The value may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the unknown directive, or {@code null} if unavailable
     */
    public ModuleElement.Directive getUnknownDirective() {
        return directive;
    }

    /**
     * Returns the additional argument.
     *
     * @return the additional argument, or {@code null} if unavailable
     */
    public Object getArgument() {
        return parameter;
    }
}
