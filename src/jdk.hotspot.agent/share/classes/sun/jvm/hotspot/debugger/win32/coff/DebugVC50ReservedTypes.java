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

public interface DebugVC50ReservedTypes {

  //
  // Special types
  //

  /** Uncharacterized type (no type) */
  int T_NOTYPE = 0x0000;

  /** Absolute symbol */
  int T_ABS = 0x0001;

  /** Segment type */
  int T_SEGMENT = 0x0002;

  /** Void */
  int T_VOID = 0x0003;

  /** Near pointer to void */
  int T_PVOID = 0x0103;

  /** Far pointer to void */
  int T_PFVOID = 0x0203;

  /** Huge pointer to void */
  int T_PHVOID = 0x0303;

  /** 32 bit near pointer to void */
  int T_32PVOID = 0x0403;

  /** 32 bit far pointer to void */
  int T_32PFVOID = 0x0503;

  /** 64 bit pointer to void */
  int T_64PVOID = 0x0603;

  /** Basic 8 byte currency value */
  int T_CURRENCY = 0x0004;

  /** Near Basic string */
  int T_NBASICSTR = 0x0005;

  /** Far Basic string */
  int T_FBASICSTR = 0x0006;

  /** Untranslated type record from CV 3.x format */
  int T_NOTTRANS = 0x0007;

  /** Bit */
  int T_BIT = 0x0060;

  /** Pascal CHAR */
  int T_PASCHAR = 0x0061;

  //
  // Character types
  //

  /** 8-bit signed  */
  int T_CHAR = 0x0010;

  /** 8-bit unsigned */
  int T_UCHAR = 0x0020;

  /** Near pointer to 8-bit signed */
  int T_PCHAR = 0x0110;

  /** Near pointer to 8-bit unsigned */
  int T_PUCHAR = 0x0120;

  /** Far pointer to 8-bit signed */
  int T_PFCHAR = 0x0210;

  /** Far pointer to 8-bit unsigned */
  int T_PFUCHAR = 0x0220;

  /** Huge pointer to 8-bit signed */
  int T_PHCHAR = 0x0310;

  /** Huge pointer to 8-bit unsigned */
  int T_PHUCHAR = 0x0320;

  /** 16:32 near pointer to 8-bit signed */
  int T_32PCHAR = 0x0410;

  /** 16:32 near pointer to 8-bit unsigned */
  int T_32PUCHAR = 0x0420;

  /** 16:32 far pointer to 8-bit signed */
  int T_32PFCHAR = 0x0510;

  /** 16:32 far pointer to 8-bit unsigned */
  int T_32PFUCHAR = 0x0520;

  /** 64 bit pointer to 8 bit signed */
  int T_64PCHAR = 0x0610;

  /** 64 bit pointer to 8 bit unsigned */
  int T_64PUCHAR = 0x0620;

  //
  // Really a character types
  //

  /** real char */
  int T_RCHAR = 0x0070;

  /** near pointer to a real char */
  int T_PRCHAR = 0x0170;

  /** far pointer to a real char */
  int T_PFRCHAR = 0x0270;

  /** huge pointer to a real char */
  int T_PHRCHAR = 0x0370;

  /** 16:32 near pointer to a real char */
  int T_32PRCHAR = 0x0470;

  /** 16:32 far pointer to a real char */
  int T_32PFRCHAR = 0x0570;

  /** 64 bit pointer to a real char */
  int T_64PRCHAR = 0x0670;

  //
  // Wide character types
  //

  /** wide char */
  int T_WCHAR = 0x0071;

  /** near pointer to a wide char */
  int T_PWCHAR = 0x0171;

  /** far pointer to a wide char */
  int T_PFWCHAR = 0x0271;

  /** huge pointer to a wide char */
  int T_PHWCHAR = 0x0371;

  /** 16:32 near pointer to a wide char */
  int T_32PWCHAR = 0x0471;

  /** 16:32 far pointer to a wide char */
  int T_32PFWCHAR = 0x0571;

  /** 64 bit pointer to a wide char */
  int T_64PWCHAR = 0x0671;

  //
  // Really 16 bit integer types
  //

  /** really 16 bit signed int */
  int T_INT2 = 0x0072;

  /** really 16 bit unsigned int */
  int T_UINT2 = 0x0073;

  /** near pointer to 16 bit signed int */
  int T_PINT2 = 0x0172;

