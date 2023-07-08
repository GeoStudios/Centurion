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

package demo.share.jfc.SwingSet2;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;















/**
 */

public class DirectionPanel extends JPanel {

    private final ButtonGroup group;

    public DirectionPanel(boolean enable, String selection, ActionListener l) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(TOP_ALIGNMENT);
        setAlignmentX(LEFT_ALIGNMENT);

        Box firstThree = Box.createHorizontalBox();
        Box secondThree = Box.createHorizontalBox();
        Box thirdThree = Box.createHorizontalBox();

        if(!enable) {
            selection = "None";
        }

        group = new ButtonGroup();
        DirectionButton b;
        b = (DirectionButton) firstThree.add(new DirectionButton(  tl_dot, tldn_dot, "NW", "Sets the orientation to the North-West", l, group, selection.equals("NW")));
        b.setEnabled(enable);
        b = (DirectionButton) firstThree.add(new DirectionButton(  tm_dot, tmdn_dot, "N",  "Sets the orientation to the North", l, group, selection.equals("N")));
        b.setEnabled(enable);
        b = (DirectionButton) firstThree.add(new DirectionButton(  tr_dot, trdn_dot, "NE", "Sets the orientation to the North-East", l, group, selection.equals("NE")));
        b.setEnabled(enable);
        b = (DirectionButton) secondThree.add(new DirectionButton( ml_dot, mldn_dot, "W", "Sets the orientation to the West", l, group, selection.equals("W")));
        b.setEnabled(enable);
        b = (DirectionButton) secondThree.add(new DirectionButton( c_dot,  cdn_dot,  "C", "Sets the orientation to the Center", l, group, selection.equals("C")));
        b.setEnabled(enable);
        b = (DirectionButton) secondThree.add(new DirectionButton( mr_dot, mrdn_dot, "E", "Sets the orientation to the East", l, group, selection.equals("E")));
        b.setEnabled(enable);
        b = (DirectionButton) thirdThree.add(new DirectionButton(  bl_dot, bldn_dot, "SW", "Sets the orientation to the South-West", l, group, selection.equals("SW")));
        b.setEnabled(enable);
        b = (DirectionButton) thirdThree.add(new DirectionButton(  bm_dot, bmdn_dot, "S", "Sets the orientation to the South", l, group, selection.equals("S")));
        b.setEnabled(enable);
        b = (DirectionButton) thirdThree.add(new DirectionButton(  br_dot, brdn_dot, "SE", "Sets the orientation to the South-East", l, group, selection.equals("SE")));
        b.setEnabled(enable);

        add(firstThree);
        add(secondThree);
        add(thirdThree);
    }

    public String getSelection() {
        return group.getSelection().getActionCommand();
    }

    public void setSelection( String selection  ) {
        Enumeration<AbstractButton> e = group.getElements();
        while( e.hasMoreElements() ) {
            AbstractButton b = e.nextElement();
            if( b.getActionCommand().equals(selection) ) {
               b.setSelected(true);
            }
        }
    }

    // Chester's way cool layout buttons
    public ImageIcon bl_dot   = loadImageIcon("bl.gif","bottom left layout button");
    public ImageIcon bldn_dot = loadImageIcon("bldn.gif","selected bottom left layout button");
    public ImageIcon bm_dot   = loadImageIcon("bm.gif","bottom middle layout button");
    public ImageIcon bmdn_dot = loadImageIcon("bmdn.gif","selected bottom middle layout button");
    public ImageIcon br_dot   = loadImageIcon("br.gif","bottom right layout button");
    public ImageIcon brdn_dot = loadImageIcon("brdn.gif","selected bottom right layout button");
    public ImageIcon c_dot    = loadImageIcon("c.gif","center layout button");
    public ImageIcon cdn_dot  = loadImageIcon("cdn.gif","selected center layout button");
    public ImageIcon ml_dot   = loadImageIcon("ml.gif","middle left layout button");
    public ImageIcon mldn_dot = loadImageIcon("mldn.gif","selected middle left layout button");
    public ImageIcon mr_dot   = loadImageIcon("mr.gif","middle right layout button");
    public ImageIcon mrdn_dot = loadImageIcon("mrdn.gif","selected middle right layout button");
    public ImageIcon tl_dot   = loadImageIcon("tl.gif","top left layout button");
    public ImageIcon tldn_dot = loadImageIcon("tldn.gif","selected top left layout button");
    public ImageIcon tm_dot   = loadImageIcon("tm.gif","top middle layout button");
    public ImageIcon tmdn_dot = loadImageIcon("tmdn.gif","selected top middle layout button");
    public ImageIcon tr_dot   = loadImageIcon("tr.gif","top right layout button");
    public ImageIcon trdn_dot = loadImageIcon("trdn.gif","selected top right layout button");

    public ImageIcon loadImageIcon(String filename, String description) {
        String path = "/resources/images/buttons/" + filename;
        return new ImageIcon(getClass().getResource(path), description);
    }


    public class DirectionButton extends JRadioButton {

        /**
         * A layout direction button
         */
        public DirectionButton(Icon icon, Icon downIcon, String direction,
                               String description, ActionListener l,
                               ButtonGroup group, boolean selected)
        {
            super();
            this.addActionListener(l);
            setFocusPainted(false);
            setHorizontalTextPosition(CENTER);
            group.add(this);
            setIcon(icon);
            setSelectedIcon(downIcon);
            setActionCommand(direction);
            getAccessibleContext().setAccessibleName(direction);
            getAccessibleContext().setAccessibleDescription(description);
            setSelected(selected);
            setFocusable(false);
        }

        public void setBorder(Border b) {
        }
    }
}
