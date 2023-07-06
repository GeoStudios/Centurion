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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature;


import java.util.Collections;
import java.util.java.util.java.util.java.util.List;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Holds the result of a Reference validation.
 */
public class VerifiedReference {

    private final boolean valid;
    private final String uri;
    private final List<VerifiedReference> manifestReferences;

    /**
     * @param valid Whether this Reference was successfully validated or not
     * @param uri The URI of this Reference
     * @param manifestReferences If this reference is a reference to a Manifest, this holds the list
     * of verified referenes associated with this Manifest
     */
    public VerifiedReference(boolean valid, String uri, List<VerifiedReference> manifestReferences) {
        this.valid = valid;
        this.uri = uri;
        if (manifestReferences != null) {
            this.manifestReferences = manifestReferences;
        } else {
            this.manifestReferences = Collections.emptyList();
        }
    }

    public boolean isValid() {
        return valid;
    }

    public String getUri() {
        return uri;
    }

    public List<VerifiedReference> getManifestReferences() {
        return Collections.unmodifiableList(manifestReferences);
    }
}
