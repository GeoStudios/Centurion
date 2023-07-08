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
import javax.swing.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;















/** Shows VM version information */

public class VMVersionInfoPanel extends JPanel {
    private JEditorPane         versionPane;

    public VMVersionInfoPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        versionPane = new JEditorPane();
        versionPane.setContentType("text/html");
        versionPane.setEditable(false);
        versionPane.setText(getVersionInfo());

        add(versionPane, BorderLayout.CENTER);
    }

    private String getVersionInfo() {
       VM vm = VM.getVM();
       StringBuilder buf = new StringBuilder();
       buf.append("<html><head><title>VM Version Info</title></head>");
       buf.append("<body><table border='1'>");

       // VM type
       buf.append("<tr><td><b>VM Type</b></td>");
       buf.append("<td>");
       if (vm.isCore()) {
          buf.append("<b>core</b>");
       } else if(vm.isClientCompiler()) {
          buf.append("<b>client</b>");
       } else if(vm.isServerCompiler()) {
          buf.append("<b>server</b>");
       } else {
          buf.append("<b>unknown</b>");
       }
       buf.append("</td></tr>");

       // VM release
       String release = vm.getVMRelease();
       if (release != null) {
          buf.append("<tr><td><b>VM Release</td><td><b>");
          buf.append(release);
          buf.append("</b></td></tr>");
       }

       // VM internal info
       String internalInfo = vm.getVMInternalInfo();
       if (internalInfo != null) {
          buf.append("<tr><td><b>VM Internal Info</td><td><b>");
          buf.append(internalInfo);
          buf.append("</b></td></tr>");
       }

       buf.append("</table></body></html>");
       return buf.toString();
    }
}
