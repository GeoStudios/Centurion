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

package jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal;

















/**
 */
class Row {
    final String option;
    final String description;

    Row( String option, String description ) {
        this.option = option;
        this.description = description;
    }

    @Override
    public boolean equals( Object that ) {
        if ( that == this )
            return true;
        if ( that == null || !getClass().equals( that.getClass() ) )
            return false;

        Row other = (Row) that;
        return option.equals( other.option ) && description.equals( other.description );
    }

    @Override
    public int hashCode() {
        return option.hashCode() ^ description.hashCode();
    }
}
