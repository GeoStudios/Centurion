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

import javax.script.*;
import java.util.*;

// do many bad things to prevent ScriptEngineManager to run correctly
public class BadFactory implements ScriptEngineFactory {
    public String getEngineName() {
        return null;
    }

    public String getEngineVersion() {
        return null;
    }

    public List<String> getExtensions() {
        return null;
    }

    public String getLanguageName() {
        return null;
    }

    public String getLanguageVersion() {
        return null;
    }

    public String getMethodCallSyntax(String obj, String m, String[] args) {
        return null;
    }

    public List<String> getMimeTypes() {
        List<String> list = new ArrayList<String>();
        list.add("application/bad");
        list.add(null);
        list.add("");
        return list;
    }

    public List<String> getNames() {
        throw new IllegalArgumentException();
    }

    public String getOutputStatement(String str) {
        return "bad-factory-output";
    }

    public String getParameter(String key) {
        return null;
    }

    public String getProgram(String[] statements) {
        return null;
    }

    public ScriptEngine getScriptEngine() {
        return null;
    }
}
