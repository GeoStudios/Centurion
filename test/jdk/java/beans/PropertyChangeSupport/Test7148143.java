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
 * @bug 7148143
 * @summary Tests ClassCastException for the PropertyChangeSupport class
 * @author Sergey Malenkov
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EventListener;
import java.util.EventListenerProxy;

public class Test7148143 {

    private static class CustomProxy
            extends EventListenerProxy<EventListener>
            implements PropertyChangeListener {

        public CustomProxy() {
            super(new EventListener() {
            });
        }

        public void propertyChange(PropertyChangeEvent event) {
        }
    }

    public static void main(String[] args) {
        PropertyChangeListener listener = new CustomProxy();
        PropertyChangeSupport support = new PropertyChangeSupport(listener);
        support.addPropertyChangeListener(listener);
        support.addPropertyChangeListener("foo", listener); // cast class exception
    }
}
