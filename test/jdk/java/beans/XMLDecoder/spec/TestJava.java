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
 * @summary Tests <java> element
 * @run main/othervm -Djava.security.manager=allow TestJava
 * @author Sergey Malenkov
 */

import java.beans.XMLDecoder;

public final class TestJava extends AbstractTest {
    public static final String XML
            = "<java>\n"
            + " <void id=\"owner\" method=\"getOwner\">\n"
            + "  <void method=\"init\">\n"
            + "   <string>Hello, world!</string>\n"
            + "  </void>\n"
            + " </void>\n"
            + " <object idref=\"owner\"/>\n"
            + "</java>";

    public static void main(String[] args) {
        new TestJava().test(true);
    }

    private String message;

    public void init(String message) {
        this.message = message;
    }

    @Override
    protected void validate(XMLDecoder decoder) {
        decoder.setOwner(this);
        if (this != decoder.readObject()) {
            throw new Error("owner should be the same");
        }
        if (this.message == null) {
            throw new Error("owner's method is not called");
        }
    }
}
