/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.org.objectweb.asm.util;

import java.util.Map;
import jdk.internal.org.objectweb.asm.Label;

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

