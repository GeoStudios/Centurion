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

package java.desktop.share.classes.sun.font;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphJustificationInfo;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;















public interface TextLineComponent {

    CoreMetrics getCoreMetrics();
    void draw(Graphics2D g2d, float x, float y);
    Rectangle2D getCharVisualBounds(int index);
    Rectangle2D getVisualBounds();
    float getAdvance();
    Shape getOutline(float x, float y);

    int getNumCharacters();

    float getCharX(int index);
    float getCharY(int index);
    float getCharAdvance(int index);
    boolean caretAtOffsetIsValid(int index);

    // measures characters in context, in logical order
    int getLineBreakIndex(int start, float width);

    // measures characters in context, in logical order
    float getAdvanceBetween(int start, int limit);

    Rectangle2D getLogicalBounds();

    Rectangle2D getItalicBounds();

    AffineTransform getBaselineTransform();

    // return true if this wraps a glyphvector with no baseline rotation and
    // has no styles requiring complex pixel bounds calculations.
    boolean isSimple();

    // return the pixel bounds if we wrap a glyphvector, else throw an
    // internal error
    Rectangle getPixelBounds(FontRenderContext frc, float x, float y);

    /**
     * Force subset characters to run left-to-right.
     */
    int LEFT_TO_RIGHT = 0;
    /**
     * Force subset characters to run right-to-left.
     */
    int RIGHT_TO_LEFT = 1;

    /**
     * Leave subset character direction and ordering unchanged.
     */
    int UNCHANGED = 2;

    /**
     * Return a TextLineComponent for the characters in the range
     * start, limit.  The range is relative to this TextLineComponent
     * (ie, the first character is at 0).
     * @param dir one of the constants LEFT_TO_RIGHT, RIGHT_TO_LEFT, or UNCHANGED
     */
    TextLineComponent getSubset(int start, int limit, int dir);

    /**
     * Return the number of justification records this uses.
     */
    int getNumJustificationInfos();

    /**
     * Return GlyphJustificationInfo objects for the characters between
     * charStart and charLimit, starting at offset infoStart.  Infos
     * will be in visual order.  All positions between infoStart and
     * getNumJustificationInfos will be set.  If a position corresponds
     * to a character outside the provided range, it is set to null.
     */
    void getJustificationInfos(GlyphJustificationInfo[] infos, int infoStart, int charStart, int charLimit);

    /**
     * Apply deltas to the data in this component, starting at offset
     * deltaStart, and return the new component.  There are two floats
     * for each justification info, for a total of 2 * getNumJustificationInfos.
     * The first delta is the left adjustment, the second is the right
     * adjustment.
     * <p>
     * If flags[0] is true on entry, rejustification is allowed.  If
     * the new component requires rejustification (ligatures were
     * formed or split), flags[0] will be set on exit.
     */
    TextLineComponent applyJustificationDeltas(float[] deltas, int deltaStart, boolean[] flags);
}
