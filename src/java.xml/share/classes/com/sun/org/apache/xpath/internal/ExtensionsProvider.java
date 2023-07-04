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

package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xpath.internal.functions.FuncExtFunction;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;

/**
 * Interface that XPath objects can call to obtain access to an
 * ExtensionsTable.
 *
 * @LastModified: Oct 2017
 */
public interface ExtensionsProvider
{
  /**
   * Is the extension function available?
   */

  boolean functionAvailable(String ns, String funcName)
          throws javax.xml.transform.TransformerException;

  /**
   * Is the extension element available?
   */
  boolean elementAvailable(String ns, String elemName)
          throws javax.xml.transform.TransformerException;

  /**
   * Execute the extension function.
   */
  Object extFunction(String ns, String funcName, List<XObject> argVec,
          Object methodKey)
            throws javax.xml.transform.TransformerException;

  /**
   * Execute the extension function.
   */
  Object extFunction(FuncExtFunction extFunction, List<XObject> argVec)
            throws javax.xml.transform.TransformerException;
}
