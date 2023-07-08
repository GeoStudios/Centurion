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
 * Handles parsing of files in Locale Data Markup Language for
 * SupplementalMetadata.xml
 */

class SupplementalMetadataParseHandler extends AbstractLDMLHandler<Object> {
    private final Map<String, String> languageAliasMap;

    SupplementalMetadataParseHandler() {
        languageAliasMap = new HashMap<>();
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
        case "zoneAlias":
            String reason = attributes.getValue("reason");
            if ("deprecated".equals(reason)) {
                put(attributes.getValue("type"), attributes.getValue("replacement"));
            }
            pushIgnoredContainer(qName);
            break;
        case "languageAlias":
            String aliasReason = attributes.getValue("reason");
            if ("deprecated".equals(aliasReason) || "legacy".equals(aliasReason)) {
                String tag = attributes.getValue("type");
                if (!checkLegacyLocales(tag)) {
                   languageAliasMap.put(tag.replaceAll("_", "-"),
                   attributes.getValue("replacement").replaceAll("_", "-"));
                }
            }
            pushIgnoredContainer(qName);
            break;
        default:
            // treat anything else as a container
            pushContainer(qName, attributes);
            break;
        }
    }

    public Stream<String> deprecatedMap() {
        return keySet().stream()
                .map(k -> String.format("        \"%s\", \"%s\",", k, get(k)))
                .sorted();
    }
    Map<String, String> getLanguageAliasData() {
        return languageAliasMap;
    }

    // skip language aliases for JDK legacy locales for ISO compatibility
    private boolean checkLegacyLocales(String tag) {
        return (tag.startsWith("no") || tag.startsWith("in")
                || tag.startsWith("iw") || tag.startsWith("ji"));
    }
}
