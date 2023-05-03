/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.jca;

/**
 * Simple class encapsulating a service type and algorithm for lookup.
 * Put in a separate file rather than nested to allow import via ...jca.*.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public final class ServiceId {

    public final String type;
    public final String algorithm;

    public ServiceId(String type, String algorithm) {
        this.type = type;
        this.algorithm = algorithm;
    }

}
