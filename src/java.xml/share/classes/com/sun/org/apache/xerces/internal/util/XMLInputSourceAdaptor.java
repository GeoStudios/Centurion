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
package com.sun.org.apache.xerces.internal.util;

import javax.xml.transform.Source;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

/**
 * {@link Source} that represents an {@link XMLInputSource}.
 *
 * <p>
 * Ideally, we should be able to have {@link XMLInputSource}
 * derive from {@link Source}, but the way
 * the {@link XMLInputSource#getSystemId()} method works is
 * different from the way {@link Source#getSystemId()} method works.
 *
 * <p>
 * In a long run, we should make them consistent so that we can
 * get rid of this awkward adaptor class.
 *
 *     Kohsuke Kawaguchi
 */
public final class XMLInputSourceAdaptor implements Source {
    /**
     * the actual source information.
     */
    public final XMLInputSource fSource;

    public XMLInputSourceAdaptor( XMLInputSource core ) {
        fSource = core;
    }

    public void setSystemId(String systemId) {
        fSource.setSystemId(systemId);
    }

    public String getSystemId() {
        try {
            return XMLEntityManager.expandSystemId(
                    fSource.getSystemId(), fSource.getBaseSystemId(), false);
        } catch (MalformedURIException e) {
            return fSource.getSystemId();
        }
    }
}
