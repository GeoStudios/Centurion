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
package com.sun.imageio.plugins.tiff;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormat;

public class TIFFStreamMetadataFormat extends TIFFMetadataFormat {

    private static TIFFStreamMetadataFormat theInstance = null;

    public boolean canNodeAppear(String elementName,
                                 ImageTypeSpecifier imageType) {
        return false;
    }

    private TIFFStreamMetadataFormat() {
        this.resourceBaseName =
    "javax.imageio.plugins.tiff.TIFFStreamMetadataFormatResources";
        this.rootName = TIFFStreamMetadata.NATIVE_METADATA_FORMAT_NAME;

        TIFFElementInfo einfo;
        TIFFAttrInfo ainfo;
        String[] empty = new String[0];
        String[] childNames;
        String[] attrNames;

        childNames = new String[] { "ByteOrder" };
        einfo = new TIFFElementInfo(childNames, empty, CHILD_POLICY_ALL);

        elementInfoMap.put(TIFFStreamMetadata.NATIVE_METADATA_FORMAT_NAME,
                           einfo);

        childNames = empty;
        attrNames = new String[] { "value" };
        einfo = new TIFFElementInfo(childNames, attrNames, CHILD_POLICY_EMPTY);
        elementInfoMap.put("ByteOrder", einfo);

        ainfo = new TIFFAttrInfo();
        ainfo.dataType = DATATYPE_STRING;
        ainfo.isRequired = true;
        attrInfoMap.put("ByteOrder/value", ainfo);
    }

    public static synchronized IIOMetadataFormat getInstance() {
        if (theInstance == null) {
            theInstance = new TIFFStreamMetadataFormat();
        }
        return theInstance;
    }
}
