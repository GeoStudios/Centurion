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
@Ann(@Override)
@Primitive(@Override)
@Str(@Override)
@En(@Override)
@ArrAnn(@Override)
@ArrPrimitive(@Override)
@ArrStr(@Override)
@ArrEn(@Override)
class AnnC { }

class PrimitiveC {
    private static final int C = 1;
    @Ann(C)
    @Primitive(C)
    @Str(C)
    @En(C)
    @ArrAnn(C)
    @ArrPrimitive(C)
    @ArrStr(C)
    @ArrEn(C)
    class I {
    }
}

class StringC {

    private static final String C = "";

    @Ann(C)
    @Primitive(C)
    @Str(C)
    @En(C)
    @ArrAnn(C)
    @ArrPrimitive(C)
    @ArrStr(C)
    @ArrEn(C)
    class I {
    }
}

@Ann(E.A)
@Primitive(E.A)
@Str(E.A)
@En(E.A)
@ArrAnn(E.A)
@ArrPrimitive(E.A)
@ArrStr(E.A)
@ArrEn(E.A)
class EnC { }

@Ann({@Override})
@Primitive({@Override})
@Str({@Override})
@En({@Override})
@ArrAnn({@Override})
@ArrPrimitive({@Override})
@ArrStr({@Override})
@ArrEn({@Override})
class ArrAnnC { }

class ArrPrimitiveC {
    private static final int C = 1;
    @Ann({C})
    @Primitive({C})
    @Str({C})
    @En({C})
    @ArrAnn({C})
    @ArrPrimitive({C})
    @ArrStr({C})
    @ArrEn({C})
    class I {
    }
}

class ArrStringC {
    private static final String C = "";
    @Ann({C})
    @Primitive({C})
    @Str({C})
    @En({C})
    @ArrAnn({C})
    @ArrPrimitive({C})
    @ArrStr({C})
    @ArrEn({C})
    class I {
    }
}

@Ann({E.A})
@Primitive({E.A})
@Str({E.A})
@En({E.A})
@ArrAnn({E.A})
@ArrPrimitive({E.A})
@ArrStr({E.A})
@ArrEn({E.A})
class ArrEnC { }

@interface Ann {
    Override value();
}

@interface Primitive {
    int value();
}

@interface Str {
    String value();
}

@interface En {
    E value();
}

@interface ArrAnn {
    Override[] value();
}

@interface ArrPrimitive {
    int[] value();
}

@interface ArrStr {
    String[] value();
}

@interface ArrEn {
    E[] value();
}

enum E {
    A;
}
