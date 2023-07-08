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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.validation;


import java.util.Iterator;















/**
 * <p>An extension of ValidationState which can be configured to turn
 * off checking for ID/IDREF errors and unparsed entity errors.</p>
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public final class ConfigurableValidationState extends ValidationState {

    /**
     * Whether to check for ID/IDREF errors
     */
    private boolean fIdIdrefChecking;

    /**
     * Whether to check for unparsed entity errors
     */
    private boolean fUnparsedEntityChecking;

    /**
     * Creates a new ConfigurableValidationState.
     * By default, error checking for both ID/IDREFs
     * and unparsed entities are turned on.
     */
    public ConfigurableValidationState() {
        super();
        fIdIdrefChecking = true;
        fUnparsedEntityChecking = true;
    }

    /**
     * Turns checking for ID/IDREF errors on and off.
     * @param setting true to turn on error checking,
     *                 false to turn off error checking
     */
    public void setIdIdrefChecking(boolean setting) {
        fIdIdrefChecking = setting;
    }

    /**
     * Turns checking for unparsed entity errors on and off.
     * @param setting true to turn on error checking,
     *                 false to turn off error checking
     */
    public void setUnparsedEntityChecking(boolean setting) {
        fUnparsedEntityChecking = setting;
    }

    /**
     * Checks if all IDREFs have a corresponding ID.
     * @return null, if ID/IDREF checking is turned off
     *         otherwise, returns the value of the super implementation
     */
    public Iterator<String> checkIDRefID() {
        return (fIdIdrefChecking) ? super.checkIDRefID() : null;
    }

    /**
     * Checks if an ID has already been declared.
     * @return false, if ID/IDREF checking is turned off
     *         otherwise, returns the value of the super implementation
     */
    public boolean isIdDeclared(String name) {
        return fIdIdrefChecking && super.isIdDeclared(name);
    }

    /**
     * Checks if an entity is declared.
     * @return true, if unparsed entity checking is turned off
     *         otherwise, returns the value of the super implementation
     */
    public boolean isEntityDeclared(String name) {
        return !fUnparsedEntityChecking || super.isEntityDeclared(name);
    }

    /**
     * Checks if an entity is unparsed.
     * @return true, if unparsed entity checking is turned off
     *         otherwise, returns the value of the super implementation
     */
    public boolean isEntityUnparsed(String name) {
        return !fUnparsedEntityChecking || super.isEntityUnparsed(name);
    }

    /**
     * Adds the ID, if ID/IDREF checking is enabled.
     * @param name the ID to add
     */
    public void addId(String name) {
        if (fIdIdrefChecking) {
            super.addId(name);
        }
    }

    /**
     * Adds the IDREF, if ID/IDREF checking is enabled.
     * @param name the IDREF to add
     */
    public void addIdRef(String name) {
        if (fIdIdrefChecking) {
            super.addIdRef(name);
        }
    }
}
