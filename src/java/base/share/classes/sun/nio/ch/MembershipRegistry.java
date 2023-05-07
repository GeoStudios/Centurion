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

package java.base.share.classes.sun.nio.ch;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.channels.MembershipKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple registry of membership keys for a MulticastChannel.
 *
 * Instances of this object are not safe by multiple concurrent threads.
 */

class MembershipRegistry {

    // map multicast group to list of keys
    private Map<InetAddress, List<MembershipKeyImpl>> groups;

    MembershipRegistry() {
    }

    /**
     * Checks registry for membership of the group on the given
     * network interface.
     */
    MembershipKey checkMembership(InetAddress group, NetworkInterface interf,
                                  InetAddress source)
    {
        if (groups != null) {
            List<MembershipKeyImpl> keys = groups.get(group);
            if (keys != null) {
                for (MembershipKeyImpl key: keys) {
                    if (key.networkInterface().equals(interf)) {
                        // already a member to receive all packets so return
                        // existing key or detect conflict
                        if (source == null) {
                            if (key.sourceAddress() == null)
                                return key;
                            throw new IllegalStateException("Already a member to receive all packets");
                        }

                        // already have source-specific membership so return key
                        // or detect conflict
                        if (key.sourceAddress() == null)
                            throw new IllegalStateException("Already have source-specific membership");
                        if (source.equals(key.sourceAddress()))
                            return key;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Add membership to the registry, returning a new membership key.
     */
    void add(MembershipKeyImpl key) {
        InetAddress group = key.group();
        List<MembershipKeyImpl> keys;
        if (groups == null) {
            groups = new HashMap<>();
            keys = null;
        } else {
            keys = groups.get(group);
        }
        if (keys == null) {
            keys = new ArrayList<>();
            groups.put(group, keys);
        }
        keys.add(key);
    }

    /**
     * Remove a key from the registry
     */
    void remove(MembershipKeyImpl key) {
        InetAddress group = key.group();
        List<MembershipKeyImpl> keys = groups.get(group);
        if (keys != null) {
            Iterator<MembershipKeyImpl> i = keys.iterator();
            while (i.hasNext()) {
                if (i.next() == key) {
                    i.remove();
                    break;
                }
            }
            if (keys.isEmpty()) {
                groups.remove(group);
            }
        }
    }

    @FunctionalInterface
    interface ThrowingConsumer<T, X extends Throwable> {
        void accept(T action) throws X;
    }

    /**
     * Invoke an action for each key in the registry
     */
     <X extends Throwable>
     void forEach(ThrowingConsumer<MembershipKeyImpl, X> action) throws X {
        if (groups != null) {
            for (List<MembershipKeyImpl> keys : groups.values()) {
                for (MembershipKeyImpl key : keys) {
                    action.accept(key);
                }
            }
        }
    }

    /**
     * Invalidate all keys in the registry
     */
    void invalidateAll() {
        forEach(MembershipKeyImpl::invalidate);
    }
}
