/java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.nio.charset/

/java.base.share.classes.java.nio.charsetjava.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset Defines charsets, decoders, and encoders, for translating between
 java.base.share.classes.java.nio.charset bytes and Unicode characters.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <table class="striped" style="margin-left:2em; text-align:left">
 java.base.share.classes.java.nio.charset     <caption style="display:none">Summary of charsets, decoders, and encoders in this package</caption>
 java.base.share.classes.java.nio.charset  <thead>
 java.base.share.classes.java.nio.charset  <tr><th scope="col">Class name</th>
 java.base.share.classes.java.nio.charset      <th scope="col">Description
 java.base.share.classes.java.nio.charset  </thead>
 java.base.share.classes.java.nio.charset  <tbody>
 java.base.share.classes.java.nio.charset   <tr><th scope="row">{@link java.nio.charset.Charset}</th>
 java.base.share.classes.java.nio.charset       <td>A named mapping between characters and bytes</td></tr>
 java.base.share.classes.java.nio.charset   <tr><th scope="row">{@link java.nio.charset.CharsetDecoder}</th>
 java.base.share.classes.java.nio.charset       <td>Decodes bytes into characters</td></tr>
 java.base.share.classes.java.nio.charset   <tr><th scope="row">{@link java.nio.charset.CharsetEncoder}</th>
 java.base.share.classes.java.nio.charset       <td>Encodes characters into bytes</td></tr>
 java.base.share.classes.java.nio.charset   <tr><th scope="row">{@link java.nio.charset.CoderResult}</th>
 java.base.share.classes.java.nio.charset       <td>Describes coder results</td></tr>
 java.base.share.classes.java.nio.charset   <tr><th scope="row">{@link java.nio.charset.CodingErrorAction}</th>
 java.base.share.classes.java.nio.charset       <td>Describes actions to take when coding errors are detected</td></tr>
 java.base.share.classes.java.nio.charset </tbody>
 java.base.share.classes.java.nio.charset </table>
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <p> A <i>charset</i> is named mapping between sequences of
 java.base.share.classes.java.nio.charset sixteen-bit Unicode characters and sequences of bytes, in the sense
 java.base.share.classes.java.nio.charset defined in <a
 java.base.share.classes.java.nio.charset href="http://www.ietf.org/rfc/rfc2278.txt"><i>RFC&nbsp;2278</i></a>.
 java.base.share.classes.java.nio.charset A <i>decoder</i> is an engine which transforms bytes in a specific
 java.base.share.classes.java.nio.charset charset into characters, and an <i>encoder</i> is an engine which
 java.base.share.classes.java.nio.charset transforms characters into bytes.  Encoders and decoders operate on
 java.base.share.classes.java.nio.charset byte and character buffers.  They are collectively referred to as
 java.base.share.classes.java.nio.charset <i>coders</i>.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <p> The {@link java.nio.charset.Charset} class defines methods for
 java.base.share.classes.java.nio.charset creating coders for a given charset and for retrieving the various
 java.base.share.classes.java.nio.charset names associated with a charset.  It also defines static methods
 java.base.share.classes.java.nio.charset for testing whether a particular charset is supported, for locating
 java.base.share.classes.java.nio.charset charset instances by name, and for constructing a map that contains
 java.base.share.classes.java.nio.charset every charset for which support is available in the current Java
 java.base.share.classes.java.nio.charset virtual machine.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <p> Most users will not use these classes directly; instead they
 java.base.share.classes.java.nio.charset will use the existing charset-related constructors and methods in
 java.base.share.classes.java.nio.charset the {@link java.lang.String} class, together with the existing
 java.base.share.classes.java.nio.charset {@link java.io.InputStreamReader} and {@link
 java.base.share.classes.java.nio.charset java.io.OutputStreamWriter} classes, all of whose implementations
 java.base.share.classes.java.nio.charset have been reworked to make use of the charset facilities defined in
 java.base.share.classes.java.nio.charset this package.  A small number of changes have been made to the
 java.base.share.classes.java.nio.charset {@link java.io.InputStreamReader} and {@link
 java.base.share.classes.java.nio.charset java.io.OutputStreamWriter} classes in order to allow explicit
 java.base.share.classes.java.nio.charset charset objects to be specified in the construction of instances of
 java.base.share.classes.java.nio.charset those classes.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <p> Support for new charsets can be made available via the
 java.base.share.classes.java.nio.charset interface defined in the {@link
 java.base.share.classes.java.nio.charset java.nio.charset.spi.CharsetProvider} class in the {@link
 java.base.share.classes.java.nio.charset java.nio.charset.spi} package.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset <p> Unless otherwise noted, passing a {@code null} argument to a
 java.base.share.classes.java.nio.charset constructor or method in any class or interface in this package
 java.base.share.classes.java.nio.charset will cause a {@link java.lang.NullPointerException
 java.base.share.classes.java.nio.charset NullPointerException} to be thrown.
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset
 java.base.share.classes.java.nio.charset @since 1.4
 java.base.share.classes.java.nio.charset @author Mark Reinhold
 java.base.share.classes.java.nio.charset @author JSR-51 Expert Group
 java.base.share.classes.java.nio.charset/
package java.nio.charset;
