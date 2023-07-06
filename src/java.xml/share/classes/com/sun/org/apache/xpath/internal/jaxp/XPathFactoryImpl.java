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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.jaxp;

import java.xml.share.classes.com.sun.org.apache.xalan.internal.res.XSLMessages;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import javax.xml.XMLConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlFeatures;

/**
 * The XPathFactory builds XPaths.
 *
 *
 * @LastModified: May 2021
 */
public  class XPathFactoryImpl extends XPathFactory {

        /**
         * <p>Name of class as a constant to use for debugging.</p>
         */
        private static final String CLASS_NAME = "XPathFactoryImpl";

        /**
         *<p>XPathFunctionResolver for this XPathFactory and created XPaths.</p>
         */
        private XPathFunctionResolver xPathFunctionResolver = null;

        /**
         * <p>XPathVariableResolver for this XPathFactory and created XPaths</p>
         */
        private XPathVariableResolver xPathVariableResolver = null;

        /**
         * <p>State of secure processing feature.</p>
         */
        private boolean _isNotSecureProcessing = true;
        /**
         * <p>State of secure mode.</p>
         */
        private boolean _isSecureMode = false;

        /**
         * XML Features manager
         */
        private final JdkXmlFeatures _featureManager;

        /**
         * javax.xml.xpath.XPathFactory implementation.
         */
        @SuppressWarnings("removal")
        public XPathFactoryImpl() {
            if (System.getSecurityManager() != null) {
                _isSecureMode = true;
                _isNotSecureProcessing = false;
            }
            _featureManager = new JdkXmlFeatures(!_isNotSecureProcessing);
        }
        /**
         * <p>Is specified object model supported by this
         * <code>XPathFactory</code>?</p>
         *
         * @param objectModel Specifies the object model which the returned
         * <code>XPathFactory</code> will understand.
         *
         * @return <code>true</code> if <code>XPathFactory</code> supports
         * <code>objectModel</code>, else <code>false</code>.
         *
         * @throws NullPointerException If <code>objectModel</code> is <code>null</code>.
         * @throws IllegalArgumentException If <code>objectModel.length() == 0</code>.
         */
        public boolean isObjectModelSupported(String objectModel) {

            if (objectModel == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_OBJECT_MODEL_NULL,
                        new Object[] { this.getClass().getName() } );

                throw new NullPointerException( fmsg );
            }

