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
 * This class pools string buffers, since they are reused so often.
 * String buffers are good candidates for pooling, because of
 * their supporting character arrays.
 * @xsl.usage internal
 */
public class StringBufferPool
{

  /** The global pool of string buffers.   */
  private static final ObjectPool m_stringBufPool =
    new ObjectPool(com.sun.org.apache.xml.internal.utils.FastStringBuffer.class);

  /**
   * Get the first free instance of a string buffer, or create one
   * if there are no free instances.
   *
   * @return A string buffer ready for use.
   */
  public synchronized static FastStringBuffer get()
  {
    return (FastStringBuffer) m_stringBufPool.getInstance();
  }

  /**
   * Return a string buffer back to the pool.
   *
   * @param sb Must be a non-null reference to a string buffer.
   */
  public synchronized static void free(FastStringBuffer sb)
  {
    // Since this isn't synchronized, setLength must be
    // done before the instance is freed.
    // Fix attributed to Peter Speck <speck@ruc.dk>.
    sb.setLength(0);
    m_stringBufPool.freeInstance(sb);
  }
}
