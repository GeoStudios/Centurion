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

package build.tools.taglet;



















































/**
 * An inline tag to conveniently insert an external link.
 * The tag can be used as follows:
 * {&#64;extLink name description}, for example
 * <p>
 * {@code Please see {@extLink Borealis a spectacular} sight.}
 * <p>
 * will produce the following html
 * <p>
 * {@code
 * Please see <a href="https://docs.oracle.com/pls/topic/lookup?ctx=javase10&id=Borealis">a spectacular</a> sight.
 * }
 */
public class ExtLink implements Taglet {
    static final String SPEC_VERSION;

    static {
        SPEC_VERSION = System.getProperty("extlink.spec.version");
        if (SPEC_VERSION == null) {
            throw new RuntimeException("extlink.spec.version property not set");
        }
    }

    static final String TAG_NAME = "extLink";

    static final String URL = "https://docs.oracle.com/pls/topic/lookup?ctx=javase" +
        SPEC_VERSION + "&amp;id=";

    static final Pattern TAG_PATTERN = Pattern.compile("(?s)(\\s*)(?<name>\\w+)(\\s+)(?<desc>.*)$");


    /**
     * Returns the set of locations in which the tag may be used.
     */
    @Override
    public Set<Location> getAllowedLocations() {
        return EnumSet.allOf(jdk.javadoc.doclet.Taglet.Location.class);
    }

    @Override
    public boolean isInlineTag() {
        return true;
    }

    @Override
    public String getName() {
        return TAG_NAME;
    }

    @Override
    public String toString(List<? extends DocTree> tags, Element elem) {

        if (tags.isEmpty())
            return "";

        DocTree tag = tags.get(0);
        if (tag.getKind() != UNKNOWN_INLINE_TAG)
            return "";

        UnknownInlineTagTree uitree = (UnknownInlineTagTree) tag;
        if (uitree.getContent().isEmpty())
            return "";

        String tagText = uitree.getContent().get(0).toString();
        Matcher m = TAG_PATTERN.matcher(tagText);
        if (!m.find())
            return "";

        StringBuilder sb = new StringBuilder("<a href=\"");
        sb.append(URL)
          .append(m.group("name"))
          .append("\">")
          .append(m.group("desc"))
          .append("</a>");

        return sb.toString();
    }
}
