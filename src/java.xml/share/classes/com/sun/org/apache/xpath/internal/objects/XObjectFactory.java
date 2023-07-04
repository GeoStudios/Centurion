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
package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.Axis;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.OneStepIterator;


public class XObjectFactory
{

  /**
   * Create the right XObject based on the type of the object passed.  This
   * function can not make an XObject that exposes DOM Nodes, NodeLists, and
   * NodeIterators to the XSLT stylesheet as node-sets.
   *
   * @param val The java object which this object will wrap.
   *
   * @return the right XObject based on the type of the object passed.
   */
  static public XObject create(Object val)
  {

    XObject result;

    if (val instanceof XObject)
    {
      result = (XObject) val;
    }
    else if (val instanceof String)
    {
      result = new XString((String) val);
    }
    else if (val instanceof Boolean)
    {
      result = new XBoolean((Boolean)val);
    }
    else if (val instanceof Double)
    {
      result = new XNumber(((Double) val));
    }
    else
    {
      result = new XObject(val);
    }

    return result;
  }

  /**
   * Create the right XObject based on the type of the object passed.
   * This function <emph>can</emph> make an XObject that exposes DOM Nodes, NodeLists, and
   * NodeIterators to the XSLT stylesheet as node-sets.
   *
   * @param val The java object which this object will wrap.
   * @param xctxt The XPath context.
   *
   * @return the right XObject based on the type of the object passed.
   */
  static public XObject create(Object val, XPathContext xctxt)
  {

    XObject result;

    if (val instanceof XObject)
    {
      result = (XObject) val;
    }
    else if (val instanceof String)
    {
      result = new XString((String) val);
    }
    else if (val instanceof Boolean)
    {
      result = new XBoolean((Boolean)val);
    }
    else if (val instanceof Number)
    {
      result = new XNumber(((Number) val));
    }
    else if (val instanceof DTM dtm)
    {
      try
      {
        int dtmRoot = dtm.getDocument();
        DTMAxisIterator iter = dtm.getAxisIterator(Axis.SELF);
        iter.setStartNode(dtmRoot);
        DTMIterator iterator = new OneStepIterator(iter, Axis.SELF);
        iterator.setRoot(dtmRoot, xctxt);
        result = new XNodeSet(iterator);
      }
      catch(Exception ex)
      {
        throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(ex);
      }
    }
    else if (val instanceof DTMAxisIterator)
    {
      DTMAxisIterator iter = (DTMAxisIterator)val;
      try
      {
        DTMIterator iterator = new OneStepIterator(iter, Axis.SELF);
        iterator.setRoot(iter.getStartNode(), xctxt);
        result = new XNodeSet(iterator);
      }
      catch(Exception ex)
      {
        throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(ex);
      }
    }
    else if (val instanceof DTMIterator)
    {
      result = new XNodeSet((DTMIterator) val);
    }
    // This next three instanceofs are a little worrysome, since a NodeList
    // might also implement a Node!
    else if (val instanceof org.w3c.dom.Node)
    {
      result = new XNodeSetForDOM((org.w3c.dom.Node)val, xctxt);
    }
    // This must come after org.w3c.dom.Node, since many Node implementations
    // also implement NodeList.
    else if (val instanceof org.w3c.dom.NodeList)
    {
      result = new XNodeSetForDOM((org.w3c.dom.NodeList)val, xctxt);
    }
    else if (val instanceof org.w3c.dom.traversal.NodeIterator)
    {
      result = new XNodeSetForDOM((org.w3c.dom.traversal.NodeIterator)val, xctxt);
    }
    else
    {
      result = new XObject(val);
    }

    return result;
  }
}
