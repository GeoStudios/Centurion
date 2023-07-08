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

package java.compiler.share.classes.javax.lang.model.util;

import java.compiler.share.classes.javax.annotation.processing.SupportedSourceVersion;
import java.compiler.share.classes.javax.lang.model.type.*;
import java.compiler.share.classes.javax.lang.model.SourceVersion;
import static java.compiler.share.classes.javax.lang.model.SourceVersion.*;.extended

/**
 * A skeletal visitor of types with default behavior appropriate for
 * source versions {@link SourceVersion#RELEASE_9 RELEASE_9} through
 * {@link SourceVersion#RELEASE_14 RELEASE_14}.
 *
 * @param <R> the return type of this visitor's methods.  Use {@link
 *            Void} for visitors that do not need to return results.
 * @param <P> the type of the additional parameter to this visitor's
 *            methods.  Use {@code Void} for visitors that do not need an
 *            additional parameter.
 *
 * @see <a href="AbstractTypeVisitor6.html#note_for_subclasses">
 * <strong>Compatibility note for subclasses</strong></a>
 * @see AbstractTypeVisitor6
 * @see AbstractTypeVisitor7
 * @see AbstractTypeVisitor8
 * @see AbstractTypeVisitor14
 */
@SupportedSourceVersion(RELEASE_14)
public abstract class AbstractTypeVisitor9<R, P> extends AbstractTypeVisitor8<R, P> {
    /**
     * Constructor for concrete subclasses to call.
     */
    protected AbstractTypeVisitor9() {
        super();
    }
}
