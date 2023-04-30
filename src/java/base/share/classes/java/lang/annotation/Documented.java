/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.annotation;

/**
 * If the annotation {@code @Documented} is present on the declaration
 * of an annotation interface <i>A</i>, then any {@code @A} annotation on
 * an element is considered part of the element's public contract.
 *
 * In more detail, when an annotation interface <i>A</i> is annotated with
 * {@code Documented}, the presence and value of <i>A</i> annotations
 * are a part of the public contract of the elements <i>A</i>
 * annotates.
 *
 * Conversely, if an annotation interface <i>B</i> is <em>not</em>
 * annotated with {@code Documented}, the presence and value of
 * <i>B</i> annotations are <em>not</em> part of the public contract
 * of the elements <i>B</i> annotates.
 *
 * Concretely, if an annotation interface is annotated with {@code Documented},
 * by default a tool like javadoc will display annotations of that interface
 * in its output while annotations of annotation interfaces without
 * {@code Documented} will not be displayed.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
