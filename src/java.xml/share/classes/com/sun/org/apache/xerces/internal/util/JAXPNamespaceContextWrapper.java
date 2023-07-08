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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.java.util.java.util.java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import javax.xml.XMLConstants;















/**
 * <p>A read-only XNI wrapper around a JAXP NamespaceContext.</p>
 *
 *
 * @LastModified: Oct 2017
 */
public final class JAXPNamespaceContextWrapper implements NamespaceContext {

    private javax.xml.namespace.NamespaceContext fNamespaceContext;
    private SymbolTable fSymbolTable;
    private List<String> fPrefixes;
    private final Vector<String> fAllPrefixes = new Vector<>();

    private int[] fContext = new int[8];
    private int fCurrentContext;

    public JAXPNamespaceContextWrapper(SymbolTable symbolTable) {
        setSymbolTable(symbolTable);
    }

    public void setNamespaceContext(javax.xml.namespace.NamespaceContext context) {
        fNamespaceContext = context;
    }

    public javax.xml.namespace.NamespaceContext getNamespaceContext() {
        return fNamespaceContext;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        fSymbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return fSymbolTable;
    }

    public void setDeclaredPrefixes(List<String> prefixes) {
        fPrefixes = prefixes;
    }

    public List<String> getDeclaredPrefixes() {
        return fPrefixes;
    }

    /*
     * NamespaceContext methods
     */

    public String getURI(String prefix) {
        if (fNamespaceContext != null) {
            String uri = fNamespaceContext.getNamespaceURI(prefix);
            if (uri != null && !XMLConstants.NULL_NS_URI.equals(uri)) {
                return (fSymbolTable != null) ? fSymbolTable.addSymbol(uri) : uri.intern();
            }
        }
        return null;
    }

    public String getPrefix(String uri) {
        if (fNamespaceContext != null) {
            if (uri == null) {
                uri = XMLConstants.NULL_NS_URI;
            }
            String prefix = fNamespaceContext.getPrefix(uri);
            if (prefix == null) {
                prefix = XMLConstants.DEFAULT_NS_PREFIX;
            }
            return (fSymbolTable != null) ? fSymbolTable.addSymbol(prefix) : prefix.intern();
        }
        return null;
    }

    public Enumeration<String> getAllPrefixes() {
        // There may be duplicate prefixes in the list so we
        // first transfer them to a set to ensure uniqueness.
        return Collections.enumeration(new TreeSet<String>(fAllPrefixes));
    }

    public void pushContext() {
        // extend the array, if necessary
        if (fCurrentContext + 1 == fContext.length) {
            int[] contextarray = new int[fContext.length * 2];
            System.arraycopy(fContext, 0, contextarray, 0, fContext.length);
            fContext = contextarray;
        }
        // push context
        fContext[++fCurrentContext] = fAllPrefixes.size();
        if (fPrefixes != null) {
            fAllPrefixes.addAll(fPrefixes);
        }
    }

    public void popContext() {
        fAllPrefixes.setSize(fContext[fCurrentContext--]);
    }

    public boolean declarePrefix(String prefix, String uri) {
        return true;
    }

    public int getDeclaredPrefixCount() {
        return (fPrefixes != null) ? fPrefixes.size() : 0;
    }

    public String getDeclaredPrefixAt(int index) {
        return fPrefixes.get(index);
    }

    public void reset() {
        fCurrentContext = 0;
        fContext[fCurrentContext] = 0;
        fAllPrefixes.clear();
    }

} // JAXPNamespaceContextWrapper
