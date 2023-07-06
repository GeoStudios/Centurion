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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;















/**
 * the factory to create/return built-in XML 1.1 DVs and create user-defined DVs
 *
 * @xerces.internal
 *
 *
 */
public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {

    static Map<String, DatatypeValidator> XML11BUILTINTYPES;
    static {
        Map<String, DatatypeValidator> xml11BuiltInTypes = new HashMap<>();
        xml11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
        DatatypeValidator dvTemp = new XML11IDREFDatatypeValidator();
        xml11BuiltInTypes.put("XML11IDREF", dvTemp);
        xml11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new XML11NMTOKENDatatypeValidator();
        xml11BuiltInTypes.put("XML11NMTOKEN", dvTemp);
        xml11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(dvTemp));
        XML11BUILTINTYPES = Collections.unmodifiableMap(xml11BuiltInTypes);
    } // <clinit>

    /**
     * return a dtd type of the given name
     * This will call the super class if and only if it does not
     * recognize the passed-in name.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    @Override
    public DatatypeValidator getBuiltInDV(String name) {
        if(XML11BUILTINTYPES.get(name) != null) {
            return XML11BUILTINTYPES.get(name);
        }
        return fBuiltInTypes.get(name);
    }

    /**
     * get all built-in DVs, which are stored in a Map keyed by the name
     * New XML 1.1 datatypes are inserted.
     *
     * @return      a Map which contains all datatypes
     */
    @Override
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        final HashMap<String, DatatypeValidator> toReturn = new HashMap<>(fBuiltInTypes);
        toReturn.putAll(XML11BUILTINTYPES);
        return toReturn;
    }
}//XML11DTDDVFactoryImpl
