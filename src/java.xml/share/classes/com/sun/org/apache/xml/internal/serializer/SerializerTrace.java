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


import java.xml.share.classes.com.sun.org.xml.sax.Attributes;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This interface defines a set of integer constants that identify trace event
 * types.
 *
 * @xsl.usage internal
 */

public interface SerializerTrace {

  /**
   * Event type generated when a document begins.
   *
   */
  int EVENTTYPE_STARTDOCUMENT = 1;

  /**
   * Event type generated when a document ends.
   */
  int EVENTTYPE_ENDDOCUMENT = 2;

  /**
   * Event type generated when an element begins (after the attributes have been processed but before the children have been added).
   */
  int EVENTTYPE_STARTELEMENT = 3;

  /**
   * Event type generated when an element ends, after it's children have been added.
   */
  int EVENTTYPE_ENDELEMENT = 4;

  /**
   * Event type generated for character data (CDATA and Ignorable Whitespace have their own events).
   */
  int EVENTTYPE_CHARACTERS = 5;

  /**
   * Event type generated for ignorable whitespace (I'm not sure how much this is actually called.
   */
  int EVENTTYPE_IGNORABLEWHITESPACE = 6;

  /**
   * Event type generated for processing instructions.
   */
  int EVENTTYPE_PI = 7;

  /**
   * Event type generated after a comment has been added.
   */
  int EVENTTYPE_COMMENT = 8;

  /**
   * Event type generate after an entity ref is created.
   */
  int EVENTTYPE_ENTITYREF = 9;

  /**
   * Event type generated after CDATA is generated.
   */
  int EVENTTYPE_CDATA = 10;

  /**
   * Event type generated when characters might be written to an output stream,
   *  but  these characters never are. They will ultimately be written out via
   * EVENTTYPE_OUTPUT_CHARACTERS. This type is used as attributes are collected.
   * Whenever the attributes change this event type is fired. At the very end
   * however, when the attributes do not change anymore and are going to be
   * ouput to the document the real characters will be written out using the
   * EVENTTYPE_OUTPUT_CHARACTERS.
   */
  int EVENTTYPE_OUTPUT_PSEUDO_CHARACTERS = 11;

  /**
   * Event type generated when characters are written to an output stream.
   */
  int EVENTTYPE_OUTPUT_CHARACTERS = 12;


  /**
   * Tell if trace listeners are present.
   *
   * @return True if there are trace listeners
   */
  boolean hasTraceListeners();

  /**
   * Fire startDocument, endDocument events.
   *
   * @param eventType One of the EVENTTYPE_XXX constants.
   */
  void fireGenerateEvent(int eventType);

  /**
   * Fire startElement, endElement events.
   *
   * @param eventType One of the EVENTTYPE_XXX constants.
   * @param name The name of the element.
   * @param atts The SAX attribute list.
   */
  void fireGenerateEvent(int eventType, String name, Attributes atts);

  /**
   * Fire characters, cdata events.
   *
   * @param eventType One of the EVENTTYPE_XXX constants.
   * @param ch The char array from the SAX event.
   * @param start The start offset to be used in the char array.
   * @param length The end offset to be used in the chara array.
   */
  void fireGenerateEvent(int eventType, char[] ch, int start, int length);

  /**
   * Fire processingInstruction events.
   *
   * @param eventType One of the EVENTTYPE_XXX constants.
   * @param name The name of the processing instruction.
   * @param data The processing instruction data.
   */
  void fireGenerateEvent(int eventType, String name, String data);


  /**
   * Fire comment and entity ref events.
   *
   * @param eventType One of the EVENTTYPE_XXX constants.
   * @param data The comment or entity ref data.
   */
  void fireGenerateEvent(int eventType, String data);

}
