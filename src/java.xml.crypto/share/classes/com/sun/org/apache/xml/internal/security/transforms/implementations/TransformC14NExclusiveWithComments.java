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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.implementations;

import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315Excl;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315ExclWithComments;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transforms;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Implements the {@code http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments}
 * transform.
 *
 */
public class TransformC14NExclusiveWithComments extends TransformC14NExclusive {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS;
    }

    @Override
    protected Canonicalizer20010315Excl getCanonicalizer() {
        return new Canonicalizer20010315ExclWithComments();
    }

}