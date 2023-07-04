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
 * @bug 6244855 6244863
 * @summary Exception thrown by NotificationFilter should be ignored
 * @author Shanliang JIANG
 *
 * @run clean FilterExceptionTest
 * @run build FilterExceptionTest
 * @run main FilterExceptionTest
 */

import javax.management.*;

public class FilterExceptionTest {
    public static Exception listenerException;

    public static void main(String[] args) throws Exception {
        System.out.println(
         ">>> FilterExceptionTest-main: test on an exception thrown by NotificationFilter.");

        FilterExceptionTest.listenerException = null;

        NotificationFilter filter = new NotificationFilter() {
                public boolean isNotificationEnabled(Notification notification) {

                    System.out.println(">>> FilterExceptionTest-filter: throws exception.");

                    throw new RuntimeException("For test");
                }
            };

        NotificationListener listener = new NotificationListener() {
                public void handleNotification(Notification n, Object hb) {
                    FilterExceptionTest.listenerException =
                        new Exception("The listener received unexpected notif.");
                }
            };

        NotificationBroadcasterSupport broadcaster = new NotificationBroadcasterSupport();
        broadcaster.addNotificationListener(listener, filter, null);

        broadcaster.sendNotification(new Notification("", "", 1L));

        if (FilterExceptionTest.listenerException != null) {
            throw FilterExceptionTest.listenerException;
        }

        System.out.println(">>> FilterExceptionTest-main: Done.");
    }
}
