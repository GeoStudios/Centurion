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

package jdk.random.share.classes.jdk.random;

import java.util.concurrent.atomic.AtomicLong;
import java.util.random.RandomGenerator;
import jdk.random.share.classes.jdk.internal.util.random.RandomSupport;
import jdk.random.share.classes.jdk.internal.util.random.RandomSupport.AbstractSplittableWithBrineGenerator;
import jdk.random.share.classes.jdk.internal.util.random.RandomSupport.RandomGeneratorProperties;

/**
 * A "splittable" pseudorandom number generator (PRNG) whose period
 * is roughly 2<sup>1088</sup>.  Class {@link L64X1024MixRandom} implements
 * interfaces {@link RandomGenerator} and {@link SplittableGenerator},
 * and therefore supports methods for producing pseudorandomly chosen
 * values of type {@code int}, {@code long}, {@code float}, {@code double},
 * and {@code boolean} (and for producing streams of pseudorandomly chosen
 * numbers of type {@code int}, {@code long}, and {@code double}),
 * as well as methods for creating new split-off {@link L64X1024MixRandom}
 * objects or streams of such objects.
 *
 * <p>The {@link L64X1024MixRandom} algorithm is a specific member of
 * the LXM family of algorithms for pseudorandom number generators;
 * for more information, see the documentation for package
 * {@link jdk.random}.  Each instance of {@link L64X1024MixRandom}
 * has 1088 bits of state plus one 64-bit instance-specific parameter.
 *
 * <p>If two instances of {@link L64X1024MixRandom} are created with
 * the same seed within the same program execution, and the same
 * sequence of method calls is made for each, they will generate and
 * return identical sequences of values.
 *
 * <p>As with {@link java.util.SplittableRandom}, instances of
 * {@link L64X1024MixRandom} are <em>not</em> thread-safe.  They are
 * designed to be split, not shared, across threads (see the {@link #split}
 * method). For example, a {@link java.util.concurrent.ForkJoinTask}
 * fork/join-style computation using random numbers might include a
 * construction of the form
 * {@code new Subtask(someL64X1024MixRandom.split()).fork()}.
 *
 * <p>This class provides additional methods for generating random
 * streams, that employ the above techniques when used in
 * {@code stream.parallel()} mode.
 *
 * <p>Instances of {@link L64X1024MixRandom} are not cryptographically
 * secure.  Consider instead using {@link java.security.SecureRandom}
 * in security-sensitive applications. Additionally,
 * default-constructed instances do not use a cryptographically random
 * seed unless the {@linkplain System#getProperty system property}
 * {@code java.util.secureRandomSeed} is set to {@code true}.
 *
 *
 */
@RandomGeneratorProperties(
        name = "L64X1024MixRandom",
        group = "LXM",
        i = 1024, j = 1, k = 64,
        equidistribution = 16
)
public final class L64X1024MixRandom extends AbstractSplittableWithBrineGenerator {

    /*
     * Implementation Overview.
     *
     * The split() operation uses the current generator to choose 18 new 64-bit
     * long values that are then used to initialize the parameter `a`, the
     * state variable `s`, and the array `x` for a newly constructed generator.
     *
     * With extremely high probability, no two generators so chosen
     * will have the same `a` parameter, and testing has indicated
     * that the values generated by two instances of {@link L64X1024MixRandom}
     * will be (approximately) independent if have different values for `a`.
     *
     * The default (no-argument) constructor, in essence, uses
     * "defaultGen" to generate 18 new 64-bit values for the same
     * purpose.  Multiple generators created in this way will certainly
     * differ in their `a` parameters.  The defaultGen state must be accessed
     * in a thread-safe manner, so we use an AtomicLong to represent
     * this state.  To bootstrap the defaultGen, we start off using a
     * seed based on current time unless the
     * java.util.secureRandomSeed property is set. This serves as a
     * slimmed-down (and insecure) variant of SecureRandom that also
     * avoids stalls that may occur when using /dev/random.
     *
     * File organization: First static fields, then instance
     * fields, then constructors, then instance methods.
     */

    /* ---------------- static fields ---------------- */

    /*
     * The length of the array x.
     */

    private static final int N = 16;

    /**
     * The seed generator for default constructors.
     */
    private static final AtomicLong defaultGen = new AtomicLong(RandomSupport.initialSeed());

    /*
     * Multiplier used in the LCG portion of the algorithm.
     * Chosen based on research by Sebastiano Vigna and Guy Steele (2019).
     * The spectral scores for dimensions 2 through 8 for the multiplier 0xd1342543de82ef95
     * are [0.958602, 0.937479, 0.870757, 0.822326, 0.820405, 0.813065, 0.760215].
     */

    private static final long M = 0xd1342543de82ef95L;

    /* ---------------- instance fields ---------------- */

    /**
     * The parameter that is used as an additive constant for the LCG.
     * Must be odd.
     */
    private final long a;

    /**
     * The per-instance state: s for the LCG; the array x for the XBG;
     * p is the rotating pointer into the array x.
     * At least one of the 16 elements of the array x must be nonzero.
     */
    private long s;
    private final long[] x;
    private int p = N - 1;

    /* ---------------- constructors ---------------- */

