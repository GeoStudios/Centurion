/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.core.main.io;

/**
 * The {@code DataInput} interface provides
 * for reading bytes from a binary stream and
 * reconstructing from them data in any of
 * the Java primitive types. There is also
 * a
 * facility for reconstructing a {@code String}
 * from data in
 * <a href="#modified-utf-8">modified UTF-8</a>
 * format.
 * <p>
 * It is generally true of all the reading
 * routines in this interface that if end of
 * file is reached before the desired number
 * of bytes has been read, an {@code EOFException}
 * (which is a kind of {@code IOException})
 * is thrown. If any byte cannot be read for
 * any reason other than end of file, an {@code IOException}
 * other than {@code EOFException} is
 * thrown. In particular, an {@code IOException}
 * may be thrown if the input stream has been
 * closed.
 *
 * <h2><a id="modified-utf-8">Modified UTF-8</a></h2>
 * <p>
 * Implementations of the DataInput and DataOutput interfaces represent
 * Unicode strings in a format that is a slight modification of UTF-8.
 * (For information regarding the standard UTF-8 format, see section
 * <i>3.9 Unicode Encoding Forms</i> of <i>The Unicode Standard, Version
 * 4.0</i>)
 *
 * <ul>
 * <li>Characters in the range {@code '\u005Cu0001'} to
 *         {@code '\u005Cu007F'} are represented by a single byte.
 * <li>The null character {@code '\u005Cu0000'} and characters
 *         in the range {@code '\u005Cu0080'} to {@code '\u005Cu07FF'} are
 *         represented by a pair of bytes.
 * <li>Characters in the range {@code '\u005Cu0800'}
 *         to {@code '\u005CuFFFF'} are represented by three bytes.
 * </ul>
 *
 *   <table class="plain" style="margin-left:2em;">
 *     <caption>Encoding of UTF-8 values</caption>
 *     <thead>
 *     <tr>
 *       <th scope="col" rowspan="2">Value</th>
 *       <th scope="col" rowspan="2">Byte</th>
 *       <th scope="col" colspan="8" id="bit_a">Bit Values</th>
 *     </tr>
 *     <tr>
 *       <!-- Value -->
 *       <!-- Byte -->
 *       <th scope="col" style="width:3em"> 7 </th>
 *       <th scope="col" style="width:3em"> 6 </th>
 *       <th scope="col" style="width:3em"> 5 </th>
 *       <th scope="col" style="width:3em"> 4 </th>
 *       <th scope="col" style="width:3em"> 3 </th>
 *       <th scope="col" style="width:3em"> 2 </th>
 *       <th scope="col" style="width:3em"> 1 </th>
 *       <th scope="col" style="width:3em"> 0 </th>
 *     </thead>
 *     <tbody>
 *     <tr>
 *       <th scope="row" style="text-align:left; font-weight:normal">
 *         {@code \u005Cu0001} to {@code \u005Cu007F} </th>
 *       <th scope="row" style="font-weight:normal; text-align:center"> 1 </th>
 *       <td style="text-align:center">0
 *       <td colspan="7" style="text-align:right; padding-right:6em">bits 6-0
 *     </tr>
 *     <tr>
 *       <th scope="row" rowspan="2" style="text-align:left; font-weight:normal">
 *           {@code \u005Cu0000},<br>
 *           {@code \u005Cu0080} to {@code \u005Cu07FF} </th>
 *       <th scope="row" style="font-weight:normal; text-align:center"> 1 </th>
 *       <td style="text-align:center">1
 *       <td style="text-align:center">1
 *       <td style="text-align:center">0
 *       <td colspan="5" style="text-align:right; padding-right:6em">bits 10-6
 *     </tr>
 *     <tr>
 *       <!-- (value) -->
 *       <th scope="row" style="font-weight:normal; text-align:center"> 2 </th>
 *       <td style="text-align:center">1
 *       <td style="text-align:center">0
 *       <td colspan="6" style="text-align:right; padding-right:6em">bits 5-0
 *     </tr>
 *     <tr>
 *       <th scope="row" rowspan="3" style="text-align:left; font-weight:normal">
 *         {@code \u005Cu0800} to {@code \u005CuFFFF} </th>
 *       <th scope="row" style="font-weight:normal; text-align:center"> 1 </th>
 *       <td style="text-align:center">1
 *       <td style="text-align:center">1
 *       <td style="text-align:center">1
 *       <td style="text-align:center">0
 *       <td colspan="4" style="text-align:right; padding-right:6em">bits 15-12
 *     </tr>
 *     <tr>
 *       <!-- (value) -->
 *       <th scope="row" style="font-weight:normal; text-align:center"> 2 </th>
 *       <td style="text-align:center">1
 *       <td style="text-align:center">0
 *       <td colspan="6" style="text-align:right; padding-right:6em">bits 11-6
 *     </tr>
 *     <tr>
 *       <!-- (value) -->
 *       <th scope="row" style="font-weight:normal; text-align:center"> 3 </th>
 *       <td style="text-align:center">1
 *       <td style="text-align:center">0
 *       <td colspan="6" style="text-align:right; padding-right:6em">bits 5-0
 *     </tr>
 *     </tbody>
 *   </table>
 *
 * <p>
 * The differences between this format and the
 * standard UTF-8 format are the following:
 * <ul>
 * <li>The null byte {@code '\u005Cu0000'} is encoded in 2-byte format
 *     rather than 1-byte, so that the encoded strings never have
 *     embedded nulls.
 * <li>Only the 1-byte, 2-byte, and 3-byte formats are used.
 * <li><a href="../lang/Character.html#unicode">Supplementary characters</a>
 *     are represented in the form of surrogate pairs.
 * </ul>
 * @see     java.core.main.io.DataInputStream
 * @see     java.core.main.io.DataOutput
 * @author Logan Abernathy
 * @since Alpha CDK 0.1
 */

public interface DataInput {
}