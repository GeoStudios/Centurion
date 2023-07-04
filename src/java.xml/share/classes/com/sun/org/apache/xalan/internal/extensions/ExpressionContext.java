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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.extensions;

import javax.xml.transform.ErrorListener;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

/**
 * An object that implements this interface can supply
 * information about the current XPath expression context.
 */
public interface ExpressionContext
{

  /**
   * Get the current context node.
   * @return The current context node.
   */
  Node getContextNode();

  /**
   * Get the current context node list.
   * @return An iterator for the current context list, as
   * defined in XSLT.
   */
  NodeIterator getContextNodes();

  /**
   * Get the error listener.
   * @return The registered error listener.
   */
  ErrorListener getErrorListener();

  /**
   * Get the value of a node as a number.
   * @param n Node to be converted to a number.  May be null.
   * @return value of n as a number.
   */
  double toNumber(Node n);

  /**
   * Get the value of a node as a string.
   * @param n Node to be converted to a string.  May be null.
   * @return value of n as a string, or an empty string if n is null.
   */
  String toString(Node n);

  /**
   * Get a variable based on it's qualified name.
   *
   * @param qname The qualified name of the variable.
   *
   * @return The evaluated value of the variable.
   *
   * @throws javax.xml.transform.TransformerException
   */
  XObject getVariableOrParam(com.sun.org.apache.xml.internal.utils.QName qname)
            throws javax.xml.transform.TransformerException;

  /**
   * Get the XPathContext that owns this ExpressionContext.
   *
   * Note: exslt:function requires the XPathContext to access
   * the variable stack and TransformerImpl.
   *
   * @return The current XPathContext.
   * @throws javax.xml.transform.TransformerException
   */
  com.sun.org.apache.xpath.internal.XPathContext getXPathContext()
            throws javax.xml.transform.TransformerException;

}