    /**
     * Basic constructor that initializes all fields from parameters.
     * It then adjusts the field values if necessary to ensure that
     * all constraints on the values of fields are met.
     *
     * @param a additive parameter for the LCG
     * @param s initial state for the LCG
     * @param x0 first word of the initial state for the XBG
     * @param x1 second word of the initial state for the XBG
     * @param x2 third word of the initial state for the XBG
     * @param x3 fourth word of the initial state for the XBG
     * @param x4 fifth word of the initial state for the XBG
     * @param x5 sixth word of the initial state for the XBG
     * @param x6 seventh word of the initial state for the XBG
     * @param x7 eight word of the initial state for the XBG
     * @param x8 ninth word of the initial state for the XBG
     * @param x9 tenth word of the initial state for the XBG
     * @param x10 eleventh word of the initial state for the XBG
     * @param x11 twelfth word of the initial state for the XBG
     * @param x12 thirteenth word of the initial state for the XBG
     * @param x13 fourteenth word of the initial state for the XBG
     * @param x14 fifteenth word of the initial state for the XBG
     * @param x15 sixteenth word of the initial state for the XBG
     */
    public L64X1024MixRandom(long a, long s,
                             long x0, long x1, long x2, long x3,
                             long x4, long x5, long x6, long x7,
                             long x8, long x9, long x10, long x11,
                             long x12, long x13, long x14, long x15) {
        // Force a to be odd.
        this.a = a | 1;
        this.s = s;
        this.x = new long[N];
        this.x[0] = x0;
        this.x[1] = x1;
        this.x[2] = x2;
        this.x[3] = x3;
        this.x[4] = x4;
        this.x[5] = x5;
        this.x[6] = x6;
        this.x[7] = x7;
        this.x[8] = x8;
        this.x[9] = x9;
        this.x[10] = x10;
        this.x[11] = x11;
        this.x[12] = x12;
        this.x[13] = x13;
        this.x[14] = x14;
        this.x[15] = x15;
        // If x0, x1, ..., x15 are all zero (very unlikely), we must choose nonzero values.
        if ((x0 | x1 | x2 | x3 | x4 | x5 | x6 | x7 | x8 | x9 | x10 | x11 | x12 | x13 | x14 | x15) == 0) {
       long v = s;
            // At least fifteen of the sixteen values generated here will be nonzero.
            for (int j = 0; j < N; j++) {
                this.x[j] = RandomSupport.mixStafford13(v += RandomSupport.GOLDEN_RATIO_64);
            }
        }
    }

    /**
     * Creates a new instance of {@link L64X1024MixRandom} using the
     * specified {@code long} value as the initial seed. Instances of
     * {@link L64X1024MixRandom} created with the same seed in the same
     * program execution generate identical sequences of values.
     *
     * @param seed the initial seed
     */
    public L64X1024MixRandom(long seed) {
        // Using a value with irregularly spaced 1-bits to xor the seed
        // argument tends to improve "pedestrian" seeds such as 0 or
        // other small integers.  We may as well use SILVER_RATIO_64.
        //
        // The seed is hashed by mixMurmur64 to produce the `a` parameter.
        // The seed is hashed by mixStafford13 to produce the initial `x[0]`,
        // which will then be used to produce the first generated value.
        // The other x values are filled in as if by a SplitMix PRNG with
        // GOLDEN_RATIO_64 as the gamma value and mixStafford13 as the mixer.
        this(RandomSupport.mixMurmur64(seed ^= RandomSupport.SILVER_RATIO_64),
             1,
             RandomSupport.mixStafford13(seed),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed += RandomSupport.GOLDEN_RATIO_64),
             RandomSupport.mixStafford13(seed + RandomSupport.GOLDEN_RATIO_64));
    }

    /**
     * Creates a new instance of {@link L64X1024MixRandom} that is likely to
     * generate sequences of values that are statistically independent
     * of those of any other instances in the current program execution,
     * but may, and typically does, vary across program invocations.
     */
    public L64X1024MixRandom() {
        // Using GOLDEN_RATIO_64 here gives us a good Weyl sequence of values.
        this(defaultGen.getAndAdd(RandomSupport.GOLDEN_RATIO_64));
    }

    /**
     * Creates a new instance of {@link L64X1024MixRandom} using the specified array of
     * initial seed bytes. Instances of {@link L64X1024MixRandom} created with the same
     * seed array in the same program execution generate identical sequences of values.
     *
     * @param seed the initial seed
     */
    public L64X1024MixRandom(byte[] seed) {
        // Convert the seed to 18 long values, of which the last 16 are not all zero.
        long[] data = RandomSupport.convertSeedBytesToLongs(seed, 18, 16);
        long a = data[0], s = data[1];
        // Force a to be odd.
        this.a = a | 1;
        this.s = s;
        this.x = new long[N];
        System.arraycopy(data, 2, this.x, 0, N);
    }

    /* ---------------- public methods ---------------- */

    @Override
    public SplittableGenerator split(SplittableGenerator source, long brine) {
       // Pick a new instance "at random", but use the brine for `a`.
        return new L64X1024MixRandom(brine << 1, source.nextLong(),
                    source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong(),
                                     source.nextLong(), source.nextLong());
    }

    @Override
    public long nextLong() {
        // First part of xoroshiro1024: fetch array data
        final int q = p;
        final long s0 = x[p = (p + 1) & (N - 1)];
        long s15 = x[q];

       // Compute the result based on current state information
       // (this allows the computation to be overlapped with state update).

       final long result = RandomSupport.mixLea64(s + s0);

       // Update the LCG subgenerator
        s = M * s + a;  // LCG

        // Second part of xoroshiro1024: update array data
        s15 ^= s0;
        x[q] = Long.rotateLeft(s0, 25) ^ s15 ^ (s15 << 27);
        x[p] = Long.rotateLeft(s15, 36);

        return result;
    }

}
