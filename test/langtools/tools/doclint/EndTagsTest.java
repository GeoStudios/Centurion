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
public class EndTagsTest {
    /** <p>  <a id="a1"> text <img alt="image" src="image.png"> </a> </p> */
    public void valid_all() { }

    /** <p>  <a id="a2"> text <img alt="image" src="image.png"> </a> */
    public void valid_omit_optional_close() { }

    /** </a> */
    public void invalid_missing_start() { }

    /** <p> </a> */
    public void invalid_missing_start_2() { }

    /** <p> text </p> </a> */
    public void invalid_missing_start_3() { }

    /** <img alt="image" src="image.png"> </img> */
    public void invalid_end() { }

    /** <invalid> </invalid> */
    public void unknown_start_end() { }

    /** <invalid> */
    public void unknown_start() { }

    /** </invalid> */
    public void unknown_end() { }
}

