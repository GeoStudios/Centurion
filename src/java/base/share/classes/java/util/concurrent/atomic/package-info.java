/java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.util.concurrent.atomic/

/java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic This file is available under and governed by the GNU General Public
 java.base.share.classes.java.util.concurrent.atomic License version 2 only, as published by the Free Software Foundation.
 java.base.share.classes.java.util.concurrent.atomic However, the following notice accompanied the original version of this
 java.base.share.classes.java.util.concurrent.atomic file:
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic Written by Doug Lea with assistance from members of JCP JSR-166
 java.base.share.classes.java.util.concurrent.atomic Expert Group and released to the public domain, as explained at
 java.base.share.classes.java.util.concurrent.atomic http://creativecommons.org/publicdomain/zero/1.0/
 java.base.share.classes.java.util.concurrent.atomic/

/java.base.share.classes.java.util.concurrent.atomicjava.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic A small toolkit of classes that support lock-free thread-safe
 java.base.share.classes.java.util.concurrent.atomic programming on single variables.  Instances of Atomic classes
 java.base.share.classes.java.util.concurrent.atomic maintain values that are accessed and updated using methods
 java.base.share.classes.java.util.concurrent.atomic otherwise available for fields using associated atomic {@link
 java.base.share.classes.java.util.concurrent.atomic java.lang.invoke.VarHandle} operations.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>Instances of classes
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicBoolean},
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicInteger},
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicLong}, and
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicReference}
 java.base.share.classes.java.util.concurrent.atomic each provide access and updates to a single variable of the
 java.base.share.classes.java.util.concurrent.atomic corresponding type.  Each class also provides appropriate utility
 java.base.share.classes.java.util.concurrent.atomic methods for that type.  For example, classes {@code AtomicLong} and
 java.base.share.classes.java.util.concurrent.atomic {@code AtomicInteger} provide atomic increment methods.  One
 java.base.share.classes.java.util.concurrent.atomic application is to generate sequence numbers, as in:
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <pre> {@code
 java.base.share.classes.java.util.concurrent.atomic class Sequencer {
 java.base.share.classes.java.util.concurrent.atomic   private final AtomicLong sequenceNumber
 java.base.share.classes.java.util.concurrent.atomic     = new AtomicLong(17);
 java.base.share.classes.java.util.concurrent.atomic   public long next() {
 java.base.share.classes.java.util.concurrent.atomic     return sequenceNumber.getAndIncrement();
 java.base.share.classes.java.util.concurrent.atomic   }
 java.base.share.classes.java.util.concurrent.atomic }}</pre>
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>Arbitrary transformations of the contained value are provided both
 java.base.share.classes.java.util.concurrent.atomic by low-level read-modify-write operations such as {@code compareAndSet}
 java.base.share.classes.java.util.concurrent.atomic and by higher-level methods such as {@code getAndUpdate}.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>These classes are not general purpose replacements for {@code
 java.base.share.classes.java.util.concurrent.atomic java.lang.Integer} and related classes.  They do <em>not</em>
 java.base.share.classes.java.util.concurrent.atomic define methods such as {@code equals}, {@code hashCode} and {@code
 java.base.share.classes.java.util.concurrent.atomic compareTo}.  Because atomic variables are expected to be mutated,
 java.base.share.classes.java.util.concurrent.atomic they are poor choices for hash table keys.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>The
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicIntegerArray},
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicLongArray}, and
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicReferenceArray} classes
 java.base.share.classes.java.util.concurrent.atomic further extend atomic operation support to arrays of these types.
 java.base.share.classes.java.util.concurrent.atomic These classes are also notable in providing {@code volatile} access
 java.base.share.classes.java.util.concurrent.atomic semantics for their array elements.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>In addition to classes representing single values and arrays,
 java.base.share.classes.java.util.concurrent.atomic this package contains <em>Updater</em> classes that can be used to
 java.base.share.classes.java.util.concurrent.atomic obtain {@code compareAndSet} and related operations on any selected
 java.base.share.classes.java.util.concurrent.atomic {@code volatile} field of any selected class. These classes
 java.base.share.classes.java.util.concurrent.atomic predate the introduction of {@link
 java.base.share.classes.java.util.concurrent.atomic java.lang.invoke.VarHandle}, and are of more limited use.
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicReferenceFieldUpdater},
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicIntegerFieldUpdater}, and
 java.base.share.classes.java.util.concurrent.atomic {@link java.util.concurrent.atomic.AtomicLongFieldUpdater} are
 java.base.share.classes.java.util.concurrent.atomic reflection-based utilities that provide access to the associated
 java.base.share.classes.java.util.concurrent.atomic field types.  These are mainly of use in atomic data structures in
 java.base.share.classes.java.util.concurrent.atomic which several {@code volatile} fields of the same node (for
 java.base.share.classes.java.util.concurrent.atomic example, the links of a tree node) are independently subject to
 java.base.share.classes.java.util.concurrent.atomic atomic updates.  These classes enable greater flexibility in how
 java.base.share.classes.java.util.concurrent.atomic and when to use atomic updates, at the expense of more awkward
 java.base.share.classes.java.util.concurrent.atomic reflection-based setup, less convenient usage, and weaker
 java.base.share.classes.java.util.concurrent.atomic guarantees.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic <p>The {@link java.util.concurrent.atomic.AtomicMarkableReference}
 java.base.share.classes.java.util.concurrent.atomic class associates a single boolean with a reference.  For example, this
 java.base.share.classes.java.util.concurrent.atomic bit might be used inside a data structure to mean that the object
 java.base.share.classes.java.util.concurrent.atomic being referenced has logically been deleted.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic The {@link java.util.concurrent.atomic.AtomicStampedReference}
 java.base.share.classes.java.util.concurrent.atomic class associates an integer value with a reference.  This may be
 java.base.share.classes.java.util.concurrent.atomic used for example, to represent version numbers corresponding to
 java.base.share.classes.java.util.concurrent.atomic series of updates.
 java.base.share.classes.java.util.concurrent.atomic
 java.base.share.classes.java.util.concurrent.atomic @since 1.5
 java.base.share.classes.java.util.concurrent.atomic/
package java.util.concurrent.atomic;
