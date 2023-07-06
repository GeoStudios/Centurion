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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver;


import java.util.Collections;
import java.util.Map;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Attr;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




public class ResourceResolverContext {

    private final Map<String, String> properties;

    public final String uriToResolve;

    public final boolean secureValidation;

    public final String baseUri;

    public final Attr attr;

    public ResourceResolverContext(Attr attr, String baseUri, boolean secureValidation) {
        this(attr, baseUri, secureValidation, Collections.emptyMap());
    }

    public ResourceResolverContext(Attr attr, String baseUri, boolean secureValidation, Map<String, String> properties) {
        this.attr = attr;
        this.baseUri = baseUri;
        this.secureValidation = secureValidation;
        this.uriToResolve = attr != null ? attr.getValue() : null;
        this.properties = Collections.unmodifiableMap(properties != null ? properties : Collections.emptyMap());
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
