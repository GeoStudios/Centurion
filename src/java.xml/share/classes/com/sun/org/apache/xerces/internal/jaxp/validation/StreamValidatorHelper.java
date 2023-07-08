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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.parsers.XML11Configuration;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.java.io.java.io.java.io.IOException;
import java.lang.ref.SoftReference;
import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;

/**
 * <p>
 * A validator helper for <code>StreamSource</code>s.</p>
 *
 * @LastModified: May 2021
 */
final class StreamValidatorHelper implements ValidatorHelper {

    // feature identifiers
    /**
     * Feature identifier: parser settings.
     */
    private static final String PARSER_SETTINGS
            = Constants.XERCES_FEATURE_PREFIX + Constants.PARSER_SETTINGS;

    // property identifiers
    /**
     * Property identifier: entity resolver.
     */
    private static final String ENTITY_RESOLVER
            = Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_RESOLVER_PROPERTY;

    /**
     * Property identifier: error handler.
     */
    private static final String ERROR_HANDLER
            = Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_HANDLER_PROPERTY;

    /**
     * Property identifier: error reporter.
     */
    private static final String ERROR_REPORTER
            = Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    /**
     * Property identifier: XML Schema validator.
     */
    private static final String SCHEMA_VALIDATOR
            = Constants.XERCES_PROPERTY_PREFIX + Constants.SCHEMA_VALIDATOR_PROPERTY;

    /**
     * Property identifier: symbol table.
     */
    private static final String SYMBOL_TABLE
            = Constants.XERCES_PROPERTY_PREFIX + Constants.SYMBOL_TABLE_PROPERTY;

    /**
     * Property identifier: validation manager.
     */
    private static final String VALIDATION_MANAGER
            = Constants.XERCES_PROPERTY_PREFIX + Constants.VALIDATION_MANAGER_PROPERTY;

    /**
     * Property id: security manager.
     */
    private static final String SECURITY_MANAGER
            = Constants.XERCES_PROPERTY_PREFIX + Constants.SECURITY_MANAGER_PROPERTY;

    //
    // Data
    //
    /**
     * SoftReference to parser configuration. *
     */
    private SoftReference<XMLParserConfiguration> fConfiguration = new SoftReference<>(null);

    /**
     * Schema validator. *
     */
    private final com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator fSchemaValidator;

    /**
     * Component manager. *
     */
    private final XMLSchemaValidatorComponentManager fComponentManager;

    private ValidatorHandlerImpl handler = null;

    public StreamValidatorHelper(XMLSchemaValidatorComponentManager componentManager) {
        fComponentManager = componentManager;
        fSchemaValidator = (com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator)
                fComponentManager.getProperty(SCHEMA_VALIDATOR);
    }

    public void validate(Source source, Result result)
            throws SAXException, IOException {
        if (result == null || result instanceof StreamResult) {
            final StreamSource streamSource = (StreamSource) source;
            TransformerHandler identityTransformerHandler;

            if (result != null) {
                try {
                    SAXTransformerFactory tf = JdkXmlUtils.getSAXTransformFactory(
                            fComponentManager.getFeature(JdkConstants.OVERRIDE_PARSER));

                    identityTransformerHandler = tf.newTransformerHandler();
                } catch (TransformerConfigurationException e) {
                    throw new TransformerFactoryConfigurationError(e);
                }

                handler = new ValidatorHandlerImpl(fComponentManager);
                handler.setContentHandler(identityTransformerHandler);
                identityTransformerHandler.setResult(result);
            }

            XMLInputSource input = new XMLInputSource(streamSource.getPublicId(),
                    streamSource.getSystemId(), null, false);
            input.setByteStream(streamSource.getInputStream());
            input.setCharacterStream(streamSource.getReader());

            // Gets the parser configuration. We'll create and initialize a new one, if we
            // haven't created one before or if the previous one was garbage collected.
            XMLParserConfiguration config = fConfiguration.get();
            if (config == null) {
                config = initialize();
            }
            // If settings have changed on the component manager, refresh the error handler and entity resolver.
            else if (fComponentManager.getFeature(PARSER_SETTINGS)) {
                config.setProperty(ENTITY_RESOLVER, fComponentManager.getProperty(ENTITY_RESOLVER));
                config.setProperty(ERROR_HANDLER, fComponentManager.getProperty(ERROR_HANDLER));
            }

            // prepare for parse
            fComponentManager.reset();
            fSchemaValidator.setDocumentHandler(handler);

            try {
                config.parse(input);
            } catch (XMLParseException e) {
                throw Util.toSAXParseException(e);
            } catch (XNIException e) {
                throw Util.toSAXException(e);
            }
            return;
        }
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(
                fComponentManager.getLocale(),
                "SourceResultMismatch",
                new Object[]{source.getClass().getName(), result.getClass().getName()}));
    }

    private XMLParserConfiguration initialize() {
        XML11Configuration config = new XML11Configuration();
        if (fComponentManager.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING)) {
            config.setProperty(SECURITY_MANAGER, new XMLSecurityManager());
        }
        config.setProperty(ENTITY_RESOLVER, fComponentManager.getProperty(ENTITY_RESOLVER));
        config.setProperty(ERROR_HANDLER, fComponentManager.getProperty(ERROR_HANDLER));
        XMLErrorReporter errorReporter = (XMLErrorReporter) fComponentManager.getProperty(ERROR_REPORTER);
        config.setProperty(ERROR_REPORTER, errorReporter);
        // add message formatters
        if (errorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN) == null) {
            XMLMessageFormatter xmft = new XMLMessageFormatter();
            errorReporter.putMessageFormatter(XMLMessageFormatter.XML_DOMAIN, xmft);
            errorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
        }
        config.setProperty(SYMBOL_TABLE, fComponentManager.getProperty(SYMBOL_TABLE));
        config.setProperty(VALIDATION_MANAGER, fComponentManager.getProperty(VALIDATION_MANAGER));
        config.setDocumentHandler(fSchemaValidator);
        config.setDTDHandler(null);
        config.setDTDContentModelHandler(null);
        config.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER,
                fComponentManager.getProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER));
        config.setProperty(Constants.SECURITY_MANAGER,
                fComponentManager.getProperty(Constants.SECURITY_MANAGER));

        // Passing on the CatalogFeatures settings
        JdkXmlUtils.catalogFeaturesConfig2Config(fComponentManager, config);

        config.setProperty(JdkConstants.CDATA_CHUNK_SIZE,
                fComponentManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE));

        fConfiguration = new SoftReference<>(config);
        return config;
    }

} // StreamValidatorHelper
