/java.base.share.classes.java.io
 java.base.share.classes.java.io Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.io/

/java.base.share.classes.java.iojava.base.share.classes.java.io
 java.base.share.classes.java.io Provides for system input and output through data streams,
 java.base.share.classes.java.io serialization and the file system.
 java.base.share.classes.java.io
 java.base.share.classes.java.io Unless otherwise noted, passing a {@code null} argument to a constructor or
 java.base.share.classes.java.io method in any class or interface in this package will cause a
 java.base.share.classes.java.io {@code NullPointerException} to be thrown.
 java.base.share.classes.java.io
 java.base.share.classes.java.io A <i>pathname string</i> passed as a {@code String} argument to a
 java.base.share.classes.java.io constructor or method in any class or interface in this package will be
 java.base.share.classes.java.io interpreted as described in the class specification of {@link File}.
 java.base.share.classes.java.io
 java.base.share.classes.java.io <h2>Object Serialization</h2>
 java.base.share.classes.java.io <p><strong>Warning: Deserialization of untrusted data is inherently dangerous
 java.base.share.classes.java.io and should be avoided. Untrusted data should be carefully validated according to the
 java.base.share.classes.java.io "Serialization and Deserialization" section of the
 java.base.share.classes.java.io {@extLink secure_coding_guidelines_javase Secure Coding Guidelines for Java SE}.
 java.base.share.classes.java.io </strong></p>
 java.base.share.classes.java.io <ul>
 java.base.share.classes.java.io   <li><a href="{@docRoot}/../specs/serialization/index.html">
 java.base.share.classes.java.io       <cite>Java Object Serialization Specification</cite></a>
 java.base.share.classes.java.io   <li>{@extLink serialization_filter_guide Serial Filtering} best practices</li>
 java.base.share.classes.java.io   <li>{@extLink serialver_tool_reference The serialver tool}</li>
 java.base.share.classes.java.io </ul>
 java.base.share.classes.java.io
 java.base.share.classes.java.io @since Pre Java 1
 java.base.share.classes.java.io @author Logan Abernathy
 java.base.share.classes.java.io @edited 24/4/2023
 java.base.share.classes.java.io/
package java.io;
