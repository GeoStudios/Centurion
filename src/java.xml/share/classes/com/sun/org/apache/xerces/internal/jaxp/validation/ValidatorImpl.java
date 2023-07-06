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

import java.io.java.io.java.io.java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.Constants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.Status;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import java.xml.share.classes.com.sun.org.w3c.dom.ls.LSResourceResolver;
import java.xml.share.classes.com.sun.org.xml.sax.ErrorHandler;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotRecognizedException;
import java.xml.share.classes.com.sun.org.xml.sax.SAXNotSupportedException;

/**
 * <p>Implementation of Validator for W3C XML Schemas.</p>
 *
 */
final class ValidatorImpl extends Validator implements PSVIProvider {

    //
    // Data
    //

    /** Component manager. **/
    private final XMLSchemaValidatorComponentManager fComponentManager;

    /** SAX validator helper. **/
    private ValidatorHandlerImpl fSAXValidatorHelper;

    /** DOM validator helper. **/
    private DOMValidatorHelper fDOMValidatorHelper;

    /** Stream validator helper. **/
    private StreamValidatorHelper fStreamValidatorHelper;

    /** StAX validator helper. **/
    private StAXValidatorHelper fStaxValidatorHelper;

    /** Flag for tracking whether features/properties changed since last reset. */
    private boolean fConfigurationChanged = false;

    /** Flag for tracking whether the error handler changed since last reset. */
    private boolean fErrorHandlerChanged = false;

    /** Flag for tracking whether the resource resolver changed since last reset. */
    private boolean fResourceResolverChanged = false;

    /** Support current-element-node property */
    private static final String CURRENT_ELEMENT_NODE =
            Constants.XERCES_PROPERTY_PREFIX + Constants.CURRENT_ELEMENT_NODE_PROPERTY;

    public ValidatorImpl(XSGrammarPoolContainer grammarContainer) {
        fComponentManager = new XMLSchemaValidatorComponentManager(grammarContainer);
        setErrorHandler(null);
        setResourceResolver(null);
    }

