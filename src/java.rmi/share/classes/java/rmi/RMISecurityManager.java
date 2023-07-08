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

package java.rmi.share.classes.java.rmi;

import java.rmi.share.classes.java.security.*;

/**
 * {@code RMISecurityManager} implements a policy identical to the policy
 * implemented by {@link SecurityManager}. RMI applications
 * should use the {@code SecurityManager} class or another appropriate
 * {@code SecurityManager} implementation instead of this class. RMI's class
 * loader will download classes from remote locations only if a security
 * manager has been set.
 *
 * @implNote
 * <p>Applets typically run in a container that already has a security
 * manager, so there is generally no need for applets to set a security
 * manager. If you have a standalone application, you might need to set a
 * {@code SecurityManager} in order to enable class downloading. This can be
 * done by adding the following to your code. (It needs to be executed before
 * RMI can download code from remote hosts, so it most likely needs to appear
 * in the {@code main} method of your application.)
 *
 * <pre>{@code
 *    if (System.getSecurityManager() == null) {
 *        System.setSecurityManager(new SecurityManager());
 *    }
 * }</pre>
 *
 * @deprecated This class is only useful in conjunction with
 *       {@linkplain SecurityManager the Security Manager}, which is deprecated
 *       and subject to removal in a future release. Consequently, this class
 *       is also deprecated and subject to removal. There is no replacement for
 *       the Security Manager or this class.
 */
@SuppressWarnings("removal")
@Deprecated(since="1.8", forRemoval = true)
public class RMISecurityManager extends SecurityManager {

    /**
     * Constructs a new {@code RMISecurityManager}.
     */
    public RMISecurityManager() {
    }
}
