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

import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkXmlFeatures;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;

/**
 * The XPathExpression interface encapsulates a (compiled) XPath expression.
 *
 */
public class XPathExpressionImpl extends XPathImplUtil implements XPathExpression {

    private com.sun.org.apache.xpath.internal.XPath xpath;

    /** Protected constructor to prevent direct instantiation; use compile()
     * from the context.
     */
    protected XPathExpressionImpl() {
        this(null, null, null, null, false, new JdkXmlFeatures(false));
    }

    protected XPathExpressionImpl(com.sun.org.apache.xpath.internal.XPath xpath,
            JAXPPrefixResolver prefixResolver,
            XPathFunctionResolver functionResolver,
            XPathVariableResolver variableResolver) {
        this(xpath, prefixResolver, functionResolver, variableResolver,
             false, new JdkXmlFeatures(false));
    }

    protected XPathExpressionImpl(com.sun.org.apache.xpath.internal.XPath xpath,
            JAXPPrefixResolver prefixResolver,XPathFunctionResolver functionResolver,
            XPathVariableResolver variableResolver, boolean featureSecureProcessing,
            JdkXmlFeatures featureManager) {
        this.xpath = xpath;
        this.prefixResolver = prefixResolver;
        this.functionResolver = functionResolver;
        this.variableResolver = variableResolver;
        this.featureSecureProcessing = featureSecureProcessing;
        this.overrideDefaultParser = featureManager.getFeature(
                JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        this.featureManager = featureManager;
    }

    public void setXPath (com.sun.org.apache.xpath.internal.XPath xpath) {
        this.xpath = xpath;
    }

    public Object eval(Object item, QName returnType)
            throws javax.xml.transform.TransformerException {
        XObject resultObject = eval(item, xpath);
        return getResultAsType(resultObject, returnType);
    }

    @Override
    public Object evaluate(Object item, QName returnType)
        throws XPathExpressionException {
        isSupported(returnType);
        try {
            return eval(item, returnType);
        } catch (java.lang.NullPointerException npe) {
            // If VariableResolver returns null Or if we get
            // NullPointerException at this stage for some other reason
            // then we have to reurn XPathException
            throw new XPathExpressionException (npe);
        } catch (javax.xml.transform.TransformerException te) {
            Throwable nestedException = te.getException();
            if (nestedException instanceof javax.xml.xpath.XPathFunctionException) {
                throw (javax.xml.xpath.XPathFunctionException)nestedException;
            } else {
                // For any other exceptions we need to throw
                // XPathExpressionException (as per spec)
                throw new XPathExpressionException(te);
            }
        }
    }

    @Override
    public String evaluate(Object item)
        throws XPathExpressionException {
        return (String)this.evaluate(item, XPathConstants.STRING);
    }

    @Override
    public Object evaluate(InputSource source, QName returnType)
        throws XPathExpressionException {
        isSupported (returnType);
        try {
            Document document = getDocument(source);
            return eval(document, returnType);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    @Override
    public String evaluate(InputSource source)
        throws XPathExpressionException {
        return (String)this.evaluate(source, XPathConstants.STRING);
    }

    @Override
    public <T>T evaluateExpression(Object item, Class<T> type)
        throws XPathExpressionException {
        isSupportedClassType(type);

        try {
            XObject resultObject = eval(item, xpath);
            if (type.isAssignableFrom(XPathEvaluationResult.class)) {
                return getXPathResult(resultObject, type);
            } else {
                return XPathResultImpl.getValue(resultObject, type);
            }

        } catch (javax.xml.transform.TransformerException te) {
            throw new XPathExpressionException(te);
        }
    }

    @Override
    public XPathEvaluationResult<?> evaluateExpression(Object item)
        throws XPathExpressionException {
        return evaluateExpression(item, XPathEvaluationResult.class);
    }

    @Override
    public <T>T  evaluateExpression(InputSource source, Class<T> type)
            throws XPathExpressionException {
        Document document = getDocument(source);
        return evaluateExpression(document, type);
    }

    @Override
    public XPathEvaluationResult<?> evaluateExpression(InputSource source)
        throws XPathExpressionException {
        return evaluateExpression(source, XPathEvaluationResult.class);
    }
 }
