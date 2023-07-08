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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.objects;

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class represents an XPath null object, and is capable of
 * converting the null to other types, such as a string.
 * @xsl.usage general
 */
public class XNull extends XNodeSet
{
    static final long serialVersionUID = -6841683711458983005L;

  /**
   * Create an XObject.
   */
  public XNull()
  {
    super();
  }

  /**
   * Tell what kind of class this is.
   *
   * @return type CLASS_NULL
   */
  public int getType()
  {
    return CLASS_NULL;
  }

  /**
   * Given a request type, return the equivalent string.
   * For diagnostic purposes.
   *
   * @return type string "#CLASS_NULL"
   */
  public String getTypeString()
  {
    return "#CLASS_NULL";
  }

  /**
   * Cast result object to a number.
   *
   * @return 0.0
   */

  public double num()
  {
    return 0.0;
  }

  /**
   * Cast result object to a boolean.
   *
   * @return false
   */
  public boolean bool()
  {
    return false;
  }

  /**
   * Cast result object to a string.
   *
   * @return empty string ""
   */
  public String str()
  {
    return "";
  }

  /**
   * Cast result object to a result tree fragment.
   *
   * @param support XPath context to use for the conversion
   *
   * @return The object as a result tree fragment.
   */
  public int rtf(XPathContext support)
  {
    // DTM frag = support.createDocumentFragment();
    // %REVIEW%
    return DTM.NULL;
  }

//  /**
//   * Cast result object to a nodelist.
//   *
//   * @return null
//   */
//  public DTMIterator iter()
//  {
//    return null;
//  }

  /**
   * Tell if two objects are functionally equal.
   *
   * @param obj2 Object to compare this to
   *
   * @return True if the given object is of type CLASS_NULL
   */
  public boolean equals(XObject obj2)
  {
    return obj2.getType() == CLASS_NULL;
  }
}
