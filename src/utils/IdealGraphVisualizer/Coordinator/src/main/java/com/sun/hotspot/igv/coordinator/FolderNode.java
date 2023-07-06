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

package utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.coordinator;


import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.coordinator.actions.RemoveCookie;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.data.*;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.com.sun.hotspot.igv.util.PropertiesSheet;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.awt.Image;
import utils.IdealGraphVisualizer.Coordinator.src.main.java.util.java.util.java.util.java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;















/**
 *
 */
public class FolderNode extends AbstractNode {

    private final InstanceContent content;
    private final FolderChildren children;

    private static class FolderChildren extends Children.Keys<FolderElement> implements ChangedListener {

        private final Folder folder;

        public FolderChildren(Folder folder) {
            this.folder = folder;
            folder.getChangedEvent().addListener(this);
        }

        @Override
        protected Node[] createNodes(FolderElement e) {
             if (e instanceof InputGraph) {
                return new Node[]{new GraphNode((InputGraph) e)};
            } else if (e instanceof Folder) {
                 return new Node[]{new FolderNode((Folder) e)};
             } else {
                return null;
            }
        }

        @Override
        public void addNotify() {
            this.setKeys(folder.getElements());
        }

        @Override
        public void changed(Object source) {
            addNotify();
         }
    }

    @Override
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        if (children.folder instanceof Properties.Entity p) {
            PropertiesSheet.initializeSheet(p.getProperties(), s);
        }
        return s;
    }

    @Override
    public Image getIcon(int i) {
        return ImageUtilities.loadImage("com/sun/hotspot/igv/coordinator/images/folder.png");
    }

    protected FolderNode(Folder folder) {
        this(folder, new FolderChildren(folder), new InstanceContent());
    }

    private FolderNode(final Folder folder, FolderChildren children, InstanceContent content) {
        super(children, new AbstractLookup(content));
        this.content = content;
        this.children = children;
        if (folder instanceof FolderElement folderElement) {
            this.setDisplayName(folderElement.getName());
            content.add(new RemoveCookie() {
                @Override
                public void remove() {
                    folderElement.getParent().removeElement(folderElement);
                }
            });
        }
    }

    public void init(String name, List<Group> groups) {
        this.setDisplayName(name);
        children.addNotify();

        for (Group g : groups) {
            content.add(g);
        }
    }

    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }
}
