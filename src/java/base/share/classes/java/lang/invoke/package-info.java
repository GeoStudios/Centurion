/java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.lang.invoke/

/java.base.share.classes.java.lang.invokejava.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke The {@code java.lang.invoke} package provides low-level primitives for interacting
 java.base.share.classes.java.lang.invoke with the Java Virtual Machine.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke As described in the Java Virtual Machine Specification, certain types in this package
 java.base.share.classes.java.lang.invoke are given special treatment by the virtual machine:
 java.base.share.classes.java.lang.invoke <ul>
 java.base.share.classes.java.lang.invoke <li>The classes {@link java.lang.invoke.MethodHandle MethodHandle} and
 java.base.share.classes.java.lang.invoke {@link java.lang.invoke.VarHandle VarHandle} contain
 java.base.share.classes.java.lang.invoke <a href="MethodHandle.html#sigpoly">signature polymorphic methods</a>
 java.base.share.classes.java.lang.invoke which can be linked regardless of their type descriptor.
 java.base.share.classes.java.lang.invoke Normally, method linkage requires exact matching of type descriptors.
 java.base.share.classes.java.lang.invoke </li>
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <li>The JVM bytecode format supports immediate constants of
 java.base.share.classes.java.lang.invoke the classes {@link java.lang.invoke.MethodHandle MethodHandle} and
 java.base.share.classes.java.lang.invoke {@link java.lang.invoke.MethodType MethodType}.
 java.base.share.classes.java.lang.invoke </li>
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <li>The {@code invokedynamic} instruction makes use of bootstrap {@code MethodHandle}
 java.base.share.classes.java.lang.invoke constants to dynamically resolve {@code CallSite} objects for custom method invocation
 java.base.share.classes.java.lang.invoke behavior.
 java.base.share.classes.java.lang.invoke </li>
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <li>The {@code ldc} instruction makes use of bootstrap {@code MethodHandle} constants
 java.base.share.classes.java.lang.invoke to dynamically resolve custom constant values.
 java.base.share.classes.java.lang.invoke </li>
 java.base.share.classes.java.lang.invoke </ul>
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h2><a id="jvm_mods"></a>Dynamic resolution of call sites and constants</h2>
 java.base.share.classes.java.lang.invoke The following low-level information summarizes relevant parts of the
 java.base.share.classes.java.lang.invoke Java Virtual Machine specification.  For full details, please see the
 java.base.share.classes.java.lang.invoke current version of that specification.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h3><a id="indyinsn"></a>Dynamically-computed call sites</h3>
 java.base.share.classes.java.lang.invoke An {@code invokedynamic} instruction is originally in an unlinked state.
 java.base.share.classes.java.lang.invoke In this state, there is no target method for the instruction to invoke.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Before the JVM can execute an {@code invokedynamic} instruction,
 java.base.share.classes.java.lang.invoke the instruction must first be <em>linked</em>.
 java.base.share.classes.java.lang.invoke Linking is accomplished by calling a <em>bootstrap method</em>
 java.base.share.classes.java.lang.invoke which is given the static information content of the call,
 java.base.share.classes.java.lang.invoke and which must produce a {@link java.lang.invoke.CallSite}
 java.base.share.classes.java.lang.invoke that gives the behavior of the invocation.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Each {@code invokedynamic} instruction statically specifies its own
 java.base.share.classes.java.lang.invoke bootstrap method as a constant pool reference.
 java.base.share.classes.java.lang.invoke The constant pool reference also specifies the invocation's name and method type descriptor,
 java.base.share.classes.java.lang.invoke just like {@code invokestatic} and the other invoke instructions.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h3><a id="condycon"></a>Dynamically-computed constants</h3>
 java.base.share.classes.java.lang.invoke The constant pool may contain constants tagged {@code CONSTANT_Dynamic},
 java.base.share.classes.java.lang.invoke equipped with bootstrap methods which perform their resolution.
 java.base.share.classes.java.lang.invoke Such a <em>dynamic constant</em> is originally in an unresolved state.
 java.base.share.classes.java.lang.invoke Before the JVM can use a dynamically-computed constant, it must first be <em>resolved</em>.
 java.base.share.classes.java.lang.invoke Dynamically-computed constant resolution is accomplished by calling a <em>bootstrap method</em>
 java.base.share.classes.java.lang.invoke which is given the static information content of the constant,
 java.base.share.classes.java.lang.invoke and which must produce a value of the constant's statically declared type.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Each dynamically-computed constant statically specifies its own
 java.base.share.classes.java.lang.invoke bootstrap method as a constant pool reference.
 java.base.share.classes.java.lang.invoke The constant pool reference also specifies the constant's name and field type descriptor,
 java.base.share.classes.java.lang.invoke just like {@code getstatic} and the other field reference instructions.
 java.base.share.classes.java.lang.invoke (Roughly speaking, a dynamically-computed constant is to a dynamically-computed call site
 java.base.share.classes.java.lang.invoke as a {@code CONSTANT_Fieldref} is to a {@code CONSTANT_Methodref}.)
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h3><a id="bsm"></a>Execution of bootstrap methods</h3>
 java.base.share.classes.java.lang.invoke Resolving a dynamically-computed call site or constant
 java.base.share.classes.java.lang.invoke starts with resolving constants from the constant pool for the
 java.base.share.classes.java.lang.invoke following items:
 java.base.share.classes.java.lang.invoke <ul>
 java.base.share.classes.java.lang.invoke <li>the bootstrap method, a {@code CONSTANT_MethodHandle}</li>
 java.base.share.classes.java.lang.invoke <li>the {@code Class} or {@code MethodType} derived from
 java.base.share.classes.java.lang.invoke type component of the {@code CONSTANT_NameAndType} descriptor</li>
 java.base.share.classes.java.lang.invoke <li>static arguments, if any (note that static arguments can themselves be
 java.base.share.classes.java.lang.invoke dynamically-computed constants)</li>
 java.base.share.classes.java.lang.invoke </ul>
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke The bootstrap method is then invoked, as if by
 java.base.share.classes.java.lang.invoke {@link java.lang.invoke.MethodHandle#invoke MethodHandle.invoke},
 java.base.share.classes.java.lang.invoke with the following arguments:
 java.base.share.classes.java.lang.invoke <ul>
 java.base.share.classes.java.lang.invoke <li>a {@code MethodHandles.Lookup}, which is a lookup object on the <em>caller class</em>
 java.base.share.classes.java.lang.invoke in which dynamically-computed constant or call site occurs</li>
 java.base.share.classes.java.lang.invoke <li>a {@code String}, the name mentioned in the {@code CONSTANT_NameAndType}</li>
 java.base.share.classes.java.lang.invoke <li>a {@code MethodType} or {@code Class}, the resolved type descriptor of the {@code CONSTANT_NameAndType}</li>
 java.base.share.classes.java.lang.invoke <li>a {@code Class}, the resolved type descriptor of the constant, if it is a dynamic constant </li>
 java.base.share.classes.java.lang.invoke <li>the additional resolved static arguments, if any</li>
 java.base.share.classes.java.lang.invoke </ul>
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke For a dynamically-computed call site, the returned result must be a non-null reference to a
 java.base.share.classes.java.lang.invoke {@link java.lang.invoke.CallSite CallSite}.
 java.base.share.classes.java.lang.invoke The type of the call site's target must be exactly equal to the type
 java.base.share.classes.java.lang.invoke derived from the invocation's type descriptor and passed to
 java.base.share.classes.java.lang.invoke the bootstrap method. If these conditions are not met, a {@code BootstrapMethodError} is thrown.
 java.base.share.classes.java.lang.invoke On success the call site then becomes permanently linked to the {@code invokedynamic}
 java.base.share.classes.java.lang.invoke instruction.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke For a dynamically-computed constant, the first parameter of the bootstrap
 java.base.share.classes.java.lang.invoke method must be assignable to {@code MethodHandles.Lookup}. If this condition
 java.base.share.classes.java.lang.invoke is not met, a {@code BootstrapMethodError} is thrown.
 java.base.share.classes.java.lang.invoke On success the result of the bootstrap method is cached as the resolved
 java.base.share.classes.java.lang.invoke constant value.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke If an exception, {@code E} say, occurs during execution of the bootstrap method, then
 java.base.share.classes.java.lang.invoke resolution fails and terminates abnormally. {@code E} is rethrown if the type of
 java.base.share.classes.java.lang.invoke {@code E} is {@code Error} or a subclass, otherwise a
 java.base.share.classes.java.lang.invoke {@code BootstrapMethodError} that wraps {@code E} is thrown.
 java.base.share.classes.java.lang.invoke If this happens, the same error will be thrown for all
 java.base.share.classes.java.lang.invoke subsequent attempts to execute the {@code invokedynamic} instruction or load the
 java.base.share.classes.java.lang.invoke dynamically-computed constant.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h3>Timing of resolution</h3>
 java.base.share.classes.java.lang.invoke An {@code invokedynamic} instruction is linked just before its first execution.
 java.base.share.classes.java.lang.invoke A dynamically-computed constant is resolved just before the first time it is used
 java.base.share.classes.java.lang.invoke (by pushing it on the stack or linking it as a bootstrap method parameter).
 java.base.share.classes.java.lang.invoke The bootstrap method call implementing the linkage occurs within
 java.base.share.classes.java.lang.invoke a thread that is attempting a first execution or first use.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke If there are several such threads, the bootstrap method may be
 java.base.share.classes.java.lang.invoke invoked in several threads concurrently.
 java.base.share.classes.java.lang.invoke Therefore, bootstrap methods which access global application
 java.base.share.classes.java.lang.invoke data must take the usual precautions against race conditions.
 java.base.share.classes.java.lang.invoke In any case, every {@code invokedynamic} instruction is either
 java.base.share.classes.java.lang.invoke unlinked or linked to a unique {@code CallSite} object.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke In an application which requires {@code invokedynamic} instructions with individually
 java.base.share.classes.java.lang.invoke mutable behaviors, their bootstrap methods should produce distinct
 java.base.share.classes.java.lang.invoke {@link java.lang.invoke.CallSite CallSite} objects, one for each linkage request.
 java.base.share.classes.java.lang.invoke Alternatively, an application can link a single {@code CallSite} object
 java.base.share.classes.java.lang.invoke to several {@code invokedynamic} instructions, in which case
 java.base.share.classes.java.lang.invoke a change to the target method will become visible at each of
 java.base.share.classes.java.lang.invoke the instructions.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke If several threads simultaneously execute a bootstrap method for a single dynamically-computed
 java.base.share.classes.java.lang.invoke call site or constant, the JVM must choose one bootstrap method result and install it visibly to
 java.base.share.classes.java.lang.invoke all threads.  Any other bootstrap method calls are allowed to complete, but their
 java.base.share.classes.java.lang.invoke results are ignored.
 java.base.share.classes.java.lang.invoke <p style="font-size:smaller;">
 java.base.share.classes.java.lang.invoke <em>Discussion:</em>
 java.base.share.classes.java.lang.invoke These rules do not enable the JVM to share call sites,
 java.base.share.classes.java.lang.invoke or to issue &ldquo;causeless&rdquo; bootstrap method calls.
 java.base.share.classes.java.lang.invoke Every {@code invokedynamic} instruction transitions at most once from unlinked to linked,
 java.base.share.classes.java.lang.invoke just before its first invocation.
 java.base.share.classes.java.lang.invoke There is no way to undo the effect of a completed bootstrap method call.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke <h3>Types of bootstrap methods</h3>
 java.base.share.classes.java.lang.invoke For a dynamically-computed call site, the bootstrap method is invoked with parameter
 java.base.share.classes.java.lang.invoke types {@code MethodHandles.Lookup}, {@code String}, {@code MethodType}, and the types
 java.base.share.classes.java.lang.invoke of any static arguments; the return type is {@code CallSite}.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke For a dynamically-computed constant, the bootstrap method is invoked with parameter types
 java.base.share.classes.java.lang.invoke {@code MethodHandles.Lookup}, {@code String}, {@code Class}, and the types of any
 java.base.share.classes.java.lang.invoke static arguments; the return type is the type represented by the {@code Class}.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Because {@link java.lang.invoke.MethodHandle#invoke MethodHandle.invoke} allows for
 java.base.share.classes.java.lang.invoke adaptations between the invoked method type and the bootstrap method handle's method type,
 java.base.share.classes.java.lang.invoke there is flexibility in the declaration of the bootstrap method.
 java.base.share.classes.java.lang.invoke For a dynamically-computed constant the first parameter type of the bootstrap method handle
 java.base.share.classes.java.lang.invoke must be assignable to {@code MethodHandles.Lookup}, other than that constraint the same degree
 java.base.share.classes.java.lang.invoke of flexibility applies to bootstrap methods of dynamically-computed call sites and
 java.base.share.classes.java.lang.invoke dynamically-computed constants.
 java.base.share.classes.java.lang.invoke Note: this constraint allows for the future possibility where the bootstrap method is
 java.base.share.classes.java.lang.invoke invoked with just the parameter types of static arguments, thereby supporting a wider
 java.base.share.classes.java.lang.invoke range of methods compatible with the static arguments (such as methods that don't declare
 java.base.share.classes.java.lang.invoke or require the lookup, name, and type meta-data parameters).
 java.base.share.classes.java.lang.invoke <p> For example, for dynamically-computed call site, the first argument
 java.base.share.classes.java.lang.invoke could be {@code Object} instead of {@code MethodHandles.Lookup}, and the return type
 java.base.share.classes.java.lang.invoke could also be {@code Object} instead of {@code CallSite}.
 java.base.share.classes.java.lang.invoke (Note that the types and number of the stacked arguments limit
 java.base.share.classes.java.lang.invoke the legal kinds of bootstrap methods to appropriately typed
 java.base.share.classes.java.lang.invoke static methods and constructors.)
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke If a pushed value is a primitive type, it may be converted to a reference by boxing conversion.
 java.base.share.classes.java.lang.invoke If the bootstrap method is a variable arity method (its modifier bit {@code 0x0080} is set),
 java.base.share.classes.java.lang.invoke then some or all of the arguments specified here may be collected into a trailing array parameter.
 java.base.share.classes.java.lang.invoke (This is not a special rule, but rather a useful consequence of the interaction
 java.base.share.classes.java.lang.invoke between {@code CONSTANT_MethodHandle} constants, the modifier bit for variable arity methods,
 java.base.share.classes.java.lang.invoke and the {@link java.lang.invoke.MethodHandle#asVarargsCollector asVarargsCollector} transformation.)
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Given these rules, here are examples of legal bootstrap method declarations for
 java.base.share.classes.java.lang.invoke dynamically-computed call sites, given various numbers {@code N} of extra arguments.
 java.base.share.classes.java.lang.invoke The first row (marked {@code java.base.share.classes.java.lang.invoke}) will work for any number of extra arguments.
 java.base.share.classes.java.lang.invoke <table class="plain" style="vertical-align:top">
 java.base.share.classes.java.lang.invoke <caption style="display:none">Static argument types</caption>
 java.base.share.classes.java.lang.invoke <thead>
 java.base.share.classes.java.lang.invoke <tr><th scope="col">N</th><th scope="col">Sample bootstrap method</th></tr>
 java.base.share.classes.java.lang.invoke </thead>
 java.base.share.classes.java.lang.invoke <tbody>
 java.base.share.classes.java.lang.invoke <tr><th scope="row" style="font-weight:normal; vertical-align:top">java.base.share.classes.java.lang.invoke</th><td>
 java.base.share.classes.java.lang.invoke     <ul style="list-style:none; padding-left: 0; margin:0">
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, String name, MethodType type, Object... args)}
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Object... args)}
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Object caller, Object... nameAndTypeWithArgs)}
 java.base.share.classes.java.lang.invoke     </ul></td></tr>
 java.base.share.classes.java.lang.invoke <tr><th scope="row" style="font-weight:normal; vertical-align:top">0</th><td>
 java.base.share.classes.java.lang.invoke     <ul style="list-style:none; padding-left: 0; margin:0">
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, String name, MethodType type)}
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, Object... nameAndType)}
 java.base.share.classes.java.lang.invoke     </ul></td></tr>
 java.base.share.classes.java.lang.invoke <tr><th scope="row" style="font-weight:normal; vertical-align:top">1</th><td>
 java.base.share.classes.java.lang.invoke     {@code CallSite bootstrap(Lookup caller, String name, MethodType type, Object arg)}</td></tr>
 java.base.share.classes.java.lang.invoke <tr><th scope="row" style="font-weight:normal; vertical-align:top">2</th><td>
 java.base.share.classes.java.lang.invoke     <ul style="list-style:none; padding-left: 0; margin:0">
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, String name, MethodType type, Object... args)}
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, String name, MethodType type, String... args)}
 java.base.share.classes.java.lang.invoke     <li>{@code CallSite bootstrap(Lookup caller, String name, MethodType type, String x, int y)}
 java.base.share.classes.java.lang.invoke     </ul></td></tr>
 java.base.share.classes.java.lang.invoke </tbody>
 java.base.share.classes.java.lang.invoke </table>
 java.base.share.classes.java.lang.invoke The last example assumes that the extra arguments are of type
 java.base.share.classes.java.lang.invoke {@code String} and {@code Integer} (or {@code int}), respectively.
 java.base.share.classes.java.lang.invoke The second-to-last example assumes that all extra arguments are of type
 java.base.share.classes.java.lang.invoke {@code String}.
 java.base.share.classes.java.lang.invoke The other examples work with all types of extra arguments.  Note that all
 java.base.share.classes.java.lang.invoke the examples except the second and third also work with dynamically-computed
 java.base.share.classes.java.lang.invoke constants if the return type is changed to be compatible with the
 java.base.share.classes.java.lang.invoke constant's declared type (such as {@code Object}, which is always compatible).
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke Since dynamically-computed constants can be provided as static arguments to bootstrap
 java.base.share.classes.java.lang.invoke methods, there are no limitations on the types of bootstrap arguments.
 java.base.share.classes.java.lang.invoke However, arguments of type {@code boolean}, {@code byte}, {@code short}, or {@code char}
 java.base.share.classes.java.lang.invoke cannot be <em>directly</em> supplied by {@code CONSTANT_Integer}
 java.base.share.classes.java.lang.invoke constant pool entries, since the {@code asType} conversions do
 java.base.share.classes.java.lang.invoke not perform the necessary narrowing primitive conversions.
 java.base.share.classes.java.lang.invoke <p>
 java.base.share.classes.java.lang.invoke In the above examples, the return type is always {@code CallSite},
 java.base.share.classes.java.lang.invoke but that is not a necessary feature of bootstrap methods.
 java.base.share.classes.java.lang.invoke In the case of a dynamically-computed call site, the only requirement is that
 java.base.share.classes.java.lang.invoke the return type of the bootstrap method must be convertible
 java.base.share.classes.java.lang.invoke (using the {@code asType} conversions) to {@code CallSite}, which
 java.base.share.classes.java.lang.invoke means the bootstrap method return type might be {@code Object} or
 java.base.share.classes.java.lang.invoke {@code ConstantCallSite}.
 java.base.share.classes.java.lang.invoke In the case of a dynamically-resolved constant, the return type of the bootstrap
 java.base.share.classes.java.lang.invoke method must be convertible to the type of the constant, as
 java.base.share.classes.java.lang.invoke represented by its field type descriptor.  For example, if the
 java.base.share.classes.java.lang.invoke dynamic constant has a field type descriptor of {@code "C"}
 java.base.share.classes.java.lang.invoke ({@code char}) then the bootstrap method return type could be
 java.base.share.classes.java.lang.invoke {@code Object}, {@code Character}, or {@code char}, but not
 java.base.share.classes.java.lang.invoke {@code int} or {@code Integer}.
 java.base.share.classes.java.lang.invoke
 java.base.share.classes.java.lang.invoke @author John Rose, JSR 292 EG
 java.base.share.classes.java.lang.invoke @since 1.7
 java.base.share.classes.java.lang.invoke/

package java.lang.invoke;
