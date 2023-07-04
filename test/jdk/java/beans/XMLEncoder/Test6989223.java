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
 * @bug 6989223
 * @summary Tests Rectangle2D.Double encoding
 * @run main/othervm -Djava.security.manager=allow Test6989223
 * @author Sergey Malenkov
 */

import java.awt.geom.Rectangle2D;

public class Test6989223 extends AbstractTest {
    public static void main(String[] args) {
        new Test6989223().test(true);
    }

    protected Object getObject() {
        return new Bean(1, 2, 3, 4);
    }

    @Override
    protected Object getAnotherObject() {
        return new Bean(1, 2, 3, 5);
    }

    public static class Bean extends Rectangle2D.Double {
        public Bean() {
        }

        public Bean(double x, double y, double w, double h) {
            super(x, y, w, h);
        }

        @Override
        public boolean equals(Object object) {
            return super.equals(object); // to avoid recursion during validation
        }
    }
}
