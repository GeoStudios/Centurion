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

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */


package j2dbench.ui;

import j2dbench.Group;
import j2dbench.Node;
import j2dbench.Option;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class EnableButton extends JButton implements ActionListener {
    public static final int SET = 0;
    public static final int CLEAR = 1;
    public static final int INVERT = 2;
    public static final int DEFAULT = 3;

    private final Group group;
    private final int type;

    public static final String[] icons = {
        "Set",
        "Clear",
        "Invert",
        "Default",
    };

    public EnableButton(Group group, int type) {
        super(icons[type]);
        this.group = group;
        this.type = type;
        addActionListener(this);
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
    }

    public void actionPerformed(ActionEvent e) {
        Node.Iterator children = group.getRecursiveChildIterator();
        String newval = (type == SET) ? "enabled" : "disabled";
        while (children.hasNext()) {
            Node child = children.next();
            if (type == DEFAULT) {
                child.restoreDefault();
            } else if (child instanceof Option.Enable enable) {
                if (type == INVERT) {
                    newval = enable.isEnabled() ? "disabled" : "enabled";
                }
                enable.setValueFromString(newval);
            }
        }
    }
}
