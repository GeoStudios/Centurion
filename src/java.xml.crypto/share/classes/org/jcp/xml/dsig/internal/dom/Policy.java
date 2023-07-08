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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.AccessController;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.base.share.classes.java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The secure validation policy as specified by the
 * jdk.xml.dsig.secureValidationPolicy security property.
 */
public final class Policy {

    // all restrictions are initialized to be unconstrained
    private static final Set<URI> disallowedAlgs = new HashSet<>();
    private static int maxTrans = Integer.MAX_VALUE;
    private static int maxRefs = Integer.MAX_VALUE;
    private static final Set<String> disallowedRefUriSchemes = new HashSet<>();
    private static final Map<String, Integer> minKeyMap = new HashMap<>();
    private static boolean noDuplicateIds = false;
    private static boolean noRMLoops = false;

    static {
        try {
            initialize();
        } catch (Exception e) {
            throw new SecurityException(
                "Cannot initialize the secure validation policy", e);
        }
    }

    private Policy() {}

    private static void initialize() {
        @SuppressWarnings("removal")
        String prop =
            AccessController.doPrivileged((PrivilegedAction<String>) () ->
                Security.getProperty("jdk.xml.dsig.secureValidationPolicy"));
        if (prop == null || prop.isEmpty()) {
            // no policy specified, so don't enforce any restrictions
            return;
        }
        String[] entries = prop.split(",");
        for (String entry : entries) {
            String[] tokens = entry.split("\\s");
            String type = tokens[0];
            switch(type) {
                case "disallowAlg":
                    if (tokens.length != 2) {
                        error(entry);
                    }
                    disallowedAlgs.add(URI.create(tokens[1]));
                    break;
                case "maxTransforms":
                    if (tokens.length != 2) {
                        error(entry);
                    }
                    maxTrans = Integer.parseUnsignedInt(tokens[1]);
                    break;
                case "maxReferences":
                    if (tokens.length != 2) {
                        error(entry);
                    }
                    maxRefs = Integer.parseUnsignedInt(tokens[1]);
                    break;
                case "disallowReferenceUriSchemes":
                    if (tokens.length == 1) {
                        error(entry);
                    }
                    for (int i = 1; i < tokens.length; i++) {
                        String scheme = tokens[i];
                        disallowedRefUriSchemes.add(
                            scheme.toLowerCase(Locale.ROOT));
                    }
                    break;
                case "minKeySize":
                    if (tokens.length != 3) {
                        error(entry);
                    }
                    minKeyMap.put(tokens[1],
                                  Integer.parseUnsignedInt(tokens[2]));
                    break;
                case "noDuplicateIds":
                    if (tokens.length != 1) {
                        error(entry);
                    }
                    noDuplicateIds = true;
                    break;
                case "noRetrievalMethodLoops":
                    if (tokens.length != 1) {
                        error(entry);
                    }
                    noRMLoops = true;
                    break;
                default:
                    error(entry);
            }
        }
    }

    public static boolean restrictAlg(String alg) {
        try {
            URI uri = new URI(alg);
            return disallowedAlgs.contains(uri);
        } catch (URISyntaxException use) {
            return false;
        }
    }

    public static boolean restrictNumTransforms(int numTrans) {
        return (numTrans > maxTrans);
    }

    public static boolean restrictNumReferences(int numRefs) {
        return (numRefs > maxRefs);
    }

    public static boolean restrictReferenceUriScheme(String uri) {
        if (uri != null) {
            String scheme = java.net.URI.create(uri).getScheme();
            if (scheme != null) {
                return disallowedRefUriSchemes.contains(
                    scheme.toLowerCase(Locale.ROOT));
            }
        }
        return false;
    }

    public static boolean restrictKey(String type, int size) {
        return (size < minKeyMap.getOrDefault(type, 0));
    }

    public static boolean restrictDuplicateIds() {
        return noDuplicateIds;
    }

    public static boolean restrictRetrievalMethodLoops() {
        return noRMLoops;
    }

    public static Set<URI> disabledAlgs() {
        return Collections.unmodifiableSet(disallowedAlgs);
    }

    public static int maxTransforms() {
        return maxTrans;
    }

    public static int maxReferences() {
        return maxRefs;
    }

    public static Set<String> disabledReferenceUriSchemes() {
        return Collections.unmodifiableSet(disallowedRefUriSchemes);
    }

    public static int minKeySize(String type) {
        return minKeyMap.getOrDefault(type, 0);
    }

    private static void error(String entry) {
        throw new IllegalArgumentException(
            "Invalid jdk.xml.dsig.secureValidationPolicy entry: " + entry);
    }
}
