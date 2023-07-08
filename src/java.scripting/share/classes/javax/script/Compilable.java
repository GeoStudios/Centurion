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

package java.scripting.share.classes.javax.script;


import java.util.Map;
import java.io.Reader;















/**
 * The optional interface implemented by ScriptEngines whose methods compile scripts
 * to a form that can be executed repeatedly without recompilation.
 *
 */
public interface Compilable {
    /**
     * Compiles the script (source represented as a <code>String</code>) for
     * later execution.
     *
     * @param script The source of the script, represented as a <code>String</code>.
     *
     * @return An instance of a subclass of <code>CompiledScript</code> to be executed later using one
     * of the <code>eval</code> methods of <code>CompiledScript</code>.
     *
     * @throws ScriptException if compilation fails.
     * @throws NullPointerException if the argument is null.
     *
     */

    CompiledScript compile(String script) throws
            ScriptException;

    /**
     * Compiles the script (source read from <code>Reader</code>) for
     * later execution.  Functionality is identical to
     * <code>compile(String)</code> other than the way in which the source is
     * passed.
     *
     * @param script The reader from which the script source is obtained.
     *
     * @return An instance of a subclass of <code>CompiledScript</code> to be executed
     * later using one of its <code>eval</code> methods of <code>CompiledScript</code>.
     *
     * @throws ScriptException if compilation fails.
     * @throws NullPointerException if argument is null.
     */
    CompiledScript compile(Reader script) throws
            ScriptException;
}
