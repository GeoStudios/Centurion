/java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.util.stream/

/java.base.share.classes.java.util.streamjava.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Classes to support functional-style operations on streams of elements, such
 java.base.share.classes.java.util.stream as map-reduce transformations on collections.  For example:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     int sum = widgets.stream()
 java.base.share.classes.java.util.stream                      .filter(b -> b.getColor() == RED)
 java.base.share.classes.java.util.stream                      .mapToInt(b -> b.getWeight())
 java.base.share.classes.java.util.stream                      .sum();
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Here we use {@code widgets}, a {@code Collection<Widget>},
 java.base.share.classes.java.util.stream as a source for a stream, and then perform a filter-map-reduce on the stream
 java.base.share.classes.java.util.stream to obtain the sum of the weights of the red widgets.  (Summation is an
 java.base.share.classes.java.util.stream example of a <a href="package-summary.html#Reduction">reduction</a>
 java.base.share.classes.java.util.stream operation.)
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The key abstraction introduced in this package is <em>stream</em>.  The
 java.base.share.classes.java.util.stream classes {@link java.util.stream.Stream}, {@link java.util.stream.IntStream},
 java.base.share.classes.java.util.stream {@link java.util.stream.LongStream}, and {@link java.util.stream.DoubleStream}
 java.base.share.classes.java.util.stream are streams over objects and the primitive {@code int}, {@code long}, and
 java.base.share.classes.java.util.stream {@code double} types.  Streams differ from collections in several ways:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <ul>
 java.base.share.classes.java.util.stream     <li>No storage.  A stream is not a data structure that stores elements;
 java.base.share.classes.java.util.stream     instead, it conveys elements from a source such as a data structure,
 java.base.share.classes.java.util.stream     an array, a generator function, or an I/O channel, through a pipeline of
 java.base.share.classes.java.util.stream     computational operations.</li>
 java.base.share.classes.java.util.stream     <li>Functional in nature.  An operation on a stream produces a result,
 java.base.share.classes.java.util.stream     but does not modify its source.  For example, filtering a {@code Stream}
 java.base.share.classes.java.util.stream     obtained from a collection produces a new {@code Stream} without the
 java.base.share.classes.java.util.stream     filtered elements, rather than removing elements from the source
 java.base.share.classes.java.util.stream     collection.</li>
 java.base.share.classes.java.util.stream     <li>Laziness-seeking.  Many stream operations, such as filtering, mapping,
 java.base.share.classes.java.util.stream     or duplicate removal, can be implemented lazily, exposing opportunities
 java.base.share.classes.java.util.stream     for optimization.  For example, "find the first {@code String} with
 java.base.share.classes.java.util.stream     three consecutive vowels" need not examine all the input strings.
 java.base.share.classes.java.util.stream     Stream operations are divided into intermediate ({@code Stream}-producing)
 java.base.share.classes.java.util.stream     operations and terminal (value- or side-effect-producing) operations.
 java.base.share.classes.java.util.stream     Intermediate operations are always lazy.</li>
 java.base.share.classes.java.util.stream     <li>Possibly unbounded.  While collections have a finite size, streams
 java.base.share.classes.java.util.stream     need not.  Short-circuiting operations such as {@code limit(n)} or
 java.base.share.classes.java.util.stream     {@code findFirst()} can allow computations on infinite streams to
 java.base.share.classes.java.util.stream     complete in finite time.</li>
 java.base.share.classes.java.util.stream     <li>Consumable. The elements of a stream are only visited once during
 java.base.share.classes.java.util.stream     the life of a stream. Like an {@link java.util.Iterator}, a new stream
 java.base.share.classes.java.util.stream     must be generated to revisit the same elements of the source.
 java.base.share.classes.java.util.stream     </li>
 java.base.share.classes.java.util.stream </ul>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Streams can be obtained in a number of ways. Some examples include:
 java.base.share.classes.java.util.stream <ul>
 java.base.share.classes.java.util.stream     <li>From a {@link java.util.Collection} via the {@code stream()} and
 java.base.share.classes.java.util.stream     {@code parallelStream()} methods;</li>
 java.base.share.classes.java.util.stream     <li>From an array via {@link java.util.Arrays#stream(Object[])};</li>
 java.base.share.classes.java.util.stream     <li>From static factory methods on the stream classes, such as
 java.base.share.classes.java.util.stream     {@link java.util.stream.Stream#of(Object[])},
 java.base.share.classes.java.util.stream     {@link java.util.stream.IntStream#range(int, int)}
 java.base.share.classes.java.util.stream     or {@link java.util.stream.Stream#iterate(Object, UnaryOperator)};</li>
 java.base.share.classes.java.util.stream     <li>The lines of a file can be obtained from {@link java.io.BufferedReader#lines()};</li>
 java.base.share.classes.java.util.stream     <li>Streams of file paths can be obtained from methods in {@link java.nio.file.Files};</li>
 java.base.share.classes.java.util.stream     <li>Streams of random numbers can be obtained from {@link java.util.Random#ints()};</li>
 java.base.share.classes.java.util.stream     <li>Numerous other stream-bearing methods in the JDK, including
 java.base.share.classes.java.util.stream     {@link java.util.BitSet#stream()},
 java.base.share.classes.java.util.stream     {@link java.util.regex.Pattern#splitAsStream(java.lang.CharSequence)},
 java.base.share.classes.java.util.stream     and {@link java.util.jar.JarFile#stream()}.</li>
 java.base.share.classes.java.util.stream </ul>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Additional stream sources can be provided by third-party libraries using
 java.base.share.classes.java.util.stream <a href="package-summary.html#StreamSources">these techniques</a>.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h2><a id="StreamOps">Stream operations and pipelines</a></h2>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Stream operations are divided into <em>intermediate</em> and
 java.base.share.classes.java.util.stream <em>terminal</em> operations, and are combined to form <em>stream
 java.base.share.classes.java.util.stream pipelines</em>.  A stream pipeline consists of a source (such as a
 java.base.share.classes.java.util.stream {@code Collection}, an array, a generator function, or an I/O channel);
 java.base.share.classes.java.util.stream followed by zero or more intermediate operations such as
 java.base.share.classes.java.util.stream {@code Stream.filter} or {@code Stream.map}; and a terminal operation such
 java.base.share.classes.java.util.stream as {@code Stream.forEach} or {@code Stream.reduce}.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Intermediate operations return a new stream.  They are always
 java.base.share.classes.java.util.stream <em>lazy</em>; executing an intermediate operation such as
 java.base.share.classes.java.util.stream {@code filter()} does not actually perform any filtering, but instead
 java.base.share.classes.java.util.stream creates a new stream that, when traversed, contains the elements of
 java.base.share.classes.java.util.stream the initial stream that match the given predicate.  Traversal
 java.base.share.classes.java.util.stream of the pipeline source does not begin until the terminal operation of the
 java.base.share.classes.java.util.stream pipeline is executed.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Terminal operations, such as {@code Stream.forEach} or
 java.base.share.classes.java.util.stream {@code IntStream.sum}, may traverse the stream to produce a result or a
 java.base.share.classes.java.util.stream side-effect. After the terminal operation is performed, the stream pipeline
 java.base.share.classes.java.util.stream is considered consumed, and can no longer be used; if you need to traverse
 java.base.share.classes.java.util.stream the same data source again, you must return to the data source to get a new
 java.base.share.classes.java.util.stream stream.  In almost all cases, terminal operations are <em>eager</em>,
 java.base.share.classes.java.util.stream completing their traversal of the data source and processing of the pipeline
 java.base.share.classes.java.util.stream before returning.  Only the terminal operations {@code iterator()} and
 java.base.share.classes.java.util.stream {@code spliterator()} are not; these are provided as an "escape hatch" to enable
 java.base.share.classes.java.util.stream arbitrary client-controlled pipeline traversals in the event that the
 java.base.share.classes.java.util.stream existing operations are not sufficient to the task.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p> Processing streams lazily allows for significant efficiencies; in a
 java.base.share.classes.java.util.stream pipeline such as the filter-map-sum example above, filtering, mapping, and
 java.base.share.classes.java.util.stream summing can be fused into a single pass on the data, with minimal
 java.base.share.classes.java.util.stream intermediate state. Laziness also allows avoiding examining all the data
 java.base.share.classes.java.util.stream when it is not necessary; for operations such as "find the first string
 java.base.share.classes.java.util.stream longer than 1000 characters", it is only necessary to examine just enough
 java.base.share.classes.java.util.stream strings to find one that has the desired characteristics without examining
 java.base.share.classes.java.util.stream all of the strings available from the source. (This behavior becomes even
 java.base.share.classes.java.util.stream more important when the input stream is infinite and not merely large.)
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Intermediate operations are further divided into <em>stateless</em>
 java.base.share.classes.java.util.stream and <em>stateful</em> operations. Stateless operations, such as {@code filter}
 java.base.share.classes.java.util.stream and {@code map}, retain no state from previously seen element when processing
 java.base.share.classes.java.util.stream a new element -- each element can be processed
 java.base.share.classes.java.util.stream independently of operations on other elements.  Stateful operations, such as
 java.base.share.classes.java.util.stream {@code distinct} and {@code sorted}, may incorporate state from previously
 java.base.share.classes.java.util.stream seen elements when processing new elements.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Stateful operations may need to process the entire input
 java.base.share.classes.java.util.stream before producing a result.  For example, one cannot produce any results from
 java.base.share.classes.java.util.stream sorting a stream until one has seen all elements of the stream.  As a result,
 java.base.share.classes.java.util.stream under parallel computation, some pipelines containing stateful intermediate
 java.base.share.classes.java.util.stream operations may require multiple passes on the data or may need to buffer
 java.base.share.classes.java.util.stream significant data.  Pipelines containing exclusively stateless intermediate
 java.base.share.classes.java.util.stream operations can be processed in a single pass, whether sequential or parallel,
 java.base.share.classes.java.util.stream with minimal data buffering.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Further, some operations are deemed <em>short-circuiting</em> operations.
 java.base.share.classes.java.util.stream An intermediate operation is short-circuiting if, when presented with
 java.base.share.classes.java.util.stream infinite input, it may produce a finite stream as a result.  A terminal
 java.base.share.classes.java.util.stream operation is short-circuiting if, when presented with infinite input, it may
 java.base.share.classes.java.util.stream terminate in finite time.  Having a short-circuiting operation in the pipeline
 java.base.share.classes.java.util.stream is a necessary, but not sufficient, condition for the processing of an infinite
 java.base.share.classes.java.util.stream stream to terminate normally in finite time.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="Parallelism">Parallelism</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Processing elements with an explicit {@code for-}loop is inherently serial.
 java.base.share.classes.java.util.stream Streams facilitate parallel execution by reframing the computation as a pipeline of
 java.base.share.classes.java.util.stream aggregate operations, rather than as imperative operations on each individual
 java.base.share.classes.java.util.stream element.  All streams operations can execute either in serial or in parallel.
 java.base.share.classes.java.util.stream The stream implementations in the JDK create serial streams unless parallelism is
 java.base.share.classes.java.util.stream explicitly requested.  For example, {@code Collection} has methods
 java.base.share.classes.java.util.stream {@link java.util.Collection#stream} and {@link java.util.Collection#parallelStream},
 java.base.share.classes.java.util.stream which produce sequential and parallel streams respectively; other
 java.base.share.classes.java.util.stream stream-bearing methods such as {@link java.util.stream.IntStream#range(int, int)}
 java.base.share.classes.java.util.stream produce sequential streams but these streams can be efficiently parallelized by
 java.base.share.classes.java.util.stream invoking their {@link java.util.stream.BaseStream#parallel()} method.
 java.base.share.classes.java.util.stream To execute the prior "sum of weights of widgets" query in parallel, we would
 java.base.share.classes.java.util.stream do:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     int sumOfWeights = widgets.parallelStream()
 java.base.share.classes.java.util.stream                               .filter(b -> b.getColor() == RED)
 java.base.share.classes.java.util.stream                               .mapToInt(b -> b.getWeight())
 java.base.share.classes.java.util.stream                               .sum();
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The only difference between the serial and parallel versions of this
 java.base.share.classes.java.util.stream example is the creation of the initial stream, using "{@code parallelStream()}"
 java.base.share.classes.java.util.stream instead of "{@code stream()}". The stream pipeline is executed sequentially or
 java.base.share.classes.java.util.stream in parallel depending on the mode of the stream on which the terminal operation
 java.base.share.classes.java.util.stream is invoked. The sequential or parallel mode of a stream can be determined with the
 java.base.share.classes.java.util.stream {@link java.util.stream.BaseStream#isParallel()} method, and the
 java.base.share.classes.java.util.stream stream's mode can be modified with the
 java.base.share.classes.java.util.stream {@link java.util.stream.BaseStream#sequential()} and
 java.base.share.classes.java.util.stream {@link java.util.stream.BaseStream#parallel()} operations.
 java.base.share.classes.java.util.stream The most recent sequential or parallel mode setting applies to the
 java.base.share.classes.java.util.stream execution of the entire stream pipeline.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Except for operations identified as explicitly nondeterministic, such
 java.base.share.classes.java.util.stream as {@code findAny()}, whether a stream executes sequentially or in parallel
 java.base.share.classes.java.util.stream should not change the result of the computation.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Most stream operations accept parameters that describe user-specified
 java.base.share.classes.java.util.stream behavior, which are often lambda expressions.  To preserve correct behavior,
 java.base.share.classes.java.util.stream these <em>behavioral parameters</em> must be <em>non-interfering</em>, and in
 java.base.share.classes.java.util.stream most cases must be <em>stateless</em>.  Such parameters are always instances
 java.base.share.classes.java.util.stream of a <a href="../function/package-summary.html">functional interface</a> such
 java.base.share.classes.java.util.stream as {@link java.util.function.Function}, and are often lambda expressions or
 java.base.share.classes.java.util.stream method references.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="NonInterference">Non-interference</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Streams enable you to execute possibly-parallel aggregate operations over a
 java.base.share.classes.java.util.stream variety of data sources, including even non-thread-safe collections such as
 java.base.share.classes.java.util.stream {@code ArrayList}. This is possible only if we can prevent
 java.base.share.classes.java.util.stream <em>interference</em> with the data source during the execution of a stream
 java.base.share.classes.java.util.stream pipeline.  Except for the escape-hatch operations {@code iterator()} and
 java.base.share.classes.java.util.stream {@code spliterator()}, execution begins when the terminal operation is
 java.base.share.classes.java.util.stream invoked, and ends when the terminal operation completes.  For most data
 java.base.share.classes.java.util.stream sources, preventing interference means ensuring that the data source is
 java.base.share.classes.java.util.stream <em>not modified at all</em> during the execution of the stream pipeline.
 java.base.share.classes.java.util.stream The notable exception to this are streams whose sources are concurrent
 java.base.share.classes.java.util.stream collections, which are specifically designed to handle concurrent modification.
 java.base.share.classes.java.util.stream Concurrent stream sources are those whose {@code Spliterator} reports the
 java.base.share.classes.java.util.stream {@code CONCURRENT} characteristic.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Accordingly, behavioral parameters in stream pipelines whose source might
 java.base.share.classes.java.util.stream not be concurrent should never modify the stream's data source.
 java.base.share.classes.java.util.stream A behavioral parameter is said to <em>interfere</em> with a non-concurrent
 java.base.share.classes.java.util.stream data source if it modifies, or causes to be
 java.base.share.classes.java.util.stream modified, the stream's data source.  The need for non-interference applies
 java.base.share.classes.java.util.stream to all pipelines, not just parallel ones.  Unless the stream source is
 java.base.share.classes.java.util.stream concurrent, modifying a stream's data source during execution of a stream
 java.base.share.classes.java.util.stream pipeline can cause exceptions, incorrect answers, or nonconformant behavior.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream For well-behaved stream sources, the source can be modified before the
 java.base.share.classes.java.util.stream terminal operation commences and those modifications will be reflected in
 java.base.share.classes.java.util.stream the covered elements.  For example, consider the following code:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     List<String> l = new ArrayList(Arrays.asList("one", "two"));
 java.base.share.classes.java.util.stream     Stream<String> sl = l.stream();
 java.base.share.classes.java.util.stream     l.add("three");
 java.base.share.classes.java.util.stream     String s = sl.collect(joining(" "));
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream First a list is created consisting of two strings: "one" and "two". Then a
 java.base.share.classes.java.util.stream stream is created from that list. Next the list is modified by adding a third
 java.base.share.classes.java.util.stream string: "three". Finally the elements of the stream are collected and joined
 java.base.share.classes.java.util.stream together. Since the list was modified before the terminal {@code collect}
 java.base.share.classes.java.util.stream operation commenced the result will be a string of "one two three". All the
 java.base.share.classes.java.util.stream streams returned from JDK collections, and most other JDK classes,
 java.base.share.classes.java.util.stream are well-behaved in this manner; for streams generated by other libraries, see
 java.base.share.classes.java.util.stream <a href="package-summary.html#StreamSources">Low-level stream
 java.base.share.classes.java.util.stream construction</a> for requirements for building well-behaved streams.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="Statelessness">Stateless behaviors</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Stream pipeline results may be nondeterministic or incorrect if the behavioral
 java.base.share.classes.java.util.stream parameters to the stream operations are <em>stateful</em>.  A stateful lambda
 java.base.share.classes.java.util.stream (or other object implementing the appropriate functional interface) is one
 java.base.share.classes.java.util.stream whose result depends on any state which might change during the execution
 java.base.share.classes.java.util.stream of the stream pipeline.  An example of a stateful lambda is the parameter
 java.base.share.classes.java.util.stream to {@code map()} in:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     Set<Integer> seen = Collections.synchronizedSet(new HashSet<>());
 java.base.share.classes.java.util.stream     stream.parallel().map(e -> { if (seen.add(e)) return 0; else return e; })...
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Here, if the mapping operation is performed in parallel, the results for the
 java.base.share.classes.java.util.stream same input could vary from run to run, due to thread scheduling differences,
 java.base.share.classes.java.util.stream whereas, with a stateless lambda expression the results would always be the
 java.base.share.classes.java.util.stream same.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Note also that attempting to access mutable state from behavioral parameters
 java.base.share.classes.java.util.stream presents you with a bad choice with respect to safety and performance; if
 java.base.share.classes.java.util.stream you do not synchronize access to that state, you have a data race and
 java.base.share.classes.java.util.stream therefore your code is broken, but if you do synchronize access to that
 java.base.share.classes.java.util.stream state, you risk having contention undermine the parallelism you are seeking
 java.base.share.classes.java.util.stream to benefit from.  The best approach is to avoid stateful behavioral
 java.base.share.classes.java.util.stream parameters to stream operations entirely; there is usually a way to
 java.base.share.classes.java.util.stream restructure the stream pipeline to avoid statefulness.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="SideEffects">Side-effects</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream Side-effects in behavioral parameters to stream operations are, in general,
 java.base.share.classes.java.util.stream discouraged, as they can often lead to unwitting violations of the
 java.base.share.classes.java.util.stream statelessness requirement, as well as other thread-safety hazards.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>If the behavioral parameters do have side-effects, unless explicitly
 java.base.share.classes.java.util.stream stated, there are no guarantees as to:
 java.base.share.classes.java.util.stream <ul>
 java.base.share.classes.java.util.stream    <li>the <a href="../concurrent/package-summary.html#MemoryVisibility">
 java.base.share.classes.java.util.stream    <i>visibility</i></a> of those side-effects to other threads;</li>
 java.base.share.classes.java.util.stream    <li>that different operations on the "same" element within the same stream
 java.base.share.classes.java.util.stream    pipeline are executed in the same thread; and</li>
 java.base.share.classes.java.util.stream    <li>that behavioral parameters are always invoked, since a stream
 java.base.share.classes.java.util.stream    implementation is free to elide operations (or entire stages) from a
 java.base.share.classes.java.util.stream    stream pipeline if it can prove that it would not affect the result of the
 java.base.share.classes.java.util.stream    computation.
 java.base.share.classes.java.util.stream    </li>
 java.base.share.classes.java.util.stream </ul>
 java.base.share.classes.java.util.stream <p>The ordering of side-effects may be surprising.  Even when a pipeline is
 java.base.share.classes.java.util.stream constrained to produce a <em>result</em> that is consistent with the
 java.base.share.classes.java.util.stream encounter order of the stream source (for example,
 java.base.share.classes.java.util.stream {@code IntStream.range(0,5).parallel().map(x -> xjava.base.share.classes.java.util.stream2).toArray()}
 java.base.share.classes.java.util.stream must produce {@code [0, 2, 4, 6, 8]}), no guarantees are made as to the order
 java.base.share.classes.java.util.stream in which the mapper function is applied to individual elements, or in what
 java.base.share.classes.java.util.stream thread any behavioral parameter is executed for a given element.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The eliding of side-effects may also be surprising.  With the exception of
 java.base.share.classes.java.util.stream terminal operations {@link java.util.stream.Stream#forEach forEach} and
 java.base.share.classes.java.util.stream {@link java.util.stream.Stream#forEachOrdered forEachOrdered}, side-effects
 java.base.share.classes.java.util.stream of behavioral parameters may not always be executed when the stream
 java.base.share.classes.java.util.stream implementation can optimize away the execution of behavioral parameters
 java.base.share.classes.java.util.stream without affecting the result of the computation.  (For a specific example
 java.base.share.classes.java.util.stream see the API note documented on the {@link java.util.stream.Stream#count count}
 java.base.share.classes.java.util.stream operation.)
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Many computations where one might be tempted to use side-effects can be more
 java.base.share.classes.java.util.stream safely and efficiently expressed without side-effects, such as using
 java.base.share.classes.java.util.stream <a href="package-summary.html#Reduction">reduction</a> instead of mutable
 java.base.share.classes.java.util.stream accumulators. However, side-effects such as using {@code println()} for debugging
 java.base.share.classes.java.util.stream purposes are usually harmless.  A small number of stream operations, such as
 java.base.share.classes.java.util.stream {@code forEach()} and {@code peek()}, can operate only via side-effects;
 java.base.share.classes.java.util.stream these should be used with care.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>As an example of how to transform a stream pipeline that inappropriately
 java.base.share.classes.java.util.stream uses side-effects to one that does not, the following code searches a stream
 java.base.share.classes.java.util.stream of strings for those matching a given regular expression, and puts the
 java.base.share.classes.java.util.stream matches in a list.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     ArrayList<String> results = new ArrayList<>();
 java.base.share.classes.java.util.stream     stream.filter(s -> pattern.matcher(s).matches())
 java.base.share.classes.java.util.stream           .forEach(s -> results.add(s));  // Unnecessary use of side-effects!
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream This code unnecessarily uses side-effects.  If executed in parallel, the
 java.base.share.classes.java.util.stream non-thread-safety of {@code ArrayList} would cause incorrect results, and
 java.base.share.classes.java.util.stream adding needed synchronization would cause contention, undermining the
 java.base.share.classes.java.util.stream benefit of parallelism.  Furthermore, using side-effects here is completely
 java.base.share.classes.java.util.stream unnecessary; the {@code forEach()} can simply be replaced with a reduction
 java.base.share.classes.java.util.stream operation that is safer, more efficient, and more amenable to
 java.base.share.classes.java.util.stream parallelization:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     List<String> results =
 java.base.share.classes.java.util.stream         stream.filter(s -> pattern.matcher(s).matches())
 java.base.share.classes.java.util.stream               .toList();  // No side-effects!
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="Ordering">Ordering</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Streams may or may not have a defined <em>encounter order</em>.  Whether
 java.base.share.classes.java.util.stream or not a stream has an encounter order depends on the source and the
 java.base.share.classes.java.util.stream intermediate operations.  Certain stream sources (such as {@code List} or
 java.base.share.classes.java.util.stream arrays) are intrinsically ordered, whereas others (such as {@code HashSet})
 java.base.share.classes.java.util.stream are not.  Some intermediate operations, such as {@code sorted()}, may impose
 java.base.share.classes.java.util.stream an encounter order on an otherwise unordered stream, and others may render an
 java.base.share.classes.java.util.stream ordered stream unordered, such as {@link java.util.stream.BaseStream#unordered()}.
 java.base.share.classes.java.util.stream Further, some terminal operations may ignore encounter order, such as
 java.base.share.classes.java.util.stream {@code forEach()}.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>If a stream is ordered, most operations are constrained to operate on the
 java.base.share.classes.java.util.stream elements in their encounter order; if the source of a stream is a {@code List}
 java.base.share.classes.java.util.stream containing {@code [1, 2, 3]}, then the result of executing {@code map(x -> xjava.base.share.classes.java.util.stream2)}
 java.base.share.classes.java.util.stream must be {@code [2, 4, 6]}.  However, if the source has no defined encounter
 java.base.share.classes.java.util.stream order, then any permutation of the values {@code [2, 4, 6]} would be a valid
 java.base.share.classes.java.util.stream result.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>For sequential streams, the presence or absence of an encounter order does
 java.base.share.classes.java.util.stream not affect performance, only determinism.  If a stream is ordered, repeated
 java.base.share.classes.java.util.stream execution of identical stream pipelines on an identical source will produce
 java.base.share.classes.java.util.stream an identical result; if it is not ordered, repeated execution might produce
 java.base.share.classes.java.util.stream different results.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>For parallel streams, relaxing the ordering constraint can sometimes enable
 java.base.share.classes.java.util.stream more efficient execution.  Certain aggregate operations,
 java.base.share.classes.java.util.stream such as filtering duplicates ({@code distinct()}) or grouped reductions
 java.base.share.classes.java.util.stream ({@code Collectors.groupingBy()}) can be implemented more efficiently if ordering of elements
 java.base.share.classes.java.util.stream is not relevant.  Similarly, operations that are intrinsically tied to encounter order,
 java.base.share.classes.java.util.stream such as {@code limit()}, may require
 java.base.share.classes.java.util.stream buffering to ensure proper ordering, undermining the benefit of parallelism.
 java.base.share.classes.java.util.stream In cases where the stream has an encounter order, but the user does not
 java.base.share.classes.java.util.stream particularly <em>care</em> about that encounter order, explicitly de-ordering
 java.base.share.classes.java.util.stream the stream with {@link java.util.stream.BaseStream#unordered() unordered()} may
 java.base.share.classes.java.util.stream improve parallel performance for some stateful or terminal operations.
 java.base.share.classes.java.util.stream However, most stream pipelines, such as the "sum of weight of blocks" example
 java.base.share.classes.java.util.stream above, still parallelize efficiently even under ordering constraints.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h2><a id="Reduction">Reduction operations</a></h2>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream A <em>reduction</em> operation (also called a <em>fold</em>) takes a sequence
 java.base.share.classes.java.util.stream of input elements and combines them into a single summary result by repeated
 java.base.share.classes.java.util.stream application of a combining operation, such as finding the sum or maximum of
 java.base.share.classes.java.util.stream a set of numbers, or accumulating elements into a list.  The streams classes have
 java.base.share.classes.java.util.stream multiple forms of general reduction operations, called
 java.base.share.classes.java.util.stream {@link java.util.stream.Stream#reduce(java.util.function.BinaryOperator) reduce()}
 java.base.share.classes.java.util.stream and {@link java.util.stream.Stream#collect(java.util.stream.Collector) collect()},
 java.base.share.classes.java.util.stream as well as multiple specialized reduction forms such as
 java.base.share.classes.java.util.stream {@link java.util.stream.IntStream#sum() sum()}, {@link java.util.stream.IntStream#max() max()},
 java.base.share.classes.java.util.stream or {@link java.util.stream.IntStream#count() count()}.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Of course, such operations can be readily implemented as simple sequential
 java.base.share.classes.java.util.stream loops, as in:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream    int sum = 0;
 java.base.share.classes.java.util.stream    for (int x : numbers) {
 java.base.share.classes.java.util.stream       sum += x;
 java.base.share.classes.java.util.stream    }
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream However, there are good reasons to prefer a reduce operation
 java.base.share.classes.java.util.stream over a mutative accumulation such as the above.  Not only is a reduction
 java.base.share.classes.java.util.stream "more abstract" -- it operates on the stream as a whole rather than individual
 java.base.share.classes.java.util.stream elements -- but a properly constructed reduce operation is inherently
 java.base.share.classes.java.util.stream parallelizable, so long as the function(s) used to process the elements
 java.base.share.classes.java.util.stream are <a href="package-summary.html#Associativity">associative</a> and
 java.base.share.classes.java.util.stream <a href="package-summary.html#Statelessness">stateless</a>.
 java.base.share.classes.java.util.stream For example, given a stream of numbers for which we want to find the sum, we
 java.base.share.classes.java.util.stream can write:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream    int sum = numbers.stream().reduce(0, (x,y) -> x+y);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream or:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream    int sum = numbers.stream().reduce(0, Integer::sum);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>These reduction operations can run safely in parallel with almost no
 java.base.share.classes.java.util.stream modification:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream    int sum = numbers.parallelStream().reduce(0, Integer::sum);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Reduction parallellizes well because the implementation
 java.base.share.classes.java.util.stream can operate on subsets of the data in parallel, and then combine the
 java.base.share.classes.java.util.stream intermediate results to get the final correct answer.  (Even if the language
 java.base.share.classes.java.util.stream had a "parallel for-each" construct, the mutative accumulation approach would
 java.base.share.classes.java.util.stream still require the developer to provide
 java.base.share.classes.java.util.stream thread-safe updates to the shared accumulating variable {@code sum}, and
 java.base.share.classes.java.util.stream the required synchronization would then likely eliminate any performance gain from
 java.base.share.classes.java.util.stream parallelism.)  Using {@code reduce()} instead removes all of the
 java.base.share.classes.java.util.stream burden of parallelizing the reduction operation, and the library can provide
 java.base.share.classes.java.util.stream an efficient parallel implementation with no additional synchronization
 java.base.share.classes.java.util.stream required.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The "widgets" examples shown earlier shows how reduction combines with
 java.base.share.classes.java.util.stream other operations to replace for-loops with bulk operations.  If {@code widgets}
 java.base.share.classes.java.util.stream is a collection of {@code Widget} objects, which have a {@code getWeight} method,
 java.base.share.classes.java.util.stream we can find the heaviest widget with:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     OptionalInt heaviest = widgets.parallelStream()
 java.base.share.classes.java.util.stream                                   .mapToInt(Widget::getWeight)
 java.base.share.classes.java.util.stream                                   .max();
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>In its more general form, a {@code reduce} operation on elements of type
 java.base.share.classes.java.util.stream {@code <T>} yielding a result of type {@code <U>} requires three parameters:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream <U> U reduce(U identity,
 java.base.share.classes.java.util.stream              BiFunction<U, ? super T, U> accumulator,
 java.base.share.classes.java.util.stream              BinaryOperator<U> combiner);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream Here, the <em>identity</em> element is both an initial seed value for the reduction
 java.base.share.classes.java.util.stream and a default result if there are no input elements. The <em>accumulator</em>
 java.base.share.classes.java.util.stream function takes a partial result and the next element, and produces a new
 java.base.share.classes.java.util.stream partial result. The <em>combiner</em> function combines two partial results
 java.base.share.classes.java.util.stream to produce a new partial result.  (The combiner is necessary in parallel
 java.base.share.classes.java.util.stream reductions, where the input is partitioned, a partial accumulation computed
 java.base.share.classes.java.util.stream for each partition, and then the partial results are combined to produce a
 java.base.share.classes.java.util.stream final result.)
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>More formally, the {@code identity} value must be an <em>identity</em> for
 java.base.share.classes.java.util.stream the combiner function. This means that for all {@code u},
 java.base.share.classes.java.util.stream {@code combiner.apply(identity, u)} is equal to {@code u}. Additionally, the
 java.base.share.classes.java.util.stream {@code combiner} function must be <a href="package-summary.html#Associativity">associative</a> and
 java.base.share.classes.java.util.stream must be compatible with the {@code accumulator} function: for all {@code u}
 java.base.share.classes.java.util.stream and {@code t}, {@code combiner.apply(u, accumulator.apply(identity, t))} must
 java.base.share.classes.java.util.stream be {@code equals()} to {@code accumulator.apply(u, t)}.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The three-argument form is a generalization of the two-argument form,
 java.base.share.classes.java.util.stream incorporating a mapping step into the accumulation step.  We could
 java.base.share.classes.java.util.stream re-cast the simple sum-of-weights example using the more general form as
 java.base.share.classes.java.util.stream follows:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     int sumOfWeights = widgets.stream()
 java.base.share.classes.java.util.stream                               .reduce(0,
 java.base.share.classes.java.util.stream                                       (sum, b) -> sum + b.getWeight(),
 java.base.share.classes.java.util.stream                                       Integer::sum);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream though the explicit map-reduce form is more readable and therefore should
 java.base.share.classes.java.util.stream usually be preferred. The generalized form is provided for cases where
 java.base.share.classes.java.util.stream significant work can be optimized away by combining mapping and reducing
 java.base.share.classes.java.util.stream into a single function.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="MutableReduction">Mutable reduction</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream A <em>mutable reduction operation</em> accumulates input elements into a
 java.base.share.classes.java.util.stream mutable result container, such as a {@code Collection} or {@code StringBuilder},
 java.base.share.classes.java.util.stream as it processes the elements in the stream.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>If we wanted to take a stream of strings and concatenate them into a
 java.base.share.classes.java.util.stream single long string, we <em>could</em> achieve this with ordinary reduction:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     String concatenated = strings.reduce("", String::concat)
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>We would get the desired result, and it would even work in parallel.  However,
 java.base.share.classes.java.util.stream we might not be happy about the performance!  Such an implementation would do
 java.base.share.classes.java.util.stream a great deal of string copying, and the run time would be <em>O(n^2)</em> in
 java.base.share.classes.java.util.stream the number of characters.  A more performant approach would be to accumulate
 java.base.share.classes.java.util.stream the results into a {@link java.lang.StringBuilder}, which is a mutable
 java.base.share.classes.java.util.stream container for accumulating strings.  We can use the same technique to
 java.base.share.classes.java.util.stream parallelize mutable reduction as we do with ordinary reduction.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The mutable reduction operation is called
 java.base.share.classes.java.util.stream {@link java.util.stream.Stream#collect(Collector) collect()},
 java.base.share.classes.java.util.stream as it collects together the desired results into a result container such
 java.base.share.classes.java.util.stream as a {@code Collection}.
 java.base.share.classes.java.util.stream A {@code collect} operation requires three functions:
 java.base.share.classes.java.util.stream a supplier function to construct new instances of the result container, an
 java.base.share.classes.java.util.stream accumulator function to incorporate an input element into a result
 java.base.share.classes.java.util.stream container, and a combining function to merge the contents of one result
 java.base.share.classes.java.util.stream container into another.  The form of this is very similar to the general
 java.base.share.classes.java.util.stream form of ordinary reduction:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream <R> R collect(Supplier<R> supplier,
 java.base.share.classes.java.util.stream               BiConsumer<R, ? super T> accumulator,
 java.base.share.classes.java.util.stream               BiConsumer<R, R> combiner);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream <p>As with {@code reduce()}, a benefit of expressing {@code collect} in this
 java.base.share.classes.java.util.stream abstract way is that it is directly amenable to parallelization: we can
 java.base.share.classes.java.util.stream accumulate partial results in parallel and then combine them, so long as the
 java.base.share.classes.java.util.stream accumulation and combining functions satisfy the appropriate requirements.
 java.base.share.classes.java.util.stream For example, to collect the String representations of the elements in a
 java.base.share.classes.java.util.stream stream into an {@code ArrayList}, we could write the obvious sequential
 java.base.share.classes.java.util.stream for-each form:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     ArrayList<String> strings = new ArrayList<>();
 java.base.share.classes.java.util.stream     for (T element : stream) {
 java.base.share.classes.java.util.stream         strings.add(element.toString());
 java.base.share.classes.java.util.stream     }
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream Or we could use a parallelizable collect form:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     ArrayList<String> strings = stream.collect(() -> new ArrayList<>(),
 java.base.share.classes.java.util.stream                                                (c, e) -> c.add(e.toString()),
 java.base.share.classes.java.util.stream                                                (c1, c2) -> c1.addAll(c2));
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream or, pulling the mapping operation out of the accumulator function, we could
 java.base.share.classes.java.util.stream express it more succinctly as:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     List<String> strings = stream.map(Object::toString)
 java.base.share.classes.java.util.stream                                  .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream Here, our supplier is just the {@link java.util.ArrayList#ArrayList()
 java.base.share.classes.java.util.stream ArrayList constructor}, the accumulator adds the stringified element to an
 java.base.share.classes.java.util.stream {@code ArrayList}, and the combiner simply uses {@link java.util.ArrayList#addAll addAll}
 java.base.share.classes.java.util.stream to copy the strings from one container into the other.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The three aspects of {@code collect} -- supplier, accumulator, and
 java.base.share.classes.java.util.stream combiner -- are tightly coupled.  We can use the abstraction of a
 java.base.share.classes.java.util.stream {@link java.util.stream.Collector} to capture all three aspects.  The
 java.base.share.classes.java.util.stream above example for collecting strings into a {@code List} can be rewritten
 java.base.share.classes.java.util.stream using a standard {@code Collector} as:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     List<String> strings = stream.map(Object::toString)
 java.base.share.classes.java.util.stream                                  .collect(Collectors.toList());
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Packaging mutable reductions into a Collector has another advantage:
 java.base.share.classes.java.util.stream composability.  The class {@link java.util.stream.Collectors} contains a
 java.base.share.classes.java.util.stream number of predefined factories for collectors, including combinators
 java.base.share.classes.java.util.stream that transform one collector into another.  For example, suppose we have a
 java.base.share.classes.java.util.stream collector that computes the sum of the salaries of a stream of
 java.base.share.classes.java.util.stream employees, as follows:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     Collector<Employee, ?, Integer> summingSalaries
 java.base.share.classes.java.util.stream         = Collectors.summingInt(Employee::getSalary);
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream (The {@code ?} for the second type parameter merely indicates that we don't
 java.base.share.classes.java.util.stream care about the intermediate representation used by this collector.)
 java.base.share.classes.java.util.stream If we wanted to create a collector to tabulate the sum of salaries by
 java.base.share.classes.java.util.stream department, we could reuse {@code summingSalaries} using
 java.base.share.classes.java.util.stream {@link java.util.stream.Collectors#groupingBy(java.util.function.Function, java.util.stream.Collector) groupingBy}:
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     Map<Department, Integer> salariesByDept
 java.base.share.classes.java.util.stream         = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
 java.base.share.classes.java.util.stream                                                            summingSalaries));
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>As with the regular reduction operation, {@code collect()} operations can
 java.base.share.classes.java.util.stream only be parallelized if appropriate conditions are met.  For any partially
 java.base.share.classes.java.util.stream accumulated result, combining it with an empty result container must
 java.base.share.classes.java.util.stream produce an equivalent result.  That is, for a partially accumulated result
 java.base.share.classes.java.util.stream {@code p} that is the result of any series of accumulator and combiner
 java.base.share.classes.java.util.stream invocations, {@code p} must be equivalent to
 java.base.share.classes.java.util.stream {@code combiner.apply(p, supplier.get())}.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Further, however the computation is split, it must produce an equivalent
 java.base.share.classes.java.util.stream result.  For any input elements {@code t1} and {@code t2}, the results
 java.base.share.classes.java.util.stream {@code r1} and {@code r2} in the computation below must be equivalent:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     A a1 = supplier.get();
 java.base.share.classes.java.util.stream     accumulator.accept(a1, t1);
 java.base.share.classes.java.util.stream     accumulator.accept(a1, t2);
 java.base.share.classes.java.util.stream     R r1 = finisher.apply(a1);  // result without splitting
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream     A a2 = supplier.get();
 java.base.share.classes.java.util.stream     accumulator.accept(a2, t1);
 java.base.share.classes.java.util.stream     A a3 = supplier.get();
 java.base.share.classes.java.util.stream     accumulator.accept(a3, t2);
 java.base.share.classes.java.util.stream     R r2 = finisher.apply(combiner.apply(a2, a3));  // result with splitting
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Here, equivalence generally means according to {@link java.lang.Object#equals(Object)}.
 java.base.share.classes.java.util.stream but in some cases equivalence may be relaxed to account for differences in
 java.base.share.classes.java.util.stream order.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="ConcurrentReduction">Reduction, concurrency, and ordering</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream With some complex reduction operations, for example a {@code collect()} that
 java.base.share.classes.java.util.stream produces a {@code Map}, such as:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     Map<Buyer, List<Transaction>> salesByBuyer
 java.base.share.classes.java.util.stream         = txns.parallelStream()
 java.base.share.classes.java.util.stream               .collect(Collectors.groupingBy(Transaction::getBuyer));
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream it may actually be counterproductive to perform the operation in parallel.
 java.base.share.classes.java.util.stream This is because the combining step (merging one {@code Map} into another by
 java.base.share.classes.java.util.stream key) can be expensive for some {@code Map} implementations.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Suppose, however, that the result container used in this reduction
 java.base.share.classes.java.util.stream was a concurrently modifiable collection -- such as a
 java.base.share.classes.java.util.stream {@link java.util.concurrent.ConcurrentHashMap}. In that case, the parallel
 java.base.share.classes.java.util.stream invocations of the accumulator could actually deposit their results
 java.base.share.classes.java.util.stream concurrently into the same shared result container, eliminating the need for
 java.base.share.classes.java.util.stream the combiner to merge distinct result containers. This potentially provides
 java.base.share.classes.java.util.stream a boost to the parallel execution performance. We call this a
 java.base.share.classes.java.util.stream <em>concurrent</em> reduction.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>A {@link java.util.stream.Collector} that supports concurrent reduction is
 java.base.share.classes.java.util.stream marked with the {@link java.util.stream.Collector.Characteristics#CONCURRENT}
 java.base.share.classes.java.util.stream characteristic.  However, a concurrent collection also has a downside.  If
 java.base.share.classes.java.util.stream multiple threads are depositing results concurrently into a shared container,
 java.base.share.classes.java.util.stream the order in which results are deposited is non-deterministic. Consequently,
 java.base.share.classes.java.util.stream a concurrent reduction is only possible if ordering is not important for the
 java.base.share.classes.java.util.stream stream being processed. The {@link java.util.stream.Stream#collect(Collector)}
 java.base.share.classes.java.util.stream implementation will only perform a concurrent reduction if
 java.base.share.classes.java.util.stream <ul>
 java.base.share.classes.java.util.stream <li>The stream is parallel;</li>
 java.base.share.classes.java.util.stream <li>The collector has the
 java.base.share.classes.java.util.stream {@link java.util.stream.Collector.Characteristics#CONCURRENT} characteristic,
 java.base.share.classes.java.util.stream and;</li>
 java.base.share.classes.java.util.stream <li>Either the stream is unordered, or the collector has the
 java.base.share.classes.java.util.stream {@link java.util.stream.Collector.Characteristics#UNORDERED} characteristic.
 java.base.share.classes.java.util.stream </ul>
 java.base.share.classes.java.util.stream You can ensure the stream is unordered by using the
 java.base.share.classes.java.util.stream {@link java.util.stream.BaseStream#unordered()} method.  For example:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     Map<Buyer, List<Transaction>> salesByBuyer
 java.base.share.classes.java.util.stream         = txns.parallelStream()
 java.base.share.classes.java.util.stream               .unordered()
 java.base.share.classes.java.util.stream               .collect(groupingByConcurrent(Transaction::getBuyer));
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream (where {@link java.util.stream.Collectors#groupingByConcurrent} is the
 java.base.share.classes.java.util.stream concurrent equivalent of {@code groupingBy}).
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Note that if it is important that the elements for a given key appear in
 java.base.share.classes.java.util.stream the order they appear in the source, then we cannot use a concurrent
 java.base.share.classes.java.util.stream reduction, as ordering is one of the casualties of concurrent insertion.
 java.base.share.classes.java.util.stream We would then be constrained to implement either a sequential reduction or
 java.base.share.classes.java.util.stream a merge-based parallel reduction.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h3><a id="Associativity">Associativity</a></h3>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream An operator or function {@code op} is <em>associative</em> if the following
 java.base.share.classes.java.util.stream holds:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     (a op b) op c == a op (b op c)
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream The importance of this to parallel evaluation can be seen if we expand this
 java.base.share.classes.java.util.stream to four terms:
 java.base.share.classes.java.util.stream <pre>{@code
 java.base.share.classes.java.util.stream     a op b op c op d == (a op b) op (c op d)
 java.base.share.classes.java.util.stream }</pre>
 java.base.share.classes.java.util.stream So we can evaluate {@code (a op b)} in parallel with {@code (c op d)}, and
 java.base.share.classes.java.util.stream then invoke {@code op} on the results.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Examples of associative operations include numeric addition, min, and
 java.base.share.classes.java.util.stream max, and string concatenation.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <h2><a id="StreamSources">Low-level stream construction</a></h2>
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream So far, all the stream examples have used methods like
 java.base.share.classes.java.util.stream {@link java.util.Collection#stream()} or {@link java.util.Arrays#stream(Object[])}
 java.base.share.classes.java.util.stream to obtain a stream.  How are those stream-bearing methods implemented?
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>The class {@link java.util.stream.StreamSupport} has a number of
 java.base.share.classes.java.util.stream low-level methods for creating a stream, all using some form of a
 java.base.share.classes.java.util.stream {@link java.util.Spliterator}. A spliterator is the parallel analogue of an
 java.base.share.classes.java.util.stream {@link java.util.Iterator}; it describes a (possibly infinite) collection of
 java.base.share.classes.java.util.stream elements, with support for sequentially advancing, bulk traversal, and
 java.base.share.classes.java.util.stream splitting off some portion of the input into another spliterator which can
 java.base.share.classes.java.util.stream be processed in parallel.  At the lowest level, all streams are driven by a
 java.base.share.classes.java.util.stream spliterator.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>There are a number of implementation choices in implementing a
 java.base.share.classes.java.util.stream spliterator, nearly all of which are tradeoffs between simplicity of
 java.base.share.classes.java.util.stream implementation and runtime performance of streams using that spliterator.
 java.base.share.classes.java.util.stream The simplest, but least performant, way to create a spliterator is to
 java.base.share.classes.java.util.stream create one from an iterator using
 java.base.share.classes.java.util.stream {@link java.util.Spliterators#spliteratorUnknownSize(java.util.Iterator, int)}.
 java.base.share.classes.java.util.stream While such a spliterator will work, it will likely offer poor parallel
 java.base.share.classes.java.util.stream performance, since we have lost sizing information (how big is the
 java.base.share.classes.java.util.stream underlying data set), as well as being constrained to a simplistic
 java.base.share.classes.java.util.stream splitting algorithm.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>A higher-quality spliterator will provide balanced and known-size
 java.base.share.classes.java.util.stream splits, accurate sizing information, and a number of other
 java.base.share.classes.java.util.stream {@link java.util.Spliterator#characteristics() characteristics} of the
 java.base.share.classes.java.util.stream spliterator or data that can be used by implementations to optimize
 java.base.share.classes.java.util.stream execution.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>Spliterators for mutable data sources have an additional challenge;
 java.base.share.classes.java.util.stream timing of binding to the data, since the data could change between the time
 java.base.share.classes.java.util.stream the spliterator is created and the time the stream pipeline is executed.
 java.base.share.classes.java.util.stream Ideally, a spliterator for a stream would report a characteristic of
 java.base.share.classes.java.util.stream {@code IMMUTABLE} or {@code CONCURRENT}; if not it should be
 java.base.share.classes.java.util.stream <a href="../Spliterator.html#binding"><em>late-binding</em></a>. If a source
 java.base.share.classes.java.util.stream cannot directly supply a recommended spliterator, it may indirectly supply
 java.base.share.classes.java.util.stream a spliterator using a {@code Supplier}, and construct a stream via the
 java.base.share.classes.java.util.stream {@code Supplier}-accepting versions of
 java.base.share.classes.java.util.stream {@link java.util.stream.StreamSupport#stream(Supplier, int, boolean) stream()}.
 java.base.share.classes.java.util.stream The spliterator is obtained from the supplier only after the terminal
 java.base.share.classes.java.util.stream operation of the stream pipeline commences.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream <p>These requirements significantly reduce the scope of potential
 java.base.share.classes.java.util.stream interference between mutations of the stream source and execution of stream
 java.base.share.classes.java.util.stream pipelines. Streams based on spliterators with the desired characteristics,
 java.base.share.classes.java.util.stream or those using the Supplier-based factory forms, are immune to
 java.base.share.classes.java.util.stream modifications of the data source prior to commencement of the terminal
 java.base.share.classes.java.util.stream operation (provided the behavioral parameters to the stream operations meet
 java.base.share.classes.java.util.stream the required criteria for non-interference and statelessness).  See
 java.base.share.classes.java.util.stream <a href="package-summary.html#NonInterference">Non-Interference</a>
 java.base.share.classes.java.util.stream for more details.
 java.base.share.classes.java.util.stream
 java.base.share.classes.java.util.stream @since 1.8
 java.base.share.classes.java.util.stream/
package java.util.stream;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
