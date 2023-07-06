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
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.PrefixResolver;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler.Compiler;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler.OpMap;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Base for iterators that handle predicates.  Does the basic next
 * node logic, so all the derived iterator has to do is get the
 * next node.
 */
public abstract class BasicTestIterator extends LocPathIterator
{
    static final long serialVersionUID = 3505378079378096623L;
  /**
   * Create a LocPathIterator object.
   *
   * @param nscontext The namespace context for this iterator,
   * should be OK if null.
   */
  protected BasicTestIterator()
  {
  }

  /**
   * Create a LocPathIterator object.
   *
   * @param nscontext The namespace context for this iterator,
   * should be OK if null.
   */
  protected BasicTestIterator(PrefixResolver nscontext)
  {

    super(nscontext);
  }

  /**
   * Create a LocPathIterator object, including creation
   * of step walkers from the opcode list, and call back
   * into the Compiler to create predicate expressions.
   *
   * @param compiler The Compiler which is creating
   * this expression.
   * @param opPos The position of this iterator in the
   * opcode list from the compiler.
   *
   * @throws javax.xml.transform.TransformerException
   */
  protected BasicTestIterator(Compiler compiler, int opPos, int analysis)
          throws javax.xml.transform.TransformerException
  {
    super(compiler, opPos, analysis, false);

    int firstStepPos = OpMap.getFirstChildPos(opPos);
    int whatToShow = compiler.getWhatToShow(firstStepPos);

    if ((0 == (whatToShow
               & (DTMFilter.SHOW_ATTRIBUTE
               | DTMFilter.SHOW_NAMESPACE
               | DTMFilter.SHOW_ELEMENT
               | DTMFilter.SHOW_PROCESSING_INSTRUCTION)))
               || (whatToShow == DTMFilter.SHOW_ALL))
      initNodeTest(whatToShow);
    else
    {
      initNodeTest(whatToShow, compiler.getStepNS(firstStepPos),
                              compiler.getStepLocalName(firstStepPos));
    }
    initPredicateInfo(compiler, firstStepPos);
  }

  /**
   * Create a LocPathIterator object, including creation
   * of step walkers from the opcode list, and call back
   * into the Compiler to create predicate expressions.
   *
   * @param compiler The Compiler which is creating
   * this expression.
   * @param opPos The position of this iterator in the
   * opcode list from the compiler.
   * @param shouldLoadWalkers True if walkers should be
   * loaded, or false if this is a derived iterator and
   * it doesn't wish to load child walkers.
   *
   * @throws javax.xml.transform.TransformerException
   */
  protected BasicTestIterator(
          Compiler compiler, int opPos, int analysis, boolean shouldLoadWalkers)
            throws javax.xml.transform.TransformerException
  {
    super(compiler, opPos, analysis, shouldLoadWalkers);
  }

  /**
   * Get the next node via getNextXXX.  Bottlenecked for derived class override.
   * @return The next node on the axis, or DTM.NULL.
   */
  protected abstract int getNextNode();

  /**
   *  Returns the next node in the set and advances the position of the
   * iterator in the set. After a NodeIterator is created, the first call
   * to nextNode() returns the first node in the set.
   *
   * @return  The next <code>Node</code> in the set being iterated over, or
   *   <code>null</code> if there are no more members in that set.
   */
  public int nextNode()
  {
        if(m_foundLast)
        {
                m_lastFetched = DTM.NULL;
                return DTM.NULL;
        }

    if(DTM.NULL == m_lastFetched)
    {
      resetProximityPositions();
    }

    int next;

    com.sun.org.apache.xpath.internal.VariableStack vars;
    int savedStart;
    if (-1 != m_stackFrame)
    {
      vars = m_execContext.getVarStack();

      // These three statements need to be combined into one operation.
      savedStart = vars.getStackFrame();

      vars.setStackFrame(m_stackFrame);
    }
    else
    {
      // Yuck.  Just to shut up the compiler!
      vars = null;
      savedStart = 0;
    }

    try
    {
      do
      {
        next = getNextNode();

        if (DTM.NULL != next)
        {
          if(DTMIterator.FILTER_ACCEPT == acceptNode(next))
            break;
          else
            continue;
        }
        else
          break;
      }
      while (next != DTM.NULL);

      if (DTM.NULL != next)
      {
        m_pos++;
        return next;
      }
      else
      {
        m_foundLast = true;

        return DTM.NULL;
      }
    }
    finally
    {
      if (-1 != m_stackFrame)
      {
        // These two statements need to be combined into one operation.
        vars.setStackFrame(savedStart);
      }
    }
  }

  /**
   *  Get a cloned Iterator that is reset to the beginning
   *  of the query.
   *
   *  @return A cloned NodeIterator set of the start of the query.
   *
   *  @throws CloneNotSupportedException
   */
  public DTMIterator cloneWithReset() throws CloneNotSupportedException
  {

    ChildTestIterator clone = (ChildTestIterator) super.cloneWithReset();

    clone.resetProximityPositions();

    return clone;
  }

}
