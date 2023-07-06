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

import java.io.Serializable;

// java import

/**
 * <p>Represents relational constraints similar to database query "where
 * clauses". Instances of QueryExp are returned by the static methods of the
 * {@link Query} class.</p>
 *
 * <p>It is possible, but not
 * recommended, to create custom queries by implementing this
 * interface.  In that case, it is better to extend the {@link
 * QueryEval} class than to implement the interface directly, so that
 * the {@link #setMBeanServer} method works correctly.
 *
 * @see MBeanServer#queryNames MBeanServer.queryNames
 */
public interface QueryExp extends Serializable {

     /**
      * Applies the QueryExp on an MBean.
      *
      * @param name The name of the MBean on which the QueryExp will be applied.
      *
      * @return  True if the query was successfully applied to the MBean, false otherwise
      *
      * @throws BadStringOperationException when an invalid string
      * operation is passed to a method for constructing a query
      * @throws BadBinaryOpValueExpException when an invalid expression
      * is passed to a method for constructing a query
      * @throws BadAttributeValueExpException when an invalid MBean
      * attribute is passed to a query constructing method
      * @throws InvalidApplicationException when an invalid apply is attempted
      */
     boolean apply(ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException,
         BadAttributeValueExpException, InvalidApplicationException ;

     /**
      * Sets the MBean server on which the query is to be performed.
      *
      * @param s The MBean server on which the query is to be performed.
      */
     void setMBeanServer(MBeanServer s) ;

 }
