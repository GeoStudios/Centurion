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

package java.xml.crypto.share.classes.javax.xml.crypto;


import java.security.spec.AlgorithmParameterSpec;















/*
 * $Id: AlgorithmMethod.java,v 1.4 2005/05/10 15:47:41 mullan Exp $
 */



/**
 * An abstract representation of an algorithm defined in the XML Security
 * specifications. Subclasses represent specific types of XML security
 * algorithms, such as a {@link javax.xml.crypto.dsig.Transform}.
 *
 */
public interface AlgorithmMethod {

    /**
     * Returns the algorithm URI of this <code>AlgorithmMethod</code>.
     *
     * @return the algorithm URI of this <code>AlgorithmMethod</code>
     */
    String getAlgorithm();

    /**
     * Returns the algorithm parameters of this <code>AlgorithmMethod</code>.
     *
     * @return the algorithm parameters of this <code>AlgorithmMethod</code>.
     *    Returns <code>null</code> if this <code>AlgorithmMethod</code> does
     *    not require parameters and they are not specified.
     */
    AlgorithmParameterSpec getParameterSpec();
}
