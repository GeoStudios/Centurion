/java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.util.concurrent/

/java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent This file is available under and governed by the GNU General Public
 java.base.share.classes.java.util.concurrent License version 2 only, as published by the Free Software Foundation.
 java.base.share.classes.java.util.concurrent However, the following notice accompanied the original version of this
 java.base.share.classes.java.util.concurrent file:
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Written by Doug Lea with assistance from members of JCP JSR-166
 java.base.share.classes.java.util.concurrent Expert Group and released to the public domain, as explained at
 java.base.share.classes.java.util.concurrent http://creativecommons.org/publicdomain/zero/1.0/
 java.base.share.classes.java.util.concurrent/

/java.base.share.classes.java.util.concurrentjava.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Utility classes commonly useful in concurrent programming.  This
 java.base.share.classes.java.util.concurrent package includes a few small standardized extensible frameworks, as
 java.base.share.classes.java.util.concurrent well as some classes that provide useful functionality and are
 java.base.share.classes.java.util.concurrent otherwise tedious or difficult to implement.  Here are brief
 java.base.share.classes.java.util.concurrent descriptions of the main components.  See also the
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.locks} and
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.atomic} packages.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2>Executors</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <b>Interfaces.</b>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.Executor} is a simple standardized
 java.base.share.classes.java.util.concurrent interface for defining custom thread-like subsystems, including
 java.base.share.classes.java.util.concurrent thread pools, asynchronous I/O, and lightweight task frameworks.
 java.base.share.classes.java.util.concurrent Depending on which concrete Executor class is being used, tasks may
 java.base.share.classes.java.util.concurrent execute in a newly created thread, an existing task-execution thread,
 java.base.share.classes.java.util.concurrent or the thread calling {@link java.util.concurrent.Executor#execute
 java.base.share.classes.java.util.concurrent execute}, and may execute sequentially or concurrently.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ExecutorService} provides a more
 java.base.share.classes.java.util.concurrent complete asynchronous task execution framework.  An
 java.base.share.classes.java.util.concurrent ExecutorService manages queuing and scheduling of tasks,
 java.base.share.classes.java.util.concurrent and allows controlled shutdown.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent The {@link java.util.concurrent.ScheduledExecutorService}
 java.base.share.classes.java.util.concurrent subinterface and associated interfaces add support for
 java.base.share.classes.java.util.concurrent delayed and periodic task execution.  ExecutorServices
 java.base.share.classes.java.util.concurrent provide methods arranging asynchronous execution of any
 java.base.share.classes.java.util.concurrent function expressed as {@link java.util.concurrent.Callable},
 java.base.share.classes.java.util.concurrent the result-bearing analog of {@link java.lang.Runnable}.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent A {@link java.util.concurrent.Future} returns the results of
 java.base.share.classes.java.util.concurrent a function, allows determination of whether execution has
 java.base.share.classes.java.util.concurrent completed, and provides a means to cancel execution.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent A {@link java.util.concurrent.RunnableFuture} is a {@code Future}
 java.base.share.classes.java.util.concurrent that possesses a {@code run} method that upon execution,
 java.base.share.classes.java.util.concurrent sets its results.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <b>Implementations.</b>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Classes {@link java.util.concurrent.ThreadPoolExecutor} and
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ScheduledThreadPoolExecutor}
 java.base.share.classes.java.util.concurrent provide tunable, flexible thread pools.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent The {@link java.util.concurrent.Executors} class provides
 java.base.share.classes.java.util.concurrent factory methods for the most common kinds and configurations
 java.base.share.classes.java.util.concurrent of Executors, as well as a few utility methods for using
 java.base.share.classes.java.util.concurrent them.  Other utilities based on {@code Executors} include the
 java.base.share.classes.java.util.concurrent concrete class {@link java.util.concurrent.FutureTask}
 java.base.share.classes.java.util.concurrent providing a common extensible implementation of Futures, and
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ExecutorCompletionService}, that
 java.base.share.classes.java.util.concurrent assists in coordinating the processing of groups of
 java.base.share.classes.java.util.concurrent asynchronous tasks.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>Class {@link java.util.concurrent.ForkJoinPool} provides an
 java.base.share.classes.java.util.concurrent Executor primarily designed for processing instances of {@link
 java.base.share.classes.java.util.concurrent java.util.concurrent.ForkJoinTask} and its subclasses.  These
 java.base.share.classes.java.util.concurrent classes employ a work-stealing scheduler that attains high
 java.base.share.classes.java.util.concurrent throughput for tasks conforming to restrictions that often hold in
 java.base.share.classes.java.util.concurrent computation-intensive parallel processing.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2>Queues</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent The {@link java.util.concurrent.ConcurrentLinkedQueue} class
 java.base.share.classes.java.util.concurrent supplies an efficient scalable thread-safe non-blocking FIFO queue.
 java.base.share.classes.java.util.concurrent The {@link java.util.concurrent.ConcurrentLinkedDeque} class is
 java.base.share.classes.java.util.concurrent similar, but additionally supports the {@link java.util.Deque}
 java.base.share.classes.java.util.concurrent interface.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>Five implementations in {@code java.util.concurrent} support
 java.base.share.classes.java.util.concurrent the extended {@link java.util.concurrent.BlockingQueue}
 java.base.share.classes.java.util.concurrent interface, that defines blocking versions of put and take:
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.LinkedBlockingQueue},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ArrayBlockingQueue},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.SynchronousQueue},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.PriorityBlockingQueue}, and
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.DelayQueue}.
 java.base.share.classes.java.util.concurrent The different classes cover the most common usage contexts
 java.base.share.classes.java.util.concurrent for producer-consumer, messaging, parallel tasking, and
 java.base.share.classes.java.util.concurrent related concurrent designs.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>Extended interface {@link java.util.concurrent.TransferQueue},
 java.base.share.classes.java.util.concurrent and implementation {@link java.util.concurrent.LinkedTransferQueue}
 java.base.share.classes.java.util.concurrent introduce a synchronous {@code transfer} method (along with related
 java.base.share.classes.java.util.concurrent features) in which a producer may optionally block awaiting its
 java.base.share.classes.java.util.concurrent consumer.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>The {@link java.util.concurrent.BlockingDeque} interface
 java.base.share.classes.java.util.concurrent extends {@code BlockingQueue} to support both FIFO and LIFO
 java.base.share.classes.java.util.concurrent (stack-based) operations.
 java.base.share.classes.java.util.concurrent Class {@link java.util.concurrent.LinkedBlockingDeque}
 java.base.share.classes.java.util.concurrent provides an implementation.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2>Timing</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent The {@link java.util.concurrent.TimeUnit} class provides
 java.base.share.classes.java.util.concurrent multiple granularities (including nanoseconds) for
 java.base.share.classes.java.util.concurrent specifying and controlling time-out based operations.  Most
 java.base.share.classes.java.util.concurrent classes in the package contain operations based on time-outs
 java.base.share.classes.java.util.concurrent in addition to indefinite waits.  In all cases that
 java.base.share.classes.java.util.concurrent time-outs are used, the time-out specifies the minimum time
 java.base.share.classes.java.util.concurrent that the method should wait before indicating that it
 java.base.share.classes.java.util.concurrent timed-out.  Implementations make a &quot;best effort&quot;
 java.base.share.classes.java.util.concurrent to detect time-outs as soon as possible after they occur.
 java.base.share.classes.java.util.concurrent However, an indefinite amount of time may elapse between a
 java.base.share.classes.java.util.concurrent time-out being detected and a thread actually executing
 java.base.share.classes.java.util.concurrent again after that time-out.  All methods that accept timeout
 java.base.share.classes.java.util.concurrent parameters treat values less than or equal to zero to mean
 java.base.share.classes.java.util.concurrent not to wait at all.  To wait "forever", you can use a value
 java.base.share.classes.java.util.concurrent of {@code Long.MAX_VALUE}.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2>Synchronizers</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Five classes aid common special-purpose synchronization idioms.
 java.base.share.classes.java.util.concurrent <ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <li>{@link java.util.concurrent.Semaphore} is a classic concurrency tool.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <li>{@link java.util.concurrent.CountDownLatch} is a very simple yet
 java.base.share.classes.java.util.concurrent very common utility for blocking until a given number of signals,
 java.base.share.classes.java.util.concurrent events, or conditions hold.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <li>A {@link java.util.concurrent.CyclicBarrier} is a resettable
 java.base.share.classes.java.util.concurrent multiway synchronization point useful in some styles of parallel
 java.base.share.classes.java.util.concurrent programming.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <li>A {@link java.util.concurrent.Phaser} provides
 java.base.share.classes.java.util.concurrent a more flexible form of barrier that may be used to control phased
 java.base.share.classes.java.util.concurrent computation among multiple threads.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <li>An {@link java.util.concurrent.Exchanger} allows two threads to
 java.base.share.classes.java.util.concurrent exchange objects at a rendezvous point, and is useful in several
 java.base.share.classes.java.util.concurrent pipeline designs.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent </ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2>Concurrent Collections</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Besides Queues, this package supplies Collection implementations
 java.base.share.classes.java.util.concurrent designed for use in multithreaded contexts:
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ConcurrentHashMap},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ConcurrentSkipListMap},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.ConcurrentSkipListSet},
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.CopyOnWriteArrayList}, and
 java.base.share.classes.java.util.concurrent {@link java.util.concurrent.CopyOnWriteArraySet}.
 java.base.share.classes.java.util.concurrent When many threads are expected to access a given collection, a
 java.base.share.classes.java.util.concurrent {@code ConcurrentHashMap} is normally preferable to a synchronized
 java.base.share.classes.java.util.concurrent {@code HashMap}, and a {@code ConcurrentSkipListMap} is normally
 java.base.share.classes.java.util.concurrent preferable to a synchronized {@code TreeMap}.
 java.base.share.classes.java.util.concurrent A {@code CopyOnWriteArrayList} is preferable to a synchronized
 java.base.share.classes.java.util.concurrent {@code ArrayList} when the expected number of reads and traversals
 java.base.share.classes.java.util.concurrent greatly outnumber the number of updates to a list.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p>The "Concurrent" prefix used with some classes in this package
 java.base.share.classes.java.util.concurrent is a shorthand indicating several differences from similar
 java.base.share.classes.java.util.concurrent "synchronized" classes.  For example {@code java.util.Hashtable} and
 java.base.share.classes.java.util.concurrent {@code Collections.synchronizedMap(new HashMap())} are
 java.base.share.classes.java.util.concurrent synchronized.  But {@link
 java.base.share.classes.java.util.concurrent java.util.concurrent.ConcurrentHashMap} is "concurrent".  A
 java.base.share.classes.java.util.concurrent concurrent collection is thread-safe, but not governed by a
 java.base.share.classes.java.util.concurrent single exclusion lock.  In the particular case of
 java.base.share.classes.java.util.concurrent ConcurrentHashMap, it safely permits any number of
 java.base.share.classes.java.util.concurrent concurrent reads as well as a large number of concurrent
 java.base.share.classes.java.util.concurrent writes.  "Synchronized" classes can be useful when you need
 java.base.share.classes.java.util.concurrent to prevent all access to a collection via a single lock, at
 java.base.share.classes.java.util.concurrent the expense of poorer scalability.  In other cases in which
 java.base.share.classes.java.util.concurrent multiple threads are expected to access a common collection,
 java.base.share.classes.java.util.concurrent "concurrent" versions are normally preferable.  And
 java.base.share.classes.java.util.concurrent unsynchronized collections are preferable when either
 java.base.share.classes.java.util.concurrent collections are unshared, or are accessible only when
 java.base.share.classes.java.util.concurrent holding other locks.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <p id="Weakly">Most concurrent Collection implementations
 java.base.share.classes.java.util.concurrent (including most Queues) also differ from the usual {@code java.util}
 java.base.share.classes.java.util.concurrent conventions in that their {@linkplain java.util.Iterator Iterators}
 java.base.share.classes.java.util.concurrent and {@linkplain java.util.Spliterator Spliterators} provide
 java.base.share.classes.java.util.concurrent <em>weakly consistent</em> rather than fast-fail traversal:
 java.base.share.classes.java.util.concurrent <ul>
 java.base.share.classes.java.util.concurrent <li>they may proceed concurrently with other operations
 java.base.share.classes.java.util.concurrent <li>they will never throw {@link java.util.ConcurrentModificationException
 java.base.share.classes.java.util.concurrent ConcurrentModificationException}
 java.base.share.classes.java.util.concurrent <li>they are guaranteed to traverse elements as they existed upon
 java.base.share.classes.java.util.concurrent construction exactly once, and may (but are not guaranteed to)
 java.base.share.classes.java.util.concurrent reflect any modifications subsequent to construction.
 java.base.share.classes.java.util.concurrent </ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <h2 id="MemoryVisibility">Memory Consistency Properties</h2>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent Chapter 17 of
 java.base.share.classes.java.util.concurrent <cite>The Java Language Specification</cite> defines the
 java.base.share.classes.java.util.concurrent <i>happens-before</i> relation on memory operations such as reads and
 java.base.share.classes.java.util.concurrent writes of shared variables.  The results of a write by one thread are
 java.base.share.classes.java.util.concurrent guaranteed to be visible to a read by another thread only if the write
 java.base.share.classes.java.util.concurrent operation <i>happens-before</i> the read operation.  The
 java.base.share.classes.java.util.concurrent {@code synchronized} and {@code volatile} constructs, as well as the
 java.base.share.classes.java.util.concurrent {@code Thread.start()} and {@code Thread.join()} methods, can form
 java.base.share.classes.java.util.concurrent <i>happens-before</i> relationships.  In particular:
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <ul>
 java.base.share.classes.java.util.concurrent   <li>Each action in a thread <i>happens-before</i> every action in that
 java.base.share.classes.java.util.concurrent   thread that comes later in the program's order.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>An unlock ({@code synchronized} block or method exit) of a
 java.base.share.classes.java.util.concurrent   monitor <i>happens-before</i> every subsequent lock ({@code synchronized}
 java.base.share.classes.java.util.concurrent   block or method entry) of that same monitor.  And because
 java.base.share.classes.java.util.concurrent   the <i>happens-before</i> relation is transitive, all actions
 java.base.share.classes.java.util.concurrent   of a thread prior to unlocking <i>happen-before</i> all actions
 java.base.share.classes.java.util.concurrent   subsequent to any thread locking that monitor.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>A write to a {@code volatile} field <i>happens-before</i> every
 java.base.share.classes.java.util.concurrent   subsequent read of that same field.  Writes and reads of
 java.base.share.classes.java.util.concurrent   {@code volatile} fields have similar memory consistency effects
 java.base.share.classes.java.util.concurrent   as entering and exiting monitors, but do <em>not</em> entail
 java.base.share.classes.java.util.concurrent   mutual exclusion locking.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>A call to {@code start} on a thread <i>happens-before</i> any
 java.base.share.classes.java.util.concurrent   action in the started thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>All actions in a thread <i>happen-before</i> any other thread
 java.base.share.classes.java.util.concurrent   successfully returns from a {@code join} on that thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent </ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent The methods of all classes in {@code java.util.concurrent} and its
 java.base.share.classes.java.util.concurrent subpackages extend these guarantees to higher-level
 java.base.share.classes.java.util.concurrent synchronization.  In particular:
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent <ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>Actions in a thread prior to placing an object into any concurrent
 java.base.share.classes.java.util.concurrent   collection <i>happen-before</i> actions subsequent to the access or
 java.base.share.classes.java.util.concurrent   removal of that element from the collection in another thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>Actions in a thread prior to the submission of a {@code Runnable}
 java.base.share.classes.java.util.concurrent   to an {@code Executor} <i>happen-before</i> its execution begins.
 java.base.share.classes.java.util.concurrent   Similarly for {@code Callables} submitted to an {@code ExecutorService}.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>Actions taken by the asynchronous computation represented by a
 java.base.share.classes.java.util.concurrent   {@code Future} <i>happen-before</i> actions subsequent to the
 java.base.share.classes.java.util.concurrent   retrieval of the result via {@code Future.get()} in another thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>Actions prior to "releasing" synchronizer methods such as
 java.base.share.classes.java.util.concurrent   {@code Lock.unlock}, {@code Semaphore.release}, and
 java.base.share.classes.java.util.concurrent   {@code CountDownLatch.countDown} <i>happen-before</i> actions
 java.base.share.classes.java.util.concurrent   subsequent to a successful "acquiring" method such as
 java.base.share.classes.java.util.concurrent   {@code Lock.lock}, {@code Semaphore.acquire},
 java.base.share.classes.java.util.concurrent   {@code Condition.await}, and {@code CountDownLatch.await} on the
 java.base.share.classes.java.util.concurrent   same synchronizer object in another thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>For each pair of threads that successfully exchange objects via
 java.base.share.classes.java.util.concurrent   an {@code Exchanger}, actions prior to the {@code exchange()}
 java.base.share.classes.java.util.concurrent   in each thread <i>happen-before</i> those subsequent to the
 java.base.share.classes.java.util.concurrent   corresponding {@code exchange()} in another thread.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent   <li>Actions prior to calling {@code CyclicBarrier.await} and
 java.base.share.classes.java.util.concurrent   {@code Phaser.awaitAdvance} (as well as its variants)
 java.base.share.classes.java.util.concurrent   <i>happen-before</i> actions performed by the barrier action, and
 java.base.share.classes.java.util.concurrent   actions performed by the barrier action <i>happen-before</i> actions
 java.base.share.classes.java.util.concurrent   subsequent to a successful return from the corresponding {@code await}
 java.base.share.classes.java.util.concurrent   in other threads.
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent </ul>
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent @jls 17.4.5 Happens-before Order
 java.base.share.classes.java.util.concurrent
 java.base.share.classes.java.util.concurrent @since 1.5
 java.base.share.classes.java.util.concurrent/
package java.util.concurrent;
