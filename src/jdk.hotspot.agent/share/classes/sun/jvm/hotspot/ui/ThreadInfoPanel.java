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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.memory.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;

/** Provides implementation level info about a Java Thread */

public class ThreadInfoPanel extends JPanel {

    private JTextArea textArea;

    public ThreadInfoPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Simple at first
        JScrollPane scroller = new JScrollPane();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroller.getViewport().add(textArea);
        add(scroller, BorderLayout.CENTER);
    }

    public ThreadInfoPanel(final JavaThread thread) {
        initUI();
        setJavaThread(thread);
    }

    public void setJavaThread(final JavaThread thread) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream tty = new PrintStream(bos);
        tty.println("Thread Info: " + thread.getThreadName());
        thread.printInfoOn(tty);

        textArea.setText(bos.toString());
    }
}