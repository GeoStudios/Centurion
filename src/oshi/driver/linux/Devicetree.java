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
 * Utility to read info from the devicetree.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

@ThreadSafe
public final class Devicetree {

    private Devicetree() {
    }

    /**
     * Query the model from the devicetree
     *
     * @return The model if available, null otherwise
     */
    public static String queryModel() {
        String modelStr = FileUtil.getStringFromFile("/sys/firmware/devicetree/base/model");
        if (!modelStr.isEmpty()) {
            return modelStr.replace("Machine: ", "");
        }
        return null;
    }
}