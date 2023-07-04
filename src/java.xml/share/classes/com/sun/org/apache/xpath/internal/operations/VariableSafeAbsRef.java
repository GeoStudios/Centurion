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
package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XObject;


/**
 * This is a "smart" variable reference that is used in situations where
 * an absolute path is optimized into a variable reference, but may
 * be used in some situations where the document context may have changed.
 * For instance, in select="document(doc/@href)//name[//salary &gt; 7250]", the
 * root in the predicate will be different for each node in the set.  While
 * this is easy to detect statically in this case, in other cases static
 * detection would be very hard or impossible.  So, this class does a dynamic check
 * to make sure the document context of the referenced variable is the same as
 * the current document context, and, if it is not, execute the referenced variable's
 * expression with the current context instead.
 */
public class VariableSafeAbsRef extends Variable
{
    static final long serialVersionUID = -9174661990819967452L;

  /**
   * Dereference the variable, and return the reference value.  Note that lazy
   * evaluation will occur.  If a variable within scope is not found, a warning
   * will be sent to the error listener, and an empty nodeset will be returned.
   *
   *
   * @param xctxt The runtime execution context.
   *
   * @return The evaluated variable, or an empty nodeset if not found.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt, boolean destructiveOK)
        throws javax.xml.transform.TransformerException
  {
        XNodeSet xns = (XNodeSet)super.execute(xctxt, destructiveOK);
        DTMManager dtmMgr = xctxt.getDTMManager();
        int context = xctxt.getContextNode();
        if(dtmMgr.getDTM(xns.getRoot()).getDocument() !=
           dtmMgr.getDTM(context).getDocument())
        {
                Expression expr = (Expression)xns.getContainedIter();
                xns = (XNodeSet)expr.asIterator(xctxt, context);
        }
        return xns;
  }

}
