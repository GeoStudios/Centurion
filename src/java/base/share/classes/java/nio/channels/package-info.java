/java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.nio.channels/

/java.base.share.classes.java.nio.channelsjava.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels Defines channels, which represent connections to entities that are capable of
 java.base.share.classes.java.nio.channels performing I/O operations, such as files and sockets; defines selectors, for
 java.base.share.classes.java.nio.channels multiplexed, non-blocking I/O operations.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <a id="channels"></a>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <table class="striped" style="text-align:left; margin-left:2em">
 java.base.share.classes.java.nio.channels     <caption style="display:none">Lists channels and their descriptions</caption>
 java.base.share.classes.java.nio.channels <thead>
 java.base.share.classes.java.nio.channels <tr><th scope="col">Channels</th>
 java.base.share.classes.java.nio.channels     <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio.channels </thead>
 java.base.share.classes.java.nio.channels <tbody>
 java.base.share.classes.java.nio.channels <tr><th scope="row"><i>{@link java.nio.channels.Channel}</i></th>
 java.base.share.classes.java.nio.channels     <td>A nexus for I/O operations</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:1em"><i>{@link java.nio.channels.ReadableByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can read into a buffer</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em"><i>{@link java.nio.channels.ScatteringByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can read into a sequence of buffers</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:1em"><i>{@link java.nio.channels.WritableByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can write from a buffer</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em"><i>{@link java.nio.channels.GatheringByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can write from a sequence of buffers</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:1em"><i>{@link java.nio.channels.ByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can read/write to/from a buffer</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em"><i>{@link java.nio.channels.SeekableByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>A {@code ByteChannel} connected to an entity that contains a variable-length
 java.base.share.classes.java.nio.channels         sequence of bytes</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:1em"><i>{@link java.nio.channels.AsynchronousChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Supports asynchronous I/O operations.</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em"><i>{@link java.nio.channels.AsynchronousByteChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can read and write bytes asynchronously</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:1em"><i>{@link java.nio.channels.NetworkChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>A channel to a network socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em"><i>{@link java.nio.channels.MulticastChannel}</i></span></th>
 java.base.share.classes.java.nio.channels     <td>Can join Internet Protocol (IP) multicast groups</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">{@link java.nio.channels.Channels}</th>
 java.base.share.classes.java.nio.channels     <td>Utility methods for channel/stream interoperation</td></tr>
 java.base.share.classes.java.nio.channels </tbody>
 java.base.share.classes.java.nio.channels </table>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> A <i>channel</i> represents an open connection to an entity such as a
 java.base.share.classes.java.nio.channels hardware device, a file, a network socket, or a program component that is
 java.base.share.classes.java.nio.channels capable of performing one or more distinct I/O operations, for example reading
 java.base.share.classes.java.nio.channels or writing.  As specified in the {@link java.nio.channels.Channel} interface,
 java.base.share.classes.java.nio.channels channels are either open or closed, and they are both <i>asynchronously
 java.base.share.classes.java.nio.channels closeable</i> and <i>interruptible</i>.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.Channel} interface is extended by several
 java.base.share.classes.java.nio.channels other interfaces.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.ReadableByteChannel} interface specifies a
 java.base.share.classes.java.nio.channels {@link java.nio.channels.ReadableByteChannel#read read} method that reads bytes
 java.base.share.classes.java.nio.channels from the channel into a buffer; similarly, the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.WritableByteChannel} interface specifies a {@link
 java.base.share.classes.java.nio.channels java.nio.channels.WritableByteChannel#write write} method that writes bytes
 java.base.share.classes.java.nio.channels from a buffer to the channel. The {@link java.nio.channels.ByteChannel}
 java.base.share.classes.java.nio.channels interface unifies these two interfaces for the common case of channels that can
 java.base.share.classes.java.nio.channels both read and write bytes. The {@link java.nio.channels.SeekableByteChannel}
 java.base.share.classes.java.nio.channels interface extends the {@code ByteChannel} interface with methods to {@link
 java.base.share.classes.java.nio.channels java.nio.channels.SeekableByteChannel#position() query} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.SeekableByteChannel#position(long) modify} the channel's
 java.base.share.classes.java.nio.channels current position, and its {@link java.nio.channels.SeekableByteChannel#size
 java.base.share.classes.java.nio.channels size}.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.ScatteringByteChannel} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.GatheringByteChannel} interfaces extend the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.ReadableByteChannel} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.WritableByteChannel} interfaces, respectively, adding {@link
 java.base.share.classes.java.nio.channels java.nio.channels.ScatteringByteChannel#read read} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.GatheringByteChannel#write write} methods that take a
 java.base.share.classes.java.nio.channels sequence of buffers rather than a single buffer.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.NetworkChannel} interface specifies methods
 java.base.share.classes.java.nio.channels to {@link java.nio.channels.NetworkChannel#bind bind} the channel's socket,
 java.base.share.classes.java.nio.channels obtain the address to which the socket is bound, and methods to {@link
 java.base.share.classes.java.nio.channels java.nio.channels.NetworkChannel#getOption get} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.NetworkChannel#setOption set} socket options. The {@link
 java.base.share.classes.java.nio.channels java.nio.channels.MulticastChannel} interface specifies methods to join
 java.base.share.classes.java.nio.channels Internet Protocol (IP) multicast groups.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.Channels} utility class defines static methods
 java.base.share.classes.java.nio.channels that support the interoperation of the stream classes of the {@link
 java.base.share.classes.java.nio.channels java.io} package with the channel classes of this package.  An appropriate
 java.base.share.classes.java.nio.channels channel can be constructed from an {@link java.io.InputStream} or an {@link
 java.base.share.classes.java.nio.channels java.io.OutputStream}, and conversely an {@link java.io.InputStream} or an
 java.base.share.classes.java.nio.channels {@link java.io.OutputStream} can be constructed from a channel.  A {@link
 java.base.share.classes.java.nio.channels java.io.Reader} can be constructed that uses a given charset to decode bytes
 java.base.share.classes.java.nio.channels from a given readable byte channel, and conversely a {@link java.io.Writer} can
 java.base.share.classes.java.nio.channels be constructed that uses a given charset to encode characters into bytes and
 java.base.share.classes.java.nio.channels write them to a given writable byte channel.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <table class="striped" style="margin-left:2em; text-align:left">
 java.base.share.classes.java.nio.channels     <caption style="display:none">
 java.base.share.classes.java.nio.channels         Lists file channels and their descriptions</caption>
 java.base.share.classes.java.nio.channels <thead>
 java.base.share.classes.java.nio.channels <tr><th scope="col">File channels</th>
 java.base.share.classes.java.nio.channels     <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio.channels </thead>
 java.base.share.classes.java.nio.channels <tbody>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.FileChannel}</th>
 java.base.share.classes.java.nio.channels     <td>Reads, writes, maps, and manipulates files</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.FileLock}</th>
 java.base.share.classes.java.nio.channels     <td>A lock on a (region of a) file</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.MappedByteBuffer}</th>
 java.base.share.classes.java.nio.channels     <td>A direct byte buffer mapped to a region of a file</td></tr>
 java.base.share.classes.java.nio.channels </tbody>
 java.base.share.classes.java.nio.channels </table>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The {@link java.nio.channels.FileChannel} class supports the usual
 java.base.share.classes.java.nio.channels operations of reading bytes from, and writing bytes to, a channel connected to
 java.base.share.classes.java.nio.channels a file, as well as those of querying and modifying the current file position
 java.base.share.classes.java.nio.channels and truncating the file to a specific size.  It defines methods for acquiring
 java.base.share.classes.java.nio.channels locks on the whole file or on a specific region of a file; these methods return
 java.base.share.classes.java.nio.channels instances of the {@link java.nio.channels.FileLock} class.  Finally, it defines
 java.base.share.classes.java.nio.channels methods for forcing updates to the file to be written to the storage device that
 java.base.share.classes.java.nio.channels contains it, for efficiently transferring bytes between the file and other
 java.base.share.classes.java.nio.channels channels, and for mapping a region of the file directly into memory.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> A {@code FileChannel} is created by invoking one of its static {@link
 java.base.share.classes.java.nio.channels java.nio.channels.FileChannel#open open} methods, or by invoking the {@code
 java.base.share.classes.java.nio.channels getChannel} method of a {@link java.io.FileInputStream}, {@link
 java.base.share.classes.java.nio.channels java.io.FileOutputStream}, or {@link java.io.RandomAccessFile} to return a
 java.base.share.classes.java.nio.channels file channel connected to the same underlying file as the {@link java.io}
 java.base.share.classes.java.nio.channels class.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <a id="multiplex"></a>
 java.base.share.classes.java.nio.channels <table class="striped" style="margin-left:2em; text-align:left">
 java.base.share.classes.java.nio.channels     <caption style="display:none">
 java.base.share.classes.java.nio.channels         Lists multiplexed, non-blocking channels and their descriptions</caption>
 java.base.share.classes.java.nio.channels <thead>
 java.base.share.classes.java.nio.channels <tr><th scope="col">Multiplexed, non-blocking I/O</th>
 java.base.share.classes.java.nio.channels     <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio.channels </thead>
 java.base.share.classes.java.nio.channels <tbody>
 java.base.share.classes.java.nio.channels <tr><th scope="row">{@link java.nio.channels.SelectableChannel}</th>
 java.base.share.classes.java.nio.channels     <td>A channel that can be multiplexed</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em">{@link java.nio.channels.DatagramChannel}</span></th>
 java.base.share.classes.java.nio.channels     <td>A channel to a datagram-oriented socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em">{@link java.nio.channels.Pipe.SinkChannel}</span></th>
 java.base.share.classes.java.nio.channels     <td>The write end of a pipe</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em">{@link java.nio.channels.Pipe.SourceChannel}</span></th>
 java.base.share.classes.java.nio.channels     <td>The read end of a pipe</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em">{@link java.nio.channels.ServerSocketChannel}</span></th>
 java.base.share.classes.java.nio.channels     <td>A channel to a stream-oriented listening socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     <span style="padding-left:2em">{@link java.nio.channels.SocketChannel}</span></th>
 java.base.share.classes.java.nio.channels     <td>A channel for a stream-oriented connecting socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">{@link java.nio.channels.Selector}</th>
 java.base.share.classes.java.nio.channels     <td>A multiplexor of selectable channels</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">{@link java.nio.channels.SelectionKey}</th>
 java.base.share.classes.java.nio.channels     <td>A token representing the registration of a channel
 java.base.share.classes.java.nio.channels     with a selector</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">{@link java.nio.channels.Pipe}</th>
 java.base.share.classes.java.nio.channels     <td>Two channels that form a unidirectional pipe</td></tr>
 java.base.share.classes.java.nio.channels </tbody>
 java.base.share.classes.java.nio.channels </table>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Multiplexed, non-blocking I/O, which is much more scalable than
 java.base.share.classes.java.nio.channels thread-oriented, blocking I/O, is provided by <i>selectors</i>, <i>selectable
 java.base.share.classes.java.nio.channels channels</i>, and <i>selection keys</i>.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> A <a href="Selector.html"><i>selector</i></a> is a multiplexor of <a
 java.base.share.classes.java.nio.channels href="SelectableChannel.html"><i>selectable channels</i></a>, which in turn are
 java.base.share.classes.java.nio.channels a special type of channel that can be put into <a
 java.base.share.classes.java.nio.channels href="SelectableChannel.html#bm"><i>non-blocking mode</i></a>.  To perform
 java.base.share.classes.java.nio.channels multiplexed I/O operations, one or more selectable channels are first created,
 java.base.share.classes.java.nio.channels put into non-blocking mode, and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.SelectableChannel#register <i>registered</i>}
 java.base.share.classes.java.nio.channels with a selector.  Registering a channel specifies the set of I/O operations
 java.base.share.classes.java.nio.channels that will be tested for readiness by the selector, and returns a <a
 java.base.share.classes.java.nio.channels href="SelectionKey.html"><i>selection key</i></a> that represents the
 java.base.share.classes.java.nio.channels registration.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Once some channels have been registered with a selector, a <a
 java.base.share.classes.java.nio.channels href="Selector.html#selop"><i>selection operation</i></a> can be performed in
 java.base.share.classes.java.nio.channels order to discover which channels, if any, have become ready to perform one or
 java.base.share.classes.java.nio.channels more of the operations in which interest was previously declared.  If a channel
 java.base.share.classes.java.nio.channels is ready then the key returned when it was registered will be added to the
 java.base.share.classes.java.nio.channels selector's <i>selected-key set</i>.  The key set, and the keys within it, can
 java.base.share.classes.java.nio.channels be examined in order to determine the operations for which each channel is
 java.base.share.classes.java.nio.channels ready.  From each key one can retrieve the corresponding channel in order to
 java.base.share.classes.java.nio.channels perform whatever I/O operations are required.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> That a selection key indicates that its channel is ready for some operation
 java.base.share.classes.java.nio.channels is a hint, but not a guarantee, that such an operation can be performed by a
 java.base.share.classes.java.nio.channels thread without causing the thread to block.  It is imperative that code that
 java.base.share.classes.java.nio.channels performs multiplexed I/O be written so as to ignore these hints when they prove
 java.base.share.classes.java.nio.channels to be incorrect.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> This package defines selectable-channel classes corresponding to the {@link
 java.base.share.classes.java.nio.channels java.net.DatagramSocket}, {@link java.net.ServerSocket}, and {@link
 java.base.share.classes.java.nio.channels java.net.Socket} classes defined in the {@link java.net} package.
 java.base.share.classes.java.nio.channels Minor changes to these classes have been made in order to support sockets that
 java.base.share.classes.java.nio.channels are associated with channels.  This package also defines a simple class that
 java.base.share.classes.java.nio.channels implements unidirectional pipes.  In all cases, a new selectable channel is
 java.base.share.classes.java.nio.channels created by invoking the static {@code open} method of the corresponding class.
 java.base.share.classes.java.nio.channels If a channel needs an associated socket then a socket will be created as a side
 java.base.share.classes.java.nio.channels effect of this operation.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> {@link java.nio.channels.DatagramChannel},
 java.base.share.classes.java.nio.channels {@link java.nio.channels.SocketChannel} and
 java.base.share.classes.java.nio.channels {@link java.nio.channels.ServerSocketChannel}s can be created
 java.base.share.classes.java.nio.channels with different {@link java.net.ProtocolFamily protocol families}. The standard
 java.base.share.classes.java.nio.channels family types are specified in {@link java.net.StandardProtocolFamily}.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Channels for <i>Internet Protocol</i> sockets are created using the
 java.base.share.classes.java.nio.channels {@link java.net.StandardProtocolFamily#INET INET} or {@link
 java.base.share.classes.java.nio.channels java.net.StandardProtocolFamily#INET6 INET6} protocol families. <i>Internet
 java.base.share.classes.java.nio.channels Protocol</i> sockets support network communication using TCP and UDP and are
 java.base.share.classes.java.nio.channels addressed using {@link java.net.InetSocketAddress}es which encapsulate an IP
 java.base.share.classes.java.nio.channels address and port number. <i>Internet Protocol</i> sockets are also the default
 java.base.share.classes.java.nio.channels type created, when a protocol family is not specified in the channel factory
 java.base.share.classes.java.nio.channels creation method.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Channels for <a id="unixdomain"></a><i>Unix Domain</i> sockets are created
 java.base.share.classes.java.nio.channels using the {@link java.net.StandardProtocolFamily#UNIX UNIX} protocol family.
 java.base.share.classes.java.nio.channels <i>Unix Domain</i> sockets support local inter-process
 java.base.share.classes.java.nio.channels communication on the same host, and are addressed using {@link
 java.base.share.classes.java.nio.channels java.net.UnixDomainSocketAddress}es which encapsulate a filesystem pathname
 java.base.share.classes.java.nio.channels on the local system.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> The implementation of selectors, selectable channels, and selection keys
 java.base.share.classes.java.nio.channels can be replaced by "plugging in" an alternative definition or instance of the
 java.base.share.classes.java.nio.channels {@link java.nio.channels.spi.SelectorProvider} class defined in the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi} package.  It is not expected that many developers
 java.base.share.classes.java.nio.channels will actually make use of this facility; it is provided primarily so that
 java.base.share.classes.java.nio.channels sophisticated users can take advantage of operating-system-specific
 java.base.share.classes.java.nio.channels I/O-multiplexing mechanisms when very high performance is required.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Much of the bookkeeping and synchronization required to implement the
 java.base.share.classes.java.nio.channels multiplexed-I/O abstractions is performed by the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AbstractInterruptibleChannel}, {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AbstractSelectableChannel}, {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AbstractSelectionKey}, and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AbstractSelector} classes in the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi} package.  When defining a custom selector provider,
 java.base.share.classes.java.nio.channels only the {@link java.nio.channels.spi.AbstractSelector} and {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AbstractSelectionKey} classes should be subclassed
 java.base.share.classes.java.nio.channels directly; custom channel classes should extend the appropriate {@link
 java.base.share.classes.java.nio.channels java.nio.channels.SelectableChannel} subclasses defined in this package.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <a id="async"></a>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <table class="striped" style="padding-left:2em; text-align:left">
 java.base.share.classes.java.nio.channels     <caption style="display:none">
 java.base.share.classes.java.nio.channels         Lists asynchronous channels and their descriptions</caption>
 java.base.share.classes.java.nio.channels <thead>
 java.base.share.classes.java.nio.channels <tr><th scope="col">Asynchronous I/O</th>
 java.base.share.classes.java.nio.channels     <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio.channels </thead>
 java.base.share.classes.java.nio.channels <tbody>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.AsynchronousFileChannel}</th>
 java.base.share.classes.java.nio.channels     <td>An asynchronous channel for reading, writing, and manipulating a file</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.AsynchronousSocketChannel}</th>
 java.base.share.classes.java.nio.channels     <td>An asynchronous channel to a stream-oriented connecting socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.AsynchronousServerSocketChannel}</th>
 java.base.share.classes.java.nio.channels     <td>An asynchronous channel to a stream-oriented listening socket</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.CompletionHandler}</th>
 java.base.share.classes.java.nio.channels     <td>A handler for consuming the result of an asynchronous operation</td></tr>
 java.base.share.classes.java.nio.channels <tr><th scope="row">
 java.base.share.classes.java.nio.channels     {@link java.nio.channels.AsynchronousChannelGroup}</th>
 java.base.share.classes.java.nio.channels     <td>A grouping of asynchronous channels for the purpose of resource sharing</td></tr>
 java.base.share.classes.java.nio.channels </tbody>
 java.base.share.classes.java.nio.channels </table>
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> {@link java.nio.channels.AsynchronousChannel Asynchronous channels} are a
 java.base.share.classes.java.nio.channels special type of channel capable of asynchronous I/O operations. Asynchronous
 java.base.share.classes.java.nio.channels channels are non-blocking and define methods to initiate asynchronous
 java.base.share.classes.java.nio.channels operations, returning a {@link java.util.concurrent.Future} representing the
 java.base.share.classes.java.nio.channels pending result of each operation. The {@code Future} can be used to poll or
 java.base.share.classes.java.nio.channels wait for the result of the operation. Asynchronous I/O operations can also
 java.base.share.classes.java.nio.channels specify a {@link java.nio.channels.CompletionHandler} to invoke when the
 java.base.share.classes.java.nio.channels operation completes. A completion handler is user provided code that is executed
 java.base.share.classes.java.nio.channels to consume the result of I/O operation.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> This package defines asynchronous-channel classes that are connected to
 java.base.share.classes.java.nio.channels a stream-oriented connecting or listening socket, or a datagram-oriented socket.
 java.base.share.classes.java.nio.channels It also defines the {@link java.nio.channels.AsynchronousFileChannel} class
 java.base.share.classes.java.nio.channels for asynchronous reading, writing, and manipulating a file. As with the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.FileChannel} it supports operations to truncate the file
 java.base.share.classes.java.nio.channels to a specific size, force updates to the file to be written to the storage
 java.base.share.classes.java.nio.channels device, or acquire locks on the whole file or on a specific region of the file.
 java.base.share.classes.java.nio.channels Unlike the {@code FileChannel} it does not define methods for mapping a
 java.base.share.classes.java.nio.channels region of the file directly into memory. Where memory mapped I/O is required,
 java.base.share.classes.java.nio.channels then a {@code FileChannel} can be used.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Asynchronous channels are bound to an asynchronous channel group for the
 java.base.share.classes.java.nio.channels purpose of resource sharing. A group has an associated {@link
 java.base.share.classes.java.nio.channels java.util.concurrent.ExecutorService} to which tasks are submitted to handle
 java.base.share.classes.java.nio.channels I/O events and dispatch to completion handlers that consume the result of
 java.base.share.classes.java.nio.channels asynchronous operations performed on channels in the group. The group can
 java.base.share.classes.java.nio.channels optionally be specified when creating the channel or the channel can be bound
 java.base.share.classes.java.nio.channels to a <em>default group</em>. Sophisticated users may wish to create their
 java.base.share.classes.java.nio.channels own asynchronous channel groups or configure the {@code ExecutorService}
 java.base.share.classes.java.nio.channels that will be used for the default group.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> As with selectors, the implementation of asynchronous channels can be
 java.base.share.classes.java.nio.channels replaced by "plugging in" an alternative definition or instance of the {@link
 java.base.share.classes.java.nio.channels java.nio.channels.spi.AsynchronousChannelProvider} class defined in the
 java.base.share.classes.java.nio.channels {@link java.nio.channels.spi} package.  It is not expected that many
 java.base.share.classes.java.nio.channels developers will actually make use of this facility; it is provided primarily
 java.base.share.classes.java.nio.channels so that sophisticated users can take advantage of operating-system-specific
 java.base.share.classes.java.nio.channels asynchronous I/O mechanisms when very high performance is required.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 java.base.share.classes.java.nio.channels or method in any class or interface in this package will cause a {@link
 java.base.share.classes.java.nio.channels java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.nio.channels
 java.base.share.classes.java.nio.channels @since 1.4
 java.base.share.classes.java.nio.channels @author Mark Reinhold
 java.base.share.classes.java.nio.channels @author JSR-51 Expert Group
 java.base.share.classes.java.nio.channels/

package java.nio.channels;
