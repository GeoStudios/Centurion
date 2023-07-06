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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.basic;


import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.cdbg.*;















/** Provides notification about failed resolutions in the debug info
    database without causing the entire resolve operation to fail */
public interface ResolveListener {
  /** Indicates failure to resolve a type within another type */
  void resolveFailed(Type containingType, LazyType failedResolve, String detail);

  /** Indicates failure to resolve the address of a static field in a
      type */
  void resolveFailed(Type containingType, String staticFieldName);

  /** Indicates failure to resolve reference to a type from a symbol */
  void resolveFailed(Sym containingSymbol, LazyType failedResolve, String detail);

  /** Indicates failure to resolve reference from one symbol to
      another (currently occurs only from BlockSyms to other BlockSyms) */
  void resolveFailed(Sym containingSymbol, LazyBlockSym failedResolve, String detail);
}
