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

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */



import java.awt.Color;
import java.awt.Font;


/**
 */
public class SampleData {

    /** Font used for drawing. */
    protected Font font;
    /** Color used for text. */
    protected Color color;
    /** Value to display. */
    protected String string;

    /**
     * Constructs a new instance of SampleData with the passed in
     * arguments.
     */
    public SampleData(Font newFont, Color newColor, String newString) {
        font = newFont;
        color = newColor;
        string = newString;
    }

    /**
     * Sets the font that is used to represent this object.
     */
    public void setFont(Font newFont) {
        font = newFont;
    }

    /**
     * Returns the Font used to represent this object.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets the color used to draw the text.
     */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /**
     * Returns the color used to draw the text.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the string to display for this object.
     */
    public void setString(String newString) {
        string = newString;
    }

    /**
     * Returnes the string to display for this object.
     */
    public String string() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
