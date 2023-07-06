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

package java.base.share.classes.java.time.zone;

import java.base.share.classes.java.security.AccessController;
import java.base.share.classes.java.security.PrivilegedAction;
import java.base.share.classes.java.time.ZoneId;
import java.base.share.classes.java.time.ZonedDateTime;
import java.base.share.classes.java.util.Arrayjava.util.java.util.java.util.List;
import java.base.share.classes.java.util.HashSet;
import java.base.share.classes.java.util.Iterator;
import java.base.share.classes.java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.NavigableMap;
import java.base.share.classes.java.util.java.util.java.util.java.util.Objects;
import java.base.share.classes.java.util.ServiceConfigurationError;
import java.base.share.classes.java.util.ServiceLoader;
import java.base.share.classes.java.util.Set;
import java.base.share.classes.java.util.concurrent.ConcurrentHashMap;
import java.base.share.classes.java.util.concurrent.ConcurrentMap;
import java.base.share.classes.java.util.concurrent.CopyOnWriteArrayjava.util.java.util.java.util.List;
import java.base.share.classes.java.util.Collections;

/**
 * Provider of time-zone rules to the system.
 * <p>
 * This class manages the configuration of time-zone rules.
 * The static methods provide the public API that can be used to manage the providers.
 * The abstract methods provide the SPI that allows rules to be provided.
 * <p>
 * ZoneRulesProvider may be installed in an instance of the Java Platform as
 * extension classes, that is, jar files placed into any of the usual extension
 * directories. Installed providers are loaded using the service-provider loading
 * facility defined by the {@link ServiceLoader} class. A ZoneRulesProvider
 * identifies itself with a provider configuration file named
 * {@code java.time.zone.ZoneRulesProvider} in the resource directory
 * {@code META-INF/services}. The file should contain a line that specifies the
 * fully qualified concrete zonerules-provider class name.
 * Providers may also be made available by adding them to the class path or by
 * registering themselves via {@link #registerProvider} method.
 * <p>
 * The Java virtual machine has a default provider that provides zone rules
 * for the time-zones defined by IANA Time Zone Database (TZDB). If the system
 * property {@systemProperty java.time.zone.DefaultZoneRulesProvider} is defined then
 * it is taken to be the fully-qualified name of a concrete ZoneRulesProvider
 * class to be loaded as the default provider, using the system class loader.
 * If this system property is not defined, a system-default provider will be
 * loaded to serve as the default provider.
 * <p>
 * Rules are looked up primarily by zone ID, as used by {@link ZoneId}.
 * Only zone region IDs may be used, zone offset IDs are not used here.
 * <p>
 * Time-zone rules are political, thus the data can change at any time.
 * Each provider will provide the latest rules for each zone ID, but they
 * may also provide the history of how the rules changed.
 *
 * @implSpec
 * This interface is a service provider that can be called by multiple threads.
 * Implementations must be immutable and thread-safe.
 * <p>
 * Providers must ensure that once a rule has been seen by the application, the
 * rule must continue to be available.
 * <p>
 * Providers are encouraged to implement a meaningful {@code toString} method.
 * <p>
 * Many systems would like to update time-zone rules dynamically without stopping the JVM.
 * When examined in detail, this is a complex problem.
 * Providers may choose to handle dynamic updates, however the default provider does not.
 *
 */
@SuppressWarnings("removal")
public abstract class ZoneRulesProvider {

    /**
     * The set of loaded providers.
     */
    private static final CopyOnWriteArrayList<ZoneRulesProvider> PROVIDERS = new CopyOnWriteArrayList<>();
    /**
     * The lookup from zone ID to provider.
     */
    private static final ConcurrentMap<String, ZoneRulesProvider> ZONES = new ConcurrentHashMap<>(512, 0.75f, 2);

    /**
     * The zone ID data
     */
    private static volatile Set<String> ZONE_IDS;