    @Override
    public void validate(Source source, Result result)
        throws SAXException, IOException {
        if (source instanceof SAXSource) {
            // Hand off to SAX validator helper.
            if (fSAXValidatorHelper == null) {
                fSAXValidatorHelper = new ValidatorHandlerImpl(fComponentManager);
            }
            fSAXValidatorHelper.validate(source, result);
        }
        else if (source instanceof DOMSource) {
            // Hand off to DOM validator helper.
            if (fDOMValidatorHelper == null) {
                fDOMValidatorHelper = new DOMValidatorHelper(fComponentManager);
            }
            fDOMValidatorHelper.validate(source, result);
        }
        else if (source instanceof StreamSource) {
            // Hand off to stream validator helper.
            if (fStreamValidatorHelper == null) {
                fStreamValidatorHelper = new StreamValidatorHelper(fComponentManager);
            }
            fStreamValidatorHelper.validate(source, result);
        }
        else if (source instanceof StAXSource) {
            // Hand off to stax validator helper.
            if (fStaxValidatorHelper == null) {
                fStaxValidatorHelper = new StAXValidatorHelper(fComponentManager);
            }
            fStaxValidatorHelper.validate(source, result);
        }
        // Source parameter cannot be null.
        else if (source == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    "SourceParameterNull", null));
        }
        // Source parameter must be a SAXSource, DOMSource or StreamSource
        else {
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    "SourceNotAccepted", new Object [] {source.getClass().getName()}));
        }
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        fErrorHandlerChanged = (errorHandler != null);
        fComponentManager.setErrorHandler(errorHandler);
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return fComponentManager.getErrorHandler();
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver) {
        fResourceResolverChanged = (resourceResolver != null);
        fComponentManager.setResourceResolver(resourceResolver);
    }

    @Override
    public LSResourceResolver getResourceResolver() {
        return fComponentManager.getResourceResolver();
    }

    @Override
    public boolean getFeature(String name)
        throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        }
        try {
            return fComponentManager.getFeature(name);
        }
        catch (XMLConfigurationException e) {
            final String identifier = e.getIdentifier();
            final String key = e.getType() == Status.NOT_RECOGNIZED ?
                    "feature-not-recognized" : "feature-not-supported";
            throw new SAXNotRecognizedException(
                    SAXMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    key, new Object [] {identifier}));
        }
    }

    @Override
    public void setFeature(String name, boolean value)
        throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        }
        try {
            fComponentManager.setFeature(name, value);
        }
        catch (XMLConfigurationException e) {
            final String identifier = e.getIdentifier();
            final String key;
            if (e.getType() == Status.NOT_ALLOWED) {
                //for now, the identifier can only be (XMLConstants.FEATURE_SECURE_PROCESSING)
                throw new SAXNotSupportedException(
                    SAXMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    "jaxp-secureprocessing-feature", null));
            } else if (e.getType() == Status.NOT_RECOGNIZED) {
                key = "feature-not-recognized";
            } else {
                key = "feature-not-supported";
            }
            throw new SAXNotRecognizedException(
                    SAXMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    key, new Object [] {identifier}));
        }
        fConfigurationChanged = true;
    }

    @Override
    public Object getProperty(String name)
        throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        }
        //Support current-element-node; return current node if DOMSource is used.
        if (CURRENT_ELEMENT_NODE.equals(name)) {
            return (fDOMValidatorHelper != null) ? fDOMValidatorHelper.getCurrentElement() : null;
        }
        try {
            return fComponentManager.getProperty(name);
        }
        catch (XMLConfigurationException e) {
            final String identifier = e.getIdentifier();
            final String key = e.getType() == Status.NOT_RECOGNIZED ?
                    "property-not-recognized" : "property-not-supported";
            throw new SAXNotRecognizedException(
                    SAXMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    key, new Object [] {identifier}));
        }
    }

    @Override
    public void setProperty(String name, Object object)
        throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name == null) {
            throw new NullPointerException();
        }
        try {
            fComponentManager.setProperty(name, object);
        }
        catch (XMLConfigurationException e) {
            final String identifier = e.getIdentifier();
            final String key = e.getType() == Status.NOT_RECOGNIZED ?
                    "property-not-recognized" : "property-not-supported";
            throw new SAXNotRecognizedException(
                    SAXMessageFormatter.formatMessage(fComponentManager.getLocale(),
                    key, new Object [] {identifier}));
        }
        fConfigurationChanged = true;
    }

    @Override
    public void reset() {
        // avoid resetting features and properties if the state the validator
        // is currently in, is the same as it will be after reset.
        if (fConfigurationChanged) {
            fComponentManager.restoreInitialState();
            setErrorHandler(null);
            setResourceResolver(null);
            fConfigurationChanged = false;
            fErrorHandlerChanged = false;
            fResourceResolverChanged = false;
        }
        else {
            if (fErrorHandlerChanged) {
                setErrorHandler(null);
                fErrorHandlerChanged = false;
            }
            if (fResourceResolverChanged) {
                setResourceResolver(null);
                fResourceResolverChanged = false;
            }
        }
    }

    /*
     * PSVIProvider methods
     */

    @Override
    public ElementPSVI getElementPSVI() {
        return (fSAXValidatorHelper != null) ? fSAXValidatorHelper.getElementPSVI() : null;
    }

    @Override
    public AttributePSVI getAttributePSVI(int index) {
        return (fSAXValidatorHelper != null) ? fSAXValidatorHelper.getAttributePSVI(index) : null;
    }

    @Override
    public AttributePSVI getAttributePSVIByName(String uri, String localname) {
        return (fSAXValidatorHelper != null) ? fSAXValidatorHelper.getAttributePSVIByName(uri, localname) : null;
    }

} // ValidatorImpl