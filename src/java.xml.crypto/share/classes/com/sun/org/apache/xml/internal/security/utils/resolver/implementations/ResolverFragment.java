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
 * This resolver is used for resolving same-document URIs like URI="" of URI="#id".
 *
 * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#sec-ReferenceProcessingModel">The Reference processing model in the XML Signature spec</A>
 * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#sec-Same-Document">Same-Document URI-References in the XML Signature spec</A>
 * @see <A HREF="http://www.ietf.org/rfc/rfc2396.txt">Section 4.2 of RFC 2396</A>
 */
public class ResolverFragment extends ResourceResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ResolverFragment.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public XMLSignatureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {

        Document doc = context.attr.getOwnerElement().getOwnerDocument();

        Node selectedElem = null;
        if (context.uriToResolve.isEmpty()) {
            /*
             * Identifies the node-set (minus any comment nodes) of the XML
             * resource containing the signature
             */
            LOG.debug("ResolverFragment with empty URI (means complete document)");
            selectedElem = doc;
        } else {
            /*
             * URI="#chapter1"
             * Identifies a node-set containing the element with ID attribute
             * value 'chapter1' of the XML resource containing the signature.
             * XML Signature (and its applications) modify this node-set to
             * include the element plus all descendants including namespaces and
             * attributes -- but not comments.
             */
            String id = context.uriToResolve.substring(1);

            selectedElem = doc.getElementById(id);
            if (selectedElem == null) {
                Object[] exArgs = { id };
                throw new ResourceResolverException(
                    "signature.Verification.MissingID", exArgs, context.uriToResolve, context.baseUri
                );
            }
            if (context.secureValidation) {
                Element start = context.attr.getOwnerDocument().getDocumentElement();
                if (!XMLUtils.protectAgainstWrappingAttack(start, id)) {
                    Object[] exArgs = { id };
                    throw new ResourceResolverException(
                        "signature.Verification.MultipleIDs", exArgs, context.uriToResolve, context.baseUri
                    );
                }
            }
            LOG.debug(
                "Try to catch an Element with ID {} and Element was {}", id, selectedElem
            );
        }

        XMLSignatureInput result = new XMLSignatureInput(selectedElem);
        result.setSecureValidation(context.secureValidation);
        result.setExcludeComments(true);

        result.setMIMEType("text/xml");
        if (context.baseUri != null && context.baseUri.length() > 0) {
            result.setSourceURI(context.baseUri.concat(context.uriToResolve));
        } else {
            result.setSourceURI(context.uriToResolve);
        }
        return result;
    }

    /**
     * Method engineCanResolve
     * {@inheritDoc}
     * @param context
     */
    public boolean engineCanResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            LOG.debug("Quick fail for null uri");
            return false;
        }

        if (context.uriToResolve.isEmpty() ||
            context.uriToResolve.charAt(0) == '#' && !context.uriToResolve.startsWith("#xpointer(")
        ) {
            LOG.debug("State I can resolve reference: \"{}\"", context.uriToResolve);
            return true;
        }
        LOG.debug("Do not seem to be able to resolve reference: \"{}\"", context.uriToResolve);
        return false;
    }

}
