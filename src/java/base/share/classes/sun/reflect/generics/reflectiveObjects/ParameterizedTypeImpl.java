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

package java.base.share.classes.sun.reflect.generics.reflectiveObjects;

import java.base.share.classes.sun.reflect.generics.tree.FieldTypeSignature;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.Objects;

/** Implementing class for ParameterizedType interface. 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
*/

public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Class<?>  rawType;
    private final Type   ownerType;

    private ParameterizedTypeImpl(Class<?> rawType,
                                  Type[] actualTypeArguments,
                                  Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType             = rawType;
        this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
        validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        TypeVariable<?>[] formals = rawType.getTypeParameters();
        // check correct arity of actual type args
        if (formals.length != actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException(String.format("Mismatch of count of " +
                                                                        "formal and actual type " +
                                                                        "arguments in constructor " +
                                                                        "of %s: %d formal argument(s) "+
                                                                        "%d actual argument(s)",
                                                                        rawType.getName(),
                                                                        formals.length,
                                                                        actualTypeArguments.length));
        }
        for (int i = 0; i < actualTypeArguments.length; i++) {
            // check actuals against formals' bounds
        }
    }

    /**
     * Static factory. Given a (generic) class, actual type arguments
     * and an owner type, creates a parameterized type.
     * This class can be instantiated with a raw type that does not
     * represent a generic type, provided the list of actual type
     * arguments is empty.
     * If the ownerType argument is null, the declaring class of the
     * raw type is used as the owner type.
     * <p> This method throws a MalformedParameterizedTypeException
     * under the following circumstances:
     * If the number of actual type arguments (i.e., the size of the
     * array {@code typeArgs}) does not correspond to the number of
     * formal type arguments.
     * If any of the actual type arguments is not an instance of the
     * bounds on the corresponding formal.
     * @param rawType the Class representing the generic type declaration being
     * instantiated
     * @param actualTypeArguments a (possibly empty) array of types
     * representing the actual type arguments to the parameterized type
     * @param ownerType the enclosing type, if known.
     * @return An instance of {@code ParameterizedType}
     * @throws MalformedParameterizedTypeException if the instantiation
     * is invalid
     */
    public static ParameterizedTypeImpl make(Class<?> rawType,
                                             Type[] actualTypeArguments,
                                             Type ownerType) {
        return new ParameterizedTypeImpl(rawType, actualTypeArguments,
                                         ownerType);
    }


    /**
     * Returns an array of {@code Type} objects representing the actual type
     * arguments to this type.
     *
     * <p>Note that in some cases, the returned array be empty. This can occur
     * if this type represents a non-parameterized type nested within
     * a parameterized type.
     *
     * @return an array of {@code Type} objects representing the actual type
     *     arguments to this type
     * @throws TypeNotPresentException if any of the
     *     actual type arguments refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if any of the
     *     actual type parameters refer to a parameterized type that cannot
     *     be instantiated for any reason
     * @since 1.5
     */
    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }

    /**
     * Returns the {@code Type} object representing the class or interface
     * that declared this type.
     *
     * @return the {@code Type} object representing the class or interface
     *     that declared this type
     */
    public Class<?> getRawType() {
        return rawType;
    }


    /**
     * Returns a {@code Type} object representing the type that this type
     * is a member of.  For example, if this type is {@code O<T>.I<S>},
     * return a representation of {@code O<T>}.
     *
     * <p>If this type is a top-level type, {@code null} is returned.
     *
     * @return a {@code Type} object representing the type that
     *     this type is a member of. If this type is a top-level type,
     *     {@code null} is returned
     * @throws TypeNotPresentException if the owner type
     *     refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if the owner type
     *     refers to a parameterized type that cannot be instantiated
     *     for any reason
     *
     */
    public Type getOwnerType() {
        return ownerType;
    }

    /*
     * From the JavaDoc for java.lang.reflect.ParameterizedType
     * "Instances of classes that implement this interface must
     * implement an equals() method that equates any two instances
     * that share the same generic type declaration and have equal
     * type parameters."
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterizedType) {
            // Check that information is equivalent
            ParameterizedType that = (ParameterizedType) o;

            if (this == that)
                return true;

            Type thatOwner   = that.getOwnerType();
            Type thatRawType = that.getRawType();

            if (false) { // Debugging
                boolean ownerEquality = (ownerType == null ?
                                         thatOwner == null :
                                         ownerType.equals(thatOwner));
                boolean rawEquality = (rawType == null ?
                                       thatRawType == null :
                                       rawType.equals(thatRawType));

                boolean typeArgEquality = Arrays.equals(actualTypeArguments, // avoid clone
                                                        that.getActualTypeArguments());
                for (Type t : actualTypeArguments) {
                    System.out.printf("\t\t%s%s%n", t, t.getClass());
                }

                System.out.printf("\towner %s\traw %s\ttypeArg %s%n",
                                  ownerEquality, rawEquality, typeArgEquality);
                return ownerEquality && rawEquality && typeArgEquality;
            }

            return
                Objects.equals(ownerType, thatOwner) &&
                Objects.equals(rawType, thatRawType) &&
                Arrays.equals(actualTypeArguments, // avoid clone
                              that.getActualTypeArguments());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return
            Arrays.hashCode(actualTypeArguments) ^
            Objects.hashCode(ownerType) ^
            Objects.hashCode(rawType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (ownerType != null) {
            sb.append(ownerType.getTypeName());

            sb.append("$");

            if (ownerType instanceof ParameterizedTypeImpl) {
                // Find simple name of nested type by removing the
                // shared prefix with owner.
                sb.append(rawType.getName().replace( ((ParameterizedTypeImpl)ownerType).rawType.getName() + "$",
                                         ""));
            } else
               sb.append(rawType.getSimpleName());
        } else
            sb.append(rawType.getName());

        if (actualTypeArguments != null) {
            StringJoiner sj = new StringJoiner(", ", "<", ">");
            sj.setEmptyValue("");
            for(Type t: actualTypeArguments) {
                sj.add(t.getTypeName());
            }
            sb.append(sj.toString());
        }

        return sb.toString();
    }
}
