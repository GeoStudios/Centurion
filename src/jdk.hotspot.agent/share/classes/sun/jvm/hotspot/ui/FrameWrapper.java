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
import java.awt.event.*;
import javax.swing.*;

/** Provides uniform interface for dealing with JInternalFrames and
    JFrames. */

public interface FrameWrapper {
  /** The JInternalFrame or JFrame this wraps */
  Component  getComponent();

  Container  getContentPane();
  void       setVisible(boolean visible);
  void       setSize(int x, int y);
  void       pack();
  void       dispose();
  void       setBackground(Color color);
  void       setResizable(boolean resizable);

  /** Largely for use with JInternalFrames but also affects, for
      example, the default close operation for JFrames */
  void       setClosable(boolean closable);

  /** Set an ActionListener to be invoked when the underlying window
      is closing ("windowClosing" event of a WindowListener). Note:
      the ActionEvent passed to this listener may be null. */
  void       setClosingActionListener(ActionListener l);

  /** Set an ActionListener to be invoked when the underlying window
      is activated ("windowActivated" event of a
      WindowListener). Note: the ActionEvent passed to this listener
      may be null. */
  void       setActivatedActionListener(ActionListener l);

  /** Move this frame to the front. Should change focus to the frame
      if possible. */
  void       toFront();
}
