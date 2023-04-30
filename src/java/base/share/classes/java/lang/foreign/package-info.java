/java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.lang.foreign/

/java.base.share.classes.java.lang.foreignjava.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <p>Provides low-level access to memory and functions outside the Java runtime.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h2 id="fma">Foreign memory access</h2>
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign The main abstraction introduced to support foreign memory access is {@link java.lang.foreign.MemorySegment}, which
 java.base.share.classes.java.lang.foreign models a contiguous region of memory, residing either inside or outside the Java heap. The contents of a memory
 java.base.share.classes.java.lang.foreign segment can be described using a {@link java.lang.foreign.MemoryLayout memory layout}, which provides
 java.base.share.classes.java.lang.foreign basic operations to query sizes, offsets and alignment constraints. Memory layouts also provide
 java.base.share.classes.java.lang.foreign an alternate, more abstract way, to <a href=MemorySegment.html#segment-deref>access memory segments</a>
 java.base.share.classes.java.lang.foreign using {@linkplain java.lang.foreign.MemoryLayout#varHandle(java.lang.foreign.MemoryLayout.PathElement...) var handles},
 java.base.share.classes.java.lang.foreign which can be computed using <a href="MemoryLayout.html#layout-paths"><em>layout paths</em></a>.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign For example, to allocate an off-heap region of memory big enough to hold 10 values of the primitive type {@code int},
 java.base.share.classes.java.lang.foreign and fill it with values ranging from {@code 0} to {@code 9}, we can use the following code:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang = java:
 java.base.share.classes.java.lang.foreign MemorySegment segment = MemorySegment.allocateNative(10 java.base.share.classes.java.lang.foreign 4, SegmentScope.auto());
 java.base.share.classes.java.lang.foreign for (int i = 0 ; i < 10 ; i++) {
 java.base.share.classes.java.lang.foreign     segment.setAtIndex(ValueLayout.JAVA_INT, i, i);
 java.base.share.classes.java.lang.foreign }
 java.base.share.classes.java.lang.foreign}
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign This code creates a <em>native</em> memory segment, that is, a memory segment backed by
 java.base.share.classes.java.lang.foreign off-heap memory; the size of the segment is 40 bytes, enough to store 10 values of the primitive type {@code int}.
 java.base.share.classes.java.lang.foreign The segment is associated with an {@linkplain java.lang.foreign.SegmentScope#auto() automatic scope}. This
 java.base.share.classes.java.lang.foreign means that the off-heap region of memory backing the segment is managed, automatically, by the garbage collector.
 java.base.share.classes.java.lang.foreign As such, the off-heap memory backing the native segment will be released at some unspecified
 java.base.share.classes.java.lang.foreign point <em>after</em> the segment becomes <a href="../../../java/lang/ref/package.html#reachability">unreachable</a>.
 java.base.share.classes.java.lang.foreign This is similar to what happens with direct buffers created via {@link java.nio.ByteBuffer#allocateDirect(int)}.
 java.base.share.classes.java.lang.foreign It is also possible to manage the lifecycle of allocated native segments more directly, as shown in a later section.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign Inside a loop, we then initialize the contents of the memory segment; note how the
 java.base.share.classes.java.lang.foreign {@linkplain java.lang.foreign.MemorySegment#setAtIndex(ValueLayout.OfInt, long, int) access method}
 java.base.share.classes.java.lang.foreign accepts a {@linkplain java.lang.foreign.ValueLayout value layout}, which specifies the size, alignment constraint,
 java.base.share.classes.java.lang.foreign byte order as well as the Java type ({@code int}, in this case) associated with the access operation. More specifically,
 java.base.share.classes.java.lang.foreign if we view the memory segment as a set of 10 adjacent slots, {@code s[i]}, where {@code 0 <= i < 10},
 java.base.share.classes.java.lang.foreign where the size of each slot is exactly 4 bytes, the initialization logic above will set each slot
 java.base.share.classes.java.lang.foreign so that {@code s[i] = i}, again where {@code 0 <= i < 10}.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h3 id="deallocation">Deterministic deallocation</h3>
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign When writing code that manipulates memory segments, especially if backed by memory which resides outside the Java heap, it is
 java.base.share.classes.java.lang.foreign often crucial that the resources associated with a memory segment are released when the segment is no longer in use,
 java.base.share.classes.java.lang.foreign and in a timely fashion. For this reason, there might be cases where waiting for the garbage collector to determine that a segment
 java.base.share.classes.java.lang.foreign is <a href="../../../java/lang/ref/package.html#reachability">unreachable</a> is not optimal.
 java.base.share.classes.java.lang.foreign Clients that operate under these assumptions might want to programmatically release the memory backing a memory segment.
 java.base.share.classes.java.lang.foreign This can be done, using the {@link java.lang.foreign.Arena} abstraction, as shown below:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang = java:
 java.base.share.classes.java.lang.foreign try (Arena arena = Arena.openConfined()) {
 java.base.share.classes.java.lang.foreign     MemorySegment segment = arena.allocate(10 java.base.share.classes.java.lang.foreign 4);
 java.base.share.classes.java.lang.foreign     for (int i = 0 ; i < 10 ; i++) {
 java.base.share.classes.java.lang.foreign         segment.setAtIndex(ValueLayout.JAVA_INT, i, i);
 java.base.share.classes.java.lang.foreign     }
 java.base.share.classes.java.lang.foreign }
 java.base.share.classes.java.lang.foreign}
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign This example is almost identical to the prior one; this time we first create an arena
 java.base.share.classes.java.lang.foreign which is used to allocate multiple native segments which share the same life-cycle. That is, all the segments
 java.base.share.classes.java.lang.foreign allocated by the arena will be associated with the same {@linkplain java.lang.foreign.SegmentScope scope}.
 java.base.share.classes.java.lang.foreign Note the use of the <em>try-with-resources</em> construct: this idiom ensures that the off-heap region of memory backing the
 java.base.share.classes.java.lang.foreign native segment will be released at the end of the block, according to the semantics described in Section {@jls 14.20.3}
 java.base.share.classes.java.lang.foreign of <cite>The Java Language Specification</cite>.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h3 id="safety">Safety</h3>
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign This API provides strong safety guarantees when it comes to memory access. First, when dereferencing a memory segment,
 java.base.share.classes.java.lang.foreign the access coordinates are validated (upon access), to make sure that access does not occur at any address which resides
 java.base.share.classes.java.lang.foreign <em>outside</em> the boundaries of the memory segment used by the access operation. We call this guarantee <em>spatial safety</em>;
 java.base.share.classes.java.lang.foreign in other words, access to memory segments is bounds-checked, in the same way as array access is, as described in
 java.base.share.classes.java.lang.foreign Section {@jls 15.10.4} of <cite>The Java Language Specification</cite>.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign Since memory segments created with an arena can become invalid (see above), segments are also validated (upon access) to make sure that
 java.base.share.classes.java.lang.foreign the scope associated with the segment being accessed is still alive.
 java.base.share.classes.java.lang.foreign We call this guarantee <em>temporal safety</em>. Together, spatial and temporal safety ensure that each memory access
 java.base.share.classes.java.lang.foreign operation either succeeds - and accesses a valid location within the region of memory backing the memory segment - or fails.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h2 id="ffa">Foreign function access</h2>
 java.base.share.classes.java.lang.foreign The key abstractions introduced to support foreign function access are {@link java.lang.foreign.SymbolLookup},
 java.base.share.classes.java.lang.foreign {@link java.lang.foreign.FunctionDescriptor} and {@link java.lang.foreign.Linker}. The first is used to look up symbols
 java.base.share.classes.java.lang.foreign inside libraries; the second is used to model the signature of foreign functions, while the third provides
 java.base.share.classes.java.lang.foreign linking capabilities which allows modelling foreign functions as {@link java.lang.invoke.MethodHandle} instances,
 java.base.share.classes.java.lang.foreign so that clients can perform foreign function calls directly in Java, without the need for intermediate layers of C/C++
 java.base.share.classes.java.lang.foreign code (as is the case with the <a href="{@docRoot}/../specs/jni/index.html">Java Native Interface (JNI)</a>).
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign For example, to compute the length of a string using the C standard library function {@code strlen} on a Linux x64 platform,
 java.base.share.classes.java.lang.foreign we can use the following code:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang = java:
 java.base.share.classes.java.lang.foreign Linker linker = Linker.nativeLinker();
 java.base.share.classes.java.lang.foreign SymbolLookup stdlib = linker.defaultLookup();
 java.base.share.classes.java.lang.foreign MethodHandle strlen = linker.downcallHandle(
 java.base.share.classes.java.lang.foreign     stdlib.find("strlen").get(),
 java.base.share.classes.java.lang.foreign     FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
 java.base.share.classes.java.lang.foreign );
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign try (Arena arena = Arena.openConfined()) {
 java.base.share.classes.java.lang.foreign     MemorySegment cString = arena.allocateUtf8String("Hello");
 java.base.share.classes.java.lang.foreign     long len = (long)strlen.invoke(cString); // 5
 java.base.share.classes.java.lang.foreign }
 java.base.share.classes.java.lang.foreign}
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign Here, we obtain a {@linkplain java.lang.foreign.Linker#nativeLinker() native linker} and we use it
 java.base.share.classes.java.lang.foreign to {@linkplain java.lang.foreign.SymbolLookup#find(java.lang.String) look up} the {@code strlen} symbol in the
 java.base.share.classes.java.lang.foreign standard C library; a <em>downcall method handle</em> targeting said symbol is subsequently
 java.base.share.classes.java.lang.foreign {@linkplain java.lang.foreign.Linker#downcallHandle(FunctionDescriptor, Linker.Option...) obtained}.
 java.base.share.classes.java.lang.foreign To complete the linking successfully, we must provide a {@link java.lang.foreign.FunctionDescriptor} instance,
 java.base.share.classes.java.lang.foreign describing the signature of the {@code strlen} function.
 java.base.share.classes.java.lang.foreign From this information, the linker will uniquely determine the sequence of steps which will turn
 java.base.share.classes.java.lang.foreign the method handle invocation (here performed using {@link java.lang.invoke.MethodHandle#invoke(java.lang.Object...)})
 java.base.share.classes.java.lang.foreign into a foreign function call, according to the rules specified by the ABI of the underlying platform.
 java.base.share.classes.java.lang.foreign The {@link java.lang.foreign.Arena} class also provides many useful methods for
 java.base.share.classes.java.lang.foreign interacting with foreign code, such as
 java.base.share.classes.java.lang.foreign {@linkplain java.lang.foreign.SegmentAllocator#allocateUtf8String(java.lang.String) converting} Java strings into
 java.base.share.classes.java.lang.foreign zero-terminated, UTF-8 strings, as demonstrated in the above example.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h3 id="upcalls">Upcalls</h3>
 java.base.share.classes.java.lang.foreign The {@link java.lang.foreign.Linker} interface also allows clients to turn an existing method handle (which might point
 java.base.share.classes.java.lang.foreign to a Java method) into a memory segment, so that Java code can effectively be passed to other foreign functions.
 java.base.share.classes.java.lang.foreign For instance, we can write a method that compares two integer values, as follows:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang=java :
 java.base.share.classes.java.lang.foreign class IntComparator {
 java.base.share.classes.java.lang.foreign     static int intCompare(MemorySegment addr1, MemorySegment addr2) {
 java.base.share.classes.java.lang.foreign         return addr1.get(ValueLayout.JAVA_INT, 0) -
 java.base.share.classes.java.lang.foreign                addr2.get(ValueLayout.JAVA_INT, 0);
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign     }
 java.base.share.classes.java.lang.foreign }
 java.base.share.classes.java.lang.foreign }
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign The above method accesses two foreign memory segments containing an integer value, and performs a simple comparison
 java.base.share.classes.java.lang.foreign by returning the difference between such values. We can then obtain a method handle which targets the above static
 java.base.share.classes.java.lang.foreign method, as follows:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang = java:
 java.base.share.classes.java.lang.foreign FunctionDescriptor intCompareDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT,
 java.base.share.classes.java.lang.foreign                                                                 ValueLayout.ADDRESS.asUnbounded(),
 java.base.share.classes.java.lang.foreign                                                                 ValueLayout.ADDRESS.asUnbounded());
 java.base.share.classes.java.lang.foreign MethodHandle intCompareHandle = MethodHandles.lookup().findStatic(IntComparator.class,
 java.base.share.classes.java.lang.foreign                                                 "intCompare",
 java.base.share.classes.java.lang.foreign                                                 intCompareDescriptor.toMethodType());
 java.base.share.classes.java.lang.foreign}
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign As before, we need to create a {@link java.lang.foreign.FunctionDescriptor} instance, this time describing the signature
 java.base.share.classes.java.lang.foreign of the function pointer we want to create. The descriptor can be used to
 java.base.share.classes.java.lang.foreign {@linkplain java.lang.foreign.FunctionDescriptor#toMethodType() derive} a method type
 java.base.share.classes.java.lang.foreign that can be used to look up the method handle for {@code IntComparator.intCompare}.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign Now that we have a method handle instance, we can turn it into a fresh function pointer,
 java.base.share.classes.java.lang.foreign using the {@link java.lang.foreign.Linker} interface, as follows:
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign {@snippet lang = java:
 java.base.share.classes.java.lang.foreign SegmentScope scope = ...
 java.base.share.classes.java.lang.foreign MemorySegment comparFunc = Linker.nativeLinker().upcallStub(
 java.base.share.classes.java.lang.foreign     intCompareHandle, intCompareDescriptor, scope);
 java.base.share.classes.java.lang.foreign );
 java.base.share.classes.java.lang.foreign}
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign The {@link java.lang.foreign.FunctionDescriptor} instance created in the previous step is then used to
 java.base.share.classes.java.lang.foreign {@linkplain java.lang.foreign.Linker#upcallStub(java.lang.invoke.MethodHandle, FunctionDescriptor, SegmentScope) create}
 java.base.share.classes.java.lang.foreign a new upcall stub; the layouts in the function descriptors allow the linker to determine the sequence of steps which
 java.base.share.classes.java.lang.foreign allow foreign code to call the stub for {@code intCompareHandle} according to the rules specified by the ABI of the
 java.base.share.classes.java.lang.foreign underlying platform.
 java.base.share.classes.java.lang.foreign The lifecycle of the upcall stub is tied to the {@linkplain java.lang.foreign.SegmentScope scope}
 java.base.share.classes.java.lang.foreign provided when the upcall stub is created. This same scope is made available by the {@link java.lang.foreign.MemorySegment}
 java.base.share.classes.java.lang.foreign instance returned by that method.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign <h2 id="restricted">Restricted methods</h2>
 java.base.share.classes.java.lang.foreign Some methods in this package are considered <em>restricted</em>. Restricted methods are typically used to bind native
 java.base.share.classes.java.lang.foreign foreign data and/or functions to first-class Java API elements which can then be used directly by clients. For instance
 java.base.share.classes.java.lang.foreign the restricted method {@link java.lang.foreign.MemorySegment#ofAddress(long, long, SegmentScope)}
 java.base.share.classes.java.lang.foreign can be used to create a fresh segment with the given spatial bounds out of a native address.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign Binding foreign data and/or functions is generally unsafe and, if done incorrectly, can result in VM crashes,
 java.base.share.classes.java.lang.foreign or memory corruption when the bound Java API element is accessed. For instance, in the case of
 java.base.share.classes.java.lang.foreign {@link java.lang.foreign.MemorySegment#ofAddress(long, long, SegmentScope)}, if the provided spatial bounds are
 java.base.share.classes.java.lang.foreign incorrect, a client of the segment returned by that method might crash the VM, or corrupt
 java.base.share.classes.java.lang.foreign memory when attempting to access said segment. For these reasons, it is crucial for code that calls a restricted method
 java.base.share.classes.java.lang.foreign to never pass arguments that might cause incorrect binding of foreign data and/or functions to a Java API.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign Given the potential danger of restricted methods, the Java runtime issues a warning on the standard error stream
 java.base.share.classes.java.lang.foreign every time a restricted method is invoked. Such warnings can be disabled by granting access to restricted methods
 java.base.share.classes.java.lang.foreign to selected modules. This can be done either via implementation-specific command line options, or programmatically, e.g. by calling
 java.base.share.classes.java.lang.foreign {@link java.lang.ModuleLayer.Controller#enableNativeAccess(java.lang.Module)}.
 java.base.share.classes.java.lang.foreign <p>
 java.base.share.classes.java.lang.foreign For every class in this package, unless specified otherwise, any method arguments of reference
 java.base.share.classes.java.lang.foreign type must not be null, and any null argument will elicit a {@code NullPointerException}.  This fact is not individually
 java.base.share.classes.java.lang.foreign documented for methods of this API.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign @apiNote Usual memory model guarantees, for example stated in {@jls 6.6} and {@jls 10.4}, do not apply
 java.base.share.classes.java.lang.foreign when accessing native memory segments as these segments are backed by off-heap regions of memory.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign @implNote
 java.base.share.classes.java.lang.foreign In the reference implementation, access to restricted methods can be granted to specific modules using the command line option
 java.base.share.classes.java.lang.foreign {@code --enable-native-access=M1,M2, ... Mn}, where {@code M1}, {@code M2}, {@code ... Mn} are module names
 java.base.share.classes.java.lang.foreign (for the unnamed module, the special value {@code ALL-UNNAMED} can be used). If this option is specified, access to
 java.base.share.classes.java.lang.foreign restricted methods is only granted to the modules listed by that option. If this option is not specified,
 java.base.share.classes.java.lang.foreign access to restricted methods is enabled for all modules, but access to restricted methods will result in runtime warnings.
 java.base.share.classes.java.lang.foreign
 java.base.share.classes.java.lang.foreign @since Pre Java 1
 java.base.share.classes.java.lang.foreign @author Logan Abernathy
 java.base.share.classes.java.lang.foreign @edited 24/4/2023
 java.base.share.classes.java.lang.foreign/
package java.lang.foreign;

