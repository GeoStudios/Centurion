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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;


import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.Attjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.DOM2Helper;
import javax.xml.transform.Result;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.w3c.dom.EntityReference;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.ProcessingInstruction;
import java.xml.share.classes.com.sun.org.w3c.dom.Text;
import java.xml.share.classes.com.sun.org.xml.sax.ContentHandler;
import java.xml.share.classes.com.sun.org.xml.sax.Locator;
import java.xml.share.classes.com.sun.org.xml.sax.ext.LexicalHandler;
import java.xml.share.classes.com.sun.org.xml.sax.helpers.LocatorImpl;















/**
 * This class does a pre-order walk of the DOM tree, calling a ContentHandler
 * interface as it goes.
 *
 * This class is a copy of the one in com.sun.org.apache.xml.internal.utils.
 * It exists to cut the serializers dependancy on that package.
 *
 * @xsl.usage internal
 */

public final class TreeWalker
{

  /** Local reference to a ContentHandler          */
  final private ContentHandler m_contentHandler;
  /**
   * If m_contentHandler is a SerializationHandler, then this is
   * a reference to the same object.
   */
  final private SerializationHandler m_Serializer;

  /** Locator object for this TreeWalker          */
  final private LocatorImpl m_locator = new LocatorImpl();

  /**
   * Get the ContentHandler used for the tree walk.
   *
   * @return the ContentHandler used for the tree walk
   */
  public ContentHandler getContentHandler()
  {
    return m_contentHandler;
  }

  public TreeWalker(ContentHandler ch) {
      this(ch, null);
  }
  /**
   * Constructor.
   * @param   contentHandler The implemention of the
   * contentHandler operation (toXMLString, digest, ...)
   */
  public TreeWalker(ContentHandler contentHandler, String systemId)
  {
      // Set the content handler
      m_contentHandler = contentHandler;
      if (m_contentHandler instanceof SerializationHandler) {
          m_Serializer = (SerializationHandler) m_contentHandler;
      } else {
          m_Serializer = null;
      }
      // Set the system ID, if it is given
      m_contentHandler.setDocumentLocator(m_locator);
      if (systemId != null) {
          m_locator.setSystemId(systemId);
      }
  }

  /**
   * Perform a pre-order traversal non-recursive style.
   *
   * Note that TreeWalker assumes that the subtree is intended to represent
   * a complete (though not necessarily well-formed) document and, during a
   * traversal, startDocument and endDocument will always be issued to the
   * SAX listener.
   *
   * @param pos Node in the tree where to start traversal
   *
   * @throws TransformerException
   */
  public void traverse(Node pos) throws org.xml.sax.SAXException
  {

    this.m_contentHandler.startDocument();

    Node top = pos;

    while (null != pos)
    {
      startNode(pos);

      Node nextNode = pos.getFirstChild();

      while (null == nextNode)
      {
        endNode(pos);

        if (top.equals(pos))
          break;

        nextNode = pos.getNextSibling();

        if (null == nextNode)
        {
          pos = pos.getParentNode();

          if ((null == pos) || (top.equals(pos)))
          {
            if (null != pos)
              endNode(pos);

            nextNode = null;

            break;
          }
        }
      }

      pos = nextNode;
    }
    this.m_contentHandler.endDocument();
  }

  /**
   * Perform a pre-order traversal non-recursive style.

   * Note that TreeWalker assumes that the subtree is intended to represent
   * a complete (though not necessarily well-formed) document and, during a
   * traversal, startDocument and endDocument will always be issued to the
   * SAX listener.
   *
   * @param pos Node in the tree where to start traversal
   * @param top Node in the tree where to end traversal
   *
   * @throws TransformerException
   */
  public void traverse(Node pos, Node top) throws org.xml.sax.SAXException
  {

    this.m_contentHandler.startDocument();

    while (null != pos)
    {
      startNode(pos);

      Node nextNode = pos.getFirstChild();

      while (null == nextNode)
      {
        endNode(pos);

        if ((null != top) && top.equals(pos))
          break;

        nextNode = pos.getNextSibling();

        if (null == nextNode)
        {
          pos = pos.getParentNode();

          if ((null == pos) || ((null != top) && top.equals(pos)))
          {
            nextNode = null;

            break;
          }
        }
      }

      pos = nextNode;
    }
    this.m_contentHandler.endDocument();
  }

