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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.utils;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * A very simple lookup table that stores a list of strings, the even
 * number strings being keys, and the odd number strings being values.
 *
 * This class is a copy of the one in com.sun.org.apache.xml.internal.utils.
 * It exists to cut the serializers dependancy on that package.
 *
 * This class is not a public API, it is only public so it can be used
 * in com.sun.org.apache.xml.internal.serializer.
 *
 * @xsl.usage internal
 */
public final class StringToIntTable
{

  public static final int INVALID_KEY = -10000;

  /** Block size to allocate          */
  private final int m_blocksize;

  /** Array of strings this table points to. Associated with ints
   * in m_values         */
  private String[] m_map;

  /** Array of ints this table points. Associated with strings from
   * m_map.         */
  private int[] m_values;

  /** Number of ints in the table          */
  private int m_firstFree = 0;

  /** Size of this table         */
  private int m_mapSize;

  /**
   * Default constructor.  Note that the default
   * block size is very small, for small lists.
   */
  public StringToIntTable()
  {

    m_blocksize = 8;
    m_mapSize = m_blocksize;
    m_map = new String[m_blocksize];
    m_values = new int[m_blocksize];
  }

  /**
   * Construct a StringToIntTable, using the given block size.
   *
   * @param blocksize Size of block to allocate
   */
  public StringToIntTable(int blocksize)
  {

    m_blocksize = blocksize;
    m_mapSize = blocksize;
    m_map = new String[blocksize];
    m_values = new int[m_blocksize];
  }

  /**
   * Get the length of the list.
   *
   * @return the length of the list
   */
  public int getLength()
  {
    return m_firstFree;
  }

  /**
   * Append a string onto the vector.
   *
   * @param key String to append
   * @param value The int value of the string
   */
  public void put(String key, int value)
  {

    if ((m_firstFree + 1) >= m_mapSize)
    {
      m_mapSize += m_blocksize;

      String[] newMap = new String[m_mapSize];

      System.arraycopy(m_map, 0, newMap, 0, m_firstFree + 1);

      m_map = newMap;

      int[] newValues = new int[m_mapSize];

      System.arraycopy(m_values, 0, newValues, 0, m_firstFree + 1);

      m_values = newValues;
    }

    m_map[m_firstFree] = key;
    m_values[m_firstFree] = value;

    m_firstFree++;
  }

  /**
   * Tell if the table contains the given string.
   *
   * @param key String to look for
   *
   * @return The String's int value
   *
   */
  public int get(String key)
  {

    for (int i = 0; i < m_firstFree; i++)
    {
      if (m_map[i].equals(key))
        return m_values[i];
    }

    return INVALID_KEY;
  }

  /**
   * Tell if the table contains the given string. Ignore case.
   *
   * @param key String to look for
   *
   * @return The string's int value
   */
  public int getIgnoreCase(String key)
  {

    if (null == key)
        return INVALID_KEY;

    for (int i = 0; i < m_firstFree; i++)
    {
      if (m_map[i].equalsIgnoreCase(key))
        return m_values[i];
    }

    return INVALID_KEY;
  }

  /**
   * Tell if the table contains the given string.
   *
   * @param key String to look for
   *
   * @return True if the string is in the table
   */
  public boolean contains(String key)
  {

    for (int i = 0; i < m_firstFree; i++)
    {
      if (m_map[i].equals(key))
        return true;
    }

    return false;
  }

  /**
   * Return array of keys in the table.
   *
   * @return Array of strings
   */
  public String[] keys()
  {
    String [] keysArr = new String[m_firstFree];

      System.arraycopy(m_map, 0, keysArr, 0, m_firstFree);

    return keysArr;
  }
}
