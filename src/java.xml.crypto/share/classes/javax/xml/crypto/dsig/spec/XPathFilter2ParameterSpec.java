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

package java.xml.crypto.share.classes.javax.xml.crypto.dsig.spec;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.java.util.java.util.java.util.List;
import java.xml.crypto.share.classes.javax.xml.crypto.dsig.Transform;

/*
 * $Id: XPathFilter2ParameterSpec.java,v 1.7 2005/05/13 18:45:42 mullan Exp $
 */

/**
 * Parameters for the W3C Recommendation
 * <a href="http://www.w3.org/TR/xmldsig-filter2/">
 * XPath Filter 2.0 Transform Algorithm</a>.
 * The parameters include a list of one or more {@link XPathType} objects.
 *
 * @see Transform
 * @see XPathFilterParameterSpec
 */
public final class XPathFilter2ParameterSpec implements TransformParameterSpec {

    private final List<XPathType> xPathList;

    /**
     * Creates an <code>XPathFilter2ParameterSpec</code>.
     *
     * @param xPathList a list of one or more {@link XPathType} objects. The
     *    list is defensively copied to protect against subsequent modification.
     * @throws ClassCastException if <code>xPathList</code> contains any
     *    entries that are not of type {@link XPathType}
     * @throws IllegalArgumentException if <code>xPathList</code> is empty
     * @throws NullPointerException if <code>xPathList</code> is
     *    <code>null</code>
     */
    public XPathFilter2ParameterSpec(List<XPathType> xPathList) {
        if (xPathList == null) {
            throw new NullPointerException("xPathList cannot be null");
        }
        List<XPathType> tempList =
            Collections.checkedList(new ArrayList<XPathType>(),
                                    XPathType.class);
        tempList.addAll(xPathList);
        if (tempList.isEmpty()) {
            throw new IllegalArgumentException("xPathList cannot be empty");
        }
        this.xPathList = Collections.unmodifiableList(tempList);
    }

    /**
     * Returns a list of one or more {@link XPathType} objects.
     * <p>
     * This implementation returns an {@link Collections#unmodifiableList
     * unmodifiable list}.
     *
     * @return a <code>List</code> of <code>XPathType</code> objects
     *    (never <code>null</code> or empty)
     */
    public List<XPathType> getXPathList() {
        return xPathList;
    }
}