  /** near pointer to 16 bit unsigned int */
  int T_PUINT2 = 0x0173;

  /** far pointer to 16 bit signed int */
  int T_PFINT2 = 0x0272;

  /** far pointer to 16 bit unsigned int */
  int T_PFUINT2 = 0x0273;

  /** huge pointer to 16 bit signed int */
  int T_PHINT2 = 0x0372;

  /** huge pointer to 16 bit unsigned int */
  int T_PHUINT2 = 0x0373;

  /** 16:32 near pointer to 16 bit signed int */
  int T_32PINT2 = 0x0472;

  /** 16:32 near pointer to 16 bit unsigned int */
  int T_32PUINT2 = 0x0473;

  /** 16:32 far pointer to 16 bit signed int */
  int T_32PFINT2 = 0x0572;

  /** 16:32 far pointer to 16 bit unsigned int */
  int T_32PFUINT2 = 0x0573;

  /** 64 bit pointer to 16 bit signed int */
  int T_64PINT2 = 0x0672;

  /** 64 bit pointer to 16 bit unsigned int */
  int T_64PUINT2 = 0x0673;

  //
  // 16-bit short types
  //

  /** 16-bit signed  */
  int T_SHORT = 0x0011;

  /** 16-bit unsigned */
  int T_USHORT = 0x0021;

  /** Near pointer to 16-bit signed */
  int T_PSHORT = 0x0111;

  /** Near pointer to 16-bit unsigned */
  int T_PUSHORT = 0x0121;

  /** Far pointer to 16-bit signed */
  int T_PFSHORT = 0x0211;

  /** Far pointer to 16-bit unsigned */
  int T_PFUSHORT = 0x0221;

  /** Huge pointer to 16-bit signed */
  int T_PHSHORT = 0x0311;

  /** Huge pointer to 16-bit unsigned */
  int T_PHUSHORT = 0x0321;

  /** 16:32 near pointer to 16 bit signed */
  int T_32PSHORT = 0x0411;

  /** 16:32 near pointer to 16 bit unsigned */
  int T_32PUSHORT = 0x0421;

  /** 16:32 far pointer to 16 bit signed */
  int T_32PFSHORT = 0x0511;

  /** 16:32 far pointer to 16 bit unsigned */
  int T_32PFUSHORT = 0x0521;

  /** 64 bit pointer to 16 bit signed */
  int T_64PSHORT = 0x0611;

  /** 64 bit pointer to 16 bit unsigned */
  int T_64PUSHORT = 0x0621;

  //
  // Really 32 bit integer types
  //

  /** really 32 bit signed int */
  int T_INT4 = 0x0074;

  /** really 32 bit unsigned int */
  int T_UINT4 = 0x0075;

  /** near pointer to 32 bit signed int */
  int T_PINT4 = 0x0174;

  /** near pointer to 32 bit unsigned int */
  int T_PUINT4 = 0x0175;

  /** far pointer to 32 bit signed int */
  int T_PFINT4 = 0x0274;

  /** far pointer to 32 bit unsigned int */
  int T_PFUINT4 = 0x0275;

  /** huge pointer to 32 bit signed int */
  int T_PHINT4 = 0x0374;

  /** huge pointer to 32 bit unsigned int */
  int T_PHUINT4 = 0x0375;

  /** 16:32 near pointer to 32 bit signed int */
  int T_32PINT4 = 0x0474;

  /** 16:32 near pointer to 32 bit unsigned int */
  int T_32PUINT4 = 0x0475;

  /** 16:32 far pointer to 32 bit signed int */
  int T_32PFINT4 = 0x0574;

  /** 16:32 far pointer to 32 bit unsigned int */
  int T_32PFUINT4 = 0x0575;

  /** 64 bit pointer to 32 bit signed int */
  int T_64PINT4 = 0x0674;

  /** 64 bit pointer to 32 bit unsigned int */
  int T_64PUINT4 = 0x0675;

  //
  // 32-bit long types
  //

  /** 32-bit signed  */
  int T_LONG = 0x0012;

  /** 32-bit unsigned */
  int T_ULONG = 0x0022;

  /** Near pointer to 32-bit signed */
  int T_PLONG = 0x0112;

  /** Near pointer to 32-bit unsigned */
  int T_PULONG = 0x0122;

