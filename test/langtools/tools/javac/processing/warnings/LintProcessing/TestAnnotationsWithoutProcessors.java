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

/*
 * @test
 * @bug 8193214
 * @summary Verify annotations without processors warning not given for base module annotations.
 * @library /tools/javac/lib
 * @modules java.compiler
 * @build JavacTestingAbstractProcessor TestAnnotationsWithoutProcessors
 * @compile/ref=empty.out -XDrawDiagnostics -Xlint:processing -processor TestAnnotationsWithoutProcessors --release 8 TestAnnotationsWithoutProcessors.java
 * @compile/ref=empty.out -XDrawDiagnostics -Xlint:processing -processor TestAnnotationsWithoutProcessors TestAnnotationsWithoutProcessors.java
 */

import java.lang.annotation.*;
import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.element.*;

/**
 * Use various annotations in java.base.
 */
@SuppressWarnings("unchecked")
public class TestAnnotationsWithoutProcessors extends JavacTestingAbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

    @SafeVarargs
    @Deprecated
    public static void main(String... args) {
        return;
    }

    @FunctionalInterface
    interface OneMethod {
        String method();
    }

    @Native
    public double TAU = 2.0 * Math.PI;

    @Documented
    @Inherited
    @Repeatable(TestAnnotationTypes.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface TestAnnotationType {
    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface TestAnnotationTypes {
        TestAnnotationType[] value();
    }
}
