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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xpath.internal.XPathContext;
/*
 *
 *
 * Simple wrapper to DTM and XPathContext objects.
 * Used in XRTreeFrag for caching references to the objects.
 */
 public final class DTMXRTreeFrag {
  private DTM m_dtm;
  private int m_dtmIdentity = DTM.NULL;
  private XPathContext m_xctxt;

  public DTMXRTreeFrag(int dtmIdentity, XPathContext xctxt){
      m_xctxt = xctxt;
      m_dtmIdentity = dtmIdentity;
      m_dtm = xctxt.getDTM(dtmIdentity);
    }

  public void destruct(){
    m_dtm = null;
    m_xctxt = null;
 }

DTM getDTM(){return m_dtm;}
public int getDTMIdentity(){return m_dtmIdentity;}
XPathContext getXPathContext(){return m_xctxt;}

public int hashCode() { return m_dtmIdentity; }
public boolean equals(Object obj) {
   if (obj instanceof DTMXRTreeFrag) {
       return (m_dtmIdentity == ((DTMXRTreeFrag)obj).getDTMIdentity());
   }
   return false;
 }

}
