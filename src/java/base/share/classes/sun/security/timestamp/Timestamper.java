/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.timestamp;

import java.io.IOException;

/**
 * A timestamping service which conforms to the Time-Stamp Protocol (TSP)
 * defined in:
 * <a href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</a>.
 * Individual timestampers may communicate with a Timestamping Authority (TSA)
 * over different transport mechanisms. TSP permits at least the following
 * transports: HTTP, Internet mail, file-based and socket-based.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 * @see HttpTimestamper
 */
public interface Timestamper {

    /*
     * Connects to the TSA and requests a timestamp.
     *
     * @param tsQuery The timestamp query.
     * @return The result of the timestamp query.
     * @throws IOException The exception is thrown if a problem occurs while
     *         communicating with the TSA.
     */
    TSResponse generateTimestamp(TSRequest tsQuery) throws IOException;
}
