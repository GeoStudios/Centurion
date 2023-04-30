/java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.lang.reflect/

/java.base.share.classes.java.lang.reflectjava.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect Provides classes and interfaces for obtaining reflective information about
 java.base.share.classes.java.lang.reflect classes and objects.  Reflection allows programmatic access to information
 java.base.share.classes.java.lang.reflect about the fields, methods, and constructors of loaded classes, and the use
 java.base.share.classes.java.lang.reflect of reflected fields, methods, and constructors to operate on their underlying
 java.base.share.classes.java.lang.reflect counterparts, within encapsulation and security restrictions.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <p>Classes in this package, along with {@code java.lang.Class}
 java.base.share.classes.java.lang.reflect accommodate applications such as debuggers, interpreters, object
 java.base.share.classes.java.lang.reflect inspectors, class browsers, and services such as Object
 java.base.share.classes.java.lang.reflect Serialization and JavaBeans that need access to either the public
 java.base.share.classes.java.lang.reflect members of a target object (based on its runtime class) or the
 java.base.share.classes.java.lang.reflect members declared by a given class.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <p>{@link AccessibleObject} allows suppression of access checks if
 java.base.share.classes.java.lang.reflect the necessary {@link ReflectPermission} is available.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <p>{@link Array} provides static methods to dynamically create and
 java.base.share.classes.java.lang.reflect access arrays.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <h2><a id="LanguageJvmModel">Java programming language and JVM modeling in core reflection</a></h2>
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect The components of core reflection, which include types in this
 java.base.share.classes.java.lang.reflect package as well as {@link java.lang.Class Class}, {@link
 java.base.share.classes.java.lang.reflect java.lang.Package Package}, and {@link java.lang.Module Module},
 java.base.share.classes.java.lang.reflect fundamentally present a JVM model of the entities in question
 java.base.share.classes.java.lang.reflect rather than a Java programming language model.  A Java compiler,
 java.base.share.classes.java.lang.reflect such as {@code javac}, translates Java source code into executable
 java.base.share.classes.java.lang.reflect output that can be run on a JVM, primarily {@code class}
 java.base.share.classes.java.lang.reflect files. Compilers for source languages other than Java can and do
 java.base.share.classes.java.lang.reflect target the JVM as well.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <p>The translation process, including from Java language sources,
 java.base.share.classes.java.lang.reflect to executable output for the JVM is not a one-to-one
 java.base.share.classes.java.lang.reflect mapping. Structures present in the source language may have no
 java.base.share.classes.java.lang.reflect representation in the output and structures <em>not</em> present in
 java.base.share.classes.java.lang.reflect the source language may be present in the output. The latter are
 java.base.share.classes.java.lang.reflect called <i>synthetic</i> structures. Synthetic structures can
 java.base.share.classes.java.lang.reflect include {@linkplain Method#isSynthetic() methods}, {@linkplain
 java.base.share.classes.java.lang.reflect Field#isSynthetic() fields}, {@linkplain Parameter#isSynthetic()
 java.base.share.classes.java.lang.reflect parameters}, {@linkplain Class#isSynthetic() classes and
 java.base.share.classes.java.lang.reflect interfaces}. One particular kind of synthetic method is a
 java.base.share.classes.java.lang.reflect {@linkplain Method#isBridge() bridge method}. It is possible a
 java.base.share.classes.java.lang.reflect synthetic structure may not be marked as such. In particular, not
 java.base.share.classes.java.lang.reflect all {@code class} file versions support marking a parameter as
 java.base.share.classes.java.lang.reflect synthetic. A source language compiler generally has multiple ways
 java.base.share.classes.java.lang.reflect to translate a source program into a {@code class} file
 java.base.share.classes.java.lang.reflect representation. The translation may also depend on the version of
 java.base.share.classes.java.lang.reflect the {@code class} file format being targeted as different {@code
 java.base.share.classes.java.lang.reflect class} file versions have different capabilities and features. In
 java.base.share.classes.java.lang.reflect some cases the modifiers present in the {@code class} file
 java.base.share.classes.java.lang.reflect representation may differ from the modifiers on the originating
 java.base.share.classes.java.lang.reflect element in the source language, including {@link Modifier#FINAL
 java.base.share.classes.java.lang.reflect final} on a {@linkplain Parameter#getModifiers() parameter} and
 java.base.share.classes.java.lang.reflect {@code protected}, {@code private}, and {@code static} on
 java.base.share.classes.java.lang.reflect {@linkplain java.lang.Class#getModifiers() classes and interfaces}.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect <p>Besides differences in structural representation between the
 java.base.share.classes.java.lang.reflect source language and the JVM representation, core reflection also
 java.base.share.classes.java.lang.reflect exposes runtime specific information. For example, the {@linkplain
 java.base.share.classes.java.lang.reflect java.lang.Class#getClassLoader() class loaders} and {@linkplain
 java.base.share.classes.java.lang.reflect java.lang.Class#getProtectionDomain() protection domains} of a
 java.base.share.classes.java.lang.reflect {@code Class} are runtime concepts without a direct analogue in
 java.base.share.classes.java.lang.reflect source code.
 java.base.share.classes.java.lang.reflect
 java.base.share.classes.java.lang.reflect @jls 13.1 The Form of a Binary
 java.base.share.classes.java.lang.reflect @jvms 1.2 The Java Virtual Machine
 java.base.share.classes.java.lang.reflect @jvms 4.7.8 The Synthetic Attribute
 java.base.share.classes.java.lang.reflect @jvms 5.3.1 Loading Using the Bootstrap Class Loader
 java.base.share.classes.java.lang.reflect @jvms 5.3.2 Loading Using a User-defined Class Loader
 java.base.share.classes.java.lang.reflect @since 1.1
 java.base.share.classes.java.lang.reflect @revised 9
 java.base.share.classes.java.lang.reflect/
package java.lang.reflect;
