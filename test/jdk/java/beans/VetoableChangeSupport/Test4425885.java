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
 * @test
 * @bug 4425885
 * @summary Tests VetoableChangeListener notification
 * @author Sergey Malenkov
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class Test4425885 {
    private static final String PROPERTY = "property"; // NON-NLS: predefined property name

    public static void main(String[] args) {
        CheckListener first = new CheckListener();
        VetoListener second = new VetoListener();
        CheckListener third = new CheckListener();

        VetoableChangeSupport vcs = new VetoableChangeSupport(Test4425885.class);
        vcs.addVetoableChangeListener(PROPERTY, first);
        vcs.addVetoableChangeListener(PROPERTY, second);
        vcs.addVetoableChangeListener(PROPERTY, third);
        try {
            vcs.fireVetoableChange(PROPERTY, 0, 1);
        } catch (PropertyVetoException exception) {
            if (first.odd)
                throw new Error("no undo for the first listener", exception);

            if (third.odd)
                throw new Error("no undo for the third listener", exception);

            return; // expected exception
        }
        throw new Error("exception should be thrown");
    }

    private static class CheckListener implements VetoableChangeListener {
        private boolean odd; // even/odd check for notification

        public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
            this.odd = !this.odd;
        }
    }

    private static class VetoListener implements VetoableChangeListener {
        public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
            throw new PropertyVetoException("disable all changes", event);
        }
    }
}
