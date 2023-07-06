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

import jdk.xml.dom.share.classes.org.w3c.dom.DOMException;

/**
 *  The <code>CSSValue</code> interface represents a simple or a complex
 * value. A <code>CSSValue</code> object only occurs in a context of a CSS
 * property.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 */
public interface CSSValue {
    // UnitTypes
    /**
     * The value is inherited and the <code>cssText</code> contains "inherit".
     */
    short CSS_INHERIT               = 0;
    /**
     * The value is a primitive value and an instance of the
     * <code>CSSPrimitiveValue</code> interface can be obtained by using
     * binding-specific casting methods on this instance of the
     * <code>CSSValue</code> interface.
     */
    short CSS_PRIMITIVE_VALUE       = 1;
    /**
     * The value is a <code>CSSValue</code> list and an instance of the
     * <code>CSSValueList</code> interface can be obtained by using
     * binding-specific casting methods on this instance of the
     * <code>CSSValue</code> interface.
     */
    short CSS_VALUE_LIST            = 2;
    /**
     * The value is a custom value.
     */
    short CSS_CUSTOM                = 3;

    /**
     *  A string representation of the current value.
     */
    String getCssText();
    /**
     *  A string representation of the current value.
     * @exception DOMException
     *    SYNTAX_ERR: Raised if the specified CSS string value has a syntax
     *   error (according to the attached property) or is unparsable.
     *   <br>INVALID_MODIFICATION_ERR: Raised if the specified CSS string
     *   value represents a different type of values than the values allowed
     *   by the CSS property.
     *   <br> NO_MODIFICATION_ALLOWED_ERR: Raised if this value is readonly.
     */
    void setCssText(String cssText)
                       throws DOMException;

    /**
     *  A code defining the type of the value as defined above.
     */
    short getCssValueType();

}