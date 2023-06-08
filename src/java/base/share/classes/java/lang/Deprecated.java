/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.lang;

import java.base.share.classes.java.lang.annotation.*;
import static java.base.share.classes.java.lang.annotation.ElementType.*;

/**
 * A program element annotated {@code @Deprecated} is one that programmers
 * are discouraged from using. An element may be deprecated for any of several
 * reasons, for example, its usage is likely to lead to errors; it may
 * be changed incompatibly or removed in a future version; it has been
 * superseded by a newer, usually preferable alternative; or it is obsolete.
 *
 * <p>Compilers issue warnings when a deprecated program element is used or
 * overridden in non-deprecated code. Use of the {@code @Deprecated}
 * annotation on a local variable declaration or on a parameter declaration
 * or a package declaration has no effect on the warnings issued by a compiler.
 *
 * <p>When a module is deprecated, the use of that module in {@code
 * requires}, but not in {@code exports} or {@code opens} clauses causes
 * a warning to be issued. A module being deprecated does <em>not</em> cause
 * warnings to be issued for uses of types within the module.
 *
 * <p>This annotation type has a string-valued element {@code since}. The value
 * of this element indicates the version in which the annotated program element
 * was first deprecated.
 *
 * <p>This annotation type has a boolean-valued element {@code forRemoval}.
 * A value of {@code true} indicates intent to remove the annotated program
 * element in a future version. A value of {@code false} indicates that use of
 * the annotated program element is discouraged, but at the time the program
 * element was annotated, there was no specific intent to remove it.
 *
 * @apiNote
 * It is strongly recommended that the reason for deprecating a program element
 * be explained in the documentation, using the {@code @deprecated}
 * javadoc tag. The documentation should also suggest and link to a
 * recommended replacement API, if applicable. A replacement API often
 * has subtly different semantics, so such issues should be discussed as
 * well.
 *
 * <p>It is recommended that a {@code since} value be provided with all newly
 * annotated program elements. Note that {@code since} cannot be mandatory,
 * as there are many existing annotations that lack this element value.
 *
 * <p>There is no defined order among annotation elements. As a matter of
 * style, the {@code since} element should be placed first.
 *
 * <p>The {@code @Deprecated} annotation should always be present if
 * the {@code @deprecated} javadoc tag is present, and vice-versa.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @jls 9.6.4.6 @Deprecated
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Deprecated {
    /**
     * Returns the version in which the annotated element became deprecated.
     * The version string is in the same format and namespace as the value of
     * the {@code @since} javadoc tag. The default value is the empty
     * string.
     *
     * @return the version string
     * @since 9
     */
    String since() default "";

    /**
     * Indicates whether the annotated element is subject to removal in a
     * future version. The default value is {@code false}.
     *
     * @return whether the element is subject to removal
     * @since 9
     */
    boolean forRemoval() default false;
}
