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

package java.xml.share.classes.javax.xml.stream;

/**
 * This interface declares the constants used in this API.
 * Numbers in the range 0 to 256 are reserved for the specification,
 * user defined events must use event codes outside that range.
 *
 */

public interface XMLStreamConstants {
  /**
   * Indicates an event is a start element
   * @see javax.xml.stream.events.StartElement
   */
  int START_ELEMENT=1;
  /**
   * Indicates an event is an end element
   * @see javax.xml.stream.events.EndElement
   */
  int END_ELEMENT=2;
  /**
   * Indicates an event is a processing instruction
   * @see javax.xml.stream.events.ProcessingInstruction
   */
  int PROCESSING_INSTRUCTION=3;

  /**
   * Indicates an event is characters
   * @see javax.xml.stream.events.Characters
   */
  int CHARACTERS=4;

  /**
   * Indicates an event is a comment
   * @see javax.xml.stream.events.Comment
   */
  int COMMENT=5;

  /**
   * The characters are white space
   * (see [XML], 2.10 "White Space Handling").
   * Events are only reported as SPACE if they are ignorable white
   * space.  Otherwise they are reported as CHARACTERS.
   * @see javax.xml.stream.events.Characters
   */
  int SPACE=6;

  /**
   * Indicates an event is a start document
   * @see javax.xml.stream.events.StartDocument
   */
  int START_DOCUMENT=7;

  /**
   * Indicates an event is an end document
   * @see javax.xml.stream.events.EndDocument
   */
  int END_DOCUMENT=8;

  /**
   * Indicates an event is an entity reference
   * @see javax.xml.stream.events.EntityReference
   */
  int ENTITY_REFERENCE=9;

  /**
   * Indicates an event is an attribute
   * @see javax.xml.stream.events.Attribute
   */
  int ATTRIBUTE=10;

  /**
   * Indicates an event is a DTD
   * @see javax.xml.stream.events.DTD
   */
  int DTD=11;

  /**
   * Indicates an event is a CDATA section
   * @see javax.xml.stream.events.Characters
   */
  int CDATA=12;

  /**
   * Indicates the event is a namespace declaration
   *
   * @see javax.xml.stream.events.Namespace
   */
  int NAMESPACE=13;

  /**
   * Indicates a Notation
   * @see javax.xml.stream.events.NotationDeclaration
   */
  int NOTATION_DECLARATION=14;

  /**
   * Indicates a Entity Declaration
   * @see javax.xml.stream.events.NotationDeclaration
   */
  int ENTITY_DECLARATION=15;
}
