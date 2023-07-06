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

package java.xml.share.classes.javax.xml.validation;

















/**
 * <p>Factory that creates {@link SchemaFactory}.</p>
 *
 * <p><b>DO NOT USE THIS CLASS</b></p>
 *
 * <p>
 * This class was introduced as a part of an early proposal during the
 * JSR-206 standardization process. The proposal was eventually abandoned
 * but this class accidentally remained in the source tree, and made its
 * way into the final version.
 * </p><p>
 * This class does not participate in any JAXP 1.3 or JAXP 1.4 processing.
 * It must not be used by users or JAXP implementations.
 * </p>
 *
 */
public abstract class SchemaFactoryLoader {

    /**
     * A do-nothing constructor.
     */
    protected SchemaFactoryLoader() {
    }

    /**
     * Creates a new {@link SchemaFactory} object for the specified
     * schema language.
     *
     * @param schemaLanguage
     *      See <a href="SchemaFactory.html#schemaLanguage">
     *      the list of available schema languages</a>.
     *
     * @throws NullPointerException
     *      If the <code>schemaLanguage</code> parameter is null.
     *
     * @return <code>null</code> if the callee fails to create one.
     */
    public abstract SchemaFactory newFactory(String schemaLanguage);
}
