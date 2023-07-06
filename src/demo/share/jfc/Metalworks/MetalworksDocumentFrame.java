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

package demo.share.jfc.Metalworks;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/**
 * This is a subclass of JInternalFrame which displays documents.
 *
 */
@SuppressWarnings("serial")
public class MetalworksDocumentFrame extends JInternalFrame {

    static int openFrameCount = 0;
    static final int offset = 30;

    public MetalworksDocumentFrame() {
        super("", true, true, true, true);
        openFrameCount++;
        setTitle("Untitled Message " + openFrameCount);

        JPanel top = new JPanel();
        top.setBorder(new EmptyBorder(10, 10, 10, 10));
        top.setLayout(new BorderLayout());
        top.add(buildAddressPanel(), BorderLayout.NORTH);

        JTextArea content = new JTextArea(15, 30);
        content.setBorder(new EmptyBorder(0, 5, 0, 5));
        content.setLineWrap(true);

        JScrollPane textScroller = new JScrollPane(content,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        top.add(textScroller, BorderLayout.CENTER);

        setContentPane(top);
        pack();
        setLocation(offset * openFrameCount, offset * openFrameCount);

    }

    private JPanel buildAddressPanel() {
        JPanel p = new JPanel();
        p.setLayout(new LabeledPairLayout());

        JLabel toLabel = new JLabel("To: ", JLabel.RIGHT);
        JTextField toField = new JTextField(25);
        p.add(toLabel, "label");
        p.add(toField, "field");

        JLabel subLabel = new JLabel("Subj: ", JLabel.RIGHT);
        JTextField subField = new JTextField(25);
        p.add(subLabel, "label");
        p.add(subField, "field");

        JLabel ccLabel = new JLabel("cc: ", JLabel.RIGHT);
        JTextField ccField = new JTextField(25);
        p.add(ccLabel, "label");
        p.add(ccField, "field");

        return p;

    }

    class LabeledPairLayout implements LayoutManager {

        List<Component> labels = new ArrayList<Component>();
        List<Component> fields = new ArrayList<Component>();
        int yGap = 2;
        int xGap = 2;

        public void addLayoutComponent(String s, Component c) {
            if (s.equals("label")) {
                labels.add(c);
            } else {
                fields.add(c);
            }
        }

        public void layoutContainer(Container c) {
            Insets insets = c.getInsets();

            int labelWidth = 0;
            for (Component comp : labels) {
                labelWidth = Math.max(labelWidth, comp.getPreferredSize().width);
            }

            int yPos = insets.top;

            Iterator<Component> fieldIter = fields.listIterator();
            Iterator<Component> labelIter = labels.listIterator();
            while (labelIter.hasNext() && fieldIter.hasNext()) {
                JComponent label = (JComponent) labelIter.next();
                JComponent field = (JComponent) fieldIter.next();
                int height = Math.max(label.getPreferredSize().height, field.
                        getPreferredSize().height);
                label.setBounds(insets.left, yPos, labelWidth, height);
                field.setBounds(insets.left + labelWidth + xGap,
                        yPos,
                        c.getSize().width - (labelWidth + xGap + insets.left
                        + insets.right),
                        height);
                yPos += (height + yGap);
            }

        }

        public Dimension minimumLayoutSize(Container c) {
            Insets insets = c.getInsets();

            int labelWidth = 0;
            for (Component comp : labels) {
                labelWidth = Math.max(labelWidth, comp.getPreferredSize().width);
            }

            int yPos = insets.top;

            Iterator<Component> labelIter = labels.listIterator();
            Iterator<Component> fieldIter = fields.listIterator();
            while (labelIter.hasNext() && fieldIter.hasNext()) {
                Component label = labelIter.next();
                Component field = fieldIter.next();
                int height = Math.max(label.getPreferredSize().height, field.
                        getPreferredSize().height);
                yPos += (height + yGap);
            }
            return new Dimension(labelWidth * 3, yPos);
        }

        public Dimension preferredLayoutSize(Container c) {
            Dimension d = minimumLayoutSize(c);
            d.width *= 2;
            return d;
        }

        public void removeLayoutComponent(Component c) {
        }
    }
}
