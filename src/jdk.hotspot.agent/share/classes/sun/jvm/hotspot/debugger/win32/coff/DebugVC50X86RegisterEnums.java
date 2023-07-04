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

public interface DebugVC50X86RegisterEnums {
  /** 8-bit registers */
  int NONE = 0;
  int AL = 1;
  int CL = 2;
  int DL = 3;
  int BL = 4;
  int AH = 5;
  int CH = 6;
  int DH = 7;
  int BH = 8;

  /** 16-bit registers */
  int AX = 9;
  int CX = 10;
  int DX = 11;
  int BX = 12;
  int SP = 13;
  int BP = 14;
  int SI = 15;
  int DI = 16;

  /** 32-bit registers */
  int EAX = 17;
  int ECX = 18;
  int EDX = 19;
  int EBX = 20;
  int ESP = 21;
  int EBP = 22;
  int ESI = 23;
  int EDI = 24;

  /** Segment registers */
  int ES = 25;
  int CS = 26;
  int SS = 27;
  int DS = 28;
  int FS = 29;
  int GS = 30;

  /** Special cases */
  int IP = 31;
  int FLAGS = 32;
  int EIP = 33;
  int EFLAGS = 34;

  /** PCODE Registers */
  int TEMP = 40;
  int TEMPH = 41;
  int QUOTE = 42;

  /** System Registers */
  int CR0 = 80;
  int CR1 = 81;
  int CR2 = 82;
  int CR3 = 83;
  int DR0 = 90;
  int DR1 = 91;
  int DR2 = 92;
  int DR3 = 93;
  int DR4 = 94;
  int DR5 = 95;
  int DR6 = 96;
  int DR7 = 97;

  /** Register extensions for 80x87 */
  int ST0 = 128;
  int ST1 = 129;
  int ST2 = 130;
  int ST3 = 131;
  int ST4 = 132;
  int ST5 = 133;
  int ST6 = 134;
  int ST7 = 135;
  int CONTROL = 136;
  int STATUS = 137;
  int TAG = 138;
  int FPIP = 139;
  int FPCS = 140;
  int FPDO = 141;
  int FPDS = 142;
  int ISEM = 143;
  int FPEIP = 144;
  int FPEDO = 145;
}
