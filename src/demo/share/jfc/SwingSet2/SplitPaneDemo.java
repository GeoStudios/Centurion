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


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

/**
 * Split Pane demo
 *
 */
public class SplitPaneDemo extends DemoModule {

    JSplitPane splitPane = null;
    JLabel earth = null;
    JLabel moon = null;

    JTextField divSize;
    JTextField earthSize;
    JTextField moonSize;

    /**
     * main method allows us to run as a standalone demo.
     */
    public static void main(String[] args) {
        SplitPaneDemo demo = new SplitPaneDemo(null);
        demo.mainImpl();
    }

    /**
     * SplitPaneDemo Constructor
     */
    public SplitPaneDemo(SwingSet2 swingset) {
        super(swingset, "SplitPaneDemo", "toolbar/JSplitPane.gif");

        earth = new JLabel(createImageIcon("splitpane/earth.jpg", getString("SplitPaneDemo.earth")));
        earth.setMinimumSize(new Dimension(20, 20));

        moon = new JLabel(createImageIcon("splitpane/moon.jpg", getString("SplitPaneDemo.moon")));
        moon.setMinimumSize(new Dimension(20, 20));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, earth, moon);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        splitPane.setDividerLocation(200);

        getDemoPanel().add(splitPane, BorderLayout.CENTER);
        getDemoPanel().setBackground(Color.black);

        getDemoPanel().add(createSplitPaneControls(), BorderLayout.SOUTH);
    }

    /**
     * Creates controls to alter the JSplitPane.
     */
    protected JPanel createSplitPaneControls() {
        JPanel wrapper = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JRadioButton button;

        Box buttonWrapper = new Box(BoxLayout.X_AXIS);

        wrapper.setLayout(new GridLayout(0, 1));

        /* Create a radio button to vertically split the split pane. */
        button = new JRadioButton(getString("SplitPaneDemo.vert_split"));
        button.setMnemonic(getMnemonic("SplitPaneDemo.vert_split_mnemonic"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            }
        });
        group.add(button);
        buttonWrapper.add(button);

        /* Create a radio button the horizontally split the split pane. */
        button = new JRadioButton(getString("SplitPaneDemo.horz_split"));
        button.setMnemonic(getMnemonic("SplitPaneDemo.horz_split_mnemonic"));
        button.setSelected(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            }
        });
        group.add(button);
        buttonWrapper.add(button);

        /* Create a check box as to whether or not the split pane continually
           lays out the component when dragging. */
        JCheckBox checkBox = new JCheckBox(getString("SplitPaneDemo.cont_layout"));
        checkBox.setMnemonic(getMnemonic("SplitPaneDemo.cont_layout_mnemonic"));
        checkBox.setSelected(true);

        checkBox.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                splitPane.setContinuousLayout(
                    ((JCheckBox)e.getSource()).isSelected());
            }
        });
        buttonWrapper.add(checkBox);

        /* Create a check box as to whether or not the split pane divider
           contains the oneTouchExpandable buttons. */
        checkBox = new JCheckBox(getString("SplitPaneDemo.one_touch_expandable"));
        checkBox.setMnemonic(getMnemonic("SplitPaneDemo.one_touch_expandable_mnemonic"));
        checkBox.setSelected(true);

        checkBox.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                splitPane.setOneTouchExpandable(
                    ((JCheckBox) e.getSource()).isSelected());
            }
        });
        buttonWrapper.add(checkBox);
        wrapper.add(buttonWrapper);

        /* Create a text field to change the divider size. */
        JPanel                   tfWrapper;
        JLabel                   label;

        divSize = new JTextField();
        divSize.setText(Integer.toString(splitPane.getDividerSize()));
        divSize.setColumns(5);
        divSize.getAccessibleContext().setAccessibleName(getString("SplitPaneDemo.divider_size"));
        divSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  value = ((JTextField)e.getSource()).getText();
                int newSize;

                try {
                    newSize = Integer.parseInt(value);
                } catch (Exception ex) {
                    newSize = -1;
                }
                if(newSize > 0) {
                    splitPane.setDividerSize(newSize);
                } else {
                    JOptionPane.showMessageDialog(splitPane,
                                                  getString("SplitPaneDemo.invalid_divider_size"),
                                                  getString("SplitPaneDemo.error"),
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        label = new JLabel(getString("SplitPaneDemo.divider_size"));
        tfWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfWrapper.add(label);
        tfWrapper.add(divSize);
        label.setLabelFor(divSize);
        label.setDisplayedMnemonic(getMnemonic("SplitPaneDemo.divider_size_mnemonic"));
        wrapper.add(tfWrapper);

        /* Create a text field that will change the preferred/minimum size
           of the earth component. */
        earthSize = new JTextField(String.valueOf(earth.getMinimumSize().width));
        earthSize.setColumns(5);
        earthSize.getAccessibleContext().setAccessibleName(getString("SplitPaneDemo.first_component_min_size"));
        earthSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String           value = ((JTextField)e.getSource()).getText();
                int              newSize;

                try {
                    newSize = Integer.parseInt(value);
                } catch (Exception ex) {
                    newSize = -1;
                }
                if(newSize > 10) {
                    earth.setMinimumSize(new Dimension(newSize, newSize));
                } else {
                    JOptionPane.showMessageDialog(splitPane,
                                                  getString("SplitPaneDemo.invalid_min_size") +
                                                  getString("SplitPaneDemo.must_be_greater_than") + 10,
                                                  getString("SplitPaneDemo.error"),
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        label = new JLabel(getString("SplitPaneDemo.first_component_min_size"));
        tfWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfWrapper.add(label);
        tfWrapper.add(earthSize);
        label.setLabelFor(earthSize);
        label.setDisplayedMnemonic(getMnemonic("SplitPaneDemo.first_component_min_size_mnemonic"));
        wrapper.add(tfWrapper);

        /* Create a text field that will change the preferred/minimum size
           of the moon component. */
        moonSize = new JTextField(String.valueOf(moon.getMinimumSize().width));
        moonSize.setColumns(5);
        moonSize.getAccessibleContext().setAccessibleName(getString("SplitPaneDemo.second_component_min_size"));
        moonSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String           value = ((JTextField)e.getSource()).getText();
                int              newSize;

                try {
                    newSize = Integer.parseInt(value);
                } catch (Exception ex) {
                    newSize = -1;
                }
                if(newSize > 10) {
                    moon.setMinimumSize(new Dimension(newSize, newSize));
                } else {
                    JOptionPane.showMessageDialog(splitPane,
                                                  getString("SplitPaneDemo.invalid_min_size") +
                                                  getString("SplitPaneDemo.must_be_greater_than") + 10,
                                                  getString("SplitPaneDemo.error"),
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        label = new JLabel(getString("SplitPaneDemo.second_component_min_size"));
        tfWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfWrapper.add(label);
        tfWrapper.add(moonSize);
        label.setLabelFor(moonSize);
        label.setDisplayedMnemonic(getMnemonic("SplitPaneDemo.second_component_min_size_mnemonic"));
        wrapper.add(tfWrapper);

        return wrapper;
    }

    void updateDragEnabled(boolean dragEnabled) {
        divSize.setDragEnabled(dragEnabled);
        earthSize.setDragEnabled(dragEnabled);
        moonSize.setDragEnabled(dragEnabled);
    }

}
