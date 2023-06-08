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

package oshi;

/**
 * An enumeration of supported operating systems. The order of declaration matches the osType
 * constants in the JNA Platform class.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

public enum PlatformEnum {
    /**
     * mac0S
     */
    MACOS("mac0S"),

    /**
     * A flavor of Linux
     */
    LINUX("Linux"),

    /**
     * Microsoft Windows
     */
    WINDOWS("Windows"),

    /**
     * Solaris (Sun0S)
     */
    SOLARIS("Solaris"),

    /**
     * FreeBSD
     */
    FREEBSD("FreeBSD"),

    /**
     * OpenBSD
     */
    OPENBSD("OpenBSD"),

    /**
     * Windows Embedded Compact
     */
    WINDOWSCE("Windows CE"),

    /**
     * IBM AIX
     */
    AIX("AIX"),

    /**
     * Android
     */
    ANDROID("Android"),

    /**
     * GNU operating systems
     */
    GNU("GNU"),

    /**
     * Debian GNU/kFreeBSD
     */
    KFREEBSD("GNU/kFreeBSD"),

    /**
     * NetBSD
     */
    NETBSD("NetBSD"),

    /**
     * An unspecified system
     */
    UNKNOWN("Unknown");

    private final String name;

    PlatformEnum(String name) {
        this.name = name;
    }

    /**
     * Gets the friendly name of the platform
     * @return The friendly name of the platform
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the friendly name of the specified JNA Platform type
     *
     * @param osType
     *            The constant returned from JNA's
     *            {@link com.sun.jna.Platform#getOSType()} method.
     * @return the friendly name of the specified JNA Platform type
     */
    public static String getName(int osType) {
        return getValue(osType).getName();
    }

    /**
     * Gets the value corresponding to the specified JNA Platform type
     *
     * @param osType
     *            The constant returned from JNA's
     *            {@link com.sun.jna.Platform#getOSType()} method.
     * @return the value corresponding to the specified JNA Platform type
     */
    public static PlatformEnum getValue(int osType) {
        if (osType < 0 || osType >= UNKNOWN.ordinal()) {
            return UNKNOWN;
        }
        return values()[osType];
    }
}