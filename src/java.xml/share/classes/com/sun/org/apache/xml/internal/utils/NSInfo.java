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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.utils;

/**
 * This class holds information about the namespace info
 * of a node.  It is used to optimize namespace lookup in
 * a generic DOM.
 * @xsl.usage internal
 */
public class NSInfo
{

  /**
   * Constructor NSInfo
   *
   *
   * @param hasProcessedNS Flag indicating whether namespaces
   * have been processed for this node
   * @param hasXMLNSAttrs Flag indicating whether this node
   * has XMLNS attributes.
   */
  public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs)
  {

    m_hasProcessedNS = hasProcessedNS;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_namespace = null;
    m_ancestorHasXMLNSAttrs = ANCESTORXMLNSUNPROCESSED;
  }

  // Unused at the moment

  /**
   * Constructor NSInfo
   *
   *
   * @param hasProcessedNS Flag indicating whether namespaces
   * have been processed for this node
   * @param hasXMLNSAttrs Flag indicating whether this node
   * has XMLNS attributes.
   * @param ancestorHasXMLNSAttrs Flag indicating whether one of this node's
   * ancestor has XMLNS attributes.
   */
  public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs,
                int ancestorHasXMLNSAttrs)
  {

    m_hasProcessedNS = hasProcessedNS;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_ancestorHasXMLNSAttrs = ancestorHasXMLNSAttrs;
    m_namespace = null;
  }

  /**
   * Constructor NSInfo
   *
   *
   * @param namespace The namespace URI
   * @param hasXMLNSAttrs Flag indicating whether this node
   * has XMLNS attributes.
   */
  public NSInfo(String namespace, boolean hasXMLNSAttrs)
  {

    m_hasProcessedNS = true;
    m_hasXMLNSAttrs = hasXMLNSAttrs;
    m_namespace = namespace;
    m_ancestorHasXMLNSAttrs = ANCESTORXMLNSUNPROCESSED;
  }

  /** The namespace URI          */
  public String m_namespace;

  /** Flag indicating whether this node has an XMLNS attribute          */
  public boolean m_hasXMLNSAttrs;

  /** Flag indicating whether namespaces have been processed for this node */
  public boolean m_hasProcessedNS;

  /** Flag indicating whether one of this node's ancestor has an XMLNS attribute          */
  public int m_ancestorHasXMLNSAttrs;

  /** Constant for ancestors XMLNS atributes not processed          */
  public static final int ANCESTORXMLNSUNPROCESSED = 0;

  /** Constant indicating an ancestor has an XMLNS attribute           */
  public static final int ANCESTORHASXMLNS = 1;

  /** Constant indicating ancestors don't have an XMLNS attribute           */
  public static final int ANCESTORNOXMLNS = 2;
}
