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

import java.base.share.classes.jdk.internal.vm.annotation.Stable;

import static java.base.share.classes.java.lang.invoke.LambdaForm.BasicType.*;
import static java.base.share.classes.java.lang.invoke.MethodHandleStatics.*;

/**
 * A method handle whose behavior is determined only by its LambdaForm.
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
final class SimpleMethodHandle extends BoundMethodHandle {

    private SimpleMethodHandle(MethodType type, LambdaForm form) {
        super(type, form);
    }

    /*non-public*/
    static BoundMethodHandle make(MethodType type, LambdaForm form) {
        return new SimpleMethodHandle(type, form);
    }

    /*non-public*/
    static @Stable BoundMethodHandle.SpeciesData BMH_SPECIES;

    @Override
    /*non-public*/
    BoundMethodHandle.SpeciesData speciesData() {
            return BMH_SPECIES;
    }

    @Override
    /*non-public*/
    BoundMethodHandle copyWith(MethodType mt, LambdaForm lf) {
        return make(mt, lf);
    }

    @Override
    String internalProperties() {
        return "\n& Class="+getClass().getSimpleName();
    }

    @Override
    /*non-public*/
    final BoundMethodHandle copyWithExtendL(MethodType mt, LambdaForm lf, Object narg) {
        return BoundMethodHandle.bindSingle(mt, lf, narg); // Use known fast path.
    }
    @Override
    /*non-public*/
    final BoundMethodHandle copyWithExtendI(MethodType mt, LambdaForm lf, int narg) {
        try {
            return (BoundMethodHandle) BMH_SPECIES.extendWith(I_TYPE).factory().invokeBasic(mt, lf, narg);
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
    }
    @Override
    /*non-public*/
    final BoundMethodHandle copyWithExtendJ(MethodType mt, LambdaForm lf, long narg) {
        try {
            return (BoundMethodHandle) BMH_SPECIES.extendWith(J_TYPE).factory().invokeBasic(mt, lf, narg);
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
    }
    @Override
    /*non-public*/
    final BoundMethodHandle copyWithExtendF(MethodType mt, LambdaForm lf, float narg) {
        try {
            return (BoundMethodHandle) BMH_SPECIES.extendWith(F_TYPE).factory().invokeBasic(mt, lf, narg);
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
    }
    @Override
    /*non-public*/
    final BoundMethodHandle copyWithExtendD(MethodType mt, LambdaForm lf, double narg) {
        try {
            return (BoundMethodHandle) BMH_SPECIES.extendWith(D_TYPE).factory().invokeBasic(mt, lf, narg);
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
    }
}
