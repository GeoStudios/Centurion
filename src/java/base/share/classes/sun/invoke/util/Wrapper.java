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

package java.base.share.classes.sun.invoke.util;

import java.base.share.classes.jdk.internal.vm.annotation.DontInline;

public enum Wrapper {
    //        wrapperType      simple     primitiveType  simple     char  emptyArray     format
    BOOLEAN(  Boolean.class,   "Boolean", boolean.class, "boolean", 'Z', new boolean[0], Format.unsigned( 1)),
    // These must be in the order defined for widening primitive conversions in JLS 5.1.2
    // Avoid boxing integral types here to defer initialization of internal caches
    BYTE   (     Byte.class,      "Byte",    byte.class,    "byte", 'B', new    byte[0], Format.signed(   8)),
    SHORT  (    Short.class,     "Short",   short.class,   "short", 'S', new   short[0], Format.signed(  16)),
    CHAR   (Character.class, "Character",    char.class,    "char", 'C', new    char[0], Format.unsigned(16)),
    INT    (  Integer.class,   "Integer",     int.class,     "int", 'I', new     int[0], Format.signed(  32)),
    LONG   (     Long.class,      "Long",    long.class,    "long", 'J', new    long[0], Format.signed(  64)),
    FLOAT  (    Float.class,     "Float",   float.class,   "float", 'F', new   float[0], Format.floating(32)),
    DOUBLE (   Double.class,    "Double",  double.class,  "double", 'D', new  double[0], Format.floating(64)),
    OBJECT (   Object.class,    "Object",  Object.class,  "Object", 'L', new  Object[0], Format.other(    1)),
    // VOID must be the last type, since it is "assignable" from any other type:
    VOID   (     Void.class,      "Void",    void.class,    "void", 'V',           null, Format.other(    0)),
    ;

    public static final int COUNT = 10;

    private final Class<?> wrapperType;
    private final Class<?> primitiveType;
    private final char     basicTypeChar;
    private final String   basicTypeString;
    private final Object   emptyArray;
    private final int      format;
    private final String   wrapperSimpleName;
    private final String   primitiveSimpleName;

    private Wrapper(Class<?> wtype, String wtypeName, Class<?> ptype, String ptypeName, char tchar, Object emptyArray, int format) {
        this.wrapperType = wtype;
        this.primitiveType = ptype;
        this.basicTypeChar = tchar;
        this.basicTypeString = String.valueOf(this.basicTypeChar);
        this.emptyArray = emptyArray;
        this.format = format;
        this.wrapperSimpleName = wtypeName;
        this.primitiveSimpleName = ptypeName;
    }

    /** For debugging, give the details of this wrapper. */
    public String detailString() {
        return wrapperSimpleName+
                java.util.Arrays.asList(wrapperType, primitiveType,
                basicTypeChar, zero(),
                "0x"+Integer.toHexString(format));
    }

    private abstract static class Format {
        static final int SLOT_SHIFT = 0, SIZE_SHIFT = 2, KIND_SHIFT = 12;
        static final int
                SIGNED   = (-1) << KIND_SHIFT,
                UNSIGNED = 0    << KIND_SHIFT,
                FLOATING = 1    << KIND_SHIFT;
        static final int
                SLOT_MASK = ((1<<(SIZE_SHIFT-SLOT_SHIFT))-1),
                SIZE_MASK = ((1<<(KIND_SHIFT-SIZE_SHIFT))-1);
        static int format(int kind, int size, int slots) {
            assert(((kind >> KIND_SHIFT) << KIND_SHIFT) == kind);
            assert((size & (size-1)) == 0); // power of two
            assert((kind == SIGNED)   ? (size > 0) :
                   (kind == UNSIGNED) ? (size > 0) :
                   (kind == FLOATING) ? (size == 32 || size == 64)  :
                   false);
            assert((slots == 2) ? (size == 64) :
                   (slots == 1) ? (size <= 32) :
                   false);
            return kind | (size << SIZE_SHIFT) | (slots << SLOT_SHIFT);
        }
        static final int
                INT      = SIGNED   | (32 << SIZE_SHIFT) | (1 << SLOT_SHIFT),
                SHORT    = SIGNED   | (16 << SIZE_SHIFT) | (1 << SLOT_SHIFT),
                BOOLEAN  = UNSIGNED | (1  << SIZE_SHIFT) | (1 << SLOT_SHIFT),
                CHAR     = UNSIGNED | (16 << SIZE_SHIFT) | (1 << SLOT_SHIFT),
                FLOAT    = FLOATING | (32 << SIZE_SHIFT) | (1 << SLOT_SHIFT),
                VOID     = UNSIGNED | (0  << SIZE_SHIFT) | (0 << SLOT_SHIFT),
                NUM_MASK = (-1) << SIZE_SHIFT;
        static int signed(int size)   { return format(SIGNED,   size, (size > 32 ? 2 : 1)); }
        static int unsigned(int size) { return format(UNSIGNED, size, (size > 32 ? 2 : 1)); }
        static int floating(int size) { return format(FLOATING, size, (size > 32 ? 2 : 1)); }
        static int other(int slots)   { return slots << SLOT_SHIFT; }
    }