  /** Far pointer to 32-bit signed */
  int T_PFLONG = 0x0212;

  /** Far pointer to 32-bit unsigned */
  int T_PFULONG = 0x0222;

  /** Huge pointer to 32-bit signed */
  int T_PHLONG = 0x0312;

  /** Huge pointer to 32-bit unsigned */
  int T_PHULONG = 0x0322;

  /** 16:32 near pointer to 32 bit signed  */
  int T_32PLONG = 0x0412;

  /** 16:32 near pointer to 32 bit unsigned  */
  int T_32PULONG = 0x0422;

  /** 16:32 far pointer to 32 bit signed  */
  int T_32PFLONG = 0x0512;

  /** 16:32 far pointer to 32 bit unsigned */
  int T_32PFULONG = 0x0522;

  /** 64 bit pointer to 32 bit signed  */
  int T_64PLONG = 0x0612;

  /** 64 bit pointer to 32 bit unsigned */
  int T_64PULONG = 0x0622;

  //
  // Really 64-bit integer types
  //

  /** 64-bit signed int */
  int T_INT8 = 0x0076;

  /** 64-bit unsigned int */
  int T_UINT8 = 0x0077;

  /** Near pointer to 64-bit signed int */
  int T_PINT8 = 0x0176;

  /** Near pointer to 64-bit unsigned int */
  int T_PUINT8 = 0x0177;

  /** Far pointer to 64-bit signed int */
  int T_PFINT8 = 0x0276;

  /** Far pointer to 64-bit unsigned int */
  int T_PFUINT8 = 0x0277;

  /** Huge pointer to 64-bit signed int */
  int T_PHINT8 = 0x0376;

  /** Huge pointer to 64-bit unsigned int */
  int T_PHUINT8 = 0x0377;

  /** 16:32 near pointer to 64 bit signed int */
  int T_32PINT8 = 0x0476;

  /** 16:32 near pointer to 64 bit unsigned int */
  int T_32PUINT8 = 0x0477;

  /** 16:32 far pointer to 64 bit signed int */
  int T_32PFINT8 = 0x0576;

  /** 16:32 far pointer to 64 bit unsigned int */
  int T_32PFUINT8 = 0x0577;

  /** 64 bit pointer to 64 bit signed int */
  int T_64PINT8 = 0x0676;

  /** 64 bit pointer to 64 bit unsigned int */
  int T_64PUINT8 = 0x0677;

  //
  // 64-bit integral types
  //

  /** 64-bit signed  */
  int T_QUAD = 0x0013;

  /** 64-bit unsigned */
  int T_UQUAD = 0x0023;

  /** Near pointer to 64-bit signed */
  int T_PQUAD = 0x0113;

  /** Near pointer to 64-bit unsigned */
  int T_PUQUAD = 0x0123;

  /** Far pointer to 64-bit signed */
  int T_PFQUAD = 0x0213;

  /** Far pointer to 64-bit unsigned */
  int T_PFUQUAD = 0x0223;

  /** Huge pointer to 64-bit signed */
  int T_PHQUAD = 0x0313;

  /** Huge pointer to 64-bit unsigned */
  int T_PHUQUAD = 0x0323;

  /** 16:32 near pointer to 64 bit signed  */
  int T_32PQUAD = 0x0413;

  /** 16:32 near pointer to 64 bit unsigned  */
  int T_32PUQUAD = 0x0423;

  /** 16:32 far pointer to 64 bit signed  */
  int T_32PFQUAD = 0x0513;

  /** 16:32 far pointer to 64 bit unsigned  */
  int T_32PFUQUAD = 0x0523;

  /** 64 bit pointer to 64 bit signed  */
  int T_64PQUAD = 0x0613;

  /** 64 bit pointer to 64 bit unsigned  */
  int T_64PUQUAD = 0x0623;

  //
  // 32-bit real types
  //

  /** 32-bit real  */
  int T_REAL32 = 0x0040;

  /** Near pointer to 32-bit real */
  int T_PREAL32 = 0x0140;

  /** Far pointer to 32-bit real */
  int T_PFREAL32 = 0x0240;

  /** Huge pointer to 32-bit real */
  int T_PHREAL32 = 0x0340;

  /** 16:32 near pointer to 32 bit real */
  int T_32PREAL32 = 0x0440;

  /** 16:32 far pointer to 32 bit real */
  int T_32PFREAL32 = 0x0540;

