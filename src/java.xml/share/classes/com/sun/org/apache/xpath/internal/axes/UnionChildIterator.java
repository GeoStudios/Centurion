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

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.QName;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.patterns.NodeTest;
import java.util.java.util.java.util.java.util.List;

/**
 * This class defines a simplified type of union iterator that only
 * tests along the child axes.  If the conditions are right, it is
 * much faster than using a UnionPathIterator.
 *
 * @LastModified: Oct 2017
 */
public class UnionChildIterator extends ChildTestIterator
{
    static final long serialVersionUID = 3500298482193003495L;
  /**
   * Even though these may hold full LocPathIterators, this array does
   * not have to be cloned, since only the node test and predicate
   * portion are used, and these only need static information.  However,
   * also note that index predicates can not be used!
   */
  private PredicatedNodeTest[] m_nodeTests = null;

  /**
   * Constructor for UnionChildIterator
   */
  public UnionChildIterator()
  {
    super(null);
  }

  /**
   * Add a node test to the union list.
   *
   * @param test reference to a NodeTest, which will be added
   * directly to the list of node tests (in other words, it will
   * not be cloned).  The parent of this test will be set to
   * this object.
   */
  public void addNodeTest(PredicatedNodeTest test)
  {

    // Increase array size by only 1 at a time.  Fix this
    // if it looks to be a problem.
    if (null == m_nodeTests)
    {
      m_nodeTests = new PredicatedNodeTest[1];
      m_nodeTests[0] = test;
    }
    else
    {
      PredicatedNodeTest[] tests = m_nodeTests;
      int len = m_nodeTests.length;

      m_nodeTests = new PredicatedNodeTest[len + 1];

      System.arraycopy(tests, 0, m_nodeTests, 0, len);

      m_nodeTests[len] = test;
    }
    test.exprSetParent(this);
  }

  /**
   * This function is used to fixup variables from QNames to stack frame
   * indexes at stylesheet build time.
   * @param vars List of QNames that correspond to variables.  This list
   * should be searched backwards for the first qualified name that
   * corresponds to the variable reference qname.  The position of the
   * QName in the vector from the start of the vector will be its position
   * in the stack frame (but variables above the globalsTop value will need
   * to be offset to the current stack frame).
   */
  public void fixupVariables(List<QName> vars, int globalsSize)
  {
    super.fixupVariables(vars, globalsSize);
    if (m_nodeTests != null) {
      for (int i = 0; i < m_nodeTests.length; i++) {
        m_nodeTests[i].fixupVariables(vars, globalsSize);
      }
    }
  }

  /**
   * Test whether a specified node is visible in the logical view of a
   * TreeWalker or NodeIterator. This function will be called by the
   * implementation of TreeWalker and NodeIterator; it is not intended to
   * be called directly from user code.
   * @param n  The node to check to see if it passes the filter or not.
   * @return  a constant to determine whether the node is accepted,
   *   rejected, or skipped, as defined  above .
   */
  public short acceptNode(int n)
  {
    XPathContext xctxt = getXPathContext();
    try
    {
      xctxt.pushCurrentNode(n);
      for (int i = 0; i < m_nodeTests.length; i++)
      {
        PredicatedNodeTest pnt = m_nodeTests[i];
        XObject score = pnt.execute(xctxt, n);
        if (score != NodeTest.SCORE_NONE)
        {
          // Note that we are assuming there are no positional predicates!
          if (pnt.getPredicateCount() > 0)
          {
            if (pnt.executePredicates(n, xctxt))
              return DTMIterator.FILTER_ACCEPT;
          }
          else
            return DTMIterator.FILTER_ACCEPT;

        }
      }
    }
    catch (javax.xml.transform.TransformerException se)
    {

      // TODO: Fix this.
      throw new RuntimeException(se.getMessage());
    }
    finally
    {
      xctxt.popCurrentNode();
    }
    return DTMIterator.FILTER_SKIP;
  }

}