/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.annotation;

/**
 * The annotation interface {@code java.base.share.classes.java.lang.annotation.Repeatable} is
 * used to indicate that the annotation interface whose declaration it
 * (meta-)annotates is <em>repeatable</em>. The value of
 * {@code @Repeatable} indicates the <em>containing annotation
 * interface</em> for the repeatable annotation interface.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @jls 9.6.3 Repeatable Annotation Interfaces
 * @jls 9.7.5 Multiple Annotations of the Same Interface
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Repeatable {
    /**
     * Indicates the <em>containing annotation interface</em> for the
     * repeatable annotation interface.
     * @return the containing annotation interface
     */
    Class<? extends Annotation> value();
}
