/java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.lang.constant/

/java.base.share.classes.java.lang.constantjava.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant Classes and interfaces to represent <em>nominal descriptors</em> for run-time
 java.base.share.classes.java.lang.constant entities such as classes or method handles, and classfile entities such as
 java.base.share.classes.java.lang.constant constant pool entries or {@code invokedynamic} call sites.  These classes
 java.base.share.classes.java.lang.constant are suitable for use in bytecode reading and writing APIs, {@code invokedynamic}
 java.base.share.classes.java.lang.constant bootstraps, bytecode intrinsic APIs, and compile-time or link-time program
 java.base.share.classes.java.lang.constant analysis tools.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <p>Every API that reads and writes bytecode instructions needs to model the
 java.base.share.classes.java.lang.constant operands to these instructions and other classfile structures (such as entries
 java.base.share.classes.java.lang.constant in the bootstrap methods table or stack maps, which frequently reference
 java.base.share.classes.java.lang.constant entries in the classfile constant pool.) Such entries can denote values of
 java.base.share.classes.java.lang.constant fundamental types, such as strings or integers; parts of a program, such as
 java.base.share.classes.java.lang.constant classes or method handles; or values of arbitrary user-defined types.  The
 java.base.share.classes.java.lang.constant {@link java.lang.constant.ConstantDesc} hierarchy provides a representation of
 java.base.share.classes.java.lang.constant constant pool entries in nominal form that is convenient for APIs to model
 java.base.share.classes.java.lang.constant operands of bytecode instructions.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <h2><a id="nominal"></a>Nominal Descriptors</h2>
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <p>A {@link java.lang.constant.ConstantDesc} is a description of a constant
 java.base.share.classes.java.lang.constant value.  Such a description is the <em>nominal form</em> of the constant value;
 java.base.share.classes.java.lang.constant it is not the value itself, but rather a "recipe" for describing the value,
 java.base.share.classes.java.lang.constant storing the value in a constant pool entry, or reconstituting the value given
 java.base.share.classes.java.lang.constant a class loading context.  Every {@link java.lang.constant.ConstantDesc}
 java.base.share.classes.java.lang.constant knows how to <em>resolve</em> itself -- compute the value that it describes --
 java.base.share.classes.java.lang.constant via {@link java.lang.constant.ConstantDesc#resolveConstantDesc(java.lang.invoke.MethodHandles.Lookup) ConstantDesc.resolveConstantDesc}.
 java.base.share.classes.java.lang.constant This allows an API which accepts {@link java.lang.constant.ConstantDesc}
 java.base.share.classes.java.lang.constant objects to evaluate them reflectively, provided that the classes and methods
 java.base.share.classes.java.lang.constant referenced in their nominal description are present and accessible.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <p>The subtypes of {@link java.lang.constant.ConstantDesc} describe various kinds
 java.base.share.classes.java.lang.constant of constant values.  For each type of loadable constant pool entry defined in JVMS {@jvms 4.4},
 java.base.share.classes.java.lang.constant there is a corresponding subtype of {@link java.lang.constant.ConstantDesc}:
 java.base.share.classes.java.lang.constant {@link java.lang.constant.ClassDesc}, {@link java.lang.constant.MethodTypeDesc},
 java.base.share.classes.java.lang.constant {@link java.lang.constant.DirectMethodHandleDesc}, {@link java.lang.String},
 java.base.share.classes.java.lang.constant {@link java.lang.Integer}, {@link java.lang.Long}, {@link java.lang.Float},
 java.base.share.classes.java.lang.constant {@link java.lang.Double}, and {@link java.lang.constant.DynamicConstantDesc}.  These classes
 java.base.share.classes.java.lang.constant provide type-specific accessor methods to extract the nominal information for
 java.base.share.classes.java.lang.constant that kind of constant.  When a bytecode-writing API encounters a {@link java.lang.constant.ConstantDesc},
 java.base.share.classes.java.lang.constant it should examine it to see which of these types it is, cast it, extract
 java.base.share.classes.java.lang.constant its nominal information, and generate the corresponding entry to the constant pool.
 java.base.share.classes.java.lang.constant When a bytecode-reading API encounters a constant pool entry, it can
 java.base.share.classes.java.lang.constant convert it to the appropriate type of nominal descriptor.  For dynamic
 java.base.share.classes.java.lang.constant constants, bytecode-reading APIs may wish to use the factory
 java.base.share.classes.java.lang.constant {@link java.lang.constant.DynamicConstantDesc#ofCanonical(DirectMethodHandleDesc, java.lang.String, ClassDesc, ConstantDesc[]) DynamicConstantDesc.ofCanonical},
 java.base.share.classes.java.lang.constant which will inspect the bootstrap and, for well-known bootstraps, return
 java.base.share.classes.java.lang.constant a more specific subtype of {@link java.lang.constant.DynamicConstantDesc}, such as
 java.base.share.classes.java.lang.constant {@link java.lang.Enum.EnumDesc}.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <p>Another way to obtain the nominal description of a value is to ask the value
 java.base.share.classes.java.lang.constant itself.  A {@link java.lang.constant.Constable} is a type whose values
 java.base.share.classes.java.lang.constant can describe themselves in nominal form as a {@link java.lang.constant.ConstantDesc}.
 java.base.share.classes.java.lang.constant Fundamental types such as {@link java.lang.String} and {@link java.lang.Class}
 java.base.share.classes.java.lang.constant implement {@link java.lang.constant.Constable}, as can user-defined
 java.base.share.classes.java.lang.constant classes.  Entities that generate classfiles (such as compilers) can introspect
 java.base.share.classes.java.lang.constant over constable objects to obtain a more efficient way to represent their values
 java.base.share.classes.java.lang.constant in classfiles.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant <p>This package also includes {@link java.lang.constant.DynamicCallSiteDesc},
 java.base.share.classes.java.lang.constant which represents a (non-loadable) {@code Constant_InvokeDynamic_info} constant
 java.base.share.classes.java.lang.constant pool entry.  It describes the bootstrap method, invocation name and type,
 java.base.share.classes.java.lang.constant and bootstrap arguments associated with an {@code invokedynamic} instruction.
 java.base.share.classes.java.lang.constant It is also suitable for describing {@code invokedynamic} call sites in bytecode
 java.base.share.classes.java.lang.constant reading and writing APIs.
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant @jvms 4.4 The Constant Pool
 java.base.share.classes.java.lang.constant
 java.base.share.classes.java.lang.constant @since Pre Java 1
 java.base.share.classes.java.lang.constant @author Logan Abernathy
 java.base.share.classes.java.lang.constant @edited 24/4/2023
 java.base.share.classes.java.lang.constant/
package java.lang.constant;
