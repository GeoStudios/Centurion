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

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;

import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;

/**
 * An {@link jdk.internal.org.objectweb.asm.Attribute} that can generate the ASM code to create an equivalent
 * attribute.
 *
 * @author Eugene Kuleshov
 */
// DontCheck(AbbreviationAsWordInName): can't be renamed (for backward binary compatibility).
public interface ASMifierSupport {

    /**
      * Generates the ASM code to create an attribute equal to this attribute.
      *
      * @param outputBuilder where the generated code must be appended.
      * @param visitorVariableName the name of the visitor variable in the produced code.
      * @param labelNames the names of the labels in the generated code.
      */
    void asmify(
            StringBuilder outputBuilder, String visitorVariableName, Map<Label, String> labelNames);
}

