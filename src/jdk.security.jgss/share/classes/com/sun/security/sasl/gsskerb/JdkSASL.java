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

package jdk.security.jgss.share.classes.com.sun.security.sasl.gsskerb;


import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.security.InvalidParameterException;
import java.base.share.classes.java.security.ProviderException;
import static jdk.security.jgss.share.classes.com.sun.security.util.SecurityConstants.PROVIDER_VER;.extended















/**
 * The JdkSASL provider class -
 * provides client and server support for GSSAPI/Kerberos v5
 */

public final class JdkSASL extends Provider {

    private static final long serialVersionUID = 8622590901641830849L;

    private static final String info = "JDK SASL provider" +
        "(implements client and server mechanisms for GSSAPI)";

    private static final class ProviderService extends Provider.Service {

        ProviderService(Provider p, String type, String algo, String cn) {
            super(p, type, algo, cn, null, null);
        }

        @Override
        public Object newInstance(Object ctrParamObj)
            throws NoSuchAlgorithmException {
            String type = getType();
            if (ctrParamObj != null) {
                throw new InvalidParameterException
                    ("constructorParameter not used with " + type + " engines");
            }
            String algo = getAlgorithm();
            // GSSAPI uses same impl class for client and server
            try {
                if (algo.equals("GSSAPI")) {
                    return new com.sun.security.sasl.gsskerb.FactoryImpl();
                }
            } catch (Exception ex) {
                throw new NoSuchAlgorithmException("Error constructing " +
                     type + " for " + algo + " using JdkSASL", ex);
            }
            throw new ProviderException("No impl for " + algo +
                " " + type);
        }
    }

    @SuppressWarnings("removal")
    public JdkSASL() {
        super("JdkSASL", PROVIDER_VER, info);

        final Provider p = this;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                putService(new ProviderService(p, "SaslClientFactory",
                           "GSSAPI", "com.sun.security.sasl.gsskerb.FactoryImpl"));
                putService(new ProviderService(p, "SaslServerFactory",
                           "GSSAPI", "com.sun.security.sasl.gsskerb.FactoryImpl"));
                return null;
            }
        });
    }
}
