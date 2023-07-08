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

import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XMLString;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XMLStringFactory;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Class XMLStringFactoryImpl creates XString versions of XMLStrings.
 * @xsl.usage internal
 */
public class XMLStringFactoryImpl extends XMLStringFactory
{
  /** The XMLStringFactory to pass to DTM construction.   */
  private static final XMLStringFactory m_xstringfactory =
    new XMLStringFactoryImpl();

  /**
   * Get the XMLStringFactory to pass to DTM construction.
   *
   *
   * @return A never-null static reference to a String factory.
   */
  public static XMLStringFactory getFactory()
  {
    return m_xstringfactory;
  }

  /**
   * Create a new XMLString from a Java string.
   *
   *
   * @param string Java String reference, which must be non-null.
   *
   * @return An XMLString object that wraps the String reference.
   */
  public XMLString newstr(String string)
  {
    return new XString(string);
  }

  /**
   * Create a XMLString from a FastStringBuffer.
   *
   *
   * @param fsb FastStringBuffer reference, which must be non-null.
   * @param start The start position in the array.
   * @param length The number of characters to read from the array.
   *
   * @return An XMLString object that wraps the FastStringBuffer reference.
   */
  public XMLString newstr(FastStringBuffer fsb, int start, int length)
  {
    return new XStringForFSB(fsb, start, length);
  }

  /**
   * Create a XMLString from a FastStringBuffer.
   *
   *
   * @param string FastStringBuffer reference, which must be non-null.
   * @param start The start position in the array.
   * @param length The number of characters to read from the array.
   *
   * @return An XMLString object that wraps the FastStringBuffer reference.
   */
  public XMLString newstr(char[] string, int start, int length)
  {
    return new XStringForChars(string, start, length);
  }

  /**
   * Get a cheap representation of an empty string.
   *
   * @return An non-null reference to an XMLString that represents "".
   */
  public XMLString emptystr()
  {
    return XString.EMPTYSTRING;
  }

}
