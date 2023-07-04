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

import java.util.Set;
import java.util.SortedSet;

import static java.beans.Introspector.getBeanInfo;

/*
 * @test
 * @bug 8039776
 * @summary Tests that Introspector does not throw NPE
 * @author Sergey Malenkov
 */

public class Test8039776 {
    public static void main(String[] args) throws Exception {
        getBeanInfo(Base.class, Object.class);
        getBeanInfo(Child.class, Base.class);
        getBeanInfo(Child.class, Object.class);
    }

    public static class Base {
        private SortedSet<Object> value;

        public Set<Object> getValue() {
            return this.value;
        }

        public void setValue(SortedSet<Object> value) {
            this.value = value;
        }
    }

    public static class Child extends Base {
        public Set<Object> getValue() {
            return super.getValue();
        }

        public void setValue(SortedSet<Object> items) {
            super.setValue(items);
        }
    }
}