  /** 64 pointer to 32 bit real */
  int T_64PREAL32 = 0x0640;

  //
  // 48-bit real types
  //

  /** 48-bit real  */
  int T_REAL48 = 0x0044;

  /** Near pointer to 48-bit real */
  int T_PREAL48 = 0x0144;

  /** Far pointer to 48-bit real */
  int T_PFREAL48 = 0x0244;

  /** Huge pointer to 48-bit real */
  int T_PHREAL48 = 0x0344;

  /** 16:32 near pointer to 48 bit real */
  int T_32PREAL48 = 0x0444;

  /** 16:32 far pointer to 48 bit real */
  int T_32PFREAL48 = 0x0544;

  /** 64 bit pointer to 48 bit real */
  int T_64PREAL48 = 0x0644;

  //
  // 64-bit real types
  //

  /** 64-bit real  */
  int T_REAL64 = 0x0041;

  /** Near pointer to 64-bit real */
  int T_PREAL64 = 0x0141;

  /** Far pointer to 64-bit real */
  int T_PFREAL64 = 0x0241;

  /** Huge pointer to 64-bit real */
  int T_PHREAL64 = 0x0341;

  /** 16:32 near pointer to 64 bit real */
  int T_32PREAL64 = 0x0441;

  /** 16:32 far pointer to 64 bit real */
  int T_32PFREAL64 = 0x0541;

  /** 64 bit pointer to 64 bit real */
  int T_64PREAL64 = 0x0641;

  //
  // 80-bit real types
  //

  /** 80-bit real  */
  int T_REAL80 = 0x0042;

  /** Near pointer to 80-bit real */
  int T_PREAL80 = 0x0142;

  /** Far pointer to 80-bit real */
  int T_PFREAL80 = 0x0242;

  /** Huge pointer to 80-bit real */
  int T_PHREAL80 = 0x0342;

  /** 16:32 near pointer to 80 bit real */
  int T_32PREAL80 = 0x0442;

  /** 16:32 far pointer to 80 bit real */
  int T_32PFREAL80 = 0x0542;

  /** 64 bit pointer to 80 bit real */
  int T_64PREAL80 = 0x0642;

  //
  // 128-bit real types
  //

  /** 128-bit real  */
  int T_REAL128 = 0x0043;

  /** Near pointer to 128-bit real */
  int T_PREAL128 = 0x0143;

  /** Far pointer to 128-bit real */
  int T_PFREAL128 = 0x0243;

  /** Huge pointer to 128-bit real */
  int T_PHREAL128 = 0x0343;

  /** 16:32 near pointer to 128 bit real */
  int T_32PREAL128 = 0x0443;

  /** 16:32 far pointer to 128 bit real */
  int T_32PFREAL128 = 0x0543;

  /** 64 bit pointer to 128 bit real */
  int T_64PREAL128 = 0x0643;

  //
  // 32-bit complex types
  //

  /** 32-bit complex  */
  int T_CPLX32 = 0x0050;

  /** Near pointer to 32-bit complex */
  int T_PCPLX32 = 0x0150;

  /** Far pointer to 32-bit complex */
  int T_PFCPLX32 = 0x0250;

  /** Huge pointer to 32-bit complex */
  int T_PHCPLX32 = 0x0350;

  /** 16:32 near pointer to 32 bit complex */
  int T_32PCPLX32 = 0x0450;

  /** 16:32 far pointer to 32 bit complex */
  int T_32PFCPLX32 = 0x0550;

  /** 64 bit pointer to 32 bit complex */
  int T_64PCPLX32 = 0x0650;

  //
  // 64-bit complex types
  //

  /** 64-bit complex  */
  int T_CPLX64 = 0x0051;

  /** Near pointer to 64-bit complex */
  int T_PCPLX64 = 0x0151;

  /** Far pointer to 64-bit complex */
  int T_PFCPLX64 = 0x0251;

  /** Huge pointer to 64-bit complex */
  int T_PHCPLX64 = 0x0351;

  /** 16:32 near pointer to 64 bit complex */
  int T_32PCPLX64 = 0x0451;

  /** 16:32 far pointer to 64 bit complex */
  int T_32PFCPLX64 = 0x0551;

  /** 64 bit pointer to 64 bit complex */
  int T_64PCPLX64 = 0x0651;

