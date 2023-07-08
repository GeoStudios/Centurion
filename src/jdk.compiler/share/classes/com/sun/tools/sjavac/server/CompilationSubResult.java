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

package jdk.compiler.share.classes.com.sun.tools.sjavac.server;


import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import jdk.compiler.share.classes.com.sun.tools.javac.main.Main.Result;
import jdk.compiler.share.classes.com.sun.tools.sjavac.pubapi.PubApi;















/**
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class CompilationSubResult implements Serializable {

    static final long serialVersionUID = 46739181113L;

    public Result result;
    public Map<String, Set<URI>> packageArtifacts = new HashMap<>();
    public Map<String, Map<String, Set<String>>> packageDependencies = new HashMap<>();
    public Map<String, Map<String, Set<String>>> packageCpDependencies = new HashMap<>();
    public Map<String, PubApi> packagePubapis = new HashMap<>();
    public Map<String, PubApi> dependencyPubapis = new HashMap<>();
    public String stdout = "";
    public String stderr = "";

    public CompilationSubResult(Result result) {
        this.result = result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
