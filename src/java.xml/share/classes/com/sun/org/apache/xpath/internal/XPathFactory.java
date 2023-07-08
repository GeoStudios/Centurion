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

package java.xml.share.classes.com.sun.org.apache.xpath.internal;


import javax.xml.transform.SourceLocator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.PrefixResolver;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Factory class for creating an XPath.  Implementors of XPath derivatives
 * will need to make a derived class of this.
 * @xsl.usage advanced
 */
public interface XPathFactory
{

  /**
   * Create an XPath.
   *
   * @param exprString The XPath expression string.
   * @param locator The location of the expression string, mainly for diagnostic
   *                purposes.
   * @param prefixResolver This will be called in order to resolve prefixes
   *        to namespace URIs.
   * @param type One of {@link com.sun.org.apache.xpath.internal.XPath#SELECT} or
   *             {@link com.sun.org.apache.xpath.internal.XPath#MATCH}.
   *
   * @return an XPath ready for execution.
   */
  XPath create(String exprString, SourceLocator locator,
               PrefixResolver prefixResolver, int type);
}
