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

import java.security.Provider;
import java.security.Security;
import java.lang.Exception;

/*
 * @test
 * @bug 8030823 8130696 8196414
 * @run main/othervm ProviderVersionCheck
 * @summary Verify all providers in the default Providers list have the proper
 * version for the release
 * @author Anthony Scarpino
 */

public class ProviderVersionCheck {

    public static void main(String arg[]) throws Exception{

        boolean failure = false;

        for (Provider p: Security.getProviders()) {
            System.out.print(p.getName() + " ");
            String specVersion = System.getProperty("java.specification.version");
            if (p.getVersion() != Double.parseDouble(specVersion)) {
                System.out.println("failed. " + "Version received was " +
                        p.getVersion());
                failure = true;
            } else {
                System.out.println("passed.");
            }
        }

        if (failure) {
            throw new Exception("Provider(s) failed to have the expected " +
                    "version value.");
        }
    }

}
