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

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverContext;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A simple ResourceResolver for requests into the local filesystem.
 */
public class ResolverLocalFilesystem extends ResourceResolverSpi {

    private static final int FILE_URI_LENGTH = "file:/".length();

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ResolverLocalFilesystem.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public XMLSignatureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {
        try {
            // calculate new URI
            URI uriNew = getNewURI(context.uriToResolve, context.baseUri);

            String fileName =
                ResolverLocalFilesystem.translateUriToFilename(uriNew.toString());
            InputStream inputStream = Files.newInputStream(Paths.get(fileName));
            XMLSignatureInput result = new XMLSignatureInput(inputStream);
            result.setSecureValidation(context.secureValidation);

            result.setSourceURI(uriNew.toString());

            return result;
        } catch (Exception e) {
            throw new ResourceResolverException(e, context.uriToResolve, context.baseUri, "generic.EmptyMessage");
        }
    }

    /**
     * Method translateUriToFilename
     *
     * @param uri
     * @return the string of the filename
     */
    private static String translateUriToFilename(String uri) {

        String subStr = uri.substring(FILE_URI_LENGTH);

        if (subStr.indexOf("%20") > -1) {
            int offset = 0;
            int index = 0;
            StringBuilder temp = new StringBuilder(subStr.length());
            do {
                index = subStr.indexOf("%20",offset);
                if (index == -1) {
                    temp.append(subStr.substring(offset));
                } else {
                    temp.append(subStr, offset, index);
                    temp.append(' ');
                    offset = index + 3;
                }
            } while(index != -1);
            subStr = temp.toString();
        }

        if (subStr.charAt(1) == ':') {
            // we're running M$ Windows, so this works fine
            return subStr;
        }
        // we're running some UNIX, so we have to prepend a slash
        return "/" + subStr;
    }

    /**
     * {@inheritDoc}
     */
    public boolean engineCanResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            return false;
        }

        if (context.uriToResolve.isEmpty() || context.uriToResolve.charAt(0) == '#' ||
            context.uriToResolve.startsWith("http:")) {
            return false;
        }

        try {
            LOG.debug("I was asked whether I can resolve {}", context.uriToResolve);

            if (context.uriToResolve.startsWith("file:") || context.baseUri.startsWith("file:")) {
                LOG.debug("I state that I can resolve {}", context.uriToResolve);
                return true;
            }
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        }

        LOG.debug("But I can't");

        return false;
    }

    private static URI getNewURI(String uri, String baseURI) throws URISyntaxException {
        URI newUri = null;
        if (baseURI == null || "".equals(baseURI)) {
            newUri = new URI(uri);
        } else {
            newUri = new URI(baseURI).resolve(uri);
        }

        // if the URI contains a fragment, ignore it
        if (newUri.getFragment() != null) {
            return new URI(newUri.getScheme(), newUri.getSchemeSpecificPart(), null);
        }
        return newUri;
    }
}
