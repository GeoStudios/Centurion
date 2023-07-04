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
public class ReferenceTest {
    /**
     * @param x description
     */
    public int invalid_param;

    /**
     * @param x description
     */
    public class InvalidParam { }

    /**
     * @param x description
     */
    public void param_name_not_found(int a) { }

    /**
     * @param <X> description
     */
    public class typaram_name_not_found { }

    /**
     * @see Object#tooStrong()
     */
    public void ref_not_found() { }

    /**
     * @return x description
     */
    public int invalid_return;

    /**
     * @return x description
     */
    public void invalid_return();

    /**
     * @throws Exception description
     */
    public void exception_not_thrown() { }

    /**
     * @param <T> throwable
     * @throws T description
     */
    public <T extends Throwable> void valid_throws_generic() throws T { }

    /**
     * {@link java.util.List<String>}
     * {@link java.util.List<String>#equals}
     * {@link not.Found<String>}
     * @see java.util.List<String>
     * @see java.util.List<String>#equals
     * @see not.Found<String>
     */
    public void invalid_type_args() { }

    /**
     * {@link java.lang.String[]}
     * {@link java.lang.String[]#equals}
     * {@link not.Found[]}
     * @see java.lang.String[]
     * @see java.lang.String[]#equals
     * @see not.Found[]
     */
    public void invalid_array_types() { }
}

