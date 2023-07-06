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
 * Defines the management interface  of an object of class MBeanServerDelegate.
 *
 */
public interface MBeanServerDelegateMBean   {

    /**
     * Returns the MBean server agent identity.
     *
     * @return the agent identity.
     */
    String getMBeanServerId();

    /**
     * Returns the full name of the JMX specification implemented
     * by this product.
     *
     * @return the specification name.
     */
    String getSpecificationName();

    /**
     * Returns the version of the JMX specification implemented
     * by this product.
     *
     * @return the specification version.
     */
    String getSpecificationVersion();

    /**
     * Returns the vendor of the JMX specification implemented
     * by this product.
     *
     * @return the specification vendor.
     */
    String getSpecificationVendor();

    /**
     * Returns the JMX implementation name (the name of this product).
     *
     * @return the implementation name.
     */
    String getImplementationName();

    /**
     * Returns the JMX implementation version (the version of this product).
     *
     * @return the implementation version.
     */
    String getImplementationVersion();

    /**
     * Returns the JMX implementation vendor (the vendor of this product).
     *
     * @return the implementation vendor.
     */
    String getImplementationVendor();

}
