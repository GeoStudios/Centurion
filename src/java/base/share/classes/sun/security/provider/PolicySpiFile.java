/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
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

    private PolicyFile pf;

    public PolicySpiFile(Policy.Parameters params) {

        if (params == null) {
            pf = new PolicyFile();
        } else {
            if (!(params instanceof URIParameter)) {
                throw new IllegalArgumentException
                        ("Unrecognized policy parameter: " + params);
            }
            URIParameter uriParam = (URIParameter)params;
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
