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

/** */
public class HtmlTagsTest {
    /**
     * <xyz> ... </xyz>
     */
    public void unknownTag1() { }

    /**
     * <div> <xyz> </div>
     */
    public void unknownTag2() { }

    /**
     * <br/>
     */
    public void selfClosingTag() { }

    /**
     * <html>
     */
    public void not_allowed() { }

    /**
     * <span> <p> </span>
     */
    public void not_allowed_inline() { }

    /**
     * {@link java.lang.String <p> }
     * {@link java.lang.String <p> }
     */
    public void not_allowed_inline_2() { }

    /**
     * <img src="any.jpg" alt="alt"> </img>
     */
    public void end_not_allowed() { }

    /**
     * <i> <b> </i>
     */
    public void start_not_matched() { }

    /**
     * <i> </b> </i>
     */
    public void end_unexpected() { }

    /**
     * <ul> text <li> ... </li> </ul>
     */
    public void text_not_allowed() { }

    /**
     * <ul> <b>text</b> <li> ... </li> </ul>
     */
    public void inline_not_allowed() { }


}