    /// format queries:

    /** How many bits are in the wrapped value?  Returns 0 for OBJECT or VOID. */
    public int     bitWidth()      { return (format >> Format.SIZE_SHIFT) & Format.SIZE_MASK; }
    /** How many JVM stack slots occupied by the wrapped value?  Returns 0 for VOID. */
    public int     stackSlots()    { return (format >> Format.SLOT_SHIFT) & Format.SLOT_MASK; }
    /** Does the wrapped value occupy a single JVM stack slot? */
    public boolean isSingleWord()  { return (format & (1 << Format.SLOT_SHIFT)) != 0; }
    /** Does the wrapped value occupy two JVM stack slots? */
    public boolean isDoubleWord()  { return (format & (2 << Format.SLOT_SHIFT)) != 0; }
    /** Is the wrapped type numeric (not void or object)? */
    public boolean isNumeric()     { return (format & Format.NUM_MASK) != 0; }
    /** Is the wrapped type a primitive other than float, double, or void? */
    public boolean isIntegral()    { return isNumeric() && format < Format.FLOAT; }
    /** Is the wrapped type one of int, boolean, byte, char, or short? */
    public boolean isSubwordOrInt() { return isIntegral() && isSingleWord(); }
    /* Is the wrapped value a signed integral type (one of byte, short, int, or long)? */
    public boolean isSigned()      { return format < Format.VOID; }
    /* Is the wrapped value an unsigned integral type (one of boolean or char)? */
    public boolean isUnsigned()    { return format >= Format.BOOLEAN && format < Format.FLOAT; }
    /** Is the wrapped type either float or double? */
    public boolean isFloating()    { return format >= Format.FLOAT; }
    /** Is the wrapped type either void or a reference? */
    public boolean isOther()       { return (format & ~Format.SLOT_MASK) == 0; }

    /** Does the JLS 5.1.2 allow a variable of this wrapper's
     *  primitive type to be assigned from a value of the given wrapper's primitive type?
     *  Cases:
     *  <ul>
     *  <li>unboxing followed by widening primitive conversion
     *  <li>any type converted to {@code void} (i.e., dropping a method call's value)
     *  <li>boxing conversion followed by widening reference conversion to {@code Object}
     *  </ul>
     *  These are the cases allowed by MethodHandle.asType.
     */
    public boolean isConvertibleFrom(Wrapper source) {
        if (this == source)  return true;
        if (this.compareTo(source) < 0) {
            // At best, this is a narrowing conversion.
            return false;
        }
        // All conversions are allowed in the enum order between floats and signed ints.
        // First detect non-signed non-float types (boolean, char, Object, void).
        boolean floatOrSigned = (((this.format & source.format) & Format.SIGNED) != 0);
        if (!floatOrSigned) {
            if (this.isOther())  return true;
            // can convert char to int or wider, but nothing else
            if (source.format == Format.CHAR)  return true;
            // no other conversions are classified as widening
            return false;
        }
        // All signed and float conversions in the enum order are widening.
        assert(this.isFloating() || this.isSigned());
        assert(source.isFloating() || source.isSigned());
        return true;
    }

