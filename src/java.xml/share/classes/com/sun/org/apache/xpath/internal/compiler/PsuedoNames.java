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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.compiler;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * This is used to represent names of nodes that may not be named, like a
 * comment node.
 */
public class PsuedoNames
{

  /**
   * Psuedo name for a wild card pattern ('*').
   */
  public static final String PSEUDONAME_ANY = "*";

  /**
   * Psuedo name for the root node.
   */
  public static final String PSEUDONAME_ROOT = "/";

  /**
   * Psuedo name for a text node.
   */
  public static final String PSEUDONAME_TEXT = "#text";

  /**
   * Psuedo name for a comment node.
   */
  public static final String PSEUDONAME_COMMENT = "#comment";

  /**
   * Psuedo name for a processing instruction node.
   */
  public static final String PSEUDONAME_PI = "#pi";

  /**
   * Psuedo name for an unknown type value.
   */
  public static final String PSEUDONAME_OTHER = "*";
}
