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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.functions;

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XMLString;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XString;
import java.xml.share.classes.com.sun.org.xml.sax.ContentHandler;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Execute the normalize-space() function.
 * @xsl.usage advanced
 */
public class FuncNormalizeSpace extends FunctionDef1Arg
{
    static final long serialVersionUID = -3377956872032190880L;

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
    XMLString s1 = getArg0AsString(xctxt);

    return (XString)s1.fixWhiteSpace(true, true, false);
  }

  /**
   * Execute an expression in the XPath runtime context, and return the
   * result of the expression.
   *
   *
   * @param xctxt The XPath runtime context.
   *
   * @return The result of the expression in the form of a <code>XObject</code>.
   *
   * @throws javax.xml.transform.TransformerException if a runtime exception
   *         occurs.
   */
  public void executeCharsToContentHandler(XPathContext xctxt,
                                              ContentHandler handler)
    throws javax.xml.transform.TransformerException,
           org.xml.sax.SAXException
  {
    if(Arg0IsNodesetExpr())
    {
      int node = getArg0AsNode(xctxt);
      if(DTM.NULL != node)
      {
        DTM dtm = xctxt.getDTM(node);
        dtm.dispatchCharactersEvents(node, handler, true);
      }
    }
    else
    {
      XObject obj = execute(xctxt);
      obj.dispatchCharactersEvents(handler);
    }
  }

}
