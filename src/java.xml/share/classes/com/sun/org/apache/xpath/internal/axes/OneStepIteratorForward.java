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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.axes;

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMFilter;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.Expression;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler.Compiler;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler.OpMap;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class implements a general iterator for
 * those LocationSteps with only one step, and perhaps a predicate,
 * that only go forward (i.e. it can not be used with ancestors,
 * preceding, etc.)
 * @see com.sun.org.apache.xpath.internal.axes#ChildTestIterator
 * @xsl.usage advanced
 */
public class OneStepIteratorForward extends ChildTestIterator
{
    static final long serialVersionUID = -1576936606178190566L;
  /** The traversal axis from where the nodes will be filtered. */
  protected int m_axis = -1;

  /**
   * Create a OneStepIterator object.
   *
   * @param compiler A reference to the Compiler that contains the op map.
   * @param opPos The position within the op map, which contains the
   * location path expression for this itterator.
   *
   * @throws javax.xml.transform.TransformerException
   */
  OneStepIteratorForward(Compiler compiler, int opPos, int analysis)
          throws javax.xml.transform.TransformerException
  {
    super(compiler, opPos, analysis);
    int firstStepPos = OpMap.getFirstChildPos(opPos);

    m_axis = WalkerFactory.getAxisFromStep(compiler, firstStepPos);

  }

  /**
   * Create a OneStepIterator object that will just traverse the self axes.
   *
   * @param axis One of the com.sun.org.apache.xml.internal.dtm.Axis integers.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public OneStepIteratorForward(int axis)
  {
    super(null);

    m_axis = axis;
    int whatToShow = DTMFilter.SHOW_ALL;
    initNodeTest(whatToShow);
  }

  /**
   * Initialize the context values for this expression
   * after it is cloned.
   *
   * @param context The XPath runtime context for this
   * transformation.
   */
  public void setRoot(int context, Object environment)
  {
    super.setRoot(context, environment);
    m_traverser = m_cdtm.getAxisTraverser(m_axis);

  }

//  /**
//   * Return the first node out of the nodeset, if this expression is
//   * a nodeset expression.  This is the default implementation for
//   * nodesets.
//   * <p>WARNING: Do not mutate this class from this function!</p>
//   * @param xctxt The XPath runtime context.
//   * @return the first node out of the nodeset, or DTM.NULL.
//   */
//  public int asNode(XPathContext xctxt)
//    throws javax.xml.transform.TransformerException
//  {
//    if(getPredicateCount() > 0)
//      return super.asNode(xctxt);
//
//    int current = xctxt.getCurrentNode();
//
//    DTM dtm = xctxt.getDTM(current);
//    DTMAxisTraverser traverser = dtm.getAxisTraverser(m_axis);
//
//    String localName = getLocalName();
//    String namespace = getNamespace();
//    int what = m_whatToShow;
//
//    // System.out.println("what: ");
//    // NodeTest.debugWhatToShow(what);
//    if(DTMFilter.SHOW_ALL == what
//       || ((DTMFilter.SHOW_ELEMENT & what) == 0)
//       || localName == NodeTest.WILD
//       || namespace == NodeTest.WILD)
//    {
//      return traverser.first(current);
//    }
//    else
//    {
//      int type = getNodeTypeTest(what);
//      int extendedType = dtm.getExpandedTypeID(namespace, localName, type);
//      return traverser.first(current, extendedType);
//    }
//  }

  /**
   * Get the next node via getFirstAttribute && getNextAttribute.
   */
  protected int getNextNode()
  {
    m_lastFetched = (DTM.NULL == m_lastFetched)
                     ? m_traverser.first(m_context)
                     : m_traverser.next(m_context, m_lastFetched);
    return m_lastFetched;
  }

  /**
   * Returns the axis being iterated, if it is known.
   *
   * @return Axis.CHILD, etc., or -1 if the axis is not known or is of multiple
   * types.
   */
  public int getAxis()
  {
    return m_axis;
  }

  /**
   * @see Expression#deepEquals(Expression)
   */
  public boolean deepEquals(Expression expr)
  {
        if(!super.deepEquals(expr))
                return false;

      return m_axis == ((OneStepIteratorForward) expr).m_axis;
  }

}
