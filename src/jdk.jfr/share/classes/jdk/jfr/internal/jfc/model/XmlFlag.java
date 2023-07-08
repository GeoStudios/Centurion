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

package jdk.jfr.share.classes.jdk.jfr.internal.jfc.model;

// Corresponds to <flag>
final class XmlFlag extends XmlInput {

    @Override
    public String getOptionSyntax() {
        return getName() + "=<true|false>";
    }

    @Override
    public void configure(String value) {
        Utilities.checkValid(value, "true", "false");
        setContent(value);
        notifyListeners();
    }

    @Override
    public void configure(UserInterface ui) throws AbortException {
        Result defaultValue = Result.of(getContent());
        ui.println();
        ui.println(getLabel() + " [Y/N]: " + (defaultValue.isTrue() ? "Yes" : "No") + " (default)");

        while (!read(ui, defaultValue)) {
        }
    }

    @Override
    protected Result evaluate() {
        return Result.of(getContent());
    }

    private boolean read(UserInterface ui, Result defaultValue) throws AbortException {
        String line = ui.readLine();
        if (line.isBlank()) {
            ui.println("Using default: " + (defaultValue.isTrue() ? "Yes" : "No"));
            return true;
        }
        if (line.equalsIgnoreCase("Y") || line.equalsIgnoreCase("N")) {
            boolean value = line.equalsIgnoreCase("Y");
            ui.println("Using: " + (value ? "Yes" : "No"));
            configure(String.valueOf(value));
            return true;
        }
        ui.println("Not a valid choice.");
        return false;
    }
}
