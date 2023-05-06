/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.net;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Basic SocketImpl that relies on the internal HTTP protocol handler
 * implementation to perform the HTTP tunneling and authentication. Once
 * connected, all socket operations delegate to a platform SocketImpl.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

/*package*/ @SuppressWarnings("removal") class HttpConnectSocketImpl extends DelegatingSocketImpl {

    private static final String httpURLClazzStr =
                                  "sun.net.www.protocol.http.HttpURLConnection";
    private static final String netClientClazzStr = "sun.net.NetworkClient";
    private static final String doTunnelingStr = "doTunneling";
    private static final Field httpField;
    private static final Field serverSocketField;
    private static final Method doTunneling;

    private final String server;
    private final Socket socket;
    private InetSocketAddress external_address;
    private HashMap<Integer, Object> optionsMap = new HashMap<>();

    static  {
        try {
            Class<?> httpClazz = Class.forName(httpURLClazzStr, true, null);
            httpField = httpClazz.getDeclaredField("http");
            doTunneling = httpClazz.getDeclaredMethod(doTunnelingStr);
            Class<?> netClientClazz = Class.forName(netClientClazzStr, true, null);
            serverSocketField = netClientClazz.getDeclaredField("serverSocket");

            java.security.AccessController.doPrivileged(
                new java.security.PrivilegedAction<>() {
                    public Void run() {
                        httpField.setAccessible(true);
                        serverSocketField.setAccessible(true);
                        return null;
                }
            });
        } catch (ReflectiveOperationException x) {
            throw new InternalError("Should not reach here", x);
        }
    }

    HttpConnectSocketImpl(Proxy proxy, SocketImpl delegate, Socket socket) {
        super(delegate);
        this.socket = socket;
        SocketAddress a = proxy.address();
        if ( !(a instanceof InetSocketAddress ad) )
            throw new IllegalArgumentException("Unsupported address type");

        server = ad.getHostString();
        port = ad.getPort();
    }

    @Override
    protected void connect(String host, int port) throws IOException {
        connect(new InetSocketAddress(host, port), 0);
    }

    @Override
    protected void connect(InetAddress address, int port) throws IOException {
        connect(new InetSocketAddress(address, port), 0);
    }

    @Override
    protected void connect(SocketAddress endpoint, int timeout)
        throws IOException
    {
        if (!(endpoint instanceof InetSocketAddress epoint))
            throw new IllegalArgumentException("Unsupported address type");
        String destHost = epoint.isUnresolved() ? epoint.getHostName()
                                                : epoint.getAddress().getHostAddress();
        final int destPort = epoint.getPort();

        SecurityManager security = System.getSecurityManager();
        if (security != null)
            security.checkConnect(destHost, destPort);

        if (destHost.contains(":"))
            destHost = "[" + destHost + "]";

        // Connect to the HTTP proxy server
        String urlString = "http://" + destHost + ":" + destPort;
        Socket httpSocket = privilegedDoTunnel(urlString, timeout);

        // Success!
        external_address = epoint;

        // close the original socket impl and release its descriptor
        close();

        // change Socket to use httpSocket's SocketImpl
        SocketImpl si = httpSocket.impl();
        socket.setImpl(si);

        // best effort is made to try and reset options previously set
        Set<Map.Entry<Integer,Object>> options = optionsMap.entrySet();
        try {
            for(Map.Entry<Integer,Object> entry : options) {
                si.setOption(entry.getKey(), entry.getValue());
            }
        } catch (IOException x) {  /* gulp! */  }
    }


    @Override
    protected void listen(int backlog) {
        throw new InternalError("should not get here");
    }

    @Override
    protected void accept(SocketImpl s) {
        throw new InternalError("should not get here");
    }

    @Override
    void reset() {
        throw new InternalError("should not get here");
    }

    @Override
    public void setOption(int opt, Object val) throws SocketException {
        delegate.setOption(opt, val);

        if (external_address != null)
            return;  // we're connected, just return

        // store options so that they can be re-applied to the impl after connect
        optionsMap.put(opt, val);
    }

    private Socket privilegedDoTunnel(final String urlString,
                                      final int timeout)
        throws IOException
    {
        try {
            return java.security.AccessController.doPrivileged(
                new java.security.PrivilegedExceptionAction<>() {
                    public Socket run() throws IOException {
                        return doTunnel(urlString, timeout);
                }
            });
        } catch (java.security.PrivilegedActionException pae) {
            throw (IOException) pae.getException();
        }
    }

    private Socket doTunnel(String urlString, int connectTimeout)
        throws IOException
    {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(server, port));
        @SuppressWarnings("deprecation")
        URL destURL = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) destURL.openConnection(proxy);
        conn.setConnectTimeout(connectTimeout);
        int timeout = (int) getOption(SocketOptions.SO_TIMEOUT);
        if (timeout > 0) {
            conn.setReadTimeout(timeout);
        }
        conn.connect();
        doTunneling(conn);
        try {
            Object httpClient = httpField.get(conn);
            return (Socket) serverSocketField.get(httpClient);
        } catch (IllegalAccessException x) {
            throw new InternalError("Should not reach here", x);
        }
    }

    private void doTunneling(HttpURLConnection conn) throws IOException {
        try {
            doTunneling.invoke(conn);
        } catch (ReflectiveOperationException x) {
            Throwable cause = x.getCause();
            if (cause instanceof IOException) {
                throw (IOException) cause;
            }
            throw new InternalError("Should not reach here", x);
        }
    }

    @Override
    protected InetAddress getInetAddress() {
        if (external_address != null)
            return external_address.getAddress();
        else
            return delegate.getInetAddress();
    }

    @Override
    protected int getPort() {
        if (external_address != null)
            return external_address.getPort();
        else
            return delegate.getPort();
    }
}
