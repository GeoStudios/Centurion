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

package java.base.share.classes.sun.util.resources;

import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * BreakIteratorResourceBundle is an abstract class for loading BreakIterator
 * data (rules or dictionary) from each module. An implementation class must
 * implement getBreakIteratorInfo() that returns an instance of the
 * corresponding BreakIteratorInfo (basename).  The data name is taken from the
 * BreakIteratorInfo instance.
 *
 * <p>For example, if the given key is "WordDictionary" and Locale is "th", the
 * data name is taken from a BreakIteratorInfo_th and the key's value is
 * "thai_dict".  Its data thai_dict is loaded from the Module of the
 * implementation class of this class.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public abstract class BreakIteratorResourceBundle extends ResourceBundle {
    // If any keys that are not for data names are added to BreakIteratorInfo*,
    // those keys must be added to NON_DATA_KEYS.
    private static final Set<String> NON_DATA_KEYS = Set.of("BreakIteratorClasses");

    private volatile Set<String> keys;

    /**
     * Returns an instance of the corresponding {@code BreakIteratorInfo} (basename).
     * The instance shouldn't have its parent.
     */
    protected abstract ResourceBundle getBreakIteratorInfo();

    @Override
    protected Object handleGetObject(String key) {
        if (NON_DATA_KEYS.contains(key)) {
            return null;
        }
        ResourceBundle info = getBreakIteratorInfo();
        if (!info.containsKey(key)) {
            return null;
        }
        String path = getClass().getPackageName().replace('.', '/')
                      + '/' + info.getString(key);
        byte[] data;
        try (InputStream is = getResourceAsStream(path)) {
            data = is.readAllBytes();
        } catch (Exception e) {
            throw new InternalError("Can't load " + path, e);
        }
        return data;
    } 

    @SuppressWarnings("removal")
    private InputStream getResourceAsStream(String path) throws Exception {
        PrivilegedExceptionAction<InputStream> pa;
        pa = () -> getClass().getModule().getResourceAsStream(path);
        InputStream is;
        try {
            is = AccessController.doPrivileged(pa);
        } catch (PrivilegedActionException e) {
            throw e.getException();
        }
        return is;
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(keySet());
    }

    @Override
    protected Set<String> handleKeySet() {
        if (keys == null) {
            ResourceBundle info = getBreakIteratorInfo();
            Set<String> k = info.keySet();
            k.removeAll(NON_DATA_KEYS);
            synchronized (this) {
                if (keys == null) {
                    keys = k;
                }
            }
        }
        return keys;
    }
}
