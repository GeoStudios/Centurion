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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import static javax.tools.Diagnostic.Kind.NOTE;

class Annotated {
    @A(1)
    int a1;

    @A(value = 2)
    int a2;

    @A(value = {3})
    int a3;

    @A(value = {4, 5})
    int a4;

    @B(x = @C(x = E.ONE, y = E.TWO), y = @C(x = E.ONE, y = E.TWO))
    int b;
}

@Retention(RetentionPolicy.RUNTIME)
@interface A {
    int[] value() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@interface B {
    C x() default @C;

    C y() default @C;
}

@Retention(RetentionPolicy.RUNTIME)
@interface C {
    E x() default E.ONE;

    E y() default E.ONE;
}

enum E {
    ONE,
    TWO
}

public class T6388543 extends JavacTestingAbstractProcessor {
    public boolean process(Set<? extends TypeElement> annos, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }
        for (Element e : elements.getTypeElement("Annotated").getEnclosedElements()) {
            for (AnnotationMirror a : e.getAnnotationMirrors()) {
                for (AnnotationValue v : a.getElementValues().values()) {
                    printValue(e, a, v);
                }
            }
        }
        return false;
    }

    private void printValue(Element e, AnnotationMirror a, AnnotationValue v) {
        messager.printMessage(NOTE, String.format("note:value %s + %s", a, v), e, a, v);
        v.accept(
                new SimpleAnnotationValueVisitor<Void, Void>() {
                    @Override
                    public Void visitArray(List<? extends AnnotationValue> values, Void unused) {
                        for (AnnotationValue value : values) {
                            printValue(e, a, value);
                        }
                        return null;
                    }

                    @Override
                    public Void visitAnnotation(AnnotationMirror nestedAnnotation, Void unused) {
                        for (AnnotationValue value : nestedAnnotation.getElementValues().values()) {
                            printValue(e, a, value);
                        }
                        return null;
                    }
                },
                null);
    }
}
