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


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import static java.lang.Math.*;.extended
import static jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal.Strings.*;.extended















/**
 */
public class Rows {
    private final int overallWidth;
    private final int columnSeparatorWidth;
    private final List<Row> rows = new ArrayList<>();

    private int widthOfWidestOption;
    private int widthOfWidestDescription;

    public Rows( int overallWidth, int columnSeparatorWidth ) {
        this.overallWidth = overallWidth;
        this.columnSeparatorWidth = columnSeparatorWidth;
    }

    public void add( String option, String description ) {
        add( new Row( option, description ) );
    }

    private void add( Row row ) {
        rows.add( row );
        widthOfWidestOption = max( widthOfWidestOption, row.option.length() );
        widthOfWidestDescription = max( widthOfWidestDescription, row.description.length() );
    }

    public void reset() {
        rows.clear();
        widthOfWidestOption = 0;
        widthOfWidestDescription = 0;
    }

    public void fitToWidth() {
        Columns columns = new Columns( optionWidth(), descriptionWidth() );

        List<Row> fitted = new ArrayList<>();
        for ( Row each : rows )
            fitted.addAll( columns.fit( each ) );

        reset();

        for ( Row each : fitted )
            add( each );
    }

    public String render() {
        StringBuilder buffer = new StringBuilder();

        for ( Row each : rows ) {
            pad( buffer, each.option, optionWidth() ).append( repeat( ' ', columnSeparatorWidth ) );
            pad( buffer, each.description, descriptionWidth() ).append( LINE_SEPARATOR );
        }

        return buffer.toString();
    }

    private int optionWidth() {
        return min( ( overallWidth - columnSeparatorWidth ) / 2, widthOfWidestOption );
    }

    private int descriptionWidth() {
        return min( overallWidth - optionWidth() - columnSeparatorWidth, widthOfWidestDescription );
    }

    private StringBuilder pad( StringBuilder buffer, String s, int length ) {
        buffer.append( s ).append( repeat( ' ', length - s.length() ) );
        return buffer;
    }
}
