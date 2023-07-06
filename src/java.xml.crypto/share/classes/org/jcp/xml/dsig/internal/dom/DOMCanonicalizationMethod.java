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

import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based abstract implementation of CanonicalizationMethod.
 *
 */
public class DOMCanonicalizationMethod extends DOMTransform
    implements CanonicalizationMethod {

    private static final Set<String> C14N_ALGORITHMS;

    static {
        Set<String> algorithms = new HashSet<>();
        algorithms.add(CanonicalizationMethod.INCLUSIVE);
        algorithms.add(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS);
        algorithms.add(CanonicalizationMethod.EXCLUSIVE);
        algorithms.add(CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS);
        algorithms.add(DOMCanonicalXMLC14N11Method.C14N_11);
        algorithms.add(DOMCanonicalXMLC14N11Method.C14N_11_WITH_COMMENTS);
        C14N_ALGORITHMS = Collections.unmodifiableSet(algorithms);
    }

    /**
     * Creates a {@code DOMCanonicalizationMethod}.
     *
     * @param spi TransformService
     */
    public DOMCanonicalizationMethod(TransformService spi)
        throws InvalidAlgorithmParameterException
    {
        super(spi);
        if (!(spi instanceof ApacheCanonicalizer) && !isC14Nalg(spi.getAlgorithm())) {
            throw new InvalidAlgorithmParameterException("Illegal CanonicalizationMethod");
        }
    }

    /**
     * Creates a {@code DOMCanonicalizationMethod} from an element. It unmarshals any
     * algorithm-specific input parameters.
     *
     * @param cmElem a CanonicalizationMethod element
     */
    public DOMCanonicalizationMethod(Element cmElem, XMLCryptoContext context,
                                     Provider provider)
        throws MarshalException
    {
        super(cmElem, context, provider);
        if (!(spi instanceof ApacheCanonicalizer) && !isC14Nalg(spi.getAlgorithm())) {
            throw new MarshalException("Illegal CanonicalizationMethod");
        }
    }

    /**
     * Canonicalizes the specified data using the underlying canonicalization
     * algorithm. This is a convenience method that is equivalent to invoking
     * the {@link #transform transform} method.
     *
     * @param data the data to be canonicalized
     * @param xc the {@code XMLCryptoContext} containing
     *     additional context (may be {@code null} if not applicable)
     * @return the canonicalized data
     * @throws NullPointerException if {@code data} is {@code null}
     * @throws TransformException if an unexpected error occurs while
     *    canonicalizing the data
     */
    public Data canonicalize(Data data, XMLCryptoContext xc)
        throws TransformException
    {
        return transform(data, xc);
    }

    public Data canonicalize(Data data, XMLCryptoContext xc, OutputStream os)
        throws TransformException
    {
        return transform(data, xc, os);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CanonicalizationMethod ocm)) {
            return false;
        }

        return getAlgorithm().equals(ocm.getAlgorithm()) &&
            DOMUtils.paramsEqual(getParameterSpec(), ocm.getParameterSpec());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getAlgorithm().hashCode();
        AlgorithmParameterSpec spec = getParameterSpec();
        if (spec != null) {
            result = 31 * result + spec.hashCode();
        }

        return result;
    }

    private static boolean isC14Nalg(String alg) {
        return alg != null && C14N_ALGORITHMS.contains(alg);
    }

}
