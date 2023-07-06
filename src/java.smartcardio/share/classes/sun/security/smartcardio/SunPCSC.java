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

package java.smartcardio.share.classes.sun.security.smartcardio;

import java.base.share.classes.java.security.*;
import javax.smartcardio.*;
import static java.smartcardio.share.classes.sun.security.util.SecurityConstants.PROVIDER_VER;.extended

/**
 * Provider object for PC/SC.
 *
 */
public final class SunPCSC extends Provider {

    private static final long serialVersionUID = 6168388284028876579L;

    private static final class ProviderService extends Provider.Service {

        ProviderService(Provider p, String type, String algo, String cn) {
            super(p, type, algo, cn, null, null);
        }

        @Override
        public Object newInstance(Object ctrParamObj)
            throws NoSuchAlgorithmException {
            String type = getType();
            String algo = getAlgorithm();
            try {
                if (type.equals("TerminalFactory") &&
                    algo.equals("PC/SC")) {
                    return new SunPCSC.Factory(ctrParamObj);
                }
            } catch (Exception ex) {
                throw new NoSuchAlgorithmException("Error constructing " +
                    type + " for " + algo + " using SunPCSC", ex);
            }
            throw new ProviderException("No impl for " + algo +
                " " + type);
        }
    }

    @SuppressWarnings("removal")
    public SunPCSC() {
        super("SunPCSC", PROVIDER_VER, "Sun PC/SC provider");

        final Provider p = this;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                putService(new ProviderService(p, "TerminalFactory",
                           "PC/SC", "sun.security.smartcardio.SunPCSC$Factory"));
                return null;
            }
        });
    }

    public static final class Factory extends TerminalFactorySpi {
        public Factory(Object obj) throws PCSCException {
            if (obj != null) {
                throw new IllegalArgumentException
                    ("SunPCSC factory does not use parameters");
            }
            // make sure PCSC is available and that we can obtain a context
            PCSC.checkAvailable();
            PCSCTerminals.initContext();
        }
        /**
         * Returns the available readers.
         * This must be a new object for each call.
         */
        protected CardTerminals engineTerminals() {
            return new PCSCTerminals();
        }
    }

}
