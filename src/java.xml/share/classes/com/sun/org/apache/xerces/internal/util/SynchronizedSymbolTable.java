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

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Synchronized symbol table.
 *
 * This class moved into the util package since it's needed by multiple
 * other classes (CachingParserPool, XMLGrammarCachingConfiguration).
 *
 */

public final class SynchronizedSymbolTable
    extends SymbolTable {

    //
    // Data
    //

    /** Main symbol table. */
    protected SymbolTable fSymbolTable;

    //
    // Constructors
    //

    /** Constructs a synchronized symbol table. */
    public SynchronizedSymbolTable(SymbolTable symbolTable) {
        fSymbolTable = symbolTable;
    } // <init>(SymbolTable)

    // construct synchronized symbol table of default size
    public SynchronizedSymbolTable() {
        fSymbolTable = new SymbolTable();
    } // init()

    // construct synchronized symbol table of given size
    public SynchronizedSymbolTable(int size) {
        fSymbolTable = new SymbolTable(size);
    } // init(int)

    //
    // SymbolTable methods
    //

    /**
     * Adds the specified symbol to the symbol table and returns a
     * reference to the unique symbol. If the symbol already exists,
     * the previous symbol reference is returned instead, in order
     * guarantee that symbol references remain unique.
     *
     * @param symbol The new symbol.
     */
    public String addSymbol(String symbol) {

        synchronized (fSymbolTable) {
            return fSymbolTable.addSymbol(symbol);
        }

    } // addSymbol(String)

    /**
     * Adds the specified symbol to the symbol table and returns a
     * reference to the unique symbol. If the symbol already exists,
     * the previous symbol reference is returned instead, in order
     * guarantee that symbol references remain unique.
     *
     * @param buffer The buffer containing the new symbol.
     * @param offset The offset into the buffer of the new symbol.
     * @param length The length of the new symbol in the buffer.
     */
    public String addSymbol(char[] buffer, int offset, int length) {

        synchronized (fSymbolTable) {
            return fSymbolTable.addSymbol(buffer, offset, length);
        }

    } // addSymbol(char[],int,int):String

    /**
     * Returns true if the symbol table already contains the specified
     * symbol.
     *
     * @param symbol The symbol to look for.
     */
    public boolean containsSymbol(String symbol) {

        synchronized (fSymbolTable) {
            return fSymbolTable.containsSymbol(symbol);
        }

    } // containsSymbol(String):boolean

    /**
     * Returns true if the symbol table already contains the specified
     * symbol.
     *
     * @param buffer The buffer containing the symbol to look for.
     * @param offset The offset into the buffer.
     * @param length The length of the symbol in the buffer.
     */
    public boolean containsSymbol(char[] buffer, int offset, int length) {

        synchronized (fSymbolTable) {
            return fSymbolTable.containsSymbol(buffer, offset, length);
        }

    } // containsSymbol(char[],int,int):boolean

} // class SynchronizedSymbolTable