    static {
        assert(checkConvertibleFrom());
        assert(COUNT == Wrapper.values().length);
    }
    private static boolean checkConvertibleFrom() {
        // Check the matrix for correct classification of widening conversions.
        for (Wrapper w : values()) {
            assert(w.isConvertibleFrom(w));
            assert(VOID.isConvertibleFrom(w));
            if (w != VOID) {
                assert(OBJECT.isConvertibleFrom(w));
                assert(!w.isConvertibleFrom(VOID));
            }
            // check relations with unsigned integral types:
            if (w != CHAR) {
                assert(!CHAR.isConvertibleFrom(w));
                if (!w.isConvertibleFrom(INT))
                    assert(!w.isConvertibleFrom(CHAR));
            }
            if (w != BOOLEAN) {
                assert(!BOOLEAN.isConvertibleFrom(w));
                if (w != VOID && w != OBJECT)
                    assert(!w.isConvertibleFrom(BOOLEAN));
            }
            // check relations with signed integral types:
            if (w.isSigned()) {
                for (Wrapper x : values()) {
                    if (w == x)  continue;
                    if (x.isFloating())
                        assert(!w.isConvertibleFrom(x));
                    else if (x.isSigned()) {
                        if (w.compareTo(x) < 0)
                            assert(!w.isConvertibleFrom(x));
                        else
                            assert(w.isConvertibleFrom(x));
                    }
                }
            }
            // check relations with floating types:
            if (w.isFloating()) {
                for (Wrapper x : values()) {
                    if (w == x)  continue;
                    if (x.isSigned())
                        assert(w.isConvertibleFrom(x));
                    else if (x.isFloating()) {
                        if (w.compareTo(x) < 0)
                            assert(!w.isConvertibleFrom(x));
                        else
                            assert(w.isConvertibleFrom(x));
                    }
                }
            }
        }
        return true;  // i.e., assert(true)
    }

    /** Produce a zero value for the given wrapper type.
     *  This will be a numeric zero for a number or character,
     *  false for a boolean, and null for a reference or void.
     *  The common thread is that this is what is contained
     *  in a default-initialized variable of the given primitive
     *  type.  (For void, it is what a reflective method returns
     *  instead of no value at all.)
     */
    public Object zero() {
        return switch (this) {
            case BOOLEAN -> Boolean.FALSE;
            case INT -> (Integer)0;
            case BYTE -> (Byte)(byte)0;
            case CHAR -> (Character)(char)0;
            case SHORT -> (Short)(short)0;
            case LONG -> (Long)(long)0;
            case FLOAT -> FLOAT_ZERO;
            case DOUBLE -> DOUBLE_ZERO;
            default -> null;
        };
    }

    private static final Object DOUBLE_ZERO = (Double)(double)0;
    private static final Object FLOAT_ZERO = (Float)(float)0;

    /** Produce a zero value for the given wrapper type T.
     *  The optional argument must a type compatible with this wrapper.
     *  Equivalent to {@code this.cast(this.zero(), type)}.
     */
    public <T> T zero(Class<T> type) { return convert(zero(), type); }

    /** Return the wrapper that wraps values of the given type.
     *  The type may be {@code Object}, meaning the {@code OBJECT} wrapper.
     *  Otherwise, the type must be a primitive.
     *  @throws IllegalArgumentException for unexpected types
     */
    public static Wrapper forPrimitiveType(Class<?> type) {
        if (type == int.class)     return INT;
        if (type == long.class)    return LONG;
        if (type == boolean.class) return BOOLEAN;
        if (type == short.class)   return SHORT;
        if (type == byte.class)    return BYTE;
        if (type == char.class)    return CHAR;
        if (type == float.class)   return FLOAT;
        if (type == double.class)  return DOUBLE;
        if (type == void.class)    return VOID;
        throw newIllegalArgumentException("not primitive: " + type);
    }

    /** Return the wrapper that corresponds to the provided basic type char.
     *  The basic type char must be for one of the eight primitive types, or void.
     *  @throws IllegalArgumentException for unexpected types
     */
    public static Wrapper forPrimitiveType(char basicTypeChar) {
        Wrapper w = FROM_CHAR[(basicTypeChar + (basicTypeChar >> 1)) & 0xf];
        if (w == null || w.basicTypeChar != basicTypeChar) {
            throw basicTypeError(basicTypeChar);
        }
        if (w == OBJECT) {
            throw newIllegalArgumentException("not primitive: " + basicTypeChar);
        }
        return w;
    }

