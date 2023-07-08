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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.implementations;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverContext;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Handles barename XPointer Reference URIs.
 * <p></p>
 * To retain comments while selecting an element by an identifier ID,
 * use the following full XPointer: URI='#xpointer(id('ID'))'.
 * <p></p>
 * To retain comments while selecting the entire document,
 * use the following full XPointer: URI='#xpointer(/)'.
 * This XPointer contains a simple XPath expression that includes
 * the root node, which the second to last step above replaces with all
 * nodes of the parse tree (all descendants, plus all attributes,
 * plus all namespaces nodes).
 *
 */
public class ResolverXPointer extends ResourceResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ResolverXPointer.class);

    private static final String XP = "#xpointer(id(";
    private static final int XP_LENGTH = XP.length();

    /**
     * {@inheritDoc}
     */
    @Override
    public XMLSignatureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {

        Node resultNode = null;
        Document doc = context.attr.getOwnerElement().getOwnerDocument();

        if (isXPointerSlash(context.uriToResolve)) {
            resultNode = doc;
        } else if (isXPointerId(context.uriToResolve)) {
            String id = getXPointerId(context.uriToResolve);
            resultNode = doc.getElementById(id);

            if (context.secureValidation) {
                Element start = context.attr.getOwnerDocument().getDocumentElement();
                if (!XMLUtils.protectAgainstWrappingAttack(start, id)) {
                    Object[] exArgs = { id };
                    throw new ResourceResolverException(
                        "signature.Verification.MultipleIDs", exArgs, context.uriToResolve, context.baseUri
                    );
                }
            }

            if (resultNode == null) {
                Object[] exArgs = { id };

                throw new ResourceResolverException(
                    "signature.Verification.MissingID", exArgs, context.uriToResolve, context.baseUri
                );
            }
        }

        XMLSignatureInput result = new XMLSignatureInput(resultNode);
        result.setSecureValidation(context.secureValidation);

        result.setMIMEType("text/xml");
        if (context.baseUri != null && context.baseUri.length() > 0) {
            result.setSourceURI(context.baseUri.concat(context.uriToResolve));
        } else {
            result.setSourceURI(context.uriToResolve);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public boolean engineCanResolveURI(ResourceResolverContext context) {
        return isXPointerSlash(context.uriToResolve) || isXPointerId(context.uriToResolve);
    }

    /**
     * Method isXPointerSlash
     *
     * @param uri
     * @return true if begins with xpointer
     */
    private static boolean isXPointerSlash(String uri) {
        return "#xpointer(/)".equals(uri);
    }

    /**
     * Method isXPointerId
     *
     * @param uri
     * @return whether it has an xpointer id
     */
    private static boolean isXPointerId(String uri) {
        if (uri != null && uri.startsWith(XP) && uri.endsWith("))")) {
            String idPlusDelim = uri.substring(XP_LENGTH, uri.length() - 2);

            int idLen = idPlusDelim.length() -1;
            if (idPlusDelim.charAt(0) == '"' && idPlusDelim.charAt(idLen) == '"'
                || idPlusDelim.charAt(0) == '\'' && idPlusDelim.charAt(idLen) == '\'') {
                LOG.debug("Id = {}", idPlusDelim.substring(1, idLen));
                return true;
            }
        }

        return false;
    }

    /**
     * Method getXPointerId
     *
     * @param uri
     * @return xpointerId to search.
     */
    private static String getXPointerId(String uri) {
        if (uri.startsWith(XP) && uri.endsWith("))")) {
            String idPlusDelim = uri.substring(XP_LENGTH, uri.length() - 2);

            int idLen = idPlusDelim.length() -1;
            if (idPlusDelim.charAt(0) == '"' && idPlusDelim.charAt(idLen) == '"'
                || idPlusDelim.charAt(0) == '\'' && idPlusDelim.charAt(idLen) == '\'') {
                return idPlusDelim.substring(1, idLen);
            }
        }

        return null;
    }
}
