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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.parsers;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd.XMLNSDTDValidator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This is configuration uses a scanner that integrates both scanning of the document
 * and binding namespaces.
 *
 * If namespace feature is turned on, the pipeline is constructured with the
 * following components:
 * XMLNSDocumentScannerImpl -> XMLNSDTDValidator -> (optional) XMLSchemaValidator
 *
 * If the namespace feature is turned off the default document scanner implementation
 * is used (XMLDocumentScannerImpl).
 * <p>
 * In addition to the features and properties recognized by the base
 * parser configuration, this class recognizes these additional
 * features and properties:
 * <ul>
 * <li>Features
 *  <ul>
 *  <li>http://apache.org/xml/features/validation/schema</li>
 *  <li>http://apache.org/xml/features/validation/schema-full-checking</li>
 *  <li>http://apache.org/xml/features/validation/schema/normalized-value</li>
 *  <li>http://apache.org/xml/features/validation/schema/element-default</li>
 *  </ul>
 * <li>Properties
 *  <ul>
 *   <li>http://apache.org/xml/properties/internal/error-reporter</li>
 *   <li>http://apache.org/xml/properties/internal/entity-manager</li>
 *   <li>http://apache.org/xml/properties/internal/document-scanner</li>
 *   <li>http://apache.org/xml/properties/internal/dtd-scanner</li>
 *   <li>http://apache.org/xml/properties/internal/grammar-pool</li>
 *   <li>http://apache.org/xml/properties/internal/validator/dtd</li>
 *   <li>http://apache.org/xml/properties/internal/datatype-validator-factory</li>
 *  </ul>
 * </ul>
 *
 *
 */
