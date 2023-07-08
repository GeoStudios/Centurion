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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.win32.coff;


import java.util.*;















/** Provides iteration-style access to the symbols in the sstGlobalSym
    (and possibly other) subsections of the VC++ 5.0 debug
    information. Clients should walk down these platform-dependent
    symbols and transform them into the platform-independent
    interfaces described in the package sun.jvm.hotspot.debugger.csym. */

public interface DebugVC50SymbolIterator
  extends DebugVC50SymbolTypes, DebugVC50SymbolEnums {

  /** Indicates whether this iterator has processed all of the
      available symbols. */
  boolean done();

  /** Go to the next symbol. NOTE that the iterator is pointing at the
      first symbol initially, so one should use a while (!iter.done())
      { ...  iter.next(); } construct.

      @throw NoSuchElementException if the iterator is already done
      and next() is called. */
  void next() throws NoSuchElementException;

  /** Length of record, in bytes, excluding the length field. */
  short getLength();

  /** The type enumeration is defined in {@link
      sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolTypes} */
  int getType();

  /** For debugging: returns the file offset of the current symbol. */
  int getOffset();

  /////////////////////////
  // S_COMPILE accessors //
  /////////////////////////

  /** Machine enumeration specifying target processor; see
      DebugVC50SymbolEnums. */
  byte getCompilerTargetProcessor();

  /** Compile flags; see DebugVC50SymbolEnums. */
  int getCompilerFlags();

  /** Length-prefixed string specifying language processor version.
      Language processors can place additional data in version string
      if desired. */
  String getComplierVersion();

  //////////////////////////
  // S_REGISTER accessors //
  //////////////////////////

  /** Type of the symbol which is in the register */
  int getRegisterSymbolType();

  /** Enumerate of the registers in which the symbol is stored. The
      high and low bytes are treated independently for values split
      across two registers (i.e., 64-bit values on a 32-bit machine.) */
  short getRegisterEnum();

  /** Length-prefixed name of the symbol stored in the register. */
  String getRegisterSymbolName();

  // Note: register tracking elided as it is not implemented in the
  // Microsoft compilers.

  //////////////////////////
  // S_CONSTANT accessors //
  //////////////////////////

  /** Type of symbol or containing enum. This record is used to output
      constants and C enumerations. If used to output an enumeration,
      then the type index refers to the containing enum. */
  int getConstantType();

  /** Numeric leaf containing the value of the symbol as an int */
  int getConstantValueAsInt() throws DebugVC50WrongNumericTypeException;

  /** Numeric leaf containing the value of the symbol as a long */
  long getConstantValueAsLong() throws DebugVC50WrongNumericTypeException;

  /** Numeric leaf containing the value of the symbol as a float */
  float getConstantValueAsFloat() throws DebugVC50WrongNumericTypeException;

  /** Numeric leaf containing the value of the symbol as a double */
  double getConstantValueAsDouble() throws DebugVC50WrongNumericTypeException;

  /** Length-prefixed name of the symbol */
  String getConstantName();

  /////////////////////
  // S_UDT accessors //
  /////////////////////

  /** Type of symbol. This specifies a C typedef or user-defined type,
      such as classes, structures, unions, or enums. */
  int getUDTType();

  /** Length-prefixed name of the user defined type. */
  String getUDTName();

  /////////////////////////
  // S_SSEARCH accessors //
  /////////////////////////

  // FIXME: Add more documentation and understand what this does

  /** $$SYMBOL offset of the procedure or thunk record for this module
      that has the lowest offset for the specified segment. */
  int getSearchSymbolOffset();

  /** Segment (PE section) that this Start Search refers to. */
  short getSearchSegment();

  /////////////////////
  // S_END accessors //
  /////////////////////

  // (No accessors)
  // Closes the scope of the nearest preceding Block Start, Global
  // Procedure Start, Local Procedure Start, With Start, or Thunk
  // Start definition.

  //////////////////////
  // S_SKIP accessors //
  //////////////////////

  // (No accessors)
  // Use the length field, available in every symbol, to skip over
  // these records.

  ///////////////////////////
  // S_CVRESERVE accessors //
  ///////////////////////////

  // (No accessors)

  /////////////////////////
  // S_OBJNAME accessors //
  /////////////////////////

  /** Signature used to determine whether changes in precompiled types
      defined in this module require a recompilation of users of those
      types. This does not have much meaning given that the algorithm
      for computing the signature is unspecified. */
  int getObjectCodeViewSignature();

  /** Length prefixed name of the object file without any path
      information prepended to the name. */
  String getObjectName();

  ////////////////////////
  // S_ENDARG accessors //
  ////////////////////////

  // (No accessors)

  //////////////////////////
  // S_COBOLUDT accessors //
  //////////////////////////

  // (Elided as they are irrelevant)

  /////////////////////////
  // S_MANYREG accessors //
  /////////////////////////

  /** Type index of the symbol. This record is used to specify that a
      symbol is stored in a set of registers. */
  int getManyRegType();

  /** Count of the register enumerates that follow. */
  byte getManyRegCount();

  /** Get the <i>i</i>th register (0..getManyRegCount() - 1). The
      registers are listed high order register first. */
  byte getManyRegRegister(int i);

  /** Name of the symbol. */
  String getManyRegName();

  ////////////////////////
  // S_RETURN accessors //
  ////////////////////////

  /** Logical or of FUNCRET_VARARGS_LEFT_TO_RIGHT_MASK (push varargs
      left to right if set) and FUNCRET_RETURNEE_STACK_CLEANUP_MASK
      (returnee cleans up stack if true). */
  short getReturnFlags();

  /** Function return style; see constants in {@link
      sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolEnums}. */
  byte getReturnStyle();

  /** Get count of registers containing return value; only valid for
      FUNCRET_IN_REGISTERS return style. */
  byte getReturnRegisterCount();

  /** Get <i>i</i>th register (0..getReturnRegisterCount() - 1)
      containing return value, high order first; only valid for
      FUNCRET_IN_REGISTERS return style. */
  byte getReturnRegister(int i);

  ///////////////////////////
  // S_ENTRYTHIS accessors //
  ///////////////////////////

  /** Advance this iterator to the symbol (which actually describes
      the <b>this</b> pointer) contained within the S_ENTRYTHIS
      symbol. */
  void advanceToEntryThisSymbol();

  ///////////////////////////////////////////////////////////////////////
  //                                                                   //
  //                                                                   //
  // Symbols for (Intel) 16:32 Segmented and 32-bit Flat Architectures //
  //                                                                   //
  //                                                                   //
  ///////////////////////////////////////////////////////////////////////

  /////////////////////////
  // S_BPREL32 accessors //
  /////////////////////////

  // This symbol specifies symbols that are allocated on the stack for
  // a procedure. For C/C++, these include the actual parameters to a
  // function and the local nonstatic variables of functions.

  /** Signed offset relative to BP. If 0, then the symbol was assigned
      to a register or never instantiated by the optimizer and cannot
      be evaluated because its location is unknown. */
  int getBPRelOffset();

  /** Type of the symbol. */
  int getBPRelType();

  /** Length-prefixed name of the symbol. */
  String getBPRelName();

  ///////////////////////////////////////
  // S_LDATA32 and S_GDATA32 accessors //
  ///////////////////////////////////////

  // FIXME: consider documenting this as covering S_PUB32 symbols as
  // well

  // The formats of S_LDATA32 and S_GDATA32 symbols match; the only
  // difference is the type tag.
  //
  // LDATA32 symbols are used for data that is not exported from a
  // module. In C/C++, symbols that are declared static are emitted as
  // Local Data symbols. Symbols that are emitted as Local Data cannot
  // be moved by CVPACK into the global symbol table for the
  // executable file.
  //
  // GDATA32 records have the same format as the Local Data 16:32
  // except that the record type is S_GDATA32. For C/C++, symbols that
  // are not specifically declared static are emitted as Global Data
  // Symbols and can be compacted by CVPACK into the global symbol
  // table.

  /** Type index of the symbol. */
  int getLGDataType();

  /** Offset portion of the symbol address. */
  int getLGDataOffset();

  /** Segment portion of the symbol address. */
  short getLGDataSegment();

  /** Length-prefixed name of symbol. */
  String getLGDataName();

  ///////////////////////
  // S_PUB32 accessors //
  ///////////////////////

  // FIXME: has the same format as the above; consider updating
  // documentation. No separate accessors provided.

  ///////////////////////////////////////
  // S_LPROC32 and S_GPROC32 accessors //
  ///////////////////////////////////////

  // LPROC32 and GPROC32 symbols have the same format, differing only
  // in the type tag.
  //
  // The LPROC32 symbol record defines a local (file static) procedure
  // definition. For C/C++, functions that are declared static to a
  // module are emitted as Local Procedure symbols. Functions not
  // specifically declared static are emitted as Global Procedures.
  //
  // GPROC32 records are used for procedures that are not specifically
  // declared static to a module. The format is the same as the Local
  // Procedure Start 16:32 symbol.

  /** Creates a new symbol iterator pointing to the symbol opening the
      enclosing lexical scope of this function (if any); returns null
      if there is no enclosing scope. */
  DebugVC50SymbolIterator getLGProcParent();

  /** Gets the absolute file offset of the parent symbol, or 0 if
      none. This is useful for constructing and resolving types in a
      lazy fashion. */
  int getLGProcParentOffset();

  /** Creates a new symbol iterator pointing to the block end symbol
      terminating the lexical scope, or NULL if there is no containing
      lexical scope. */
  DebugVC50SymbolIterator getLGProcEnd();

  /** Gets the absolute file offset of the end symbol. This is useful
      for constructing and resolving types in a lazy fashion. */
  int getLGProcEndOffset();

  /** Creates a new symbol iterator pointing to the next outermost
      scope symbol in the segment (if any); returns null if this is
      the last outermost scope for the current segment. (See the
      documentation for more information.) */
  DebugVC50SymbolIterator getLGProcNext();

  /** Gets the absolute file offset of the next symbol, or 0 if none.
      This is useful for constructing and resolving types in a lazy
      fashion. */
  int getLGProcNextOffset();

  /** Length in bytes of this procedure. */
  int getLGProcLength();

  /** Offset in bytes from the start of the procedure to the point
      where the stack frame has been set up. Parameter and frame
      variables can be viewed at this point. */
  int getLGProcDebugStart();

  /** Offset in bytes from the start of the procedure to the point
      where the procedure is ready to return and has calculated its
      return value, if any. Frame and register variables can still be
      viewed. */
  int getLGProcDebugEnd();

  /** Type of the procedure type record. */
  int getLGProcType();

  /** Offset portion of the procedure address. */
  int getLGProcOffset();

  /** Segment portion of the procedure address. */
  short getLGProcSegment();

  /** Value defined by bitwise or of the the PROCFLAGS enumeration in
      {@link
      sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolEnums}. */
  byte getLGProcFlags();

  /** Length-prefixed name of procedure. */
  String getLGProcName();

  /////////////////////////
  // S_THUNK32 accessors //
  /////////////////////////

  // This record is used to specify any piece of code that exists
  // outside a procedure. It is followed by an End record. The thunk
  // record is intended for small code fragments. and a two byte
  // length field is sufficient for its intended purpose.

  /** Creates a new symbol iterator pointing to the symbol opening the
      enclosing lexical scope of this thunk (if any); returns null if
      there is no enclosing scope. */
  DebugVC50SymbolIterator getThunkParent();

  /** Gets the absolute file offset of the parent symbol, or 0 if
      none. This is useful for constructing and resolving types in a
      lazy fashion. */
  int getThunkParentOffset();

  /** Creates a new symbol iterator pointing to the block end symbol
      terminating the lexical scope, or NULL if there is no containing
      lexical scope. */
  DebugVC50SymbolIterator getThunkEnd();

  /** Gets the absolute file offset of the end symbol. This is useful
      for constructing and resolving types in a lazy fashion. */
  int getThunkEndOffset();

  /** Creates a new symbol iterator pointing to the next outermost
      scope symbol in the segment (if any); returns null if this is
      the last outermost scope for the current segment. (See the
      documentation for more information.) */
  DebugVC50SymbolIterator getThunkNext();

  /** Gets the absolute file offset of the next symbol, or 0 if none.
      This is useful for constructing and resolving types in a lazy
      fashion. */
  int getThunkNextOffset();

  /** Offset portion of the thunk address. */
  int getThunkOffset();

  /** Segment portion of the procedure address. */
  short getThunkSegment();

  /** Length in bytes of this thunk. */
  short getThunkLength();

  /** Ordinal specifying the type of thunk; see THUNK enumeration in
      {@link
      sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolEnums}. */
  byte getThunkType();

  /** Length-prefixed name of thunk. */
  String getThunkName();

  /** Delta to be added to "this" pointer; only valid if thunk type is
      "adjustor". */
  short getThunkAdjustorThisDelta();

  /** Length-prefixed name of target function; only valid if thunk type is
      "adjustor". */
  String getThunkAdjustorTargetName();

  /** Displacement into the virtual table; only valid if thunk type is
      "vcall". */
  short getThunkVCallDisplacement();

  /** Offset of p-code entry point; only valid if thunk type is
      "pcode". */
  int getThunkPCodeOffset();

  /** Segment of p-code entry point; only valid if thunk type is
      "pcode". */
  short getThunkPCodeSegment();

  /////////////////////////
  // S_BLOCK32 accessors //
  /////////////////////////

  // This symbol specifies the start of an inner block of lexically
  // scoped symbols. The lexical scope is terminated by a matching
  // S_END symbol.

  /** Creates a new symbol iterator pointing to the symbol opening the
      enclosing lexical scope of this scope (if any); returns null if
      there is no enclosing scope. */
  DebugVC50SymbolIterator getBlockParent();

  /** Gets the absolute file offset of the parent symbol, or 0 if
      none. This is useful for constructing and resolving types in a
      lazy fashion. */
  int getBlockParentOffset();

  /** Creates a new symbol iterator pointing to the block end symbol
      terminating this scope. */
  DebugVC50SymbolIterator getBlockEnd();

  /** Gets the absolute file offset of the end symbol. This is useful
      for constructing and resolving types in a lazy fashion. */
  int getBlockEndOffset();

  /** Length in bytes of the scope of this block. */
  int getBlockLength();

  /** Offset portion of the segmented procedure address. */
  int getBlockOffset();

  /** Segment portion of the segmented procedure address. */
  short getBlockSegment();

  /** Length-prefixed name of the block. */
  String getBlockName();

  ////////////////////////
  // S_WITH32 accessors //
  ////////////////////////

  // FIXME: this is a Pascal construct; ignored for now

  /////////////////////////
  // S_LABEL32 accessors //
  /////////////////////////

  /** Offset portion of the segmented address of the start of the
      block. */
  int getLabelOffset();

  /** Segment portion of the segmented address of the start of the
      block. */
  short getLabelSegment();

  /** Label flags. These are the same as the PROCFLAGS enumeration. */
  byte getLabelFlags();

  /** Length prefixed name of label. */
  String getLabelName();

  ////////////////////////////
  // S_CEXMODEL32 accessors //
  ////////////////////////////

  // This record is used to notify the debugger that, starting at the
  // given code offset and until the address specified by the next
  // Change Execution Model record, the execution model is of the
  // specified type. The native execution model is assumed in the
  // absence of Change Execution Model records.

  /** Offset portion of start of the block where the change occurs. */
  int getChangeOffset();

  /** Segment portion of start of the block where the change occurs. */
  short getChangeSegment();

  /** The execution model, enumerated in EXMODEL constants in {@link
      sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolEnums}. */
  short getChangeModel();

  // FIXME: figure out how to deal with variant (or whether it is
  // necessary)

  ////////////////////////////
  // S_VFTTABLE32 accessors //
  ////////////////////////////

  // This record is used to describe the base class path for the
  // virtual function table descriptor.

  /** The type index of the class at the root of the path. */
  int getVTableRoot();

  /** Type index of the record describing the base class path from the
      root to the leaf class for the virtual function table. */
  int getVTablePath();

  /** Offset portion of start of the virtual function table. */
  int getVTableOffset();

  /** Segment portion of the virtual function table. */
  short getVTableSegment();

  //////////////////////////
  // S_REGREL32 accessors //
  //////////////////////////

  // This symbol specifies symbols that are allocated relative to a
  // register.

  /** Signed offset relative to register. */
  int getRegRelOffset();

  /** Type of the symbol. */
  int getRegRelType();

  /** Register enumerates on which the symbol is based. Note that the
      register field can specify a pair of register such as ES:EBX. */
  short getRegRelRegister();

  /** Length-prefixed name of the symbol. */
  String getRegRelName();

  ///////////////////////////////////////////
  // S_LTHREAD32 and S_GTHREAD32 accessors //
  ///////////////////////////////////////////

  // These symbols are used for data declared with the __thread
  // storage attribute that is not exported from a module. In C/C++,
  // __thread symbols that are declared static are emitted as Local
  // Thread Storage 16:32 symbols. Symbols that are emitted as Local
  // Thread Storage 16:32 cannot be moved by CVPACK into the global
  // symbol table for the executable file. __thread symbols that are
  // not specifically declared static are emitted as Global Thread
  // Storage 16:32 symbols and can be compacted by CVPACK into the
  // global symbol table.

  /** Type index. */
  int getLThreadType();

  /** Offset into thread local storage. */
  int getLThreadOffset();

  /** Segment of thread local storage. */
  short getLThreadSegment();

  /** Length prefixed name. */
  String getLThreadName();

  // NOTE: accessors for all other kinds of symbols (i.e., MIPS)
  // elided for now (FIXME)
}
