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

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.DomainCombiner;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import jdk.internal.access.SharedSecrets;

/*
 * @test
 * @bug 8064331
 * @summary Make sure that JavaSecurityAccess.doIntersectionPrivilege()
 *          is not dropping the information about the domain combiner of
 *          the stack ACC
 * @modules java.base/jdk.internal.access
 */

public class PreserveCombinerTest {
    public static void main(String[]args) throws Exception {
        final DomainCombiner dc = new DomainCombiner() {
            @Override
            public ProtectionDomain[] combine(ProtectionDomain[] currentDomains, ProtectionDomain[] assignedDomains) {
                return currentDomains; // basically a no-op
            }
        };

        // Get an instance of the saved ACC
        AccessControlContext saved = AccessController.getContext();
        // Simulate the stack ACC with a DomainCombiner attached
        AccessControlContext stack = new AccessControlContext(AccessController.getContext(), dc);

        // Now try to run JavaSecurityAccess.doIntersectionPrivilege() and assert
        // whether the DomainCombiner from the stack ACC is preserved
        boolean ret = SharedSecrets.getJavaSecurityAccess().doIntersectionPrivilege(new PrivilegedAction<Boolean>() {
            @Override
            public Boolean run() {
                return dc == AccessController.getContext().getDomainCombiner();
            }
        }, stack, saved);

        if (!ret) {
            System.exit(1);
        }
    }
}

