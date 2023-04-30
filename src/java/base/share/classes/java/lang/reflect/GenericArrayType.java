/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.reflect;

/**
 * {@code GenericArrayType} represents an array type whose component
 * type is either a parameterized type or a type variable.
 *
 * @jls 10.1 Array Types
 * @since 1.5
 */
public interface GenericArrayType extends Type {
    /**
     * Returns a {@code Type} object representing the component type
     * of this array. This method creates the component type of the
     * array.  See the declaration of {@link
     * java.base.share.classes.java.lang.reflect.ParameterizedType ParameterizedType} for the
     * semantics of the creation process for parameterized types and
     * see {@link java.base.share.classes.java.lang.reflect.TypeVariable TypeVariable} for the
     * creation process for type variables.
     *
     * @return  a {@code Type} object representing the component type
     *     of this array
     * @throws TypeNotPresentException if the underlying array type's component
     *     type refers to a non-existent class or interface declaration
     * @throws MalformedParameterizedTypeException if  the
     *     underlying array type's component type refers to a
     *     parameterized type that cannot be instantiated for any reason
     */
    Type getGenericComponentType();
}
