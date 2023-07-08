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
 * Provides information on the location of an event.
 *
 * All the information provided by a Location is optional.  For example
 * an application may only report line numbers.
 *
 * @version 1.0
 */
public interface Location {
  /**
   * Return the line number where the current event ends,
   * returns -1 if none is available.
   * @return the current line number
   */
  int getLineNumber();

  /**
   * Return the column number where the current event ends,
   * returns -1 if none is available.
   * @return the current column number
   */
  int getColumnNumber();

  /**
   * Return the byte or character offset into the input source this location
   * is pointing to. If the input source is a file or a byte stream then
   * this is the byte offset into that stream, but if the input source is
   * a character media then the offset is the character offset.
   * Returns -1 if there is no offset available.
   * @return the current offset
   */
  int getCharacterOffset();

  /**
   * Returns the public ID of the XML
   * @return the public ID, or null if not available
   */
  String getPublicId();

  /**
   * Returns the system ID of the XML
   * @return the system ID, or null if not available
   */
  String getSystemId();
}
