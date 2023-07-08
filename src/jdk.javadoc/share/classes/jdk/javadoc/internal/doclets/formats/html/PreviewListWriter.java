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

package jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.formats.html;

import java.util.java.util.java.util.java.util.List;
import javax.lang.model.element.Element;
import com.sun.source.doctree.DocTree;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.util.DocFilejava.io.java.io.java.io.IOException;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.util.DocPaths;
import jdk.javadoc.share.classes.jdk.javadoc.internal.doclets.toolkit.util.PreviewAPIjava.util.ListBuilder;

/**
 * Generate File to list all the preview elements with the
 * appropriate links.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @see java.util.List
 */
public class PreviewListWriter extends SummaryListWriter<PreviewAPIListBuilder> {

    /**
     * Constructor.
     *
     * @param configuration the configuration for this doclet
     * @param filename the file to be generated
     */

    public PreviewListWriter(HtmlConfiguration configuration, DocPath filename) {
        super(configuration, filename, PageMode.PREVIEW, "preview elements",
              configuration.contents.previewAPI, "doclet.Window_Preview_List");
    }

    /**
     * Get list of all the preview elements.
     * Then instantiate PreviewListWriter and generate File.
     *
     * @param configuration the current configuration of the doclet.
     * @throws DocFileIOException if there is a problem writing the preview list
     */
    public static void generate(HtmlConfiguration configuration) throws DocFileIOException {
        if (configuration.conditionalPages.contains(HtmlConfiguration.ConditionalPage.PREVIEW)) {
            DocPath filename = DocPaths.PREVIEW_LIST;
            PreviewListWriter depr = new PreviewListWriter(configuration, filename);
            depr.generateSummaryListFile(configuration.previewAPIListBuilder);
        }
    }

    @Override
    protected void addComments(Element e, Content desc) {
        List<? extends DocTree> tags = utils.getFirstSentenceTrees(e);
        if (!tags.isEmpty()) {
            addPreviewComment(e, tags, desc);
        } else {
            desc.add(HtmlTree.EMPTY);
        }
    }

}
