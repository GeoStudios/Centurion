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

import java.xml.share.classes.com.sun.org.apache.xpath.internal.Expression;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.ExpressionOwner;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathVisitor;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.functions.FuncLast;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.functions.FuncPosition;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.functions.Function;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XNumber;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Div;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Minus;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Mod;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Mult;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Plus;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Quo;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Variable;

@SuppressWarnings("deprecation")
public class HasPositionalPredChecker extends XPathVisitor
{
        private boolean m_hasPositionalPred = false;
        private int m_predDepth = 0;

        /**
         * Process the LocPathIterator to see if it contains variables
         * or functions that may make it context dependent.
         * @param path LocPathIterator that is assumed to be absolute, but needs checking.
         * @return true if the path is confirmed to be absolute, false if it
         * may contain context dependencies.
         */
        public static boolean check(LocPathIterator path)
        {
                HasPositionalPredChecker hppc = new HasPositionalPredChecker();
                path.callVisitors(null, hppc);
                return hppc.m_hasPositionalPred;
        }

        /**
         * Visit a function.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param func The function reference object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitFunction(ExpressionOwner owner, Function func)
        {
                if((func instanceof FuncPosition) ||
                   (func instanceof FuncLast))
                        m_hasPositionalPred = true;
                return true;
        }

//      /**
//       * Visit a variable reference.
//       * @param owner The owner of the expression, to which the expression can
//       *              be reset if rewriting takes place.
//       * @param var The variable reference object.
//       * @return true if the sub expressions should be traversed.
//       */
//      public boolean visitVariableRef(ExpressionOwner owner, Variable var)
//      {
//              m_hasPositionalPred = true;
//              return true;
//      }

  /**
   * Visit a predicate within a location path.  Note that there isn't a
   * proper unique component for predicates, and that the expression will
   * be called also for whatever type Expression is.
   *
   * @param owner The owner of the expression, to which the expression can
   *              be reset if rewriting takes place.
   * @param pred The predicate object.
   * @return true if the sub expressions should be traversed.
   */
  public boolean visitPredicate(ExpressionOwner owner, Expression pred)
  {
    m_predDepth++;

    if(m_predDepth == 1)
    {
      if((pred instanceof Variable) ||
         (pred instanceof XNumber) ||
         (pred instanceof Div) ||
         (pred instanceof Plus) ||
         (pred instanceof Minus) ||
         (pred instanceof Mod) ||
         (pred instanceof Quo) ||
         (pred instanceof Mult) ||
         (pred instanceof com.sun.org.apache.xpath.internal.operations.Number) ||
         (pred instanceof Function))
          m_hasPositionalPred = true;
      else
        pred.callVisitors(owner, this);
    }

    m_predDepth--;

    // Don't go have the caller go any further down the subtree.
    return false;
  }

}