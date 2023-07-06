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

package java.xml.share.classes.com.sun.org.apache.xml.internal.utils;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * A very simple table that stores a list of StringToStringTables, optimized
 * for small lists.
 * @xsl.usage internal
 */
public class StringToStringTableVector
{

  /** Size of blocks to allocate         */
  private final int m_blocksize;

  /** Array of StringToStringTable objects          */
  private StringToStringTable[] m_map;

  /** Number of StringToStringTable objects in this array          */
  private int m_firstFree = 0;

  /** Size of this array          */
  private int m_mapSize;

  /**
   * Default constructor.  Note that the default
   * block size is very small, for small lists.
   */
  public StringToStringTableVector()
  {

    m_blocksize = 8;
    m_mapSize = m_blocksize;
    m_map = new StringToStringTable[m_blocksize];
  }

  /**
   * Construct a StringToStringTableVector, using the given block size.
   *
   * @param blocksize Size of blocks to allocate
   */
  public StringToStringTableVector(int blocksize)
  {

    m_blocksize = blocksize;
    m_mapSize = blocksize;
    m_map = new StringToStringTable[blocksize];
  }

  /**
   * Get the length of the list.
   *
   * @return Number of StringToStringTable objects in the list
   */
  public final int getLength()
  {
    return m_firstFree;
  }

  /**
   * Get the length of the list.
   *
   * @return Number of StringToStringTable objects in the list
   */
  public final int size()
  {
    return m_firstFree;
  }

  /**
   * Append a StringToStringTable object onto the vector.
   *
   * @param value StringToStringTable object to add
   */
  public final void addElement(StringToStringTable value)
  {

    if ((m_firstFree + 1) >= m_mapSize)
    {
      m_mapSize += m_blocksize;

      StringToStringTable[] newMap = new StringToStringTable[m_mapSize];

      System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);

      m_map = newMap;
    }

    m_map[m_firstFree] = value;

    m_firstFree++;
  }

  /**
   * Given a string, find the last added occurance value
   * that matches the key.
   *
   * @param key String to look up
   *
   * @return the last added occurance value that matches the key
   * or null if not found.
   */
  public final String get(String key)
  {

    for (int i = m_firstFree - 1; i >= 0; --i)
    {
      String nsuri = m_map[i].get(key);

      if (nsuri != null)
        return nsuri;
    }

    return null;
  }

  /**
   * Given a string, find out if there is a value in this table
   * that matches the key.
   *
   * @param key String to look for
   *
   * @return True if the string was found in table, null if not
   */
  public final boolean containsKey(String key)
  {

    for (int i = m_firstFree - 1; i >= 0; --i)
    {
      if (m_map[i].get(key) != null)
        return true;
    }

    return false;
  }

  /**
   * Remove the last element.
   */
  public final void removeLastElem()
  {

    if (m_firstFree > 0)
    {
      m_map[m_firstFree] = null;

      m_firstFree--;
    }
  }

  /**
   * Get the nth element.
   *
   * @param i Index of element to find
   *
   * @return The StringToStringTable object at the given index
   */
  public final StringToStringTable elementAt(int i)
  {
    return m_map[i];
  }

  /**
   * Tell if the table contains the given StringToStringTable.
   *
   * @param s The StringToStringTable to find
   *
   * @return True if the StringToStringTable is found
   */
  public final boolean contains(StringToStringTable s)
  {

    for (int i = 0; i < m_firstFree; i++)
    {
      if (m_map[i].equals(s))
        return true;
    }

    return false;
  }
}
