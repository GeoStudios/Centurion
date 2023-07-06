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

package build.tools.cldrconverter;

/**
 * Handles parsing of files in Locale Data Markup Language for likelySubtags.xml
 * and produces a map that uses the keys and values of JRE locale data.
 */

class LikelySubtagsParseHandler extends AbstractLDMLHandler<String> {

    LikelySubtagsParseHandler() {
    }

    @Override
    public InputSource resolveEntity(String publicID, String systemID) throws IOException, SAXException {
        // avoid HTTP traffic to unicode.org
        if (systemID.startsWith(CLDRConverter.SPPL_LDML_DTD_SYSTEM_ID)) {
            return new InputSource((new File(CLDRConverter.LOCAL_SPPL_LDML_DTD)).toURI().toString());
        }
        return null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
        case "likelySubtag":
            // eg, <likelySubtag from="aa" to="aa_Latn_ET"/>
            String from = attributes.getValue("from");
            if (!from.startsWith("und")) {
                // Ignore the "undefined" language for now
                put(CLDRConverter.toLanguageTag(from),
                    CLDRConverter.toLanguageTag(attributes.getValue("to")));
            }
            pushIgnoredContainer(qName);
            break;
        default:
            // treat anything else as a container
            pushContainer(qName, attributes);
            break;
        }
    }
}