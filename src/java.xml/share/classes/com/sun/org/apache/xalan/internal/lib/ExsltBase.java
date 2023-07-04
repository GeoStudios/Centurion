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
package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeProxy;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The base class for some EXSLT extension classes.
 * It contains common utility methods to be used by the sub-classes.
 */
public abstract class ExsltBase
{
  /**
   * Return the string value of a Node
   *
   * @param n The Node.
   * @return The string value of the Node
   */
  protected static String toString(Node n)
  {
    if (n instanceof DTMNodeProxy)
         return ((DTMNodeProxy)n).getStringValue();
    else
    {
      String value = n.getNodeValue();
      if (value == null)
      {
        NodeList nodelist = n.getChildNodes();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < nodelist.getLength(); i++)
        {
          Node childNode = nodelist.item(i);
          buf.append(toString(childNode));
        }
        return buf.toString();
      }
      else
        return value;
    }
  }

  /**
   * Convert the string value of a Node to a number.
   * Return NaN if the string is not a valid number.
   *
   * @param n The Node.
   * @return The number value of the Node
   */
  protected static double toNumber(Node n)
  {
    double d = 0.0;
    String str = toString(n);
    try
    {
      d = Double.valueOf(str).doubleValue();
    }
    catch (NumberFormatException e)
    {
      d= Double.NaN;
    }
    return d;
  }
}
