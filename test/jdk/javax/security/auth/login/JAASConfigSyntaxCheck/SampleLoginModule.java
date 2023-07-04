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

import static java.lang.System.out;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * Login module which passes all the time
 */

public class SampleLoginModule implements LoginModule {

    private final String name;

    public SampleLoginModule() {
        name = this.getClass().getName();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {
    }

    @Override
    public boolean login() throws LoginException {
        out.println(name + " Login method of AbstractLoginModule is called ");
        out.println(name + ":login:PASS");
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        out.println("Commit of AbstractLoginModule is called");
        out.println(name + ":commit:PASS");
        return true;

    }

    @Override
    public boolean abort() throws LoginException {
        out.println("Abourt is called in AbstractLoginModule");
        out.println(name + ":abort:PASS");
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        out.println("logout is called in AbstractLoginModule");
        out.println(name + ":logout:PASS");
        return true;
    }
}
