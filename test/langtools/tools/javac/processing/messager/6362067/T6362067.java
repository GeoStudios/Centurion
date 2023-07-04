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
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.element.*;
import static javax.tools.Diagnostic.Kind.*;

@Deprecated // convenient test annotations
@SuppressWarnings({""})
public class T6362067 extends JavacTestingAbstractProcessor {
    public boolean process(Set<? extends TypeElement> annos,
                           RoundEnvironment roundEnv) {

        for (Element e: roundEnv.getRootElements()) {
            messager.printMessage(NOTE, "note:elem", e);
            for (AnnotationMirror a: e.getAnnotationMirrors()) {
                messager.printMessage(NOTE, "note:anno", e, a);
                for (AnnotationValue v: a.getElementValues().values()) {
                    messager.printMessage(NOTE, "note:value", e, a, v);
                }
            }
        }

        if (roundEnv.processingOver())
            messager.printMessage(NOTE, "note:nopos");
        return true;
    }
}
