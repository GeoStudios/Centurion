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

package java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref;

import javax.xml.transform.SourceLocator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <code>NodeLocator</code> maintains information on an XML source
 * node.
 *
 */
public class NodeLocator implements SourceLocator
{
  protected String m_publicId;
  protected String m_systemId;
  protected int m_lineNumber;
  protected int m_columnNumber;

  /**
   * Creates a new <code>NodeLocator</code> instance.
   *
   * @param publicId a <code>String</code> value
   * @param systemId a <code>String</code> value
   * @param lineNumber an <code>int</code> value
   * @param columnNumber an <code>int</code> value
   */
  public NodeLocator(String publicId, String systemId,
                     int lineNumber, int columnNumber)
  {
    this.m_publicId = publicId;
    this.m_systemId = systemId;
    this.m_lineNumber = lineNumber;
    this.m_columnNumber = columnNumber;
  }

  /**
   * <code>getPublicId</code> returns the public ID of the node.
   *
   * @return a <code>String</code> value
   */
  public String getPublicId()
  {
    return m_publicId;
  }

  /**
   * <code>getSystemId</code> returns the system ID of the node.
   *
   * @return a <code>String</code> value
   */
  public String getSystemId()
  {
    return m_systemId;
  }

  /**
   * <code>getLineNumber</code> returns the line number of the node.
   *
   * @return an <code>int</code> value
   */
  public int getLineNumber()
  {
    return m_lineNumber;
  }

  /**
   * <code>getColumnNumber</code> returns the column number of the
   * node.
   *
   * @return an <code>int</code> value
   */
  public int getColumnNumber()
  {
    return m_columnNumber;
  }

  /**
   * <code>toString</code> returns a string representation of this
   * NodeLocator instance.
   *
   * @return a <code>String</code> value
   */
  public String toString()
  {
    return "file '" + m_systemId
      + "', line #" + m_lineNumber
      + ", column #" + m_columnNumber;
  }
}
