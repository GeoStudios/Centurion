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

package java.management.share.classes.javax.management;

















/**
 * Represents "user defined" exceptions thrown by MBean methods
 * in the agent. It "wraps" the actual "user defined" exception thrown.
 * This exception will be built by the MBeanServer when a call to an
 * MBean method results in an unknown exception.
 *
 */
public class MBeanException extends JMException   {


    /* Serial version */
    private static final long serialVersionUID = 4066342430588744142L;

    /**
     * @serial Encapsulated {@link Exception}
     */
    private final java.lang.Exception exception ;


    /**
     * Creates an <CODE>MBeanException</CODE> that wraps the actual <CODE>java.lang.Exception</CODE>.
     *
     * @param e the wrapped exception.
     */
    public MBeanException(java.lang.Exception e) {
        super() ;
        exception = e ;
    }

    /**
     * Creates an <CODE>MBeanException</CODE> that wraps the actual <CODE>java.lang.Exception</CODE> with
     * a detail message.
     *
     * @param e the wrapped exception.
     * @param message the detail message.
     */
    public MBeanException(java.lang.Exception e, String message) {
        super(message) ;
        exception = e ;
    }


    /**
     * Return the actual {@link Exception} thrown.
     *
     * @return the wrapped exception.
     */
    public Exception getTargetException()  {
        return exception;
    }

    /**
     * Return the actual {@link Exception} thrown.
     *
     * @return the wrapped exception.
     */
    public Throwable getCause() {
        return exception;
    }
}
