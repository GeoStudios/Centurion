/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.sun.net.www.protocol.http;


import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.ListIterator;
import java.util.HashMap;















/**
 */
public class AuthCacheImpl implements AuthCache {
    // No blocking IO is performed within the synchronized code blocks
    // in this class, so there is no need to convert this class to using
    // java.util.concurrent.locks
    HashMap<String,LinkedList<AuthCacheValue>> hashtable;

    public AuthCacheImpl () {
        hashtable = new HashMap<String,LinkedList<AuthCacheValue>>();
    }

    public void setMap (HashMap<String,LinkedList<AuthCacheValue>> map) {
        hashtable = map;
    }

    // put a value in map according to primary key + secondary key which
    // is the path field of AuthenticationInfo
    public synchronized void put (String pkey, AuthCacheValue value) {
        LinkedList<AuthCacheValue> list = hashtable.get (pkey);
        String skey = value.getPath();
        if (list == null) {
            list = new LinkedList<AuthCacheValue>();
            hashtable.put(pkey, list);
        }
        // Check if the path already exists or a super-set of it exists
        ListIterator<AuthCacheValue> iter = list.listIterator();
        while (iter.hasNext()) {
            AuthenticationInfo inf = (AuthenticationInfo)iter.next();
            if (inf.path == null || inf.path.startsWith (skey)) {
                iter.remove ();
            }
        }
        iter.add(value);
    }

    // get a value from map checking both primary
    // and secondary (urlpath) key

    public synchronized AuthCacheValue get (String pkey, String skey) {
        AuthenticationInfo result = null;
        LinkedList<AuthCacheValue> list = hashtable.get (pkey);
        if (list == null || list.size() == 0) {
            return null;
        }
        if (skey == null) {
            // list should contain only one element
            return list.get (0);
        }
        ListIterator<AuthCacheValue> iter = list.listIterator();
        while (iter.hasNext()) {
            AuthenticationInfo inf = (AuthenticationInfo)iter.next();
            if (skey.startsWith (inf.path)) {
                return inf;
            }
        }
        return null;
    }

    public synchronized void remove (String pkey, AuthCacheValue entry) {
        LinkedList<AuthCacheValue> list = hashtable.get (pkey);
        if (list == null) {
            return;
        }
        if (entry == null) {
            list.clear();
            return;
        }
        ListIterator<AuthCacheValue> iter = list.listIterator ();
        while (iter.hasNext()) {
            AuthenticationInfo inf = (AuthenticationInfo)iter.next();
            if (entry.equals(inf)) {
                iter.remove ();
            }
        }
    }
}
