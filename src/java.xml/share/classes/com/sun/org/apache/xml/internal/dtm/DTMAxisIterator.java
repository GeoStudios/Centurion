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
 * This class iterates over a single XPath Axis, and returns node handles.
 */
public interface DTMAxisIterator extends Cloneable
{

  /** Specifies the end of the iteration, and is the same as DTM.NULL.  */
  int END = DTM.NULL;

  /**
   * Get the next node in the iteration.
   *
   * @return The next node handle in the iteration, or END.
   */
  int next();

  /**
   * Resets the iterator to the last start node.
   *
   * @return A DTMAxisIterator, which may or may not be the same as this
   *         iterator.
   */
  DTMAxisIterator reset();

  /**
   * @return the number of nodes in this iterator.  This may be an expensive
   * operation when called the first time.
   */
  int getLast();

  /**
   * @return The position of the current node in the set, as defined by XPath.
   */
  int getPosition();

  /**
   * Remembers the current node for the next call to gotoMark().
   */
  void setMark();

  /**
   * Restores the current node remembered by setMark().
   */
  void gotoMark();

  /**
   * Set start to END should 'close' the iterator,
   * i.e. subsequent call to next() should return END.
   *
   * @param node Sets the root of the iteration.
   *
   * @return A DTMAxisIterator set to the start of the iteration.
   */
  DTMAxisIterator setStartNode(int node);

  /**
   * Get start to END should 'close' the iterator,
   * i.e. subsequent call to next() should return END.
   *
   * @return The root node of the iteration.
   */
  int getStartNode();

  /**
   * @return true if this iterator has a reversed axis, else false.
   */
  boolean isReverse();

  /**
   * @return a deep copy of this iterator. The clone should not be reset
   * from its current position.
   */
  DTMAxisIterator cloneIterator();

  /**
   * Set if restartable.
   */
  void setRestartable(boolean isRestartable);

  /**
   * Return the node at the given position.
   *
   * @param position The position
   * @return The node at the given position.
   */
  int getNodeByPosition(int position);
}
