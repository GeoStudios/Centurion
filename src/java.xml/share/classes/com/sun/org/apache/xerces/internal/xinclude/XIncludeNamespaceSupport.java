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
package com.sun.org.apache.xerces.internal.xinclude;

import com.sun.org.apache.xerces.internal.xni.NamespaceContext;

/**
 * This is an implementation of NamespaceContext which is intended to be used for
 * XInclude processing.  It enables each context to be marked as invalid, if necessary,
 * to indicate that the namespaces recorded on those contexts won't be apparent in the
 * resulting infoset.
 *
 *
 */
public class XIncludeNamespaceSupport extends MultipleScopeNamespaceSupport {

    /**
     * This stores whether or not the context at the matching depth was valid.
     */
    private boolean[] fValidContext = new boolean[8];

    /**
     *
     */
    public XIncludeNamespaceSupport() {
        super();
    }

    /**
     * @param context
     */
    public XIncludeNamespaceSupport(NamespaceContext context) {
        super(context);
    }

    /**
     * Pushes a new context onto the stack.
     */
    public void pushContext() {
        super.pushContext();
        if (fCurrentContext + 1 == fValidContext.length) {
            boolean[] contextarray = new boolean[fValidContext.length * 2];
            System.arraycopy(fValidContext, 0, contextarray, 0, fValidContext.length);
            fValidContext = contextarray;
        }

        fValidContext[fCurrentContext] = true;
    }

    /**
     * This method is used to set a context invalid for XInclude namespace processing.
     * Any context defined by an &lt;include&gt; or &lt;fallback&gt; element is not
     * valid for processing the include parent's [in-scope namespaces]. Thus, contexts
     * defined by these elements are set to invalid by the XInclude processor using
     * this method.
     */
    public void setContextInvalid() {
        fValidContext[fCurrentContext] = false;
    }

    /**
     * This returns the namespace URI which was associated with the given pretext, in
     * the context that existed at the include parent of the current element.  The
     * include parent is the last element, before the current one, which was not set
     * to an invalid context using setContextInvalid()
     *
     * @param prefix the prefix of the desired URI
     * @return the URI corresponding to the prefix in the context of the include parent
     */
    public String getURIFromIncludeParent(String prefix) {
        int lastValidContext = fCurrentContext - 1;
        while (lastValidContext > 0 && !fValidContext[lastValidContext]) {
            lastValidContext--;
        }
        return getURI(prefix, lastValidContext);
    }
}
