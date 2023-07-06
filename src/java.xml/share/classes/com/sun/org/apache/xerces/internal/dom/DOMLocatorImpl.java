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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;


import java.xml.share.classes.com.sun.org.w3c.dom.DOMLocator;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * <code>DOMLocatorImpl</code> is an implementaion that describes a location (e.g.
 * where an error occured).
 * <p>See also the <a href='http://www.w3.org/TR/2001/WD-DOM-Level-3-Core-20010913'>Document Object Model (DOM) Level 3 Core Specification</a>.
 *
 * @xerces.internal
 *
 */

public class DOMLocatorImpl implements DOMLocator {

    //
    // Data
    //

   /**
    * The column number where the error occured,
    * or -1 if there is no column number available.
    */
   public int fColumnNumber = -1;

   /**
    * The line number where the error occured,
    * or -1 if there is no line number available.
    */
   public int fLineNumber = -1;

   /** related data node*/
   public Node fRelatedNode = null;

   /**
    * The URI where the error occured,
    * or null if there is no URI available.
    */
   public String fUri = null;

   /**
    * The byte offset into the input source this locator is pointing to or -1
    * if there is no byte offset available
    */
   public int fByteOffset = -1;

   /**
    * The UTF-16, as defined in [Unicode] and Amendment 1 of [ISO/IEC 10646],
    * offset into the input source this locator is pointing to or -1 if there
    * is no UTF-16 offset available.
    */
   public int fUtf16Offset = -1;

   //
   // Constructors
   //

   public DOMLocatorImpl(){
   }

   public DOMLocatorImpl (int lineNumber, int columnNumber, String uri ){
        fLineNumber = lineNumber ;
        fColumnNumber = columnNumber ;
        fUri = uri;
   } // DOMLocatorImpl (int lineNumber, int columnNumber, String uri )

   public DOMLocatorImpl (int lineNumber, int columnNumber, int utf16Offset, String uri ){
        fLineNumber = lineNumber ;
        fColumnNumber = columnNumber ;
        fUri = uri;
        fUtf16Offset = utf16Offset;
   } // DOMLocatorImpl (int lineNumber, int columnNumber, int utf16Offset, String uri )

   public DOMLocatorImpl (int lineNumber, int columnNumber, int byteoffset, Node relatedData, String uri ){
        fLineNumber = lineNumber ;
        fColumnNumber = columnNumber ;
        fByteOffset = byteoffset ;
        fRelatedNode = relatedData ;
        fUri = uri;
   } // DOMLocatorImpl (int lineNumber, int columnNumber, int offset, Node errorNode, String uri )

   public DOMLocatorImpl (int lineNumber, int columnNumber, int byteoffset, Node relatedData, String uri, int utf16Offset ){
        fLineNumber = lineNumber ;
        fColumnNumber = columnNumber ;
        fByteOffset = byteoffset ;
        fRelatedNode = relatedData ;
        fUri = uri;
        fUtf16Offset = utf16Offset;
   } // DOMLocatorImpl (int lineNumber, int columnNumber, int offset, Node errorNode, String uri )


  /**
   * The line number where the error occured, or -1 if there is no line
   * number available.
   */
   public int getLineNumber(){
        return fLineNumber;
   }

  /**
   * The column number where the error occured, or -1 if there is no column
   * number available.
   */
  public int getColumnNumber(){
        return fColumnNumber;
  }


  /**
   * The URI where the error occured, or null if there is no URI available.
   */
  public String getUri(){
        return fUri;
  }


  public Node getRelatedNode(){
    return fRelatedNode;
  }


  /**
   * The byte offset into the input source this locator is pointing to or -1
   * if there is no byte offset available
   */
  public int getByteOffset(){
        return fByteOffset;
  }

  /**
   * The UTF-16, as defined in [Unicode] and Amendment 1 of [ISO/IEC 10646],
   * offset into the input source this locator is pointing to or -1 if there
   * is no UTF-16 offset available.
   */
  public int getUtf16Offset(){
        return fUtf16Offset;
  }

}// class DOMLocatorImpl
