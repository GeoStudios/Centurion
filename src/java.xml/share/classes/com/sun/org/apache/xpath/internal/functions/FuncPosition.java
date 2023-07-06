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
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.QName;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.axes.SubContextjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler.Compiler;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XNumber;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.java.util.java.util.java.util.List;

/**
 * Execute the Position() function.
 * @xsl.usage advanced
 * @LastModified: Oct 2017
 */
public class FuncPosition extends Function
{
    static final long serialVersionUID = -9092846348197271582L;
  private boolean m_isTopLevel;

  /**
   * Figure out if we're executing a toplevel expression.
   * If so, we can't be inside of a predicate.
   */
  public void postCompileStep(Compiler compiler)
  {
    m_isTopLevel = compiler.getLocationPathDepth() == -1;
  }

  /**
   * Get the position in the current context node list.
   *
   * @param xctxt Runtime XPath context.
   *
   * @return The current position of the itteration in the context node list,
   *         or -1 if there is no active context node list.
   */
  public int getPositionInContextNodeList(XPathContext xctxt)
  {

    // System.out.println("FuncPosition- entry");
    // If we're in a predicate, then this will return non-null.
    SubContextList iter = m_isTopLevel ? null : xctxt.getSubContextList();

    if (null != iter)
    {
      int prox = iter.getProximityPosition(xctxt);

      // System.out.println("FuncPosition- prox: "+prox);
      return prox;
    }

    DTMIterator cnl = xctxt.getContextNodeList();

    if (null != cnl)
    {
      int n = cnl.getCurrentNode();
      if(n == DTM.NULL)
      {
        if(cnl.getCurrentPos() == 0)
          return 0;

        // Then I think we're in a sort.  See sort21.xsl. So the iterator has
        // already been spent, and is not on the node we're processing.
        // It's highly possible that this is an issue for other context-list
        // functions.  Shouldn't be a problem for last(), and it shouldn't be
        // a problem for current().
        try
        {
          cnl = cnl.cloneWithReset();
        }
        catch(CloneNotSupportedException cnse)
        {
          throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(cnse);
        }
        int currentNode = xctxt.getContextNode();
        // System.out.println("currentNode: "+currentNode);
        while(DTM.NULL != (n = cnl.nextNode()))
        {
          if(n == currentNode)
            break;
        }
      }
      // System.out.println("n: "+n);
      // System.out.println("FuncPosition- cnl.getCurrentPos(): "+cnl.getCurrentPos());
      return cnl.getCurrentPos();
    }

    // System.out.println("FuncPosition - out of guesses: -1");
    return -1;
  }

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
    double pos = getPositionInContextNodeList(xctxt);

    return new XNumber(pos);
  }

  /**
   * No arguments to process, so this does nothing.
   */
  public void fixupVariables(List<QName> vars, int globalsSize)
  {
    // no-op
  }
}
