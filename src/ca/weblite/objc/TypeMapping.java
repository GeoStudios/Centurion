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

package ca.weblite.objc;

/**
 * Interface to be implemented by objects that map Java types to Objective-C
 * types.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */
public interface TypeMapping {
    /**
     * Converts a C variable to the corresponding Java variable given the
     * context of the specified signature.
     *
     * @param cVar The C variable to be converted.
     * @param signature The signature that tells what type of variable we are dealing with according to <a href="https://developer.apple.com/library/mac/#documentation/Cocoa/Conceptual/ObjCRuntimeGuide/Articles/ocrtTypeEncodings.html">Objective-C Type Encoding</a> conventions.
     * @param root The root TypeMapping object
     * @return The converted Java object.
     */
    public Object cToJ(Object cVar, String signature, TypeMapping root);

    /**
     * Converts a Java variable to the corresponding Java variable given the
     * context of the specified signature.
     *
     * @param jVar The Java variable to be converted.
     * @param signature The signature that tells what type of variable we are dealing with according to <a href="https://developer.apple.com/library/mac/#documentation/Cocoa/Conceptual/ObjCRuntimeGuide/Articles/ocrtTypeEncodings.html">Objective-C Type Encoding</a> conventions.
     * @param root a {@link ca.weblite.objc.TypeMapping} object.
     * @return The converted C variable
     */
    public Object jToC(Object jVar, String signature, TypeMapping root);
}