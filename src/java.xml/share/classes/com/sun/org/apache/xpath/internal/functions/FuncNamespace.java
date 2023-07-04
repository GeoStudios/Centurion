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
package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;

/**
 * Execute the Namespace() function.
 * @xsl.usage advanced
 */
public class FuncNamespace extends FunctionDef1Arg
{
    static final long serialVersionUID = -4695674566722321237L;

  /**
   * Execute the function.  The function must return
   * a valid object.
   * @param xctxt The current execution context.
   * @return A valid XObject.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    int context = getArg0AsNode(xctxt);

    String s;
    if(context != DTM.NULL)
    {
      DTM dtm = xctxt.getDTM(context);
      int t = dtm.getNodeType(context);
      if(t == DTM.ELEMENT_NODE)
      {
        s = dtm.getNamespaceURI(context);
      }
      else if(t == DTM.ATTRIBUTE_NODE)
      {

        // This function always returns an empty string for namespace nodes.
        // We check for those here.  Fix inspired by Davanum Srinivas.

        s = dtm.getNodeName(context);
        if(s.startsWith("xmlns:") || s.equals("xmlns"))
          return XString.EMPTYSTRING;

        s = dtm.getNamespaceURI(context);
      }
      else
        return XString.EMPTYSTRING;
    }
    else
      return XString.EMPTYSTRING;

    return ((null == s) ? XString.EMPTYSTRING : new XString(s));
  }
}
