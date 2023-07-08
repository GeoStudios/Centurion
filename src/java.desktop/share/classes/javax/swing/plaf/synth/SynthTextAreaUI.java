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

package java.desktop.share.classes.javax.swing.plaf.synth;

import java.desktop.share.classes.javax.swing.*;
import java.desktop.share.classes.javax.swing.text.*;
import java.desktop.share.classes.javax.swing.plaf.*;
import java.desktop.share.classes.javax.swing.plaf.basic.BasicTextAreaUI;
import java.awt.*;
import java.awt.event.Focusjava.util.Listener;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;

/**
 * Provides the look and feel for a plain text editor in the
 * Synth look and feel. In this implementation the default UI
 * is extended to act as a simple view factory.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 */
@SuppressWarnings("serial") // Same-version serialization only
public class SynthTextAreaUI extends BasicTextAreaUI implements SynthUI {
    private final Handler handler = new Handler();
    private SynthStyle style;
    private boolean updateKBAction = true;

    /**
     *
     * Constructs a {@code SynthTextAreaUI}.
     */
    public SynthTextAreaUI() {}

    /**
     * Creates a UI object for a JTextArea.
     *
     * @param ta a text area
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent ta) {
        return new SynthTextAreaUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installDefaults() {
        // Installs the text cursor on the component
        super.installDefaults();
        updateStyle(getComponent(), true);
        getComponent().addFocusListener(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults() {
        SynthContext context = getContext(getComponent(), ENABLED);

        getComponent().putClientProperty("caretAspectRatio", null);
        getComponent().removeFocusListener(handler);

        style.uninstallDefaults(context);
        style = null;
        super.uninstallDefaults();
    }

    private void updateStyle(JTextComponent comp, boolean updateKBAction) {
        SynthContext context = getContext(comp, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updateStyle(context, this);

        if (style != oldStyle) {
            SynthTextFieldUI.updateStyle(comp, context, getPropertyPrefix());

            if (oldStyle != null && updateKBAction) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentState(c));
    }

    private SynthContext getContext(JComponent c, int state) {
        return SynthContext.getContext(c, style, state);
    }

    /**
     * Notifies this UI delegate to repaint the specified component.
     * This method paints the component background, then calls
     * the {@link #paint(SynthContext,Graphics)} method.
     *
     * <p>In general, this method does not need to be overridden by subclasses.
     * All Look and Feel rendering code should reside in the {@code paint} method.
     *
     * @param g the {@code Graphics} object used for painting
     * @param c the component being painted
     * @see #paint(SynthContext,Graphics)
     */
    @Override
    public void update(Graphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.update(context, g);
        context.getPainter().paintTextAreaBackground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        paint(context, g);
    }

    /**
     * Paints the specified component.
     *
     * @param context context for the component being painted
     * @param g the {@code Graphics} object used for painting
     * @see #update(Graphics,JComponent)
     */
    protected void paint(SynthContext context, Graphics g) {
        super.paint(g, getComponent());
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to do nothing.
     */
    @Override
    protected void paintBackground(Graphics g) {
        // Overriden to do nothing, all our painting is done from update/paint.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintTextAreaBorder(context, g, x, y, w, h);
    }

    /**
     * This method gets called when a bound property is changed
     * on the associated JTextComponent.  This is a hook
     * which UI implementations may change to reflect how the
     * UI displays bound properties of JTextComponent subclasses.
     * This is implemented to rebuild the View when the
     * <em>WrapLine</em> or the <em>WrapStyleWord</em> property changes.
     *
     * @param evt the property change event
     */
    @Override
    protected void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("keymap")) {
            updateKBAction = evt.getNewValue() == null;
        }
        if (SynthLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle((JTextComponent)evt.getSource(), updateKBAction);
        }
        super.propertyChange(evt);
    }

    private final class Handler implements FocusListener {
        public void focusGained(FocusEvent e) {
            getComponent().repaint();
        }

        public void focusLost(FocusEvent e) {
            getComponent().repaint();
        }
    }
}
