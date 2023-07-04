/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class T8074381b {

    @SuppressWarnings("unchecked")
    public Invocation resolve(Handler handler) {
        return new Invocation((t) -> handler.handle((String) t));
    }

    public static class Handler {
        public void handle(String s) {
            System.out.println(s);
        }
    }

    public static class Invocation<T> {
        public final ThrowingConsumer<T> consumer;

        public Invocation(final ThrowingConsumer<T> consumer) {
            this.consumer = consumer;
        }
    }

    @FunctionalInterface
    public interface ThrowingConsumer<T> extends BiConsumer<T,Consumer<Throwable>> {
        @Override
        default void accept(final T elem, final Consumer<Throwable> errorHandler) {
            try {
                acceptThrows(elem);
            } catch (final Throwable e) {
                errorHandler.accept(e);
            }
        }

        void acceptThrows(T elem) throws Throwable;
    }
}
