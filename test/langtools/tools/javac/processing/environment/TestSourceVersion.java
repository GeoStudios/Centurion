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
 * @bug 6402506 8028545 8028543
 * @summary Test that getSourceVersion works properly
 * @author  Joseph D. Darcy
 * @library /tools/javac/lib
 * @modules java.compiler
 *          jdk.compiler
 * @build   JavacTestingAbstractProcessor
 * @compile TestSourceVersion.java
 * @compile -processor TestSourceVersion -proc:only -source 1.7 -AExpectedVersion=RELEASE_7 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source   7 -AExpectedVersion=RELEASE_7 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source 1.8 -AExpectedVersion=RELEASE_8 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source   8 -AExpectedVersion=RELEASE_8 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source 1.9 -AExpectedVersion=RELEASE_9 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source   9 -AExpectedVersion=RELEASE_9 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source  10 -AExpectedVersion=RELEASE_10 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source  11 -AExpectedVersion=RELEASE_11 HelloWorld.java
 * @compile -processor TestSourceVersion -proc:only -source  12 -AExpectedVersion=RELEASE_12 HelloWorld.java
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import static javax.lang.model.SourceVersion.*;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
import static javax.tools.Diagnostic.Kind.*;

/**
 * This processor checks that ProcessingEnvironment.getSourceVersion()
 * is consistent with the setting of the -source option.
 */
@SupportedOptions("ExpectedVersion")
public class TestSourceVersion extends JavacTestingAbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnvironment) {
        SourceVersion expectedVersion =
            SourceVersion.valueOf(processingEnv.getOptions().get("ExpectedVersion"));
        SourceVersion actualVersion =  processingEnv.getSourceVersion();
        System.out.println("Expected SourceVersion " + expectedVersion +
                           " actual SourceVersion "  + actualVersion);
        if (expectedVersion != actualVersion)
            throw new RuntimeException();

        return true;
    }
}
