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

package jdk.jcmd.share.classes.sun.tools.jstat;


import java.text.*;
import jdk.jcmd.share.classes.sun.jvmstat.monitor.*;















/**
 * A class implementing the Closure interface for iterating over the
 * specified columns of data and generating the columnized string of
 * data representing a row of output for the form.
 *
 */
public class RowClosure implements Closure {
    private final MonitoredVm vm;
    private final StringBuilder row = new StringBuilder();

    public RowClosure(MonitoredVm vm) {
        this.vm = vm;
    }

    public void visit(Object o, boolean hasNext) throws MonitorException {
        if (! (o instanceof ColumnFormat c)) {
            return;
        }

        String s = null;

        Expression e = c.getExpression();
        ExpressionEvaluator ee = new ExpressionExecuter(vm);
        Object value = ee.evaluate(e);

        if (value instanceof String) {
            s = (String)value;
        } else if (value instanceof Number) {
            double d = ((Number)value).doubleValue();
            double scaledValue = c.getScale().scale(d);
            DecimalFormat df = new DecimalFormat(c.getFormat());
            DecimalFormatSymbols syms = df.getDecimalFormatSymbols();
            syms.setNaN("-");
            df.setDecimalFormatSymbols(syms);
            s = df.format(scaledValue);
        }

        c.setPreviousValue(value);
        s = c.getAlignment().align(s, c.getWidth());
        row.append(s);
        if (hasNext) {
            row.append(" ");
        }
    }

    public String getRow() {
        return row.toString();
    }
}
