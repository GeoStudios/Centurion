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

package tests;


import java.util.Collection;
import java.util.Collections;














class CompileTest2 {

        class Request<R extends Request<R, V>,V> {}

        class DeltaRequest extends Request<DeltaRequest, double[]> {}

        class RequestMap<V> {
                public <R extends Request<R, W>, W extends V> R test (Collection<R> c) {
                        // In my real code I make use of W of course
                        return null;
                }

        }

        public void f () {
                RequestMap<Object> m = new RequestMap<Object> ();
                Collection<DeltaRequest> c = Collections.singleton (new DeltaRequest ());
                // This line not compile?
                DeltaRequest o = m.<DeltaRequest, double[]>test (c);
        }
}
