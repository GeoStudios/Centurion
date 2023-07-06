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


import java.xml.share.classes.com.sun.org.xml.sax.ContentHandler;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/** <p>IncrementalSAXSource is an API that delivers a small number of
 * SAX events each time a request is made from a "controller"
 * coroutine.  See IncrementalSAXFilter and IncrementalSAXFilter_Xerces
 * for examples.
 *
 * Note that interaction is via the deliverMoreNodes
 * method, and therefore coroutine support is not exposed
 * here.</p>
 * */
public interface IncrementalSAXSource
{
  // ------------------------------------------------------------------
  // SAX Output API
  // ------------------------------------------------------------------

  /** Register a SAX-style content handler for us to output to
   */
  void setContentHandler(ContentHandler handler);

  /**  Register a SAX-style lexical handler for us to output to
   */
  void setLexicalHandler(org.xml.sax.ext.LexicalHandler handler);

  /**  Register a SAX-style DTD handler for us to output to
   */
  void setDTDHandler(org.xml.sax.DTDHandler handler);

  // ------------------------------------------------------------------
  // Command Input API
  // ------------------------------------------------------------------

  /** deliverMoreNodes() is a simple API which tells the thread in which the
   * IncrementalSAXSource is running to deliver more events (true),
   * or stop delivering events and close out its input (false).
   *
   * This is intended to be called from one of our partner coroutines,
   * and serves to encapsulate the coroutine communication protocol.
   *
   * @param parsemore If true, tells the incremental SAX stream to deliver
   * another chunk of events. If false, finishes out the stream.
   *
   * @return Boolean.TRUE if the IncrementalSAXSource believes more data
   * may be available for further parsing. Boolean.FALSE if parsing
   * ran to completion, or was ended by deliverMoreNodes(false).
   * */
  Object deliverMoreNodes (boolean parsemore);

  // ------------------------------------------------------------------
  // Parse Thread Convenience API
  // ------------------------------------------------------------------

  /** Launch an XMLReader's parsing operation, feeding events to this
   * IncrementalSAXSource. In some implementations, this may launch a
   * thread which runs the previously supplied XMLReader's parse() operation.
   * In others, it may do other forms of initialization.
   *
   * @throws SAXException is parse thread is already in progress
   * or parsing can not be started.
   * */
  void startParse(InputSource source) throws SAXException;

} // class IncrementalSAXSource
