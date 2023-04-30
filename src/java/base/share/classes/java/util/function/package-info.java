/java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.util.function/

/java.base.share.classes.java.util.functionjava.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <em>Functional interfaces</em> provide target types for lambda expressions
 java.base.share.classes.java.util.function and method references.  Each functional interface has a single abstract
 java.base.share.classes.java.util.function method, called the <em>functional method</em> for that functional interface,
 java.base.share.classes.java.util.function to which the lambda expression's parameter and return types are matched or
 java.base.share.classes.java.util.function adapted.  Functional interfaces can provide a target type in multiple
 java.base.share.classes.java.util.function contexts, such as assignment context, method invocation, or cast context:
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <pre>{@code
 java.base.share.classes.java.util.function     // Assignment context
 java.base.share.classes.java.util.function     Predicate<String> p = String::isEmpty;
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     // Method invocation context
 java.base.share.classes.java.util.function     stream.filter(e -> e.getSize() > 10)...
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     // Cast context
 java.base.share.classes.java.util.function     stream.map((ToIntFunction) e -> e.getSize())...
 java.base.share.classes.java.util.function }</pre>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <p>The interfaces in this package are general purpose functional interfaces
 java.base.share.classes.java.util.function used by the JDK, and are available to be used by user code as well.  While
 java.base.share.classes.java.util.function they do not identify a complete set of function shapes to which lambda
 java.base.share.classes.java.util.function expressions might be adapted, they provide enough to cover common
 java.base.share.classes.java.util.function requirements. Other functional interfaces provided for specific purposes,
 java.base.share.classes.java.util.function such as {@link java.io.FileFilter}, are defined in the packages where they
 java.base.share.classes.java.util.function are used.
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <p>The interfaces in this package are annotated with
 java.base.share.classes.java.util.function {@link java.lang.FunctionalInterface}. This annotation is not a requirement
 java.base.share.classes.java.util.function for the compiler to recognize an interface as a functional interface, but
 java.base.share.classes.java.util.function merely an aid to capture design intent and enlist the help of the compiler in
 java.base.share.classes.java.util.function identifying accidental violations of design intent.
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <p>Functional interfaces often represent abstract concepts like functions,
 java.base.share.classes.java.util.function actions, or predicates.  In documenting functional interfaces, or referring
 java.base.share.classes.java.util.function to variables typed as functional interfaces, it is common to refer directly
 java.base.share.classes.java.util.function to those abstract concepts, for example using "this function" instead of
 java.base.share.classes.java.util.function "the function represented by this object".  When an API method is said to
 java.base.share.classes.java.util.function accept or return a functional interface in this manner, such as "applies the
 java.base.share.classes.java.util.function provided function to...", this is understood to mean a <i>non-null</i>
 java.base.share.classes.java.util.function reference to an object implementing the appropriate functional interface,
 java.base.share.classes.java.util.function unless potential nullity is explicitly specified.
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <p>The functional interfaces in this package follow an extensible naming
 java.base.share.classes.java.util.function convention, as follows:
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function <ul>
 java.base.share.classes.java.util.function     <li>There are several basic function shapes, including
 java.base.share.classes.java.util.function     {@link java.util.function.Function} (unary function from {@code T} to {@code R}),
 java.base.share.classes.java.util.function     {@link java.util.function.Consumer} (unary function from {@code T} to {@code void}),
 java.base.share.classes.java.util.function     {@link java.util.function.Predicate} (unary function from {@code T} to {@code boolean}),
 java.base.share.classes.java.util.function     and {@link java.util.function.Supplier} (nullary function to {@code R}).
 java.base.share.classes.java.util.function     </li>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     <li>Function shapes have a natural arity based on how they are most
 java.base.share.classes.java.util.function     commonly used.  The basic shapes can be modified by an arity prefix to
 java.base.share.classes.java.util.function     indicate a different arity, such as
 java.base.share.classes.java.util.function     {@link java.util.function.BiFunction} (binary function from {@code T} and
 java.base.share.classes.java.util.function     {@code U} to {@code R}).
 java.base.share.classes.java.util.function     </li>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     <li>There are additional derived function shapes which extend the basic
 java.base.share.classes.java.util.function     function shapes, including {@link java.util.function.UnaryOperator}
 java.base.share.classes.java.util.function     (extends {@code Function}) and {@link java.util.function.BinaryOperator}
 java.base.share.classes.java.util.function     (extends {@code BiFunction}).
 java.base.share.classes.java.util.function     </li>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     <li>Type parameters of functional interfaces can be specialized to
 java.base.share.classes.java.util.function     primitives with additional type prefixes.  To specialize the return type
 java.base.share.classes.java.util.function     for a type that has both generic return type and generic arguments, we
 java.base.share.classes.java.util.function     prefix {@code ToXxx}, as in {@link java.util.function.ToIntFunction}.
 java.base.share.classes.java.util.function     Otherwise, type arguments are specialized left-to-right, as in
 java.base.share.classes.java.util.function     {@link java.util.function.DoubleConsumer}
 java.base.share.classes.java.util.function     or {@link java.util.function.ObjIntConsumer}.
 java.base.share.classes.java.util.function     (The type prefix {@code Obj} is used to indicate that we don't want to
 java.base.share.classes.java.util.function     specialize this parameter, but want to move on to the next parameter,
 java.base.share.classes.java.util.function     as in {@link java.util.function.ObjIntConsumer}.)
 java.base.share.classes.java.util.function     These schemes can be combined, as in {@code IntToDoubleFunction}.
 java.base.share.classes.java.util.function     </li>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function     <li>If there are specialization prefixes for all arguments, the arity
 java.base.share.classes.java.util.function     prefix may be left out (as in {@link java.util.function.ObjIntConsumer}).
 java.base.share.classes.java.util.function     </li>
 java.base.share.classes.java.util.function </ul>
 java.base.share.classes.java.util.function
 java.base.share.classes.java.util.function @see java.lang.FunctionalInterface
 java.base.share.classes.java.util.function @since 1.8
 java.base.share.classes.java.util.function/
package java.util.function;
