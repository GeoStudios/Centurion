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

package java.xml.share.classes.com.sun.org.apache.xpath.internal;

import javax.xml.transform.SourceLocator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A class that implements this interface can construct expressions,
 * give information about child and parent expressions,
 * and give the originating source information.  A class that implements
 * this interface does not lay any claim to being directly executable.
 *
 * <p>Note: This interface should not be considered stable.  Only exprSetParent
 * and exprGetParent can be counted on to work reliably.  Work in progress.</p>
 */
public interface ExpressionNode extends SourceLocator
{
  /** This pair of methods are used to inform the node of its
    parent. */
  void exprSetParent(ExpressionNode n);
  ExpressionNode exprGetParent();

  /** This method tells the node to add its argument to the node's
    list of children.  */
  void exprAddChild(ExpressionNode n, int i);

  /** This method returns a child node.  The children are numbered
     from zero, left to right. */
  ExpressionNode exprGetChild(int i);

  /** Return the number of children the node has. */
  int exprGetNumChildren();
}
