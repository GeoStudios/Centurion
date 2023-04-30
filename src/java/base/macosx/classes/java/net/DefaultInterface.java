/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.java.net;

/**
 * Choose a network interface to be the default for
 * outgoing IPv6 traffic that does not specify a scope_id (and which needs one).
 * We choose the first interface that is up and is (in order of preference):
 * 1. neither loopback nor point to point
 * 2. point to point
 * 3. loopback
 * 4. none.
 * Platforms that do not require a default interface implement a dummy
 * that returns null.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

import java.base.share.classes.java.security.AccessController;
import java.base.share.classes.java.security.PrivilegedAction;
import java.base.share.classes.java.util.Enumeration;
import java.base.share.classes.java.io.IOException;

class DefaultInterface {

    private static final NetworkInterface defaultInterface =
        chooseDefaultInterface();

    static NetworkInterface getDefault() {
        return defaultInterface;
    }

    /**
     * Choose a default interface. This method returns the first interface that
     * is both "up" and supports multicast. This method chooses an interface in
     * order of preference, using the following algorithm:
     *
     * <pre>
     * Interfaces that are down, or don't support multicasting, are skipped.
     * In steps 1-4 below, PPP and loopback interfaces are skipped.
     *
     * 1. The first interface that has at least an IPv4 address, and an IPv6 address,
     *    and a non link-local IP address, is picked.
     *
     * 2. If none is found, then the first interface that has at least an
     *    IPv4 address, and an IPv6 address is picked.
     *
     * 3. If none is found, then the first interface that has at least a
     *    non link local IP address is picked.
     *
     * 4. If none is found, then the first non loopback and non PPP interface
     *    is picked.
     *
     * 5. If none is found then first PPP interface is picked.
     *
     * 6. If none is found, then the first loopback interface is picked.
     *
     * 7. If none is found, then null is returned.
     * </pre>
     *
     * @return  the chosen interface or {@code null} if there isn't a suitable
     *          default
     */
    private static NetworkInterface chooseDefaultInterface() {
        Enumeration<NetworkInterface> nifs;

        try {
           nifs = NetworkInterface.getNetworkInterfaces();
        } catch (IOException ignore) {
            // unable to enumerate network interfaces
            return null;
        }

        NetworkInterface preferred = null;
        NetworkInterface dual = null;
        NetworkInterface nonLinkLocal = null;
        NetworkInterface ppp = null;
        NetworkInterface loopback = null;

        while (nifs.hasMoreElements()) {
            NetworkInterface ni = nifs.nextElement();
            try {
                if (!ni.isUp() || !ni.supportsMulticast())
                    continue;

                boolean ip4 = false, ip6 = false, isNonLinkLocal = false;
                PrivilegedAction<Enumeration<InetAddress>> pa = ni::getInetAddresses;
                @SuppressWarnings("removal")
                Enumeration<InetAddress> addrs = AccessController.doPrivileged(pa);
                while (addrs.hasMoreElements()) {
                    InetAddress addr = addrs.nextElement();
                    if (!addr.isAnyLocalAddress()) {
                        if (addr instanceof Inet4Address) {
                            ip4 = true;
                        } else if (addr instanceof Inet6Address) {
                            ip6 = true;
                        }
                        if (!addr.isLinkLocalAddress()) {
                            isNonLinkLocal = true;
                        }
                    }
                }

                boolean isLoopback = ni.isLoopback();
                boolean isPPP = ni.isPointToPoint();
                if (!isLoopback && !isPPP) {
                    // found an interface that is not the loopback or a
                    // point-to-point interface
                    if (preferred == null) {
                        preferred = ni;
                    }
                    if (ip4 && ip6) {
                        if (isNonLinkLocal) return ni;
                        if (dual == null) dual = ni;
                    }
                    if (nonLinkLocal == null) {
                        if (isNonLinkLocal) nonLinkLocal = ni;
                    }
                }
                if (ppp == null && isPPP)
                    ppp = ni;
                if (loopback == null && isLoopback)
                    loopback = ni;

            } catch (IOException skip) { }
        }

        if (dual != null) {
            return dual;
        } else if (nonLinkLocal != null) {
            return nonLinkLocal;
        } else if (preferred != null) {
            return preferred;
        } else {
            return (ppp != null) ? ppp : loopback;
        }
    }
}
