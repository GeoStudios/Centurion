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

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * This class defines the constants which are the names of the four default
 * output methods.
 * <p>
 * Three default output methods are defined: XML, HTML, and TEXT.
 * These constants can be used as an argument to the
 * OutputPropertiesFactory.getDefaultMethodProperties() method to get
 * the properties to create a serializer.
 *
 * This class is a public API.
 *
 * @see OutputPropertiesFactory
 * @see Serializer
 *
 * @xsl.usage general
 */
public final class Method
{
    /**
     * A private constructor to prevent the creation of such a class.
     */
    private Method() {

    }

  /**
   * The output method type for XML documents: <tt>xml</tt>.
   */
  public static final String XML = "xml";

  /**
   * The output method type for HTML documents: <tt>html</tt>.
   */
  public static final String HTML = "html";

  /**
   * The output method for XHTML documents,
   * this method type is not currently supported: <tt>xhtml</tt>.
   */
  public static final String XHTML = "xhtml";

  /**
   * The output method type for text documents: <tt>text</tt>.
   */
  public static final String TEXT = "text";

  /**
   * The "internal" method, just used when no method is
   * specified in the style sheet, and a serializer of this type wraps either an
   * XML or HTML type (depending on the first tag in the output being html or
   * not)
   */
  public static final String UNKNOWN = "";
}
