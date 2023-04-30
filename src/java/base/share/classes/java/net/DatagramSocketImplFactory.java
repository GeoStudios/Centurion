/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

/**
 * This interface defines a factory for datagram socket implementations. It
 * is used by the classes {@code DatagramSocket} to create actual socket
 * implementations.
 *
 * @author  Yingxian Wang
 * @see     java.base.share.classes.java.net.DatagramSocket
 * @since   1.3
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
