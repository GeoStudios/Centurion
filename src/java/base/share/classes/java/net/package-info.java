/java.base.share.classes.java.net
 java.base.share.classes.java.net Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.net/

/java.base.share.classes.java.netjava.base.share.classes.java.net
 java.base.share.classes.java.net Provides the classes for implementing networking applications.
 java.base.share.classes.java.net
 java.base.share.classes.java.net <p> The java.net package can be roughly divided in two sections:</p>
 java.base.share.classes.java.net <ul>
 java.base.share.classes.java.net     <li><p><i>A Low Level API</i>, which deals with the
 java.base.share.classes.java.net               following abstractions:</p>
 java.base.share.classes.java.net     <ul>
 java.base.share.classes.java.net       <li><p><i>Addresses</i>, which are networking identifiers,
 java.base.share.classes.java.net              like IP addresses.</p></li>
 java.base.share.classes.java.net       <li><p><i>Sockets</i>, which are basic bidirectional data communication
 java.base.share.classes.java.net              mechanisms.</p></li>
 java.base.share.classes.java.net       <li><p><i>Interfaces</i>, which describe network interfaces. </p></li>
 java.base.share.classes.java.net     </ul></li>
 java.base.share.classes.java.net     <li> <p><i>A High Level API</i>, which deals with the following
 java.base.share.classes.java.net          abstractions:</p>
 java.base.share.classes.java.net     <ul>
 java.base.share.classes.java.net       <li><p><i>URIs</i>, which represent
 java.base.share.classes.java.net               Universal Resource Identifiers.</p></li>
 java.base.share.classes.java.net       <li><p><i>URLs</i>, which represent
 java.base.share.classes.java.net               Universal Resource Locators.</p></li>
 java.base.share.classes.java.net       <li><p><i>Connections</i>, which represents connections to the resource
 java.base.share.classes.java.net               pointed to by <i>URLs</i>.</p></li>
 java.base.share.classes.java.net       </ul></li>
 java.base.share.classes.java.net </ul>
 java.base.share.classes.java.net <h2>Addresses</h2>
 java.base.share.classes.java.net <p>Addresses are used throughout the java.net APIs as either host
 java.base.share.classes.java.net    identifiers, or socket endpoint identifiers.</p>
 java.base.share.classes.java.net <p>The {@link java.net.InetAddress} class is the abstraction representing an
 java.base.share.classes.java.net    IP (Internet Protocol) address.  It has two subclasses:
 java.base.share.classes.java.net <ul>
 java.base.share.classes.java.net       <li>{@link java.net.Inet4Address} for IPv4 addresses.</li>
 java.base.share.classes.java.net       <li>{@link java.net.Inet6Address} for IPv6 addresses.</li>
 java.base.share.classes.java.net </ul>
 java.base.share.classes.java.net <p>But, in most cases, there is no need to deal directly with the subclasses,
 java.base.share.classes.java.net    as the InetAddress abstraction should cover most of the needed
 java.base.share.classes.java.net    functionality.</p>
 java.base.share.classes.java.net <h3><b>About IPv6</b></h3>
 java.base.share.classes.java.net <p>Not all systems have support for the IPv6 protocol, and while the Java
 java.base.share.classes.java.net    networking stack will attempt to detect it and use it transparently when
 java.base.share.classes.java.net    available, it is also possible to disable its use with a system property.
 java.base.share.classes.java.net    In the case where IPv6 is not available, or explicitly disabled,
 java.base.share.classes.java.net    Inet6Address are not valid arguments for most networking operations any
 java.base.share.classes.java.net    more. While methods like {@link java.net.InetAddress#getByName} are
 java.base.share.classes.java.net    guaranteed not to return an Inet6Address when looking up host names, it
 java.base.share.classes.java.net    is possible, by passing literals, to create such an object. In which
 java.base.share.classes.java.net    case, most methods, when called with an Inet6Address will throw an
 java.base.share.classes.java.net    Exception.</p>
 java.base.share.classes.java.net <h2>Sockets</h2>
 java.base.share.classes.java.net <p>Sockets are means to establish a communication link between machines over
 java.base.share.classes.java.net    the network. The java.net package provides 4 kinds of Sockets:</p>
 java.base.share.classes.java.net <ul>
 java.base.share.classes.java.net       <li>{@link java.net.Socket} is a TCP client API, and will typically
 java.base.share.classes.java.net            be used to {@linkplain java.net.Socket#connect(SocketAddress)
 java.base.share.classes.java.net            connect} to a remote host.</li>
 java.base.share.classes.java.net       <li>{@link java.net.ServerSocket} is a TCP server API, and will
 java.base.share.classes.java.net            typically {@linkplain java.net.ServerSocket#accept accept}
 java.base.share.classes.java.net            connections from client sockets.</li>
 java.base.share.classes.java.net       <li>{@link java.net.DatagramSocket} is a UDP endpoint API and is used
 java.base.share.classes.java.net            to {@linkplain java.net.DatagramSocket#send send} and
 java.base.share.classes.java.net            {@linkplain java.net.DatagramSocket#receive receive}
 java.base.share.classes.java.net            {@linkplain java.net.DatagramPacket datagram packets}.</li>
 java.base.share.classes.java.net       <li>{@link java.net.MulticastSocket} is a subclass of
 java.base.share.classes.java.net            {@code DatagramSocket} used when dealing with multicast
 java.base.share.classes.java.net            groups.</li>
 java.base.share.classes.java.net </ul>
 java.base.share.classes.java.net <p>Sending and receiving with TCP sockets is done through InputStreams and
 java.base.share.classes.java.net    OutputStreams which can be obtained via the
 java.base.share.classes.java.net    {@link java.net.Socket#getInputStream} and
 java.base.share.classes.java.net    {@link java.net.Socket#getOutputStream} methods.</p>
 java.base.share.classes.java.net <h2>Interfaces</h2>
 java.base.share.classes.java.net <p>The {@link java.net.NetworkInterface} class provides APIs to browse and
 java.base.share.classes.java.net    query all the networking interfaces (e.g. ethernet connection or PPP
 java.base.share.classes.java.net    endpoint) of the local machine. It is through that class that you can
 java.base.share.classes.java.net    check if any of the local interfaces is configured to support IPv6.</p>
 java.base.share.classes.java.net <p>Note, all conforming implementations must support at least one
 java.base.share.classes.java.net    {@code NetworkInterface} object, which must either be connected to a
 java.base.share.classes.java.net    network, or be a "loopback" interface that can only communicate with
 java.base.share.classes.java.net    entities on the same machine.</p>
 java.base.share.classes.java.net
 java.base.share.classes.java.net <h2>High level API</h2>
 java.base.share.classes.java.net <p>A number of classes in the java.net package do provide for a much higher
 java.base.share.classes.java.net    level of abstraction and allow for easy access to resources on the
 java.base.share.classes.java.net    network. The classes are:
 java.base.share.classes.java.net <ul>
 java.base.share.classes.java.net       <li>{@link java.net.URI} is the class representing a
 java.base.share.classes.java.net            Universal Resource Identifier, as specified in RFC 2396.
 java.base.share.classes.java.net            As the name indicates, this is just an Identifier and doesn't
 java.base.share.classes.java.net            provide directly the means to access the resource.</li>
 java.base.share.classes.java.net       <li>{@link java.net.URL} is the class representing a
 java.base.share.classes.java.net            Universal Resource Locator, which is both an older concept for
 java.base.share.classes.java.net            URIs and a means to access the resources.</li>
 java.base.share.classes.java.net       <li>{@link java.net.URLConnection} is created from a URL and is the
 java.base.share.classes.java.net            communication link used to access the resource pointed by the
 java.base.share.classes.java.net            URL. This abstract class will delegate most of the work to the
 java.base.share.classes.java.net            underlying protocol handlers like http or https.</li>
 java.base.share.classes.java.net       <li>{@link java.net.HttpURLConnection} is a subclass of URLConnection
 java.base.share.classes.java.net            and provides some additional functionalities specific to the
 java.base.share.classes.java.net            HTTP protocol. This API has been superseded by the newer
 java.base.share.classes.java.net            {@linkplain java.net.http/java.net.http HTTP Client API}.</li>
 java.base.share.classes.java.net </ul>
 java.base.share.classes.java.net <p>The recommended usage is to use {@link java.net.URI} to identify
 java.base.share.classes.java.net    resources, then convert it into a {@link java.net.URL} when it is time to
 java.base.share.classes.java.net    access the resource. From that URL, you can either get the
 java.base.share.classes.java.net    {@link java.net.URLConnection} for fine control, or get directly the
 java.base.share.classes.java.net    InputStream.
 java.base.share.classes.java.net <p>Here is an example:</p>
 java.base.share.classes.java.net <pre>
 java.base.share.classes.java.net URI uri = new URI("http://www.example.com/");
 java.base.share.classes.java.net URL url = uri.toURL();
 java.base.share.classes.java.net InputStream in = url.openStream();
 java.base.share.classes.java.net </pre>
 java.base.share.classes.java.net <h2>Protocol Handlers</h2>
 java.base.share.classes.java.net As mentioned, URL and URLConnection rely on protocol handlers which must be
 java.base.share.classes.java.net present, otherwise an Exception is thrown. This is the major difference with
 java.base.share.classes.java.net URIs which only identify resources, and therefore don't need to have access
 java.base.share.classes.java.net to the protocol handler. So, while it is possible to create an URI with any
 java.base.share.classes.java.net kind of protocol scheme (e.g. {@code myproto://myhost.mydomain/resource/}),
 java.base.share.classes.java.net a similar URL will try to instantiate the handler for the specified protocol;
 java.base.share.classes.java.net if it doesn't exist an exception will be thrown.
 java.base.share.classes.java.net <p>By default the protocol handlers are loaded dynamically from the default
 java.base.share.classes.java.net    location. It is, however, possible to deploy additional protocols handlers
 java.base.share.classes.java.net    as {@link java.util.ServiceLoader services}. Service providers of type
 java.base.share.classes.java.net    {@linkplain java.net.spi.URLStreamHandlerProvider} are located at
 java.base.share.classes.java.net    runtime, as specified in the {@linkplain
 java.base.share.classes.java.net    java.net.URL#URL(String,String,int,String) URL constructor}.
 java.base.share.classes.java.net <h2>Additional Specification</h2>
 java.base.share.classes.java.net <ul>
 java.base.share.classes.java.net       <li><a href="doc-files/net-properties.html">
 java.base.share.classes.java.net            Networking System Properties</a></li>
 java.base.share.classes.java.net </ul>
 java.base.share.classes.java.net
 java.base.share.classes.java.net @since 1.0
 java.base.share.classes.java.net/
@SuppressWarnings("doclint:reference") // cross-module links
package java.net;
