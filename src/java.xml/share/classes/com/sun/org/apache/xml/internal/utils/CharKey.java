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
 * Simple class for fast lookup of char values, when used with
 * hashtables.  You can set the char, then use it as a key.
 * @xsl.usage internal
 */
public class CharKey {

  /** String value          */
  private char m_char;

  /**
   * Constructor CharKey
   *
   * @param key char value of this object.
   */
  public CharKey(char key)
  {
    m_char = key;
  }

  /**
   * Default constructor for a CharKey.
   */
  public CharKey()
  {
  }

  /**
   * Get the hash value of the character.
   *
   * @return hash value of the character.
   */
  public final void setChar(char c)
  {
    m_char = c;
  }

  /**
   * Get the hash value of the character.
   *
   * @return hash value of the character.
   */
  public final int hashCode()
  {
    return m_char;
  }

  /**
   * Override of equals() for this object
   *
   * @param obj to compare to
   *
   * @return True if this object equals this string value
   */
  public final boolean equals(Object obj)
  {
    return ((CharKey)obj).m_char == m_char;
  }
}
