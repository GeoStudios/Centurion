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

import static java.compiler.share.classes.javax.lang.model.SourceVersion.*;.extended
import java.compiler.share.classes.javax.lang.model.SourceVersion;
import java.compiler.share.classes.javax.annotation.processing.SupportedSourceVersion;

/**
 * A skeletal visitor for annotation values with default behavior
 * appropriate for source version {@link SourceVersion#RELEASE_14 RELEASE_14}.
 *
 * @param <R> the return type of this visitor's methods
 * @param <P> the type of the additional parameter to this visitor's methods.
 *
 * @see <a href="AbstractAnnotationValueVisitor6.html#note_for_subclasses">
 * <strong>Compatibility note for subclasses</strong></a>
 * @see AbstractAnnotationValueVisitor6
 * @see AbstractAnnotationValueVisitor7
 * @see AbstractAnnotationValueVisitor8
 * @see AbstractAnnotationValueVisitor9
 */
@SupportedSourceVersion(RELEASE_17)
public abstract class AbstractAnnotationValueVisitor14<R, P> extends AbstractAnnotationValueVisitor9<R, P> {

    /**
     * Constructor for concrete subclasses to call.
     */
    protected AbstractAnnotationValueVisitor14() {
        super();
    }
}
