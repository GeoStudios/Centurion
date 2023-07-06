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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.win32.coff;

















public interface DebugVC50TypeLeafIndices {

  //
  // Leaf indices for type records that can be referenced from symbols:
  //

  int LF_MODIFIER   = 0x1001;
  int LF_POINTER    = 0x1002;
  int LF_ARRAY      = 0x1003;
  int LF_CLASS      = 0x1004;
  int LF_STRUCTURE  = 0x1005;
  int LF_UNION      = 0x1006;
  int LF_ENUM       = 0x1007;
  int LF_PROCEDURE  = 0x1008;
  int LF_MFUNCTION  = 0x1009;
  int LF_VTSHAPE    = 0x000a;
  int LF_COBOL0     = 0x100a;
  int LF_COBOL1     = 0x000c;
  int LF_BARRAY     = 0x100b;
  int LF_LABEL      = 0x000e;
  int LF_NULL       = 0x000f;
  int LF_NOTTRAN    = 0x0010;
  int LF_DIMARRAY   = 0x100c;
  int LF_VFTPATH    = 0x100d;
  int LF_PRECOMP    = 0x100e;
  int LF_ENDPRECOMP = 0x0014;
  int LF_OEM        = 0x100f;
  int LF_TYPESERVER = 0x0016;

  //
  // Leaf indices for type records that can be referenced from other type records:
  //

  int LF_SKIP       = 0x1200;
  int LF_ARGLIST    = 0x1201;
  int LF_DEFARG     = 0x1202;
  int LF_FIELDLIST  = 0x1203;
  int LF_DERIVED    = 0x1204;
  int LF_BITFIELD   = 0x1205;
  int LF_METHODLIST = 0x1206;
  int LF_DIMCONU    = 0x1207;
  int LF_DIMCONLU   = 0x1208;
  int LF_DIMVARU    = 0x1209;
  int LF_DIMVARLU   = 0x120a;
  int LF_REFSYM     = 0x020c;

  //
  // Leaf indices for fields of complex lists:
  //

  int LF_BCLASS       = 0x1400;
  int LF_VBCLASS      = 0x1401;
  int LF_IVBCLASS     = 0x1402;
  int LF_ENUMERATE    = 0x0403;
  int LF_FRIENDFCN    = 0x1403;
  int LF_INDEX        = 0x1404;
  int LF_MEMBER       = 0x1405;
  int LF_STMEMBER     = 0x1406;
  int LF_METHOD       = 0x1407;
  int LF_NESTTYPE     = 0x1408;
  int LF_VFUNCTAB     = 0x1409;
  int LF_FRIENDCLS    = 0x140a;
  int LF_ONEMETHOD    = 0x140b;
  int LF_VFUNCOFF     = 0x140c;
  int LF_NESTTYPEEX   = 0x140d;
  int LF_MEMBERMODIFY = 0x140e;

  //
  // Leaf indices for numeric fields of symbols and type records:
  //

  int LF_NUMERIC    = 0x8000;
  int LF_CHAR       = 0x8000;
  int LF_SHORT      = 0x8001;
  int LF_USHORT     = 0x8002;
  int LF_LONG       = 0x8003;
  int LF_ULONG      = 0x8004;
  int LF_REAL32     = 0x8005;
  int LF_REAL64     = 0x8006;
  int LF_REAL80     = 0x8007;
  int LF_REAL128    = 0x8008;
  int LF_QUADWORD   = 0x8009;
  int LF_UQUADWORD  = 0x800a;
  int LF_REAL48     = 0x800b;
  int LF_COMPLEX32  = 0x800c;
  int LF_COMPLEX64  = 0x800d;
  int LF_COMPLEX80  = 0x800e;
  int LF_COMPLEX128 = 0x800f;
  int LF_VARSTRING  = 0x8010;

  int LF_PAD0       = 0xf0;
  int LF_PAD1       = 0xf1;
  int LF_PAD2       = 0xf2;
  int LF_PAD3       = 0xf3;
  int LF_PAD4       = 0xf4;
  int LF_PAD5       = 0xf5;
  int LF_PAD6       = 0xf6;
  int LF_PAD7       = 0xf7;
  int LF_PAD8       = 0xf8;
  int LF_PAD9       = 0xf9;
  int LF_PAD10      = 0xfa;
  int LF_PAD11      = 0xfb;
  int LF_PAD12      = 0xfc;
  int LF_PAD13      = 0xfd;
  int LF_PAD14      = 0xfe;
  int LF_PAD15      = 0xff;
}
