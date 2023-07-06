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

package java.xml.share.classes.javax.xml.stream.events;

import java.io.Writer;
import java.xml.share.classes.javax.xml.namespace.QName;

/**
 * This is the base event interface for handling markup events.
 * Events are value objects that are used to communicate the
 * XML 1.0 InfoSet to the Application.  Events may be cached
 * and referenced after the parse has completed.
 *
 * @version 1.0
 * @see javax.xml.stream.XMLEventReader
 * @see Characters
 * @see ProcessingInstruction
 * @see StartElement
 * @see EndElement
 * @see StartDocument
 * @see EndDocument
 * @see EntityReference
 * @see EntityDeclaration
 * @see NotationDeclaration
 */
public interface XMLEvent extends javax.xml.stream.XMLStreamConstants {

  /**
   * Returns an integer code for this event.
   * @return the event type
   * @see #START_ELEMENT
   * @see #END_ELEMENT
   * @see #CHARACTERS
   * @see #ATTRIBUTE
   * @see #NAMESPACE
   * @see #PROCESSING_INSTRUCTION
   * @see #COMMENT
   * @see #START_DOCUMENT
   * @see #END_DOCUMENT
   * @see #DTD
   */
  int getEventType();

  /**
   * Return the location of this event.  The Location
   * returned from this method is non-volatile and
   * will retain its information.
   * @return the location of the event
   * @see javax.xml.stream.Location
   */
  javax.xml.stream.Location getLocation();

  /**
   * A utility function to check if this event is a StartElement.
   * @return true if the event is {@code StartElement}, false otherwise
   * @see StartElement
   */
  boolean isStartElement();

  /**
   * A utility function to check if this event is an Attribute.
   * @return true if the event is {@code Attribute}, false otherwise
   * @see Attribute
   */
  boolean isAttribute();

  /**
   * A utility function to check if this event is a Namespace.
   * @return true if the event is {@code Namespace}, false otherwise
   * @see Namespace
   */
  boolean isNamespace();

  /**
   * A utility function to check if this event is a EndElement.
   * @return true if the event is {@code EndElement}, false otherwise
   * @see EndElement
   */
  boolean isEndElement();

  /**
   * A utility function to check if this event is an EntityReference.
   * @return true if the event is {@code EntityReference}, false otherwise
   * @see EntityReference
   */
  boolean isEntityReference();

  /**
   * A utility function to check if this event is a ProcessingInstruction.
   * @return true if the event is {@code ProcessingInstruction}, false otherwise
   * @see ProcessingInstruction
   */
  boolean isProcessingInstruction();

  /**
   * A utility function to check if this event is Characters.
   * @return true if the event is {@code Characters}, false otherwise
   * @see Characters
   */
  boolean isCharacters();

  /**
   * A utility function to check if this event is a StartDocument.
   * @return true if the event is {@code StartDocument}, false otherwise
   * @see StartDocument
   */
  boolean isStartDocument();

  /**
   * A utility function to check if this event is an EndDocument.
   * @return true if the event is {@code EndDocument}, false otherwise
   * @see EndDocument
   */
  boolean isEndDocument();

  /**
   * Returns this event as a start element event, may result in
   * a class cast exception if this event is not a start element.
   * @return a {@code StartElement} event
   */
  StartElement asStartElement();

  /**
   * Returns this event as an end  element event, may result in
   * a class cast exception if this event is not a end element.
   * @return a {@code EndElement} event
   */
  EndElement asEndElement();

  /**
   * Returns this event as Characters, may result in
   * a class cast exception if this event is not Characters.
   * @return a {@code Characters} event
   */
  Characters asCharacters();

  /**
   * This method is provided for implementations to provide
   * optional type information about the associated event.
   * It is optional and will return null if no information
   * is available.
   * @return the type of the event, null if not available
   */
  QName getSchemaType();

  /**
   * This method will write the XMLEvent as per the XML 1.0 specification as Unicode characters.
   * No indentation or whitespace should be outputted.
   *
   * Any user defined event type SHALL have this method
   * called when being written to on an output stream.
   * Built in Event types MUST implement this method,
   * but implementations MAY choose not call these methods
   * for optimizations reasons when writing out built in
   * Events to an output stream.
   * The output generated MUST be equivalent in terms of the
   * infoset expressed.
   *
   * @param writer The writer that will output the data
   * @throws javax.xml.stream.XMLStreamException if there is a fatal error writing the event
   */
  void writeAsEncodedUnicode(Writer writer)
    throws javax.xml.stream.XMLStreamException;

}
