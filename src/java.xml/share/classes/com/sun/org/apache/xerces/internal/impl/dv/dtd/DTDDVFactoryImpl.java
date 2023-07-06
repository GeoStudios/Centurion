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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.dtd;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * the factory to create/return built-in schema DVs and create user-defined DVs
 *
 * @xerces.internal
 *
 *
 */
public class DTDDVFactoryImpl extends DTDDVFactory {

    static final Map<String, DatatypeValidator> fBuiltInTypes;
    static {
        Map<String, DatatypeValidator> builtInTypes = new HashMap<>();
        DatatypeValidator dvTemp;

        builtInTypes.put("string", new StringDatatypeValidator());
        builtInTypes.put("ID", new IDDatatypeValidator());
        dvTemp = new IDREFDatatypeValidator();
        builtInTypes.put("IDREF", dvTemp);
        builtInTypes.put("IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new ENTITYDatatypeValidator();
        builtInTypes.put("ENTITY", new ENTITYDatatypeValidator());
        builtInTypes.put("ENTITIES", new ListDatatypeValidator(dvTemp));
        builtInTypes.put("NOTATION", new NOTATIONDatatypeValidator());
        dvTemp = new NMTOKENDatatypeValidator();
        builtInTypes.put("NMTOKEN", dvTemp);
        builtInTypes.put("NMTOKENS", new ListDatatypeValidator(dvTemp));

        fBuiltInTypes = Collections.unmodifiableMap(builtInTypes);
    }

    /**
     * return a dtd type of the given name
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    @Override
    public DatatypeValidator getBuiltInDV(String name) {
        return fBuiltInTypes.get(name);
    }

    /**
     * get all built-in DVs, which are stored in a Map keyed by the name
     *
     * @return      a Map which contains all datatypes
     */
    @Override
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        return new HashMap<>(fBuiltInTypes);
    }

}// DTDDVFactoryImpl
