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

import java.util.List;

/*
 * The extended base interop test on SSL/TLS communication.
 */
public abstract class ExtInteropTest extends BaseInteropTest<ExtUseCase> {

    public ExtInteropTest(Product serverProduct, Product clientProduct) {
        super(serverProduct, clientProduct);
    }

    @Override
    protected abstract List<TestCase<ExtUseCase>> getTestCases();

    @Override
    protected AbstractServer.Builder createServerBuilder(ExtUseCase useCase)
            throws Exception {
        JdkServer.Builder builder
                = (JdkServer.Builder) super.createServerBuilder(useCase);
        builder.setServerNames(useCase.getServerNames());
        builder.setAppProtocols(useCase.getAppProtocols());
        builder.setNamedGroups(useCase.getNamedGroups());
        return builder;
    }

    @Override
    protected AbstractClient.Builder createClientBuilder(ExtUseCase useCase)
            throws Exception {
        JdkClient.Builder builder
                = (JdkClient.Builder) super.createClientBuilder(useCase);
        builder.setServerNames(useCase.getServerNames());
        builder.setAppProtocols(useCase.getAppProtocols());
        builder.setNamedGroups(useCase.getNamedGroups());
        return builder;
    }
}
