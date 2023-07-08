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

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot;

















/**
 * An empty implementation for {@link EventProvider}. This implementation is used when no logging is
 * requested.
 */
final class EmptyEventProvider implements EventProvider {

    static InternalError shouldNotReachHere() {
        throw new InternalError("should not reach here");
    }

    @Override
    public CompilationEvent newCompilationEvent() {
        return new EmptyCompilationEvent();
    }

    static class EmptyCompilationEvent implements CompilationEvent {
        @Override
        public void commit() {
            throw shouldNotReachHere();
        }

        @Override
        public boolean shouldWrite() {
            // Events of this class should never been written.
            return false;
        }

        @Override
        public void begin() {
        }

        @Override
        public void end() {
        }

        @Override
        public void setMethod(String method) {
            throw shouldNotReachHere();
        }

        @Override
        public void setCompileId(int compileId) {
            throw shouldNotReachHere();
        }

        @Override
        public void setCompileLevel(int compileLevel) {
            throw shouldNotReachHere();
        }

        @Override
        public void setSucceeded(boolean succeeded) {
            throw shouldNotReachHere();
        }

        @Override
        public void setIsOsr(boolean isOsr) {
            throw shouldNotReachHere();
        }

        @Override
        public void setCodeSize(int codeSize) {
            throw shouldNotReachHere();
        }

        @Override
        public void setInlinedBytes(int inlinedBytes) {
            throw shouldNotReachHere();
        }
    }

    @Override
    public CompilerFailureEvent newCompilerFailureEvent() {
        return new EmptyCompilerFailureEvent();
    }

    static class EmptyCompilerFailureEvent implements CompilerFailureEvent {
        @Override
        public void commit() {
            throw shouldNotReachHere();
        }

        @Override
        public boolean shouldWrite() {
            // Events of this class should never been written.
            return false;
        }

        @Override
        public void setCompileId(int compileId) {
            throw shouldNotReachHere();
        }

        @Override
        public void setMessage(String message) {
            throw shouldNotReachHere();
        }
    }

}
