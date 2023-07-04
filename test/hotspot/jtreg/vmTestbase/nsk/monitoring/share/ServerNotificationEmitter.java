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

package nsk.monitoring.share;

import nsk.share.*;
import nsk.monitoring.share.*;
import javax.management.*;

/**
 * NotificationEmitter that delegates functionality to MBeanServer.
 */
public class ServerNotificationEmitter implements NotificationEmitter {
        private MBeanServer mbeanServer;
        private ObjectName name;

        public ServerNotificationEmitter(MBeanServer mbeanServer, ObjectName name) {
                this.mbeanServer = mbeanServer;
                this.name = name;
        }

        public ServerNotificationEmitter(MBeanServer mbeanServer, String name) {
                try {
                        this.mbeanServer = mbeanServer;
                        this.name = new ObjectName(name);
                } catch (MalformedObjectNameException e) {
                        throw Monitoring.convertException(e);
                }
        }

        public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)  {
                try {
                        mbeanServer.addNotificationListener(name, listener, filter, handback);
                } catch (InstanceNotFoundException e) {
                        throw Monitoring.convertException(e);
                }
        }

        public MBeanNotificationInfo[] getNotificationInfo() {
                throw new TestBug("ServerNotificationEmitter.getNotificationInfo() not implemented.");
        }

        public void removeNotificationListener(NotificationListener listener)
                throws ListenerNotFoundException {
                try {
                        mbeanServer.removeNotificationListener(name, listener);
                } catch (InstanceNotFoundException e) {
                        throw Monitoring.convertException(e);
                }
        }

        public void removeNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback)
                throws ListenerNotFoundException {
                try {
                        mbeanServer.removeNotificationListener(name, listener, filter, handback);
                } catch (InstanceNotFoundException e) {
                        throw Monitoring.convertException(e);
                }
        }
}
