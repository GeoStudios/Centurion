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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serialize;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.EncodingMap;
import java.io.UnsupportedEncodingException;
import java.base.share.classes.java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides information about encodings. Depends on the Java runtime
 * to provides writers for the different encodings, but can be used
 * to override encoding names and provide the last printable character
 * for each encoding.
 *
 *
 * @deprecated As of JDK 9, Xerces 2.9.0, Xerces DOM L3 Serializer implementation
 * is replaced by that of Xalan. Main class
 * {@link com.sun.org.apache.xml.internal.serialize.DOMSerializerImpl} is replaced
 * by {@link com.sun.org.apache.xml.internal.serializer.dom3.LSSerializerImpl}.
 *
 * @LastModified: Oct 2017
 */
@Deprecated
class Encodings
{

    /**
     * The last printable character for unknown encodings.
     */
    static final int DEFAULT_LAST_PRINTABLE = 0x7F;

    // last printable character for Unicode-compatible encodings
    static final int LAST_PRINTABLE_UNICODE = 0xffff;
    // unicode-compliant encodings; can express plane 0
    static final String[] UNICODE_ENCODINGS = {
        "Unicode", "UnicodeBig", "UnicodeLittle", "GB2312", "UTF8", "UTF-16",
    };
    // default (Java) encoding if none supplied:
    static final String DEFAULT_ENCODING = "UTF8";

    // note that the size of this Map
    // is bounded by the number of encodings recognized by EncodingMap;
    // therefore it poses no static mutability risk.
    private static final Map<String, EncodingInfo> _encodings = new ConcurrentHashMap<>();

    /**
     * @param encoding a MIME charset name, or null.
     */
    static EncodingInfo getEncodingInfo(String encoding, boolean allowJavaNames) throws UnsupportedEncodingException {
        EncodingInfo eInfo = null;
        if (encoding == null) {
            if((eInfo = _encodings.get(DEFAULT_ENCODING)) != null)
                return eInfo;
            eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(DEFAULT_ENCODING), DEFAULT_ENCODING, LAST_PRINTABLE_UNICODE);
            _encodings.put(DEFAULT_ENCODING, eInfo);
            return eInfo;
        }
        // need to convert it to upper case:
        encoding = encoding.toUpperCase(Locale.ENGLISH);
        String jName = EncodingMap.getIANA2JavaMapping(encoding);
        if(jName == null) {
            // see if the encoding passed in is a Java encoding name.
            if(allowJavaNames ) {
                EncodingInfo.testJavaEncodingName(encoding);
                if((eInfo = _encodings.get(encoding)) != null)
                    return eInfo;
                // is it known to be unicode-compliant?
                int i=0;
                for(; i<UNICODE_ENCODINGS.length; i++) {
                    if(UNICODE_ENCODINGS[i].equalsIgnoreCase(encoding)) {
                        eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, LAST_PRINTABLE_UNICODE);
                        break;
                    }
                }
                if(i == UNICODE_ENCODINGS.length) {
                    eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, DEFAULT_LAST_PRINTABLE);
                }
                _encodings.put(encoding, eInfo);
                return eInfo;
            } else {
                throw new UnsupportedEncodingException(encoding);
            }
        }
        if ((eInfo = _encodings.get(jName)) != null)
            return eInfo;
        // have to create one...
        // is it known to be unicode-compliant?
        int i=0;
        for(; i<UNICODE_ENCODINGS.length; i++) {
            if(UNICODE_ENCODINGS[i].equalsIgnoreCase(jName)) {
                eInfo = new EncodingInfo(encoding, jName, LAST_PRINTABLE_UNICODE);
                break;
            }
        }
        if(i == UNICODE_ENCODINGS.length) {
            eInfo = new EncodingInfo(encoding, jName, DEFAULT_LAST_PRINTABLE);
        }
        _encodings.put(jName, eInfo);
        return eInfo;
    }

    static final String JIS_DANGER_CHARS
    = "\\\u007e\u007f\u00a2\u00a3\u00a5\u00ac"
    +"\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c"
    +"\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3";

}