  // Flag indicating whether following text to be processed is raw text
  boolean nextIsRaw = false;

  /**
   * Optimized dispatch of characters.
   */
  private void dispatachChars(Node node)
     throws org.xml.sax.SAXException
  {
    if(m_Serializer != null)
    {
      this.m_Serializer.characters(node);
    }
    else
    {
      String data = ((Text) node).getData();
      this.m_contentHandler.characters(data.toCharArray(), 0, data.length());
    }
  }

  /**
   * Start processing given node
   *
   *
   * @param node Node to process
   *
   * @throws org.xml.sax.SAXException
   */
  protected void startNode(Node node) throws org.xml.sax.SAXException
  {

//   TODO: <REVIEW>
//    A Serializer implements ContentHandler, but not NodeConsumer
//    so drop this reference to NodeConsumer which would otherwise
//    pull in all sorts of things
//    if (m_contentHandler instanceof NodeConsumer)
//    {
//      ((NodeConsumer) m_contentHandler).setOriginatingNode(node);
//    }
//    TODO: </REVIEW>

                if (node instanceof Locator loc)
                {
                  m_locator.setColumnNumber(loc.getColumnNumber());
                        m_locator.setLineNumber(loc.getLineNumber());
                        m_locator.setPublicId(loc.getPublicId());
                        m_locator.setSystemId(loc.getSystemId());
                }
                else
                {
                        m_locator.setColumnNumber(0);
      m_locator.setLineNumber(0);
                }

    switch (node.getNodeType())
    {
    case Node.COMMENT_NODE :
    {
      String data = ((Comment) node).getData();

      if (m_contentHandler instanceof LexicalHandler)
      {
        LexicalHandler lh = ((LexicalHandler) this.m_contentHandler);

        lh.comment(data.toCharArray(), 0, data.length());
      }
    }
    break;
    case Node.DOCUMENT_FRAGMENT_NODE :

      // ??;
      break;
    case Node.DOCUMENT_NODE :

      break;
    case Node.ELEMENT_NODE :
      Element elem_node = (Element) node;
      {
          // Make sure the namespace node
          // for the element itself is declared
          // to the ContentHandler
          String uri = elem_node.getNamespaceURI();
          if (uri != null) {
              String prefix = elem_node.getPrefix();
              if (prefix==null)
                prefix="";
              this.m_contentHandler.startPrefixMapping(prefix,uri);
          }
      }
      NamedNodeMap atts = elem_node.getAttributes();
      int nAttrs = atts.getLength();
      // System.out.println("TreeWalker#startNode: "+node.getNodeName());


      // Make sure the namespace node of
      // each attribute is declared to the ContentHandler
      for (int i = 0; i < nAttrs; i++)
      {
        final Node attr = atts.item(i);
        final String attrName = attr.getNodeName();
        final int colon = attrName.indexOf(':');
        final String prefix;

        if (attrName.equals("xmlns") || attrName.startsWith("xmlns:"))
        {
          // Use "" instead of null, as Xerces likes "" for the
          // name of the default namespace.  Fix attributed
          // to "Steven Murray" <smurray@ebt.com>.
          if (colon < 0)
            prefix = "";
          else
            prefix = attrName.substring(colon + 1);

          this.m_contentHandler.startPrefixMapping(prefix,
                                                   attr.getNodeValue());
        }
        else if (colon > 0) {
            prefix = attrName.substring(0,colon);
            String uri = attr.getNamespaceURI();
            if (uri != null)
                this.m_contentHandler.startPrefixMapping(prefix,uri);
        }
      }

      String ns = DOM2Helper.getNamespaceOfNode(node);
      if(null == ns)
        ns = "";
      this.m_contentHandler.startElement(ns,
                                         DOM2Helper.getLocalNameOfNode(node),
                                         node.getNodeName(),
                                         new AttList(atts));
      break;
    case Node.PROCESSING_INSTRUCTION_NODE :
    {
      ProcessingInstruction pi = (ProcessingInstruction) node;
      String name = pi.getNodeName();

      // String data = pi.getData();
      if (name.equals("xslt-next-is-raw"))
      {
        nextIsRaw = true;
      }
      else
      {
        this.m_contentHandler.processingInstruction(pi.getNodeName(),
                                                    pi.getData());
      }
    }
    break;
    case Node.CDATA_SECTION_NODE :
    {
      boolean isLexH = (m_contentHandler instanceof LexicalHandler);
      LexicalHandler lh = isLexH
                          ? ((LexicalHandler) this.m_contentHandler) : null;

      if (isLexH)
      {
        lh.startCDATA();
      }

      dispatachChars(node);

      {
        if (isLexH)
        {
          lh.endCDATA();
        }
      }
    }
    break;
    case Node.TEXT_NODE :
    {
      //String data = ((Text) node).getData();

      if (nextIsRaw)
      {
        nextIsRaw = false;

        m_contentHandler.processingInstruction(Result.PI_DISABLE_OUTPUT_ESCAPING, "");
        dispatachChars(node);
        m_contentHandler.processingInstruction(Result.PI_ENABLE_OUTPUT_ESCAPING, "");
      }
      else
      {
        dispatachChars(node);
      }
    }
    break;
    case Node.ENTITY_REFERENCE_NODE :
    {
      EntityReference eref = (EntityReference) node;

      if (m_contentHandler instanceof LexicalHandler)
      {
        ((LexicalHandler) this.m_contentHandler).startEntity(
          eref.getNodeName());
      }
      else
      {

        // warning("Can not output entity to a pure SAX ContentHandler");
      }
    }
    break;
    default :
    }
  }

