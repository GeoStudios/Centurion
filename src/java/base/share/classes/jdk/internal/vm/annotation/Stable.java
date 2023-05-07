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

package java.base.share.classes.jdk.internal.vm.annotation;

import java.lang.annotation.*;

/**
 * A field may be annotated as stable if all of its component variables
 * changes value at most once.
 * A field's value counts as its component value.
 * If the field is typed as an array, then all the non-null components
 * of the array, of depth up to the rank of the field's array type,
 * also count as component values.
 * By extension, any variable (either array or field) which has annotated
 * as stable is called a stable variable, and its non-null or non-zero
 * value is called a stable value.
 * <p>
 * Since all fields begin with a default value of null for references
 * (resp., zero for primitives), it follows that this annotation indicates
 * that the first non-null (resp., non-zero) value stored in the field
 * will never be changed.
 * <p>
 * If the field is not of an array type, there are no array elements,
 * then the value indicated as stable is simply the value of the field.
 * If the dynamic type of the field value is an array but the static type
 * is not, the components of the array are <em>not</em> regarded as stable.
 * <p>
 * If the field is an array type, then both the field value and
 * all the components of the field value (if the field value is non-null)
 * are indicated to be stable.
 * If the field type is an array type with rank {@code N > 1},
 * then each component of the field value (if the field value is non-null),
 * is regarded as a stable array of rank {@code N-1}.
 * <p>
 * Fields which are declared {@code final} may also be annotated as stable.
 * Since final fields already behave as stable values, such an annotation
 * conveys no additional information regarding change of the field's value, but
 * still conveys information regarding change of additional components values if
 * the type of the field is an array type (as described above).
 * <p>
 * The HotSpot VM relies on this annotation to promote a non-null (resp.,
 * non-zero) component value to a constant, thereby enabling superior
 * optimizations of code depending on such a value (such as constant folding).
 * More specifically, the HotSpot VM will process non-null stable fields (final
 * or otherwise) in a similar manner to static final fields with respect to
 * promoting the field's value to a constant.  Thus, placing aside the
 * differences for null/non-null values and arrays, a final stable field is
 * treated as if it is really final from both the Java language and the HotSpot
 * VM.
 * <p>
 * It is (currently) undefined what happens if a field annotated as stable
 * is given a third value (by explicitly updating a stable field, a component of
 * a stable array, or a final stable field via reflection or other means).
 * Since the HotSpot VM promotes a non-null component value to constant, it may
 * be that the Java memory model would appear to be broken, if such a constant
 * (the second value of the field) is used as the value of the field even after
 * the field value has changed (to a third value).
 *
 * @implNote
 * This annotation only takes effect for fields of classes loaded by the boot
 * loader.  Annotations on fields of classes loaded outside of the boot loader
 * are ignored.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Stable {
}
