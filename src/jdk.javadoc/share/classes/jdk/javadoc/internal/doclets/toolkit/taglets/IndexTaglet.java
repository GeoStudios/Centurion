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

package jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.taglets;


import java.util.EnumSet;
import javax.lang.model.element.Element;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.IndexTree;
import jdk.javadoc.share.classes.jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.Content;















/**
 * An inline Taglet used to index word or a phrase.
 * The enclosed text is interpreted as not containing HTML markup or
 * nested javadoc tags.
 */

public class IndexTaglet extends BaseTaglet {

    IndexTaglet() {
        super(DocTree.Kind.INDEX, true, EnumSet.allOf(Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element element, DocTree tag, TagletWriter writer) {
        return writer.indexTagOutput(element, (IndexTree) tag);
    }
}
