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
 * @bug 8214583
 * @summary Check that getContext works after JIT compiler escape analysis.
 */
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.DomainCombiner;
import java.security.ProtectionDomain;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DoPriv {

    static void go(final DomainCombiner dc0, final AccessControlContext co, final int index) throws Exception {
        final AccessControlContext ci = new AccessControlContext(co, dc0);
        AccessController.doPrivileged((PrivilegedExceptionAction<Integer>)() -> {
            AccessControlContext c1 = AccessController.getContext();
            DomainCombiner dc = c1.getDomainCombiner();
            if (dc != dc0 || dc == null) {
                throw new AssertionError("iteration " + index + " " + dc + " != " + dc0);
            }
            return 0;
        }, ci);
    }

    public static void main(String[] args) throws Exception {
        final DomainCombiner dc0 = new DomainCombiner() {
            public ProtectionDomain[] combine(ProtectionDomain[] currentDomains,
                                            ProtectionDomain[] assignedDomains) {
                return null;
            }
        };

        final AccessControlContext co = AccessController.getContext();

        for (int i = 0; i < 500_000; ++i) {
            go(dc0, co, i);
        }
    }
}
