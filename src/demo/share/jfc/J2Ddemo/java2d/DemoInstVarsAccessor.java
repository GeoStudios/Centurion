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
package java2d;

import java.awt.Color;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;

/**
 * The interface provides access to instance variables of 'J2Ddemo' object, which
 * were static variables of 'J2Ddemo' class read/written from various parts of the
 * demo classes. The interface is a part of the fix which changed static variables
 * for instance variables in certain demo classes.
 */
public interface DemoInstVarsAccessor {
    GlobalControls getControls();
    MemoryMonitor getMemoryMonitor();
    PerformanceMonitor getPerformanceMonitor();
    JTabbedPane getTabbedPane();
    DemoGroup[] getGroup();
    void setGroupColumns(int columns);
    JCheckBoxMenuItem getVerboseCB();
    JCheckBoxMenuItem getCcthreadCB();
    JCheckBoxMenuItem getPrintCB();
    Color getBackgroundColor();
    JCheckBoxMenuItem getMemoryCB();
    JCheckBoxMenuItem getPerfCB();
    Intro getIntro();
}
