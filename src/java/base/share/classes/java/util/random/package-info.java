/java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.util.random/

/java.base.share.classes.java.util.randomjava.base.share.classes.java.util.random
 java.base.share.classes.java.util.random This package contains classes and interfaces that support a generic API
 java.base.share.classes.java.util.random for random number generation.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p>These classes and interfaces support the definition and use of "random
 java.base.share.classes.java.util.random generators", a term covering what have traditionally been called "random
 java.base.share.classes.java.util.random number generators" as well as generators of other sorts of randomly chosen
 java.base.share.classes.java.util.random values (eg. booleans). These classes and interfaces cover not only
 java.base.share.classes.java.util.random deterministic (pseudorandom) algorithms but also generators of values that
 java.base.share.classes.java.util.random use some "truly random" physical source (stochastic algorithms perhaps making
 java.base.share.classes.java.util.random use of thermal noise, for example, or quantum-mechanical effects).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The principal interface is {@link RandomGenerator}, which provides
 java.base.share.classes.java.util.random methods for requesting individual values of type {@code int}, {@code long},
 java.base.share.classes.java.util.random {@code float}, {@code double}, or {@code boolean} chosen pseudorandomly
 java.base.share.classes.java.util.random from a uniform distribution; methods for requesting values of type
 java.base.share.classes.java.util.random {@code double} chosen pseudorandomly from a normal distribution or from an
 java.base.share.classes.java.util.random exponential distribution; and methods for creating streams of values of type
 java.base.share.classes.java.util.random {@code int}, {@code long}, or {@code double} chosen pseudorandomly from a
 java.base.share.classes.java.util.random uniform distribution (such streams are spliterator-based, allowing for
 java.base.share.classes.java.util.random parallel processing of their elements). There are also static factory methods
 java.base.share.classes.java.util.random for creating an instance of a specific random number generator algorithm
 java.base.share.classes.java.util.random given its name.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The principal supporting class is {@link RandomGeneratorFactory}. This
 java.base.share.classes.java.util.random can be used to generate multiple random number generators for a specific
 java.base.share.classes.java.util.random algorithm. {@link RandomGeneratorFactory} also provides methods for
 java.base.share.classes.java.util.random selecting random number generator algorithms. RandomGeneratorFactory
 java.base.share.classes.java.util.random registers implementations of {@link RandomGenerator} interface using the
 java.base.share.classes.java.util.random service provider API.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> An important subsidiary interface is
 java.base.share.classes.java.util.random {@link RandomGenerator.StreamableGenerator}, which provides methods for
 java.base.share.classes.java.util.random creating spliterator-based streams of {@link RandomGenerator} objects,
 java.base.share.classes.java.util.random allowing for parallel processing of these objects using multiple threads.
 java.base.share.classes.java.util.random Unlike {@link java.util.Random}, most implementations of
 java.base.share.classes.java.util.random {@link RandomGenerator} are <i>not</i> thread-safe. The intent is that
 java.base.share.classes.java.util.random instances should not be shared among threads; rather, each thread should have
 java.base.share.classes.java.util.random its own random generator(s) to use. The various pseudorandom algorithms
 java.base.share.classes.java.util.random provided by this package are designed so that multiple instances will (with
 java.base.share.classes.java.util.random very high probability) behave as if statistically independent.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For many purposes, these are the only two interfaces that a consumer of
 java.base.share.classes.java.util.random pseudorandom values will need. There are also some more specialized
 java.base.share.classes.java.util.random interfaces that describe more specialized categories of random number
 java.base.share.classes.java.util.random generators {@link RandomGenerator.SplittableGenerator SplittableGenerator},
 java.base.share.classes.java.util.random {@link RandomGenerator.JumpableGenerator JumpableGenerator},
 java.base.share.classes.java.util.random {@link RandomGenerator.LeapableGenerator LeapableGenerator}, and
 java.base.share.classes.java.util.random {@link RandomGenerator.ArbitrarilyJumpableGenerator ArbitrarilyJumpableGenerator}
 java.base.share.classes.java.util.random that have specific strategies for creating statistically independent instances.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <h2>Using the Random Number Generator Interfaces</h2>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random To get started, an application should first create one instance of a
 java.base.share.classes.java.util.random generator class. Assume that the contents of the package
 java.base.share.classes.java.util.random {@link java.util.random} has been imported:
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <blockquote>{@code import java.util.random.java.base.share.classes.java.util.random;}</blockquote>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random Then one can choose a specific implementation by giving the name of a generator
 java.base.share.classes.java.util.random algorithm to the static method {@link RandomGenerator#of}, in which case the
 java.base.share.classes.java.util.random no-arguments constructor for that implementation is used:
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <blockquote>{@code RandomGenerator g = RandomGenerator.of("L64X128MixRandom");}</blockquote>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random For a single-threaded application, this is all that is needed. One can then
 java.base.share.classes.java.util.random invoke methods of {@code g} such as
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextInt nextInt()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextFloat nextFloat()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextDouble nextDouble()} and
 java.base.share.classes.java.util.random {@link RandomGenerator#nextBoolean nextBoolean()} to generate individual
 java.base.share.classes.java.util.random randomly chosen values. One can also use the methods
 java.base.share.classes.java.util.random {@link RandomGenerator#ints ints()}, {@link RandomGenerator#longs longs()}
 java.base.share.classes.java.util.random and {@link RandomGenerator#doubles doubles()} to create streams of randomly
 java.base.share.classes.java.util.random chosen values. The methods
 java.base.share.classes.java.util.random {@link RandomGenerator#nextGaussian nextGaussian()} and
 java.base.share.classes.java.util.random {@link RandomGenerator#nextExponential nextExponential()} draw floating-point
 java.base.share.classes.java.util.random values from nonuniform distributions.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For a multi-threaded application, one can repeat the preceding steps
 java.base.share.classes.java.util.random to create additional {@linkplain RandomGenerator RandomGenerators}, but
 java.base.share.classes.java.util.random often it is preferable to use methods of the one single initially
 java.base.share.classes.java.util.random created generator to create others like it. (One reason is that some
 java.base.share.classes.java.util.random generator algorithms, if asked to create a new set of generators all at
 java.base.share.classes.java.util.random once, can make a special effort to ensure that the new generators are
 java.base.share.classes.java.util.random statistically independent.) If the initial generator implements the
 java.base.share.classes.java.util.random interface {@link RandomGenerator.StreamableGenerator}, then the method
 java.base.share.classes.java.util.random {@link RandomGenerator.StreamableGenerator#rngs rngs()} can be used to
 java.base.share.classes.java.util.random create a stream of generators. If this is a parallel stream, then it is
 java.base.share.classes.java.util.random easy to get parallel execution by using the
 java.base.share.classes.java.util.random {@link java.util.stream.Stream#map map()} method on the stream.
 java.base.share.classes.java.util.random <p> For a multi-threaded application that forks new threads dynamically,
 java.base.share.classes.java.util.random another approach is to use an initial generator that implements the interface
 java.base.share.classes.java.util.random {@link RandomGenerator.SplittableGenerator}, which is then considered to
 java.base.share.classes.java.util.random "belong" to the initial thread for its exclusive use; then whenever any
 java.base.share.classes.java.util.random thread needs to fork a new thread, it first uses the
 java.base.share.classes.java.util.random {@link RandomGenerator.SplittableGenerator#split split()} method of its own
 java.base.share.classes.java.util.random generator to create a new generator, which is then passed to the newly
 java.base.share.classes.java.util.random created thread for exclusive use by that new thread.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <h2>Choosing a Random Number Generator Algorithm</h2>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> There are three groups of random number generator algorithm provided
 java.base.share.classes.java.util.random in Java: the Legacy group, the LXM group, and the Xoroshiro/Xoshiro group.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The legacy group includes random number generators that existed
 java.base.share.classes.java.util.random before JDK 17: Random, ThreadLocalRandom, SplittableRandom, and
 java.base.share.classes.java.util.random SecureRandom. Random (LCG) is the weakest of the available algorithms, and it
 java.base.share.classes.java.util.random is recommended that users migrate to newer algorithms. If an application
 java.base.share.classes.java.util.random requires a random number generator algorithm that is cryptographically
 java.base.share.classes.java.util.random secure, then it should continue to use an instance of the class {@link
 java.base.share.classes.java.util.random java.security.SecureRandom}.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The algorithms in the LXM group are similar to each other. The parameters
 java.base.share.classes.java.util.random of each algorithm can be found in the algorithm name. The number after "L" indicates the
 java.base.share.classes.java.util.random number of state bits for the LCG subgenerator, and the number after "X" indicates the
 java.base.share.classes.java.util.random number of state bits for the XBG subgenerator. "Mix" indicates that
 java.base.share.classes.java.util.random the algorithm uses an 8-operation bit-mixing function; "StarStar" indicates use
 java.base.share.classes.java.util.random of a 3-operation bit-scrambler.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The algorithms in the Xoroshiro/Xoshiro group are more traditional algorithms
 java.base.share.classes.java.util.random (see David Blackman and Sebastiano Vigna, "Scrambled Linear Pseudorandom
 java.base.share.classes.java.util.random Number Generators," ACM Transactions on Mathematical Software, 2021);
 java.base.share.classes.java.util.random the number in the name indicates the number of state bits.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For applications (such as physical simulation, machine learning, and
 java.base.share.classes.java.util.random games) that do not require a cryptographically secure algorithm, this package
 java.base.share.classes.java.util.random provides multiple implementations of interface {@link RandomGenerator} that
 java.base.share.classes.java.util.random provide trade-offs among speed, space, period, accidental correlation, and
 java.base.share.classes.java.util.random equidistribution properties.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For applications with no special requirements,
 java.base.share.classes.java.util.random {@code L64X128MixRandom} has a good balance among speed, space,
 java.base.share.classes.java.util.random and period, and is suitable for both single-threaded and multi-threaded
 java.base.share.classes.java.util.random applications when used properly (a separate instance for each thread).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> If the application uses only a single thread, then
 java.base.share.classes.java.util.random {@code Xoroshiro128PlusPlus} is even smaller and faster, and
 java.base.share.classes.java.util.random certainly has a sufficiently long period.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For an application running in a 32-bit hardware environment and using
 java.base.share.classes.java.util.random only one thread or a small number of threads, {@code L32X64MixRandom} may be a good
 java.base.share.classes.java.util.random choice.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For an application that uses many threads that are allocated in one batch
 java.base.share.classes.java.util.random at the start of the computation, either a "jumpable" generator such as
 java.base.share.classes.java.util.random {@code Xoroshiro128PlusPlus} or
 java.base.share.classes.java.util.random {@code Xoshiro256PlusPlus} may be used, or a "splittable"
 java.base.share.classes.java.util.random generator such as {@code L64X128MixRandom} or
 java.base.share.classes.java.util.random {@code L64X256MixRandom} may be used.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For an application that creates many threads dynamically, perhaps through
 java.base.share.classes.java.util.random the use of spliterators, a "splittable" generator such as
 java.base.share.classes.java.util.random {@code L64X128MixRandom} or {@code L64X256MixRandom} is
 java.base.share.classes.java.util.random recommended. If the number of generators created dynamically may
 java.base.share.classes.java.util.random be very large (millions or more), then using generators such as
 java.base.share.classes.java.util.random {@code L128X128MixRandom} or {@code L128X256MixRandom},
 java.base.share.classes.java.util.random which use a 128-bit parameter rather than a 64-bit parameter for their LCG
 java.base.share.classes.java.util.random subgenerator, will make it much less likely that two instances use the same
 java.base.share.classes.java.util.random state cycle.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For an application that uses tuples of consecutively generated values, it
 java.base.share.classes.java.util.random may be desirable to use a generator that is <i>k</i>-equidistributed such
 java.base.share.classes.java.util.random that <i>k</i> is at least as large as the length of the tuples being
 java.base.share.classes.java.util.random generated. The generator {@code L64X256MixRandom} is provably
 java.base.share.classes.java.util.random 4-equidistributed, and {@code L64X1024MixRandom} is provably
 java.base.share.classes.java.util.random 16-equidistributed.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For applications that generate large permutations, it may be best to use
 java.base.share.classes.java.util.random a generator whose period is much larger than the total number of possible
 java.base.share.classes.java.util.random permutations; otherwise it will be impossible to generate some of the
 java.base.share.classes.java.util.random intended permutations. For example, if the goal is to shuffle a deck of 52
 java.base.share.classes.java.util.random cards, the number of possible permutations is 52! (52 factorial), which is
 java.base.share.classes.java.util.random larger than 2<sup>225</sup> (but smaller than 2<sup>226</sup>), so it may be
 java.base.share.classes.java.util.random best to use a generator whose period at least 2<sup>256</sup>, such as
 java.base.share.classes.java.util.random {@code L64X256MixRandom} or {@code L64X1024MixRandom}
 java.base.share.classes.java.util.random or {@code L128X256MixRandom} or
 java.base.share.classes.java.util.random {@code L128X1024MixRandom}. (It is of course also necessary to
 java.base.share.classes.java.util.random provide sufficiently many seed bits when the generator is initialized, or
 java.base.share.classes.java.util.random else it will still be impossible to generate some of the intended
 java.base.share.classes.java.util.random permutations.)
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <h2><a id="algorithms">Random Number Generator Algorithms Available</a></h2>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random These algorithms [in the table below] must be found with the current version
 java.base.share.classes.java.util.random of Java SE. A particular JDK implementation may recognize additional
 java.base.share.classes.java.util.random algorithms; check the JDK's documentation for details. The set of algorithms
 java.base.share.classes.java.util.random required by Java SE may be updated by changes to the Java SE specification.
 java.base.share.classes.java.util.random Over time, new algorithms may be added and old algorithms may be removed.
 java.base.share.classes.java.util.random <p>In addition, as another life-cycle phase, an algorithm may be {@linkplain
 java.base.share.classes.java.util.random RandomGeneratorFactory#isDeprecated() deprecated}. A deprecated algorithm is
 java.base.share.classes.java.util.random not recommended for use. If a required algorithm is deprecated, it may be
 java.base.share.classes.java.util.random removed in a future release. Due to advances in random number generator
 java.base.share.classes.java.util.random algorithm development and analysis, an algorithm may be deprecated during the
 java.base.share.classes.java.util.random lifetime of a particular Java SE release. Changing the deprecation status of
 java.base.share.classes.java.util.random an algorithm is <em>not</em> a specification change.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <table class="striped">
 java.base.share.classes.java.util.random  <caption>Available Algorithms</caption>
 java.base.share.classes.java.util.random  <thead>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th style="text-align:left">Algorithm</th>
 java.base.share.classes.java.util.random      <th style="text-align:left">Group</th>
 java.base.share.classes.java.util.random      <th style="text-align:left">Period</th>
 java.base.share.classes.java.util.random      <th style="text-align:right">StateBits</th>
 java.base.share.classes.java.util.random      <th style="text-align:right">Equidistribution</th>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  </thead>
 java.base.share.classes.java.util.random  <tbody>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L128X1024MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(1024).subtract(BigInteger.ONE).shiftLeft(128)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1152</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L128X128MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(128).subtract(BigInteger.ONE).shiftLeft(128)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">256</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L128X256MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(256).subtract(BigInteger.ONE).shiftLeft(128)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">384</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L32X64MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE).shiftLeft(32)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">96</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L64X1024MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(1024).subtract(BigInteger.ONE).shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1088</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">16</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L64X128MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(128).subtract(BigInteger.ONE).shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">192</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">2</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L64X128StarStarRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(128).subtract(BigInteger.ONE).shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">192</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">2</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">L64X256MixRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">LXM</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(256).subtract(BigInteger.ONE).shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">320</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">4</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">Random</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">Legacy</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(48)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">48</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">0</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">SplittableRandom</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">Legacy</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">64</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">ThreadLocalRandom <sup>java.base.share.classes.java.util.random</sup></th>
 java.base.share.classes.java.util.random      <td style="text-align:left">Legacy</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(64)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">64</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">Xoroshiro128PlusPlus</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">Xoroshiro</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(128).subtract(BigInteger.ONE)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">128</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">1</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  <tr>
 java.base.share.classes.java.util.random      <th scope="row" style="text-align:left">Xoshiro256PlusPlus</th>
 java.base.share.classes.java.util.random      <td style="text-align:left">Xoshiro</td>
 java.base.share.classes.java.util.random      <td style="text-align:left">BigInteger.ONE.shiftLeft(256).subtract(BigInteger.ONE)</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">256</td>
 java.base.share.classes.java.util.random      <td style="text-align:right">3</td>
 java.base.share.classes.java.util.random  </tr>
 java.base.share.classes.java.util.random  </tbody>
 java.base.share.classes.java.util.random </table>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p><sup>java.base.share.classes.java.util.random</sup> ThreadLocalRandom can only be accessed via
 java.base.share.classes.java.util.random {@link java.util.concurrent.ThreadLocalRandom#current()}.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <h2>Categories of Random Number Generator Algorithms</h2>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random Historically, most pseudorandom generator algorithms have been based on some
 java.base.share.classes.java.util.random sort of finite-state machine with a single, large cycle of states; when it is
 java.base.share.classes.java.util.random necessary to have multiple threads use the same algorithm simultaneously, the
 java.base.share.classes.java.util.random usual technique is to arrange for each thread to traverse a different region
 java.base.share.classes.java.util.random of the state cycle. These regions may be doled out to threads by starting
 java.base.share.classes.java.util.random with a single initial state and then using a "jump function" that travels a
 java.base.share.classes.java.util.random long distance around the cycle (perhaps 2<sup>64</sup> steps or more); the
 java.base.share.classes.java.util.random jump function is applied repeatedly and sequentially, to identify widely
 java.base.share.classes.java.util.random spaced states that are then doled out, one to each thread, to serve as the
 java.base.share.classes.java.util.random initial state for the generator to be used by that thread. This strategy is
 java.base.share.classes.java.util.random supported by the interface {@link RandomGenerator.JumpableGenerator}.
 java.base.share.classes.java.util.random Sometimes it is desirable to support two levels of jumping (by long distances
 java.base.share.classes.java.util.random and by <i>really</i> long distances); this strategy is supported by the
 java.base.share.classes.java.util.random interface {@link RandomGenerator.LeapableGenerator}. In this package,
 java.base.share.classes.java.util.random implementations of this interface include "Xoroshiro128PlusPlus" and
 java.base.share.classes.java.util.random "Xoshiro256PlusPlus". There is also an interface
 java.base.share.classes.java.util.random {@link RandomGenerator.ArbitrarilyJumpableGenerator} for algorithms that allow
 java.base.share.classes.java.util.random jumping along the state cycle by any user-specified distance; there are currently
 java.base.share.classes.java.util.random no implementations of this interface in this package.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> A more recent category of "splittable" pseudorandom generator algorithms
 java.base.share.classes.java.util.random uses a large family of state cycles and makes some attempt to ensure that
 java.base.share.classes.java.util.random distinct instances use different state cycles; but even if two instances
 java.base.share.classes.java.util.random "accidentally" use the same state cycle, they are highly likely to traverse
 java.base.share.classes.java.util.random different regions of that shared state cycle. This strategy is
 java.base.share.classes.java.util.random supported by the interface {@link RandomGenerator.SplittableGenerator}.
 java.base.share.classes.java.util.random In this package, implementations of this interface include
 java.base.share.classes.java.util.random "L32X64MixRandom",
 java.base.share.classes.java.util.random "L64X128StarStarRandom",
 java.base.share.classes.java.util.random "L64X128MixRandom",
 java.base.share.classes.java.util.random "L64X256MixRandom",
 java.base.share.classes.java.util.random "L64X1024MixRandom",
 java.base.share.classes.java.util.random "L128X128MixRandom",
 java.base.share.classes.java.util.random "L128X256MixRandom", and
 java.base.share.classes.java.util.random "L128X1024MixRandom"; note that the class
 java.base.share.classes.java.util.random {@link java.util.SplittableRandom} also implements this interface.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <h2>The LXM Family of Random Number Generator Algorithms</h2>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random The structure of the central nextLong (or nextInt) method of an LXM
 java.base.share.classes.java.util.random algorithm follows a suggestion in December 2017 by Sebastiano Vigna
 java.base.share.classes.java.util.random that using one Linear Congruential Generator (LCG) as a first subgenerator
 java.base.share.classes.java.util.random and one Xor-Based Generator (XBG) as a second subgenerator (rather
 java.base.share.classes.java.util.random than using two LCG subgenerators) would provide a longer period, superior
 java.base.share.classes.java.util.random equidistribution, scalability, and better quality.  Each of the
 java.base.share.classes.java.util.random specific implementations here combines one of the best currently known
 java.base.share.classes.java.util.random XBG algorithms (xoroshiro128 or xoshiro256, described by Blackman and
 java.base.share.classes.java.util.random Vigna in "Scrambled Linear Pseudorandom Number Generators", <i>ACM Transactions
 java.base.share.classes.java.util.random on Mathematical Software</i>, 2021) with an LCG that uses one of the best
 java.base.share.classes.java.util.random currently known multipliers (found by a search for better multipliers
 java.base.share.classes.java.util.random in 2019 by Steele and Vigna, described in "Computationally Easy, Spectrally
 java.base.share.classes.java.util.random Good Multipliers for Congruential Pseudorandom Number Generators",
 java.base.share.classes.java.util.random <i>Software: Practice and Experience</i> (2021), doi:10.1002/spe.3030),
 java.base.share.classes.java.util.random and then applies either a mixing function identified by Doug Lea or
 java.base.share.classes.java.util.random or a simple scrambler proposed by Blackman and Vigna. Testing has
 java.base.share.classes.java.util.random confirmed that the LXM algorithm is far superior in quality to the
 java.base.share.classes.java.util.random SplitMix algorithm (2014) used by {@code SplittableRandom}
 java.base.share.classes.java.util.random (see Steele and Vigna, "LXM: Better Splittable Pseudorandom Number
 java.base.share.classes.java.util.random Generators (and Almost as Fast)", <i>Proc. 2021 ACM OOPSLA Conference</i>).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random Each class with a name of the form
 java.base.share.classes.java.util.random {@code L}<i>p</i>{@code X}<i>q</i>{@code SomethingRandom}
 java.base.share.classes.java.util.random uses some specific member of the LXM family of random number
 java.base.share.classes.java.util.random algorithms; "LXM" is short for "LCG, XBG, Mixer". Every LXM
 java.base.share.classes.java.util.random generator has two subgenerators; one is an LCG (Linear Congruential
 java.base.share.classes.java.util.random Generator) and the other is an XBG (Xor-Based Generator). Each output of an LXM
 java.base.share.classes.java.util.random generator is the result of combining state from the LCG with state from the
 java.base.share.classes.java.util.random XBG using a Mixing function (and then the state of the LCG
 java.base.share.classes.java.util.random and the state of the XBG are advanced).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The LCG subgenerator has an update step of the form {@code s = mjava.base.share.classes.java.util.randoms + a},
 java.base.share.classes.java.util.random where {@code s}, {@code m}, and {@code a} are all binary integers of the same
 java.base.share.classes.java.util.random size, each having <i>p</i> bits; {@code s} is the mutable state, the
 java.base.share.classes.java.util.random multiplier {@code m} is fixed (the same for all instances of a class) and the
 java.base.share.classes.java.util.random addend {@code a} is a parameter (a final field of the instance). The
 java.base.share.classes.java.util.random parameter {@code a} is required to be odd (this allows the LCG to have the
 java.base.share.classes.java.util.random maximal period, namely 2<sup><i>p</i></sup>); therefore there are
 java.base.share.classes.java.util.random 2<sup><i>p</i>&minus;1</sup> distinct choices of parameter. (When the size of
 java.base.share.classes.java.util.random {@code s} is 128 bits, then we use the name "{@code sh}" below to refer to
 java.base.share.classes.java.util.random the high half of {@code s}, that is, the high-order 64 bits of {@code s}.)
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The XBG subgenerator can in principle be any one of a wide variety
 java.base.share.classes.java.util.random of XBG algorithms; in this package it is always either
 java.base.share.classes.java.util.random {@code xoroshiro128}, {@code xoshiro256}, or {@code xoroshiro1024}, in each
 java.base.share.classes.java.util.random case without any final scrambler (such as "+" or "java.base.share.classes.java.util.randomjava.base.share.classes.java.util.random") because LXM uses
 java.base.share.classes.java.util.random a separate Mixer later in the process. The XBG state consists of
 java.base.share.classes.java.util.random some fixed number of {@code int} or {@code long} fields, generally named
 java.base.share.classes.java.util.random {@code x0}, {@code x1}, and so on, which can take on any values provided that
 java.base.share.classes.java.util.random they are not all zero. The collective total size of these fields is <i>q</i>
 java.base.share.classes.java.util.random bits; therefore the period of this subgenerator is
 java.base.share.classes.java.util.random 2<sup><i>q</i></sup>&minus;1.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> Because the periods 2<sup><i>p</i></sup> and 2<sup><i>q</i></sup>&minus;1
 java.base.share.classes.java.util.random of the two subgenerators are relatively prime, the <em>period</em> of any
 java.base.share.classes.java.util.random single instance of an LXM algorithm (the length of the series of generated
 java.base.share.classes.java.util.random values before it repeats) is the product of the periods of the subgenerators,
 java.base.share.classes.java.util.random that is, 2<sup><i>p</i></sup>(2<sup><i>q</i></sup>&minus;1), which is just
 java.base.share.classes.java.util.random slightly smaller than 2<sup>(<i>p</i>+<i>q</i>)</sup>. Moreover, if two
 java.base.share.classes.java.util.random distinct instances of the same LXM algorithm have different {@code a}
 java.base.share.classes.java.util.random parameters, then their cycles of produced values will be different.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> Generally speaking, among the "{@code L}<i>p</i>{@code X}<i>q</i>"
 java.base.share.classes.java.util.random generators, the memory required for an instance is 2<i>p</i>+<i>q</i> bits.
 java.base.share.classes.java.util.random (If <i>q</i> is 1024 or larger, the XBG state is represented as an
 java.base.share.classes.java.util.random array, so additional bits are needed for the array object header, and another
 java.base.share.classes.java.util.random 32 bits are used for an array index.)
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> Larger values of <i>p</i> imply a lower probability that two distinct
 java.base.share.classes.java.util.random instances will traverse the same state cycle, and larger values of <i>q</i>
 java.base.share.classes.java.util.random imply that the generator is equidistributed in a larger number of dimensions
 java.base.share.classes.java.util.random (this is provably true when <i>p</i> is 64, and conjectured to be
 java.base.share.classes.java.util.random approximately true when <i>p</i> is 128). A class with "{@code Mix}" in its
 java.base.share.classes.java.util.random name uses a fairly strong mixing function with excellent avalanche
 java.base.share.classes.java.util.random characteristics; a class with "{@code StarStar}" in its name uses a weaker
 java.base.share.classes.java.util.random but faster mixing function.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The specific LXM algorithms used in this package are all chosen so that
 java.base.share.classes.java.util.random the 64-bit values produced by the {@link RandomGenerator#nextLong nextLong()}
 java.base.share.classes.java.util.random method are exactly equidistributed (for example, for any specific instance of
 java.base.share.classes.java.util.random "L64X128MixRandom", over the course of its cycle each of the
 java.base.share.classes.java.util.random 2<sup>64</sup> possible {@code long} values will be produced
 java.base.share.classes.java.util.random 2<sup>128</sup>&minus;1 times). The values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextInt nextInt()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextFloat nextFloat()}, and
 java.base.share.classes.java.util.random {@link RandomGenerator#nextDouble nextDouble()} methods are likewise exactly
 java.base.share.classes.java.util.random equidistributed. Some algorithms provide a further guarantee of
 java.base.share.classes.java.util.random <i>k</i>-equidistribution for some <i>k</i> greater than 1, meaning that successive
 java.base.share.classes.java.util.random non-overlapping <i>k</i>-tuples of 64-bit values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()} method are exactly
 java.base.share.classes.java.util.random equidistributed (equally likely to occur).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The following table gives the period, state size (in bits), parameter
 java.base.share.classes.java.util.random size (in bits, including the low-order bit that is required always to be a
 java.base.share.classes.java.util.random 1-bit), and equidistribution property for each of the specific LXM algorithms
 java.base.share.classes.java.util.random used in this package.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <table class="striped">
 java.base.share.classes.java.util.random <caption>Algorithm Properties</caption>
 java.base.share.classes.java.util.random <thead>
 java.base.share.classes.java.util.random   <tr><th style="text-align:left">Implementation</th>
 java.base.share.classes.java.util.random       <th style="text-align:right">Period</th>
 java.base.share.classes.java.util.random       <th style="text-align:right">State size</th>
 java.base.share.classes.java.util.random       <th style="text-align:right">Parameter size</th>
 java.base.share.classes.java.util.random       <th style="text-align:left">{@link RandomGenerator#nextLong nextLong()} values are</th></tr>
 java.base.share.classes.java.util.random </thead>
 java.base.share.classes.java.util.random <tbody>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L32X64MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>32</sup>(2<sup>64</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">96 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">32 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left"></td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X128StarStarRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>64</sup>(2<sup>128</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">192 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">64 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">2-equidistributed and exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X128MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>64</sup>(2<sup>128</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">192 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">64 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">2-equidistributed and exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X256MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>64</sup>(2<sup>256</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">320 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">64 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">4-equidistributed and exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X1024MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>64</sup>(2<sup>1024</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">1088 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">64 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">16-equidistributed and exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X128MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>128</sup>(2<sup>128</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">256 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">128 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X256MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>128</sup>(2<sup>256</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">384 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">128 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X1024MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">2<sup>128</sup>(2<sup>1024</sup>&minus;1)</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">1152 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:right">128 bits</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">exactly equidistributed</td></tr>
 java.base.share.classes.java.util.random </tbody>
 java.base.share.classes.java.util.random </table>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random For the algorithms listed above whose names begin with {@code L32}, the
 java.base.share.classes.java.util.random 32-bit values produced by the {@link RandomGenerator#nextInt nextInt()}
 java.base.share.classes.java.util.random method are exactly equidistributed, but the 64-bit values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()} method are not exactly
 java.base.share.classes.java.util.random equidistributed.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> For the algorithms listed above whose names begin with {@code L64} or
 java.base.share.classes.java.util.random {@code L128}, the 64-bit values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()} method are <i>exactly
 java.base.share.classes.java.util.random equidistributed</i>: every instance, over the course of its cycle, will
 java.base.share.classes.java.util.random produce each of the 2<sup>64</sup> possible {@code long} values exactly the
 java.base.share.classes.java.util.random same number of times. For example, any specific instance of
 java.base.share.classes.java.util.random "L64X256MixRandom", over the course of its cycle each of the
 java.base.share.classes.java.util.random 2<sup>64</sup> possible {@code long} values will be produced
 java.base.share.classes.java.util.random 2<sup>256</sup>&minus;1 times. The values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextInt nextInt()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextFloat nextFloat()}, and
 java.base.share.classes.java.util.random {@link RandomGenerator#nextDouble nextDouble()} methods are likewise exactly
 java.base.share.classes.java.util.random equidistributed.
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> In addition, for the algorithms listed above whose names begin with
 java.base.share.classes.java.util.random {@code L64}, the 64-bit values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()} method are
 java.base.share.classes.java.util.random <i>k</i>-equidistributed (but not exactly <i>k</i>-equidistributed). To be
 java.base.share.classes.java.util.random precise, and taking "L64X256MixRandom" as an example: for
 java.base.share.classes.java.util.random any specific instance of "L64X256MixRandom", consider the
 java.base.share.classes.java.util.random (overlapping) length-4 subsequences of the cycle of 64-bit values produced by
 java.base.share.classes.java.util.random {@link RandomGenerator#nextLong nextLong()} (assuming no other methods are
 java.base.share.classes.java.util.random called that would affect the state). There are
 java.base.share.classes.java.util.random 2<sup>64</sup>(2<sup>256</sup>&minus;1) such subsequences, and each
 java.base.share.classes.java.util.random subsequence, which consists of 4 64-bit values, can have one of
 java.base.share.classes.java.util.random 2<sup>256</sup> values. Of those 2<sup>256</sup> subsequence values, nearly
 java.base.share.classes.java.util.random all of them (2<sup>256</sup>&minus;2<sup>64</sup>) occur 2<sup>64</sup> times
 java.base.share.classes.java.util.random over the course of the entire cycle, and the other 2<sup>64</sup> subsequence
 java.base.share.classes.java.util.random values occur only 2<sup>64</sup>&minus;1 times. So the ratio of the
 java.base.share.classes.java.util.random probability of getting any specific one of the less common subsequence values
 java.base.share.classes.java.util.random and the probability of getting any specific one of the more common
 java.base.share.classes.java.util.random subsequence values is 1&minus;2<sup>-64</sup>. (Note that the set of
 java.base.share.classes.java.util.random 2<sup>64</sup> less-common subsequence values will differ from one instance
 java.base.share.classes.java.util.random of "L64X256MixRandom" to another, as a function of the
 java.base.share.classes.java.util.random additive parameter of the LCG.) The values produced by the
 java.base.share.classes.java.util.random {@link RandomGenerator#nextInt nextInt()},
 java.base.share.classes.java.util.random {@link RandomGenerator#nextFloat nextFloat()}, and
 java.base.share.classes.java.util.random {@link RandomGenerator#nextDouble nextDouble()} methods are likewise
 java.base.share.classes.java.util.random 4-equidistributed (but not exactly 4-equidistributed).
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <p> The next table gives the LCG multiplier value, the name of the specific
 java.base.share.classes.java.util.random XBG algorithm used, the specific numeric parameters for that XBG
 java.base.share.classes.java.util.random algorithm, and the mixing function for each of the specific LXM algorithms
 java.base.share.classes.java.util.random used in this package. (Note that the multiplier used for the 128-bit LCG
 java.base.share.classes.java.util.random cases is 65 bits wide, so the constant {@code 0x1d605bbb58c8abbfdL} shown in
 java.base.share.classes.java.util.random the table cannot actually be used in code; instead, only the 64 low-order
 java.base.share.classes.java.util.random bits {@code 0xd605bbb58c8abbfdL} are represented in the source code, and the
 java.base.share.classes.java.util.random missing 1-bit is handled through special coding of the multiply-add algorithm
 java.base.share.classes.java.util.random used in the LCG.)
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random <table class="striped">
 java.base.share.classes.java.util.random <caption>LXM Multipliers</caption>
 java.base.share.classes.java.util.random <thead>
 java.base.share.classes.java.util.random   <tr><th style="text-align:left">Implementation</th>
 java.base.share.classes.java.util.random       <th style="text-align:right">LCG multiplier {@code m}</th>
 java.base.share.classes.java.util.random       <th style="text-align:left">XBG algorithm</th>
 java.base.share.classes.java.util.random       <th style="text-align:left">XBG parameters</th>
 java.base.share.classes.java.util.random       <th style="text-align:left">Mixing function</th></tr>
 java.base.share.classes.java.util.random </thead>
 java.base.share.classes.java.util.random <tbody>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L32X64MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0xadb4a92d}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro64}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (26, 9, 13)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea32{@code (s+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X128StarStarRandom" </th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0xd1342543de82ef95L}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro128}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (24, 16, 37)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code Long.rotateLeft((s+x0)java.base.share.classes.java.util.random 5, 7) java.base.share.classes.java.util.random 9}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X128MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0xd1342543de82ef95L}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro128}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (24, 16, 37)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (s+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X256MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0xd1342543de82ef95L}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoshiro256}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (17, 45)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (s+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L64X1024MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0xd1342543de82ef95L}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro1024}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (25, 27, 36)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (s+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X128MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0x1d605bbb58c8abbfdL}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro128}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (24, 16, 37)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (sh+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X256MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0x1d605bbb58c8abbfdL}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoshiro256}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (17, 45)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (sh+x0)}</td></tr>
 java.base.share.classes.java.util.random   <tr><th scope="row" style="text-align:left">"L128X1024MixRandom"</th>
 java.base.share.classes.java.util.random       <td style="text-align:right">{@code 0x1d605bbb58c8abbfdL}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code xoroshiro1024}, version 1.0</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">{@code (25, 27, 36)}</td>
 java.base.share.classes.java.util.random       <td style="text-align:left">mixLea64{@code (sh+x0)}</td></tr>
 java.base.share.classes.java.util.random </tbody>
 java.base.share.classes.java.util.random </table>
 java.base.share.classes.java.util.random
 java.base.share.classes.java.util.random @since   17
 java.base.share.classes.java.util.random/
package java.util.random;

