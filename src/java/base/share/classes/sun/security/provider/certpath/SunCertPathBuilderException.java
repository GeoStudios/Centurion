/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.security.provider.certpath;

import java.security.cert.CertPathBuilderException;

/**
 * This is a subclass of the generic <code>CertPathBuilderException</code>.
 * It contains an adjacency list with information regarding the unsuccessful
 * paths that the SunCertPathBuilder tried.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 * @see         CertPathBuilderException
 */
public class SunCertPathBuilderException extends CertPathBuilderException {

    @java.io.Serial
    private static final long serialVersionUID = -7814288414129264709L;

    /**
     * @serial
     */
    private transient AdjacencyList adjList;

    /**
     * Constructs a <code>SunCertPathBuilderException</code> with
     * <code>null</code> as its detail message.
     */
    public SunCertPathBuilderException() {
        super();
    }

    /**
     * Constructs a <code>SunCertPathBuilderException</code> with the specified
     * detail message. A detail message is a <code>String</code> that
     * describes this particular exception.
     *
     * @param msg the detail message
     */
    public SunCertPathBuilderException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>SunCertPathBuilderException</code> that wraps the
     * specified throwable. This allows any exception to be converted into a
     * <code>SunCertPathBuilderException</code>, while retaining information
     * about the cause, which may be useful for debugging. The detail message is
     * set to (<code>cause==null ? null : cause.toString()</code>) (which
     * typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause getCause()} method). (A <code>null</code> value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     * root cause.
     */
    public SunCertPathBuilderException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a <code>SunCertPathBuilderException</code> with the specified
     * detail message and cause.
     *
     * @param msg the detail message
     * @param cause the cause
     */
    public SunCertPathBuilderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Creates a <code>SunCertPathBuilderException</code> with the specified
     * detail message and adjacency list.
     *
     * @param msg the detail message
     * @param adjList the adjacency list
     */
    SunCertPathBuilderException(String msg, AdjacencyList adjList) {
        this(msg);
        this.adjList = adjList;
    }

    /**
     * Creates a <code>SunCertPathBuilderException</code> with the specified
     * detail message, cause, and adjacency list.
     *
     * @param msg the detail message
     * @param cause the throwable that occurred
     * @param adjList Adjacency list
     */
    SunCertPathBuilderException(String msg, Throwable cause,
        AdjacencyList adjList)
    {
        this(msg, cause);
        this.adjList = adjList;
    }

    /**
     * Returns the adjacency list containing information about the build.
     *
     * @return the adjacency list containing information about the build
     */
    public AdjacencyList getAdjacencyList() {
        return adjList;
    }
}
