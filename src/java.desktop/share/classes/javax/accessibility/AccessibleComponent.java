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

package javax.accessibility;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusListener;

/**
 * The {@code AccessibleComponent} interface should be supported by any object
 * that is rendered on the screen. This interface provides the standard
 * mechanism for an assistive technology to determine and set the graphical
 * representation of an object. Applications can determine if an object supports
 * the {@code AccessibleComponent} interface by first obtaining its
 * {@code AccessibleContext} and then calling the
 * {@link AccessibleContext#getAccessibleComponent} method. If the return value
 * is not {@code null}, the object supports this interface.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleComponent
 */
public interface AccessibleComponent {

    /**
     * Gets the background color of this object.
     *
     * @return the background color, if supported, of the object; otherwise,
     *         {@code null}
     * @see #setBackground
     */
    Color getBackground();

    /**
     * Sets the background color of this object.
     *
     * @param  c the new color for the background
     * @see #setBackground
     */
    void setBackground(Color c);

    /**
     * Gets the foreground color of this object.
     *
     * @return the foreground color, if supported, of the object; otherwise,
     *         {@code null}
     * @see #setForeground
     */
    Color getForeground();

    /**
     * Sets the foreground color of this object.
     *
     * @param  c the new color for the foreground
     * @see #getForeground
     */
    void setForeground(Color c);

    /**
     * Gets the cursor of this object.
     *
     * @return the cursor, if supported, of the object; otherwise, {@code null}
     * @see #setCursor
     */
    Cursor getCursor();

    /**
     * Sets the cursor of this object.
     *
     * @param  cursor the new cursor for the object
     * @see #getCursor
     */
    void setCursor(Cursor cursor);

    /**
     * Gets the font of this object.
     *
     * @return the font, if supported, for the object; otherwise, {@code null}
     * @see #setFont
     */
    Font getFont();

    /**
     * Sets the font of this object.
     *
     * @param  f the new font for the object
     * @see #getFont
     */
    void setFont(Font f);

    /**
     * Gets the {@code FontMetrics} of this object.
     *
     * @param  f the font for which font metrics is to be obtained
     * @return the {@code FontMetrics}, if supported, the object; otherwise,
     *         {@code null}
     * @see #getFont
     */
    FontMetrics getFontMetrics(Font f);

    /**
     * Determines if the object is enabled. Objects that are enabled will also
     * have the {@code AccessibleState.ENABLED} state set in their
     * {@code AccessibleStateSets}.
     *
     * @return {@code true} if object is enabled; otherwise, {@code false}
     * @see #setEnabled
     * @see AccessibleContext#getAccessibleStateSet
     * @see AccessibleState#ENABLED
     * @see AccessibleStateSet
     */
    boolean isEnabled();

    /**
     * Sets the enabled state of the object.
     *
     * @param  b if {@code true}, enables this object; otherwise, disables it
     * @see #isEnabled
     */
    void setEnabled(boolean b);

    /**
     * Determines if the object is visible. Note: this means that the object
     * intends to be visible; however, it may not be showing on the screen
     * because one of the objects that this object is contained by is currently
     * not visible. To determine if an object is showing on the screen, use
     * {@link #isShowing()}
     * <p>
     * Objects that are visible will also have the
     * {@code AccessibleState.VISIBLE} state set in their
     * {@code AccessibleStateSets}.
     *
     * @return {@code true} if object is visible; otherwise, {@code false}
     * @see #setVisible
     * @see AccessibleContext#getAccessibleStateSet
     * @see AccessibleState#VISIBLE
     * @see AccessibleStateSet
     */
    boolean isVisible();

    /**
     * Sets the visible state of the object.
     *
     * @param  b if {@code true}, shows this object; otherwise, hides it
     * @see #isVisible
     */
    void setVisible(boolean b);

