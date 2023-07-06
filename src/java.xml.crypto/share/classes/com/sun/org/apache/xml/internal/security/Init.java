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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security;

import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Enumeration;
import java.util.java.util.java.util.java.util.List;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transform;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.ElementProxy;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.I18n;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolver;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class does the configuration of the library. This includes creating
 * the mapping of Canonicalization and Transform algorithms. Initialization is
 * done by calling {@link Init#init} which should be done in any static block
 * of the files of this library. We ensure that this call is only executed once.
 */
public class Init {

    /** The namespace for CONF file **/
    public static final String CONF_NS = "http://www.xmlsecurity.org/NS/#configuration";

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(Init.class);

    /** Field alreadyInitialized */
    private static boolean alreadyInitialized = false;

    /**
     * Method isInitialized
     * @return true if the library is already initialized.
     */
    public static final synchronized boolean isInitialized() {
        return Init.alreadyInitialized;
    }

    /**
     * Method init
     *
     */
    public static synchronized void init() {
        if (alreadyInitialized) {
            return;
        }

        @SuppressWarnings("removal")
        InputStream is =
            AccessController.doPrivileged(
                (PrivilegedAction<InputStream>)
                    () -> {
                        String cfile =
                            System.getProperty("com.sun.org.apache.xml.internal.security.resource.config");
                        if (cfile == null) {
                            return null;
                        }
                        return getResourceAsStream(cfile, Init.class);
                    }
                );
        if (is == null) {
            dynamicInit();
        } else {
            fileInit(is);
            try {
                is.close();
            } catch (IOException ex) {
                LOG.warn(ex.getMessage());
            }
        }

        alreadyInitialized = true;
    }

    /**
     * Dynamically initialise the library by registering the default algorithms/implementations
     */
    @SuppressWarnings("removal")
    private static void dynamicInit() {
        //
        // Load the Resource Bundle - the default is the English resource bundle.
        // To load another resource bundle, call I18n.init(...) before calling this
        // method.
        //
        I18n.init("en", "US");

        LOG.debug("Registering default algorithms");

        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>(){
                @Override public Void run() throws XMLSecurityException {
                    //
                    // Bind the default prefixes
                    //
                    ElementProxy.registerDefaultPrefixes();

                    //
                    // Set the default Transforms
                    //
                    Transform.registerDefaultAlgorithms();

                    //
                    // Set the default signature algorithms
                    //
                    SignatureAlgorithm.registerDefaultAlgorithms();

                    //
                    // Set the default JCE algorithms
                    //
                    JCEMapper.registerDefaultAlgorithms();

                    //
                    // Set the default c14n algorithms
                    //
                    Canonicalizer.registerDefaultAlgorithms();

                    //
                    // Register the default resolvers
                    //
                    ResourceResolver.registerDefaultResolvers();

                    //
                    // Register the default key resolvers
                    //
                    KeyResolver.registerDefaultResolvers();

                    return null;
                }
            });
        } catch (PrivilegedActionException ex) {
            XMLSecurityException xse = (XMLSecurityException)ex.getException();
            LOG.error(xse.getMessage(), xse);
            xse.printStackTrace();
        }
    }

    /**
     * Initialise the library from a configuration file
     */
    private static void fileInit(InputStream is) {
        try {
            /* read library configuration file */
            Document doc = XMLUtils.read(is, true);
            Node config = doc.getFirstChild();
            for (; config != null; config = config.getNextSibling()) {
                if ("Configuration".equals(config.getLocalName())) {
                    break;
                }
            }
            if (config == null) {
                LOG.error("Error in reading configuration file - Configuration element not found");
                return;
            }
            for (Node el = config.getFirstChild(); el != null; el = el.getNextSibling()) {
                if (Node.ELEMENT_NODE != el.getNodeType()) {
                    continue;
                }
                String tag = el.getLocalName();
                if ("ResourceBundles".equals(tag)) {
                    Element resource = (Element)el;
                    /* configure internationalization */
                    Attr langAttr = resource.getAttributeNodeNS(null, "defaultLanguageCode");
                    Attr countryAttr = resource.getAttributeNodeNS(null, "defaultCountryCode");
                    String languageCode =
                        (langAttr == null) ? null : langAttr.getNodeValue();
                    String countryCode =
                        (countryAttr == null) ? null : countryAttr.getNodeValue();
                    I18n.init(languageCode, countryCode);
                }

                if ("CanonicalizationMethods".equals(tag)) {
                    Element[] list =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "CanonicalizationMethod");

                    for (Element element : list) {
                        String uri = element.getAttributeNS(null, "URI");
                        String javaClass =
                            element.getAttributeNS(null, "JAVACLASS");
                        try {
                            Canonicalizer.register(uri, javaClass);
                            LOG.debug("Canonicalizer.register({}, {})", uri, javaClass);
                        } catch (ClassNotFoundException e) {
                            Object[] exArgs = { uri, javaClass };
                            LOG.error(I18n.translate("algorithm.classDoesNotExist", exArgs));
                        }
                    }
                }

                if ("TransformAlgorithms".equals(tag)) {
                    Element[] tranElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "TransformAlgorithm");

                    for (Element element : tranElem) {
                        String uri = element.getAttributeNS(null, "URI");
                        String javaClass =
                            element.getAttributeNS(null, "JAVACLASS");
                        try {
                            Transform.register(uri, javaClass);
                            LOG.debug("Transform.register({}, {})", uri, javaClass);
                        } catch (ClassNotFoundException e) {
                            Object[] exArgs = { uri, javaClass };

                            LOG.error(I18n.translate("algorithm.classDoesNotExist", exArgs));
                        } catch (NoClassDefFoundError ex) {
                            LOG.warn("Not able to found dependencies for algorithm, I'll keep working.");
                        }
                    }
                }

                if ("JCEAlgorithmMappings".equals(tag)) {
                    Node algorithmsNode = ((Element)el).getElementsByTagName("Algorithms").item(0);
                    if (algorithmsNode != null) {
                        Element[] algorithms =
                            XMLUtils.selectNodes(algorithmsNode.getFirstChild(), CONF_NS, "Algorithm");
                        for (Element element : algorithms) {
                            String id = element.getAttributeNS(null, "URI");
                            JCEMapper.register(id, new JCEMapper.Algorithm(element));
                        }
                    }
                }

                if ("SignatureAlgorithms".equals(tag)) {
                    Element[] sigElems =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "SignatureAlgorithm");

                    for (Element sigElem : sigElems) {
                        String uri = sigElem.getAttributeNS(null, "URI");
                        String javaClass =
                            sigElem.getAttributeNS(null, "JAVACLASS");

                        /** $todo$ handle registering */

                        try {
                            SignatureAlgorithm.register(uri, javaClass);
                            LOG.debug("SignatureAlgorithm.register({}, {})", uri, javaClass);
                        } catch (ClassNotFoundException e) {
                            Object[] exArgs = { uri, javaClass };

                            LOG.error(I18n.translate("algorithm.classDoesNotExist", exArgs));
                        }
                    }
                }

                if ("ResourceResolvers".equals(tag)) {
                    Element[] resolverElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "Resolver");
                    List<String> classNames = new ArrayList<>(resolverElem.length);
                    for (Element element : resolverElem) {
                        String javaClass =
                            element.getAttributeNS(null, "JAVACLASS");
                        String description =
                            element.getAttributeNS(null, "DESCRIPTION");

                        if (description != null && description.length() > 0) {
                            LOG.debug("Register Resolver: {}: {}", javaClass, description);
                        } else {
                            LOG.debug("Register Resolver: {}: For unknown purposes", javaClass);
                        }
                        classNames.add(javaClass);
                    }
                    ResourceResolver.registerClassNames(classNames);
                }

                if ("KeyResolver".equals(tag)){
                    Element[] resolverElem =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "Resolver");
                    List<String> classNames = new ArrayList<>(resolverElem.length);
                    for (Element element : resolverElem) {
                        String javaClass =
                            element.getAttributeNS(null, "JAVACLASS");
                        String description =
                            element.getAttributeNS(null, "DESCRIPTION");

                        if (description != null && description.length() > 0) {
                            LOG.debug("Register Resolver: {}: {}", javaClass, description);
                        } else {
                            LOG.debug("Register Resolver: {}: For unknown purposes", javaClass);
                        }
                        classNames.add(javaClass);
                    }
                    KeyResolver.registerClassNames(classNames);
                }

                if ("PrefixMappings".equals(tag)){
                    LOG.debug("Now I try to bind prefixes:");

                    Element[] nl =
                        XMLUtils.selectNodes(el.getFirstChild(), CONF_NS, "PrefixMapping");

                    for (Element element : nl) {
                        String namespace = element.getAttributeNS(null, "namespace");
                        String prefix = element.getAttributeNS(null, "prefix");
                        LOG.debug("Now I try to bind {} to {}", prefix, namespace);
                        ElementProxy.setDefaultPrefix(namespace, prefix);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Bad: ", e);
        }
    }
    /**
     * Load a given resource. <p></p> This method will try to load the resource
     * using the following methods (in order):
     * <ul>
     * <li>From Thread.currentThread().getContextClassLoader()
     * <li>From ClassLoaderUtil.class.getClassLoader()
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName The name of the resource to load
     * @param callingClass The Class object of the calling object
     */
    public static URL getResource(String resourceName, Class<?> callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null && resourceName.charAt(0) == '/') {
            //certain classloaders need it without the leading /
            url =
                    Thread.currentThread().getContextClassLoader().getResource(
                            resourceName.substring(1)
                    );
        }

        ClassLoader cluClassloader = Init.class.getClassLoader();
        if (cluClassloader == null) {
            cluClassloader = ClassLoader.getSystemClassLoader();
        }
        if (url == null) {
            url = cluClassloader.getResource(resourceName);
        }
        if (url == null && resourceName.charAt(0) == '/') {
            //certain classloaders need it without the leading /
            url = cluClassloader.getResource(resourceName.substring(1));
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if (url == null) {
            url = callingClass.getResource(resourceName);
        }

        if (url == null && resourceName.charAt(0) != '/') {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    /**
     * Load a given resources. <p></p> This method will try to load the resources
     * using the following methods (in order):
     * <ul>
     * <li>From Thread.currentThread().getContextClassLoader()
     * <li>From ClassLoaderUtil.class.getClassLoader()
     * <li>callingClass.getClassLoader()
     * </ul>
     *
     * @param resourceName The name of the resource to load
     * @param callingClass The Class object of the calling object
     */
    private static List<URL> getResources(String resourceName, Class<?> callingClass) {
        List<URL> ret = new ArrayList<>();
        Enumeration<URL> urls = new Enumeration<URL>() {
            public boolean hasMoreElements() {
                return false;
            }
            public URL nextElement() {
                return null;
            }

        };
        try {
            urls = Thread.currentThread().getContextClassLoader().getResources(resourceName);
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
            //ignore
        }
        if (!urls.hasMoreElements() && resourceName.charAt(0) == '/') {
            //certain classloaders need it without the leading /
            try {
                urls =
                        Thread.currentThread().getContextClassLoader().getResources(
                                resourceName.substring(1)
                        );
            } catch (IOException e) {
                LOG.debug(e.getMessage(), e);
                // ignore
            }
        }

        ClassLoader cluClassloader = Init.class.getClassLoader();
        if (cluClassloader == null) {
            cluClassloader = ClassLoader.getSystemClassLoader();
        }
        if (!urls.hasMoreElements()) {
            try {
                urls = cluClassloader.getResources(resourceName);
            } catch (IOException e) {
                LOG.debug(e.getMessage(), e);
                // ignore
            }
        }
        if (!urls.hasMoreElements() && resourceName.charAt(0) == '/') {
            //certain classloaders need it without the leading /
            try {
                urls = cluClassloader.getResources(resourceName.substring(1));
            } catch (IOException e) {
                LOG.debug(e.getMessage(), e);
                // ignore
            }
        }

        if (!urls.hasMoreElements()) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                try {
                    urls = cl.getResources(resourceName);
                } catch (IOException e) {
                    LOG.debug(e.getMessage(), e);
                    // ignore
                }
            }
        }

        if (!urls.hasMoreElements()) {
            URL url = callingClass.getResource(resourceName);
            if (url != null) {
                ret.add(url);
            }
        }
        while (urls.hasMoreElements()) {
            ret.add(urls.nextElement());
        }

        if (ret.isEmpty() && resourceName != null && resourceName.charAt(0) != '/') {
            return getResources('/' + resourceName, callingClass);
        }
        return ret;
    }

    /**
     * This is a convenience method to load a resource as a stream. <p></p> The
     * algorithm used to find the resource is given in getResource()
     *
     * @param resourceName The name of the resource to load
     * @param callingClass The Class object of the calling object
     */
    private static InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);

        try {
            return (url != null) ? url.openStream() : null;
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
            return null;
        }
    }
}
