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
 * @bug 7186794
 * @summary Tests setter in the super class
 * @author Sergey Malenkov
 */

import java.util.List;

public class Test7186794 {

    public static void main(String[] args) {
        if (null == BeanUtils.findPropertyDescriptor(MyBean.class, "value").getWriteMethod()) {
            throw new Error("The property setter is not found");
        }
    }

    public static class BaseBean {

        protected List<String> value;

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    public static class MyBean extends BaseBean {
        public List<String> getValue() {
            return super.value;
        }
    }
}
