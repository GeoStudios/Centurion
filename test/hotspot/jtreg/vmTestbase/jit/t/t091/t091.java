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

package jit.t.t091;

import nsk.share.TestFailure;
import nsk.share.GoldChecker;

/*
 * @test
 *
 * @summary converted from VM Testbase jit/t/t091.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.t.t091.t091
 */

// This one has a couple of opc_wide prefixes.  These things are hard to
// generate.  All the gratuitous-looking ifs in this test are necessary
// in order to keep javac limping along.  If the assignments aren't
// conditional, javac croaks about "statement not reached" every 64
// assignments.
//
// At time of writing, this didn't work because pass1 of the jit was
// screwing up the flags in the state[] vector around the wide
// instructions.

public class t091
{
    public static final GoldChecker goldChecker = new GoldChecker( "t091" );

    public static void main(String[] argv)
    {
        int i;
        int x0;
        int x1;
        int x2;
        int x3;
        int x4;
        int x5;
        int x6;
        int x7;
        int x8;
        int x9;
        int x10;
        int x11;
        int x12;
        int x13;
        int x14;
        int x15;
        int x16;
        int x17;
        int x18;
        int x19;
        int x20;
        int x21;
        int x22;
        int x23;
        int x24;
        int x25;
        int x26;
        int x27;
        int x28;
        int x29;
        int x30;
        int x31;
        int x32;
        int x33;
        int x34;
        int x35;
        int x36;
        int x37;
        int x38;
        int x39;
        int x40;
        int x41;
        int x42;
        int x43;
        int x44;
        int x45;
        int x46;
        int x47;
        int x48;
        int x49;
        int x50;
        int x51;
        int x52;
        int x53;
        int x54;
        int x55;
        int x56;
        int x57;
        int x58;
        int x59;
        int x60;
        int x61;
        int x62;
        int x63;
        int x64;
        int x65;
        int x66;
        int x67;
        int x68;
        int x69;
        int x70;
        int x71;
        int x72;
        int x73;
        int x74;
        int x75;
        int x76;
        int x77;
        int x78;
        int x79;
        int x80;
        int x81;
        int x82;
        int x83;
        int x84;
        int x85;
        int x86;
        int x87;
        int x88;
        int x89;
        int x90;
        int x91;
        int x92;
        int x93;
        int x94;
        int x95;
        int x96;
        int x97;
        int x98;
        int x99;
        int x100;
        int x101;
        int x102;
        int x103;
        int x104;
        int x105;
        int x106;
        int x107;
        int x108;
        int x109;
        int x110;
        int x111;
        int x112;
        int x113;
        int x114;
        int x115;
        int x116;
        int x117;
        int x118;
        int x119;
        int x120;
        int x121;
        int x122;
        int x123;
        int x124;
        int x125;
        int x126;
        int x127;
        int x128;
        int x129;
        int x130;
        int x131;
        int x132;
        int x133;
        int x134;
        int x135;
        int x136;
        int x137;
        int x138;
        int x139;
        int x140;
        int x141;
        int x142;
        int x143;
        int x144;
        int x145;
        int x146;
        int x147;
        int x148;
        int x149;
        int x150;
        int x151;
        int x152;
        int x153;
        int x154;
        int x155;
        int x156;
        int x157;
        int x158;
        int x159;
        int x160;
        int x161;
        int x162;
        int x163;
        int x164;
        int x165;
        int x166;
        int x167;
        int x168;
        int x169;
        int x170;
        int x171;
        int x172;
        int x173;
        int x174;
        int x175;
        int x176;
        int x177;
        int x178;
        int x179;
        int x180;
        int x181;
        int x182;
        int x183;
        int x184;
        int x185;
        int x186;
        int x187;
        int x188;
        int x189;
        int x190;
        int x191;
        int x192;
        int x193;
        int x194;
        int x195;
        int x196;
        int x197;
        int x198;
        int x199;
        int x200;
        int x201;
        int x202;
        int x203;
        int x204;
        int x205;
        int x206;
        int x207;
        int x208;
        int x209;
        int x210;
        int x211;
        int x212;
        int x213;
        int x214;
        int x215;
        int x216;
        int x217;
        int x218;
        int x219;
        int x220;
        int x221;
        int x222;
        int x223;
        int x224;
        int x225;
        int x226;
        int x227;
        int x228;
        int x229;
        int x230;
        int x231;
        int x232;
        int x233;
        int x234;
        int x235;
        int x236;
        int x237;
        int x238;
        int x239;
        int x240;
        int x241;
        int x242;
        int x243;
        int x244;
        int x245;
        int x246;
        int x247;
        int x248;
        int x249;
        int x250;
        int x251;
        int x252;
        int x253;
        int x254;
        int x255;
        int j;
        i = 39;
        if(i == 0)x0 = 0;
        if(i == 0)x1 = 1;
        if(i == 0)x2 = 2;
        if(i == 0)x3 = 3;
        if(i == 0)x4 = 4;
        if(i == 0)x5 = 5;
        if(i == 0)x6 = 6;
        if(i == 0)x7 = 7;
        if(i == 0)x8 = 8;
        if(i == 0)x9 = 9;
        if(i == 0)x10 = 10;
        if(i == 0)x11 = 11;
        if(i == 0)x12 = 12;
        if(i == 0)x13 = 13;
        if(i == 0)x14 = 14;
        if(i == 0)x15 = 15;
        if(i == 0)x16 = 16;
        if(i == 0)x17 = 17;
        if(i == 0)x18 = 18;
        if(i == 0)x19 = 19;
        if(i == 0)x20 = 20;
        if(i == 0)x21 = 21;
        if(i == 0)x22 = 22;
        if(i == 0)x23 = 23;
        if(i == 0)x24 = 24;
        if(i == 0)x25 = 25;
        if(i == 0)x26 = 26;
        if(i == 0)x27 = 27;
        if(i == 0)x28 = 28;
        if(i == 0)x29 = 29;
        if(i == 0)x30 = 30;
        if(i == 0)x31 = 31;
        if(i == 0)x32 = 32;
        if(i == 0)x33 = 33;
        if(i == 0)x34 = 34;
        if(i == 0)x35 = 35;
        if(i == 0)x36 = 36;
        if(i == 0)x37 = 37;
        if(i == 0)x38 = 38;
        if(i == 0)x39 = 39;
        if(i == 0)x40 = 40;
        if(i == 0)x41 = 41;
        if(i == 0)x42 = 42;
        if(i == 0)x43 = 43;
        if(i == 0)x44 = 44;
        if(i == 0)x45 = 45;
        if(i == 0)x46 = 46;
        if(i == 0)x47 = 47;
        if(i == 0)x48 = 48;
        if(i == 0)x49 = 49;
        if(i == 0)x50 = 50;
        if(i == 0)x51 = 51;
        if(i == 0)x52 = 52;
        if(i == 0)x53 = 53;
        if(i == 0)x54 = 54;
        if(i == 0)x55 = 55;
        if(i == 0)x56 = 56;
        if(i == 0)x57 = 57;
        if(i == 0)x58 = 58;
        if(i == 0)x59 = 59;
        if(i == 0)x60 = 60;
        if(i == 0)x61 = 61;
        if(i == 0)x62 = 62;
        if(i == 0)x63 = 63;
        if(i == 0)x64 = 64;
        if(i == 0)x65 = 65;
        if(i == 0)x66 = 66;
        if(i == 0)x67 = 67;
        if(i == 0)x68 = 68;
        if(i == 0)x69 = 69;
        if(i == 0)x70 = 70;
        if(i == 0)x71 = 71;
        if(i == 0)x72 = 72;
        if(i == 0)x73 = 73;
        if(i == 0)x74 = 74;
        if(i == 0)x75 = 75;
        if(i == 0)x76 = 76;
        if(i == 0)x77 = 77;
        if(i == 0)x78 = 78;
        if(i == 0)x79 = 79;
        if(i == 0)x80 = 80;
        if(i == 0)x81 = 81;
        if(i == 0)x82 = 82;
        if(i == 0)x83 = 83;
        if(i == 0)x84 = 84;
        if(i == 0)x85 = 85;
        if(i == 0)x86 = 86;
        if(i == 0)x87 = 87;
        if(i == 0)x88 = 88;
        if(i == 0)x89 = 89;
        if(i == 0)x90 = 90;
        if(i == 0)x91 = 91;
        if(i == 0)x92 = 92;
        if(i == 0)x93 = 93;
        if(i == 0)x94 = 94;
        if(i == 0)x95 = 95;
        if(i == 0)x96 = 96;
        if(i == 0)x97 = 97;
        if(i == 0)x98 = 98;
        if(i == 0)x99 = 99;
        if(i == 0)x100 = 100;
        if(i == 0)x101 = 101;
        if(i == 0)x102 = 102;
        if(i == 0)x103 = 103;
        if(i == 0)x104 = 104;
        if(i == 0)x105 = 105;
        if(i == 0)x106 = 106;
        if(i == 0)x107 = 107;
        if(i == 0)x108 = 108;
        if(i == 0)x109 = 109;
        if(i == 0)x110 = 110;
        if(i == 0)x111 = 111;
        if(i == 0)x112 = 112;
        if(i == 0)x113 = 113;
        if(i == 0)x114 = 114;
        if(i == 0)x115 = 115;
        if(i == 0)x116 = 116;
        if(i == 0)x117 = 117;
        if(i == 0)x118 = 118;
        if(i == 0)x119 = 119;
        if(i == 0)x120 = 120;
        if(i == 0)x121 = 121;
        if(i == 0)x122 = 122;
        if(i == 0)x123 = 123;
        if(i == 0)x124 = 124;
        if(i == 0)x125 = 125;
        if(i == 0)x126 = 126;
        if(i == 0)x127 = 127;
        if(i == 0)x128 = 128;
        if(i == 0)x129 = 129;
        if(i == 0)x130 = 130;
        if(i == 0)x131 = 131;
        if(i == 0)x132 = 132;
        if(i == 0)x133 = 133;
        if(i == 0)x134 = 134;
        if(i == 0)x135 = 135;
        if(i == 0)x136 = 136;
        if(i == 0)x137 = 137;
        if(i == 0)x138 = 138;
        if(i == 0)x139 = 139;
        if(i == 0)x140 = 140;
        if(i == 0)x141 = 141;
        if(i == 0)x142 = 142;
        if(i == 0)x143 = 143;
        if(i == 0)x144 = 144;
        if(i == 0)x145 = 145;
        if(i == 0)x146 = 146;
        if(i == 0)x147 = 147;
        if(i == 0)x148 = 148;
        if(i == 0)x149 = 149;
        if(i == 0)x150 = 150;
        if(i == 0)x151 = 151;
        if(i == 0)x152 = 152;
        if(i == 0)x153 = 153;
        if(i == 0)x154 = 154;
        if(i == 0)x155 = 155;
        if(i == 0)x156 = 156;
        if(i == 0)x157 = 157;
        if(i == 0)x158 = 158;
        if(i == 0)x159 = 159;
        if(i == 0)x160 = 160;
        if(i == 0)x161 = 161;
        if(i == 0)x162 = 162;
        if(i == 0)x163 = 163;
        if(i == 0)x164 = 164;
        if(i == 0)x165 = 165;
        if(i == 0)x166 = 166;
        if(i == 0)x167 = 167;
        if(i == 0)x168 = 168;
        if(i == 0)x169 = 169;
        if(i == 0)x170 = 170;
        if(i == 0)x171 = 171;
        if(i == 0)x172 = 172;
        if(i == 0)x173 = 173;
        if(i == 0)x174 = 174;
        if(i == 0)x175 = 175;
        if(i == 0)x176 = 176;
        if(i == 0)x177 = 177;
        if(i == 0)x178 = 178;
        if(i == 0)x179 = 179;
        if(i == 0)x180 = 180;
        if(i == 0)x181 = 181;
        if(i == 0)x182 = 182;
        if(i == 0)x183 = 183;
        if(i == 0)x184 = 184;
        if(i == 0)x185 = 185;
        if(i == 0)x186 = 186;
        if(i == 0)x187 = 187;
        if(i == 0)x188 = 188;
        if(i == 0)x189 = 189;
        if(i == 0)x190 = 190;
        if(i == 0)x191 = 191;
        if(i == 0)x192 = 192;
        if(i == 0)x193 = 193;
        if(i == 0)x194 = 194;
        if(i == 0)x195 = 195;
        if(i == 0)x196 = 196;
        if(i == 0)x197 = 197;
        if(i == 0)x198 = 198;
        if(i == 0)x199 = 199;
        if(i == 0)x200 = 200;
        if(i == 0)x201 = 201;
        if(i == 0)x202 = 202;
        if(i == 0)x203 = 203;
        if(i == 0)x204 = 204;
        if(i == 0)x205 = 205;
        if(i == 0)x206 = 206;
        if(i == 0)x207 = 207;
        if(i == 0)x208 = 208;
        if(i == 0)x209 = 209;
        if(i == 0)x210 = 210;
        if(i == 0)x211 = 211;
        if(i == 0)x212 = 212;
        if(i == 0)x213 = 213;
        if(i == 0)x214 = 214;
        if(i == 0)x215 = 215;
        if(i == 0)x216 = 216;
        if(i == 0)x217 = 217;
        if(i == 0)x218 = 218;
        if(i == 0)x219 = 219;
        if(i == 0)x220 = 220;
        if(i == 0)x221 = 221;
        if(i == 0)x222 = 222;
        if(i == 0)x223 = 223;
        if(i == 0)x224 = 224;
        if(i == 0)x225 = 225;
        if(i == 0)x226 = 226;
        if(i == 0)x227 = 227;
        if(i == 0)x228 = 228;
        if(i == 0)x229 = 229;
        if(i == 0)x230 = 230;
        if(i == 0)x231 = 231;
        if(i == 0)x232 = 232;
        if(i == 0)x233 = 233;
        if(i == 0)x234 = 234;
        if(i == 0)x235 = 235;
        if(i == 0)x236 = 236;
        if(i == 0)x237 = 237;
        if(i == 0)x238 = 238;
        if(i == 0)x239 = 239;
        if(i == 0)x240 = 240;
        if(i == 0)x241 = 241;
        if(i == 0)x242 = 242;
        if(i == 0)x243 = 243;
        if(i == 0)x244 = 244;
        if(i == 0)x245 = 245;
        if(i == 0)x246 = 246;
        if(i == 0)x247 = 247;
        if(i == 0)x248 = 248;
        if(i == 0)x249 = 249;
        if(i == 0)x250 = 250;
        if(i == 0)x251 = 251;
        if(i == 0)x252 = 252;
        if(i == 0)x253 = 253;
        if(i == 0)x254 = 254;
        if(i == 0)x255 = 255;
        j = 42;
        t091.goldChecker.println(i+j);
        t091.goldChecker.check();
    }
}
