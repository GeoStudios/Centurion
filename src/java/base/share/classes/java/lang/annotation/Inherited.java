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

package java.base.share.classes.java.lang.annotation;

/**
 * Indicates that an annotation interface is automatically inherited.  If
 * an Inherited meta-annotation is present on an annotation interface
 * declaration, and the user queries the annotation interface on a class
 * declaration, and the class declaration has no annotation for this interface,
 * then the class's superclass will automatically be queried for the
 * annotation interface.  This process will be repeated until an annotation for
 * this interface is found, or the top of the class hierarchy (Object)
 * is reached.  If no superclass has an annotation for this interface, then
 * the query will indicate that the class in question has no such annotation.
 *
 * <p>Note that this meta-annotation interface has no effect if the annotated
 * interface is used to annotate anything other than a class.  Note also
 * that this meta-annotation only causes annotations to be inherited
 * from superclasses; annotations on implemented interfaces have no
 * effect.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 * @jls 9.6.4.3 @Inherited
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}
