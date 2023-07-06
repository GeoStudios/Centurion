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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSImplementationImpl;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementation;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMImplementationjava.util.java.util.java.util.List;

/**
 * Allows to retrieve <code>XSImplementation</code>, DOM Level 3 Core and LS implementations
 * and PSVI implementation.
 * <p>See also the
 * <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407/core.html#DOMImplementationSource'>
 * Document Object Model (DOM) Level 3 Core Specification</a>.
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public class DOMXSImplementationSourceImpl
    extends DOMImplementationSourceImpl {

    /**
     * A method to request a DOM implementation.
     * @param features A string that specifies which features are required.
     *   This is a space separated list in which each feature is specified
     *   by its name optionally followed by a space and a version number.
     *   This is something like: "XML 1.0 Traversal Events 2.0"
     * @return An implementation that has the desired features, or
     *   <code>null</code> if this source has none.
     */
    public DOMImplementation getDOMImplementation(String features) {
        DOMImplementation impl = super.getDOMImplementation(features);
        if (impl != null){
            return impl;
        }
        // if not try the PSVIDOMImplementation
        impl = PSVIDOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }
        // if not try the XSImplementation
        impl = XSImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            return impl;
        }

        return null;
    }

    /**
     * A method to request a list of DOM implementations that support the
     * specified features and versions, as specified in .
     * @param features A string that specifies which features and versions
     *   are required. This is a space separated list in which each feature
     *   is specified by its name optionally followed by a space and a
     *   version number. This is something like: "XML 3.0 Traversal +Events
     *   2.0"
     * @return A list of DOM implementations that support the desired
     *   features.
     */
    public DOMImplementationList getDOMImplementationList(String features) {
        final List<DOMImplementation> implementations = new ArrayList<>();

        // first check whether the CoreDOMImplementation would do
        DOMImplementationList list = super.getDOMImplementationList(features);
        //Add core DOMImplementations
        for (int i=0; i < list.getLength(); i++ ) {
            implementations.add(list.item(i));
        }

        DOMImplementation impl = PSVIDOMImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }

        impl = XSImplementationImpl.getDOMImplementation();
        if (testImpl(impl, features)) {
            implementations.add(impl);
        }
        return new DOMImplementationListImpl(implementations);
    }
}