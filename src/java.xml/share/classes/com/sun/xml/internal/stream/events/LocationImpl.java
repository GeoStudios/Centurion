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

package java.xml.share.classes.com.sun.xml.internal.stream.events;

import javax.xml.stream.Location;

/**
 *Implementation of Location interface to be used by
 *event readers.
 */
public class LocationImpl implements Location{
    String systemId;
    String publicId;
    int colNo;
    int lineNo;
    int charOffset;
    LocationImpl(Location loc){
        systemId = loc.getSystemId();
        publicId = loc.getPublicId();
        lineNo = loc.getLineNumber();
        colNo = loc.getColumnNumber();
        charOffset = loc.getCharacterOffset();
    }

    public int getCharacterOffset(){
        return charOffset;
    }

    public int getColumnNumber() {
        return colNo;
    }

    public int getLineNumber(){
        return lineNo;
    }

    public String getPublicId(){
        return publicId;
    }

    public String getSystemId(){
        return systemId;
    }

    public String toString(){
        String sbuffer = "Line number = " + getLineNumber() +
                "\n" +
                "Column number = " + getColumnNumber() +
                "\n" +
                "System Id = " + getSystemId() +
                "\n" +
                "Public Id = " + getPublicId() +
                "\n" +
                "CharacterOffset = " + getCharacterOffset() +
                "\n";
        return sbuffer;
    }

}