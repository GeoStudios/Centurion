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


import java.xml.share.classes.com.sun.org.apache.xalan.internal.templates.Constants;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.transformer.TransformerImpl;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.QName;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.ExtensionsProvider;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XBoolean;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


// J2SE does not support Xalan interpretive
/*
*/

/**
 * Execute the ExtElementAvailable() function.
 * @xsl.usage advanced
 */
public class FuncExtElementAvailable extends FunctionOneArg
{
    static final long serialVersionUID = -472533699257968546L;

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

    String prefix;
    String namespace;
    String methName;

    String fullName = m_arg0.execute(xctxt).str();
    int indexOfNSSep = fullName.indexOf(':');

    if (indexOfNSSep < 0)
    {
      prefix = "";
      namespace = Constants.S_XSLNAMESPACEURL;
      methName = fullName;
    }
    else
    {
      prefix = fullName.substring(0, indexOfNSSep);
      namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
      if (null == namespace)
        return XBoolean.S_FALSE;
      methName= fullName.substring(indexOfNSSep + 1);
    }

    if (namespace.equals(Constants.S_XSLNAMESPACEURL)
    ||  namespace.equals(Constants.S_BUILTIN_EXTENSIONS_URL)) {

        // J2SE does not support Xalan interpretive
        /*
      try {
        TransformerImpl transformer = (TransformerImpl) xctxt.getOwnerObject();
        return transformer.getStylesheet().getAvailableElements().containsKey(
                                                            new QName(namespace, methName))
               ? XBoolean.S_TRUE : XBoolean.S_FALSE;
      } catch (Exception e) {
        return XBoolean.S_FALSE;
      }
      */
        return XBoolean.S_FALSE;
    } else {
      //dml
      ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
      return extProvider.elementAvailable(namespace, methName)
             ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
  }
}
