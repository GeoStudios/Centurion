/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign;

import java.lang.foreign.ValueLayout;

public final class PlatformLayouts {

    private PlatformLayouts() {
        //just the one
    }

    /**
     * This class defines layout constants modelling standard primitive types supported by the x64 SystemV ABI.
     */
    public static final class SysV {
        private SysV() {
            //just the one
        }

        /**
         * The {@code bool} native type.
         */
        public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;

        /**
         * The {@code char} native type.
         */
        public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;

        /**
         * The {@code short} native type.
         */
        public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT.withBitAlignment(16);

        /**
         * The {@code int} native type.
         */
        public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT.withBitAlignment(32);

        /**
         * The {@code long} native type.
         */
        public static final ValueLayout.OfLong C_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code long long} native type.
         */
        public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code float} native type.
         */
        public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT.withBitAlignment(32);

        /**
         * The {@code double} native type.
         */
        public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE.withBitAlignment(64);

        /**
         * The {@code T*} native type.
         */
        public static final ValueLayout.OfAddress C_POINTER = ValueLayout.ADDRESS.withBitAlignment(64).asUnbounded();

        /**
         * The {@code va_list} native type, as it is passed to a function.
         */
        public static final ValueLayout.OfAddress C_VA_LIST = SysV.C_POINTER;
    }

    /**
     * This class defines layout constants modelling standard primitive types supported by the x64 Windows ABI.
     */
    public static final class Win64 {

        private Win64() {
            //just the one
        }

        /**
         * The {@code bool} native type.
         */
        public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;

        /**
         * The {@code char} native type.
         */
        public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;

        /**
         * The {@code short} native type.
         */
        public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT.withBitAlignment(16);

        /**
         * The {@code int} native type.
         */
        public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT.withBitAlignment(32);
        /**
         * The {@code long} native type.
         */
        public static final ValueLayout.OfInt C_LONG = ValueLayout.JAVA_INT.withBitAlignment(32);

        /**
         * The {@code long long} native type.
         */
        public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code float} native type.
         */
        public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT.withBitAlignment(32);

        /**
         * The {@code double} native type.
         */
        public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE.withBitAlignment(64);

        /**
         * The {@code T*} native type.
         */
        public static final ValueLayout.OfAddress C_POINTER = ValueLayout.ADDRESS.withBitAlignment(64).asUnbounded();

        /**
         * The {@code va_list} native type, as it is passed to a function.
         */
        public static final ValueLayout.OfAddress C_VA_LIST = Win64.C_POINTER;
    }

    /**
     * This class defines layout constants modelling standard primitive types supported by the AArch64 ABI.
     */
    public static final class AArch64 {

        private AArch64() {
            //just the one
        }

        /**
         * The {@code bool} native type.
         */
        public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;

        /**
         * The {@code char} native type.
         */
        public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;

        /**
         * The {@code short} native type.
         */
        public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT.withBitAlignment(16);

        /**
         * The {@code int} native type.
         */
        public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT.withBitAlignment(32);

        /**
         * The {@code long} native type.
         */
        public static final ValueLayout.OfLong C_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code long long} native type.
         */
        public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code float} native type.
         */
        public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT.withBitAlignment(32);

        /**
         * The {@code double} native type.
         */
        public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE.withBitAlignment(64);

        /**
         * The {@code T*} native type.
         */
        public static final ValueLayout.OfAddress C_POINTER = ValueLayout.ADDRESS.withBitAlignment(64).asUnbounded();

        /**
         * The {@code va_list} native type, as it is passed to a function.
         */
        public static final ValueLayout.OfAddress C_VA_LIST = AArch64.C_POINTER;
    }

    public static final class RISCV64 {
        private RISCV64() {
            //just the one
        }

        /**
         * The {@code bool} native type.
         */
        public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;

        /**
         * The {@code char} native type.
         */
        public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;

        /**
         * The {@code short} native type.
         */
        public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT.withBitAlignment(16);

        /**
         * The {@code int} native type.
         */
        public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT.withBitAlignment(32);

        /**
         * The {@code long} native type.
         */
        public static final ValueLayout.OfLong C_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code long long} native type.
         */
        public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG.withBitAlignment(64);

        /**
         * The {@code float} native type.
         */
        public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT.withBitAlignment(32);

        /**
         * The {@code double} native type.
         */
        public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE.withBitAlignment(64);

        /**
         * The {@code T*} native type.
         */
        public static final ValueLayout.OfAddress C_POINTER = ValueLayout.ADDRESS.withBitAlignment(64).asUnbounded();

        /**
         * The {@code va_list} native type, as it is passed to a function.
         */
        public static final ValueLayout.OfAddress C_VA_LIST = RISCV64.C_POINTER;
    }
}
