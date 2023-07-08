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

package java.desktop.share.classes.javax.print.attribute;

















/**
 * Interface {@code DocAttribute} is a tagging interface which a printing
 * attribute class implements to indicate the attribute denotes a setting for a
 * doc. ("Doc" is a short, easy-to-pronounce term that means "a piece of print
 * data.") The client may include a {@code DocAttribute} in a {@code Doc}'s
 * attribute set to specify a characteristic of that doc. If an attribute
 * implements {@link PrintRequestAttribute PrintRequestAttribute} as well as
 * {@code DocAttribute}, the client may include the attribute in a attribute set
 * which specifies a print job to specify a characteristic for all the docs in
 * that job.
 *
 * @see DocAttributeSet
 * @see PrintRequestAttributeSet
 */
public interface DocAttribute extends Attribute {
}
