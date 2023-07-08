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
 * This class is for throwing important checked exceptions
 * over non-checked methods.  It should be used with care,
 * and in limited circumstances.
 */
public class WrappedRuntimeException extends RuntimeException
{
    static final long serialVersionUID = 7140414456714658073L;

  /** Primary checked exception.
   *  @serial          */
  private final Exception m_exception;

  /**
   * Construct a WrappedRuntimeException from a
   * checked exception.
   *
   * @param e Primary checked exception
   */
  public WrappedRuntimeException(Exception e)
  {

    super(e.getMessage());

    m_exception = e;
  }

  /**
   * Constructor WrappedRuntimeException
   *
   *
   * @param msg Exception information.
   * @param e Primary checked exception
   */
  public WrappedRuntimeException(String msg, Exception e)
  {

    super(msg);

    m_exception = e;
  }

  /**
   * Get the checked exception that this runtime exception wraps.
   *
   * @return The primary checked exception
   */
  public Exception getException()
  {
    return m_exception;
  }
}
