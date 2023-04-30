/java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.nio.file.attribute/

/java.base.share.classes.java.nio.file.attributejava.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute Interfaces and classes providing access to file and file system attributes.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <table class="striped" style="padding-left:2em; text-align:left">
 java.base.share.classes.java.nio.file.attribute     <caption style="display:none">Attribute views</caption>
 java.base.share.classes.java.nio.file.attribute <thead>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="col">Attribute views</th>
 java.base.share.classes.java.nio.file.attribute     <th scope="col">Description</th></tr>
 java.base.share.classes.java.nio.file.attribute </thead>
 java.base.share.classes.java.nio.file.attribute <tbody>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row"><i>{@link java.nio.file.attribute.AttributeView}</i></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update non-opaque values associated with objects in a file system</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:1em"><i>{@link java.nio.file.attribute.FileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update file attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:2em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.BasicFileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update a basic set of file attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:3em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.PosixFileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update POSIX defined file attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:3em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.DosFileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update FAT file attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:2em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.FileOwnerAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update the owner of a file</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:3em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.AclFileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update Access Control Lists</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:2em">
 java.base.share.classes.java.nio.file.attribute     <i>{@link java.nio.file.attribute.UserDefinedFileAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update user-defined file attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute <tr><th scope="row">
 java.base.share.classes.java.nio.file.attribute     <span style="padding-left:1em"><i>{@link java.nio.file.attribute.FileStoreAttributeView}</i></span></th>
 java.base.share.classes.java.nio.file.attribute     <td>Can read or update file system attributes</td></tr>
 java.base.share.classes.java.nio.file.attribute </tbody>
 java.base.share.classes.java.nio.file.attribute </table>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> An attribute view provides a read-only or updatable view of the non-opaque
 java.base.share.classes.java.nio.file.attribute values, or <em>metadata</em>, associated with objects in a file system.
 java.base.share.classes.java.nio.file.attribute The {@link java.nio.file.attribute.FileAttributeView} interface is
 java.base.share.classes.java.nio.file.attribute extended by several other interfaces that provide views to specific sets of file
 java.base.share.classes.java.nio.file.attribute attributes. {@code FileAttributeViews} are selected by invoking the {@link
 java.base.share.classes.java.nio.file.attribute java.nio.file.Files#getFileAttributeView} method with a
 java.base.share.classes.java.nio.file.attribute <em>type-token</em> to identify the required view. Views can also be identified
 java.base.share.classes.java.nio.file.attribute by name. The {@link java.nio.file.attribute.FileStoreAttributeView} interface
 java.base.share.classes.java.nio.file.attribute provides access to file store attributes. A {@code FileStoreAttributeView} of
 java.base.share.classes.java.nio.file.attribute a given type is obtained by invoking the {@link
 java.base.share.classes.java.nio.file.attribute java.nio.file.FileStore#getFileStoreAttributeView} method.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> The {@link java.nio.file.attribute.BasicFileAttributeView}
 java.base.share.classes.java.nio.file.attribute class defines methods to read and update a <em>basic</em> set of file
 java.base.share.classes.java.nio.file.attribute attributes that are common to many file systems.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> The {@link java.nio.file.attribute.PosixFileAttributeView}
 java.base.share.classes.java.nio.file.attribute interface extends {@code BasicFileAttributeView} by defining methods
 java.base.share.classes.java.nio.file.attribute to access the file attributes commonly used by file systems and operating systems
 java.base.share.classes.java.nio.file.attribute that implement the Portable Operating System Interface (POSIX) family of
 java.base.share.classes.java.nio.file.attribute standards.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> The {@link java.nio.file.attribute.DosFileAttributeView}
 java.base.share.classes.java.nio.file.attribute class extends {@code BasicFileAttributeView} by defining methods to
 java.base.share.classes.java.nio.file.attribute access the legacy "DOS" file attributes supported on file systems such as File
 java.base.share.classes.java.nio.file.attribute Allocation Table (FAT), commonly used in consumer devices.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> The {@link java.nio.file.attribute.AclFileAttributeView}
 java.base.share.classes.java.nio.file.attribute class defines methods to read and write the Access Control List (ACL)
 java.base.share.classes.java.nio.file.attribute file attribute. The ACL model used by this file attribute view is based
 java.base.share.classes.java.nio.file.attribute on the model defined by <a href="http://www.ietf.org/rfc/rfc3530.txt">
 java.base.share.classes.java.nio.file.attribute <i>RFC&nbsp;3530: Network File System (NFS) version 4 Protocol</i></a>.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> In addition to attribute views, this package also defines classes and
 java.base.share.classes.java.nio.file.attribute interfaces that are used when accessing attributes:
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <ul>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute   <li> The {@link java.nio.file.attribute.UserPrincipal} and
 java.base.share.classes.java.nio.file.attribute   {@link java.nio.file.attribute.GroupPrincipal} interfaces represent an
 java.base.share.classes.java.nio.file.attribute   identity or group identity. </li>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute   <li> The {@link java.nio.file.attribute.UserPrincipalLookupService}
 java.base.share.classes.java.nio.file.attribute   interface defines methods to lookup user or group principals. </li>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute   <li> The {@link java.nio.file.attribute.FileAttribute} interface
 java.base.share.classes.java.nio.file.attribute   represents the value of an attribute for cases where the attribute value is
 java.base.share.classes.java.nio.file.attribute   required to be set atomically when creating an object in the file system. </li>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute </ul>
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 java.base.share.classes.java.nio.file.attribute or method in any class or interface in this package will cause a {@link
 java.base.share.classes.java.nio.file.attribute java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.nio.file.attribute
 java.base.share.classes.java.nio.file.attribute @since 1.7
 java.base.share.classes.java.nio.file.attribute/

package java.nio.file.attribute;
