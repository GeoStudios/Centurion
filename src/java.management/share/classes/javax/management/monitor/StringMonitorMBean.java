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

package java.management.share.classes.javax.management.monitor;


import java.management.share.classes.javax.management.ObjectName;















// jmx imports
//

/**
 * Exposes the remote management interface of the string monitor MBean.
 *
 *
 */
public interface StringMonitorMBean extends MonitorMBean {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the derived gauge.
     *
     * @return The derived gauge.
     * @deprecated As of JMX 1.2, replaced by {@link #getDerivedGauge(ObjectName)}
     */
    @Deprecated
    String getDerivedGauge();

    /**
     * Gets the derived gauge timestamp.
     *
     * @return The derived gauge timestamp.
     * @deprecated As of JMX 1.2, replaced by {@link #getDerivedGaugeTimeStamp(ObjectName)}
     */
    @Deprecated
    long getDerivedGaugeTimeStamp();

    /**
     * Gets the derived gauge for the specified MBean.
     *
     * @param object the MBean for which the derived gauge is to be returned
     * @return The derived gauge for the specified MBean if this MBean is in the
     *         set of observed MBeans, or <code>null</code> otherwise.
     *
     */
    String getDerivedGauge(ObjectName object);

    /**
     * Gets the derived gauge timestamp for the specified MBean.
     *
     * @param object the MBean for which the derived gauge timestamp is to be returned
     * @return The derived gauge timestamp for the specified MBean if this MBean
     *         is in the set of observed MBeans, or <code>null</code> otherwise.
     *
     */
    long getDerivedGaugeTimeStamp(ObjectName object);

    /**
     * Gets the string to compare with the observed attribute.
     *
     * @return The string value.
     *
     * @see #setStringToCompare
     */
    String getStringToCompare();

    /**
     * Sets the string to compare with the observed attribute.
     *
     * @param value The string value.
     * @exception java.lang.IllegalArgumentException The specified
     * string to compare is null.
     *
     * @see #getStringToCompare
     */
    void setStringToCompare(String value) throws java.lang.IllegalArgumentException;

    /**
     * Gets the matching notification's on/off switch value.
     *
     * @return <CODE>true</CODE> if the string monitor notifies when
     * matching, <CODE>false</CODE> otherwise.
     *
     * @see #setNotifyMatch
     */
    boolean getNotifyMatch();

    /**
     * Sets the matching notification's on/off switch value.
     *
     * @param value The matching notification's on/off switch value.
     *
     * @see #getNotifyMatch
     */
    void setNotifyMatch(boolean value);

    /**
     * Gets the differing notification's on/off switch value.
     *
     * @return <CODE>true</CODE> if the string monitor notifies when
     * differing, <CODE>false</CODE> otherwise.
     *
     * @see #setNotifyDiffer
     */
    boolean getNotifyDiffer();

    /**
     * Sets the differing notification's on/off switch value.
     *
     * @param value The differing notification's on/off switch value.
     *
     * @see #getNotifyDiffer
     */
    void setNotifyDiffer(boolean value);
}
