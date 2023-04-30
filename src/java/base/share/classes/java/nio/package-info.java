/java.base.share.classes.java.nio
 java.base.share.classes.java.nio Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.nio/

/java.base.share.classes.java.niojava.base.share.classes.java.nio
 java.base.share.classes.java.nio Defines buffers, which are containers for data, and provides an
 java.base.share.classes.java.nio overview of the other NIO packages.
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> The central abstractions of the NIO APIs are: </p>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <ul>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> <a href="#buffers"><i>Buffers</i></a>, which are containers for data;
 java.base.share.classes.java.nio   </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> <a
 java.base.share.classes.java.nio   href="charset/package-summary.html"><i>Charsets</i></a> and their
 java.base.share.classes.java.nio   associated <i>decoders</i> and <i>encoders</i>, <br> which
 java.base.share.classes.java.nio   translate between bytes and Unicode characters; </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> <a
 java.base.share.classes.java.nio   href="channels/package-summary.html"><i>Channels</i></a> of
 java.base.share.classes.java.nio   various types, which represent connections <br> to entities
 java.base.share.classes.java.nio   capable of performing I/O operations; and </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> <i>Selectors</i> and <i>selection keys</i>, which
 java.base.share.classes.java.nio   together with <br> <i>selectable channels</i> define a <a
 java.base.share.classes.java.nio   href="channels/package-summary.html#multiplex">multiplexed,
 java.base.share.classes.java.nio   non-blocking <br> I/O</a> facility.  </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> <a
 java.base.share.classes.java.nio   href="file/Path.html"><i>Path</i></a>,
 java.base.share.classes.java.nio   which together with the <a href="file/Files.html"><i>Files</i></a>
 java.base.share.classes.java.nio   class provides access to files.  </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio  </ul>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> The {@code java.nio} package defines the buffer classes, which
 java.base.share.classes.java.nio are used throughout the NIO APIs.  The charset API is defined in
 java.base.share.classes.java.nio the {@link java.nio.charset} package, the channel and selector APIs
 java.base.share.classes.java.nio in the {@link java.nio.channels} package, and the files and path APIs
 java.base.share.classes.java.nio in the {@link java.nio.file} package.  Each of these subpackages has
 java.base.share.classes.java.nio its own service-provider interface (SPI) subpackage, the contents of
 java.base.share.classes.java.nio which can be used to extend the platform's default implementations or
 java.base.share.classes.java.nio to construct alternative implementations.
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <a id="buffers"> </a>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <table class="striped" style="margin-left:2em; text-align:left">
 java.base.share.classes.java.nio     <caption style="display:none">Description of the various buffers</caption>
 java.base.share.classes.java.nio   <thead>
 java.base.share.classes.java.nio   <tr><th scope="col">Buffers</th>
 java.base.share.classes.java.nio       <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio   </thead>
 java.base.share.classes.java.nio   <tbody>
 java.base.share.classes.java.nio   <tr><th scope="row">{@link java.nio.Buffer}</th>
 java.base.share.classes.java.nio       <td>Position, limit, and capacity;
 java.base.share.classes.java.nio           clear, flip, rewind, and mark/reset</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.ByteBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact, views; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:2em">{@link java.nio.MappedByteBuffer}</span></th>
 java.base.share.classes.java.nio       <td>A byte buffer mapped to a file</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.CharBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.DoubleBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.FloatBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.IntBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.LongBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">
 java.base.share.classes.java.nio         <span style="padding-left:1em">{@link java.nio.ShortBuffer}</span></th>
 java.base.share.classes.java.nio       <td>Get/put, compact; allocate, wrap</td></tr>
 java.base.share.classes.java.nio   <tr><th scope="row">{@link java.nio.ByteOrder}</th>
 java.base.share.classes.java.nio       <td>Typesafe enumeration for byte orders</td></tr>
 java.base.share.classes.java.nio </tbody>
 java.base.share.classes.java.nio </table>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> A <i>buffer</i> is a container for a fixed amount of data of a
 java.base.share.classes.java.nio specific primitive type.  In addition to its content a buffer has a
 java.base.share.classes.java.nio <i>position</i>, which is the index of the next element to be read
 java.base.share.classes.java.nio or written, and a <i>limit</i>, which is the index of the first
 java.base.share.classes.java.nio element that should not be read or written.  The base {@link
 java.base.share.classes.java.nio java.nio.Buffer} class defines these properties as well as methods
 java.base.share.classes.java.nio for <i>clearing</i>, <i>flipping</i>, and <i>rewinding</i>, for
 java.base.share.classes.java.nio <i>marking</i> the current position, and for <i>resetting</i> the
 java.base.share.classes.java.nio position to the previous mark.
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> There is a buffer class for each non-boolean primitive type.
 java.base.share.classes.java.nio Each class defines a family of <i>get</i> and <i>put</i> methods
 java.base.share.classes.java.nio for moving data out of and in to a buffer, methods for
 java.base.share.classes.java.nio <i>compacting</i>, <i>duplicating</i>, and <i>slicing</i> a buffer,
 java.base.share.classes.java.nio and static methods for <i>allocating</i> a new buffer as well as
 java.base.share.classes.java.nio for <i>wrapping</i> an existing array into a buffer.
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> Byte buffers are distinguished in that they can be used as the
 java.base.share.classes.java.nio sources and targets of I/O operations.  They also support several
 java.base.share.classes.java.nio features not found in the other buffer classes:
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <ul>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> A byte buffer can be allocated as a <a
 java.base.share.classes.java.nio   href="ByteBuffer.html#direct"> <i>direct</i></a> buffer, in which
 java.base.share.classes.java.nio   case the Java virtual machine will make a best effort to perform
 java.base.share.classes.java.nio   native I/O operations directly upon it.  </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> A byte buffer can be created by {@link
 java.base.share.classes.java.nio   java.nio.channels.FileChannel#map <i>mapping</i>} a region of a
 java.base.share.classes.java.nio   file directly into memory, in which case a few additional
 java.base.share.classes.java.nio   file-related operations defined in the {@link
 java.base.share.classes.java.nio   java.nio.MappedByteBuffer} class are available.  </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio   <li><p> A byte buffer provides access to its content as either a
 java.base.share.classes.java.nio   heterogeneous or homogeneous sequence of <a
 java.base.share.classes.java.nio   href="ByteBuffer.html#bin"><i>binary data</i></a> of any
 java.base.share.classes.java.nio   non-boolean primitive type, in either big-endian or little-endian
 java.base.share.classes.java.nio   <a href="ByteOrder.html">byte order</a>.  </p></li>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio </ul>
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio <p> Unless otherwise noted, passing a {@code null} argument to a
 java.base.share.classes.java.nio constructor or method in any class or interface in this package
 java.base.share.classes.java.nio will cause a {@link java.lang.NullPointerException
 java.base.share.classes.java.nio NullPointerException} to be thrown.
 java.base.share.classes.java.nio
 java.base.share.classes.java.nio @since 1.4
 java.base.share.classes.java.nio @author Mark Reinhold
 java.base.share.classes.java.nio @author JSR-51 Expert Group
 java.base.share.classes.java.nio/
package java.nio;
