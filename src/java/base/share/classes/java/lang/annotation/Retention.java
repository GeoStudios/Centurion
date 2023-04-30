/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.annotation;

/**
 * Indicates how long annotations with the annotated interface are to
 * be retained.  If no Retention annotation is present on
 * an annotation interface declaration, the retention policy defaults to
 * {@code RetentionPolicy.CLASS}.
 *
 * <p>A Retention meta-annotation has effect only if the
 * meta-annotated interface is used directly for annotation.  It has no
 * effect if the meta-annotated interface is used as a member interface in
 * another annotation interface.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @jls 9.6.4.2 @Retention
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy value();
}
