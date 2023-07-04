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

import java.util.Comparator;

public class EqualsHashCodeWarningTest {
    @Override
    public boolean equals(Object o) {
        return o == this;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public Comparator m() {
        return new Comparator() {
            @Override
            public boolean equals(Object o) {return true;}

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }
}

class SubClass extends EqualsHashCodeWarningTest {
    @Override
    public boolean equals(Object o) {
        return true;
    }
}

@SuppressWarnings("overrides")
class DontWarnMe {
    @Override
    public boolean equals(Object o) {
        return true;
    }
}

class DoWarnMe {
    @Override
    public boolean equals(Object o) {
        return o == this;
    }
}

abstract class IamAbstractGetMeOutOfHere {
    public boolean equals(Object o){return true;}
}

interface I {
    public boolean equals(Object o);
}

enum E {
    A, B
}

@interface anno {}
