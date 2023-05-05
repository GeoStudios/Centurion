/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * This interface defines a factory for datagram socket implementations. It
 * is used by the classes {@code DatagramSocket} to create actual socket
 * implementations.
 *
 * @see     java.base.share.classes.java.net.DatagramSocket
 * @since Java 2
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface DatagramSocketImplFactory {
    /**
     * Creates a new {@code DatagramSocketImpl} instance.
     *
     * @return  a new instance of {@code DatagramSocketImpl}.
     * @see     java.base.share.classes.java.net.DatagramSocketImpl
     */
    DatagramSocketImpl createDatagramSocketImpl();
}