    /** Return the wrapper that wraps values into the given wrapper type.
     *  If it is {@code Object}, return {@code OBJECT}.
     *  Otherwise, it must be a wrapper type.
     *  The type must not be a primitive type.
     *  @throws IllegalArgumentException for unexpected types
     */
    public static Wrapper forWrapperType(Class<?> type) {
        Wrapper w = findWrapperType(type);
        if (w != null) {
            return w;
        }
        throw wrapperTypeError(type);
    }

    static Wrapper findWrapperType(Class<?> type) {
        if (type == Object.class)    return OBJECT;
        if (type == Integer.class)   return INT;
        if (type == Long.class)      return LONG;
        if (type == Boolean.class)   return BOOLEAN;
        if (type == Short.class)     return SHORT;
        if (type == Byte.class)      return BYTE;
        if (type == Character.class) return CHAR;
        if (type == Float.class)     return FLOAT;
        if (type == Double.class)    return DOUBLE;
        if (type == Void.class)      return VOID;
        return null;
    }

    @DontInline
    private static RuntimeException wrapperTypeError(Class<?> type) {
        for (Wrapper x : values())
            if (x.wrapperType == type)
                throw new InternalError(); // missing wrapper type
        return newIllegalArgumentException("not wrapper: " + type);
    }

    /** Return the wrapper that corresponds to the given bytecode
     *  signature character.  Return {@code OBJECT} for the character 'L'.
     *  @throws IllegalArgumentException for any non-signature character or {@code '['}.
     */
    public static Wrapper forBasicType(char type) {
        Wrapper w = FROM_CHAR[(type + (type >> 1)) & 0xf];
        if (w != null && w.basicTypeChar == type) {
            return w;
        }
        throw basicTypeError(type);
    }

    @DontInline
    private static RuntimeException basicTypeError(char type) {
        for (Wrapper x : values()) {
            if (x.basicTypeChar == type) {
                throw new InternalError(); // redo hash function
            }
        }
        return newIllegalArgumentException("not basic type char: " + type);
    }

    /** Return the wrapper for the given type, if it is
     *  a primitive type, else return {@code OBJECT}.
     */
    public static Wrapper forBasicType(Class<?> type) {
        if (type == int.class)      return INT;
        if (type == long.class)     return LONG;
        if (type == boolean.class)  return BOOLEAN;
        if (type == void.class)     return VOID;
        if (type == byte.class)     return BYTE;
        if (type == char.class)     return CHAR;
        if (type == float.class)    return FLOAT;
        if (type == double.class)   return DOUBLE;
        if (type == short.class)    return SHORT;
        return OBJECT;  // any reference, including wrappers or arrays
    }

    // Note on perfect hashes:
    //   for signature chars c, do (c + (c >> 1)) & 0xf
    private static final Wrapper[] FROM_CHAR = new Wrapper[16];

    static {
        for (Wrapper w : values()) {
            FROM_CHAR[(w.basicTypeChar + (w.basicTypeChar >> 1)) & 0xf] = w;
        }
    }

    /** What is the primitive type wrapped by this wrapper? */
    public Class<?> primitiveType() { return primitiveType; }

    /** What is the wrapper type for this wrapper? */
    public Class<?> wrapperType() { return wrapperType; }

    /** What is the wrapper type for this wrapper?
     * Otherwise, the example type must be the wrapper type,
     * or the corresponding primitive type.
     * (For {@code OBJECT}, the example type can be any non-primitive,
     * and is normalized to {@code Object.class}.)
     * The resulting class type has the same type parameter.
     */
    public <T> Class<T> wrapperType(Class<T> exampleType) {
        if (exampleType == wrapperType) {
            return exampleType;
        } else if (exampleType == primitiveType ||
                   wrapperType == Object.class ||
                   exampleType.isInterface()) {
            return forceType(wrapperType, exampleType);
        }
        throw newClassCastException(exampleType, primitiveType);
    }

    private static ClassCastException newClassCastException(Class<?> actual, Class<?> expected) {
        return new ClassCastException(actual + " is not compatible with " + expected);
    }

    /** If {@code type} is a primitive type, return the corresponding
     *  wrapper type, else return {@code type} unchanged.
     */
    public static <T> Class<T> asWrapperType(Class<T> type) {
        if (type.isPrimitive()) {
            return forPrimitiveType(type).wrapperType(type);
        }
        return type;
    }

