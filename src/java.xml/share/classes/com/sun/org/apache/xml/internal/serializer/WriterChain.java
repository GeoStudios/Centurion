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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;


import java.io.java.io.java.io.java.io.IOException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * It is unfortunate that java.io.Writer is a class rather than an interface.
 * The serializer has a number of classes that extend java.io.Writer
 * and which send their ouput to a yet another wrapped Writer or OutputStream.
 *
 * The purpose of this interface is to force such classes to over-ride all of
 * the important methods defined on the java.io.Writer class, namely these:
 * <code>
 * write(int val)
 * write(char[] chars)
 * write(char[] chars, int start, int count)
 * write(String chars)
 * write(String chars, int start, int count)
 * flush()
 * close()
 * </code>
 * In this manner nothing will accidentally go directly to
 * the base class rather than to the wrapped Writer or OutputStream.
 *
 * The purpose of this class is to have a uniform way of chaining the output of one writer to
 * the next writer in the chain. In addition there are methods to obtain the Writer or
 * OutputStream that this object sends its output to.
 *
 * This interface is only for internal use withing the serializer.
 * @xsl.usage internal
 */
interface WriterChain
{
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void write(int val) throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void write(char[] chars) throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void write(char[] chars, int start, int count) throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void write(String chars) throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void write(String chars, int start, int count) throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void flush() throws IOException;
    /** This method forces us to over-ride the method defined in java.io.Writer */
    void close() throws IOException;

    /**
     * If this method returns null, getOutputStream() must return non-null.
     * Get the writer that this writer sends its output to.
     *
     * It is possible that the Writer returned by this method does not
     * implement the WriterChain interface.
     */
    java.io.Writer getWriter();

    /**
     * If this method returns null, getWriter() must return non-null.
     * Get the OutputStream that this writer sends its output to.
     */
    java.io.OutputStream getOutputStream();
}
