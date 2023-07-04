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
package com.sun.org.apache.xml.internal.security.transforms.params;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.transforms.TransformParam;
import com.sun.org.apache.xml.internal.security.utils.ElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This Object serves as Content for the ds:Transforms for exclusive
 * Canonicalization.
 * <p></p>
 * It implements the {@link Element} interface
 * and can be used directly in a DOM tree.
 *
 */
public class InclusiveNamespaces extends ElementProxy implements TransformParam {

    /** Field _TAG_EC_INCLUSIVENAMESPACES */
    public static final String _TAG_EC_INCLUSIVENAMESPACES =
        "InclusiveNamespaces";

    /** Field _ATT_EC_PREFIXLIST */
    public static final String _ATT_EC_PREFIXLIST = "PrefixList";

    /** Field ExclusiveCanonicalizationNamespace */
    public static final String ExclusiveCanonicalizationNamespace =
        "http://www.w3.org/2001/10/xml-exc-c14n#";

    /**
     * Constructor XPathContainer
     *
     * @param doc
     * @param prefixList
     */
    public InclusiveNamespaces(Document doc, String prefixList) {
        this(doc, InclusiveNamespaces.prefixStr2Set(prefixList));
    }

    /**
     * Constructor InclusiveNamespaces
     *
     * @param doc
     * @param prefixes
     */
    public InclusiveNamespaces(Document doc, Set<String> prefixes) {
        super(doc);

        SortedSet<String> prefixList = null;
        if (prefixes instanceof SortedSet<?>) {
            prefixList = (SortedSet<String>)prefixes;
        } else {
            prefixList = new TreeSet<>(prefixes);
        }

        StringBuilder sb = new StringBuilder();
        for (String prefix : prefixList) {
            if ("xmlns".equals(prefix)) {
                sb.append("#default ");
            } else {
                sb.append(prefix);
                sb.append(' ');
            }
        }

        setLocalAttribute(InclusiveNamespaces._ATT_EC_PREFIXLIST, sb.toString().trim());
    }

    /**
     * Constructor InclusiveNamespaces
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public InclusiveNamespaces(Element element, String baseURI)
        throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Method getInclusiveNamespaces
     *
     * @return The Inclusive Namespace string
     */
    public String getInclusiveNamespaces() {
        return getLocalAttribute(InclusiveNamespaces._ATT_EC_PREFIXLIST);
    }

    /**
     * Decodes the {@code inclusiveNamespaces} String and returns all
     * selected namespace prefixes as a Set. The {@code #default}
     * namespace token is represented as an empty namespace prefix
     * ({@code "xmlns"}).
     * <BR>
     * The String {@code inclusiveNamespaces=" xenc    ds #default"}
     * is returned as a Set containing the following Strings:
     * <UL>
     * <LI>{@code xmlns}</LI>
     * <LI>{@code xenc}</LI>
     * <LI>{@code ds}</LI>
     * </UL>
     *
     * @param inclusiveNamespaces
     * @return A set to string
     */
    public static SortedSet<String> prefixStr2Set(String inclusiveNamespaces) {
        SortedSet<String> prefixes = new TreeSet<>();

        if (inclusiveNamespaces == null || inclusiveNamespaces.length() == 0) {
            return prefixes;
        }

        String[] tokens = inclusiveNamespaces.split("\\s");
        for (String prefix : tokens) {
            if ("#default".equals(prefix)) {
                prefixes.add("xmlns");
            } else {
                prefixes.add(prefix);
            }
        }

        return prefixes;
    }

    /**
     * Method getBaseNamespace
     *
     * {@inheritDoc}
     */
    public String getBaseNamespace() {
        return InclusiveNamespaces.ExclusiveCanonicalizationNamespace;
    }

    /**
     * Method getBaseLocalName
     *
     * {@inheritDoc}
     */
    public String getBaseLocalName() {
        return InclusiveNamespaces._TAG_EC_INCLUSIVENAMESPACES;
    }
}
