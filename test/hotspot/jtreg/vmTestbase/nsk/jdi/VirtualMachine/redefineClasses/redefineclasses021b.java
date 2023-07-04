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

package nsk.jdi.VirtualMachine.redefineClasses;

/**
 *  <code>redefineclasses021b</code> is deugee's part of the redefineclasses021.
 */

public class redefineclasses021b {

    redefineclasses021bc obj = new redefineclasses021bc();

    interface redefineclasses021bi {


        void dummyMethod01();

    }

    public interface redefineclasses021bir {


        void dummyMethod01();

    }

    class redefineclasses021bc implements redefineclasses021bi, redefineclasses021bir {

        public void dummyMethod01() {
        }

    }
}
