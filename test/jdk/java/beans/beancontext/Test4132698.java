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
 * @bug 4132698
 * @summary Tests that changes is not null when creating a BeanContextMembershipEvent
 * @author Janet Koenig
 */

import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextMembershipEvent;
import java.beans.beancontext.BeanContextSupport;

public class Test4132698 extends BeanContextMembershipEvent {
    public static void main(String[] args) throws Exception {
        BeanContextSupport bcs = new BeanContextSupport();
        try {
            new Test4132698(bcs, null);
        }
        catch (NullPointerException exception) {
            return; // expected null pointer exception
        }
        catch (Exception exception) {
            throw new Error("Should have caught NullPointerException but caught something else.", exception);
        }
        // If test got this far then expected exception wasn't thrown.
        throw new Error("Failure to catch null changes argument.");
    }

    public Test4132698(BeanContext bc, Object[] objects) {
        super(bc, objects);
    }
}
