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
package com.sun.org.apache.xml.internal.utils;


/**
 * Simple stack for boolean values.
 * @xsl.usage internal
 */
public final class BoolStack implements Cloneable
{

  /** Array of boolean values          */
  private boolean[] m_values;

  /** Array size allocated           */
  private int m_allocatedSize;

  /** Index into the array of booleans          */
  private int m_index;

  /**
   * Default constructor.  Note that the default
   * block size is very small, for small lists.
   */
  public BoolStack()
  {
    this(32);
  }

  /**
   * Construct a IntVector, using the given block size.
   *
   * @param size array size to allocate
   */
  public BoolStack(int size)
  {

    m_allocatedSize = size;
    m_values = new boolean[size];
    m_index = -1;
  }

  /**
   * Get the length of the list.
   *
   * @return Current length of the list
   */
  public int size()
  {
    return m_index + 1;
  }

  /**
   * Clears the stack.
   *
   */
  public void clear()
  {
        m_index = -1;
  }

  /**
   * Pushes an item onto the top of this stack.
   *
   *
   * @param val the boolean to be pushed onto this stack.
   * @return  the <code>item</code> argument.
   */
  public boolean push(boolean val)
  {

    if (m_index == m_allocatedSize - 1)
      grow();

    return (m_values[++m_index] = val);
  }

  /**
   * Removes the object at the top of this stack and returns that
   * object as the value of this function.
   *
   * @return     The object at the top of this stack.
   * @throws  EmptyStackException  if this stack is empty.
   */
  public boolean pop()
  {
    return m_values[m_index--];
  }

  /**
   * Removes the object at the top of this stack and returns the
   * next object at the top as the value of this function.
   *
   *
   * @return Next object to the top or false if none there
   */
  public boolean popAndTop()
  {

    m_index--;

    return m_index >= 0 && m_values[m_index];
  }

  /**
   * Set the item at the top of this stack
   *
   *
   * @param b Object to set at the top of this stack
   */
  public void setTop(boolean b)
  {
    m_values[m_index] = b;
  }

  /**
   * Looks at the object at the top of this stack without removing it
   * from the stack.
   *
   * @return     the object at the top of this stack.
   * @throws  EmptyStackException  if this stack is empty.
   */
  public boolean peek()
  {
    return m_values[m_index];
  }

  /**
   * Looks at the object at the top of this stack without removing it
   * from the stack.  If the stack is empty, it returns false.
   *
   * @return     the object at the top of this stack.
   */
  public boolean peekOrFalse()
  {
    return m_index > -1 && m_values[m_index];
  }

  /**
   * Looks at the object at the top of this stack without removing it
   * from the stack.  If the stack is empty, it returns true.
   *
   * @return     the object at the top of this stack.
   */
  public boolean peekOrTrue()
  {
    return m_index <= -1 || m_values[m_index];
  }

  /**
   * Tests if this stack is empty.
   *
   * @return  <code>true</code> if this stack is empty;
   *          <code>false</code> otherwise.
   */
  public boolean isEmpty()
  {
    return (m_index == -1);
  }

  /**
   * Grows the size of the stack
   *
   */
  private void grow()
  {

    m_allocatedSize *= 2;

    boolean[] newVector = new boolean[m_allocatedSize];

    System.arraycopy(m_values, 0, newVector, 0, m_index + 1);

    m_values = newVector;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

}
