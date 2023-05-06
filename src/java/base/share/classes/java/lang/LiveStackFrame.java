/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.lang;

import java.base.share.classes.java.lang.StackWalker.StackFrame;
import java.util.EnumSet;
import java.util.Set;
import java.base.share.classes.jdk.internal.vm.Continuation;
import java.base.share.classes.jdk.internal.vm.ContinuationScope;

import static java.base.share.classes.java.lang.StackWalker.ExtendedOption.LOCALS_AND_OPERANDS;

/**
 * <em>UNSUPPORTED</em> This interface is intended to be package-private
 * or move to an internal package.<p>
 *
 * {@code LiveStackFrame} represents a frame storing data and partial results.
 * Each frame has its own array of local variables (JVMS section 2.6.1),
 * its own operand stack (JVMS section 2.6.2) for a method invocation.
 *
 * @jvms 2.6 Frames
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
/* package-private */
interface LiveStackFrame extends StackFrame {
    /**
     * Return the monitors held by this stack frame. This method returns
     * an empty array if no monitor is held by this stack frame.
     *
     * @return the monitors held by this stack frames
     */
    public Object[] getMonitors();

    /**
     * Gets the local variable array of this stack frame.
     *
     * <p>A single local variable can hold a value of type boolean, byte, char,
     * short, int, float, reference or returnAddress.  A pair of local variables
     * can hold a value of type long or double (JVMS section 2.6.1).  Primitive
     * locals are represented in the returned array as {@code PrimitiveSlot}s,
     * with longs and doubles occupying a pair of consecutive
     * {@code PrimitiveSlot}s.
     *
     * <p>The current VM implementation does not provide specific type
     * information for primitive locals.  This method simply returns the raw
     * contents of the VM's primitive locals on a best-effort basis, without
     * indicating a specific type.
     *
     * <p>The returned array may contain null entries for local variables that
     * are not live.
     *
     * @implNote
     * <p> The specific subclass of {@code PrimitiveSlot} will reflect the
     * underlying architecture, and will be either {@code PrimitiveSlot32} or
     * {@code PrimitiveSlot64}.
     *
     * <p>How a long or double value is stored in the pair of
     * {@code PrimitiveSlot}s can vary based on the underlying architecture and
     * VM implementation.  On 32-bit architectures, long/double values are split
     * between the two {@code PrimitiveSlot32}s.
     * On 64-bit architectures, the entire value may be stored in one of the
     * {@code PrimitiveSlot64}s, with the other {@code PrimitiveSlot64} being
     * unused.
     *
     * <p>The contents of the unused, high-order portion of a
     * {@code PrimitiveSlot64} (when storing a primitive other than a long or
     * double) is unspecified.  In particular, the unused bits are not
     * necessarily zeroed out.
     *
     * @return  the local variable array of this stack frame.
     */
    public Object[] getLocals();

    /**
     * Gets the operand stack of this stack frame.
     *
     * <p>
     * The 0-th element of the returned array represents the top of the operand stack.
     * This method returns an empty array if the operand stack is empty.
     *
     * <p>Each entry on the operand stack can hold a value of any Java Virtual
     * Machine Type.
     * For a value of primitive type, the element in the returned array is
     * a {@link PrimitiveSlot} object; otherwise, the element is the {@code Object}
     * on the operand stack.
     *
     * @return the operand stack of this stack frame.
     */
    public Object[] getStack();

    /**
     * <em>UNSUPPORTED</em> This interface is intended to be package-private
     * or moved to an internal package.<p>
     *
     * Represents a local variable or an entry on the operand stack whose value is
     * of primitive type.
     */
    public abstract class PrimitiveSlot {
        /**
         * Constructor.
         */
        PrimitiveSlot() {}

        /**
         * Returns the size, in bytes, of the slot.
         */
        public abstract int size();

        /**
         * Returns the int value if this primitive value is of size 4
         * @return the int value if this primitive value is of size 4
         *
         * @throws UnsupportedOperationException if this primitive value is not
         * of size 4.
         */
        public int intValue() {
            throw new UnsupportedOperationException("this " + size() + "-byte primitive");
        }

        /**
         * Returns the long value if this primitive value is of size 8
         * @return the long value if this primitive value is of size 8
         *
         * @throws UnsupportedOperationException if this primitive value is not
         * of size 8.
         */
        public long longValue() {
            throw new UnsupportedOperationException("this " + size() + "-byte primitive");
        }
    }


