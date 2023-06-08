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

import java.base.share.classes.jdk.internal.access.JavaLangInvokeAccess;
import java.base.share.classes.jdk.internal.access.SharedSecrets;
import java.base.share.classes.jdk.internal.vm.ContinuationScope;

import java.base.share.classes.java.lang.StackWalker.StackFrame;
import java.base.share.classes.java.lang.invoke.MethodType;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

class StackFrameInfo implements StackFrame {
    private static final JavaLangInvokeAccess JLIA =
        SharedSecrets.getJavaLangInvokeAccess();

    private final boolean retainClassRef;
    private Object memberName;    // MemberName initialized by VM
    private int bci;              // initialized by VM to >= 0
    private ContinuationScope contScope;
    private volatile StackTraceElement ste;

    /*
     * Construct an empty StackFrameInfo object that will be filled by the VM
     * during stack walking.
     *
     * @see StackStreamFactory.AbstractStackWalker#callStackWalk
     * @see StackStreamFactory.AbstractStackWalker#fetchStackFrames
     */
    StackFrameInfo(StackWalker walker) {
        this.retainClassRef = walker.retainClassRef;
        this.memberName = JLIA.newMemberName();
    }

    // package-private called by StackStreamFactory to skip
    // the capability check
    Class<?> declaringClass() {
        return JLIA.getDeclaringClass(memberName);
    }

    // ----- implementation of StackFrame methods

    @Override
    public String getClassName() {
        return declaringClass().getName();
    }

    @Override
    public Class<?> getDeclaringClass() {
        ensureRetainClassRefEnabled();
        return declaringClass();
    }

    @Override
    public String getMethodName() {
        return JLIA.getName(memberName);
    }

    @Override
    public MethodType getMethodType() {
        ensureRetainClassRefEnabled();
        return JLIA.getMethodType(memberName);
    }

    @Override
    public String getDescriptor() {
        return JLIA.getMethodDescriptor(memberName);
    }

    @Override
    public int getByteCodeIndex() {
        // bci not available for native methods
        if (isNativeMethod())
            return -1;

        return bci;
    }

    @Override
    public String getFileName() {
        return toStackTraceElement().getFileName();
    }

    @Override
    public int getLineNumber() {
        // line number not available for native methods
        if (isNativeMethod())
            return -2;

        return toStackTraceElement().getLineNumber();
    }


    @Override
    public boolean isNativeMethod() {
        return JLIA.isNative(memberName);
    }

    private String getContinuationScopeName() {
        return contScope != null ? contScope.getName() : null;
    }

    @Override
    public String toString() {
        return toStackTraceElement().toString();
    }

    @Override
    public StackTraceElement toStackTraceElement() {
        StackTraceElement s = ste;
        if (s == null) {
            synchronized (this) {
                s = ste;
                if (s == null) {
                    ste = s = StackTraceElement.of(this);
                }
            }
        }
        return s;
    }

    private void ensureRetainClassRefEnabled() {
        if (!retainClassRef) {
            throw new UnsupportedOperationException("No access to RETAIN_CLASS_REFERENCE");
        }
    }
}
