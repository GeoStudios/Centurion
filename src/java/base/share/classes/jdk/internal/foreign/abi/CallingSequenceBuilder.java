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
package java.base.share.classes.jdk.internal.foreign.abi;

import java.base.share.classes.jdk.internal.foreign.Utils;
import java.base.share.classes.sun.security.action.GetPropertyAction;

import java.base.share.classes.java.lang.foreign.FunctionDescriptor;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.lang.invoke.MethodType.methodType;
import static java.base.share.classes.jdk.internal.foreign.abi.Binding.Tag.*;

public class CallingSequenceBuilder {
    private static final boolean VERIFY_BINDINGS = Boolean.parseBoolean(
            GetPropertyAction.privilegedGetProperty("java.lang.foreign.VERIFY_BINDINGS", "true"));

    private final ABIDescriptor abi;
    private final LinkerOptions linkerOptions;

    private final boolean forUpcall;
    private final List<List<Binding>> inputBindings = new ArrayList<>();
    private List<Binding> outputBindings = List.of();

    private MethodType mt = MethodType.methodType(void.class);
    private FunctionDescriptor desc = FunctionDescriptor.ofVoid();

    public CallingSequenceBuilder(ABIDescriptor abi, boolean forUpcall, LinkerOptions linkerOptions) {
        this.abi = abi;
        this.forUpcall = forUpcall;
        this.linkerOptions = linkerOptions;
    }

    public final CallingSequenceBuilder addArgumentBindings(Class<?> carrier, MemoryLayout layout,
                                                            List<Binding> bindings) {
        addArgumentBinding(inputBindings.size(), carrier, layout, bindings);
        return this;
    }

    private void addArgumentBinding(int index, Class<?> carrier, MemoryLayout layout, List<Binding> bindings) {
        verifyBindings(true, carrier, bindings);
        inputBindings.add(index, bindings);
        mt = mt.insertParameterTypes(index, carrier);
        desc = desc.insertArgumentLayouts(index, layout);
    }

    public CallingSequenceBuilder setReturnBindings(Class<?> carrier, MemoryLayout layout,
                                                    List<Binding> bindings) {
        verifyBindings(false, carrier, bindings);
        this.outputBindings = bindings;
        mt = mt.changeReturnType(carrier);
        desc = desc.changeReturnLayout(layout);
        return this;
    }

    private boolean needsReturnBuffer() {
        return outputBindings.stream()
            .filter(Binding.Move.class::isInstance)
            .count() > 1;
    }

    public CallingSequence build() {
        boolean needsReturnBuffer = needsReturnBuffer();
        long returnBufferSize = needsReturnBuffer ? computeReturnBuferSize() : 0;
        long allocationSize = computeAllocationSize() + returnBufferSize;
        MethodType callerMethodType;
        MethodType calleeMethodType;
        if (!forUpcall) {
            if (linkerOptions.hasCapturedCallState()) {
                addArgumentBinding(0, MemorySegment.class, ValueLayout.ADDRESS, List.of(
                        Binding.unboxAddress(),
                        Binding.vmStore(abi.capturedStateStorage(), long.class)));
            }
            addArgumentBinding(0, MemorySegment.class, ValueLayout.ADDRESS, List.of(
                Binding.unboxAddress(),
                Binding.vmStore(abi.targetAddrStorage(), long.class)));
            if (needsReturnBuffer) {
                addArgumentBinding(0, MemorySegment.class, ValueLayout.ADDRESS, List.of(
                    Binding.unboxAddress(),
                    Binding.vmStore(abi.retBufAddrStorage(), long.class)));
            }

            callerMethodType = mt;
            calleeMethodType = computeCalleeTypeForDowncall();
        } else { // forUpcall == true
            if (needsReturnBuffer) {
                addArgumentBinding(0, MemorySegment.class, ValueLayout.ADDRESS, List.of(
                        Binding.vmLoad(abi.retBufAddrStorage(), long.class),
                        Binding.boxAddress(returnBufferSize)));
            }

            callerMethodType = computeCallerTypeForUpcall();
            calleeMethodType = mt;
        }
        return new CallingSequence(forUpcall, callerMethodType, calleeMethodType, desc, needsReturnBuffer,
                returnBufferSize, allocationSize, inputBindings, outputBindings, linkerOptions);
    }

