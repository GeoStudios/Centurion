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

package demo.share.jfc.J2Ddemo.java2d;

import java.awt.Color;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;

/**
 * The implementation of 'DemoInstVarsAccessor' interface with empty methods.
 * It is used, when some parts of the demo are executed as standalone applications
 * not creating 'J2Ddemo' instances, for example in 'TextureChooser.main',
 * 'DemoGroup.main', 'Surface.createDemoFrame'.
 */
public class DemoInstVarsAccessorImplBase implements DemoInstVarsAccessor {
    private final JCheckBoxMenuItem printCB = new JCheckBoxMenuItem("Default Printer");

    @Override
    public GlobalControls getControls() {
        return null;
    }

    @Override
    public MemoryMonitor getMemoryMonitor() {
        return null;
    }

    @Override
    public PerformanceMonitor getPerformanceMonitor() {
        return null;
    }

    @Override
    public JTabbedPane getTabbedPane() {
        return null;
    }

    @Override
    public DemoGroup[] getGroup() {
        return null;
    }

    @Override
    public void setGroupColumns(int columns) {
    }

    @Override
    public JCheckBoxMenuItem getVerboseCB() {
        return null;
    }

    @Override
    public JCheckBoxMenuItem getCcthreadCB() {
        return null;
    }

    @Override
    public JCheckBoxMenuItem getPrintCB() {
        return printCB;
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }

    @Override
    public JCheckBoxMenuItem getMemoryCB() {
        return null;
    }

    @Override
    public JCheckBoxMenuItem getPerfCB() {
        return null;
    }

    @Override
    public Intro getIntro() {
        return null;
    }
}
