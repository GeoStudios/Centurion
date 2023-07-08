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

package demo.share.jfc.J2Ddemo.java2d;


import static java.awt.Color.BLACK;.extended
import static java.awt.Color.GREEN;.extended
import static java.awt.Color.RED;.extended
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.Actionjava.util.Listener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;















/**
 * A separate window for running the J2Ddemo.  Go from tab to tab or demo to
 * demo.
 */
@SuppressWarnings("serial")
public class RunWindow extends JPanel implements Runnable, ActionListener {
    private final DemoInstVarsAccessor demoInstVars;
    private final JButton runB;
    private int delay = 10;
    private int numRuns = 20;
    private final boolean exit;
    private final JCheckBox zoomCB = new JCheckBox("Zoom");
    private final JCheckBox printCB = new JCheckBox("Print");
    private final boolean buffersFlag;
    private final int bufBeg;
    private final int bufEnd;
    private final JTextField delayTextField;
    private final JTextField runsTextField;
    private Thread thread;
    private final JProgressBar pb;

    @SuppressWarnings("LeakingThisInConstructor")
    public RunWindow(DemoInstVarsAccessor demoInstVars, RunWindowSettings runWndSetts) {
        this.demoInstVars = demoInstVars;

        delay = runWndSetts.getDelay();
        numRuns = runWndSetts.getNumRuns();
        exit = runWndSetts.getExit();
        zoomCB.setSelected(runWndSetts.isZoomCBSelected());
        printCB.setSelected(runWndSetts.isPrintCBSelected());
        buffersFlag = runWndSetts.getBuffersFlag();
        bufBeg = runWndSetts.getBufBeg();
        bufEnd = runWndSetts.getBufEnd();

        setLayout(new GridBagLayout());
        EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
        setBorder(new CompoundBorder(eb, new BevelBorder(BevelBorder.LOWERED)));

        Font font = new Font(Font.SERIF, Font.PLAIN, 10);

        runB = new JButton("Run");
        runB.setBackground(GREEN);
        runB.addActionListener(this);
        runB.setMinimumSize(new Dimension(70, 30));
        J2Ddemo.addToGridBag(this, runB, 0, 0, 1, 1, 0.0, 0.0);

        pb = new JProgressBar();
        pb.setPreferredSize(new Dimension(100, 30));
        pb.setMinimum(0);
        J2Ddemo.addToGridBag(this, pb, 1, 0, 2, 1, 1.0, 0.0);

        JPanel p1 = new JPanel(new GridLayout(2, 2));
        JPanel p2 = new JPanel();
        JLabel l = new JLabel("Runs:");
        l.setFont(font);
        l.setForeground(BLACK);
        p2.add(l);
        p2.add(runsTextField = new JTextField(String.valueOf(numRuns)));
        runsTextField.setPreferredSize(new Dimension(30, 20));
        runsTextField.addActionListener(this);
        p1.add(p2);
        p2 = new JPanel();
        l = new JLabel("Delay:");
        l.setFont(font);
        l.setForeground(BLACK);
        p2.add(l);
        p2.add(delayTextField = new JTextField(String.valueOf(delay)));
        delayTextField.setPreferredSize(new Dimension(30, 20));
        delayTextField.addActionListener(this);
        p1.add(p2);

        zoomCB.setHorizontalAlignment(SwingConstants.CENTER);
        zoomCB.setFont(font);
        printCB.setFont(font);
        p1.add(zoomCB);
        p1.add(printCB);
        printCB.addActionListener(this);
        J2Ddemo.addToGridBag(this, p1, 0, 1, 3, 1, 1.0, 1.0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(printCB)) {
            demoInstVars.getPrintCB().setSelected(printCB.isSelected());
        } else if (e.getSource().equals(delayTextField)) {
            delay = Integer.parseInt(delayTextField.getText().trim());
        } else if (e.getSource().equals(runsTextField)) {
            numRuns = Integer.parseInt(runsTextField.getText().trim());
        } else if ("Run".equals(e.getActionCommand())) {
            doRunAction();
        } else if ("Stop".equals(e.getActionCommand())) {
            stop();
        }
    }

