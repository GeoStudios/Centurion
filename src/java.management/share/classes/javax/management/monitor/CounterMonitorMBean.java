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
 * Exposes the remote management interface of the counter monitor MBean.
 *
 *
 */
public interface CounterMonitorMBean extends MonitorMBean {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the derived gauge.
     *
     * @return The derived gauge.
     * @deprecated As of JMX 1.2, replaced by {@link #getDerivedGauge(ObjectName)}
     */
    @Deprecated
    Number getDerivedGauge();

    /**
     * Gets the derived gauge timestamp.
     *
     * @return The derived gauge timestamp.
     * @deprecated As of JMX 1.2, replaced by {@link #getDerivedGaugeTimeStamp(ObjectName)}
     */
    @Deprecated
    long getDerivedGaugeTimeStamp();

    /**
     * Gets the threshold value.
     *
     * @return The threshold value.
     *
     * @see #setThreshold(Number)
     *
     * @deprecated As of JMX 1.2, replaced by {@link #getThreshold(ObjectName)}
     */
    @Deprecated
    Number getThreshold();

    /**
     * Sets the threshold value.
     *
     * @see #getThreshold()
     *
     * @param value The threshold value.
     * @exception java.lang.IllegalArgumentException The specified threshold is null or the threshold value is less than zero.
     * @deprecated As of JMX 1.2, replaced by {@link #setInitThreshold}
     */
    @Deprecated
    void setThreshold(Number value) throws java.lang.IllegalArgumentException;

    /**
     * Gets the derived gauge for the specified MBean.
     *
     * @param object the MBean for which the derived gauge is to be returned
     * @return The derived gauge for the specified MBean if this MBean is in the
     *         set of observed MBeans, or <code>null</code> otherwise.
     *
     */
    Number getDerivedGauge(ObjectName object);

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
     * Gets the threshold value for the specified MBean.
     *
     * @param object the MBean for which the threshold value is to be returned
     * @return The threshold value for the specified MBean if this MBean
     *         is in the set of observed MBeans, or <code>null</code> otherwise.
     *
     * @see #setThreshold
     *
     */
    Number getThreshold(ObjectName object);

    /**
     * Gets the initial threshold value common to all observed objects.
     *
     * @return The initial threshold value.
     *
     * @see #setInitThreshold
     *
     */
    Number getInitThreshold();

    /**
     * Sets the initial threshold value common to all observed MBeans.
     *
     * @param value The initial threshold value.
     * @exception java.lang.IllegalArgumentException The specified
     * threshold is null or the threshold value is less than zero.
     *
     * @see #getInitThreshold
     *
     */
    void setInitThreshold(Number value) throws java.lang.IllegalArgumentException;

    /**
     * Gets the offset value.
     *
     * @see #setOffset(Number)
     *
     * @return The offset value.
     */
    Number getOffset();

    /**
     * Sets the offset value.
     *
     * @param value The offset value.
     * @exception java.lang.IllegalArgumentException The specified
     * offset is null or the offset value is less than zero.
     *
     * @see #getOffset()
     */
    void setOffset(Number value) throws java.lang.IllegalArgumentException;

    /**
     * Gets the modulus value.
     *
     * @return The modulus value.
     *
     * @see #setModulus
     */
    Number getModulus();

    /**
     * Sets the modulus value.
     *
     * @param value The modulus value.
     * @exception java.lang.IllegalArgumentException The specified
     * modulus is null or the modulus value is less than zero.
     *
     * @see #getModulus
     */
    void setModulus(Number value) throws java.lang.IllegalArgumentException;

    /**
     * Gets the notification's on/off switch value.
     *
     * @return <CODE>true</CODE> if the counter monitor notifies when
     * exceeding the threshold, <CODE>false</CODE> otherwise.
     *
     * @see #setNotify
     */
    boolean getNotify();

    /**
     * Sets the notification's on/off switch value.
     *
     * @param value The notification's on/off switch value.
     *
     * @see #getNotify
     */
    void setNotify(boolean value);

    /**
     * Gets the difference mode flag value.
     *
     * @return <CODE>true</CODE> if the difference mode is used,
     * <CODE>false</CODE> otherwise.
     *
     * @see #setDifferenceMode
     */
    boolean getDifferenceMode();

    /**
     * Sets the difference mode flag value.
     *
     * @param value The difference mode flag value.
     *
     * @see #getDifferenceMode
     */
    void setDifferenceMode(boolean value);
}
