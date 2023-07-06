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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 *
 * Like IntVector, but used only for the OpMap array.  Length of array
 * is kept in the m_lengthPos position of the array.  Only the required methods
 * are in included here.
 * @xsl.usage internal
 */
public class OpMapVector {

 /** Size of blocks to allocate          */
  protected int m_blocksize;

  /** Array of ints          */
  protected int[] m_map; // IntStack is trying to see this directly

  /** Position where size of array is kept          */
  protected int m_lengthPos = 0;

  /** Size of array          */
  protected int m_mapSize;

    /**
   * Construct a OpMapVector, using the given block size.
   *
   * @param blocksize Size of block to allocate
   */
  public OpMapVector(int blocksize, int increaseSize, int lengthPos)
  {

    m_blocksize = increaseSize;
    m_mapSize = blocksize;
    m_lengthPos = lengthPos;
    m_map = new int[blocksize];
  }

  /**
   * Get the nth element.
   *
   * @param i index of object to get
   *
   * @return object at given index
   */
  public final int elementAt(int i)
  {
    return m_map[i];
  }

    /**
   * Sets the component at the specified index of this vector to be the
   * specified object. The previous component at that position is discarded.
   *
   * The index must be a value greater than or equal to 0 and less
   * than the current size of the vector.
   *
   * @param value object to set
   * @param index Index of where to set the object
   */
  public final void setElementAt(int value, int index)
  {
    if (index >= m_mapSize)
    {
      int oldSize = m_mapSize;

      m_mapSize += m_blocksize;

      int[] newMap = new int[m_mapSize];

      System.arraycopy(m_map, 0, newMap, 0, oldSize);

      m_map = newMap;
    }

    m_map[index] = value;
  }

  /*
   * Reset the array to the supplied size.  No checking is done.
   *
   * @param size The size to trim to.
   */
  public final void setToSize(int size) {

    int[] newMap = new int[size];

    System.arraycopy(m_map, 0, newMap, 0, m_map[m_lengthPos]);

    m_mapSize = size;
    m_map = newMap;

  }

}
