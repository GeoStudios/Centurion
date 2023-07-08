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

package java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref;


import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMDOMException;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMIterator;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.traversal.NodeFilter;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * <code>DTMNodeIterator</code> gives us an implementation of the
 * DTMNodeIterator which returns DOM nodes.
 *
 * Please note that this is not necessarily equivlaent to a DOM
 * NodeIterator operating over the same document. In particular:
 * <ul>
 *
 * <li>If there are several Text nodes in logical succession (ie,
 * across CDATASection and EntityReference boundaries), we will return
 * only the first; the caller is responsible for stepping through
 * them.
 * (%REVIEW% Provide a convenience routine here to assist, pending
 * proposed DOM Level 3 getAdjacentText() operation?) </li>
 *
 * <li>Since the whole XPath/XSLT architecture assumes that the source
 * document is not altered while we're working with it, we do not
 * promise to implement the DOM NodeIterator's "maintain current
 * position" response to document mutation. </li>
 *
 * <li>Since our design for XPath NodeIterators builds a stateful
 * filter directly into the traversal object, getNodeFilter() is not
 * supported.</li>
 *
 * </ul>
 *
 * <p>State: In progress!!</p>
 * */
public class DTMNodeIterator implements org.w3c.dom.traversal.NodeIterator
{
  private final DTMIterator dtm_iter;
  private boolean valid=true;

  //================================================================
  // Methods unique to this class

  /** Public constructor: Wrap a DTMNodeIterator around an existing
   * and preconfigured DTMIterator
   * */
  public DTMNodeIterator(DTMIterator dtmIterator)
    {
      try
      {
        dtm_iter=(DTMIterator)dtmIterator.clone();
      }
      catch(CloneNotSupportedException cnse)
      {
        throw new com.sun.org.apache.xml.internal.utils.WrappedRuntimeException(cnse);
      }
    }

  /** Access the wrapped DTMIterator. I'm not sure whether anyone will
   * need this or not, but let's write it and think about it.
   * */
  public DTMIterator getDTMIterator()
    {
      return dtm_iter;
    }


  //================================================================
  // org.w3c.dom.traversal.NodeFilter API follows

  /** Detaches the NodeIterator from the set which it iterated over,
   * releasing any computational resources and placing the iterator in
   * the INVALID state.
   * */
  public void detach()
    {
      // Theoretically, we could release dtm_iter at this point. But
      // some of the operations may still want to consult it even though
      // navigation is now invalid.
      valid=false;
    }

  /** The value of this flag determines whether the children
   * of entity reference nodes are visible to the iterator.
   *
   * @return false, always (the DTM model flattens entity references)
   * */
  public boolean getExpandEntityReferences()
    {
      return false;
    }

  /** Return a handle to the filter used to screen nodes.
   *
   * This is ill-defined in Xalan's usage of Nodeiterator, where we have
   * built stateful XPath-based filtering directly into the traversal
   * object. We could return something which supports the NodeFilter interface
   * and allows querying whether a given node would be permitted if it appeared
   * as our next node, but in the current implementation that would be very
   * complex -- and just isn't all that useful.
   *
   * @throws DOMException -- NOT_SUPPORTED_ERROR because I can't think
   * of anything more useful to do in this case
   * */
  public NodeFilter getFilter()
    {
      throw new DTMDOMException(DOMException.NOT_SUPPORTED_ERR);
    }


  /** @return The root node of the NodeIterator, as specified
   * when it was created.
   * */
  public Node getRoot()
    {
      int handle=dtm_iter.getRoot();
      return dtm_iter.getDTM(handle).getNode(handle);
    }


  /** Return a mask describing which node types are presented via the
   * iterator.
   **/
  public int getWhatToShow()
    {
      return dtm_iter.getWhatToShow();
    }

  /** @return the next node in the set and advance the position of the
   * iterator in the set.
   *
   * @throws DOMException - INVALID_STATE_ERR Raised if this method is
   * called after the detach method was invoked.
   *  */
  public Node nextNode() throws DOMException
    {
      if(!valid)
        throw new DTMDOMException(DOMException.INVALID_STATE_ERR);

      int handle=dtm_iter.nextNode();
      if (handle==DTM.NULL)
        return null;
      return dtm_iter.getDTM(handle).getNode(handle);
    }


  /** @return the next previous in the set and advance the position of the
   * iterator in the set.
   *
   * @throws DOMException - INVALID_STATE_ERR Raised if this method is
   * called after the detach method was invoked.
   *  */
  public Node previousNode()
    {
      if(!valid)
        throw new DTMDOMException(DOMException.INVALID_STATE_ERR);

      int handle=dtm_iter.previousNode();
      if (handle==DTM.NULL)
        return null;
      return dtm_iter.getDTM(handle).getNode(handle);
    }
}
