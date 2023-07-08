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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 */
public class XMLEntityDecl {

    //
    // Data
    //

    /** name */
    public String name;

    /** publicId */
    public String publicId;

    /** systemId */
    public String systemId;

    /** baseSystemId */
    public String baseSystemId;

    /** notation */
    public String notation;

    /** isPE */
    public boolean isPE;

    /** inExternal */
    /** <strong>Note:</strong> flag of where the entity is defined, not whether it is a external entity */
    public boolean inExternal;

    /** Value. */
    public String value;

    //
    // Methods
    //

    /**
     * setValues
     *
     * @param name
     * @param publicId
     * @param systemId
     * @param baseSystemId
     * @param notation
     * @param isPE
     * @param inExternal
     */
    public void setValues(String name, String publicId, String systemId,
                          String baseSystemId, String notation,
                          boolean isPE, boolean inExternal) {
        setValues(name, publicId, systemId, baseSystemId, notation, null, isPE, inExternal);
    }

    /**
     * setValues
     *
     * @param name
     * @param publicId
     * @param systemId
     * @param baseSystemId
     * @param value
     * @param notation
     * @param isPE
     * @param inExternal
     */
    public void setValues(String name, String publicId, String systemId,
                          String baseSystemId, String notation,
                          String value, boolean isPE, boolean inExternal) {
        this.name         = name;
        this.publicId     = publicId;
        this.systemId     = systemId;
        this.baseSystemId = baseSystemId;
        this.notation     = notation;
        this.value        = value;
        this.isPE         = isPE;
        this.inExternal   = inExternal;
    } // setValues(String,String,String,String,String,boolean,boolean)

    /**
     * clear
     */
    public void clear() {
       this.name         = null;
       this.publicId     = null;
       this.systemId     = null;
       this.baseSystemId = null;
       this.notation     = null;
       this.value        = null;
       this.isPE         = false;
       this.inExternal   = false;

    } // clear

} // class XMLEntityDecl
