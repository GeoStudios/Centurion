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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.lib;


import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.DOM2Helper;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.NodeSet;
import java.util.HashMap;
import java.util.Map;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;















/*
 * $Id: ExsltSets.java,v 1.1.2.1 2005/08/01 02:08:50 jeffsuttor Exp $
 */



/**
 * This class contains EXSLT set extension functions.
 * It is accessed by specifying a namespace URI as follows:
 * <pre>
 *    xmlns:set="http://exslt.org/sets"
 * </pre>
 *
 * The documentation for each function has been copied from the relevant
 * EXSLT Implementer page.
 *
 * @see <a href="http://www.exslt.org/">EXSLT</a>
 * @xsl.usage general
 */
public class ExsltSets extends ExsltBase
{
  /**
   * The set:leading function returns the nodes in the node set passed as the first argument that
   * precede, in document order, the first node in the node set passed as the second argument. If
   * the first node in the second node set is not contained in the first node set, then an empty
   * node set is returned. If the second node set is empty, then the first node set is returned.
   *
   * @param nl1 NodeList for first node-set.
   * @param nl2 NodeList for second node-set.
   * @return a NodeList containing the nodes in nl1 that precede in document order the first
   * node in nl2; an empty node-set if the first node in nl2 is not in nl1; all of nl1 if nl2
   * is empty.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static NodeList leading (NodeList nl1, NodeList nl2)
  {
    if (nl2.getLength() == 0)
      return nl1;

    NodeSet ns1 = new NodeSet(nl1);
    NodeSet leadNodes = new NodeSet();
    Node endNode = nl2.item(0);
    if (!ns1.contains(endNode))
      return leadNodes; // empty NodeSet

    for (int i = 0; i < nl1.getLength(); i++)
    {
      Node testNode = nl1.item(i);
      if (DOM2Helper.isNodeAfter(testNode, endNode)
          && !DOM2Helper.isNodeTheSame(testNode, endNode))
        leadNodes.addElement(testNode);
    }
    return leadNodes;
  }

  /**
   * The set:trailing function returns the nodes in the node set passed as the first argument that
   * follow, in document order, the first node in the node set passed as the second argument. If
   * the first node in the second node set is not contained in the first node set, then an empty
   * node set is returned. If the second node set is empty, then the first node set is returned.
   *
   * @param nl1 NodeList for first node-set.
   * @param nl2 NodeList for second node-set.
   * @return a NodeList containing the nodes in nl1 that follow in document order the first
   * node in nl2; an empty node-set if the first node in nl2 is not in nl1; all of nl1 if nl2
   * is empty.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static NodeList trailing (NodeList nl1, NodeList nl2)
  {
    if (nl2.getLength() == 0)
      return nl1;

    NodeSet ns1 = new NodeSet(nl1);
    NodeSet trailNodes = new NodeSet();
    Node startNode = nl2.item(0);
    if (!ns1.contains(startNode))
      return trailNodes; // empty NodeSet

    for (int i = 0; i < nl1.getLength(); i++)
    {
      Node testNode = nl1.item(i);
      if (DOM2Helper.isNodeAfter(startNode, testNode)
          && !DOM2Helper.isNodeTheSame(startNode, testNode))
        trailNodes.addElement(testNode);
    }
    return trailNodes;
  }

  /**
   * The set:intersection function returns a node set comprising the nodes that are within
   * both the node sets passed as arguments to it.
   *
   * @param nl1 NodeList for first node-set.
   * @param nl2 NodeList for second node-set.
   * @return a NodeList containing the nodes in nl1 that are also
   * in nl2.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static NodeList intersection(NodeList nl1, NodeList nl2)
  {
    NodeSet ns1 = new NodeSet(nl1);
    NodeSet ns2 = new NodeSet(nl2);
    NodeSet inter = new NodeSet();

    inter.setShouldCacheNodes(true);

    for (int i = 0; i < ns1.getLength(); i++)
    {
      Node n = ns1.elementAt(i);

      if (ns2.contains(n))
        inter.addElement(n);
    }

    return inter;
  }

  /**
   * The set:difference function returns the difference between two node sets - those nodes that
   * are in the node set passed as the first argument that are not in the node set passed as the
   * second argument.
   *
   * @param nl1 NodeList for first node-set.
   * @param nl2 NodeList for second node-set.
   * @return a NodeList containing the nodes in nl1 that are not in nl2.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static NodeList difference(NodeList nl1, NodeList nl2)
  {
    NodeSet ns1 = new NodeSet(nl1);
    NodeSet ns2 = new NodeSet(nl2);

    NodeSet diff = new NodeSet();

    diff.setShouldCacheNodes(true);

    for (int i = 0; i < ns1.getLength(); i++)
    {
      Node n = ns1.elementAt(i);

      if (!ns2.contains(n))
        diff.addElement(n);
    }

    return diff;
  }

  /**
   * The set:distinct function returns a subset of the nodes contained in the node-set NS passed
   * as the first argument. Specifically, it selects a node N if there is no node in NS that has
   * the same string value as N, and that precedes N in document order.
   *
   * @param nl NodeList for the node-set.
   * @return a NodeList with nodes from nl containing distinct string values.
   * In other words, if more than one node in nl contains the same string value,
   * only include the first such node found.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static NodeList distinct(NodeList nl)
  {
    NodeSet dist = new NodeSet();
    dist.setShouldCacheNodes(true);

    Map<String, Node> stringTable = new HashMap<>();

    for (int i = 0; i < nl.getLength(); i++)
    {
      Node currNode = nl.item(i);
      String key = toString(currNode);

      if (key == null)
        dist.addElement(currNode);
      else if (!stringTable.containsKey(key))
      {
        stringTable.put(key, currNode);
        dist.addElement(currNode);
      }
    }

    return dist;
  }

  /**
   * The set:has-same-node function returns true if the node set passed as the first argument shares
   * any nodes with the node set passed as the second argument. If there are no nodes that are in both
   * node sets, then it returns false.
   *
   * The Xalan extensions MethodResolver converts 'has-same-node' to 'hasSameNode'.
   *
   * Note: Not to be confused with hasSameNodes in the Xalan namespace, which returns true if
   * the two node sets contain the exactly the same nodes (perhaps in a different order),
   * otherwise false.
   *
   * @see <a href="http://www.exslt.org/">EXSLT</a>
   */
  public static boolean hasSameNode(NodeList nl1, NodeList nl2)
  {

    NodeSet ns1 = new NodeSet(nl1);
    NodeSet ns2 = new NodeSet(nl2);

    for (int i = 0; i < ns1.getLength(); i++)
    {
      if (ns2.contains(ns1.elementAt(i)))
        return true;
    }
    return false;
  }

}
