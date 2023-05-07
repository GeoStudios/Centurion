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

package java.base.share.classes.jdk.internal.org.objectweb.asm.commons;

import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;

/**
 * A code generator for switch statements.
 *
 * @author Juozas Baliuka
 * @author Chris Nokleberg
 * @author Eric Bruneton
 */
public interface TableSwitchGenerator {

    /**
      * Generates the code for a switch case.
      *
      * @param key the switch case key.
      * @param end a label that corresponds to the end of the switch statement.
      */
    void generateCase(int key, Label end);

    /** Generates the code for the default switch case. */
    void generateDefault();
}

