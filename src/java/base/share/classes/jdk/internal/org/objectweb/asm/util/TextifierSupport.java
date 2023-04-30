/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;

import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;

/**
 * An {@link jdk.internal.org.objectweb.asm.Attribute} that can print a readable representation of itself.
 *
 * @author Eugene Kuleshov
 */
public interface TextifierSupport {

    /**
      * Generates a human readable representation of this attribute.
      *
      * @param outputBuilder where the human representation of this attribute must be appended.
      * @param labelNames the human readable names of the labels.
      */
    void textify(StringBuilder outputBuilder, Map<Label, String> labelNames);
}

