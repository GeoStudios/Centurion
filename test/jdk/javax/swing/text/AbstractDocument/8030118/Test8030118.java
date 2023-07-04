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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
 * @test
 * @bug 8030118
 * @summary Tests that AbstractDocument cannot be modified from another thread
 * @author Sergey Malenkov
 */

public class Test8030118 implements DocumentListener, Runnable {
    private final CountDownLatch latch = new CountDownLatch(1);
    private final PlainDocument doc = new PlainDocument();

    private Test8030118(String string) throws Exception {
        this.doc.addDocumentListener(this);
        this.doc.insertString(0, string, null);
    }

    @Override
    public void run() {
        try {
            this.doc.remove(0, this.doc.getLength());
        } catch (BadLocationException exception) {
            throw new Error("unexpected", exception);
        }
        this.latch.countDown();
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        new Thread(this).start();
        try {
            this.latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            throw new Error("unexpected", exception);
        }
        try {
            event.getDocument().getText(event.getOffset(), event.getLength());
        } catch (BadLocationException exception) {
            throw new Error("concurrent modification", exception);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
    }

    public static void main(String[] args) throws Exception {
        new Test8030118("string");
    }
}