public class IntegratedParserConfiguration
extends StandardParserConfiguration {


    //
    // REVISIT: should this configuration depend on the others
    //          like DTD/Standard one?
    //

    /** Document scanner that does namespace binding. */
    protected XMLNSDocumentScannerImpl fNamespaceScanner;

    /** Default Xerces implementation of scanner */
    protected XMLDocumentScannerImpl fNonNSScanner;

    /** DTD Validator that does not bind namespaces */
    protected XMLDTDValidator fNonNSDTDValidator;

    //
    // Constructors
    //

    /** Default constructor. */
    public IntegratedParserConfiguration() {
        this(null, null, null);
    } // <init>()

    /**
     * Constructs a parser configuration using the specified symbol table.
     *
     * @param symbolTable The symbol table to use.
     */
    public IntegratedParserConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    } // <init>(SymbolTable)

    /**
     * Constructs a parser configuration using the specified symbol table and
     * grammar pool.
     * <p>
     * <strong>REVISIT:</strong>
     * Grammar pool will be updated when the new validation engine is
     * implemented.
     *
     * @param symbolTable The symbol table to use.
     * @param grammarPool The grammar pool to use.
     */
    public IntegratedParserConfiguration(SymbolTable symbolTable,
                                         XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    } // <init>(SymbolTable,XMLGrammarPool)

    /**
     * Constructs a parser configuration using the specified symbol table,
     * grammar pool, and parent settings.
     * <p>
     * <strong>REVISIT:</strong>
     * Grammar pool will be updated when the new validation engine is
     * implemented.
     *
     * @param symbolTable    The symbol table to use.
     * @param grammarPool    The grammar pool to use.
     * @param parentSettings The parent settings.
     */
    public IntegratedParserConfiguration(SymbolTable symbolTable,
                                         XMLGrammarPool grammarPool,
                                         XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);

        // create components
        fNonNSScanner = new XMLDocumentScannerImpl();
        fNonNSDTDValidator = new XMLDTDValidator();

        // add components
        addComponent(fNonNSScanner);
        addComponent(fNonNSDTDValidator);

    } // <init>(SymbolTable,XMLGrammarPool)


    /** Configures the pipeline. */
        protected void configurePipeline() {

                // use XML 1.0 datatype library
                setProperty(DATATYPE_VALIDATOR_FACTORY, fDatatypeValidatorFactory);

                // setup DTD pipeline
                configureDTDPipeline();

                // setup document pipeline
                if (fFeatures.get(NAMESPACES) == Boolean.TRUE) {
            fProperties.put(NAMESPACE_BINDER, fNamespaceBinder);
                        fScanner = fNamespaceScanner;
                        fProperties.put(DOCUMENT_SCANNER, fNamespaceScanner);
                        if (fDTDValidator != null) {
                                fProperties.put(DTD_VALIDATOR, fDTDValidator);
                                fNamespaceScanner.setDTDValidator(fDTDValidator);
                                fNamespaceScanner.setDocumentHandler(fDTDValidator);
                                fDTDValidator.setDocumentSource(fNamespaceScanner);
                                fDTDValidator.setDocumentHandler(fDocumentHandler);
                                if (fDocumentHandler != null) {
                                        fDocumentHandler.setDocumentSource(fDTDValidator);
                                }
                                fLastComponent = fDTDValidator;
                        }
                        else {
                                fNamespaceScanner.setDocumentHandler(fDocumentHandler);
                fNamespaceScanner.setDTDValidator(null);
                                if (fDocumentHandler != null) {
                                        fDocumentHandler.setDocumentSource(fNamespaceScanner);
                                }
                                fLastComponent = fNamespaceScanner;
                        }
                }
                else {
                        fScanner = fNonNSScanner;
                        fProperties.put(DOCUMENT_SCANNER, fNonNSScanner);
                        if (fNonNSDTDValidator != null) {
                                fProperties.put(DTD_VALIDATOR, fNonNSDTDValidator);
                                fNonNSScanner.setDocumentHandler(fNonNSDTDValidator);
                                fNonNSDTDValidator.setDocumentSource(fNonNSScanner);
                                fNonNSDTDValidator.setDocumentHandler(fDocumentHandler);
                                if (fDocumentHandler != null) {
                                        fDocumentHandler.setDocumentSource(fNonNSDTDValidator);
                                }
                                fLastComponent = fNonNSDTDValidator;
                        }
                        else {
                                fScanner.setDocumentHandler(fDocumentHandler);
                                if (fDocumentHandler != null) {
                                        fDocumentHandler.setDocumentSource(fScanner);
                                }
                                fLastComponent = fScanner;
                        }
                }

                // setup document pipeline
                if (fFeatures.get(XMLSCHEMA_VALIDATION) == Boolean.TRUE) {
                        // If schema validator was not in the pipeline insert it.
                        if (fSchemaValidator == null) {
                                fSchemaValidator = new XMLSchemaValidator();

                                // add schema component
                                fProperties.put(SCHEMA_VALIDATOR, fSchemaValidator);
                                addComponent(fSchemaValidator);
                                // add schema message formatter
                                if (fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                                        XSMessageFormatter xmft = new XSMessageFormatter();
                                        fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, xmft);
                                }

                        }

                        fLastComponent.setDocumentHandler(fSchemaValidator);
                        fSchemaValidator.setDocumentSource(fLastComponent);
                        fSchemaValidator.setDocumentHandler(fDocumentHandler);
                        if (fDocumentHandler != null) {
                                fDocumentHandler.setDocumentSource(fSchemaValidator);
                        }
                        fLastComponent = fSchemaValidator;
                }
        } // configurePipeline()



    /** Create a document scanner: this scanner performs namespace binding
      */
    protected XMLDocumentScanner createDocumentScanner() {
        fNamespaceScanner = new XMLNSDocumentScannerImpl();
        return fNamespaceScanner;
    } // createDocumentScanner():XMLDocumentScanner


    /** Create a DTD validator: this validator performs namespace binding.
      */
    protected XMLDTDValidator createDTDValidator() {
        return new XMLNSDTDValidator();
    } // createDTDValidator():XMLDTDValidator

} // class IntegratedParserConfiguration
