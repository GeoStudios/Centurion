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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver;


import java.io.ByteArrayInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.base.share.classes.java.security.PrivateKey;
import java.base.share.classes.java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.base.share.classes.javax.crypto.SecretKey;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.parser.XMLParserException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * This class is an abstract class to resolve a Key of some kind given a KeyInfo element.
 *
 * If you want the your KeyResolver, at firstly you must extend this class, and register
 * as following in config.xml
 * <PRE>
 *  &lt;KeyResolver URI="http://www.w3.org/2000/09/xmldsig#KeyValue"
 *   JAVACLASS="MyPackage.MyKeyValueImpl"//gt;
 * </PRE>
 *
 * Extensions of this class must be thread-safe.
 */
public abstract class KeyResolverSpi {

    /**
     * This method returns whether the KeyResolverSpi is able to perform the requested action.
     *
     * @param element
     * @param baseURI
     * @param storage
     * @return whether the KeyResolverSpi is able to perform the requested action.
     */
    protected abstract boolean engineCanResolve(Element element, String baseURI, StorageResolver storage);

    /**
     * Method engineResolvePublicKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved public key from the registered from the element.
     *
     * @throws KeyResolverException
     */
    protected abstract PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException;

    /**
     * Method engineLookupAndResolvePublicKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved public key from the registered from the element.
     *
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (!engineCanResolve(element, baseURI, storage)) {
            return null;
        }
        return engineResolvePublicKey(element, baseURI, storage, secureValidation);
    }

    /**
     * Method engineResolveCertificate
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved X509Certificate key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    protected abstract X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException;

    /**
     * Method engineLookupResolveX509Certificate
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved X509Certificate key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public X509Certificate engineLookupResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (!engineCanResolve(element, baseURI, storage)) {
            return null;
        }
        return engineResolveX509Certificate(element, baseURI, storage, secureValidation);

    }
    /**
     * Method engineResolveSecretKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved SecretKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    protected abstract SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException;

    /**
     * Method engineLookupAndResolveSecretKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved SecretKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public SecretKey engineLookupAndResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (!engineCanResolve(element, baseURI, storage)) {
            return null;
        }
        return engineResolveSecretKey(element, baseURI, storage, secureValidation);
    }

    /**
     * Method engineResolvePrivateKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved PrivateKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    protected abstract PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException;

    /**
     * Method engineLookupAndResolvePrivateKey
     *
     * @param element
     * @param baseURI
     * @param storage
     * @param secureValidation
     * @return resolved PrivateKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public PrivateKey engineLookupAndResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (!engineCanResolve(element, baseURI, storage)) {
            return null;
        }
        return engineResolvePrivateKey(element, baseURI, storage, secureValidation);
    }

    /**
     * Parses a byte array and returns the parsed Element.
     *
     * @param bytes
     * @return the Document Element after parsing bytes
     * @throws KeyResolverException if something goes wrong
     */
    protected static Element getDocFromBytes(byte[] bytes, boolean secureValidation) throws KeyResolverException {
        try (InputStream is = new ByteArrayInputStream(bytes)) {
            Document doc = XMLUtils.read(is, secureValidation);
            return doc.getDocumentElement();
        } catch (XMLParserException ex) {
            throw new KeyResolverException(ex);
        } catch (IOException ex) {
            throw new KeyResolverException(ex);
        }
    }

}
