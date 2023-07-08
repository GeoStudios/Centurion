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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.functions;

import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XString;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Execute the Translate() function.
 * @xsl.usage advanced
 */
public class FuncTranslate extends Function3Args
{
    static final long serialVersionUID = -1672834340026116482L;

  /**
   * Execute the function.  The function must return
   * a valid object.
   * @param xctxt The current execution context.
   * @return A valid XObject.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    String theFirstString = m_arg0.execute(xctxt).str();
    String theSecondString = m_arg1.execute(xctxt).str();
    String theThirdString = m_arg2.execute(xctxt).str();
    int theFirstStringLength = theFirstString.length();
    int theThirdStringLength = theThirdString.length();

    // A vector to contain the new characters.  We'll use it to construct
    // the result string.
    StringBuffer sbuffer = new StringBuffer();

    for (int i = 0; i < theFirstStringLength; i++)
    {
      char theCurrentChar = theFirstString.charAt(i);
      int theIndex = theSecondString.indexOf(theCurrentChar);

      if (theIndex < 0)
      {

        // Didn't find the character in the second string, so it
        // is not translated.
        sbuffer.append(theCurrentChar);
      }
      else if (theIndex < theThirdStringLength)
      {

        // OK, there's a corresponding character in the
        // third string, so do the translation...
        sbuffer.append(theThirdString.charAt(theIndex));
      }
      else
      {

        // There's no corresponding character in the
        // third string, since it's shorter than the
        // second string.  In this case, the character
        // is removed from the output string, so don't
        // do anything.
      }
    }

    return new XString(sbuffer.toString());
  }
}
