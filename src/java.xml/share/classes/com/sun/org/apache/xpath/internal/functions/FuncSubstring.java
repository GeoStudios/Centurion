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


import java.xml.share.classes.com.sun.org.apache.xalan.internal.res.XSLMessages;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XMLString;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XString;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.res.XPATHErrorResources;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Execute the Substring() function.
 * @xsl.usage advanced
 */
public class FuncSubstring extends Function3Args
{
    static final long serialVersionUID = -5996676095024715502L;

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

    XMLString s1 = m_arg0.execute(xctxt).xstr();
    double start = m_arg1.execute(xctxt).num();
    int lenOfS1 = s1.length();
    XMLString substr;

    if (lenOfS1 <= 0)
      return XString.EMPTYSTRING;
    else
    {
      int startIndex;

      if (Double.isNaN(start))
      {

        // Double.MIN_VALUE doesn't work with math below
        // so just use a big number and hope I never get caught.
        start = -1000000;
        startIndex = 0;
      }
      else
      {
        start = Math.round(start);
        startIndex = (start > 0) ? (int) start - 1 : 0;
      }

      if (null != m_arg2)
      {
        double len = m_arg2.num(xctxt);
        int end = (int) (Math.round(len) + start) - 1;

        // Normalize end index.
        if (end < 0)
          end = 0;
        else if (end > lenOfS1)
          end = lenOfS1;

        if (startIndex > lenOfS1)
          startIndex = lenOfS1;

        substr = s1.substring(startIndex, end);
      }
      else
      {
        if (startIndex > lenOfS1)
          startIndex = lenOfS1;
        substr = s1.substring(startIndex);
      }
    }

    return (XString)substr; // cast semi-safe
  }

  /**
   * Check that the number of arguments passed to this function is correct.
   *
   *
   * @param argNum The number of arguments that is being passed to the function.
   *
   * @throws WrongNumberArgsException
   */
  public void checkNumberArgs(int argNum) throws WrongNumberArgsException
  {
    if (argNum < 2)
      reportWrongNumberArgs();
  }

  /**
   * Constructs and throws a WrongNumberArgException with the appropriate
   * message for this function object.
   *
   * @throws WrongNumberArgsException
   */
  protected void reportWrongNumberArgs() throws WrongNumberArgsException {
      throw new WrongNumberArgsException(XSLMessages.createXPATHMessage(XPATHErrorResources.ER_TWO_OR_THREE, null)); //"2 or 3");
  }
}
