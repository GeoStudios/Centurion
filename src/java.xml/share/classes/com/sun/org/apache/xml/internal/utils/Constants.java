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

package java.xml.share.classes.com.sun.org.apache.xml.internal.utils;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Primary constants used by the XSLT Processor
 * @xsl.usage advanced
 */
public class Constants
{

  /**
   * Mnemonics for standard XML Namespace URIs, as Java Strings:
   * <ul>
   * <li>S_XMLNAMESPACEURI (http://www.w3.org/XML/1998/namespace) is the
   * URI permanantly assigned to the "xml:" prefix. This is used for some
   * features built into the XML specification itself, such as xml:space
   * and xml:lang. It was defined by the W3C's XML Namespaces spec.</li>
   * <li>S_XSLNAMESPACEURL (http://www.w3.org/1999/XSL/Transform) is the
   * URI which indicates that a name may be an XSLT directive. In most
   * XSLT stylesheets, this is bound to the "xsl:" prefix. It's defined
   * by the W3C's XSLT Recommendation.</li>
   * <li>S_OLDXSLNAMESPACEURL (http://www.w3.org/XSL/Transform/1.0) was
   * used in early prototypes of XSLT processors for much the same purpose
   * as S_XSLNAMESPACEURL. It is now considered obsolete, and the version
   * of XSLT which it signified is not fully compatable with the final
   * XSLT Recommendation, so what it really signifies is a badly obsolete
   * stylesheet.</li>
   * </ul> */
  public static final String
        S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace",
        S_XSLNAMESPACEURL = "http://www.w3.org/1999/XSL/Transform",
        S_OLDXSLNAMESPACEURL = "http://www.w3.org/XSL/Transform/1.0";

  /** Authorship mnemonics, as Java Strings. Not standardized,
   * as far as I know.
   * <ul>
   * <li>S_VENDOR -- the name of the organization/individual who published
   * this XSLT processor. </li>
   * <li>S_VENDORURL -- URL where one can attempt to retrieve more
   * information about this publisher and product.</li>
   * </ul>
   */
  public static final String
        S_VENDOR = "Apache Software Foundation",
        S_VENDORURL = "http://xml.apache.org";

  /** S_BUILTIN_EXTENSIONS_URL is a mnemonic for the XML Namespace
   *(http://xml.apache.org/xalan) predefined to signify Xalan's
   * built-in XSLT Extensions. When used in stylesheets, this is often
   * bound to the "xalan:" prefix.
   */
  public static final String
    S_BUILTIN_EXTENSIONS_URL = "http://xml.apache.org/xalan";

  /**
   * The old built-in extension url. It is still supported for
   * backward compatibility.
   */
  public static final String
    S_BUILTIN_OLD_EXTENSIONS_URL = "http://xml.apache.org/xslt";

  /**
   * Xalan extension namespaces.
   */
  public static final String
    // The old namespace for Java extension
    S_EXTENSIONS_OLD_JAVA_URL = "http://xml.apache.org/xslt/java",
    // The new namespace for Java extension
    S_EXTENSIONS_JAVA_URL = "http://xml.apache.org/xalan/java",
    S_EXTENSIONS_LOTUSXSL_JAVA_URL = "http://xsl.lotus.com/java",
    S_EXTENSIONS_XALANLIB_URL = "http://xml.apache.org/xalan",
    S_EXTENSIONS_REDIRECT_URL = "http://xml.apache.org/xalan/redirect",
    S_EXTENSIONS_PIPE_URL = "http://xml.apache.org/xalan/PipeDocument",
    S_EXTENSIONS_SQL_URL = "http://xml.apache.org/xalan/sql";

  /**
   * EXSLT extension namespaces.
   */
  public static final String
    S_EXSLT_COMMON_URL = "http://exslt.org/common",
    S_EXSLT_MATH_URL = "http://exslt.org/math",
    S_EXSLT_SETS_URL = "http://exslt.org/sets",
    S_EXSLT_DATETIME_URL = "http://exslt.org/dates-and-times",
    S_EXSLT_FUNCTIONS_URL = "http://exslt.org/functions",
    S_EXSLT_DYNAMIC_URL = "http://exslt.org/dynamic",
    S_EXSLT_STRINGS_URL = "http://exslt.org/strings";


  /**
   * The minimum version of XSLT supported by this processor.
   */
  public static final double XSLTVERSUPPORTED = 1.0;
}
