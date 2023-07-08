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

package java.base.share.classes.sun.security.provider;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PolicySpi;
import java.security.ProtectionDomain;
import java.security.URIParameter;
import java.net.MalformedURLException;

/**
 * This class wraps the PolicyFile subclass implementation of Policy
 * inside a PolicySpi implementation that is available from the SUN provider
 * via the Policy.getInstance calls.
 *
 */
@SuppressWarnings("removal")
public final class PolicySpiFile extends PolicySpi {

    private final PolicyFile pf;

    public PolicySpiFile(Policy.Parameters params) {

        if (params == null) {
            pf = new PolicyFile();
        } else {
            if (!(params instanceof URIParameter uriParam)) {
                throw new IllegalArgumentException
                        ("Unrecognized policy parameter: " + params);
            }
            try {
                pf = new PolicyFile(uriParam.getURI().toURL());
            } catch (MalformedURLException mue) {
                throw new IllegalArgumentException("Invalid URIParameter", mue);
            }
        }
    }

    protected PermissionCollection engineGetPermissions(CodeSource codesource) {
        return pf.getPermissions(codesource);
    }

    protected PermissionCollection engineGetPermissions(ProtectionDomain d) {
        return pf.getPermissions(d);
    }

    protected boolean engineImplies(ProtectionDomain d, Permission p) {
        return pf.implies(d, p);
    }

    protected void engineRefresh() {
        pf.refresh();
    }
}