            if (objectModel.length() == 0) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_OBJECT_MODEL_EMPTY,
                        new Object[] { this.getClass().getName() } );
                throw new IllegalArgumentException( fmsg );
            }

            // know how to support default object model, W3C DOM
            return objectModel.equals(XPathFactory.DEFAULT_OBJECT_MODEL_URI);

            // don't know how to support anything else
        }

        /**
         * <p>Returns a new <code>XPath</code> object using the underlying
         * object model determined when the factory was instantiated.</p>
         *
         * @return New <code>XPath</code>
         */
        public javax.xml.xpath.XPath newXPath() {
            return new com.sun.org.apache.xpath.internal.jaxp.XPathImpl(
                    xPathVariableResolver, xPathFunctionResolver,
                    !_isNotSecureProcessing, _featureManager );
        }

        /**
         * <p>Set a feature for this <code>XPathFactory</code> and
         * <code>XPath</code>s created by this factory.</p>
         *
         * <p>
         * Feature names are fully qualified {@link java.net.URI}s.
         * Implementations may define their own features.
         * An {@link XPathFactoryConfigurationException} is thrown if this
         * <code>XPathFactory</code> or the <code>XPath</code>s
         *  it creates cannot support the feature.
         * It is possible for an <code>XPathFactory</code> to expose a feature
         * value but be unable to change its state.
         * </p>
         *
         * <p>See {@link javax.xml.xpath.XPathFactory} for full documentation
         * of specific features.</p>
         *
         * @param name Feature name.
         * @param value Is feature state <code>true</code> or <code>false</code>.
         *
         * @throws XPathFactoryConfigurationException if this
         * <code>XPathFactory</code> or the <code>XPath</code>s
         *   it creates cannot support this feature.
         * @throws NullPointerException if <code>name</code> is
         * <code>null</code>.
         */
        @SuppressWarnings("deprecation")
        public void setFeature(String name, boolean value)
                throws XPathFactoryConfigurationException {

            // feature name cannot be null
            if (name == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_FEATURE_NAME_NULL,
                        new Object[] { CLASS_NAME,  value } );
                throw new NullPointerException( fmsg );
             }

            // secure processing?
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                if ((_isSecureMode) && (!value)) {
                    String fmsg = XSLMessages.createXPATHMessage(
                            XPATHErrorResources.ER_SECUREPROCESSING_FEATURE,
                            new Object[] { name, CLASS_NAME, value } );
                    throw new XPathFactoryConfigurationException( fmsg );
                }

                _isNotSecureProcessing = !value;
                if (value && _featureManager != null) {
                    _featureManager.setFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION,
                            JdkProperty.State.FSP, false);
                }

                // all done processing feature
                return;
            }
            if (name.equals(JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM)) {
                // for compatibility, in secure mode, useServicesMechanism is determined by the constructor
                if (_isSecureMode)
                    return;
            }

            if (_featureManager != null &&
                    _featureManager.setFeature(name, JdkProperty.State.APIPROPERTY, value)) {
                return;
            }

            // unknown feature
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_FEATURE_UNKNOWN,
                    new Object[] { name, CLASS_NAME, value } );
            throw new XPathFactoryConfigurationException( fmsg );
        }

        /**
         * <p>Get the state of the named feature.</p>
         *
         * <p>
         * Feature names are fully qualified {@link java.net.URI}s.
         * Implementations may define their own features.
         * An {@link XPathFactoryConfigurationException} is thrown if this
         * <code>XPathFactory</code> or the <code>XPath</code>s
         * it creates cannot support the feature.
         * It is possible for an <code>XPathFactory</code> to expose a feature
         * value but be unable to change its state.
         * </p>
         *
         * @param name Feature name.
         *
         * @return State of the named feature.
         *
         * @throws XPathFactoryConfigurationException if this
         * <code>XPathFactory</code> or the <code>XPath</code>s
         *   it creates cannot support this feature.
         * @throws NullPointerException if <code>name</code> is
         * <code>null</code>.
         */
        public boolean getFeature(String name)
                throws XPathFactoryConfigurationException {

            // feature name cannot be null
            if (name == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_GETTING_NULL_FEATURE,
                        new Object[] { CLASS_NAME } );
                throw new NullPointerException( fmsg );
            }

            // secure processing?
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                return !_isNotSecureProcessing;
            }

            /** Check to see if the property is managed by the feature manager **/
            int index = _featureManager.getIndex(name);
            if (index > -1) {
                return _featureManager.getFeature(index);
            }

            // unknown feature
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_GETTING_UNKNOWN_FEATURE,
                    new Object[] { name, CLASS_NAME } );

            throw new XPathFactoryConfigurationException( fmsg );
        }

        /**
         * <p>Establish a default function resolver.</p>
         *
         * <p>Any <code>XPath</code> objects constructed from this factory will use
         * the specified resolver by default.</p>
         *
         * <p>A <code>NullPointerException</code> is thrown if
         * <code>resolver</code> is <code>null</code>.</p>
         *
         * @param resolver XPath function resolver.
         *
         * @throws NullPointerException If <code>resolver</code> is
         * <code>null</code>.
         */
        public void setXPathFunctionResolver(XPathFunctionResolver resolver) {

            // resolver cannot be null
            if (resolver == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_NULL_XPATH_FUNCTION_RESOLVER,
                        new Object[] {  CLASS_NAME } );
                throw new NullPointerException( fmsg );
            }

            xPathFunctionResolver = resolver;
        }

        /**
         * <p>Establish a default variable resolver.</p>
         *
         * <p>Any <code>XPath</code> objects constructed from this factory will use
         * the specified resolver by default.</p>
         *
         * <p>A <code>NullPointerException</code> is thrown if <code>resolver</code> is <code>null</code>.</p>
         *
         * @param resolver Variable resolver.
         *
         *  @throws NullPointerException If <code>resolver</code> is
         * <code>null</code>.
         */
        public void setXPathVariableResolver(XPathVariableResolver resolver) {

                // resolver cannot be null
                if (resolver == null) {
                    String fmsg = XSLMessages.createXPATHMessage(
                            XPATHErrorResources.ER_NULL_XPATH_VARIABLE_RESOLVER,
                            new Object[] {  CLASS_NAME } );
                    throw new NullPointerException( fmsg );
                }

                xPathVariableResolver = resolver;
        }
}
