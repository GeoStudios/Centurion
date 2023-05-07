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

package java.base.share.classes.java.net.spi;

import java.lang.annotation.Native;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

/**
 * This interface defines operations for looking up host names and IP addresses.
 * {@link InetAddress} delegates all lookup operations to the <i>system-wide
 * resolver</i>.
 *
 * <p> The <i>system-wide resolver</i> can be customized by
 * <a href="InetAddressResolverProvider.html#system-wide-resolver">
 * deploying an implementation</a> of {@link InetAddressResolverProvider}.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface InetAddressResolver {

    /**
     * Given the name of a host, returns a stream of IP addresses of the requested
     * address family associated with a provided hostname.
     *
     * <p> {@code host} should be a machine name, such as "{@code www.example.com}",
     * not a textual representation of its IP address. No validation is performed on
     * the given {@code host} name: if a textual representation is supplied, the name
     * resolution is likely to fail and {@link UnknownHostException} may be thrown.
     *
     * <p> The address family type and addresses order are specified by the
     * {@code LookupPolicy} instance. Lookup operation characteristics could be
     * acquired with {@link LookupPolicy#characteristics()}.
     * If {@link InetAddressResolver.LookupPolicy#IPV4} and
     * {@link InetAddressResolver.LookupPolicy#IPV6} characteristics provided then this
     * method returns addresses of both IPV4 and IPV6 families.
     *
     * @param host         the specified hostname
     * @param lookupPolicy the address lookup policy
     * @return a stream of IP addresses for the requested host
     * @throws NullPointerException if either parameter is {@code null}
     * @throws UnknownHostException if no IP address for the {@code host} could be found
     * @see LookupPolicy
     */
    Stream<InetAddress> lookupByName(String host, LookupPolicy lookupPolicy) throws UnknownHostException;

    /**
     * Lookup the host name corresponding to the raw IP address provided.
     *
     * <p> {@code addr} argument is in network byte order: the highest order byte of the address
     * is in {@code addr[0]}.
     *
     * <p> IPv4 address byte array must be 4 bytes long and IPv6 byte array
     * must be 16 bytes long.
     *
     * @param addr byte array representing a raw IP address
     * @return {@code String} representing the host name mapping
     * @throws UnknownHostException     if no host name is found for the specified IP address
     * @throws IllegalArgumentException if the length of the provided byte array doesn't correspond
     *                                  to a valid IP address length
     * @throws NullPointerException     if addr is {@code null}
     */
    String lookupByAddress(byte[] addr) throws UnknownHostException;

    /**
     * A {@code LookupPolicy} object describes characteristics that can be applied to a lookup operation.
     * In particular, it is used to specify the ordering and which filtering should be performed when
     * {@linkplain InetAddressResolver#lookupByName(String, LookupPolicy) looking up host addresses}.
     *
     * <p> The default platform-wide lookup policy is constructed by consulting
     * <a href="doc-files/net-properties.html#Ipv4IPv6">System Properties</a> which affect
     * how IPv4 and IPv6 addresses are returned.
     *
     * @since 18
     */
    final class LookupPolicy {

        /**
         * Characteristic value signifying if IPv4 addresses need to be queried during lookup.
         */
        @Native
        public static final int IPV4 = 1 << 0;

        /**
         * Characteristic value signifying if IPv6 addresses need to be queried during lookup.
         */
        @Native
        public static final int IPV6 = 1 << 1;

        /**
         * Characteristic value signifying if IPv4 addresses should be returned
         * first by {@code InetAddressResolver}.
         */
        @Native
        public static final int IPV4_FIRST = 1 << 2;

        /**
         * Characteristic value signifying if IPv6 addresses should be returned
         * first by {@code InetAddressResolver}.
         */
        @Native
        public static final int IPV6_FIRST = 1 << 3;

        private final int characteristics;

        private LookupPolicy(int characteristics) {
            this.characteristics = characteristics;
        }

        /**
         * This factory method creates a {@link LookupPolicy LookupPolicy} instance with
         * the given {@code characteristics} value.
         *
         * <p> The {@code characteristics} value is an integer bit mask which defines
         * parameters of a forward lookup operation. These parameters define at least:
         * <ul>
         *     <li>the family type of the returned addresses</li>
         *     <li>the order in which a {@linkplain InetAddressResolver resolver}
         *         implementation should return its results</li>
         * </ul>
         *
         * <p> To request addresses of specific family types the following bit masks can be combined:
         * <ul>
         *     <li>{@link LookupPolicy#IPV4}: to request IPv4 addresses</li>
         *     <li>{@link LookupPolicy#IPV6}: to request IPv6 addresses</li>
         * </ul>
         * <br>It is an error if neither {@link LookupPolicy#IPV4} or {@link LookupPolicy#IPV6} are set.
         *
         * <p> To request a specific ordering of the results:
         * <ul>
         *     <li>{@link LookupPolicy#IPV4_FIRST}: return IPv4 addresses before any IPv6 address</li>
         *     <li>{@link LookupPolicy#IPV6_FIRST}: return IPv6 addresses before any IPv4 address</li>
         * </ul>
         * <br>If neither {@link LookupPolicy#IPV4_FIRST} or {@link LookupPolicy#IPV6_FIRST} are set it
         * implies <a href="{@docRoot}/java.base/java/net/doc-files/net-properties.html#Ipv4IPv6">"system"</a>
         * order of addresses.
         * It is an error to request both {@link LookupPolicy#IPV4_FIRST} and {@link LookupPolicy#IPV6_FIRST}.
         *
         * @param characteristics a value which represents the set of lookup characteristics
         * @return an instance of {@code InetAddressResolver.LookupPolicy}
         * @throws IllegalArgumentException if an illegal characteristics bit mask is provided
         * @see InetAddressResolver#lookupByName(String, LookupPolicy)
         */
        public static LookupPolicy of(int characteristics) {
            // At least one type of addresses should be requested
            if ((characteristics & IPV4) == 0 && (characteristics & IPV6) == 0) {
                throw new IllegalArgumentException("No address type specified");
            }

            // Requested order of addresses couldn't be determined
            if ((characteristics & IPV4_FIRST) != 0 && (characteristics & IPV6_FIRST) != 0) {
                throw new IllegalArgumentException("Addresses order cannot be determined");
            }

            // If IPv4 addresses requested to be returned first then they should be requested too
            if ((characteristics & IPV4_FIRST) != 0 && (characteristics & IPV4) == 0) {
                throw new IllegalArgumentException("Addresses order and type do not match");
            }

            // If IPv6 addresses requested to be returned first then they should be requested too
            if ((characteristics & IPV6_FIRST) != 0 && (characteristics & IPV6) == 0) {
                throw new IllegalArgumentException("Addresses order and type do not match");
            }
            return new LookupPolicy(characteristics);
        }

        /**
         * Returns the set of characteristics of this lookup policy.
         *
         * @return a characteristics value
         * @see InetAddressResolver#lookupByName(String, LookupPolicy)
         */
        public int characteristics() {
            return characteristics;
        }
    }
}
