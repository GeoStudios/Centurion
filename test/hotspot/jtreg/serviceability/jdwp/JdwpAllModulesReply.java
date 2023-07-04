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

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The JDWP reply to the ALLMODULES command
 */
public class JdwpAllModulesReply extends JdwpReply {

    private int modulesCount;
    private long[] modulesId;

    protected void parseData(DataInputStream ds) throws IOException {
        modulesCount = ds.readInt();
        modulesId = new long[modulesCount];
        for (int nmod = 0; nmod < modulesCount; ++nmod) {
            modulesId[nmod] = readRefId(ds);
        }
    }

    /**
     * Number of modules reported
     *
     * @return modules count
     */
    public int getModulesCount() {
        return modulesCount;
    }

    /**
     * The id of a module reported
     *
     * @param ndx module index in the array of the reported ids
     * @return module id
     */
    public long getModuleId(int ndx) {
        return modulesId[ndx];
    }
}
