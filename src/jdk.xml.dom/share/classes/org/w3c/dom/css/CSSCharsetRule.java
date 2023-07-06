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
 *  The <code>CSSCharsetRule</code> interface represents a @charset rule in a
 * CSS style sheet. The value of the <code>encoding</code> attribute does
 * not affect the encoding of text data in the DOM objects; this encoding is
 * always UTF-16. After a stylesheet is loaded, the value of the
 * <code>encoding</code> attribute is the value found in the
 * <code>@charset</code> rule. If there was no <code>@charset</code> in the
 * original document, then no <code>CSSCharsetRule</code> is created. The
 * value of the <code>encoding</code> attribute may also be used as a hint
 * for the encoding used on serialization of the style sheet.
 * <p> The value of the @charset rule (and therefore of the
 * <code>CSSCharsetRule</code>) may not correspond to the encoding the
 * document actually came in; character encoding information e.g. in an HTTP
 * header, has priority (see CSS document representation) but this is not
 * reflected in the <code>CSSCharsetRule</code>.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 */
public interface CSSCharsetRule extends CSSRule {
    /**
     *  The encoding information used in this <code>@charset</code> rule.
     */
    String getEncoding();
    /**
     *  The encoding information used in this <code>@charset</code> rule.
     * @exception DOMException
     *   SYNTAX_ERR: Raised if the specified encoding value has a syntax error
     *   and is unparsable.
     *   <br>NO_MODIFICATION_ALLOWED_ERR: Raised if this encoding rule is
     *   readonly.
     */
    void setEncoding(String encoding)
                           throws DOMException;

}