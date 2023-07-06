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

package jdk.xml.dom.share.classes.org.w3c.dom.css;

import jdk.xml.dom.share.classes.org.w3c.dom.Element;
import jdk.xml.dom.share.classes.org.w3c.dom.views.AbstractView;

/**
 *  This interface represents a CSS view. The <code>getComputedStyle</code>
 * method provides a read only access to the computed values of an element.
 * <p> The expectation is that an instance of the <code>ViewCSS</code>
 * interface can be obtained by using binding-specific casting methods on an
 * instance of the <code>AbstractView</code> interface.
 * <p> Since a computed style is related to an <code>Element</code> node, if
 * this element is removed from the document, the associated
 * <code>CSSStyleDeclaration</code> and <code>CSSValue</code> related to
 * this declaration are no longer valid.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 */
public interface ViewCSS extends AbstractView {
    /**
     *  This method is used to get the computed style as it is defined in [<a href='http://www.w3.org/TR/1998/REC-CSS2-19980512'>CSS2</a>].
     * @param elt  The element whose style is to be computed. This parameter
     *   cannot be null.
     * @param pseudoElt  The pseudo-element or <code>null</code> if none.
     * @return  The computed style. The <code>CSSStyleDeclaration</code> is
     *   read-only and contains only absolute values.
     */
    CSSStyleDeclaration getComputedStyle(Element elt,
                                                String pseudoElt);

}