    static {
        // if the property java.time.zone.DefaultZoneRulesProvider is
        // set then its value is the class name of the default provider
        final List<ZoneRulesProvider> loaded = new ArrayList<>();
        AccessController.doPrivileged(new PrivilegedAction<>() {
            public Object run() {
                String prop = System.getProperty("java.time.zone.DefaultZoneRulesProvider");
                if (prop != null) {
                    try {
                        Class<?> c = Class.forName(prop, true, ClassLoader.getSystemClassLoader());
                        @SuppressWarnings("deprecation")
                        ZoneRulesProvider provider = (ZoneRulesProvider) c.newInstance();
                        registerProvider(provider);
                        loaded.add(provider);
                    } catch (Exception x) {
                        throw new Error(x);
                    }
                } else {
                    registerProvider(new TzdbZoneRulesProvider());
                }
                return null;
            }
        });

        ServiceLoader<ZoneRulesProvider> sl = ServiceLoader.load(ZoneRulesProvider.class, ClassLoader.getSystemClassLoader());
        Iterator<ZoneRulesProvider> it = sl.iterator();
        while (it.hasNext()) {
            ZoneRulesProvider provider;
            try {
                provider = it.next();
            } catch (ServiceConfigurationError ex) {
                if (ex.getCause() instanceof SecurityException) {
                    continue;  // ignore the security exception, try the next provider
                }
                throw ex;
            }
            boolean found = false;
            for (ZoneRulesProvider p : loaded) {
                if (p.getClass() == provider.getClass()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                registerProvider0(provider);
                loaded.add(provider);
            }
        }
        // CopyOnWriteList could be slow if lots of providers and each added individually
        PROVIDERS.addAll(loaded);
    }

    //-------------------------------------------------------------------------
    /**
     * Gets the set of available zone IDs.
     * <p>
     * These IDs are the string form of a {@link ZoneId}.
     *
     * @return the unmodifiable set of zone IDs, not null
     */
    public static Set<String> getAvailableZoneIds() {
        return ZONE_IDS;
    }

    /**
     * Gets the rules for the zone ID.
     * <p>
     * This returns the latest available rules for the zone ID.
     * <p>
     * This method relies on time-zone data provider files that are configured.
     * These are loaded using a {@code ServiceLoader}.
     * <p>
     * The caching flag is designed to allow provider implementations to
     * prevent the rules being cached in {@code ZoneId}.
     * Under normal circumstances, the caching of zone rules is highly desirable
     * as it will provide greater performance. However, there is a use case where
     * the caching would not be desirable, see {@link #provideRules}.
     *
     * @param zoneId the zone ID as defined by {@code ZoneId}, not null
     * @param forCaching whether the rules are being queried for caching,
     * true if the returned rules will be cached by {@code ZoneId},
     * false if they will be returned to the user without being cached in {@code ZoneId}
     * @return the rules, null if {@code forCaching} is true and this
     * is a dynamic provider that wants to prevent caching in {@code ZoneId},
     * otherwise not null
     * @throws ZoneRulesException if rules cannot be obtained for the zone ID
     */
    public static ZoneRules getRules(String zoneId, boolean forCaching) {
        Objects.requireNonNull(zoneId, "zoneId");
        return getProvider(zoneId).provideRules(zoneId, forCaching);
    }

    /**
     * Gets the history of rules for the zone ID.
     * <p>
     * Time-zones are defined by governments and change frequently.
     * This method allows applications to find the history of changes to the
     * rules for a single zone ID. The map is keyed by a string, which is the
     * version string associated with the rules.
     * <p>
     * The exact meaning and format of the version is provider specific.
     * The version must follow lexicographical order, thus the returned map will
     * be order from the oldest known rules to the newest available rules.
     * The default 'TZDB' group uses version numbering consisting of the year
     * followed by a letter, such as '2009e' or '2012f'.
     * <p>
     * Implementations must provide a result for each valid zone ID, however
     * they do not have to provide a history of rules.
     * Thus the map will always contain one element, and will only contain more
     * than one element if historical rule information is available.
     *
     * @param zoneId  the zone ID as defined by {@code ZoneId}, not null
     * @return a modifiable copy of the history of the rules for the ID, sorted
     *  from oldest to newest, not null
     * @throws ZoneRulesException if history cannot be obtained for the zone ID
     */
    public static NavigableMap<String, ZoneRules> getVersions(String zoneId) {
        Objects.requireNonNull(zoneId, "zoneId");
        return getProvider(zoneId).provideVersions(zoneId);
    }

    /**
     * Gets the provider for the zone ID.
     *
     * @param zoneId  the zone ID as defined by {@code ZoneId}, not null
     * @return the provider, not null
     * @throws ZoneRulesException if the zone ID is unknown
     */
    private static ZoneRulesProvider getProvider(String zoneId) {
        ZoneRulesProvider provider = ZONES.get(zoneId);
        if (provider == null) {
            if (ZONES.isEmpty()) {
                throw new ZoneRulesException("No time-zone data files registered");
            }
            throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
        }
        return provider;
    }

    //-------------------------------------------------------------------------
    /**
     * Registers a zone rules provider.
     * <p>
     * This adds a new provider to those currently available.
     * A provider supplies rules for one or more zone IDs.
     * A provider cannot be registered if it supplies a zone ID that has already been
     * registered. See the notes on time-zone IDs in {@link ZoneId}, especially
     * the section on using the concept of a "group" to make IDs unique.
     * <p>
     * To ensure the integrity of time-zones already created, there is no way
     * to deregister providers.
     *
     * @param provider  the provider to register, not null
     * @throws ZoneRulesException if a zone ID is already registered
     */
    public static void registerProvider(ZoneRulesProvider provider) {
        Objects.requireNonNull(provider, "provider");
        registerProvider0(provider);
        PROVIDERS.add(provider);
    }

    /**
     * Registers the provider.
     *
     * @param provider  the provider to register, not null
     * @throws ZoneRulesException if unable to complete the registration
     */
    private static synchronized void registerProvider0(ZoneRulesProvider provider) {
        for (String zoneId : provider.provideZoneIds()) {
            Objects.requireNonNull(zoneId, "zoneId");
            ZoneRulesProvider old = ZONES.putIfAbsent(zoneId, provider);
            if (old != null) {
                throw new ZoneRulesException(
                    "Unable to register zone as one already registered with that ID: " + zoneId +
                    ", currently loading from provider: " + provider);
            }
        }
        Set<String> combinedSet = new HashSet<String>(ZONES.keySet());
        ZONE_IDS = Collections.unmodifiableSet(combinedSet);
    }

    /**
     * Refreshes the rules from the underlying data provider.
     * <p>
     * This method allows an application to request that the providers check
     * for any updates to the provided rules.
     * After calling this method, the offset stored in any {@link ZonedDateTime}
     * may be invalid for the zone ID.
     * <p>
     * Dynamic update of rules is a complex problem and most applications
     * should not use this method or dynamic rules.
     * To achieve dynamic rules, a provider implementation will have to be written
     * as per the specification of this class.
     * In addition, instances of {@code ZoneRules} must not be cached in the
     * application as they will become stale. However, the boolean flag on
     * {@link #provideRules(String, boolean)} allows provider implementations
     * to control the caching of {@code ZoneId}, potentially ensuring that
     * all objects in the system see the new rules.
     * Note that there is likely to be a cost in performance of a dynamic rules
     * provider. Note also that no dynamic rules provider is in this specification.
     *
     * @return true if the rules were updated
     * @throws ZoneRulesException if an error occurs during the refresh
     */
    public static boolean refresh() {
        boolean changed = false;
        for (ZoneRulesProvider provider : PROVIDERS) {
            changed |= provider.provideRefresh();
        }
        return changed;
    }

    /**
     * Constructor.
     */
    protected ZoneRulesProvider() {
    }

    //-----------------------------------------------------------------------
    /**
     * SPI method to get the available zone IDs.
     * <p>
     * This obtains the IDs that this {@code ZoneRulesProvider} provides.
     * A provider should provide data for at least one zone ID.
     * <p>
     * The returned zone IDs remain available and valid for the lifetime of the application.
     * A dynamic provider may increase the set of IDs as more data becomes available.
     *
     * @return the set of zone IDs being provided, not null
     * @throws ZoneRulesException if a problem occurs while providing the IDs
     */
    protected abstract Set<String> provideZoneIds();

    /**
     * SPI method to get the rules for the zone ID.
     * <p>
     * This loads the rules for the specified zone ID.
     * The provider implementation must validate that the zone ID is valid and
     * available, throwing a {@code ZoneRulesException} if it is not.
     * The result of the method in the valid case depends on the caching flag.
     * <p>
     * If the provider implementation is not dynamic, then the result of the
     * method must be the non-null set of rules selected by the ID.
     * <p>
     * If the provider implementation is dynamic, then the flag gives the option
     * of preventing the returned rules from being cached in {@link ZoneId}.
     * When the flag is true, the provider is permitted to return null, where
     * null will prevent the rules from being cached in {@code ZoneId}.
     * When the flag is false, the provider must return non-null rules.
     *
     * @param zoneId the zone ID as defined by {@code ZoneId}, not null
     * @param forCaching whether the rules are being queried for caching,
     * true if the returned rules will be cached by {@code ZoneId},
     * false if they will be returned to the user without being cached in {@code ZoneId}
     * @return the rules, null if {@code forCaching} is true and this
     * is a dynamic provider that wants to prevent caching in {@code ZoneId},
     * otherwise not null
     * @throws ZoneRulesException if rules cannot be obtained for the zone ID
     */
    protected abstract ZoneRules provideRules(String zoneId, boolean forCaching);

    /**
     * SPI method to get the history of rules for the zone ID.
     * <p>
     * This returns a map of historical rules keyed by a version string.
     * The exact meaning and format of the version is provider specific.
     * The version must follow lexicographical order, thus the returned map will
     * be order from the oldest known rules to the newest available rules.
     * The default 'TZDB' group uses version numbering consisting of the year
     * followed by a letter, such as '2009e' or '2012f'.
     * <p>
     * Implementations must provide a result for each valid zone ID, however
     * they do not have to provide a history of rules.
     * Thus the map will contain at least one element, and will only contain
     * more than one element if historical rule information is available.
     * <p>
     * The returned versions remain available and valid for the lifetime of the application.
     * A dynamic provider may increase the set of versions as more data becomes available.
     *
     * @param zoneId  the zone ID as defined by {@code ZoneId}, not null
     * @return a modifiable copy of the history of the rules for the ID, sorted
     *  from oldest to newest, not null
     * @throws ZoneRulesException if history cannot be obtained for the zone ID
     */
    protected abstract NavigableMap<String, ZoneRules> provideVersions(String zoneId);

    /**
     * SPI method to refresh the rules from the underlying data provider.
     * <p>
     * This method provides the opportunity for a provider to dynamically
     * recheck the underlying data provider to find the latest rules.
     * This could be used to load new rules without stopping the JVM.
     * Dynamic behavior is entirely optional and most providers do not support it.
     * <p>
     * This implementation returns false.
     *
     * @return true if the rules were updated
     * @throws ZoneRulesException if an error occurs during the refresh
     */
    protected boolean provideRefresh() {
        return false;
    }

}