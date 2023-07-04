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
// combinations of methods defined in an interface
// and overridden in subtypes

// class should compile with deprecation warnings as shown

abstract class B2 extends A implements I {
    @Deprecated public void iDep_aDep_bDep() { }
                public void iDep_aDep_bUnd() { } // warn x 2, because of I and A
    //          public void iDep_aDep_bInh() { }
    @Deprecated public void iDep_aUnd_bDep() { }
                public void iDep_aUnd_bUnd() { } // warn
    //          public void iDep_aUnd_bInh() { } // warn
    @Deprecated public void iDep_aInh_bDep() { }
                public void iDep_aInh_bUnd() { } // warn
    //          public void iDep_aInh_bInh() { }
    @Deprecated public void iUnd_aDep_bDep() { }
                public void iUnd_aDep_bUnd() { } // warn
    //          public void iUnd_aDep_bInh() { }
    @Deprecated public void iUnd_aUnd_bDep() { }
                public void iUnd_aUnd_bUnd() { }
    //          public void iUnd_aUnd_bInh() { }
    @Deprecated public void iUnd_aInh_bDep() { }
                public void iUnd_aInh_bUnd() { }
    //          public void iUnd_aInh_bInh() { }
}
