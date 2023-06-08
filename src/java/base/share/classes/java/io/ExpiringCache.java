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

package java.base.share.classes.java.io;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

final class ExpiringCache {

    private static final int QUERY_OVERFLOW = 300;
    private static final int MAX_ENTRIES = 200;
    private final long millisUntilExpiration;
    private final Map<String,Entry> map;

    // Clear out old entries every few queries
    private int queryCount;

    static final class Entry {
        private long timestamp;
        private String val;

        Entry(long timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }

        long   timestamp()                  { return timestamp;           }
        void   setTimestamp(long timestamp) { this.timestamp = timestamp; }

        String val()                        { return val;                 }
        void   setVal(String val)           { this.val = val;             }
    }

    ExpiringCache() {
        this(30000);
    }

    @SuppressWarnings("serial")
    ExpiringCache(long millisUntilExpiration) {
        this.millisUntilExpiration = millisUntilExpiration;
        map = new LinkedHashMap<>() {
            protected boolean removeEldestEntry(Map.Entry<String,Entry> eldest) {
              return size() > MAX_ENTRIES;
            }
          };
    }

    synchronized String get(String key) {
        if (++queryCount >= QUERY_OVERFLOW) {
            cleanup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            return entry.val();
        }
        return null;
    }

    synchronized void put(String key, String val) {
        if (++queryCount >= QUERY_OVERFLOW) {
            cleanup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            entry.setTimestamp(System.currentTimeMillis());
            entry.setVal(val);
        } else {
            map.put(key, new Entry(System.currentTimeMillis(), val));
        }
    }

    synchronized void clear() {
        map.clear();
    }

    private Entry entryFor(String key) {
        Entry entry = map.get(key);
        if (entry != null) {
            long delta = System.currentTimeMillis() - entry.timestamp();
            if (delta < 0 || delta >= millisUntilExpiration) {
                map.remove(key);
                entry = null;
            }
        }
        return entry;
    }

    private void cleanup() {
        Set<String> keySet = map.keySet();
        // Avoid ConcurrentModificationExceptions
        String[] keys = new String[keySet.size()];
        int i = 0;
        for (String key: keySet) {
            keys[i++] = key;
        }
        for (int j = 0; j < keys.length; j++) {
            entryFor(keys[j]);
        }
        queryCount = 0;
    }
}
