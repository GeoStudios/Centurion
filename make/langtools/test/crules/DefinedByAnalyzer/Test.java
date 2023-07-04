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

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.util.DefinedBy;
import com.sun.tools.javac.util.DefinedBy.Api;

public class Test implements SourcePositions, TaskListener {
    @Override @DefinedBy(Api.COMPILER_TREE)
    public long getStartPosition(CompilationUnitTree file, Tree tree) {
        return 0;
    }
    @Override
    public long getEndPosition(CompilationUnitTree file, Tree tree) {
        return 0;
    }
    @DefinedBy(Api.COMPILER_TREE)
    public long getEndPosition(Tree tree) {
        return 0;
    }
    @Override @DefinedBy(Api.LANGUAGE_MODEL)
    public void started(TaskEvent e) {
    }
    @Override @DefinedBy(Api.COMPILER_TREE)
    public void finished(TaskEvent e) {
    }
}
