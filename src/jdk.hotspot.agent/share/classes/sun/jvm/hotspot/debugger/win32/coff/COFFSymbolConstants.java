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

package sun.jvm.hotspot.debugger.win32.coff;

public interface COFFSymbolConstants {
  //
  // Section Number Values
  //

  /** Symbol record is not yet assigned a section. If the value is 0
      this indicates a references to an external symbol defined
      elsewhere. If the value is non-zero this is a common symbol with
      a size specified by the value. */
  short IMAGE_SYM_UNDEFINED = (short) 0;
  /** The symbol has an absolute (non-relocatable) value and is not an
      address. */
  short IMAGE_SYM_ABSOLUTE = (short) -1;
  /** The symbol provides general type or debugging information but
      does not correspond to a section. Microsoft tools use this
      setting along with .file records (storage class FILE). */
  short IMAGE_SYM_DEBUG = (short) -2;

  //
  // Type Representation
  //

  /** No type information or unknown base type. Microsoft tools use
      this setting.  */
  short IMAGE_SYM_TYPE_NULL = (short) 0;
  /** No valid type; used with void pointers and functions. */
  short IMAGE_SYM_TYPE_VOID = (short) 1;
  /** Character (signed byte). */
  short IMAGE_SYM_TYPE_CHAR = (short) 2;
  /** Two-byte signed integer. */
  short IMAGE_SYM_TYPE_SHORT = (short) 3;
  /** Natural integer type (normally four bytes in Windows NT). */
  short IMAGE_SYM_TYPE_INT = (short) 4;
  /** Four-byte signed integer. */
  short IMAGE_SYM_TYPE_LONG = (short) 5;
  /** Four-byte floating-point number. */
  short IMAGE_SYM_TYPE_FLOAT = (short) 6;
  /** Eight-byte floating-point number. */
  short IMAGE_SYM_TYPE_DOUBLE = (short) 7;
  /** Structure. */
  short IMAGE_SYM_TYPE_STRUCT = (short) 8;
  /** Union. */
  short IMAGE_SYM_TYPE_UNION = (short) 9;
  /** Enumerated type. */
  short IMAGE_SYM_TYPE_ENUM = (short) 10;
  /** Member of enumeration (a specific value). */
  short IMAGE_SYM_TYPE_MOE = (short) 11;
  /** Byte; unsigned one-byte integer. */
  short IMAGE_SYM_TYPE_BYTE = (short) 12;
  /** Word; unsigned two-byte integer. */
  short IMAGE_SYM_TYPE_WORD = (short) 13;
  /** Unsigned integer of natural size (normally, four bytes). */
  short IMAGE_SYM_TYPE_UINT = (short) 14;
  /** Unsigned four-byte integer. */
  short IMAGE_SYM_TYPE_DWORD = (short) 15;

  /** No derived type; the symbol is a simple scalar variable.  */
  short IMAGE_SYM_DTYPE_NULL = (short) 0;
  /** Pointer to base type. */
  short IMAGE_SYM_DTYPE_POINTER = (short) 1;
  /** Function returning base type. */
  short IMAGE_SYM_DTYPE_FUNCTION = (short) 2;
  /** Array of base type. */
  short IMAGE_SYM_DTYPE_ARRAY = (short) 3;

  //
  // Storage Class
  //

  /** (0xFF) Special symbol representing end of function, for
      debugging purposes. */
  byte IMAGE_SYM_CLASS_END_OF_FUNCTION = (byte) -1;
  /** No storage class assigned. */
  byte IMAGE_SYM_CLASS_NULL = (byte) 0;
  /** Automatic (stack) variable. The Value field specifies stack
      frame offset. */
  byte IMAGE_SYM_CLASS_AUTOMATIC = (byte) 1;
  /** Used by Microsoft tools for external symbols. The Value field
      indicates the size if the section number is IMAGE_SYM_UNDEFINED
      (0). If the section number is not 0, then the Value field
      specifies the offset within the section. */
  byte IMAGE_SYM_CLASS_EXTERNAL = 2;
  /** The Value field specifies the offset of the symbol within the
      section. If the Value is 0, then the symbol represents a section
      name. */
  byte IMAGE_SYM_CLASS_STATIC = (byte) 3;
  /** Register variable. The Value field specifies register number. */
  byte IMAGE_SYM_CLASS_REGISTER = (byte) 4;
  /** Symbol is defined externally. */
  byte IMAGE_SYM_CLASS_EXTERNAL_DEF = (byte) 5;
  /** Code label defined within the module. The Value field specifies
      the offset of the symbol within the section. */
  byte IMAGE_SYM_CLASS_LABEL = (byte) 6;
  /** Reference to a code label not defined. */
  byte IMAGE_SYM_CLASS_UNDEFINED_LABEL = (byte) 7;
  /** Structure member. The Value field specifies nth member. */
  byte IMAGE_SYM_CLASS_MEMBER_OF_STRUCT = (byte) 8;
  /** Formal argument (parameter) of a function. The Value field
      specifies nth argument. */
  byte IMAGE_SYM_CLASS_ARGUMENT = (byte) 9;
  /** Structure tag-name entry. */
  byte IMAGE_SYM_CLASS_STRUCT_TAG = (byte) 10;
  /** Union member. The Value field specifies nth member. */
  byte IMAGE_SYM_CLASS_MEMBER_OF_UNION = (byte) 11;
  /** Union tag-name entry. */
  byte IMAGE_SYM_CLASS_UNION_TAG = (byte) 12;
  /** Typedef entry. */
  byte IMAGE_SYM_CLASS_TYPE_DEFINITION = (byte) 13;
  /** Static data declaration. */
  byte IMAGE_SYM_CLASS_UNDEFINED_STATIC = (byte) 14;
  /** Enumerated type tagname entry. */
  byte IMAGE_SYM_CLASS_ENUM_TAG = (byte) 15;
  /** Member of enumeration. Value specifies nth member. */
  byte IMAGE_SYM_CLASS_MEMBER_OF_ENUM = (byte) 16;
  /** Register parameter. */
  byte IMAGE_SYM_CLASS_REGISTER_PARAM = (byte) 17;
  /** Bit-field reference. Value specifies nth bit in the bit field. */
  byte IMAGE_SYM_CLASS_BIT_FIELD = (byte) 18;
  /** A .bb (beginning of block) or .eb (end of block) record. Value
      is the relocatable address of the code location. */
  byte IMAGE_SYM_CLASS_BLOCK = (byte) 100;
  /** Used by Microsoft tools for symbol records that define the
      extent of a function: begin function (named .bf), end function
      (.ef), and lines in function (.lf). For .lf records, Value gives
      the number of source lines in the function. For .ef records,
      Value gives the size of function code. */
  byte IMAGE_SYM_CLASS_FUNCTION = (byte) 101;
  /** End of structure entry. */
  byte IMAGE_SYM_CLASS_END_OF_STRUCT = (byte) 102;
  /** Used by Microsoft tools, as well as traditional COFF format, for
      the source-file symbol record. The symbol is followed by
      auxiliary records that name the file. */
  byte IMAGE_SYM_CLASS_FILE = (byte) 103;
  /** Definition of a section (Microsoft tools use STATIC storage
      class instead). */
  byte IMAGE_SYM_CLASS_SECTION = (byte) 104;
  /** Weak external. */
  byte IMAGE_SYM_CLASS_WEAK_EXTERNAL = (byte) 105;
}