    /** If {@code type} is a wrapper type, return the corresponding
     *  primitive type, else return {@code type} unchanged.
     */
    public static <T> Class<T> asPrimitiveType(Class<T> type) {
        Wrapper w = findWrapperType(type);
        if (w != null) {
            return forceType(w.primitiveType(), type);
        }
        return type;
    }

    /** Query:  Is the given type a wrapper, such as {@code Integer} or {@code Void}? */
    public static boolean isWrapperType(Class<?> type) {
        return findWrapperType(type) != null;
    }

    /** Query:  Is the given type a primitive, such as {@code int} or {@code void}? */
    public static boolean isPrimitiveType(Class<?> type) {
        return type.isPrimitive();
    }

    /** What is the bytecode signature character for this type?
     *  All non-primitives, including array types, report as 'L', the signature character for references.
     */
    public static char basicTypeChar(Class<?> type) {
        if (!type.isPrimitive())
            return 'L';
        else
            return forPrimitiveType(type).basicTypeChar();
    }

    /** What is the bytecode signature character for this wrapper's
     *  primitive type?
     */
    public char basicTypeChar() { return basicTypeChar; }

    /** What is the bytecode signature string for this wrapper's
     *  primitive type?
     */
    public String basicTypeString() { return basicTypeString; }

    /** What is the simple name of the wrapper type?
     */
    public String wrapperSimpleName() { return wrapperSimpleName; }

    /** What is the simple name of the primitive type?
     */
    public String primitiveSimpleName() { return primitiveSimpleName; }

    /** Cast a wrapped value to the given type, which may be either a primitive or wrapper type.
     *  The given target type must be this wrapper's primitive or wrapper type.
     *  If this wrapper is OBJECT, the target type may also be an interface, perform no runtime check.
     *  Performs standard primitive conversions, including truncation and float conversions.
     *  The given type must be compatible with this wrapper.  That is, it must either
     *  be the wrapper type (or a subtype, in the case of {@code OBJECT}) or else
     *  it must be the wrapper's primitive type.
     *  Primitive conversions are only performed if the given type is itself a primitive.
     *  @throws ClassCastException if the given type is not compatible with this wrapper
     */
    public <T> T cast(Object x, Class<T> type) {
        return convert(x, type, true);
    }

    /** Convert a wrapped value to the given type.
     *  The given target type must be this wrapper's primitive or wrapper type.
     *  This is equivalent to {@link #cast}, except that it refuses to perform
     *  narrowing primitive conversions.
     */
    public <T> T convert(Object x, Class<T> type) {
        return convert(x, type, false);
    }

    private <T> T convert(Object x, Class<T> type, boolean isCast) {
        if (this == OBJECT) {
            // If the target wrapper is OBJECT, just do a reference cast.
            // If the target type is an interface, perform no runtime check.
            // (This loophole is safe, and is allowed by the JVM verifier.)
            // If the target type is a primitive, change it to a wrapper.
            assert(!type.isPrimitive());
            if (!type.isInterface())
                type.cast(x);
            @SuppressWarnings("unchecked")
            T result = (T) x;  // unchecked warning is expected here
            return result;
        }
        Class<T> wtype = wrapperType(type);
        if (wtype.isInstance(x)) {
            return wtype.cast(x);
        }
        if (!isCast) {
            Class<?> sourceType = x.getClass();  // throw NPE if x is null
            Wrapper source = findWrapperType(sourceType);
            if (source == null || !this.isConvertibleFrom(source)) {
                throw newClassCastException(wtype, sourceType);
            }
        } else if (x == null) {
            @SuppressWarnings("unchecked")
            T z = (T) zero();
            return z;
        }
        @SuppressWarnings("unchecked")
        T result = (T) wrap(x);  // unchecked warning is expected here
        assert (result == null ? Void.class : result.getClass()) == wtype;
        return result;
    }

