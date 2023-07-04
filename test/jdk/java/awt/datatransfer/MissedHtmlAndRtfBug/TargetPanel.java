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

import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TargetPanel extends Panel implements DropTargetListener {


    //private final CustomDropTargetListener dropTargetListener = new CustomDropTargetListener();

    private Frame frame;
    DataFlavor dataFlavor;

    public TargetPanel(Frame frame, DataFlavor dataFlavor) {
        this.dataFlavor = dataFlavor;
        this.frame = frame;
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(200, 200));
        setDropTarget(new DropTarget(this, this));
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(dataFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        }
    }

    public void dragOver(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(dataFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        }
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(dataFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        }
    }

    public void dragExit(DropTargetEvent dte) {

    }

    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        if (dtde.isDataFlavorSupported(dataFlavor)) {
            String result = null;
            try {
                Transferable t = dtde.getTransferable();
                byte[] data = (byte[]) dtde.getTransferable().getTransferData(dataFlavor);
                result = new String(data, "UTF-16");
                repaint();
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dtde.dropComplete(true);


            if (result != null && result.contains(MyTransferable.TEST_DATA)) {
                System.err.println(InterprocessMessages.EXECUTION_IS_SUCCESSFULL);
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 2000);
                return;

            }
        }
        dtde.rejectDrop();
        System.err.println(InterprocessMessages.DATA_IS_CORRUPTED);
        System.exit(InterprocessMessages.DATA_IS_CORRUPTED);
    }

}
