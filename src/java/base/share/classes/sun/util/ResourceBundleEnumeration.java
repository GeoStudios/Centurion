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

package java.base.share.classes.sun.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implements an Enumeration that combines elements from a Set and
 * an Enumeration. Used by ListResourceBundle and PropertyResourceBundle.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class ResourceBundleEnumeration implements Enumeration<String> {

    Set<String> set;
    Iterator<String> iterator;
    Enumeration<String> enumeration; // may remain null

    /**
     * Constructs a resource bundle enumeration.
     * @param set an set providing some elements of the enumeration
     * @param enumeration an enumeration providing more elements of the enumeration.
     *        enumeration may be null.
     */
    public ResourceBundleEnumeration(Set<String> set, Enumeration<String> enumeration) {
        this.set = set;
        this.iterator = set.iterator();
        this.enumeration = enumeration;
    }

    String next = null;

    public boolean hasMoreElements() {
        if (next == null) {
            if (iterator.hasNext()) {
                next = iterator.next();
            } else if (enumeration != null) {
                while (next == null && enumeration.hasMoreElements()) {
                    next = enumeration.nextElement();
                    if (set.contains(next)) {
                        next = null;
                    }
                }
            }
        }
        return next != null;
    }

    public String nextElement() {
        if (hasMoreElements()) {
            String result = next;
            next = null;
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }
}