  /**
   * End processing of given node
   *
   *
   * @param node Node we just finished processing
   *
   * @throws org.xml.sax.SAXException
   */
  protected void endNode(Node node) throws org.xml.sax.SAXException
  {

    switch (node.getNodeType())
    {
    case Node.DOCUMENT_NODE :
      break;

    case Node.ELEMENT_NODE :
      String ns = DOM2Helper.getNamespaceOfNode(node);
      if(null == ns)
        ns = "";
      this.m_contentHandler.endElement(ns,
              DOM2Helper.getLocalNameOfNode(node),
              node.getNodeName());

      if (m_Serializer == null) {
      // Don't bother with endPrefixMapping calls if the ContentHandler is a
      // SerializationHandler because SerializationHandler's ignore the
      // endPrefixMapping() calls anyways. . . .  This is an optimization.
      Element elem_node = (Element) node;
      NamedNodeMap atts = elem_node.getAttributes();
      int nAttrs = atts.getLength();

      // do the endPrefixMapping calls in reverse order
      // of the startPrefixMapping calls
      for (int i = (nAttrs-1); 0 <= i; i--)
      {
        final Node attr = atts.item(i);
        final String attrName = attr.getNodeName();
        final int colon = attrName.indexOf(':');
        final String prefix;

        if (attrName.equals("xmlns") || attrName.startsWith("xmlns:"))
        {
          // Use "" instead of null, as Xerces likes "" for the
          // name of the default namespace.  Fix attributed
          // to "Steven Murray" <smurray@ebt.com>.
          if (colon < 0)
            prefix = "";
          else
            prefix = attrName.substring(colon + 1);

          this.m_contentHandler.endPrefixMapping(prefix);
        }
        else if (colon > 0) {
            prefix = attrName.substring(0, colon);
            this.m_contentHandler.endPrefixMapping(prefix);
        }
      }
      {
          String uri = elem_node.getNamespaceURI();
          if (uri != null) {
              String prefix = elem_node.getPrefix();
              if (prefix==null)
                prefix="";
              this.m_contentHandler.endPrefixMapping(prefix);
          }
      }
      }
      break;
    case Node.CDATA_SECTION_NODE :
      break;
    case Node.ENTITY_REFERENCE_NODE :
    {
      EntityReference eref = (EntityReference) node;

      if (m_contentHandler instanceof LexicalHandler)
      {
        LexicalHandler lh = ((LexicalHandler) this.m_contentHandler);

        lh.endEntity(eref.getNodeName());
      }
    }
    break;
    default :
    }
  }
}  //TreeWalker
