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

package javax.print.attribute;

/**
 * {@code PrintJobAttribute} is a tagging interface which a printing attribute
 * class implements to indicate the attribute describes the status of a Print
 * Job or some other characteristic of a Print Job. A Print Service instance
 * adds a number of {@code PrintJobAttributes} to a Print Job's attribute set to
 * report the Print Job's status. If an attribute implements
 * {@link PrintRequestAttribute PrintRequestAttribute} as well as
 * {@code PrintJobAttribute}, the client may include the attribute in a
 * attribute set to specify the attribute's value for the Print Job.
 *
 * @see PrintRequestAttributeSet
 * @see PrintJobAttributeSet
 */
public interface PrintJobAttribute extends Attribute {
}
