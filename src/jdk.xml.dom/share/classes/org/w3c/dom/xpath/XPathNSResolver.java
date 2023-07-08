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

package jdk.xml.dom.share.classes.org.w3c.dom.xpath;

/**
 * The <code>XPathNSResolver</code> interface permit <code>prefix</code>
 * strings in the expression to be properly bound to
 * <code>namespaceURI</code> strings. <code>XPathEvaluator</code> can
 * construct an implementation of <code>XPathNSResolver</code> from a node,
 * or the interface may be implemented by any application.
 * <p>See also the <a href='https://www.w3.org/TR/DOM-Level-3-XPath/'>Document Object Model (DOM) Level 3 XPath Specification</a>.
 */
public interface XPathNSResolver {
    /**
     * Look up the namespace URI associated to the given namespace prefix. The
     * XPath evaluator must never call this with a <code>null</code> or
     * empty argument, because the result of doing this is undefined.
     * @param prefix The prefix to look for.
     * @return Returns the associated namespace URI or <code>null</code> if
     *   none is found.
     */
    String lookupNamespaceURI(String prefix);

}
