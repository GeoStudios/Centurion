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

package bench.rmi;

import bench.Benchmark;
import java.rmi.server.RMIClassLoader;
import java.security.CodeSource;

/*
 *
 */

/**
 * Benchmark for testing speed of repeated loading of a class not found in
 * classpath.
 */
public class ClassLoading implements Benchmark {

    static final String ALTROOT = "!/bench/rmi/altroot/";
    static final String CLASSNAME = "Node";

    /**
     * Repeatedly load a class not found in classpath through RMIClassLoader.
     * Arguments: <# reps>
     */
    public long run(String[] args) throws Exception {
        int reps = Integer.parseInt(args[0]);
        CodeSource csrc = getClass().getProtectionDomain().getCodeSource();
        String url = "jar:" + csrc.getLocation().toString() + ALTROOT;

        long start = System.currentTimeMillis();
        for (int i = 0; i < reps; i++)
            RMIClassLoader.loadClass(url, CLASSNAME);
        long time = System.currentTimeMillis() - start;

        return time;
    }
}