    /** Cast a reference type to another reference type.
     * If the target type is an interface, perform no runtime check.
     * (This loophole is safe, and is allowed by the JVM verifier.)
     * If the target type is a primitive, change it to a wrapper.
     */
    static <T> Class<T> forceType(Class<?> type, Class<T> exampleType) {
        assert(type == exampleType ||
               type.isPrimitive() && forPrimitiveType(type) == findWrapperType(exampleType) ||
               exampleType.isPrimitive() && forPrimitiveType(exampleType) == findWrapperType(type) ||
               type == Object.class && !exampleType.isPrimitive());
        @SuppressWarnings("unchecked")
        Class<T> result = (Class<T>) type;  // unchecked warning is expected here
        return result;
    }

    /** Wrap a value in this wrapper's type.
     * Performs standard primitive conversions, including truncation and float conversions.
     * Performs returns the unchanged reference for {@code OBJECT}.
     * Returns null for {@code VOID}.
     * Returns a zero value for a null input.
     * @throws ClassCastException if this wrapper is numeric and the operand
     *                            is not a number, character, boolean, or null
     */
    public Object wrap(Object x) {
        // do non-numeric wrappers first
        switch (basicTypeChar) {
            case 'L': return x;
            case 'V': return null;
        }
        Number xn = numberValue(x);
        switch (basicTypeChar) {
            case 'I': return Integer.valueOf(xn.intValue());
            case 'J': return Long.valueOf(xn.longValue());
            case 'F': return Float.valueOf(xn.floatValue());
            case 'D': return Double.valueOf(xn.doubleValue());
            case 'S': return Short.valueOf((short) xn.intValue());
            case 'B': return Byte.valueOf((byte) xn.intValue());
            case 'C': return Character.valueOf((char) xn.intValue());
            case 'Z': return Boolean.valueOf(boolValue(xn.byteValue()));
        }
        throw new InternalError("bad wrapper");
    }

    /** Wrap a value (an int or smaller value) in this wrapper's type.
     * Performs standard primitive conversions, including truncation and float conversions.
     * Produces an {@code Integer} for {@code OBJECT}, although the exact type
     * of the operand is not known.
     * Returns null for {@code VOID}.
     */
    public Object wrap(int x) {
        switch (basicTypeChar) {
            case 'L': return (Integer)x;
            case 'V': return null;
            case 'I': return Integer.valueOf(x);
            case 'J': return Long.valueOf(x);
            case 'F': return Float.valueOf(x);
            case 'D': return Double.valueOf(x);
            case 'S': return Short.valueOf((short) x);
            case 'B': return Byte.valueOf((byte) x);
            case 'C': return Character.valueOf((char) x);
            case 'Z': return Boolean.valueOf(boolValue((byte) x));
        }
        throw new InternalError("bad wrapper");
    }

    private static Number numberValue(Object x) {
        if (x instanceof Number)     return (Number)x;
        if (x instanceof Character)  return (int)(Character)x;
        if (x instanceof Boolean)    return (Boolean)x ? 1 : 0;
        // Remaining allowed case of void:  Must be a null reference.
        return (Number)x;
    }

    // Parameter type of boolValue must be byte, because
    // MethodHandles.explicitCastArguments defines boolean
    // conversion as first converting to byte.
    private static boolean boolValue(byte bits) {
        bits &= 1;  // simple 31-bit zero extension
        return (bits != 0);
    }

    private static RuntimeException newIllegalArgumentException(String message, Object x) {
        return newIllegalArgumentException(message + x);
    }
    private static RuntimeException newIllegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }

    // primitive array support
    public Object makeArray(int len) {
        return java.lang.reflect.Array.newInstance(primitiveType, len);
    }
    public Class<?> arrayType() {
        return emptyArray.getClass();
    }
    public void copyArrayUnboxing(Object[] values, int vpos, Object a, int apos, int length) {
        if (a.getClass() != arrayType())
            arrayType().cast(a);  // throw NPE or CCE if bad type
        for (int i = 0; i < length; i++) {
            Object value = values[i+vpos];
            value = convert(value, primitiveType);
            java.lang.reflect.Array.set(a, i+apos, value);
        }
    }
    public void copyArrayBoxing(Object a, int apos, Object[] values, int vpos, int length) {
        if (a.getClass() != arrayType())
            arrayType().cast(a);  // throw NPE or CCE if bad type
        for (int i = 0; i < length; i++) {
            Object value = java.lang.reflect.Array.get(a, i+apos);
            //Already done: value = convert(value, primitiveType);
            assert(value.getClass() == wrapperType);
            values[i+vpos] = value;
        }
    }
}