    /**
     * Gets {@code StackWalker} that can get locals and operands.
     *
     * @throws SecurityException if the security manager is present and
     * denies access to {@code RuntimePermission("liveStackFrames")}
     */
    public static StackWalker getStackWalker() {
        return getStackWalker(EnumSet.noneOf(StackWalker.Option.class));
    }

    /**
     * Gets a {@code StackWalker} instance with the given options specifying
     * the stack frame information it can access, and which will traverse at most
     * the given {@code maxDepth} number of stack frames.  If no option is
     * specified, this {@code StackWalker} obtains the method name and
     * the class name with all
     * {@linkplain StackWalker.Option#SHOW_HIDDEN_FRAMES hidden frames} skipped.
     * The returned {@code StackWalker} can get locals and operands.
     *
     * @param options stack walk {@link StackWalker.Option options}
     *
     * @throws SecurityException if the security manager is present and
     * it denies access to {@code RuntimePermission("liveStackFrames")};
     * or if the given {@code options} contains
     * {@link StackWalker.Option#RETAIN_CLASS_REFERENCE Option.RETAIN_CLASS_REFERENCE}
     * and it denies access to {@code RuntimePermission("getStackWalkerWithClassReference")}.
     */
    public static StackWalker getStackWalker(Set<StackWalker.Option> options) {
        return getStackWalker(options, null);
    }

    /**
     * Gets a {@code StackWalker} instance with the given options specifying
     * the stack frame information it can access, and which will traverse at most
     * the given {@code maxDepth} number of stack frames.  If no option is
     * specified, this {@code StackWalker} obtains the method name and
     * the class name with all
     * {@linkplain StackWalker.Option#SHOW_HIDDEN_FRAMES hidden frames} skipped.
     * The returned {@code StackWalker} can get locals and operands.
     *
     * @param options stack walk {@link StackWalker.Option options}
     * @param contScope the continuation scope up to which (inclusive) to walk the stack
     *
     * @throws SecurityException if the security manager is present and
     * it denies access to {@code RuntimePermission("liveStackFrames")}; or
     * or if the given {@code options} contains
     * {@link StackWalker.Option#RETAIN_CLASS_REFERENCE Option.RETAIN_CLASS_REFERENCE}
     * and it denies access to {@code RuntimePermission("getStackWalkerWithClassReference")}.
     */
    public static StackWalker getStackWalker(Set<StackWalker.Option> options, ContinuationScope contScope) {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("liveStackFrames"));
        }
        return StackWalker.newInstance(options, LOCALS_AND_OPERANDS, contScope);
    }

    /**
     * Gets {@code StackWalker} of the given unmounted continuation, that can get locals and operands.
     *
     * @param continuation the continuation to walk
     *
     * @throws SecurityException if the security manager is present and
     * denies access to {@code RuntimePermission("liveStackFrames")}
     */
    public static StackWalker getStackWalker(Continuation continuation) {
        return getStackWalker(EnumSet.noneOf(StackWalker.Option.class), continuation.getScope(), continuation);
    }

    /**
     * Gets a {@code StackWalker} instance with the given options specifying
     * the stack frame information it can access, and which will traverse at most
     * the given {@code maxDepth} number of stack frames.  If no option is
     * specified, this {@code StackWalker} obtains the method name and
     * the class name with all
     * {@linkplain StackWalker.Option#SHOW_HIDDEN_FRAMES hidden frames} skipped.
     * The returned {@code StackWalker} can get locals and operands.
     *
     * @param options stack walk {@link StackWalker.Option options}
     * @param continuation the continuation to walk
     *
     * @throws SecurityException if the security manager is present and
     * it denies access to {@code RuntimePermission("liveStackFrames")}; or
     * or if the given {@code options} contains
     * {@link StackWalker.Option#RETAIN_CLASS_REFERENCE Option.RETAIN_CLASS_REFERENCE}
     * and it denies access to {@code RuntimePermission("getStackWalkerWithClassReference")}.
     */
    public static StackWalker getStackWalker(Set<StackWalker.Option> options,
                                             ContinuationScope contScope,
                                             Continuation continuation) {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("liveStackFrames"));
        }
        return StackWalker.newInstance(options, LOCALS_AND_OPERANDS, contScope, continuation);
    }
}