    /**
     * Determines if the object is showing. This is determined by checking the
     * visibility of the object and its ancestors. Note: this will return
     * {@code true} even if the object is obscured by another (for example, it
     * is underneath a menu that was pulled down).
     *
     * @return {@code true} if object is showing; otherwise, {@code false}
     */
    boolean isShowing();

    /**
     * Checks whether the specified point is within this object's bounds, where
     * the point's x and y coordinates are defined to be relative to the
     * coordinate system of the object.
     *
     * @param  p the point relative to the coordinate system of the object
     * @return {@code true} if object contains point; otherwise {@code false}
     * @see #getBounds
     */
    boolean contains(Point p);

    /**
     * Returns the location of the object on the screen.
     *
     * @return the location of the object on screen; {@code null} if this object
     *         is not on the screen
     * @see #getBounds
     * @see #getLocation
     */
    Point getLocationOnScreen();

    /**
     * Gets the location of the object relative to the parent in the form of a
     * point specifying the object's top-left corner in the screen's coordinate
     * space.
     *
     * @return An instance of {@code Point} representing the top-left corner of
     *         the object's bounds in the coordinate space of the screen;
     *         {@code null} if this object or its parent are not on the screen
     * @see #getBounds
     * @see #getLocationOnScreen
     */
    Point getLocation();

    /**
     * Sets the location of the object relative to the parent.
     *
     * @param  p the new position for the top-left corner
     * @see #getLocation
     */
    void setLocation(Point p);

    /**
     * Gets the bounds of this object in the form of a {@code Rectangle} object.
     * The bounds specify this object's width, height, and location relative to
     * its parent.
     *
     * @return A rectangle indicating this component's bounds; {@code null} if
     *         this object is not on the screen.
     * @see #contains
     */
    Rectangle getBounds();

    /**
     * Sets the bounds of this object in the form of a {@code Rectangle} object.
     * The bounds specify this object's width, height, and location relative to
     * its parent.
     *
     * @param  r rectangle indicating this component's bounds
     * @see #getBounds
     */
    void setBounds(Rectangle r);

    /**
     * Returns the size of this object in the form of a {@code Dimension}
     * object. The {@code height} field of the {@code Dimension} object contains
     * this object's height, and the {@code width} field of the
     * {@code Dimension} object contains this object's width.
     *
     * @return A {@code Dimension} object that indicates the size of this
     *         component; {@code null} if this object is not on the screen
     * @see #setSize
     */
    Dimension getSize();

    /**
     * Resizes this object so that it has width and height.
     *
     * @param  d The dimension specifying the new size of the object
     * @see #getSize
     */
    void setSize(Dimension d);

    /**
     * Returns the {@code Accessible} child, if one exists, contained at the
     * local coordinate {@code Point}.
     *
     * @param  p The point relative to the coordinate system of this object
     * @return the {@code Accessible}, if it exists, at the specified location;
     *         otherwise {@code null}
     */
    Accessible getAccessibleAt(Point p);

    /**
     * Returns whether this object can accept focus or not. Objects that can
     * accept focus will also have the {@code AccessibleState.FOCUSABLE} state
     * set in their {@code AccessibleStateSets}.
     *
     * @return {@code true} if object can accept focus; otherwise {@code false}
     * @see AccessibleContext#getAccessibleStateSet
     * @see AccessibleState#FOCUSABLE
     * @see AccessibleState#FOCUSED
     * @see AccessibleStateSet
     */
    boolean isFocusTraversable();

    /**
     * Requests focus for this object. If this object cannot accept focus,
     * nothing will happen. Otherwise, the object will attempt to take focus.
     *
     * @see #isFocusTraversable
     */
    void requestFocus();

    /**
     * Adds the specified focus listener to receive focus events from this
     * component.
     *
     * @param  l the focus listener
     * @see #removeFocusListener
     */
    void addFocusListener(FocusListener l);

    /**
     * Removes the specified focus listener so it no longer receives focus
     * events from this component.
     *
     * @param  l the focus listener
     * @see #addFocusListener
     */
    void removeFocusListener(FocusListener l);
}
