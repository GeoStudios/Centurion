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

package jdk.test.whitebox.parser;
















public class DiagnosticCommand {

    public enum DiagnosticArgumentType {
        JLONG, BOOLEAN, STRING, NANOTIME, STRINGARRAY, MEMORYSIZE
    }

    private String name;
    private String desc;
    private DiagnosticArgumentType type;
    private boolean mandatory;
    private String defaultValue;
    private boolean argument;

    public DiagnosticCommand(String name, String desc, DiagnosticArgumentType type,
            boolean mandatory, String defaultValue) {
        this(name, desc, type, false, mandatory, defaultValue);
    }

    public DiagnosticCommand(String name, String desc, DiagnosticArgumentType type,
            boolean argument, boolean mandatory, String defaultValue) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.mandatory = mandatory;
        this.defaultValue = defaultValue;
        this.argument = argument;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public DiagnosticArgumentType getType() {
        return type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isArgument() {
        return argument;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
