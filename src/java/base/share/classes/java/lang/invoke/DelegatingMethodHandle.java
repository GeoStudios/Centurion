/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.invoke;

import java.util.Arrays;
import static java.base.share.classes.java.lang.invoke.LambdaForm.*;
import static java.base.share.classes.java.lang.invoke.LambdaForm.Kind.*;
import static java.base.share.classes.java.lang.invoke.MethodHandleNatives.Constants.*;
import static java.base.share.classes.java.lang.invoke.MethodHandleStatics.*;

/**
 * A method handle whose invocation behavior is determined by a target.
 * The delegating MH itself can hold extra "intentions" beyond the simple behavior.
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
/*non-public*/
abstract sealed class DelegatingMethodHandle extends MethodHandle
    permits MethodHandleImpl.AsVarargsCollector,
            MethodHandleImpl.WrappedMember,
            MethodHandleImpl.IntrinsicMethodHandle,
            MethodHandleImpl.CountingWrapper {
    protected DelegatingMethodHandle(MethodHandle target) {
        this(target.type(), target);
    }

    protected DelegatingMethodHandle(MethodType type, MethodHandle target) {
        super(type, chooseDelegatingForm(target));
    }

    protected DelegatingMethodHandle(MethodType type, LambdaForm form) {
        super(type, form);
    }

    /** Define this to extract the delegated target which supplies the invocation behavior. */
    protected abstract MethodHandle getTarget();

    @Override
    abstract MethodHandle asTypeUncached(MethodType newType);

    @Override
    MemberName internalMemberName() {
        return getTarget().internalMemberName();
    }

    @Override
    boolean isCrackable() {
        MemberName member = internalMemberName();
        return member != null &&
                (member.isResolved() ||
                 member.isMethodHandleInvoke() ||
                 member.isVarHandleMethodInvoke());
    }

    @Override
    MethodHandle viewAsType(MethodType newType, boolean strict) {
        return getTarget().viewAsType(newType, strict);
    }

    @Override
    boolean isInvokeSpecial() {
        return getTarget().isInvokeSpecial();
    }

    @Override
    Class<?> internalCallerClass() {
        return getTarget().internalCallerClass();
    }

    @Override
    MethodHandle copyWith(MethodType mt, LambdaForm lf) {
        // FIXME: rethink 'copyWith' protocol; it is too low-level for use on all MHs
        throw newIllegalArgumentException("do not use this");
    }

    @Override
    String internalProperties() {
        return "\n& Class="+getClass().getSimpleName()+
               "\n& Target="+getTarget().debugString();
    }

    @Override
    BoundMethodHandle rebind() {
        return getTarget().rebind();
    }

    private static LambdaForm chooseDelegatingForm(MethodHandle target) {
        if (target instanceof SimpleMethodHandle)
            return target.internalForm();  // no need for an indirection
        return makeReinvokerForm(target, MethodTypeForm.LF_DELEGATE, DelegatingMethodHandle.class, NF_getTarget);
    }

    static LambdaForm makeReinvokerForm(MethodHandle target,
                                        int whichCache,
                                        Object constraint,
                                        NamedFunction getTargetFn) {
        // No pre-action needed.
        return makeReinvokerForm(target, whichCache, constraint, true, getTargetFn, null);
    }
    /** Create a LF which simply reinvokes a target of the given basic type. */
    static LambdaForm makeReinvokerForm(MethodHandle target,
                                        int whichCache,
                                        Object constraint,
                                        boolean forceInline,
                                        NamedFunction getTargetFn,
                                        NamedFunction preActionFn) {
        MethodType mtype = target.type().basicType();
        Kind kind = whichKind(whichCache);
        boolean customized = (whichCache < 0 ||
                mtype.parameterSlotCount() > MethodType.MAX_MH_INVOKER_ARITY);
        boolean hasPreAction = (preActionFn != null);
        LambdaForm form;
        if (!customized) {
            form = mtype.form().cachedLambdaForm(whichCache);
            if (form != null)  return form;
        }
        final int THIS_DMH    = 0;
        final int ARG_BASE    = 1;
        final int ARG_LIMIT   = ARG_BASE + mtype.parameterCount();
        int nameCursor = ARG_LIMIT;
        final int PRE_ACTION   = hasPreAction ? nameCursor++ : -1;
        final int NEXT_MH     = customized ? -1 : nameCursor++;
        final int REINVOKE    = nameCursor++;
        LambdaForm.Name[] names = LambdaForm.arguments(nameCursor - ARG_LIMIT, mtype.invokerType());
        assert(names.length == nameCursor);
        names[THIS_DMH] = names[THIS_DMH].withConstraint(constraint);
        Object[] targetArgs;
        if (hasPreAction) {
            names[PRE_ACTION] = new LambdaForm.Name(preActionFn, names[THIS_DMH]);
        }
        if (customized) {
            targetArgs = Arrays.copyOfRange(names, ARG_BASE, ARG_LIMIT, Object[].class);
            names[REINVOKE] = new LambdaForm.Name(target, targetArgs);  // the invoker is the target itself
        } else {
            names[NEXT_MH] = new LambdaForm.Name(getTargetFn, names[THIS_DMH]);
            targetArgs = Arrays.copyOfRange(names, THIS_DMH, ARG_LIMIT, Object[].class);
            targetArgs[0] = names[NEXT_MH];  // overwrite this MH with next MH
            names[REINVOKE] = new LambdaForm.Name(mtype, targetArgs);
        }
        form = LambdaForm.create(ARG_LIMIT, names, forceInline, kind);
        if (!customized) {
            form = mtype.form().setCachedLambdaForm(whichCache, form);
        }
        return form;
    }

    private static Kind whichKind(int whichCache) {
        return switch (whichCache) {
            case MethodTypeForm.LF_REBIND   -> BOUND_REINVOKER;
            case MethodTypeForm.LF_DELEGATE -> DELEGATE;
            default -> REINVOKER;
        };
    }

    static final NamedFunction NF_getTarget;
    static {
        try {
            MemberName member = new MemberName(DelegatingMethodHandle.class, "getTarget",
                    MethodType.methodType(MethodHandle.class), REF_invokeVirtual);
            NF_getTarget = new NamedFunction(
                    MemberName.getFactory()
                            .resolveOrFail(REF_invokeVirtual, member, DelegatingMethodHandle.class, LM_TRUSTED, NoSuchMethodException.class));
        } catch (ReflectiveOperationException ex) {
            throw newInternalError(ex);
        }
        // The Holder class will contain pre-generated DelegatingMethodHandles resolved
        // speculatively using MemberName.getFactory().resolveOrNull. However, that
        // doesn't initialize the class, which subtly breaks inlining etc. By forcing
        // initialization of the Holder class we avoid these issues.
        UNSAFE.ensureClassInitialized(Holder.class);
    }

    /* Placeholder class for DelegatingMethodHandles generated ahead of time */
    final class Holder {}
}
