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

package java.desktop.share.classes.java.awt;


import java.desktop.share.classes.java.io.Serial;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;















/**
 * {@code Panel} is the simplest container class. A panel
 * provides space in which an application can attach any other
 * component, including other panels.
 * <p>
 * The default layout manager for a panel is the
 * {@code FlowLayout} layout manager.
 *
 * @see     java.awt.FlowLayout
 */
public class Panel extends Container implements Accessible {
    private static final String base = "panel";
    private static int nameCounter = 0;

    /**
     * Use serialVersionUID from JDK 1.1 for interoperability.
     */
     @Serial
     private static final long serialVersionUID = -2728009084054400034L;

    /**
     * Creates a new panel using the default layout manager.
     * The default layout manager for all panels is the
     * {@code FlowLayout} class.
     */
    public Panel() {
        this(new FlowLayout());
    }

    /**
     * Creates a new panel with the specified layout manager.
     * @param layout the layout manager for this panel.
     */
    public Panel(LayoutManager layout) {
        setLayout(layout);
    }

    /**
     * Construct a name for this component.  Called by getName() when the
     * name is null.
     */
    String constructComponentName() {
        synchronized (Panel.class) {
            return base + nameCounter++;
        }
    }

    /**
     * Creates the Panel's peer.  The peer allows you to modify the
     * appearance of the panel without changing its functionality.
     */

    public void addNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getComponentFactory().createPanel(this);
            super.addNotify();
        }
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext associated with this Panel.
     * For panels, the AccessibleContext takes the form of an
     * AccessibleAWTPanel.
     * A new AccessibleAWTPanel instance is created if necessary.
     *
     * @return an AccessibleAWTPanel that serves as the
     *         AccessibleContext of this Panel
     */
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleAWTPanel();
        }
        return accessibleContext;
    }

    /**
     * This class implements accessibility support for the
     * {@code Panel} class.  It provides an implementation of the
     * Java Accessibility API appropriate to panel user-interface elements.
     */
    protected class AccessibleAWTPanel extends AccessibleAWTContainer {

        /**
         * Use serialVersionUID from JDK 1.3 for interoperability.
         */
        @Serial
        private static final long serialVersionUID = -6409552226660031050L;

        /**
         * Constructs an {@code AccessibleAWTPanel}.
         */
        protected AccessibleAWTPanel() {}

        /**
         * Get the role of this object.
         *
         * @return an instance of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    }

}
