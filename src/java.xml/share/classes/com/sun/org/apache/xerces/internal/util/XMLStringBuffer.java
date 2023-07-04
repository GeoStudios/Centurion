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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLString;

/**
 * XMLString is a structure used to pass character arrays. However,
 * XMLStringBuffer is a buffer in which characters can be appended
 * and extends XMLString so that it can be passed to methods
 * expecting an XMLString object. This is a safe operation because
 * it is assumed that any callee will <strong>not</strong> modify
 * the contents of the XMLString structure.
 * <p>
 * The contents of the string are managed by the string buffer. As
 * characters are appended, the string buffer will grow as needed.
 * <p>
 * <strong>Note:</strong> Never set the <code>ch</code>,
 * <code>offset</code>, and <code>length</code> fields directly.
 * These fields are managed by the string buffer. In order to reset
 * the buffer, call <code>clear()</code>.
 *
 *
 */
public class XMLStringBuffer
extends XMLString {

    //
    // Constants
    //


    /** Default buffer size (32). */
    public static final int DEFAULT_SIZE = 32;

    //
    // Data
    //

    //
    // Constructors
    //

    /**
     *
     */
    public XMLStringBuffer() {
        this(DEFAULT_SIZE);
    } // <init>()

    /**
     *
     *
     * @param size
     */
    public XMLStringBuffer(int size) {
        ch = new char[size];
    } // <init>(int)

    /** Constructs a string buffer from a char. */
    public XMLStringBuffer(char c) {
        this(1);
        append(c);
    } // <init>(char)

    /** Constructs a string buffer from a String. */
    public XMLStringBuffer(String s) {
        this(s.length());
        append(s);
    } // <init>(String)

    /** Constructs a string buffer from the specified character array. */
    public XMLStringBuffer(char[] ch, int offset, int length) {
        this(length);
        append(ch, offset, length);
    } // <init>(char[],int,int)

    /** Constructs a string buffer from the specified XMLString. */
    public XMLStringBuffer(XMLString s) {
        this(s.length);
        append(s);
    } // <init>(XMLString)

    //
    // Public methods
    //

    /** Clears the string buffer. */
    public void clear() {
        offset = 0;
        length = 0;
    }

    /**
     * append
     *
     * @param c
     */
    public void append(char c) {
        if(this.length + 1 > this.ch.length){
            int newLength = this.ch.length * 2 ;
            if(newLength < this.ch.length + DEFAULT_SIZE){
                newLength = this.ch.length + DEFAULT_SIZE;
            }
            char [] tmp = new char[newLength];
            System.arraycopy(this.ch, 0, tmp, 0, this.length);
            this.ch = tmp;
        }
        this.ch[this.length] = c ;
        this.length++;
    } // append(char)

    /**
     * append
     *
     * @param s
     */
    public void append(String s) {
        int length = s.length();
        if (this.length + length > this.ch.length) {
            int newLength = this.ch.length * 2 ;
            if(newLength < this.ch.length + length + DEFAULT_SIZE){
                newLength = this.ch.length + length+ DEFAULT_SIZE;
            }

            char[] newch = new char[newLength];
            System.arraycopy(this.ch, 0, newch, 0, this.length);
            this.ch = newch;
        }
        s.getChars(0, length, this.ch, this.length);
        this.length += length;
    } // append(String)

    /**
     * append
     *
     * @param ch
     * @param offset
     * @param length
     */
    public void append(char[] ch, int offset, int length) {
        if (this.length + length > this.ch.length) {
            int newLength = this.ch.length * 2 ;
            if(newLength < this.ch.length + length + DEFAULT_SIZE){
                newLength = this.ch.length + length + DEFAULT_SIZE;
            }
            char[] newch = new char[newLength];
            System.arraycopy(this.ch, 0, newch, 0, this.length);
            this.ch = newch;
        }
        //making the code more robust as it would handle null or 0 length data,
        //add the data only when it contains some thing
        if(ch != null && length > 0){
            System.arraycopy(ch, offset, this.ch, this.length, length);
            this.length += length;
        }
    } // append(char[],int,int)

    /**
     * append
     *
     * @param s
     */
    public void append(XMLString s) {
        append(s.ch, s.offset, s.length);
    } // append(XMLString)


} // class XMLStringBuffer
