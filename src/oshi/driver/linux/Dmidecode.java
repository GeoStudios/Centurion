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

package oshi.driver.linux;

import oshi.annotation.concurrent.ThreadSafe;

/**
 * Utility to read info from {@code dmidecode}
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

@ThreadSafe
public final class Dmidecode {

    private Dmidecode() {
    }

    // $ sudo dmidecode -t bios
    // # dmidecode 2.11
    // SMBIOS 2.4 present.
    //
    // Handle 0x0000, DMI type 0, 24 bytes
    // BIOS Information
    // Vendor: Phoenix Technologies LTD
    // Version: 6.00
    // Release Date: 07/02/2015
    // Address: 0xEA5E0
    // Runtime Size: 88608 bytes
    // ROM Size: 64 kB
    // Characteristics:
    // ISA is supported
    // PCI is supported
    // PC Card (PCMCIA) is supported
    // PNP is supported
    // APM is supported
    // BIOS is upgradeable
    // BIOS shadowing is allowed
    // ESCD support is available
    // Boot from CD is supported
    // Selectable boot is supported
    // EDD is supported
    // Print screen service is supported (int 5h)
    // 8042 keyboard services are supported (int 9h)
    // Serial services are supported (int 14h)
    // Printer services are supported (int 17h)
    // CGA/mono video services are supported (int 10h)
    // ACPI is supported
    // Smart battery is supported
    // BIOS boot specification is supported
    // Function key-initiated network boot is supported
    // Targeted content distribution is supported
    // BIOS Revision: 4.6
    // Firmware Revision: 0.0

    /**
     * Query the serial number from dmidecode
     *
     * @return The serial number if available, null otherwise
     */
    public static String querySerialNumber() {
        // If root privileges this will work
        String marker = "Serial Number:";
        for (String checkLine : ExecutingCommand.runNative("dmidecode -t system")) {
            if (checkLine.contains(marker)) {
                return checkLine.split(marker)[1].trim();
            }
        }
        return null;
    }

    /**
     * Query the UUID from dmidecode
     *
     * @return The UUID if available, null otherwise
     */
    public static String queryUUID() {
        // If root privileges this will work
        String marker = "UUID:";
        for (String checkLine : ExecutingCommand.runNative("dmidecode -t system")) {
            if (checkLine.contains(marker)) {
                return checkLine.split(marker)[1].trim();
            }
        }
        return null;
    }

    /**
     * Query the name and revision from dmidecode
     *
     * @return The a pair containing the name and revision if available, null values
     *         in the pair otherwise
     */
    public static Pair<String, String> queryBiosNameRev() {
        String biosName = null;
        String revision = null;

        final String biosMarker = "SMBIOS";
        final String revMarker = "Bios Revision:";

        // Requires root, may not return anything
        for (final String checkLine : ExecutingCommand.runNative("dmidecode -t bios")) {
            if (checkLine.contains(biosMarker)) {
                String[] biosArr = ParseUtil.whitespaces.split(checkLine);
                if (biosArr.length >= 2) {
                    biosName = biosArr[0] + " " + biosArr[1];
                }
            }
            if (checkLine.contains(revMarker)) {
                revision = checkLine.split(revMarker)[1].trim();
                // SMBIOS should be first line so if we're here we are done iterating
                break;
            }
        }
        return new Pair<>(biosName, revision);
    }
}