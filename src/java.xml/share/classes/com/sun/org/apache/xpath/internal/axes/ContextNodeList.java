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

import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.traversal.NodeIterator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Classes who implement this interface can be a
 * <a href="http://www.w3.org/TR/xslt#dt-current-node-list">current node list</a>,
 * also refered to here as a <term>context node list</term>.
 * @xsl.usage advanced
 */
public interface ContextNodeList
{

  /**
   * Get the <a href="http://www.w3.org/TR/xslt#dt-current-node">current node</a>.
   *
   *
   * @return The current node, or null.
   */
  Node getCurrentNode();

  /**
   * Get the current position, which is one less than
   * the next nextNode() call will retrieve.  i.e. if
   * you call getCurrentPos() and the return is 0, the next
   * fetch will take place at index 1.
   *
   * @return The position of the
   * <a href="http://www.w3.org/TR/xslt#dt-current-node">current node</a>
   * in the  <a href="http://www.w3.org/TR/xslt#dt-current-node-list">current node list</a>.
   */
  int getCurrentPos();

  /**
   * Reset the iterator.
   */
  void reset();

  /**
   * If setShouldCacheNodes(true) is called, then nodes will
   * be cached.  They are not cached by default.
   *
   * @param b true if the nodes should be cached.
   */
  void setShouldCacheNodes(boolean b);

  /**
   * If an index is requested, NodeSetDTM will call this method
   * to run the iterator to the index.  By default this sets
   * m_next to the index.  If the index argument is -1, this
   * signals that the iterator should be run to the end.
   *
   * @param index The index to run to, or -1 if the iterator should be run
   *              to the end.
   */
  void runTo(int index);

  /**
   * Set the current position in the node set.
   * @param i Must be a valid index.
   */
  void setCurrentPos(int i);

  /**
   * Get the length of the list.
   *
   * @return The number of nodes in this node list.
   */
  int size();

  /**
   * Tells if this NodeSetDTM is "fresh", in other words, if
   * the first nextNode() that is called will return the
   * first node in the set.
   *
   * @return true if the iteration of this list has not yet begun.
   */
  boolean isFresh();

  /**
   * Get a cloned Iterator that is reset to the start of the iteration.
   *
   * @return A clone of this iteration that has been reset.
   *
   * @throws CloneNotSupportedException
   */
  NodeIterator cloneWithReset() throws CloneNotSupportedException;

  /**
   * Get a clone of this iterator.  Be aware that this operation may be
   * somewhat expensive.
   *
   *
   * @return A clone of this object.
   *
   * @throws CloneNotSupportedException
   */
  Object clone() throws CloneNotSupportedException;

  /**
   * Get the index of the last node in this list.
   *
   *
   * @return the index of the last node in this list.
   */
  int getLast();

  /**
   * Set the index of the last node in this list.
   *
   *
   * @param last the index of the last node in this list.
   */
  void setLast(int last);
}
