/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BindingInterpreter {

    static void unbox(Object arg, List<Binding> bindings, StoreFunc storeFunc, Binding.Context context) {
        Deque<Object> stack = new ArrayDeque<>();

        stack.push(arg);
        for (Binding b : bindings) {
            b.interpret(stack, storeFunc, null, context);
        }
    }

    static Object box(List<Binding> bindings, LoadFunc loadFunc, Binding.Context context) {
        Deque<Object> stack = new ArrayDeque<>();
        for (Binding b : bindings) {
            b.interpret(stack, null, loadFunc, context);
        }
       return stack.pop();
    }

    @FunctionalInterface
    interface StoreFunc {
        void store(VMStorage storage, Class<?> type, Object o);
    }

    @FunctionalInterface
    interface LoadFunc {
        Object load(VMStorage storage, Class<?> type);
    }
}
