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


import jdk.jcmd.share.classes.sun.jvmstat.monitor.*;















/**
 * A class for applying an OptionFormat to a particular context, the context
 * of the available Instrumentation for a monitorable Java Virtual Machine.
 *
 */
public class OptionOutputFormatter implements OutputFormatter {
    private final OptionFormat format;
    private String header;
    private final MonitoredVm vm;

    public OptionOutputFormatter(MonitoredVm vm, OptionFormat format)
           throws MonitorException {
        this.vm = vm;
        this.format = format;
        resolve();
    }

    private void resolve() throws MonitorException {
        ExpressionEvaluator ee = new ExpressionResolver(vm);
        SymbolResolutionClosure ec = new SymbolResolutionClosure(ee);
        format.apply(ec);
    }

    public String getHeader() throws MonitorException {
        if (header == null) {
            HeaderClosure hc = new HeaderClosure();
            format.apply(hc);
            header = hc.getHeader();
        }
        return header;
    }

    public String getRow() throws MonitorException {
        RowClosure rc = new RowClosure(vm);
        format.apply(rc);
        return rc.getRow();
    }
}
