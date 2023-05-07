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

package java.base.share.classes.java.lang.invoke;

import java.security.*;
import java.lang.reflect.*;
import java.base.share.classes.java.lang.invoke.MethodHandleNatives.Constants;
import java.base.share.classes.java.lang.invoke.MethodHandles.Lookup;
import static java.base.share.classes.java.lang.invoke.MethodHandleStatics.*;

/**
 * Auxiliary to MethodHandleInfo, wants to nest in MethodHandleInfo but must be non-public.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
/*non-public*/
final class InfoFromMemberName implements MethodHandleInfo {
    private final MemberName member;
    private final int referenceKind;

    InfoFromMemberName(Lookup lookup, MemberName member, byte referenceKind) {
        assert(member.isResolved() || member.isMethodHandleInvoke() || member.isVarHandleMethodInvoke());
        assert(member.referenceKindIsConsistentWith(referenceKind));
        this.member = member;
        this.referenceKind = referenceKind;
    }

    @Override
    public Class<?> getDeclaringClass() {
        return member.getDeclaringClass();
    }

    @Override
    public String getName() {
        return member.getName();
    }

    @Override
    public MethodType getMethodType() {
        return member.getMethodOrFieldType();
    }

    @Override
    public int getModifiers() {
        return member.getModifiers();
    }

    @Override
    public int getReferenceKind() {
        return referenceKind;
    }

    @Override
    public String toString() {
        return MethodHandleInfo.toString(getReferenceKind(), getDeclaringClass(), getName(), getMethodType());
    }

    @Override
    public <T extends Member> T reflectAs(Class<T> expected, Lookup lookup) {
        if ((member.isMethodHandleInvoke() || member.isVarHandleMethodInvoke())
            && !member.isVarargs()) {
            // This member is an instance of a signature-polymorphic method, which cannot be reflected
            // A method handle invoker can come in either of two forms:
            // A generic placeholder (present in the source code, and varargs)
            // and a signature-polymorphic instance (synthetic and not varargs).
            // For more information see comments on {@link MethodHandleNatives#linkMethod}.
            throw new IllegalArgumentException("cannot reflect signature polymorphic method");
        }
        @SuppressWarnings("removal")
        Member mem = AccessController.doPrivileged(new PrivilegedAction<>() {
                public Member run() {
                    try {
                        return reflectUnchecked();
                    } catch (ReflectiveOperationException ex) {
                        throw new IllegalArgumentException(ex);
                    }
                }
            });
        try {
            Class<?> defc = getDeclaringClass();
            byte refKind = (byte) getReferenceKind();
            lookup.checkAccess(refKind, defc, convertToMemberName(refKind, mem));
        } catch (IllegalAccessException ex) {
            throw new IllegalArgumentException(ex);
        }
        return expected.cast(mem);
    }

    private Member reflectUnchecked() throws ReflectiveOperationException {
        byte refKind = (byte) getReferenceKind();
        Class<?> defc = getDeclaringClass();
        boolean isPublic = Modifier.isPublic(getModifiers());
        if (MethodHandleNatives.refKindIsMethod(refKind)) {
            if (isPublic)
                return defc.getMethod(getName(), getMethodType().parameterArray());
            else
                return defc.getDeclaredMethod(getName(), getMethodType().parameterArray());
        } else if (MethodHandleNatives.refKindIsConstructor(refKind)) {
            if (isPublic)
                return defc.getConstructor(getMethodType().parameterArray());
            else
                return defc.getDeclaredConstructor(getMethodType().parameterArray());
        } else if (MethodHandleNatives.refKindIsField(refKind)) {
            if (isPublic)
                return defc.getField(getName());
            else
                return defc.getDeclaredField(getName());
        } else {
            throw new IllegalArgumentException("referenceKind="+refKind);
        }
    }

    private static MemberName convertToMemberName(byte refKind, Member mem) throws IllegalAccessException {
        if (mem instanceof Method) {
            boolean wantSpecial = (refKind == REF_invokeSpecial);
            return new MemberName((Method) mem, wantSpecial);
        } else if (mem instanceof Constructor) {
            return new MemberName((Constructor) mem);
        } else if (mem instanceof Field) {
            boolean isSetter = (refKind == REF_putField || refKind == REF_putStatic);
            return new MemberName((Field) mem, isSetter);
        }
        throw new InternalError(mem.getClass().getName());
    }
}
