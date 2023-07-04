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

import java.lang.annotation.*;

public class TypeUseTargetNeg {}

// Case 1:
@Target({
    ElementType.TYPE_USE,
})
@Repeatable(FooContainer.class)
@interface Foo {}

@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.TYPE,
    ElementType.TYPE_USE,
    ElementType.TYPE_PARAMETER,
    ElementType.FIELD,

})
@interface FooContainer {
  Foo[] value();
}


// Case 2:
@Target({
    ElementType.TYPE_USE,
})
@Repeatable(BarContainer.class)
@interface Bar {}

@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.TYPE,
    ElementType.TYPE_USE,
    ElementType.METHOD,
})
@interface BarContainer {
  Bar[] value();
}


// Case 3:
@Target({
    ElementType.TYPE_USE,
})
@Repeatable(BazContainer.class)
@interface Baz {}

@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.TYPE,
    ElementType.PARAMETER,
})
@interface BazContainer {
  Baz[] value();
}


// Case 4:
@Target({
    ElementType.TYPE_USE,
})
@Repeatable(QuxContainer.class)
@interface Qux {}

@interface QuxContainer {
  Qux[] value();
}


// Case 5:
@Target({})
@Repeatable(QuuxContainer.class)
@interface Quux {}

@Target({
    ElementType.TYPE_PARAMETER,
})
@interface QuuxContainer {
  Quux[] value();
}

// Case 6:
@Repeatable(QuuuxContainer.class)
@interface Quuux {}

@Target({
    ElementType.TYPE_USE,
})
@interface QuuuxContainer {
  Quuux[] value();
}
