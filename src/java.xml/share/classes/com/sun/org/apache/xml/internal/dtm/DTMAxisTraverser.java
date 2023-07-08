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

package java.xml.share.classes.com.sun.org.apache.xml.internal.dtm;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * A class that implements traverses DTMAxisTraverser interface can traverse
 * a set of nodes, usually as defined by an XPath axis.  It is different from
 * an iterator, because it does not need to hold state, and, in fact, must not
 * hold any iteration-based state.  It is meant to be implemented as an inner
 * class of a DTM, and returned by the getAxisTraverser(final int axis)
 * function.
 *
 * <p>A DTMAxisTraverser can probably not traverse a reverse axis in
 * document order.</p>
 *
 * <p>Typical usage:</p>
 * <pre><code>
 * for(int nodeHandle=myTraverser.first(myContext);
 *     nodeHandle!=DTM.NULL;
 *     nodeHandle=myTraverser.next(myContext,nodeHandle))
 * { ... processing for node indicated by nodeHandle goes here ... }
 * </code></pre>
 *
 */
public abstract class DTMAxisTraverser
{

  /**
   * By the nature of the stateless traversal, the context node can not be
   * returned or the iteration will go into an infinate loop.  So to traverse
   * an axis, the first function must be used to get the first node.
   *
   * <p>This method needs to be overloaded only by those axis that process
   * the self node. <\p>
   *
   * @param context The context node of this traversal. This is the point
   * that the traversal starts from.
   * @return the first node in the traversal.
   */
  public int first(int context)
  {
    return next(context, context);
  }

  /**
   * By the nature of the stateless traversal, the context node can not be
   * returned or the iteration will go into an infinate loop.  So to traverse
   * an axis, the first function must be used to get the first node.
   *
   * <p>This method needs to be overloaded only by those axis that process
   * the self node. <\p>
   *
   * @param context The context node of this traversal. This is the point
   * of origin for the traversal -- its "root node" or starting point.
   * @param extendedTypeID The extended type ID that must match.
   *
   * @return the first node in the traversal.
   */
  public int first(int context, int extendedTypeID)
  {
    return next(context, context, extendedTypeID);
  }

  /**
   * Traverse to the next node after the current node.
   *
   * @param context The context node of this traversal. This is the point
   * of origin for the traversal -- its "root node" or starting point.
   * @param current The current node of the traversal. This is the last known
   * location in the traversal, typically the node-handle returned by the
   * previous traversal step. For the first traversal step, context
   * should be set equal to current. Note that in order to test whether
   * context is in the set, you must use the first() method instead.
   *
   * @return the next node in the iteration, or DTM.NULL.
   * @see #first(int)
   */
  public abstract int next(int context, int current);

  /**
   * Traverse to the next node after the current node that is matched
   * by the extended type ID.
   *
   * @param context The context node of this traversal. This is the point
   * of origin for the traversal -- its "root node" or starting point.
   * @param current The current node of the traversal. This is the last known
   * location in the traversal, typically the node-handle returned by the
   * previous traversal step. For the first traversal step, context
   * should be set equal to current. Note that in order to test whether
   * context is in the set, you must use the first() method instead.
   * @param extendedTypeID The extended type ID that must match.
   *
   * @return the next node in the iteration, or DTM.NULL.
   * @see #first(int,int)
   */
  public abstract int next(int context, int current, int extendedTypeID);
}
