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
public class AnchorTest {
    // tests for <a id=value>

    /**
     * <a id=foo></a>
     */
    public void a_id_foo() { }

    /**
     * <a id=foo></a>
     */
    public void a_id_already_defined() { }

    /**
     * <a id=></a>
     */
    public void a_id_empty() { }

    /**
     * <a id="123 "></a>
     */
    public void a_id_invalid() { }

    /**
     * <a id ></a>
     */
    public void a_id_missing() { }

    // tests for id=value on non-<a> tags

    /**
     * <p id=p_id_foo>text</p>
     */
    public void p_id_foo() { }

    /**
     * <p id=foo>text</p>
     */
    public void p_id_already_defined() { }

    /**
     * <p id=>text</p>
     */
    public void p_id_empty() { }

    /**
     * <p id="123 ">text</p>
     */
    public void p_id_invalid() { }

    /**
     * <p id >text</p>
     */
    public void p_id_missing() { }


}
