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
 * <p>StringMapping class.</p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */
public class StringMapping implements TypeMapping {
    /**
     * Singleton instance.
     */
    public static final StringMapping INSTANCE = new StringMapping();

    private StringMapping() { }

    @Override
    public Object cToJ(Object cVar, String signature, TypeMapping root) {
        return new Pointer((Long)cVar).getString(0);
    }

    @Override
    public Object jToC(Object jVar, String signature, TypeMapping root) {
        return jVar;
    }

}