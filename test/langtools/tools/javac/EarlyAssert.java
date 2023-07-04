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
 * @bug 4402864
 * @summary Verify that assertions are enabled before the class is initialized
 * and not thereafter
 * @author gafter
 * @build EarlyAssert EarlyAssertWrapper
 * @run main EarlyAssertWrapper
 */

/*
 * Verify that assertions are enabled until the class is initialized
 */

class EASuper {
    static {
        EarlyAssert.foo();
    }
}

public class EarlyAssert extends EASuper {
    static public void foo() {
        boolean assertionStatus = false;
        assert assertionStatus = true;
        if (!assertionStatus) {
            throw new Error("Assertions are not enabled before initialization as they should be.");
        }
    }
    public static void main(String[] args) {
        boolean assertionStatus = false;
        assert assertionStatus = true;
        if (assertionStatus) {
            throw new Error("Assertions are not disabled after initialization as they should be.");
        }
    }
}