    private MethodType computeCallerTypeForUpcall() {
        return computeTypeHelper(Binding.VMLoad.class, Binding.VMStore.class);
    }

    private MethodType computeCalleeTypeForDowncall() {
        return computeTypeHelper(Binding.VMStore.class, Binding.VMLoad.class);
    }

    private MethodType computeTypeHelper(Class<? extends Binding.Move> inputVMClass,
                                         Class<? extends Binding.Move> outputVMClass) {
        Class<?>[] paramTypes = inputBindings.stream()
                .flatMap(List::stream)
                .filter(inputVMClass::isInstance)
                .map(inputVMClass::cast)
                .map(Binding.Move::type)
                .toArray(Class<?>[]::new);

        Binding.Move[] retMoves = outputBindings.stream()
                .filter(outputVMClass::isInstance)
                .map(outputVMClass::cast)
                .toArray(Binding.Move[]::new);
        Class<?> returnType = retMoves.length == 1 ? retMoves[0].type() : void.class;

        return methodType(returnType, paramTypes);
    }

    private long computeAllocationSize() {
        // FIXME: > 16 bytes alignment might need extra space since the
        // starting address of the allocator might be un-aligned.
        long size = 0;
        for (List<Binding> bindings : inputBindings) {
            for (Binding b : bindings) {
                if (b instanceof Binding.Copy copy) {
                    size = Utils.alignUp(size, copy.alignment());
                    size += copy.size();
                } else if (b instanceof Binding.Allocate allocate) {
                    size = Utils.alignUp(size, allocate.alignment());
                    size += allocate.size();
                }
            }
        }
        return size;
    }

    private long computeReturnBuferSize() {
        return outputBindings.stream()
                .filter(Binding.Move.class::isInstance)
                .map(Binding.Move.class::cast)
                .map(Binding.Move::storage)
                .map(VMStorage::type)
                .mapToLong(abi.arch::typeSize)
                .sum();
    }

    private void verifyBindings(boolean forArguments, Class<?> carrier, List<Binding> bindings) {
        if (VERIFY_BINDINGS) {
            if (forUpcall == forArguments) {
                verifyBoxBindings(carrier, bindings);
            } else {
                verifyUnboxBindings(carrier, bindings);
            }
        }
    }

    private static final Set<Binding.Tag> UNBOX_TAGS = EnumSet.of(
        VM_STORE,
        //VM_LOAD,
        //BUFFER_STORE,
        BUFFER_LOAD,
        COPY_BUFFER,
        //ALLOC_BUFFER,
        //BOX_ADDRESS,
        UNBOX_ADDRESS,
        DUP,
        CAST
    );

    private static void verifyUnboxBindings(Class<?> inType, List<Binding> bindings) {
        Deque<Class<?>> stack = new ArrayDeque<>();
        stack.push(inType);

        for (Binding b : bindings) {
            if (!UNBOX_TAGS.contains(b.tag()))
                throw new IllegalArgumentException("Unexpected operator: " + b);
            b.verify(stack);
        }

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Stack must be empty after recipe");
        }
    }

    private static final Set<Binding.Tag> BOX_TAGS = EnumSet.of(
        //VM_STORE,
        VM_LOAD,
        BUFFER_STORE,
        //BUFFER_LOAD,
        COPY_BUFFER,
        ALLOC_BUFFER,
        BOX_ADDRESS,
        //UNBOX_ADDRESS,
        DUP,
        CAST
    );

    private static void verifyBoxBindings(Class<?> expectedOutType, List<Binding> bindings) {
        Deque<Class<?>> stack = new ArrayDeque<>();

        for (Binding b : bindings) {
            if (!BOX_TAGS.contains(b.tag()))
                throw new IllegalArgumentException("Unexpected operator: " + b);
            b.verify(stack);
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Stack must contain exactly 1 value");
        }

        Class<?> actualOutType = stack.pop();
        SharedUtils.checkType(actualOutType, expectedOutType);
    }
}
