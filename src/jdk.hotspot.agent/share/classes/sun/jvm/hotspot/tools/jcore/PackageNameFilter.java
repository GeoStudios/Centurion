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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.tools.jcore;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.*;
import java.util.*;

public class PackageNameFilter implements ClassFilter
{
    public String[] pkgList;

    public PackageNameFilter() {
        // give comma separated list of package names to include
        this(System.getProperty("sun.jvm.hotspot.tools.jcore.PackageNameFilter.pkgList"));
    }

    public PackageNameFilter(String pattern) {
        try {
            StringTokenizer st = new StringTokenizer(pattern, ",");
            List<String> l = new LinkedList<>();
            while (st.hasMoreTokens()) {
                l.add(st.nextToken());
            }
            pkgList = l.toArray(new String[0]);
        } catch (Exception exp) {
           exp.printStackTrace();
        }
    }

    public boolean canInclude(InstanceKlass kls) {
        if (pkgList == null) {
            // Dump everything
            return true;
        }
        final int len = pkgList.length;
        if (len == 0)
            return true;
        String klassName = kls.getName().asString().replace('/', '.');
        for (int i=0; i < len; i++)
            if (klassName.startsWith(pkgList[i] )) return true;
        return false;
    }
}
