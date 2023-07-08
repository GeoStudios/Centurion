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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;


import javax.xml.stream.Location;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * <p>A light wrapper around a StAX location. This is useful
 * when bridging between StAX and XNI components.</p>
 *
 *
 */
public final class StAXLocationWrapper implements XMLLocator {

    private Location fLocation = null;

    public StAXLocationWrapper() {}

    public void setLocation(Location location) {
        fLocation = location;
    }

    public Location getLocation() {
        return fLocation;
    }

    /*
     * XMLLocator methods
     */

    public String getPublicId() {
        if (fLocation != null) {
            return fLocation.getPublicId();
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (fLocation != null) {
            return fLocation.getSystemId();
        }
        return null;
    }

    public String getBaseSystemId() {
        return null;
    }

    public String getExpandedSystemId() {
        return getLiteralSystemId();
    }

    public int getLineNumber() {
        if (fLocation != null) {
            return fLocation.getLineNumber();
        }
        return -1;
    }

    public int getColumnNumber() {
        if (fLocation != null) {
            return fLocation.getColumnNumber();
        }
        return -1;
    }

    public int getCharacterOffset() {
        if (fLocation != null) {
            return fLocation.getCharacterOffset();
        }
        return -1;
    }

    public String getEncoding() {
        return null;
    }

    public String getXMLVersion() {
        return null;
    }

} // StAXLocationWrapper
