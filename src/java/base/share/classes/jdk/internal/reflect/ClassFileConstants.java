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

package java.base.share.classes.jdk.internal.reflect;

/** Minimal set of class file constants for assembly of field and
    method accessors. */

interface ClassFileConstants {
    // Constants
    public static final byte opc_aconst_null   = (byte) 0x1;
    public static final byte opc_sipush        = (byte) 0x11;
    public static final byte opc_ldc           = (byte) 0x12;

    // Local variable loads and stores
    public static final byte opc_iload_0       = (byte) 0x1a;
    public static final byte opc_iload_1       = (byte) 0x1b;
    public static final byte opc_iload_2       = (byte) 0x1c;
    public static final byte opc_iload_3       = (byte) 0x1d;
    public static final byte opc_lload_0       = (byte) 0x1e;
    public static final byte opc_lload_1       = (byte) 0x1f;
    public static final byte opc_lload_2       = (byte) 0x20;
    public static final byte opc_lload_3       = (byte) 0x21;
    public static final byte opc_fload_0       = (byte) 0x22;
    public static final byte opc_fload_1       = (byte) 0x23;
    public static final byte opc_fload_2       = (byte) 0x24;
    public static final byte opc_fload_3       = (byte) 0x25;
    public static final byte opc_dload_0       = (byte) 0x26;
    public static final byte opc_dload_1       = (byte) 0x27;
    public static final byte opc_dload_2       = (byte) 0x28;
    public static final byte opc_dload_3       = (byte) 0x29;
    public static final byte opc_aload_0       = (byte) 0x2a;
    public static final byte opc_aload_1       = (byte) 0x2b;
    public static final byte opc_aload_2       = (byte) 0x2c;
    public static final byte opc_aload_3       = (byte) 0x2d;
    public static final byte opc_aaload        = (byte) 0x32;
    public static final byte opc_astore_0      = (byte) 0x4b;
    public static final byte opc_astore_1      = (byte) 0x4c;
    public static final byte opc_astore_2      = (byte) 0x4d;
    public static final byte opc_astore_3      = (byte) 0x4e;

    // Stack manipulation
    public static final byte opc_pop           = (byte) 0x57;
    public static final byte opc_dup           = (byte) 0x59;
    public static final byte opc_dup_x1        = (byte) 0x5a;
    public static final byte opc_swap          = (byte) 0x5f;

    // Conversions
    public static final byte opc_i2l           = (byte) 0x85;
    public static final byte opc_i2f           = (byte) 0x86;
    public static final byte opc_i2d           = (byte) 0x87;
    public static final byte opc_l2i           = (byte) 0x88;
    public static final byte opc_l2f           = (byte) 0x89;
    public static final byte opc_l2d           = (byte) 0x8a;
    public static final byte opc_f2i           = (byte) 0x8b;
    public static final byte opc_f2l           = (byte) 0x8c;
    public static final byte opc_f2d           = (byte) 0x8d;
    public static final byte opc_d2i           = (byte) 0x8e;
    public static final byte opc_d2l           = (byte) 0x8f;
    public static final byte opc_d2f           = (byte) 0x90;
    public static final byte opc_i2b           = (byte) 0x91;
    public static final byte opc_i2c           = (byte) 0x92;
    public static final byte opc_i2s           = (byte) 0x93;

    // Control flow
    public static final byte opc_ifeq          = (byte) 0x99;
    public static final byte opc_if_icmpeq     = (byte) 0x9f;
    public static final byte opc_goto          = (byte) 0xa7;

    // Return instructions
    public static final byte opc_ireturn       = (byte) 0xac;
    public static final byte opc_lreturn       = (byte) 0xad;
    public static final byte opc_freturn       = (byte) 0xae;
    public static final byte opc_dreturn       = (byte) 0xaf;
    public static final byte opc_areturn       = (byte) 0xb0;
    public static final byte opc_return        = (byte) 0xb1;

    // Field operations
    public static final byte opc_getstatic     = (byte) 0xb2;
    public static final byte opc_putstatic     = (byte) 0xb3;
    public static final byte opc_getfield      = (byte) 0xb4;
    public static final byte opc_putfield      = (byte) 0xb5;

    // Method invocations
    public static final byte opc_invokevirtual   = (byte) 0xb6;
    public static final byte opc_invokespecial   = (byte) 0xb7;
    public static final byte opc_invokestatic    = (byte) 0xb8;
    public static final byte opc_invokeinterface = (byte) 0xb9;

    // Array length
    public static final byte opc_arraylength     = (byte) 0xbe;

    // New
    public static final byte opc_new           = (byte) 0xbb;

    // Athrow
    public static final byte opc_athrow        = (byte) 0xbf;

    // Checkcast and instanceof
    public static final byte opc_checkcast     = (byte) 0xc0;
    public static final byte opc_instanceof    = (byte) 0xc1;

    // Ifnull and ifnonnull
    public static final byte opc_ifnull        = (byte) 0xc6;
    public static final byte opc_ifnonnull     = (byte) 0xc7;

    // Constant pool tags
    public static final byte CONSTANT_Class              = (byte) 7;
    public static final byte CONSTANT_Fieldref           = (byte) 9;
    public static final byte CONSTANT_Methodref          = (byte) 10;
    public static final byte CONSTANT_InterfaceMethodref = (byte) 11;
    public static final byte CONSTANT_NameAndType        = (byte) 12;
    public static final byte CONSTANT_String             = (byte) 8;
    public static final byte CONSTANT_Utf8               = (byte) 1;

    // Access flags
    public static final short ACC_PUBLIC = (short) 0x0001;
}
