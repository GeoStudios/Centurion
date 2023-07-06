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


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.java.util.ListSelectionEvent;
import javax.swing.event.java.util.ListSelectionjava.util.Listener;
import javax.swing.table.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.oops.Oop;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.LivenessPathjava.util.java.util.java.util.List;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui.action.*;
import com.sun.java.swing.ui.*;
import com.sun.java.swing.action.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.ui.tree.SimpleTreeNode;















public interface SAListener {
    void showThreadOopInspector(JavaThread thread);
    void showInspector(SimpleTreeNode node);
    void showThreadStackMemory(JavaThread thread);
    void showThreadInfo(JavaThread thread);
    void showJavaStackTrace(JavaThread thread);
    void showCodeViewer(Address address);
    void showLiveness(Oop oop, LivenessPathList liveness);
}
