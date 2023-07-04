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


import jdk.test.lib.Platform;
import test.java.awt.regtesthelpers.Sysout;

import java.applet.Applet;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileDialogForDirectories extends Applet implements ActionListener {
    private volatile Button showBtn;
    private volatile FileDialog fd;

    @Override
    public void init() {
        if (!Platform.isOSX()) {
            Sysout.createDialogWithInstructions(new String[]{
                    "Press PASS, this test is for MacOS X only."});
            return;
        }

        System.setProperty("apple.awt.fileDialogForDirectories", "true");

        setLayout(new GridLayout(1, 1));

        fd = new FileDialog(new Frame(), "Open");

        showBtn = new Button("Show File Dialog");
        showBtn.addActionListener(this);
        add(showBtn);
        String[] instructions = {
                "1) Click on 'Show File Dialog' button. A file dialog will come up.",
                "2) Check that files can't be selected.",
                "3) Check that directories can be selected.",
                "4) Repeat steps 1 - 3 a few times for different files and directories.",
                "5) If it's true then the test passed, otherwise it failed."};
        Sysout.createDialogWithInstructions(instructions);
    }//End  init()

    @Override
    public void start() {
        setSize(200, 200);
        show();
    }// start()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showBtn) {
            fd.setVisible(true);
            String output = fd.getFile();
            if (output != null) {
                Sysout.println(output + " is selected");
            }
        }
    }
}// class ManualYesNoTest
