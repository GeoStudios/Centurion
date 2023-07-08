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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.dom;

import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMWSFilter;
import java.util.HashMap;
import java.util.Map;

/*
 * $Id: DOMWSFilter.java,v 1.2.4.1 2005/09/06 06:14:31 pvedula Exp $
 */

/**
 * A wrapper class that adapts the
 * {@link com.sun.org.apache.xml.internal.dtm.DTMWSFilter DTMWSFilter} interface to the XSLTC
 * DOM {@link com.sun.org.apache.xalan.internal.xsltc.StripFilter StripFilter} interface.
 */
public class DOMWSFilter implements DTMWSFilter {

    private final AbstractTranslet m_translet;
    private StripFilter m_filter;

    // The Map for DTM to mapping array
    private final Map<DTM, short[]> m_mappings;

    // Cache the DTM and mapping that are used last time
    private DTM m_currentDTM;
    private short[] m_currentMapping;

    /**
     * Construct an adapter connecting the <code>DTMWSFilter</code> interface
     * to the <code>StripFilter</code> interface.
     *
     * @param translet A translet that also implements the StripFilter
     * interface.
     *
     * @see com.sun.org.apache.xml.internal.dtm.DTMWSFilter
     * @see com.sun.org.apache.xalan.internal.xsltc.StripFilter
     */
    public DOMWSFilter(AbstractTranslet translet) {
        m_translet = translet;
        m_mappings = new HashMap<>();

        if (translet instanceof StripFilter) {
            m_filter = (StripFilter) translet;
        }
    }

    /**
     * Test whether whitespace-only text nodes are visible in the logical
     * view of <code>DTM</code>. Normally, this function
     * will be called by the implementation of <code>DTM</code>;
     * it is not normally called directly from
     * user code.
     *
     * @param node int handle of the node.
     * @param dtm the DTM that owns this node
     * @return one of <code>NOTSTRIP</code>, <code>STRIP</code> or
     * <code>INHERIT</code>.
     */
    public short getShouldStripSpace(int node, DTM dtm) {
        if (m_filter != null && dtm instanceof DOM dom) {
            int type = 0;

            if (dtm instanceof DOMEnhancedForDTM mappableDOM) {

                short[] mapping;
                if (dtm == m_currentDTM) {
                    mapping = m_currentMapping;
                }
                else {
                    mapping = m_mappings.get(dtm);
                    if (mapping == null) {
                        mapping = mappableDOM.getMapping(
                                     m_translet.getNamesArray(),
                                     m_translet.getUrisArray(),
                                     m_translet.getTypesArray());
                        m_mappings.put(dtm, mapping);
                        m_currentDTM = dtm;
                        m_currentMapping = mapping;
                    }
                }

                int expType = mappableDOM.getExpandedTypeID(node);

                // %OPT% The mapping array does not have information about all the
                // exptypes. However it does contain enough information about all names
                // in the translet's namesArray. If the expType does not fall into the
                // range of the mapping array, it means that the expType is not for one
                // of the recognized names. In this case we can just set the type to -1.
                if (expType >= 0 && expType < mapping.length)
                  type = mapping[expType];
                else
                  type = -1;

            }
            else {
                return INHERIT;
            }

            if (m_filter.stripSpace(dom, node, type)) {
                return STRIP;
            } else {
                return NOTSTRIP;
            }
        } else {
            return NOTSTRIP;
        }
    }
}