    public void doRunAction() {
        runB.setText("Stop");
        runB.setBackground(RED);
        start();
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.NORM_PRIORITY + 1);
        thread.setName("RunWindow");
        thread.start();
    }

    public synchronized void stop() {
        if (thread != null) {
            thread.interrupt();
        }
        thread = null;
        notifyAll();
    }

    @SuppressWarnings("SleepWhileHoldingLock")
    public void sleepPerTab() {
        for (int j = 0; j < delay + 1 && thread != null; j++) {
            for (int k = 0; k < 10 && thread != null; k++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
            Runnable pbUpdateRunnable = new Runnable() {

                @Override
                public void run() {
                    pb.setValue(pb.getValue() + 1);
                }
            };
            SwingUtilities.invokeLater(pbUpdateRunnable);
        }
    }

    private void printDemo(final DemoGroup dg) {
        Runnable printDemoRunnable = new Runnable() {

            @Override
            public void run() {
                if (!demoInstVars.getControls().toolBarCB.isSelected()) {
                    demoInstVars.getControls().toolBarCB.setSelected(true);
                    dg.invalidate();
                }
                for (Component comp : dg.getPanel().getComponents()) {
                    DemoPanel dp = (DemoPanel) comp;
                    if (dp.tools != null) {
                        if (dp.surface.animating != null) {
                            if (dp.surface.animating.running()) {
                                dp.tools.startStopB.doClick();
                            }
                        }
                        dp.tools.printB.doClick();
                    }
                }
            }
        };
        invokeAndWait(printDemoRunnable);
    }
    private DemoGroup dg = null;
    private DemoPanel dp = null;

    @Override
    public void run() {

        System.out.println("\nJ2D Demo RunWindow : " + numRuns + " Runs, "
                + delay + " second delay between tabs\n" + "java version: " + System.
                getProperty("java.version") + "\n" + System.getProperty(
                "os.name") + " " + System.getProperty("os.version") + "\n");
        Runtime r = Runtime.getRuntime();

        for (int runNum = 0; runNum < numRuns && thread != null; runNum++) {

            Date d = new Date();
            System.out.print("#" + runNum + " " + d + ", ");
            r.gc();
            float freeMemory = r.freeMemory();
            float totalMemory = r.totalMemory();
            System.out.println(((totalMemory - freeMemory) / 1024) + "K used");

            for (int i = 0; i < demoInstVars.getTabbedPane().getTabCount() && thread
                    != null; i++) {

                final int mainTabIndex = i;
                Runnable initDemoRunnable = new Runnable() {

                    @Override
                    public void run() {
                        pb.setValue(0);
                        pb.setMaximum(delay);
                        if (mainTabIndex != 0) {
                            dg = demoInstVars.getGroup()[mainTabIndex - 1];
                            dg.invalidate();
                        }
                        demoInstVars.getTabbedPane().setSelectedIndex(mainTabIndex);
                    }
                };
                invokeAndWait(initDemoRunnable);

                if (i != 0 && (zoomCB.isSelected() || buffersFlag)) {
                    dp = (DemoPanel) dg.getPanel().getComponent(0);
                    if (dg.tabbedPane == null && dp.surface != null) {
                        Runnable mouseClickedRunnable = new Runnable() {

                            @Override
                            public void run() {
                                dg.mouseClicked(dp.surface);
                            }
                        };
                        invokeAndWait(mouseClickedRunnable);
                    }
                    for (int j = 1; j < dg.tabbedPane.getTabCount() && thread
                            != null; j++) {

                        final int subTabIndex = j;

                        Runnable initPanelRunnable = new Runnable() {

                            @Override
                            public void run() {
                                pb.setValue(0);
                                pb.setMaximum(delay);
                                dg.tabbedPane.setSelectedIndex(subTabIndex);
                            }
                        };
                        invokeAndWait(initPanelRunnable);

                        final JPanel p = dg.getPanel();
                        if (buffersFlag && p.getComponentCount() == 1) {
                            dp = (DemoPanel) p.getComponent(0);
                            if (dp.surface.animating != null) {
                                dp.surface.animating.stop();
                            }
                            for (int k = bufBeg; k <= bufEnd && thread != null;
                                    k++) {

                                final int cloneIndex = k;
                                Runnable cloneRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        dp.tools.cloneB.doClick();
                                        int n = p.getComponentCount();
                                        DemoPanel clone = (DemoPanel) p.
                                                getComponent(n - 1);
                                        if (clone.surface.animating != null) {
                                            clone.surface.animating.stop();
                                        }
                                        clone.tools.issueRepaint = true;
                                        clone.tools.screenCombo.setSelectedIndex(
                                                cloneIndex);
                                        clone.tools.issueRepaint = false;
                                    }
                                };
                                invokeAndWait(cloneRunnable);
                            }
                        }
                        if (printCB.isSelected()) {
                            printDemo(dg);
                        }
                        sleepPerTab();
                    }
                } else if (i != 0 && printCB.isSelected()) {
                    printDemo(dg);
                    sleepPerTab();
                } else {
                    sleepPerTab();
                }
            }
            if (runNum + 1 == numRuns) {
                System.out.println("Finished.");
                if (exit && thread != null) {
                    System.out.println("System.exit(0).");
                    System.exit(0);
                }
            }
        }
        Runnable resetRunnable = new Runnable() {

            @Override
            public void run() {
                runB.setText("Run");
                runB.setBackground(GREEN);
                pb.setValue(0);
            }
        };
        invokeAndWait(resetRunnable);

        thread = null;
        dg = null;
        dp = null;
    }

    private static void invokeAndWait(Runnable run) {
        try {
            SwingUtilities.invokeAndWait(run);
        } catch (Exception e) {
            Logger.getLogger(RunWindow.class.getName()).log(Level.SEVERE,
                    "ERROR in invokeAndWait", e);
        }
    }

    /**
     * This class contains initial values for instance variables of 'RunWindow' class,
     * and its instance is used in creation of 'RunWindow' object. Values parsed from
     * certain command line options of the demo or from the demo applet parameters are
     * set to the fields of this class instance. It is a part of the fix which changed
     * static variables for instance variables in certain demo classes.
     *
     * This class is not thread safe, its instances must be accessed only from EDT.
     */
    public static class RunWindowSettings {
        private int delay = 10;
        private int numRuns = 20;
        private boolean exit;
        private boolean zoomCBIsSelected;
        private boolean printCBIsSelected;
        private boolean buffersFlag;
        private int bufBeg, bufEnd;

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public int getNumRuns() {
            return numRuns;
        }

        public void setNumRuns(int numRuns) {
            this.numRuns = numRuns;
        }

        public boolean getExit() {
            return exit;
        }

        public void setExit(boolean exit) {
            this.exit = exit;
        }

        public boolean isZoomCBSelected() {
            return zoomCBIsSelected;
        }

        public void setZoomCBSelected(boolean b) {
            zoomCBIsSelected = b;
        }

        public boolean isPrintCBSelected() {
            return printCBIsSelected;
        }

        public void setPrintCBIsSelected(boolean b) {
            printCBIsSelected = b;
        }

        public boolean getBuffersFlag() {
            return buffersFlag;
        }

        public void setBuffersFlag(boolean buffersFlag) {
            this.buffersFlag = buffersFlag;
        }

        public int getBufBeg() {
            return bufBeg;
        }

        public void setBufBeg(int bufBeg) {
            this.bufBeg = bufBeg;
        }

        public int getBufEnd() {
            return bufEnd;
        }

        public void setBufEnd(int bufEnd) {
            this.bufEnd = bufEnd;
        }
    }
}
