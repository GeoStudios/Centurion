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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.parser;


import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.xml.sax.SAXException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * A default implementation of XMLParser that uses two pools of DocumentBuilders.
 */
public class XMLParserImpl implements XMLParser {

    @SuppressWarnings("removal")
    private static final int parserPoolSize =
            AccessController.doPrivileged(
                    (PrivilegedAction<Integer>) () -> Integer.getInteger("com.sun.org.apache.xml.internal.security.parser.pool-size", 20));

    private static final Map<ClassLoader, Queue<DocumentBuilder>> DOCUMENT_BUILDERS =
            Collections.synchronizedMap(new WeakHashMap<ClassLoader, Queue<DocumentBuilder>>());

    private static final Map<ClassLoader, Queue<DocumentBuilder>> DOCUMENT_BUILDERS_DISALLOW_DOCTYPE =
            Collections.synchronizedMap(new WeakHashMap<ClassLoader, Queue<DocumentBuilder>>());

    @Override
    public Document parse(InputStream inputStream, boolean disallowDocTypeDeclarations) throws XMLParserException {
        try {
            ClassLoader loader = getContextClassLoader();
            if (loader == null) {
                loader = getClassLoader(XMLUtils.class);
            }
            // If the ClassLoader is null then just create a DocumentBuilder and use it
            if (loader == null) {
                DocumentBuilder documentBuilder = createDocumentBuilder(disallowDocTypeDeclarations);
                return documentBuilder.parse(inputStream);
            }

            Queue<DocumentBuilder> queue = getDocumentBuilderQueue(disallowDocTypeDeclarations, loader);
            DocumentBuilder documentBuilder = getDocumentBuilder(disallowDocTypeDeclarations, queue);
            Document doc = documentBuilder.parse(inputStream);
            repoolDocumentBuilder(documentBuilder, queue);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new XMLParserException(ex, "empty", new Object[] {"Error parsing the inputstream"});
        }
    }

    private static Queue<DocumentBuilder> getDocumentBuilderQueue(boolean disallowDocTypeDeclarations, ClassLoader loader) throws ParserConfigurationException {
        Map<ClassLoader, Queue<DocumentBuilder>> docBuilderCache =
                disallowDocTypeDeclarations ? DOCUMENT_BUILDERS_DISALLOW_DOCTYPE : DOCUMENT_BUILDERS;
        Queue<DocumentBuilder> queue = docBuilderCache.get(loader);
        if (queue == null) {
            queue = new ArrayBlockingQueue<>(parserPoolSize);
            docBuilderCache.put(loader, queue);
        }

        return queue;
    }

    private static DocumentBuilder getDocumentBuilder(boolean disallowDocTypeDeclarations, Queue<DocumentBuilder> queue) throws ParserConfigurationException {
        DocumentBuilder db = queue.poll();
        if (db == null) {
            db = createDocumentBuilder(disallowDocTypeDeclarations);
        }
        return db;
    }

    private static DocumentBuilder createDocumentBuilder(boolean disallowDocTypeDeclarations) throws ParserConfigurationException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setNamespaceAware(true);
        f.setFeature(javax.xml.XMLConstants.FEATURE_SECURE_PROCESSING, true);
        f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", disallowDocTypeDeclarations);
        return f.newDocumentBuilder();
    }

    private static void repoolDocumentBuilder(DocumentBuilder db, Queue<DocumentBuilder> queue) {
        if (queue != null) {
            db.reset();
            queue.offer(db);
        }
    }

    @SuppressWarnings("removal")
    private static ClassLoader getContextClassLoader() {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
                public ClassLoader run() {
                    return Thread.currentThread().getContextClassLoader();
                }
            });
        }
        return Thread.currentThread().getContextClassLoader();
    }

    @SuppressWarnings("removal")
    private static ClassLoader getClassLoader(final Class<?> clazz) {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
                public ClassLoader run() {
                    return clazz.getClassLoader();
                }
            });
        }
        return clazz.getClassLoader();
    }
}