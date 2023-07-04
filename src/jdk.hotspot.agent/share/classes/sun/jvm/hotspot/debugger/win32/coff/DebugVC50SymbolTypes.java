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

package sun.jvm.hotspot.debugger.win32.coff;

public interface DebugVC50SymbolTypes {
  /** Compile flags symbol */
  int S_COMPILE    = 0x0001;

  /** Start search */
  int S_SSEARCH    = 0x0005;

  /** End block, procedure, with, or thunk */
  int S_END        = 0x0006;

  /** Reserve symbol space */
  int S_SKIP       = 0x0007;

  /** Reserved for CodeView internal use */
  int S_CVRESERVE  = 0x0008;

  /** Specify name of object file */
  int S_OBJNAME    = 0x0009;

  /** Specify end of arguments in function symbols */
  int S_ENDARG     = 0x000a;

  /** Microfocus COBOL user-defined type */
  int S_COBOLUDT   = 0x000b;

  /** Many register symbol */
  int S_MANYREG    = 0x000c;

  /** Function return description */
  int S_RETURN     = 0x000d;

  /** Description of this pointer at entry */
  int S_ENTRYTHIS  = 0x000e;

  /** Register variable */
  int S_REGISTER   = 0x1001;

  /** Constant symbol */
  int S_CONSTANT   = 0x1002;

  /** User-defined type */
  int S_UDT        = 0x1003;

  /** Microfocus COBOL User-defined type (#2) */
  int S_COBOLUDT2  = 0x1004;

  /** Many register symbol (#2) */
  int S_MANYREG2   = 0x1005;

  /** BP relative 16:32 */
  int S_BPREL32    = 0x1006;

  /** Local data 16:32 */
  int S_LDATA32    = 0x1007;

  /** Global data 16:32 */
  int S_GDATA32    = 0x1008;

  /** Public symbol 16:32 */
  int S_PUB32      = 0x1009;

  /** Local procedure start 16:32 */
  int S_LPROC32    = 0x100a;

  /** Global procedure start 16:32 */
  int S_GPROC32    = 0x100b;

  /** Thunk start 16:32 */
  int S_THUNK32    = 0x0206;

  /** Block start 16:32 */
  int S_BLOCK32    = 0x0207;

  /** With start 16:32 */
  int S_WITH32     = 0x0208;

  /** Label 16:32 */
  int S_LABEL32    = 0x0209;

  /** Change execution model 16:32 */
  int S_CEXMODEL32 = 0x020a;

  /** Virtual function table path descriptor 16:32 */
  int S_VFTTABLE32 = 0x100c;

  /** 16:32 offset relative to arbitrary register */
  int S_REGREL32   = 0x100d;

  /** Local Thread Storage data */
  int S_LTHREAD32  = 0x100e;

  /** Global Thread Storage data */
  int S_GTHREAD32  = 0x100f;

  /** Local procedure start MIPS */
  int S_LPROCMIPS  = 0x1010;

  /** Global procedure start MIPS */
  int S_GPROCMIPS  = 0x1011;

  /** Reference to a procedure */
  int S_PROCREF    = 0x0400;

  /** Reference to data */
  int S_DATAREF    = 0x0401;

  /** Page align symbols */
  int S_ALIGN      = 0x0402;
}
