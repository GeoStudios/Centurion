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

package jdk.hotspot.agent.share.classes.com.sun.java.swing.ui;

import jdk.hotspot.agent.share.classes.com.sun.java.awt.*;
import jdk.hotspot.agent.share.classes.com.sun.java.awt.event.*;
import javax.swing.*;

public class StatusBar extends JPanel
    implements ActionListener, MouseListener
{

    public StatusBar()
    {
        setLayout(new FlowLayout(0));
        setBorder(BorderFactory.createEtchedBorder());
        progressBar = new JProgressBar(0, 0, 100);
        progressBar.setPreferredSize(new Dimension(60, progressBar.getPreferredSize().height + 2));
        progressBar.setVisible(false);
        label = new JLabel("                                                                                        ");
        preferredSize = new Dimension(getWidth(label.getText()), 2 * getFontHeight());
        add(progressBar);
        add(label);
    }

    public static StatusBar getInstance()
    {
        if(statusBar == null)
            statusBar = new StatusBar();
        return statusBar;
    }

    public static void setInstance(StatusBar sb)
    {
        statusBar = sb;
    }

    protected int getWidth(String s)
    {
        FontMetrics fm = getFontMetrics(getFont());
        if(fm == null)
            return 0;
        else
            return fm.stringWidth(s);
    }

    protected int getFontHeight()
    {
        FontMetrics fm = getFontMetrics(getFont());
        if(fm == null)
            return 0;
        else
            return fm.getHeight();
    }

    public Dimension getPreferredSize()
    {
        return preferredSize;
    }

    public void setMessage(String message)
    {
        label.setText(message);
        label.repaint();
    }

    public void startBusyBar()
    {
        forward = true;
        if(timer == null)
        {
            setMessage("");
            progressBar.setVisible(true);
            timer = new Timer(15, this);
            timer.start();
        }
    }

    public void stopBusyBar()
    {
        if(timer != null)
        {
            timer.stop();
            timer = null;
        }
        setMessage("");
        progressBar.setVisible(false);
        progressBar.setValue(0);
    }

    public void actionPerformed(ActionEvent evt)
    {
        int value = progressBar.getValue();
        if(forward)
        {
            if(value < 100)
            {
                progressBar.setValue(value + 1);
            } else
            {
                forward = false;
                progressBar.setValue(value - 1);
            }
        } else
        if(value > 0)
        {
            progressBar.setValue(value - 1);
        } else
        {
            forward = true;
            progressBar.setValue(value + 1);
        }
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent evt)
    {
        setMessage("");
    }

    public void mouseEntered(MouseEvent evt)
    {
        if(evt.getSource() instanceof AbstractButton button)
        {
            Action action = button.getAction();
            if(action != null)
            {
                String message = (String)action.getValue("LongDescription");
                setMessage(message);
            }
        }
    }

    private static final int PROGRESS_MAX = 100;
    private static final int PROGRESS_MIN = 0;
    private final JLabel label;
    private final Dimension preferredSize;
    private final JProgressBar progressBar;
    private Timer timer;
    private boolean forward;
    private static StatusBar statusBar;

}
