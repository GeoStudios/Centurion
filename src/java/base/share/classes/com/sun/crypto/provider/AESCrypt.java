/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.base.share.classes.java.security.InvalidKeyException;
import java.base.share.classes.java.security.MessageDigest;
import java.base.share.classes.java.util.Arrays;

import java.base.share.classes.jdk.internal.vm.annotation.IntrinsicCandidate;

/**
 * Rijndael --pronounced Reindaal-- is a symmetric cipher with a 128-bit
 * block size and variable key-size (128-, 192- and 256-bit).
 * <p>
 * Rijndael was designed by <a href="mailto:rijmen@esat.kuleuven.ac.be">Vincent
 * Rijmen</a> and <a href="mailto:Joan.Daemen@village.uunet.be">Joan Daemen</a>.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

final class AESCrypt extends SymmetricCipher implements AESConstants {
    //
    // Pre-computed tables, which are copied or derived from FIPS 197.
    //

    // the pre-computed substitution table (S-box), 256 bytes
    private static final byte[] S = {
            (byte)0x63, (byte)0x7C, (byte)0x77, (byte)0x7B,
            (byte)0xF2, (byte)0x6B, (byte)0x6F, (byte)0xC5,
            (byte)0x30, (byte)0x01, (byte)0x67, (byte)0x2B,
            (byte)0xFE, (byte)0xD7, (byte)0xAB, (byte)0x76,
            (byte)0xCA, (byte)0x82, (byte)0xC9, (byte)0x7D,
            (byte)0xFA, (byte)0x59, (byte)0x47, (byte)0xF0,
            (byte)0xAD, (byte)0xD4, (byte)0xA2, (byte)0xAF,
            (byte)0x9C, (byte)0xA4, (byte)0x72, (byte)0xC0,
            (byte)0xB7, (byte)0xFD, (byte)0x93, (byte)0x26,
            (byte)0x36, (byte)0x3F, (byte)0xF7, (byte)0xCC,
            (byte)0x34, (byte)0xA5, (byte)0xE5, (byte)0xF1,
            (byte)0x71, (byte)0xD8, (byte)0x31, (byte)0x15,
            (byte)0x04, (byte)0xC7, (byte)0x23, (byte)0xC3,
            (byte)0x18, (byte)0x96, (byte)0x05, (byte)0x9A,
            (byte)0x07, (byte)0x12, (byte)0x80, (byte)0xE2,
            (byte)0xEB, (byte)0x27, (byte)0xB2, (byte)0x75,
            (byte)0x09, (byte)0x83, (byte)0x2C, (byte)0x1A,
            (byte)0x1B, (byte)0x6E, (byte)0x5A, (byte)0xA0,
            (byte)0x52, (byte)0x3B, (byte)0xD6, (byte)0xB3,
            (byte)0x29, (byte)0xE3, (byte)0x2F, (byte)0x84,
            (byte)0x53, (byte)0xD1, (byte)0x00, (byte)0xED,
            (byte)0x20, (byte)0xFC, (byte)0xB1, (byte)0x5B,
            (byte)0x6A, (byte)0xCB, (byte)0xBE, (byte)0x39,
            (byte)0x4A, (byte)0x4C, (byte)0x58, (byte)0xCF,
            (byte)0xD0, (byte)0xEF, (byte)0xAA, (byte)0xFB,
            (byte)0x43, (byte)0x4D, (byte)0x33, (byte)0x85,
            (byte)0x45, (byte)0xF9, (byte)0x02, (byte)0x7F,
            (byte)0x50, (byte)0x3C, (byte)0x9F, (byte)0xA8,
            (byte)0x51, (byte)0xA3, (byte)0x40, (byte)0x8F,
            (byte)0x92, (byte)0x9D, (byte)0x38, (byte)0xF5,
            (byte)0xBC, (byte)0xB6, (byte)0xDA, (byte)0x21,
            (byte)0x10, (byte)0xFF, (byte)0xF3, (byte)0xD2,
            (byte)0xCD, (byte)0x0C, (byte)0x13, (byte)0xEC,
            (byte)0x5F, (byte)0x97, (byte)0x44, (byte)0x17,
            (byte)0xC4, (byte)0xA7, (byte)0x7E, (byte)0x3D,
            (byte)0x64, (byte)0x5D, (byte)0x19, (byte)0x73,
            (byte)0x60, (byte)0x81, (byte)0x4F, (byte)0xDC,
            (byte)0x22, (byte)0x2A, (byte)0x90, (byte)0x88,
            (byte)0x46, (byte)0xEE, (byte)0xB8, (byte)0x14,
            (byte)0xDE, (byte)0x5E, (byte)0x0B, (byte)0xDB,
            (byte)0xE0, (byte)0x32, (byte)0x3A, (byte)0x0A,
            (byte)0x49, (byte)0x06, (byte)0x24, (byte)0x5C,
            (byte)0xC2, (byte)0xD3, (byte)0xAC, (byte)0x62,
            (byte)0x91, (byte)0x95, (byte)0xE4, (byte)0x79,
            (byte)0xE7, (byte)0xC8, (byte)0x37, (byte)0x6D,
            (byte)0x8D, (byte)0xD5, (byte)0x4E, (byte)0xA9,
            (byte)0x6C, (byte)0x56, (byte)0xF4, (byte)0xEA,
            (byte)0x65, (byte)0x7A, (byte)0xAE, (byte)0x08,
            (byte)0xBA, (byte)0x78, (byte)0x25, (byte)0x2E,
            (byte)0x1C, (byte)0xA6, (byte)0xB4, (byte)0xC6,
            (byte)0xE8, (byte)0xDD, (byte)0x74, (byte)0x1F,
            (byte)0x4B, (byte)0xBD, (byte)0x8B, (byte)0x8A,
            (byte)0x70, (byte)0x3E, (byte)0xB5, (byte)0x66,
            (byte)0x48, (byte)0x03, (byte)0xF6, (byte)0x0E,
            (byte)0x61, (byte)0x35, (byte)0x57, (byte)0xB9,
            (byte)0x86, (byte)0xC1, (byte)0x1D, (byte)0x9E,
            (byte)0xE1, (byte)0xF8, (byte)0x98, (byte)0x11,
            (byte)0x69, (byte)0xD9, (byte)0x8E, (byte)0x94,
            (byte)0x9B, (byte)0x1E, (byte)0x87, (byte)0xE9,
            (byte)0xCE, (byte)0x55, (byte)0x28, (byte)0xDF,
            (byte)0x8C, (byte)0xA1, (byte)0x89, (byte)0x0D,
            (byte)0xBF, (byte)0xE6, (byte)0x42, (byte)0x68,
            (byte)0x41, (byte)0x99, (byte)0x2D, (byte)0x0F,
            (byte)0xB0, (byte)0x54, (byte)0xBB, (byte)0x16,
    };

    // the pre-computed substitution table (inverse S-box), 256 bytes
    private static final byte[] Si = {
            (byte)0x52, (byte)0x09, (byte)0x6A, (byte)0xD5,
            (byte)0x30, (byte)0x36, (byte)0xA5, (byte)0x38,
            (byte)0xBF, (byte)0x40, (byte)0xA3, (byte)0x9E,
            (byte)0x81, (byte)0xF3, (byte)0xD7, (byte)0xFB,
            (byte)0x7C, (byte)0xE3, (byte)0x39, (byte)0x82,
            (byte)0x9B, (byte)0x2F, (byte)0xFF, (byte)0x87,
            (byte)0x34, (byte)0x8E, (byte)0x43, (byte)0x44,
            (byte)0xC4, (byte)0xDE, (byte)0xE9, (byte)0xCB,
            (byte)0x54, (byte)0x7B, (byte)0x94, (byte)0x32,
            (byte)0xA6, (byte)0xC2, (byte)0x23, (byte)0x3D,
            (byte)0xEE, (byte)0x4C, (byte)0x95, (byte)0x0B,
            (byte)0x42, (byte)0xFA, (byte)0xC3, (byte)0x4E,
            (byte)0x08, (byte)0x2E, (byte)0xA1, (byte)0x66,
            (byte)0x28, (byte)0xD9, (byte)0x24, (byte)0xB2,
            (byte)0x76, (byte)0x5B, (byte)0xA2, (byte)0x49,
            (byte)0x6D, (byte)0x8B, (byte)0xD1, (byte)0x25,
            (byte)0x72, (byte)0xF8, (byte)0xF6, (byte)0x64,
            (byte)0x86, (byte)0x68, (byte)0x98, (byte)0x16,
            (byte)0xD4, (byte)0xA4, (byte)0x5C, (byte)0xCC,
            (byte)0x5D, (byte)0x65, (byte)0xB6, (byte)0x92,
            (byte)0x6C, (byte)0x70, (byte)0x48, (byte)0x50,
            (byte)0xFD, (byte)0xED, (byte)0xB9, (byte)0xDA,
            (byte)0x5E, (byte)0x15, (byte)0x46, (byte)0x57,
            (byte)0xA7, (byte)0x8D, (byte)0x9D, (byte)0x84,
            (byte)0x90, (byte)0xD8, (byte)0xAB, (byte)0x00,
            (byte)0x8C, (byte)0xBC, (byte)0xD3, (byte)0x0A,
            (byte)0xF7, (byte)0xE4, (byte)0x58, (byte)0x05,
            (byte)0xB8, (byte)0xB3, (byte)0x45, (byte)0x06,
            (byte)0xD0, (byte)0x2C, (byte)0x1E, (byte)0x8F,
            (byte)0xCA, (byte)0x3F, (byte)0x0F, (byte)0x02,
            (byte)0xC1, (byte)0xAF, (byte)0xBD, (byte)0x03,
            (byte)0x01, (byte)0x13, (byte)0x8A, (byte)0x6B,
            (byte)0x3A, (byte)0x91, (byte)0x11, (byte)0x41,
            (byte)0x4F, (byte)0x67, (byte)0xDC, (byte)0xEA,
            (byte)0x97, (byte)0xF2, (byte)0xCF, (byte)0xCE,
            (byte)0xF0, (byte)0xB4, (byte)0xE6, (byte)0x73,
            (byte)0x96, (byte)0xAC, (byte)0x74, (byte)0x22,
            (byte)0xE7, (byte)0xAD, (byte)0x35, (byte)0x85,
            (byte)0xE2, (byte)0xF9, (byte)0x37, (byte)0xE8,
            (byte)0x1C, (byte)0x75, (byte)0xDF, (byte)0x6E,
            (byte)0x47, (byte)0xF1, (byte)0x1A, (byte)0x71,
            (byte)0x1D, (byte)0x29, (byte)0xC5, (byte)0x89,
            (byte)0x6F, (byte)0xB7, (byte)0x62, (byte)0x0E,
            (byte)0xAA, (byte)0x18, (byte)0xBE, (byte)0x1B,
            (byte)0xFC, (byte)0x56, (byte)0x3E, (byte)0x4B,
            (byte)0xC6, (byte)0xD2, (byte)0x79, (byte)0x20,
            (byte)0x9A, (byte)0xDB, (byte)0xC0, (byte)0xFE,
            (byte)0x78, (byte)0xCD, (byte)0x5A, (byte)0xF4,
            (byte)0x1F, (byte)0xDD, (byte)0xA8, (byte)0x33,
            (byte)0x88, (byte)0x07, (byte)0xC7, (byte)0x31,
            (byte)0xB1, (byte)0x12, (byte)0x10, (byte)0x59,
            (byte)0x27, (byte)0x80, (byte)0xEC, (byte)0x5F,
            (byte)0x60, (byte)0x51, (byte)0x7F, (byte)0xA9,
            (byte)0x19, (byte)0xB5, (byte)0x4A, (byte)0x0D,
            (byte)0x2D, (byte)0xE5, (byte)0x7A, (byte)0x9F,
            (byte)0x93, (byte)0xC9, (byte)0x9C, (byte)0xEF,
            (byte)0xA0, (byte)0xE0, (byte)0x3B, (byte)0x4D,
            (byte)0xAE, (byte)0x2A, (byte)0xF5, (byte)0xB0,
            (byte)0xC8, (byte)0xEB, (byte)0xBB, (byte)0x3C,
            (byte)0x83, (byte)0x53, (byte)0x99, (byte)0x61,
            (byte)0x17, (byte)0x2B, (byte)0x04, (byte)0x7E,
            (byte)0xBA, (byte)0x77, (byte)0xD6, (byte)0x26,
            (byte)0xE1, (byte)0x69, (byte)0x14, (byte)0x63,
            (byte)0x55, (byte)0x21, (byte)0x0C, (byte)0x7D,
    };

    // pre-computed tables (T-box)
    private static final int[] T1 = {
            0xC66363A5, 0xF87C7C84, 0xEE777799, 0xF67B7B8D,
            0xFFF2F20D, 0xD66B6BBD, 0xDE6F6FB1, 0x91C5C554,
            0x60303050, 0x02010103, 0xCE6767A9, 0x562B2B7D,
            0xE7FEFE19, 0xB5D7D762, 0x4DABABE6, 0xEC76769A,
            0x8FCACA45, 0x1F82829D, 0x89C9C940, 0xFA7D7D87,
            0xEFFAFA15, 0xB25959EB, 0x8E4747C9, 0xFBF0F00B,
            0x41ADADEC, 0xB3D4D467, 0x5FA2A2FD, 0x45AFAFEA,
            0x239C9CBF, 0x53A4A4F7, 0xE4727296, 0x9BC0C05B,
            0x75B7B7C2, 0xE1FDFD1C, 0x3D9393AE, 0x4C26266A,
            0x6C36365A, 0x7E3F3F41, 0xF5F7F702, 0x83CCCC4F,
            0x6834345C, 0x51A5A5F4, 0xD1E5E534, 0xF9F1F108,
            0xE2717193, 0xABD8D873, 0x62313153, 0x2A15153F,
            0x0804040C, 0x95C7C752, 0x46232365, 0x9DC3C35E,
            0x30181828, 0x379696A1, 0x0A05050F, 0x2F9A9AB5,
            0x0E070709, 0x24121236, 0x1B80809B, 0xDFE2E23D,
            0xCDEBEB26, 0x4E272769, 0x7FB2B2CD, 0xEA75759F,
            0x1209091B, 0x1D83839E, 0x582C2C74, 0x341A1A2E,
            0x361B1B2D, 0xDC6E6EB2, 0xB45A5AEE, 0x5BA0A0FB,
            0xA45252F6, 0x763B3B4D, 0xB7D6D661, 0x7DB3B3CE,
            0x5229297B, 0xDDE3E33E, 0x5E2F2F71, 0x13848497,
            0xA65353F5, 0xB9D1D168, 0x00000000, 0xC1EDED2C,
            0x40202060, 0xE3FCFC1F, 0x79B1B1C8, 0xB65B5BED,
            0xD46A6ABE, 0x8DCBCB46, 0x67BEBED9, 0x7239394B,
            0x944A4ADE, 0x984C4CD4, 0xB05858E8, 0x85CFCF4A,
            0xBBD0D06B, 0xC5EFEF2A, 0x4FAAAAE5, 0xEDFBFB16,
            0x864343C5, 0x9A4D4DD7, 0x66333355, 0x11858594,
            0x8A4545CF, 0xE9F9F910, 0x04020206, 0xFE7F7F81,
            0xA05050F0, 0x783C3C44, 0x259F9FBA, 0x4BA8A8E3,
            0xA25151F3, 0x5DA3A3FE, 0x804040C0, 0x058F8F8A,
            0x3F9292AD, 0x219D9DBC, 0x70383848, 0xF1F5F504,
            0x63BCBCDF, 0x77B6B6C1, 0xAFDADA75, 0x42212163,
            0x20101030, 0xE5FFFF1A, 0xFDF3F30E, 0xBFD2D26D,
            0x81CDCD4C, 0x180C0C14, 0x26131335, 0xC3ECEC2F,
            0xBE5F5FE1, 0x359797A2, 0x884444CC, 0x2E171739,
            0x93C4C457, 0x55A7A7F2, 0xFC7E7E82, 0x7A3D3D47,
            0xC86464AC, 0xBA5D5DE7, 0x3219192B, 0xE6737395,
            0xC06060A0, 0x19818198, 0x9E4F4FD1, 0xA3DCDC7F,
            0x44222266, 0x542A2A7E, 0x3B9090AB, 0x0B888883,
            0x8C4646CA, 0xC7EEEE29, 0x6BB8B8D3, 0x2814143C,
            0xA7DEDE79, 0xBC5E5EE2, 0x160B0B1D, 0xADDBDB76,
            0xDBE0E03B, 0x64323256, 0x743A3A4E, 0x140A0A1E,
            0x924949DB, 0x0C06060A, 0x4824246C, 0xB85C5CE4,
            0x9FC2C25D, 0xBDD3D36E, 0x43ACACEF, 0xC46262A6,
            0x399191A8, 0x319595A4, 0xD3E4E437, 0xF279798B,
            0xD5E7E732, 0x8BC8C843, 0x6E373759, 0xDA6D6DB7,
            0x018D8D8C, 0xB1D5D564, 0x9C4E4ED2, 0x49A9A9E0,
            0xD86C6CB4, 0xAC5656FA, 0xF3F4F407, 0xCFEAEA25,
            0xCA6565AF, 0xF47A7A8E, 0x47AEAEE9, 0x10080818,
            0x6FBABAD5, 0xF0787888, 0x4A25256F, 0x5C2E2E72,
            0x381C1C24, 0x57A6A6F1, 0x73B4B4C7, 0x97C6C651,
            0xCBE8E823, 0xA1DDDD7C, 0xE874749C, 0x3E1F1F21,
            0x964B4BDD, 0x61BDBDDC, 0x0D8B8B86, 0x0F8A8A85,
            0xE0707090, 0x7C3E3E42, 0x71B5B5C4, 0xCC6666AA,
            0x904848D8, 0x06030305, 0xF7F6F601, 0x1C0E0E12,
            0xC26161A3, 0x6A35355F, 0xAE5757F9, 0x69B9B9D0,
            0x17868691, 0x99C1C158, 0x3A1D1D27, 0x279E9EB9,
            0xD9E1E138, 0xEBF8F813, 0x2B9898B3, 0x22111133,
            0xD26969BB, 0xA9D9D970, 0x078E8E89, 0x339494A7,
            0x2D9B9BB6, 0x3C1E1E22, 0x15878792, 0xC9E9E920,
            0x87CECE49, 0xAA5555FF, 0x50282878, 0xA5DFDF7A,
            0x038C8C8F, 0x59A1A1F8, 0x09898980, 0x1A0D0D17,
            0x65BFBFDA, 0xD7E6E631, 0x844242C6, 0xD06868B8,
            0x824141C3, 0x299999B0, 0x5A2D2D77, 0x1E0F0F11,
            0x7BB0B0CB, 0xA85454FC, 0x6DBBBBD6, 0x2C16163A,
    };

    private static final int[] T2 = {
            0xA5C66363, 0x84F87C7C, 0x99EE7777, 0x8DF67B7B,
            0x0DFFF2F2, 0xBDD66B6B, 0xB1DE6F6F, 0x5491C5C5,
            0x50603030, 0x03020101, 0xA9CE6767, 0x7D562B2B,
            0x19E7FEFE, 0x62B5D7D7, 0xE64DABAB, 0x9AEC7676,
            0x458FCACA, 0x9D1F8282, 0x4089C9C9, 0x87FA7D7D,
            0x15EFFAFA, 0xEBB25959, 0xC98E4747, 0x0BFBF0F0,
            0xEC41ADAD, 0x67B3D4D4, 0xFD5FA2A2, 0xEA45AFAF,
            0xBF239C9C, 0xF753A4A4, 0x96E47272, 0x5B9BC0C0,
            0xC275B7B7, 0x1CE1FDFD, 0xAE3D9393, 0x6A4C2626,
            0x5A6C3636, 0x417E3F3F, 0x02F5F7F7, 0x4F83CCCC,
            0x5C683434, 0xF451A5A5, 0x34D1E5E5, 0x08F9F1F1,
            0x93E27171, 0x73ABD8D8, 0x53623131, 0x3F2A1515,
            0x0C080404, 0x5295C7C7, 0x65462323, 0x5E9DC3C3,
            0x28301818, 0xA1379696, 0x0F0A0505, 0xB52F9A9A,
            0x090E0707, 0x36241212, 0x9B1B8080, 0x3DDFE2E2,
            0x26CDEBEB, 0x694E2727, 0xCD7FB2B2, 0x9FEA7575,
            0x1B120909, 0x9E1D8383, 0x74582C2C, 0x2E341A1A,
            0x2D361B1B, 0xB2DC6E6E, 0xEEB45A5A, 0xFB5BA0A0,
            0xF6A45252, 0x4D763B3B, 0x61B7D6D6, 0xCE7DB3B3,
            0x7B522929, 0x3EDDE3E3, 0x715E2F2F, 0x97138484,
            0xF5A65353, 0x68B9D1D1, 0x00000000, 0x2CC1EDED,
            0x60402020, 0x1FE3FCFC, 0xC879B1B1, 0xEDB65B5B,
            0xBED46A6A, 0x468DCBCB, 0xD967BEBE, 0x4B723939,
            0xDE944A4A, 0xD4984C4C, 0xE8B05858, 0x4A85CFCF,
            0x6BBBD0D0, 0x2AC5EFEF, 0xE54FAAAA, 0x16EDFBFB,
            0xC5864343, 0xD79A4D4D, 0x55663333, 0x94118585,
            0xCF8A4545, 0x10E9F9F9, 0x06040202, 0x81FE7F7F,
            0xF0A05050, 0x44783C3C, 0xBA259F9F, 0xE34BA8A8,
            0xF3A25151, 0xFE5DA3A3, 0xC0804040, 0x8A058F8F,
            0xAD3F9292, 0xBC219D9D, 0x48703838, 0x04F1F5F5,
            0xDF63BCBC, 0xC177B6B6, 0x75AFDADA, 0x63422121,
            0x30201010, 0x1AE5FFFF, 0x0EFDF3F3, 0x6DBFD2D2,
            0x4C81CDCD, 0x14180C0C, 0x35261313, 0x2FC3ECEC,
            0xE1BE5F5F, 0xA2359797, 0xCC884444, 0x392E1717,
            0x5793C4C4, 0xF255A7A7, 0x82FC7E7E, 0x477A3D3D,
            0xACC86464, 0xE7BA5D5D, 0x2B321919, 0x95E67373,
            0xA0C06060, 0x98198181, 0xD19E4F4F, 0x7FA3DCDC,
            0x66442222, 0x7E542A2A, 0xAB3B9090, 0x830B8888,
            0xCA8C4646, 0x29C7EEEE, 0xD36BB8B8, 0x3C281414,
            0x79A7DEDE, 0xE2BC5E5E, 0x1D160B0B, 0x76ADDBDB,
            0x3BDBE0E0, 0x56643232, 0x4E743A3A, 0x1E140A0A,
            0xDB924949, 0x0A0C0606, 0x6C482424, 0xE4B85C5C,
            0x5D9FC2C2, 0x6EBDD3D3, 0xEF43ACAC, 0xA6C46262,
            0xA8399191, 0xA4319595, 0x37D3E4E4, 0x8BF27979,
            0x32D5E7E7, 0x438BC8C8, 0x596E3737, 0xB7DA6D6D,
            0x8C018D8D, 0x64B1D5D5, 0xD29C4E4E, 0xE049A9A9,
            0xB4D86C6C, 0xFAAC5656, 0x07F3F4F4, 0x25CFEAEA,
            0xAFCA6565, 0x8EF47A7A, 0xE947AEAE, 0x18100808,
            0xD56FBABA, 0x88F07878, 0x6F4A2525, 0x725C2E2E,
            0x24381C1C, 0xF157A6A6, 0xC773B4B4, 0x5197C6C6,
            0x23CBE8E8, 0x7CA1DDDD, 0x9CE87474, 0x213E1F1F,
            0xDD964B4B, 0xDC61BDBD, 0x860D8B8B, 0x850F8A8A,
            0x90E07070, 0x427C3E3E, 0xC471B5B5, 0xAACC6666,
            0xD8904848, 0x05060303, 0x01F7F6F6, 0x121C0E0E,
            0xA3C26161, 0x5F6A3535, 0xF9AE5757, 0xD069B9B9,
            0x91178686, 0x5899C1C1, 0x273A1D1D, 0xB9279E9E,
            0x38D9E1E1, 0x13EBF8F8, 0xB32B9898, 0x33221111,
            0xBBD26969, 0x70A9D9D9, 0x89078E8E, 0xA7339494,
            0xB62D9B9B, 0x223C1E1E, 0x92158787, 0x20C9E9E9,
            0x4987CECE, 0xFFAA5555, 0x78502828, 0x7AA5DFDF,
            0x8F038C8C, 0xF859A1A1, 0x80098989, 0x171A0D0D,
            0xDA65BFBF, 0x31D7E6E6, 0xC6844242, 0xB8D06868,
            0xC3824141, 0xB0299999, 0x775A2D2D, 0x111E0F0F,
            0xCB7BB0B0, 0xFCA85454, 0xD66DBBBB, 0x3A2C1616,
    };

    private static final int[] T3 = {
            0x63A5C663, 0x7C84F87C, 0x7799EE77, 0x7B8DF67B,
            0xF20DFFF2, 0x6BBDD66B, 0x6FB1DE6F, 0xC55491C5,
            0x30506030, 0x01030201, 0x67A9CE67, 0x2B7D562B,
            0xFE19E7FE, 0xD762B5D7, 0xABE64DAB, 0x769AEC76,
            0xCA458FCA, 0x829D1F82, 0xC94089C9, 0x7D87FA7D,
            0xFA15EFFA, 0x59EBB259, 0x47C98E47, 0xF00BFBF0,
            0xADEC41AD, 0xD467B3D4, 0xA2FD5FA2, 0xAFEA45AF,
            0x9CBF239C, 0xA4F753A4, 0x7296E472, 0xC05B9BC0,
            0xB7C275B7, 0xFD1CE1FD, 0x93AE3D93, 0x266A4C26,
            0x365A6C36, 0x3F417E3F, 0xF702F5F7, 0xCC4F83CC,
            0x345C6834, 0xA5F451A5, 0xE534D1E5, 0xF108F9F1,
            0x7193E271, 0xD873ABD8, 0x31536231, 0x153F2A15,
            0x040C0804, 0xC75295C7, 0x23654623, 0xC35E9DC3,
            0x18283018, 0x96A13796, 0x050F0A05, 0x9AB52F9A,
            0x07090E07, 0x12362412, 0x809B1B80, 0xE23DDFE2,
            0xEB26CDEB, 0x27694E27, 0xB2CD7FB2, 0x759FEA75,
            0x091B1209, 0x839E1D83, 0x2C74582C, 0x1A2E341A,
            0x1B2D361B, 0x6EB2DC6E, 0x5AEEB45A, 0xA0FB5BA0,
            0x52F6A452, 0x3B4D763B, 0xD661B7D6, 0xB3CE7DB3,
            0x297B5229, 0xE33EDDE3, 0x2F715E2F, 0x84971384,
            0x53F5A653, 0xD168B9D1, 0x00000000, 0xED2CC1ED,
            0x20604020, 0xFC1FE3FC, 0xB1C879B1, 0x5BEDB65B,
            0x6ABED46A, 0xCB468DCB, 0xBED967BE, 0x394B7239,
            0x4ADE944A, 0x4CD4984C, 0x58E8B058, 0xCF4A85CF,
            0xD06BBBD0, 0xEF2AC5EF, 0xAAE54FAA, 0xFB16EDFB,
            0x43C58643, 0x4DD79A4D, 0x33556633, 0x85941185,
            0x45CF8A45, 0xF910E9F9, 0x02060402, 0x7F81FE7F,
            0x50F0A050, 0x3C44783C, 0x9FBA259F, 0xA8E34BA8,
            0x51F3A251, 0xA3FE5DA3, 0x40C08040, 0x8F8A058F,
            0x92AD3F92, 0x9DBC219D, 0x38487038, 0xF504F1F5,
            0xBCDF63BC, 0xB6C177B6, 0xDA75AFDA, 0x21634221,
            0x10302010, 0xFF1AE5FF, 0xF30EFDF3, 0xD26DBFD2,
            0xCD4C81CD, 0x0C14180C, 0x13352613, 0xEC2FC3EC,
            0x5FE1BE5F, 0x97A23597, 0x44CC8844, 0x17392E17,
            0xC45793C4, 0xA7F255A7, 0x7E82FC7E, 0x3D477A3D,
            0x64ACC864, 0x5DE7BA5D, 0x192B3219, 0x7395E673,
            0x60A0C060, 0x81981981, 0x4FD19E4F, 0xDC7FA3DC,
            0x22664422, 0x2A7E542A, 0x90AB3B90, 0x88830B88,
            0x46CA8C46, 0xEE29C7EE, 0xB8D36BB8, 0x143C2814,
            0xDE79A7DE, 0x5EE2BC5E, 0x0B1D160B, 0xDB76ADDB,
            0xE03BDBE0, 0x32566432, 0x3A4E743A, 0x0A1E140A,
            0x49DB9249, 0x060A0C06, 0x246C4824, 0x5CE4B85C,
            0xC25D9FC2, 0xD36EBDD3, 0xACEF43AC, 0x62A6C462,
            0x91A83991, 0x95A43195, 0xE437D3E4, 0x798BF279,
            0xE732D5E7, 0xC8438BC8, 0x37596E37, 0x6DB7DA6D,
            0x8D8C018D, 0xD564B1D5, 0x4ED29C4E, 0xA9E049A9,
            0x6CB4D86C, 0x56FAAC56, 0xF407F3F4, 0xEA25CFEA,
            0x65AFCA65, 0x7A8EF47A, 0xAEE947AE, 0x08181008,
            0xBAD56FBA, 0x7888F078, 0x256F4A25, 0x2E725C2E,
            0x1C24381C, 0xA6F157A6, 0xB4C773B4, 0xC65197C6,
            0xE823CBE8, 0xDD7CA1DD, 0x749CE874, 0x1F213E1F,
            0x4BDD964B, 0xBDDC61BD, 0x8B860D8B, 0x8A850F8A,
            0x7090E070, 0x3E427C3E, 0xB5C471B5, 0x66AACC66,
            0x48D89048, 0x03050603, 0xF601F7F6, 0x0E121C0E,
            0x61A3C261, 0x355F6A35, 0x57F9AE57, 0xB9D069B9,
            0x86911786, 0xC15899C1, 0x1D273A1D, 0x9EB9279E,
            0xE138D9E1, 0xF813EBF8, 0x98B32B98, 0x11332211,
            0x69BBD269, 0xD970A9D9, 0x8E89078E, 0x94A73394,
            0x9BB62D9B, 0x1E223C1E, 0x87921587, 0xE920C9E9,
            0xCE4987CE, 0x55FFAA55, 0x28785028, 0xDF7AA5DF,
            0x8C8F038C, 0xA1F859A1, 0x89800989, 0x0D171A0D,
            0xBFDA65BF, 0xE631D7E6, 0x42C68442, 0x68B8D068,
            0x41C38241, 0x99B02999, 0x2D775A2D, 0x0F111E0F,
            0xB0CB7BB0, 0x54FCA854, 0xBBD66DBB, 0x163A2C16,
    };

    private static final int[] T4 = {
            0x6363A5C6, 0x7C7C84F8, 0x777799EE, 0x7B7B8DF6,
            0xF2F20DFF, 0x6B6BBDD6, 0x6F6FB1DE, 0xC5C55491,
            0x30305060, 0x01010302, 0x6767A9CE, 0x2B2B7D56,
            0xFEFE19E7, 0xD7D762B5, 0xABABE64D, 0x76769AEC,
            0xCACA458F, 0x82829D1F, 0xC9C94089, 0x7D7D87FA,
            0xFAFA15EF, 0x5959EBB2, 0x4747C98E, 0xF0F00BFB,
            0xADADEC41, 0xD4D467B3, 0xA2A2FD5F, 0xAFAFEA45,
            0x9C9CBF23, 0xA4A4F753, 0x727296E4, 0xC0C05B9B,
            0xB7B7C275, 0xFDFD1CE1, 0x9393AE3D, 0x26266A4C,
            0x36365A6C, 0x3F3F417E, 0xF7F702F5, 0xCCCC4F83,
            0x34345C68, 0xA5A5F451, 0xE5E534D1, 0xF1F108F9,
            0x717193E2, 0xD8D873AB, 0x31315362, 0x15153F2A,
            0x04040C08, 0xC7C75295, 0x23236546, 0xC3C35E9D,
            0x18182830, 0x9696A137, 0x05050F0A, 0x9A9AB52F,
            0x0707090E, 0x12123624, 0x80809B1B, 0xE2E23DDF,
            0xEBEB26CD, 0x2727694E, 0xB2B2CD7F, 0x75759FEA,
            0x09091B12, 0x83839E1D, 0x2C2C7458, 0x1A1A2E34,
            0x1B1B2D36, 0x6E6EB2DC, 0x5A5AEEB4, 0xA0A0FB5B,
            0x5252F6A4, 0x3B3B4D76, 0xD6D661B7, 0xB3B3CE7D,
            0x29297B52, 0xE3E33EDD, 0x2F2F715E, 0x84849713,
            0x5353F5A6, 0xD1D168B9, 0x00000000, 0xEDED2CC1,
            0x20206040, 0xFCFC1FE3, 0xB1B1C879, 0x5B5BEDB6,
            0x6A6ABED4, 0xCBCB468D, 0xBEBED967, 0x39394B72,
            0x4A4ADE94, 0x4C4CD498, 0x5858E8B0, 0xCFCF4A85,
            0xD0D06BBB, 0xEFEF2AC5, 0xAAAAE54F, 0xFBFB16ED,
            0x4343C586, 0x4D4DD79A, 0x33335566, 0x85859411,
            0x4545CF8A, 0xF9F910E9, 0x02020604, 0x7F7F81FE,
            0x5050F0A0, 0x3C3C4478, 0x9F9FBA25, 0xA8A8E34B,
            0x5151F3A2, 0xA3A3FE5D, 0x4040C080, 0x8F8F8A05,
            0x9292AD3F, 0x9D9DBC21, 0x38384870, 0xF5F504F1,
            0xBCBCDF63, 0xB6B6C177, 0xDADA75AF, 0x21216342,
            0x10103020, 0xFFFF1AE5, 0xF3F30EFD, 0xD2D26DBF,
            0xCDCD4C81, 0x0C0C1418, 0x13133526, 0xECEC2FC3,
            0x5F5FE1BE, 0x9797A235, 0x4444CC88, 0x1717392E,
            0xC4C45793, 0xA7A7F255, 0x7E7E82FC, 0x3D3D477A,
            0x6464ACC8, 0x5D5DE7BA, 0x19192B32, 0x737395E6,
            0x6060A0C0, 0x81819819, 0x4F4FD19E, 0xDCDC7FA3,
            0x22226644, 0x2A2A7E54, 0x9090AB3B, 0x8888830B,
            0x4646CA8C, 0xEEEE29C7, 0xB8B8D36B, 0x14143C28,
            0xDEDE79A7, 0x5E5EE2BC, 0x0B0B1D16, 0xDBDB76AD,
            0xE0E03BDB, 0x32325664, 0x3A3A4E74, 0x0A0A1E14,
            0x4949DB92, 0x06060A0C, 0x24246C48, 0x5C5CE4B8,
            0xC2C25D9F, 0xD3D36EBD, 0xACACEF43, 0x6262A6C4,
            0x9191A839, 0x9595A431, 0xE4E437D3, 0x79798BF2,
            0xE7E732D5, 0xC8C8438B, 0x3737596E, 0x6D6DB7DA,
            0x8D8D8C01, 0xD5D564B1, 0x4E4ED29C, 0xA9A9E049,
            0x6C6CB4D8, 0x5656FAAC, 0xF4F407F3, 0xEAEA25CF,
            0x6565AFCA, 0x7A7A8EF4, 0xAEAEE947, 0x08081810,
            0xBABAD56F, 0x787888F0, 0x25256F4A, 0x2E2E725C,
            0x1C1C2438, 0xA6A6F157, 0xB4B4C773, 0xC6C65197,
            0xE8E823CB, 0xDDDD7CA1, 0x74749CE8, 0x1F1F213E,
            0x4B4BDD96, 0xBDBDDC61, 0x8B8B860D, 0x8A8A850F,
            0x707090E0, 0x3E3E427C, 0xB5B5C471, 0x6666AACC,
            0x4848D890, 0x03030506, 0xF6F601F7, 0x0E0E121C,
            0x6161A3C2, 0x35355F6A, 0x5757F9AE, 0xB9B9D069,
            0x86869117, 0xC1C15899, 0x1D1D273A, 0x9E9EB927,
            0xE1E138D9, 0xF8F813EB, 0x9898B32B, 0x11113322,
            0x6969BBD2, 0xD9D970A9, 0x8E8E8907, 0x9494A733,
            0x9B9BB62D, 0x1E1E223C, 0x87879215, 0xE9E920C9,
            0xCECE4987, 0x5555FFAA, 0x28287850, 0xDFDF7AA5,
            0x8C8C8F03, 0xA1A1F859, 0x89898009, 0x0D0D171A,
            0xBFBFDA65, 0xE6E631D7, 0x4242C684, 0x6868B8D0,
            0x4141C382, 0x9999B029, 0x2D2D775A, 0x0F0F111E,
            0xB0B0CB7B, 0x5454FCA8, 0xBBBBD66D, 0x16163A2C,
    };


    // pre-computed inverse tables (inverse T-box)
    private static final int[] T5 = {
            0x51F4A750, 0x7E416553, 0x1A17A4C3, 0x3A275E96,
            0x3BAB6BCB, 0x1F9D45F1, 0xACFA58AB, 0x4BE30393,
            0x2030FA55, 0xAD766DF6, 0x88CC7691, 0xF5024C25,
            0x4FE5D7FC, 0xC52ACBD7, 0x26354480, 0xB562A38F,
            0xDEB15A49, 0x25BA1B67, 0x45EA0E98, 0x5DFEC0E1,
            0xC32F7502, 0x814CF012, 0x8D4697A3, 0x6BD3F9C6,
            0x038F5FE7, 0x15929C95, 0xBF6D7AEB, 0x955259DA,
            0xD4BE832D, 0x587421D3, 0x49E06929, 0x8EC9C844,
            0x75C2896A, 0xF48E7978, 0x99583E6B, 0x27B971DD,
            0xBEE14FB6, 0xF088AD17, 0xC920AC66, 0x7DCE3AB4,
            0x63DF4A18, 0xE51A3182, 0x97513360, 0x62537F45,
            0xB16477E0, 0xBB6BAE84, 0xFE81A01C, 0xF9082B94,
            0x70486858, 0x8F45FD19, 0x94DE6C87, 0x527BF8B7,
            0xAB73D323, 0x724B02E2, 0xE31F8F57, 0x6655AB2A,
            0xB2EB2807, 0x2FB5C203, 0x86C57B9A, 0xD33708A5,
            0x302887F2, 0x23BFA5B2, 0x02036ABA, 0xED16825C,
            0x8ACF1C2B, 0xA779B492, 0xF307F2F0, 0x4E69E2A1,
            0x65DAF4CD, 0x0605BED5, 0xD134621F, 0xC4A6FE8A,
            0x342E539D, 0xA2F355A0, 0x058AE132, 0xA4F6EB75,
            0x0B83EC39, 0x4060EFAA, 0x5E719F06, 0xBD6E1051,
            0x3E218AF9, 0x96DD063D, 0xDD3E05AE, 0x4DE6BD46,
            0x91548DB5, 0x71C45D05, 0x0406D46F, 0x605015FF,
            0x1998FB24, 0xD6BDE997, 0x894043CC, 0x67D99E77,
            0xB0E842BD, 0x07898B88, 0xE7195B38, 0x79C8EEDB,
            0xA17C0A47, 0x7C420FE9, 0xF8841EC9, 0x00000000,
            0x09808683, 0x322BED48, 0x1E1170AC, 0x6C5A724E,
            0xFD0EFFFB, 0x0F853856, 0x3DAED51E, 0x362D3927,
            0x0A0FD964, 0x685CA621, 0x9B5B54D1, 0x24362E3A,
            0x0C0A67B1, 0x9357E70F, 0xB4EE96D2, 0x1B9B919E,
            0x80C0C54F, 0x61DC20A2, 0x5A774B69, 0x1C121A16,
            0xE293BA0A, 0xC0A02AE5, 0x3C22E043, 0x121B171D,
            0x0E090D0B, 0xF28BC7AD, 0x2DB6A8B9, 0x141EA9C8,
            0x57F11985, 0xAF75074C, 0xEE99DDBB, 0xA37F60FD,
            0xF701269F, 0x5C72F5BC, 0x44663BC5, 0x5BFB7E34,
            0x8B432976, 0xCB23C6DC, 0xB6EDFC68, 0xB8E4F163,
            0xD731DCCA, 0x42638510, 0x13972240, 0x84C61120,
            0x854A247D, 0xD2BB3DF8, 0xAEF93211, 0xC729A16D,
            0x1D9E2F4B, 0xDCB230F3, 0x0D8652EC, 0x77C1E3D0,
            0x2BB3166C, 0xA970B999, 0x119448FA, 0x47E96422,
            0xA8FC8CC4, 0xA0F03F1A, 0x567D2CD8, 0x223390EF,
            0x87494EC7, 0xD938D1C1, 0x8CCAA2FE, 0x98D40B36,
            0xA6F581CF, 0xA57ADE28, 0xDAB78E26, 0x3FADBFA4,
            0x2C3A9DE4, 0x5078920D, 0x6A5FCC9B, 0x547E4662,
            0xF68D13C2, 0x90D8B8E8, 0x2E39F75E, 0x82C3AFF5,
            0x9F5D80BE, 0x69D0937C, 0x6FD52DA9, 0xCF2512B3,
            0xC8AC993B, 0x10187DA7, 0xE89C636E, 0xDB3BBB7B,
            0xCD267809, 0x6E5918F4, 0xEC9AB701, 0x834F9AA8,
            0xE6956E65, 0xAAFFE67E, 0x21BCCF08, 0xEF15E8E6,
            0xBAE79BD9, 0x4A6F36CE, 0xEA9F09D4, 0x29B07CD6,
            0x31A4B2AF, 0x2A3F2331, 0xC6A59430, 0x35A266C0,
            0x744EBC37, 0xFC82CAA6, 0xE090D0B0, 0x33A7D815,
            0xF104984A, 0x41ECDAF7, 0x7FCD500E, 0x1791F62F,
            0x764DD68D, 0x43EFB04D, 0xCCAA4D54, 0xE49604DF,
            0x9ED1B5E3, 0x4C6A881B, 0xC12C1FB8, 0x4665517F,
            0x9D5EEA04, 0x018C355D, 0xFA877473, 0xFB0B412E,
            0xB3671D5A, 0x92DBD252, 0xE9105633, 0x6DD64713,
            0x9AD7618C, 0x37A10C7A, 0x59F8148E, 0xEB133C89,
            0xCEA927EE, 0xB761C935, 0xE11CE5ED, 0x7A47B13C,
            0x9CD2DF59, 0x55F2733F, 0x1814CE79, 0x73C737BF,
            0x53F7CDEA, 0x5FFDAA5B, 0xDF3D6F14, 0x7844DB86,
            0xCAAFF381, 0xB968C43E, 0x3824342C, 0xC2A3405F,
            0x161DC372, 0xBCE2250C, 0x283C498B, 0xFF0D9541,
            0x39A80171, 0x080CB3DE, 0xD8B4E49C, 0x6456C190,
            0x7BCB8461, 0xD532B670, 0x486C5C74, 0xD0B85742,
    };

    private static final int[] T6 = {
            0x5051F4A7, 0x537E4165, 0xC31A17A4, 0x963A275E,
            0xCB3BAB6B, 0xF11F9D45, 0xABACFA58, 0x934BE303,
            0x552030FA, 0xF6AD766D, 0x9188CC76, 0x25F5024C,
            0xFC4FE5D7, 0xD7C52ACB, 0x80263544, 0x8FB562A3,
            0x49DEB15A, 0x6725BA1B, 0x9845EA0E, 0xE15DFEC0,
            0x02C32F75, 0x12814CF0, 0xA38D4697, 0xC66BD3F9,
            0xE7038F5F, 0x9515929C, 0xEBBF6D7A, 0xDA955259,
            0x2DD4BE83, 0xD3587421, 0x2949E069, 0x448EC9C8,
            0x6A75C289, 0x78F48E79, 0x6B99583E, 0xDD27B971,
            0xB6BEE14F, 0x17F088AD, 0x66C920AC, 0xB47DCE3A,
            0x1863DF4A, 0x82E51A31, 0x60975133, 0x4562537F,
            0xE0B16477, 0x84BB6BAE, 0x1CFE81A0, 0x94F9082B,
            0x58704868, 0x198F45FD, 0x8794DE6C, 0xB7527BF8,
            0x23AB73D3, 0xE2724B02, 0x57E31F8F, 0x2A6655AB,
            0x07B2EB28, 0x032FB5C2, 0x9A86C57B, 0xA5D33708,
            0xF2302887, 0xB223BFA5, 0xBA02036A, 0x5CED1682,
            0x2B8ACF1C, 0x92A779B4, 0xF0F307F2, 0xA14E69E2,
            0xCD65DAF4, 0xD50605BE, 0x1FD13462, 0x8AC4A6FE,
            0x9D342E53, 0xA0A2F355, 0x32058AE1, 0x75A4F6EB,
            0x390B83EC, 0xAA4060EF, 0x065E719F, 0x51BD6E10,
            0xF93E218A, 0x3D96DD06, 0xAEDD3E05, 0x464DE6BD,
            0xB591548D, 0x0571C45D, 0x6F0406D4, 0xFF605015,
            0x241998FB, 0x97D6BDE9, 0xCC894043, 0x7767D99E,
            0xBDB0E842, 0x8807898B, 0x38E7195B, 0xDB79C8EE,
            0x47A17C0A, 0xE97C420F, 0xC9F8841E, 0x00000000,
            0x83098086, 0x48322BED, 0xAC1E1170, 0x4E6C5A72,
            0xFBFD0EFF, 0x560F8538, 0x1E3DAED5, 0x27362D39,
            0x640A0FD9, 0x21685CA6, 0xD19B5B54, 0x3A24362E,
            0xB10C0A67, 0x0F9357E7, 0xD2B4EE96, 0x9E1B9B91,
            0x4F80C0C5, 0xA261DC20, 0x695A774B, 0x161C121A,
            0x0AE293BA, 0xE5C0A02A, 0x433C22E0, 0x1D121B17,
            0x0B0E090D, 0xADF28BC7, 0xB92DB6A8, 0xC8141EA9,
            0x8557F119, 0x4CAF7507, 0xBBEE99DD, 0xFDA37F60,
            0x9FF70126, 0xBC5C72F5, 0xC544663B, 0x345BFB7E,
            0x768B4329, 0xDCCB23C6, 0x68B6EDFC, 0x63B8E4F1,
            0xCAD731DC, 0x10426385, 0x40139722, 0x2084C611,
            0x7D854A24, 0xF8D2BB3D, 0x11AEF932, 0x6DC729A1,
            0x4B1D9E2F, 0xF3DCB230, 0xEC0D8652, 0xD077C1E3,
            0x6C2BB316, 0x99A970B9, 0xFA119448, 0x2247E964,
            0xC4A8FC8C, 0x1AA0F03F, 0xD8567D2C, 0xEF223390,
            0xC787494E, 0xC1D938D1, 0xFE8CCAA2, 0x3698D40B,
            0xCFA6F581, 0x28A57ADE, 0x26DAB78E, 0xA43FADBF,
            0xE42C3A9D, 0x0D507892, 0x9B6A5FCC, 0x62547E46,
            0xC2F68D13, 0xE890D8B8, 0x5E2E39F7, 0xF582C3AF,
            0xBE9F5D80, 0x7C69D093, 0xA96FD52D, 0xB3CF2512,
            0x3BC8AC99, 0xA710187D, 0x6EE89C63, 0x7BDB3BBB,
            0x09CD2678, 0xF46E5918, 0x01EC9AB7, 0xA8834F9A,
            0x65E6956E, 0x7EAAFFE6, 0x0821BCCF, 0xE6EF15E8,
            0xD9BAE79B, 0xCE4A6F36, 0xD4EA9F09, 0xD629B07C,
            0xAF31A4B2, 0x312A3F23, 0x30C6A594, 0xC035A266,
            0x37744EBC, 0xA6FC82CA, 0xB0E090D0, 0x1533A7D8,
            0x4AF10498, 0xF741ECDA, 0x0E7FCD50, 0x2F1791F6,
            0x8D764DD6, 0x4D43EFB0, 0x54CCAA4D, 0xDFE49604,
            0xE39ED1B5, 0x1B4C6A88, 0xB8C12C1F, 0x7F466551,
            0x049D5EEA, 0x5D018C35, 0x73FA8774, 0x2EFB0B41,
            0x5AB3671D, 0x5292DBD2, 0x33E91056, 0x136DD647,
            0x8C9AD761, 0x7A37A10C, 0x8E59F814, 0x89EB133C,
            0xEECEA927, 0x35B761C9, 0xEDE11CE5, 0x3C7A47B1,
            0x599CD2DF, 0x3F55F273, 0x791814CE, 0xBF73C737,
            0xEA53F7CD, 0x5B5FFDAA, 0x14DF3D6F, 0x867844DB,
            0x81CAAFF3, 0x3EB968C4, 0x2C382434, 0x5FC2A340,
            0x72161DC3, 0x0CBCE225, 0x8B283C49, 0x41FF0D95,
            0x7139A801, 0xDE080CB3, 0x9CD8B4E4, 0x906456C1,
            0x617BCB84, 0x70D532B6, 0x74486C5C, 0x42D0B857,
    };

    private static final int[] T7 = {
            0xA75051F4, 0x65537E41, 0xA4C31A17, 0x5E963A27,
            0x6BCB3BAB, 0x45F11F9D, 0x58ABACFA, 0x03934BE3,
            0xFA552030, 0x6DF6AD76, 0x769188CC, 0x4C25F502,
            0xD7FC4FE5, 0xCBD7C52A, 0x44802635, 0xA38FB562,
            0x5A49DEB1, 0x1B6725BA, 0x0E9845EA, 0xC0E15DFE,
            0x7502C32F, 0xF012814C, 0x97A38D46, 0xF9C66BD3,
            0x5FE7038F, 0x9C951592, 0x7AEBBF6D, 0x59DA9552,
            0x832DD4BE, 0x21D35874, 0x692949E0, 0xC8448EC9,
            0x896A75C2, 0x7978F48E, 0x3E6B9958, 0x71DD27B9,
            0x4FB6BEE1, 0xAD17F088, 0xAC66C920, 0x3AB47DCE,
            0x4A1863DF, 0x3182E51A, 0x33609751, 0x7F456253,
            0x77E0B164, 0xAE84BB6B, 0xA01CFE81, 0x2B94F908,
            0x68587048, 0xFD198F45, 0x6C8794DE, 0xF8B7527B,
            0xD323AB73, 0x02E2724B, 0x8F57E31F, 0xAB2A6655,
            0x2807B2EB, 0xC2032FB5, 0x7B9A86C5, 0x08A5D337,
            0x87F23028, 0xA5B223BF, 0x6ABA0203, 0x825CED16,
            0x1C2B8ACF, 0xB492A779, 0xF2F0F307, 0xE2A14E69,
            0xF4CD65DA, 0xBED50605, 0x621FD134, 0xFE8AC4A6,
            0x539D342E, 0x55A0A2F3, 0xE132058A, 0xEB75A4F6,
            0xEC390B83, 0xEFAA4060, 0x9F065E71, 0x1051BD6E,
            0x8AF93E21, 0x063D96DD, 0x05AEDD3E, 0xBD464DE6,
            0x8DB59154, 0x5D0571C4, 0xD46F0406, 0x15FF6050,
            0xFB241998, 0xE997D6BD, 0x43CC8940, 0x9E7767D9,
            0x42BDB0E8, 0x8B880789, 0x5B38E719, 0xEEDB79C8,
            0x0A47A17C, 0x0FE97C42, 0x1EC9F884, 0x00000000,
            0x86830980, 0xED48322B, 0x70AC1E11, 0x724E6C5A,
            0xFFFBFD0E, 0x38560F85, 0xD51E3DAE, 0x3927362D,
            0xD9640A0F, 0xA621685C, 0x54D19B5B, 0x2E3A2436,
            0x67B10C0A, 0xE70F9357, 0x96D2B4EE, 0x919E1B9B,
            0xC54F80C0, 0x20A261DC, 0x4B695A77, 0x1A161C12,
            0xBA0AE293, 0x2AE5C0A0, 0xE0433C22, 0x171D121B,
            0x0D0B0E09, 0xC7ADF28B, 0xA8B92DB6, 0xA9C8141E,
            0x198557F1, 0x074CAF75, 0xDDBBEE99, 0x60FDA37F,
            0x269FF701, 0xF5BC5C72, 0x3BC54466, 0x7E345BFB,
            0x29768B43, 0xC6DCCB23, 0xFC68B6ED, 0xF163B8E4,
            0xDCCAD731, 0x85104263, 0x22401397, 0x112084C6,
            0x247D854A, 0x3DF8D2BB, 0x3211AEF9, 0xA16DC729,
            0x2F4B1D9E, 0x30F3DCB2, 0x52EC0D86, 0xE3D077C1,
            0x166C2BB3, 0xB999A970, 0x48FA1194, 0x642247E9,
            0x8CC4A8FC, 0x3F1AA0F0, 0x2CD8567D, 0x90EF2233,
            0x4EC78749, 0xD1C1D938, 0xA2FE8CCA, 0x0B3698D4,
            0x81CFA6F5, 0xDE28A57A, 0x8E26DAB7, 0xBFA43FAD,
            0x9DE42C3A, 0x920D5078, 0xCC9B6A5F, 0x4662547E,
            0x13C2F68D, 0xB8E890D8, 0xF75E2E39, 0xAFF582C3,
            0x80BE9F5D, 0x937C69D0, 0x2DA96FD5, 0x12B3CF25,
            0x993BC8AC, 0x7DA71018, 0x636EE89C, 0xBB7BDB3B,
            0x7809CD26, 0x18F46E59, 0xB701EC9A, 0x9AA8834F,
            0x6E65E695, 0xE67EAAFF, 0xCF0821BC, 0xE8E6EF15,
            0x9BD9BAE7, 0x36CE4A6F, 0x09D4EA9F, 0x7CD629B0,
            0xB2AF31A4, 0x23312A3F, 0x9430C6A5, 0x66C035A2,
            0xBC37744E, 0xCAA6FC82, 0xD0B0E090, 0xD81533A7,
            0x984AF104, 0xDAF741EC, 0x500E7FCD, 0xF62F1791,
            0xD68D764D, 0xB04D43EF, 0x4D54CCAA, 0x04DFE496,
            0xB5E39ED1, 0x881B4C6A, 0x1FB8C12C, 0x517F4665,
            0xEA049D5E, 0x355D018C, 0x7473FA87, 0x412EFB0B,
            0x1D5AB367, 0xD25292DB, 0x5633E910, 0x47136DD6,
            0x618C9AD7, 0x0C7A37A1, 0x148E59F8, 0x3C89EB13,
            0x27EECEA9, 0xC935B761, 0xE5EDE11C, 0xB13C7A47,
            0xDF599CD2, 0x733F55F2, 0xCE791814, 0x37BF73C7,
            0xCDEA53F7, 0xAA5B5FFD, 0x6F14DF3D, 0xDB867844,
            0xF381CAAF, 0xC43EB968, 0x342C3824, 0x405FC2A3,
            0xC372161D, 0x250CBCE2, 0x498B283C, 0x9541FF0D,
            0x017139A8, 0xB3DE080C, 0xE49CD8B4, 0xC1906456,
            0x84617BCB, 0xB670D532, 0x5C74486C, 0x5742D0B8,
    };

    private static final int[] T8 = {
            0xF4A75051, 0x4165537E, 0x17A4C31A, 0x275E963A,
            0xAB6BCB3B, 0x9D45F11F, 0xFA58ABAC, 0xE303934B,
            0x30FA5520, 0x766DF6AD, 0xCC769188, 0x024C25F5,
            0xE5D7FC4F, 0x2ACBD7C5, 0x35448026, 0x62A38FB5,
            0xB15A49DE, 0xBA1B6725, 0xEA0E9845, 0xFEC0E15D,
            0x2F7502C3, 0x4CF01281, 0x4697A38D, 0xD3F9C66B,
            0x8F5FE703, 0x929C9515, 0x6D7AEBBF, 0x5259DA95,
            0xBE832DD4, 0x7421D358, 0xE0692949, 0xC9C8448E,
            0xC2896A75, 0x8E7978F4, 0x583E6B99, 0xB971DD27,
            0xE14FB6BE, 0x88AD17F0, 0x20AC66C9, 0xCE3AB47D,
            0xDF4A1863, 0x1A3182E5, 0x51336097, 0x537F4562,
            0x6477E0B1, 0x6BAE84BB, 0x81A01CFE, 0x082B94F9,
            0x48685870, 0x45FD198F, 0xDE6C8794, 0x7BF8B752,
            0x73D323AB, 0x4B02E272, 0x1F8F57E3, 0x55AB2A66,
            0xEB2807B2, 0xB5C2032F, 0xC57B9A86, 0x3708A5D3,
            0x2887F230, 0xBFA5B223, 0x036ABA02, 0x16825CED,
            0xCF1C2B8A, 0x79B492A7, 0x07F2F0F3, 0x69E2A14E,
            0xDAF4CD65, 0x05BED506, 0x34621FD1, 0xA6FE8AC4,
            0x2E539D34, 0xF355A0A2, 0x8AE13205, 0xF6EB75A4,
            0x83EC390B, 0x60EFAA40, 0x719F065E, 0x6E1051BD,
            0x218AF93E, 0xDD063D96, 0x3E05AEDD, 0xE6BD464D,
            0x548DB591, 0xC45D0571, 0x06D46F04, 0x5015FF60,
            0x98FB2419, 0xBDE997D6, 0x4043CC89, 0xD99E7767,
            0xE842BDB0, 0x898B8807, 0x195B38E7, 0xC8EEDB79,
            0x7C0A47A1, 0x420FE97C, 0x841EC9F8, 0x00000000,
            0x80868309, 0x2BED4832, 0x1170AC1E, 0x5A724E6C,
            0x0EFFFBFD, 0x8538560F, 0xAED51E3D, 0x2D392736,
            0x0FD9640A, 0x5CA62168, 0x5B54D19B, 0x362E3A24,
            0x0A67B10C, 0x57E70F93, 0xEE96D2B4, 0x9B919E1B,
            0xC0C54F80, 0xDC20A261, 0x774B695A, 0x121A161C,
            0x93BA0AE2, 0xA02AE5C0, 0x22E0433C, 0x1B171D12,
            0x090D0B0E, 0x8BC7ADF2, 0xB6A8B92D, 0x1EA9C814,
            0xF1198557, 0x75074CAF, 0x99DDBBEE, 0x7F60FDA3,
            0x01269FF7, 0x72F5BC5C, 0x663BC544, 0xFB7E345B,
            0x4329768B, 0x23C6DCCB, 0xEDFC68B6, 0xE4F163B8,
            0x31DCCAD7, 0x63851042, 0x97224013, 0xC6112084,
            0x4A247D85, 0xBB3DF8D2, 0xF93211AE, 0x29A16DC7,
            0x9E2F4B1D, 0xB230F3DC, 0x8652EC0D, 0xC1E3D077,
            0xB3166C2B, 0x70B999A9, 0x9448FA11, 0xE9642247,
            0xFC8CC4A8, 0xF03F1AA0, 0x7D2CD856, 0x3390EF22,
            0x494EC787, 0x38D1C1D9, 0xCAA2FE8C, 0xD40B3698,
            0xF581CFA6, 0x7ADE28A5, 0xB78E26DA, 0xADBFA43F,
            0x3A9DE42C, 0x78920D50, 0x5FCC9B6A, 0x7E466254,
            0x8D13C2F6, 0xD8B8E890, 0x39F75E2E, 0xC3AFF582,
            0x5D80BE9F, 0xD0937C69, 0xD52DA96F, 0x2512B3CF,
            0xAC993BC8, 0x187DA710, 0x9C636EE8, 0x3BBB7BDB,
            0x267809CD, 0x5918F46E, 0x9AB701EC, 0x4F9AA883,
            0x956E65E6, 0xFFE67EAA, 0xBCCF0821, 0x15E8E6EF,
            0xE79BD9BA, 0x6F36CE4A, 0x9F09D4EA, 0xB07CD629,
            0xA4B2AF31, 0x3F23312A, 0xA59430C6, 0xA266C035,
            0x4EBC3774, 0x82CAA6FC, 0x90D0B0E0, 0xA7D81533,
            0x04984AF1, 0xECDAF741, 0xCD500E7F, 0x91F62F17,
            0x4DD68D76, 0xEFB04D43, 0xAA4D54CC, 0x9604DFE4,
            0xD1B5E39E, 0x6A881B4C, 0x2C1FB8C1, 0x65517F46,
            0x5EEA049D, 0x8C355D01, 0x877473FA, 0x0B412EFB,
            0x671D5AB3, 0xDBD25292, 0x105633E9, 0xD647136D,
            0xD7618C9A, 0xA10C7A37, 0xF8148E59, 0x133C89EB,
            0xA927EECE, 0x61C935B7, 0x1CE5EDE1, 0x47B13C7A,
            0xD2DF599C, 0xF2733F55, 0x14CE7918, 0xC737BF73,
            0xF7CDEA53, 0xFDAA5B5F, 0x3D6F14DF, 0x44DB8678,
            0xAFF381CA, 0x68C43EB9, 0x24342C38, 0xA3405FC2,
            0x1DC37216, 0xE2250CBC, 0x3C498B28, 0x0D9541FF,
            0xA8017139, 0x0CB3DE08, 0xB4E49CD8, 0x56C19064,
            0xCB84617B, 0x32B670D5, 0x6C5C7448, 0xB85742D0,
    };

    private static final int[] U1 = {
            0x00000000, 0x0E090D0B, 0x1C121A16, 0x121B171D,
            0x3824342C, 0x362D3927, 0x24362E3A, 0x2A3F2331,
            0x70486858, 0x7E416553, 0x6C5A724E, 0x62537F45,
            0x486C5C74, 0x4665517F, 0x547E4662, 0x5A774B69,
            0xE090D0B0, 0xEE99DDBB, 0xFC82CAA6, 0xF28BC7AD,
            0xD8B4E49C, 0xD6BDE997, 0xC4A6FE8A, 0xCAAFF381,
            0x90D8B8E8, 0x9ED1B5E3, 0x8CCAA2FE, 0x82C3AFF5,
            0xA8FC8CC4, 0xA6F581CF, 0xB4EE96D2, 0xBAE79BD9,
            0xDB3BBB7B, 0xD532B670, 0xC729A16D, 0xC920AC66,
            0xE31F8F57, 0xED16825C, 0xFF0D9541, 0xF104984A,
            0xAB73D323, 0xA57ADE28, 0xB761C935, 0xB968C43E,
            0x9357E70F, 0x9D5EEA04, 0x8F45FD19, 0x814CF012,
            0x3BAB6BCB, 0x35A266C0, 0x27B971DD, 0x29B07CD6,
            0x038F5FE7, 0x0D8652EC, 0x1F9D45F1, 0x119448FA,
            0x4BE30393, 0x45EA0E98, 0x57F11985, 0x59F8148E,
            0x73C737BF, 0x7DCE3AB4, 0x6FD52DA9, 0x61DC20A2,
            0xAD766DF6, 0xA37F60FD, 0xB16477E0, 0xBF6D7AEB,
            0x955259DA, 0x9B5B54D1, 0x894043CC, 0x87494EC7,
            0xDD3E05AE, 0xD33708A5, 0xC12C1FB8, 0xCF2512B3,
            0xE51A3182, 0xEB133C89, 0xF9082B94, 0xF701269F,
            0x4DE6BD46, 0x43EFB04D, 0x51F4A750, 0x5FFDAA5B,
            0x75C2896A, 0x7BCB8461, 0x69D0937C, 0x67D99E77,
            0x3DAED51E, 0x33A7D815, 0x21BCCF08, 0x2FB5C203,
            0x058AE132, 0x0B83EC39, 0x1998FB24, 0x1791F62F,
            0x764DD68D, 0x7844DB86, 0x6A5FCC9B, 0x6456C190,
            0x4E69E2A1, 0x4060EFAA, 0x527BF8B7, 0x5C72F5BC,
            0x0605BED5, 0x080CB3DE, 0x1A17A4C3, 0x141EA9C8,
            0x3E218AF9, 0x302887F2, 0x223390EF, 0x2C3A9DE4,
            0x96DD063D, 0x98D40B36, 0x8ACF1C2B, 0x84C61120,
            0xAEF93211, 0xA0F03F1A, 0xB2EB2807, 0xBCE2250C,
            0xE6956E65, 0xE89C636E, 0xFA877473, 0xF48E7978,
            0xDEB15A49, 0xD0B85742, 0xC2A3405F, 0xCCAA4D54,
            0x41ECDAF7, 0x4FE5D7FC, 0x5DFEC0E1, 0x53F7CDEA,
            0x79C8EEDB, 0x77C1E3D0, 0x65DAF4CD, 0x6BD3F9C6,
            0x31A4B2AF, 0x3FADBFA4, 0x2DB6A8B9, 0x23BFA5B2,
            0x09808683, 0x07898B88, 0x15929C95, 0x1B9B919E,
            0xA17C0A47, 0xAF75074C, 0xBD6E1051, 0xB3671D5A,
            0x99583E6B, 0x97513360, 0x854A247D, 0x8B432976,
            0xD134621F, 0xDF3D6F14, 0xCD267809, 0xC32F7502,
            0xE9105633, 0xE7195B38, 0xF5024C25, 0xFB0B412E,
            0x9AD7618C, 0x94DE6C87, 0x86C57B9A, 0x88CC7691,
            0xA2F355A0, 0xACFA58AB, 0xBEE14FB6, 0xB0E842BD,
            0xEA9F09D4, 0xE49604DF, 0xF68D13C2, 0xF8841EC9,
            0xD2BB3DF8, 0xDCB230F3, 0xCEA927EE, 0xC0A02AE5,
            0x7A47B13C, 0x744EBC37, 0x6655AB2A, 0x685CA621,
            0x42638510, 0x4C6A881B, 0x5E719F06, 0x5078920D,
            0x0A0FD964, 0x0406D46F, 0x161DC372, 0x1814CE79,
            0x322BED48, 0x3C22E043, 0x2E39F75E, 0x2030FA55,
            0xEC9AB701, 0xE293BA0A, 0xF088AD17, 0xFE81A01C,
            0xD4BE832D, 0xDAB78E26, 0xC8AC993B, 0xC6A59430,
            0x9CD2DF59, 0x92DBD252, 0x80C0C54F, 0x8EC9C844,
            0xA4F6EB75, 0xAAFFE67E, 0xB8E4F163, 0xB6EDFC68,
            0x0C0A67B1, 0x02036ABA, 0x10187DA7, 0x1E1170AC,
            0x342E539D, 0x3A275E96, 0x283C498B, 0x26354480,
            0x7C420FE9, 0x724B02E2, 0x605015FF, 0x6E5918F4,
            0x44663BC5, 0x4A6F36CE, 0x587421D3, 0x567D2CD8,
            0x37A10C7A, 0x39A80171, 0x2BB3166C, 0x25BA1B67,
            0x0F853856, 0x018C355D, 0x13972240, 0x1D9E2F4B,
            0x47E96422, 0x49E06929, 0x5BFB7E34, 0x55F2733F,
            0x7FCD500E, 0x71C45D05, 0x63DF4A18, 0x6DD64713,
            0xD731DCCA, 0xD938D1C1, 0xCB23C6DC, 0xC52ACBD7,
            0xEF15E8E6, 0xE11CE5ED, 0xF307F2F0, 0xFD0EFFFB,
            0xA779B492, 0xA970B999, 0xBB6BAE84, 0xB562A38F,
            0x9F5D80BE, 0x91548DB5, 0x834F9AA8, 0x8D4697A3,
    };

    private static final int[] U2 = {
            0x00000000, 0x0B0E090D, 0x161C121A, 0x1D121B17,
            0x2C382434, 0x27362D39, 0x3A24362E, 0x312A3F23,
            0x58704868, 0x537E4165, 0x4E6C5A72, 0x4562537F,
            0x74486C5C, 0x7F466551, 0x62547E46, 0x695A774B,
            0xB0E090D0, 0xBBEE99DD, 0xA6FC82CA, 0xADF28BC7,
            0x9CD8B4E4, 0x97D6BDE9, 0x8AC4A6FE, 0x81CAAFF3,
            0xE890D8B8, 0xE39ED1B5, 0xFE8CCAA2, 0xF582C3AF,
            0xC4A8FC8C, 0xCFA6F581, 0xD2B4EE96, 0xD9BAE79B,
            0x7BDB3BBB, 0x70D532B6, 0x6DC729A1, 0x66C920AC,
            0x57E31F8F, 0x5CED1682, 0x41FF0D95, 0x4AF10498,
            0x23AB73D3, 0x28A57ADE, 0x35B761C9, 0x3EB968C4,
            0x0F9357E7, 0x049D5EEA, 0x198F45FD, 0x12814CF0,
            0xCB3BAB6B, 0xC035A266, 0xDD27B971, 0xD629B07C,
            0xE7038F5F, 0xEC0D8652, 0xF11F9D45, 0xFA119448,
            0x934BE303, 0x9845EA0E, 0x8557F119, 0x8E59F814,
            0xBF73C737, 0xB47DCE3A, 0xA96FD52D, 0xA261DC20,
            0xF6AD766D, 0xFDA37F60, 0xE0B16477, 0xEBBF6D7A,
            0xDA955259, 0xD19B5B54, 0xCC894043, 0xC787494E,
            0xAEDD3E05, 0xA5D33708, 0xB8C12C1F, 0xB3CF2512,
            0x82E51A31, 0x89EB133C, 0x94F9082B, 0x9FF70126,
            0x464DE6BD, 0x4D43EFB0, 0x5051F4A7, 0x5B5FFDAA,
            0x6A75C289, 0x617BCB84, 0x7C69D093, 0x7767D99E,
            0x1E3DAED5, 0x1533A7D8, 0x0821BCCF, 0x032FB5C2,
            0x32058AE1, 0x390B83EC, 0x241998FB, 0x2F1791F6,
            0x8D764DD6, 0x867844DB, 0x9B6A5FCC, 0x906456C1,
            0xA14E69E2, 0xAA4060EF, 0xB7527BF8, 0xBC5C72F5,
            0xD50605BE, 0xDE080CB3, 0xC31A17A4, 0xC8141EA9,
            0xF93E218A, 0xF2302887, 0xEF223390, 0xE42C3A9D,
            0x3D96DD06, 0x3698D40B, 0x2B8ACF1C, 0x2084C611,
            0x11AEF932, 0x1AA0F03F, 0x07B2EB28, 0x0CBCE225,
            0x65E6956E, 0x6EE89C63, 0x73FA8774, 0x78F48E79,
            0x49DEB15A, 0x42D0B857, 0x5FC2A340, 0x54CCAA4D,
            0xF741ECDA, 0xFC4FE5D7, 0xE15DFEC0, 0xEA53F7CD,
            0xDB79C8EE, 0xD077C1E3, 0xCD65DAF4, 0xC66BD3F9,
            0xAF31A4B2, 0xA43FADBF, 0xB92DB6A8, 0xB223BFA5,
            0x83098086, 0x8807898B, 0x9515929C, 0x9E1B9B91,
            0x47A17C0A, 0x4CAF7507, 0x51BD6E10, 0x5AB3671D,
            0x6B99583E, 0x60975133, 0x7D854A24, 0x768B4329,
            0x1FD13462, 0x14DF3D6F, 0x09CD2678, 0x02C32F75,
            0x33E91056, 0x38E7195B, 0x25F5024C, 0x2EFB0B41,
            0x8C9AD761, 0x8794DE6C, 0x9A86C57B, 0x9188CC76,
            0xA0A2F355, 0xABACFA58, 0xB6BEE14F, 0xBDB0E842,
            0xD4EA9F09, 0xDFE49604, 0xC2F68D13, 0xC9F8841E,
            0xF8D2BB3D, 0xF3DCB230, 0xEECEA927, 0xE5C0A02A,
            0x3C7A47B1, 0x37744EBC, 0x2A6655AB, 0x21685CA6,
            0x10426385, 0x1B4C6A88, 0x065E719F, 0x0D507892,
            0x640A0FD9, 0x6F0406D4, 0x72161DC3, 0x791814CE,
            0x48322BED, 0x433C22E0, 0x5E2E39F7, 0x552030FA,
            0x01EC9AB7, 0x0AE293BA, 0x17F088AD, 0x1CFE81A0,
            0x2DD4BE83, 0x26DAB78E, 0x3BC8AC99, 0x30C6A594,
            0x599CD2DF, 0x5292DBD2, 0x4F80C0C5, 0x448EC9C8,
            0x75A4F6EB, 0x7EAAFFE6, 0x63B8E4F1, 0x68B6EDFC,
            0xB10C0A67, 0xBA02036A, 0xA710187D, 0xAC1E1170,
            0x9D342E53, 0x963A275E, 0x8B283C49, 0x80263544,
            0xE97C420F, 0xE2724B02, 0xFF605015, 0xF46E5918,
            0xC544663B, 0xCE4A6F36, 0xD3587421, 0xD8567D2C,
            0x7A37A10C, 0x7139A801, 0x6C2BB316, 0x6725BA1B,
            0x560F8538, 0x5D018C35, 0x40139722, 0x4B1D9E2F,
            0x2247E964, 0x2949E069, 0x345BFB7E, 0x3F55F273,
            0x0E7FCD50, 0x0571C45D, 0x1863DF4A, 0x136DD647,
            0xCAD731DC, 0xC1D938D1, 0xDCCB23C6, 0xD7C52ACB,
            0xE6EF15E8, 0xEDE11CE5, 0xF0F307F2, 0xFBFD0EFF,
            0x92A779B4, 0x99A970B9, 0x84BB6BAE, 0x8FB562A3,
            0xBE9F5D80, 0xB591548D, 0xA8834F9A, 0xA38D4697,
    };

    private static final int[] U3 = {
            0x00000000, 0x0D0B0E09, 0x1A161C12, 0x171D121B,
            0x342C3824, 0x3927362D, 0x2E3A2436, 0x23312A3F,
            0x68587048, 0x65537E41, 0x724E6C5A, 0x7F456253,
            0x5C74486C, 0x517F4665, 0x4662547E, 0x4B695A77,
            0xD0B0E090, 0xDDBBEE99, 0xCAA6FC82, 0xC7ADF28B,
            0xE49CD8B4, 0xE997D6BD, 0xFE8AC4A6, 0xF381CAAF,
            0xB8E890D8, 0xB5E39ED1, 0xA2FE8CCA, 0xAFF582C3,
            0x8CC4A8FC, 0x81CFA6F5, 0x96D2B4EE, 0x9BD9BAE7,
            0xBB7BDB3B, 0xB670D532, 0xA16DC729, 0xAC66C920,
            0x8F57E31F, 0x825CED16, 0x9541FF0D, 0x984AF104,
            0xD323AB73, 0xDE28A57A, 0xC935B761, 0xC43EB968,
            0xE70F9357, 0xEA049D5E, 0xFD198F45, 0xF012814C,
            0x6BCB3BAB, 0x66C035A2, 0x71DD27B9, 0x7CD629B0,
            0x5FE7038F, 0x52EC0D86, 0x45F11F9D, 0x48FA1194,
            0x03934BE3, 0x0E9845EA, 0x198557F1, 0x148E59F8,
            0x37BF73C7, 0x3AB47DCE, 0x2DA96FD5, 0x20A261DC,
            0x6DF6AD76, 0x60FDA37F, 0x77E0B164, 0x7AEBBF6D,
            0x59DA9552, 0x54D19B5B, 0x43CC8940, 0x4EC78749,
            0x05AEDD3E, 0x08A5D337, 0x1FB8C12C, 0x12B3CF25,
            0x3182E51A, 0x3C89EB13, 0x2B94F908, 0x269FF701,
            0xBD464DE6, 0xB04D43EF, 0xA75051F4, 0xAA5B5FFD,
            0x896A75C2, 0x84617BCB, 0x937C69D0, 0x9E7767D9,
            0xD51E3DAE, 0xD81533A7, 0xCF0821BC, 0xC2032FB5,
            0xE132058A, 0xEC390B83, 0xFB241998, 0xF62F1791,
            0xD68D764D, 0xDB867844, 0xCC9B6A5F, 0xC1906456,
            0xE2A14E69, 0xEFAA4060, 0xF8B7527B, 0xF5BC5C72,
            0xBED50605, 0xB3DE080C, 0xA4C31A17, 0xA9C8141E,
            0x8AF93E21, 0x87F23028, 0x90EF2233, 0x9DE42C3A,
            0x063D96DD, 0x0B3698D4, 0x1C2B8ACF, 0x112084C6,
            0x3211AEF9, 0x3F1AA0F0, 0x2807B2EB, 0x250CBCE2,
            0x6E65E695, 0x636EE89C, 0x7473FA87, 0x7978F48E,
            0x5A49DEB1, 0x5742D0B8, 0x405FC2A3, 0x4D54CCAA,
            0xDAF741EC, 0xD7FC4FE5, 0xC0E15DFE, 0xCDEA53F7,
            0xEEDB79C8, 0xE3D077C1, 0xF4CD65DA, 0xF9C66BD3,
            0xB2AF31A4, 0xBFA43FAD, 0xA8B92DB6, 0xA5B223BF,
            0x86830980, 0x8B880789, 0x9C951592, 0x919E1B9B,
            0x0A47A17C, 0x074CAF75, 0x1051BD6E, 0x1D5AB367,
            0x3E6B9958, 0x33609751, 0x247D854A, 0x29768B43,
            0x621FD134, 0x6F14DF3D, 0x7809CD26, 0x7502C32F,
            0x5633E910, 0x5B38E719, 0x4C25F502, 0x412EFB0B,
            0x618C9AD7, 0x6C8794DE, 0x7B9A86C5, 0x769188CC,
            0x55A0A2F3, 0x58ABACFA, 0x4FB6BEE1, 0x42BDB0E8,
            0x09D4EA9F, 0x04DFE496, 0x13C2F68D, 0x1EC9F884,
            0x3DF8D2BB, 0x30F3DCB2, 0x27EECEA9, 0x2AE5C0A0,
            0xB13C7A47, 0xBC37744E, 0xAB2A6655, 0xA621685C,
            0x85104263, 0x881B4C6A, 0x9F065E71, 0x920D5078,
            0xD9640A0F, 0xD46F0406, 0xC372161D, 0xCE791814,
            0xED48322B, 0xE0433C22, 0xF75E2E39, 0xFA552030,
            0xB701EC9A, 0xBA0AE293, 0xAD17F088, 0xA01CFE81,
            0x832DD4BE, 0x8E26DAB7, 0x993BC8AC, 0x9430C6A5,
            0xDF599CD2, 0xD25292DB, 0xC54F80C0, 0xC8448EC9,
            0xEB75A4F6, 0xE67EAAFF, 0xF163B8E4, 0xFC68B6ED,
            0x67B10C0A, 0x6ABA0203, 0x7DA71018, 0x70AC1E11,
            0x539D342E, 0x5E963A27, 0x498B283C, 0x44802635,
            0x0FE97C42, 0x02E2724B, 0x15FF6050, 0x18F46E59,
            0x3BC54466, 0x36CE4A6F, 0x21D35874, 0x2CD8567D,
            0x0C7A37A1, 0x017139A8, 0x166C2BB3, 0x1B6725BA,
            0x38560F85, 0x355D018C, 0x22401397, 0x2F4B1D9E,
            0x642247E9, 0x692949E0, 0x7E345BFB, 0x733F55F2,
            0x500E7FCD, 0x5D0571C4, 0x4A1863DF, 0x47136DD6,
            0xDCCAD731, 0xD1C1D938, 0xC6DCCB23, 0xCBD7C52A,
            0xE8E6EF15, 0xE5EDE11C, 0xF2F0F307, 0xFFFBFD0E,
            0xB492A779, 0xB999A970, 0xAE84BB6B, 0xA38FB562,
            0x80BE9F5D, 0x8DB59154, 0x9AA8834F, 0x97A38D46,
    };

    private static final int[] U4 = {
            0x00000000, 0x090D0B0E, 0x121A161C, 0x1B171D12,
            0x24342C38, 0x2D392736, 0x362E3A24, 0x3F23312A,
            0x48685870, 0x4165537E, 0x5A724E6C, 0x537F4562,
            0x6C5C7448, 0x65517F46, 0x7E466254, 0x774B695A,
            0x90D0B0E0, 0x99DDBBEE, 0x82CAA6FC, 0x8BC7ADF2,
            0xB4E49CD8, 0xBDE997D6, 0xA6FE8AC4, 0xAFF381CA,
            0xD8B8E890, 0xD1B5E39E, 0xCAA2FE8C, 0xC3AFF582,
            0xFC8CC4A8, 0xF581CFA6, 0xEE96D2B4, 0xE79BD9BA,
            0x3BBB7BDB, 0x32B670D5, 0x29A16DC7, 0x20AC66C9,
            0x1F8F57E3, 0x16825CED, 0x0D9541FF, 0x04984AF1,
            0x73D323AB, 0x7ADE28A5, 0x61C935B7, 0x68C43EB9,
            0x57E70F93, 0x5EEA049D, 0x45FD198F, 0x4CF01281,
            0xAB6BCB3B, 0xA266C035, 0xB971DD27, 0xB07CD629,
            0x8F5FE703, 0x8652EC0D, 0x9D45F11F, 0x9448FA11,
            0xE303934B, 0xEA0E9845, 0xF1198557, 0xF8148E59,
            0xC737BF73, 0xCE3AB47D, 0xD52DA96F, 0xDC20A261,
            0x766DF6AD, 0x7F60FDA3, 0x6477E0B1, 0x6D7AEBBF,
            0x5259DA95, 0x5B54D19B, 0x4043CC89, 0x494EC787,
            0x3E05AEDD, 0x3708A5D3, 0x2C1FB8C1, 0x2512B3CF,
            0x1A3182E5, 0x133C89EB, 0x082B94F9, 0x01269FF7,
            0xE6BD464D, 0xEFB04D43, 0xF4A75051, 0xFDAA5B5F,
            0xC2896A75, 0xCB84617B, 0xD0937C69, 0xD99E7767,
            0xAED51E3D, 0xA7D81533, 0xBCCF0821, 0xB5C2032F,
            0x8AE13205, 0x83EC390B, 0x98FB2419, 0x91F62F17,
            0x4DD68D76, 0x44DB8678, 0x5FCC9B6A, 0x56C19064,
            0x69E2A14E, 0x60EFAA40, 0x7BF8B752, 0x72F5BC5C,
            0x05BED506, 0x0CB3DE08, 0x17A4C31A, 0x1EA9C814,
            0x218AF93E, 0x2887F230, 0x3390EF22, 0x3A9DE42C,
            0xDD063D96, 0xD40B3698, 0xCF1C2B8A, 0xC6112084,
            0xF93211AE, 0xF03F1AA0, 0xEB2807B2, 0xE2250CBC,
            0x956E65E6, 0x9C636EE8, 0x877473FA, 0x8E7978F4,
            0xB15A49DE, 0xB85742D0, 0xA3405FC2, 0xAA4D54CC,
            0xECDAF741, 0xE5D7FC4F, 0xFEC0E15D, 0xF7CDEA53,
            0xC8EEDB79, 0xC1E3D077, 0xDAF4CD65, 0xD3F9C66B,
            0xA4B2AF31, 0xADBFA43F, 0xB6A8B92D, 0xBFA5B223,
            0x80868309, 0x898B8807, 0x929C9515, 0x9B919E1B,
            0x7C0A47A1, 0x75074CAF, 0x6E1051BD, 0x671D5AB3,
            0x583E6B99, 0x51336097, 0x4A247D85, 0x4329768B,
            0x34621FD1, 0x3D6F14DF, 0x267809CD, 0x2F7502C3,
            0x105633E9, 0x195B38E7, 0x024C25F5, 0x0B412EFB,
            0xD7618C9A, 0xDE6C8794, 0xC57B9A86, 0xCC769188,
            0xF355A0A2, 0xFA58ABAC, 0xE14FB6BE, 0xE842BDB0,
            0x9F09D4EA, 0x9604DFE4, 0x8D13C2F6, 0x841EC9F8,
            0xBB3DF8D2, 0xB230F3DC, 0xA927EECE, 0xA02AE5C0,
            0x47B13C7A, 0x4EBC3774, 0x55AB2A66, 0x5CA62168,
            0x63851042, 0x6A881B4C, 0x719F065E, 0x78920D50,
            0x0FD9640A, 0x06D46F04, 0x1DC37216, 0x14CE7918,
            0x2BED4832, 0x22E0433C, 0x39F75E2E, 0x30FA5520,
            0x9AB701EC, 0x93BA0AE2, 0x88AD17F0, 0x81A01CFE,
            0xBE832DD4, 0xB78E26DA, 0xAC993BC8, 0xA59430C6,
            0xD2DF599C, 0xDBD25292, 0xC0C54F80, 0xC9C8448E,
            0xF6EB75A4, 0xFFE67EAA, 0xE4F163B8, 0xEDFC68B6,
            0x0A67B10C, 0x036ABA02, 0x187DA710, 0x1170AC1E,
            0x2E539D34, 0x275E963A, 0x3C498B28, 0x35448026,
            0x420FE97C, 0x4B02E272, 0x5015FF60, 0x5918F46E,
            0x663BC544, 0x6F36CE4A, 0x7421D358, 0x7D2CD856,
            0xA10C7A37, 0xA8017139, 0xB3166C2B, 0xBA1B6725,
            0x8538560F, 0x8C355D01, 0x97224013, 0x9E2F4B1D,
            0xE9642247, 0xE0692949, 0xFB7E345B, 0xF2733F55,
            0xCD500E7F, 0xC45D0571, 0xDF4A1863, 0xD647136D,
            0x31DCCAD7, 0x38D1C1D9, 0x23C6DCCB, 0x2ACBD7C5,
            0x15E8E6EF, 0x1CE5EDE1, 0x07F2F0F3, 0x0EFFFBFD,
            0x79B492A7, 0x70B999A9, 0x6BAE84BB, 0x62A38FB5,
            0x5D80BE9F, 0x548DB591, 0x4F9AA883, 0x4697A38D,
    };

    private static final int[] rcon = {
            0x00000001, 0x00000002, 0x00000004, 0x00000008,
            0x00000010, 0x00000020, 0x00000040, 0x00000080,
            0x0000001B, 0x00000036, 0x0000006C, 0x000000D8,
            0x000000AB, 0x0000004D, 0x0000009A, 0x0000002F,
            0x0000005E, 0x000000BC, 0x00000063, 0x000000C6,
            0x00000097, 0x00000035, 0x0000006A, 0x000000D4,
            0x000000B3, 0x0000007D, 0x000000FA, 0x000000EF,
            0x000000C5, 0x00000091,
    };

    private boolean ROUNDS_12 = false;
    private boolean ROUNDS_14 = false;

    /** Session and Sub keys */
    private int[][] sessionK = null;
    private int[] K = null;

    /** Cipher encryption/decryption key */
    // skip re-generating Session and Sub keys if the cipher key is
    // the same
    private byte[] lastKey = null;

    /** ROUNDS * 4 */
    private int limit = 0;

    AESCrypt() {
        // empty
    }

    /**
     * Returns this cipher's block size.
     *
     * @return this cipher's block size
     */
    int getBlockSize() {
        return AES_BLOCK_SIZE;
    }

    void init(boolean decrypting, String algorithm, byte[] key)
            throws InvalidKeyException {
        if (!algorithm.equalsIgnoreCase("AES")
                    && !algorithm.equalsIgnoreCase("Rijndael")) {
            throw new InvalidKeyException
                ("Wrong algorithm: AES or Rijndael required");
        }

        if (key == null) {      // Unlikely, but just double check it.
            throw new InvalidKeyException("Empty key");
        }

        if (!MessageDigest.isEqual(key, lastKey)) {
            // re-generate session key 'sessionK' when cipher key changes
            makeSessionKey(key);
            if (lastKey != null) {
                Arrays.fill(lastKey, (byte)0);
            }
            lastKey = key.clone();  // save cipher key
        }

        // set sub key to the corresponding session Key
        this.K = sessionK[(decrypting? 1:0)];
    }

    /**
     * Expand an int[(ROUNDS+1)][4] into int[(ROUNDS+1)*4].
     * For decryption round keys, need to rotate right by 4 ints.
     * @param kr The round keys for encryption or decryption.
     * @param decrypting True if 'kr' is for decryption and false otherwise.
     */
    private static final int[] expandToSubKey(int[][] kr, boolean decrypting) {
        int total = kr.length;
        int[] expK = new int[total*4];
        if (decrypting) {
            // decrypting, rotate right by 4 ints
            // i.e. i==0
            for(int j=0; j<4; j++) {
                expK[j] = kr[total-1][j];
            }
            for(int i=1; i<total; i++) {
                for(int j=0; j<4; j++) {
                    expK[i*4 + j] = kr[i-1][j];
                }
            }
        } else {
            // encrypting, straight expansion
            for(int i=0; i<total; i++) {
                for(int j=0; j<4; j++) {
                    expK[i*4 + j] = kr[i][j];
                }
            }
        }
        return expK;
    }

    // check if the specified length (in bytes) is a valid keysize for AES
    static boolean isKeySizeValid(int len) {
        for (int aesKeysize : AES_KEYSIZES) {
            if (len == aesKeysize) {
                return true;
            }
        }
        return false;
    }

    /**
     * Encrypt exactly one block of plaintext.
     */
    void encryptBlock(byte[] in, int inOffset,
                      byte[] out, int outOffset) {
        // Array bound checks are done in caller code, i.e.
        // FeedbackCipher.encrypt/decrypt(...) to improve performance.
        implEncryptBlock(in, inOffset, out, outOffset);
    }

    // Encryption operation. Possibly replaced with a compiler intrinsic.
    @IntrinsicCandidate
    private void implEncryptBlock(byte[] in, int inOffset,
                                  byte[] out, int outOffset)
    {
        int keyOffset = 0;
        int t0   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t1   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t2   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t3   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset]   & 0xFF)        ) ^ K[keyOffset++];

        // apply round transforms
        while( keyOffset < limit )
        {
            int a0, a1, a2;
            a0 = T1[(t0 >>> 24)       ] ^
                 T2[(t1 >>> 16) & 0xFF] ^
                 T3[(t2 >>>  8) & 0xFF] ^
                 T4[(t3       ) & 0xFF] ^ K[keyOffset++];
            a1 = T1[(t1 >>> 24)       ] ^
                 T2[(t2 >>> 16) & 0xFF] ^
                 T3[(t3 >>>  8) & 0xFF] ^
                 T4[(t0       ) & 0xFF] ^ K[keyOffset++];
            a2 = T1[(t2 >>> 24)       ] ^
                 T2[(t3 >>> 16) & 0xFF] ^
                 T3[(t0 >>>  8) & 0xFF] ^
                 T4[(t1       ) & 0xFF] ^ K[keyOffset++];
            t3 = T1[(t3 >>> 24)       ] ^
                 T2[(t0 >>> 16) & 0xFF] ^
                 T3[(t1 >>>  8) & 0xFF] ^
                 T4[(t2       ) & 0xFF] ^ K[keyOffset++];
            t0 = a0; t1 = a1; t2 = a2;
        }

        // last round is special
        int tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t0 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t1 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t2 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t3       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t1 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t2 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t3 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t0       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t2 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t3 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t0 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t1       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset];
        out[outOffset++] = (byte)(S[(t3 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t0 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t1 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset  ] = (byte)(S[(t2       ) & 0xFF] ^ (tt       ));
    }

    /**
     * Decrypt exactly one block of plaintext.
     */
    void decryptBlock(byte[] in, int inOffset,
                      byte[] out, int outOffset) {
        // Array bound checks are done in caller code, i.e.
        // FeedbackCipher.encrypt/decrypt(...) to improve performance.
        implDecryptBlock(in, inOffset, out, outOffset);
    }

    // Decrypt operation. Possibly replaced with a compiler intrinsic.
    @IntrinsicCandidate
    private void implDecryptBlock(byte[] in, int inOffset,
                                  byte[] out, int outOffset)
    {
        int keyOffset = 4;
        int t0 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t1 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t2 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t3 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset  ] & 0xFF)        ) ^ K[keyOffset++];

        int a0, a1, a2;
        if(ROUNDS_12)
        {
            a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                 T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
            a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
                 T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
            a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
                 T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
            t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
                 T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
            t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                 T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
            t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
                 T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
            t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
                 T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
            t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
                 T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];

            if(ROUNDS_14)
            {
                a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                     T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
                a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
                     T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
                a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
                     T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
                t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
                     T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
                t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                     T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
                t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
                     T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
                t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
                     T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
                t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
                     T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];
            }
        }
        a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
             T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
             T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];
        a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
             T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
             T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];
        a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
             T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
             T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];
        a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(a0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(a2>>> 8)&0xFF] ^ T8[(a1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(a1>>>24)     ] ^ T6[(a0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(a2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(a2>>>24)     ] ^ T6[(a1>>>16)&0xFF] ^
             T7[(a0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(a2>>>16)&0xFF] ^
             T7[(a1>>> 8)&0xFF] ^ T8[(a0     )&0xFF] ^ K[keyOffset++];
        a0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        a1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        a2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset];

        t1 = K[0];
        out[outOffset++] = (byte)(Si[(a0 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(t3 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(a2 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(a1       ) & 0xFF] ^ (t1       ));
        t1 = K[1];
        out[outOffset++] = (byte)(Si[(a1 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(a0 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(t3 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(a2       ) & 0xFF] ^ (t1       ));
        t1 = K[2];
        out[outOffset++] = (byte)(Si[(a2 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(a1 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(a0 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(t3       ) & 0xFF] ^ (t1       ));
        t1 = K[3];
        out[outOffset++] = (byte)(Si[(t3 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(a2 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(a1 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset  ] = (byte)(Si[(a0       ) & 0xFF] ^ (t1       ));
    }

    /**
     * Expand a user-supplied key material into a session key.
     *
     * @param k The 128/192/256-bit cipher key to use.
     * @exception InvalidKeyException  If the key is invalid.
     */
    private void makeSessionKey(byte[] k) throws InvalidKeyException {
        if (!isKeySizeValid(k.length)) {
            throw new InvalidKeyException("Invalid AES key length: " +
                    k.length + " bytes");
        }

        int ROUNDS          = getRounds(k.length);
        int ROUND_KEY_COUNT = (ROUNDS + 1) * 4;

        int BC = 4;
        int[][] Ke = new int[ROUNDS + 1][4]; // encryption round keys
        int[][] Kd = new int[ROUNDS + 1][4]; // decryption round keys

        int KC = k.length/4; // keylen in 32-bit elements

        int[] tk = new int[KC];
        int i, j;

        // copy user material bytes into temporary ints
        for (i = 0, j = 0; i < KC; i++, j+=4) {
            tk[i] = (k[j]       ) << 24 |
                    (k[j+1] & 0xFF) << 16 |
                    (k[j+2] & 0xFF) <<  8 |
                    (k[j+3] & 0xFF);
        }

        // copy values into round key arrays
        int t = 0;
        for (j = 0; (j < KC) && (t < ROUND_KEY_COUNT); j++, t++) {
            Ke[t / 4][t % 4] = tk[j];
            Kd[ROUNDS - (t / 4)][t % 4] = tk[j];
        }
        int tt, rconpointer = 0;
        while (t < ROUND_KEY_COUNT) {
            // extrapolate using phi (the round key evolution function)
            tt = tk[KC - 1];
            tk[0] ^= (S[(tt >>> 16) & 0xFF]       ) << 24 ^
                     (S[(tt >>>  8) & 0xFF] & 0xFF) << 16 ^
                     (S[(tt       ) & 0xFF] & 0xFF) <<  8 ^
                     (S[(tt >>> 24)       ] & 0xFF)       ^
                     (rcon[rconpointer++]         ) << 24;
            if (KC != 8)
                for (i = 1, j = 0; i < KC; i++, j++) tk[i] ^= tk[j];
            else {
                for (i = 1, j = 0; i < KC / 2; i++, j++) tk[i] ^= tk[j];
                tt = tk[KC / 2 - 1];
                tk[KC / 2] ^= (S[(tt       ) & 0xFF] & 0xFF)       ^
                              (S[(tt >>>  8) & 0xFF] & 0xFF) <<  8 ^
                              (S[(tt >>> 16) & 0xFF] & 0xFF) << 16 ^
                              (S[(tt >>> 24)       ]       ) << 24;
                for (j = KC / 2, i = j + 1; i < KC; i++, j++) tk[i] ^= tk[j];
            }
            // copy values into round key arrays
            for (j = 0; (j < KC) && (t < ROUND_KEY_COUNT); j++, t++) {
                Ke[t / 4][t % 4] = tk[j];
                Kd[ROUNDS - (t / 4)][t % 4] = tk[j];
            }
        }
        for (int r = 1; r < ROUNDS; r++) {
            // inverse MixColumn where needed
            for (j = 0; j < BC; j++) {
                tt = Kd[r][j];
                Kd[r][j] = U1[(tt >>> 24) & 0xFF] ^
                           U2[(tt >>> 16) & 0xFF] ^
                           U3[(tt >>>  8) & 0xFF] ^
                           U4[ tt         & 0xFF];
            }
        }

        // assemble the encryption (Ke) and decryption (Kd) round keys
        // and expand them into arrays of ints.
        int[] expandedKe = expandToSubKey(Ke, false); // decrypting==false
        int[] expandedKd = expandToSubKey(Kd, true);  // decrypting==true
        Arrays.fill(tk, 0);
        for (int[] ia: Ke) {
            Arrays.fill(ia, 0);
        }
        for (int[] ia: Kd) {
            Arrays.fill(ia, 0);
        }
        ROUNDS_12 = (ROUNDS>=12);
        ROUNDS_14 = (ROUNDS==14);
        limit = ROUNDS*4;

        // store the expanded sub keys into 'sessionK'
        if (sessionK != null) {
            // erase the previous values in sessionK
            Arrays.fill(sessionK[0], 0);
            Arrays.fill(sessionK[1], 0);
        }
        sessionK = new int[][] { expandedKe, expandedKd };
    }

    /**
     * Return The number of rounds for a given Rijndael keysize.
     *
     * @param keySize  The size of the user key material in bytes.
     *                 MUST be one of (16, 24, 32).
     * @return         The number of rounds.
     */
    private static int getRounds(int keySize) {
        return (keySize >> 2) + 6;
    }
}
