/java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.nio.file/

/java.base.share.classes.java.nio.filejava.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file Defines interfaces and classes for the Java virtual machine to access files,
 java.base.share.classes.java.nio.file file attributes, and file systems.
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <p> The java.nio.file package defines classes to access files and file
 java.base.share.classes.java.nio.file systems. The API to access file and file system attributes is defined in the
 java.base.share.classes.java.nio.file {@link java.nio.file.attribute} package. The {@link java.nio.file.spi}
 java.base.share.classes.java.nio.file package is used by service provider implementors wishing to extend the
 java.base.share.classes.java.nio.file platform default provider, or to construct other provider implementations. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2><a id="links">Symbolic Links</a></h2>
 java.base.share.classes.java.nio.file <p> Many operating systems and file systems have support for <em>symbolic links</em>.
 java.base.share.classes.java.nio.file A symbolic link is a special file that serves as a reference to another file.
 java.base.share.classes.java.nio.file For the most part, symbolic links are transparent to applications and
 java.base.share.classes.java.nio.file operations on symbolic links are automatically redirected to the <em>target</em>
 java.base.share.classes.java.nio.file of the link. Exceptions to this are when a symbolic link is deleted or
 java.base.share.classes.java.nio.file renamed/moved in which case the link is deleted or removed rather than the
 java.base.share.classes.java.nio.file target of the link. This package includes support for symbolic links where
 java.base.share.classes.java.nio.file implementations provide these semantics. File systems may support other types
 java.base.share.classes.java.nio.file that are semantically close but support for these other types of links is
 java.base.share.classes.java.nio.file not included in this package. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2><a id="interop">Interoperability</a></h2>
 java.base.share.classes.java.nio.file <p> The {@link java.io.File} class defines the {@link java.io.File#toPath
 java.base.share.classes.java.nio.file toPath} method to construct a {@link java.nio.file.Path} by converting
 java.base.share.classes.java.nio.file the abstract path represented by the {@code java.io.File} object. The resulting
 java.base.share.classes.java.nio.file {@code Path} can be used to operate on the same file as the {@code File}
 java.base.share.classes.java.nio.file object. The {@code Path} specification provides further information
 java.base.share.classes.java.nio.file on the <a href="Path.html#interop">interoperability</a> between {@code Path}
 java.base.share.classes.java.nio.file and {@code java.io.File} objects. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2>Visibility</h2>
 java.base.share.classes.java.nio.file <p> The view of the files and file system provided by classes in this package are
 java.base.share.classes.java.nio.file guaranteed to be consistent with other views provided by other instances in the
 java.base.share.classes.java.nio.file same Java virtual machine.  The view may or may not, however, be consistent with
 java.base.share.classes.java.nio.file the view of the file system as seen by other concurrently running programs due
 java.base.share.classes.java.nio.file to caching performed by the underlying operating system and delays induced by
 java.base.share.classes.java.nio.file network-filesystem protocols. This is true regardless of the language in which
 java.base.share.classes.java.nio.file these other programs are written, and whether they are running on the same machine
 java.base.share.classes.java.nio.file or on some other machine.  The exact nature of any such inconsistencies is
 java.base.share.classes.java.nio.file system-dependent and therefore unspecified. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2><a id="integrity">Synchronized I/O File Integrity</a></h2>
 java.base.share.classes.java.nio.file <p> The {@link java.nio.file.StandardOpenOption#SYNC SYNC} and {@link
 java.base.share.classes.java.nio.file java.nio.file.StandardOpenOption#DSYNC DSYNC} options are used when opening a file
 java.base.share.classes.java.nio.file to require that updates to the file are written synchronously to the underlying
 java.base.share.classes.java.nio.file storage device. In the case of the default provider, and the file resides on
 java.base.share.classes.java.nio.file a local storage device, and the {@link java.nio.channels.SeekableByteChannel
 java.base.share.classes.java.nio.file seekable} channel is connected to a file that was opened with one of these
 java.base.share.classes.java.nio.file options, then an invocation of the {@link
 java.base.share.classes.java.nio.file java.nio.channels.WritableByteChannel#write(java.nio.ByteBuffer) write}
 java.base.share.classes.java.nio.file method is only guaranteed to return when all changes made to the file
 java.base.share.classes.java.nio.file by that invocation have been written to the device. These options are useful
 java.base.share.classes.java.nio.file for ensuring that critical information is not lost in the event of a system
 java.base.share.classes.java.nio.file crash. If the file does not reside on a local device then no such guarantee
 java.base.share.classes.java.nio.file is made. Whether this guarantee is possible with other {@link
 java.base.share.classes.java.nio.file java.nio.file.spi.FileSystemProvider provider} implementations is provider
 java.base.share.classes.java.nio.file specific. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2>General Exceptions</h2>
 java.base.share.classes.java.nio.file <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 java.base.share.classes.java.nio.file or method of any class or interface in this package will cause a {@link
 java.base.share.classes.java.nio.file java.lang.NullPointerException NullPointerException} to be thrown. Additionally,
 java.base.share.classes.java.nio.file invoking a method with an array or collection containing a {@code null} element
 java.base.share.classes.java.nio.file will cause a {@code NullPointerException}, unless otherwise specified. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <p> Unless otherwise noted, methods that attempt to access the file system
 java.base.share.classes.java.nio.file will throw {@link java.nio.file.ClosedFileSystemException} when invoked on
 java.base.share.classes.java.nio.file objects associated with a {@link java.nio.file.FileSystem} that has been
 java.base.share.classes.java.nio.file {@link java.nio.file.FileSystem#close closed}. Additionally, any methods
 java.base.share.classes.java.nio.file that attempt write access to a file system will throw {@link
 java.base.share.classes.java.nio.file java.nio.file.ReadOnlyFileSystemException} when invoked on an object associated
 java.base.share.classes.java.nio.file with a {@link java.nio.file.FileSystem} that only provides read-only
 java.base.share.classes.java.nio.file access. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <p> Unless otherwise noted, invoking a method of any class or interface in
 java.base.share.classes.java.nio.file this package created by one {@link java.nio.file.spi.FileSystemProvider
 java.base.share.classes.java.nio.file provider} with a parameter that is an object created by another provider,
 java.base.share.classes.java.nio.file will throw {@link java.nio.file.ProviderMismatchException}. </p>
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file <h2><a id="optspecex">Optional Specific Exceptions</a></h2>
 java.base.share.classes.java.nio.file Most of the methods defined by classes in this package that access the
 java.base.share.classes.java.nio.file file system specify that {@link java.io.IOException} be thrown when an I/O
 java.base.share.classes.java.nio.file error occurs. In some cases, these methods define specific I/O exceptions
 java.base.share.classes.java.nio.file for common cases. These exceptions, noted as <i>optional specific exceptions</i>,
 java.base.share.classes.java.nio.file are thrown by the implementation where it can detect the specific error.
 java.base.share.classes.java.nio.file Where the specific error cannot be detected then the more general {@code
 java.base.share.classes.java.nio.file IOException} is thrown.
 java.base.share.classes.java.nio.file
 java.base.share.classes.java.nio.file @since 1.7
 java.base.share.classes.java.nio.file/
package java.nio.file;
