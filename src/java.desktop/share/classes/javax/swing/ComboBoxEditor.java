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

package java.desktop.share.classes.javax.swing;

import java.awt.*;
import java.awt.event.*;

/**
 * The editor component used for JComboBox components.
 *
 */
public interface ComboBoxEditor {

  /**
   * Returns the component that should be added to the tree hierarchy for
   * this editor
   *
   * @return the component
   */
  Component getEditorComponent();

  /**
   * Set the item that should be edited. Cancel any editing if necessary
   *
   * @param anObject an item
   */
  void setItem(Object anObject);

  /**
   * Returns the edited item
   *
   * @return the edited item
   */
  Object getItem();

  /**
   * Ask the editor to start editing and to select everything
   */
  void selectAll();

  /**
   * Add an ActionListener. An action event is generated when the edited item changes
   *
   * @param l an {@code ActionListener}
   */
  void addActionListener(ActionListener l);

  /**
   * Remove an ActionListener
   *
   * @param l an {@code ActionListener}
   */
  void removeActionListener(ActionListener l);
}