  //
  // 80-bit complex types
  //

  /** 80-bit complex  */
  int T_CPLX80 = 0x0052;

  /** Near pointer to 80-bit complex */
  int T_PCPLX80 = 0x0152;

  /** Far pointer to 80-bit complex */
  int T_PFCPLX80 = 0x0252;

  /** Huge pointer to 80-bit complex */
  int T_PHCPLX80 = 0x0352;

  /** 16:32 near pointer to 80 bit complex */
  int T_32PCPLX80 = 0x0452;

  /** 16:32 far pointer to 80 bit complex */
  int T_32PFCPLX80 = 0x0552;

  /** 64 bit pointer to 80 bit complex */
  int T_64PCPLX80 = 0x0652;

  //
  // 128-bit complex types
  //

  /** 128-bit complex  */
  int T_CPLX128 = 0x0053;

  /** Near pointer to 128-bit complex */
  int T_PCPLX128 = 0x0153;

  /** Far pointer to 128-bit complex */
  int T_PFCPLX128 = 0x0253;

  /** Huge pointer to 128-bit real */
  int T_PHCPLX128 = 0x0353;

  /** 16:32 near pointer to 128 bit complex */
  int T_32PCPLX128 = 0x0453;

  /** 16:32 far pointer to 128 bit complex */
  int T_32PFCPLX128 = 0x0553;

  /** 64 bit pointer to 128 bit complex */
  int T_64PCPLX128 = 0x0653;

  //
  // Boolean types
  //

  /** 8-bit Boolean */
  int T_BOOL08 = 0x0030;

  /** 16-bit Boolean */
  int T_BOOL16 = 0x0031;

  /** 32-bit Boolean */
  int T_BOOL32 = 0x0032;

  /** 64-bit Boolean */
  int T_BOOL64 = 0x0033;

  /** Near pointer to 8-bit Boolean */
  int T_PBOOL08 = 0x0130;

  /** Near pointer to 16-bit Boolean */
  int T_PBOOL16 = 0x0131;

  /** Near pointer to 32-bit Boolean */
  int T_PBOOL32 = 0x0132;

  /** Near pointer to 64-bit Boolean */
  int T_PBOOL64 = 0x0133;

  /** Far pointer to 8-bit Boolean */
  int T_PFBOOL08 = 0x0230;

  /** Far pointer to 16-bit Boolean */
  int T_PFBOOL16 = 0x0231;

  /** Far pointer to 32-bit Boolean */
  int T_PFBOOL32 = 0x0232;

  /** Far pointer to 64-bit Boolean */
  int T_PFBOOL64 = 0x0233;

  /** Huge pointer to 8-bit Boolean */
  int T_PHBOOL08 = 0x0330;

  /** Huge pointer to 16-bit Boolean */
  int T_PHBOOL16 = 0x0331;

  /** Huge pointer to 32-bit Boolean */
  int T_PHBOOL32 = 0x0332;

  /** Huge pointer to 64-bit Boolean */
  int T_PHBOOL64 = 0x0333;

  /** 16:32 near pointer to 8 bit boolean */
  int T_32PBOOL08 = 0x0430;

  /** 16:32 far pointer to 8 bit boolean */
  int T_32PFBOOL08 = 0x0530;

  /** 16:32 near pointer to 16 bit boolean */
  int T_32PBOOL16 = 0x0431;

  /** 16:32 far pointer to 16 bit boolean */
  int T_32PFBOOL16 = 0x0531;

  /** 16:32 near pointer to 32 bit boolean */
  int T_32PBOOL32 = 0x0432;

  /** 16:32 far pointer to 32 bit boolean */
  int T_32PFBOOL32 = 0x0532;

  /** 16:32 near pointer to 64-bit Boolean */
  int T_32PBOOL64 = 0x0433;

  /** 16:32 far pointer to 64-bit Boolean */
  int T_32PFBOOL64 = 0x0533;

  /** 64 bit pointer to 8 bit boolean */
  int T_64PBOOL08 = 0x0630;

  /** 64 bit pointer to 16 bit boolean */
  int T_64PBOOL16 = 0x0631;

  /** 64 bit pointer to 32 bit boolean */
  int T_64PBOOL32 = 0x0632;

  /** 64 bit pointer to 64-bit Boolean */
  int T_64PBOOL64 = 0x0633;
}
