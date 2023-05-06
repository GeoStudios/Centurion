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

package ca.weblite.objc.mappers;

import com.sun.jna.Pointer;

import ca.weblite.objc.TypeMapping;

/**
 * <p>PointerMapping class.</p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */
public class PointerMapping implements TypeMapping {
    /**
     * Singleton instance.
     */
    public static final PointerMapping INSTANCE = new PointerMapping();

    private PointerMapping() { }

    @Override
    public Object cToJ(Object cVar, String signature, TypeMapping root) {
        if (cVar instanceof Pointer) return cVar;
        return new Pointer((Long) cVar);
    }

    @Override
    public Object jToC(Object jVar, String signature, TypeMapping root) {
        // After some difficult deliberation I've decided that it is
        // better to require a Pointer or long to be passed in places
        // where Objective-C expects a pointer.
        return jVar;
    }
}