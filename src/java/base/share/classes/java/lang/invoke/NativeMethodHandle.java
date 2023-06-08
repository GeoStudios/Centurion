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

import java.base.share.classes.jdk.internal.vm.annotation.ForceInline;
import java.base.share.classes.jdk.internal.foreign.abi.NativeEntryPoint;

import static java.base.share.classes.java.lang.invoke.LambdaForm.*;
import static java.base.share.classes.java.lang.invoke.MethodHandleNatives.Constants.LM_TRUSTED;
import static java.base.share.classes.java.lang.invoke.MethodHandleNatives.Constants.REF_invokeStatic;
import static java.base.share.classes.java.lang.invoke.MethodHandleStatics.newInternalError;

/**
 * This class models a method handle to a native function. A native method handle is made up of a {@link NativeEntryPoint},
 * which is used to capture the characteristics of the native call (such as calling convention to be used,
 * or whether a native transition is required) and a <em>fallback</em> method handle, which can be used
 * when intrinsification of this method handle is not possible.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
/*non-public*/ final class NativeMethodHandle extends MethodHandle {
    final NativeEntryPoint nep;

    private NativeMethodHandle(MethodType type, LambdaForm form, NativeEntryPoint nep) {
        super(type, form);
        this.nep = nep;
    }

    /**
     * Creates a new native method handle with given {@link NativeEntryPoint} and <em>fallback</em> method handle.
     */
    public static MethodHandle make(NativeEntryPoint nep) {
        MethodType type = nep.type();
        if (hasIllegalType(type))
            throw new IllegalArgumentException("Illegal type(s) found: " + type);


        LambdaForm lform = preparedLambdaForm(type);
        return new NativeMethodHandle(type, lform, nep);
    }

    private static boolean hasIllegalType(MethodType type) {
        if (isIllegalType(type.returnType()))
            return true;

        for (Class<?> pType : type.ptypes()) {
            if (isIllegalType(pType))
                return true;
        }

        return false;
    }

    private static boolean isIllegalType(Class<?> pType) {
        return !(pType == long.class
              || pType == int.class
              || pType == float.class
              || pType == double.class
              || pType == void.class);
    }

    private static final MemberName.Factory IMPL_NAMES = MemberName.getFactory();

    private static LambdaForm preparedLambdaForm(MethodType mtype) {
        int id = MethodTypeForm.LF_INVNATIVE;
        mtype = mtype.basicType();
        LambdaForm lform = mtype.form().cachedLambdaForm(id);
        if (lform != null) return lform;
        lform = makePreparedLambdaForm(mtype);
        return mtype.form().setCachedLambdaForm(id, lform);
    }

    private static LambdaForm makePreparedLambdaForm(MethodType mtype) {
        MethodType linkerType = mtype
                .appendParameterTypes(Object.class); // NEP
        MemberName linker = new MemberName(MethodHandle.class, "linkToNative", linkerType, REF_invokeStatic);
        try {
            linker = IMPL_NAMES.resolveOrFail(REF_invokeStatic, linker, null, LM_TRUSTED, NoSuchMethodException.class);
        } catch (ReflectiveOperationException ex) {
            throw newInternalError(ex);
        }
        final int NMH_THIS = 0;
        final int ARG_BASE = 1;
        final int ARG_LIMIT = ARG_BASE + mtype.parameterCount();
        int nameCursor = ARG_LIMIT;
        final int GET_NEP = nameCursor++;
        final int LINKER_CALL = nameCursor++;

        LambdaForm.Name[] names = arguments(nameCursor - ARG_LIMIT, mtype.invokerType());
        assert (names.length == nameCursor);

        names[GET_NEP] = new LambdaForm.Name(Lazy.NF_internalNativeEntryPoint, names[NMH_THIS]);

        Object[] outArgs = new Object[linkerType.parameterCount()];
        System.arraycopy(names, ARG_BASE, outArgs, 0, mtype.parameterCount());
        outArgs[outArgs.length - 1] = names[GET_NEP];
        names[LINKER_CALL] = new LambdaForm.Name(linker, outArgs);

        LambdaForm lform = LambdaForm.create(ARG_LIMIT, names, LAST_RESULT);
        // This is a tricky bit of code.  Don't send it through the LF interpreter.
        lform.compileToBytecode();
        return lform;
    }

    final
    @Override
    MethodHandle copyWith(MethodType mt, LambdaForm lf) {
        assert (this.getClass() == NativeMethodHandle.class);  // must override in subclasses
        return new NativeMethodHandle(mt, lf, nep);
    }

    @Override
    BoundMethodHandle rebind() {
        return BoundMethodHandle.makeReinvoker(this);
    }

    @ForceInline
    static Object internalNativeEntryPoint(Object mh) {
        return ((NativeMethodHandle)mh).nep;
    }

    /**
     * Pre-initialized NamedFunctions for bootstrapping purposes.
     * Factored in an inner class to delay initialization until first usage.
     */
    private static class Lazy {

        static final NamedFunction
                NF_internalNativeEntryPoint;

        static {
            try {
                Class<NativeMethodHandle> THIS_CLASS = NativeMethodHandle.class;
                NamedFunction[] nfs = new NamedFunction[]{
                        NF_internalNativeEntryPoint = new NamedFunction(
                                THIS_CLASS.getDeclaredMethod("internalNativeEntryPoint", Object.class)),
                };
                for (NamedFunction nf : nfs) {
                    // Each nf must be statically invocable or we get tied up in our bootstraps.
                    assert (InvokerBytecodeGenerator.isStaticallyInvocable(nf.member)) : nf;
                    nf.resolve();
                }
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }
    }
}
