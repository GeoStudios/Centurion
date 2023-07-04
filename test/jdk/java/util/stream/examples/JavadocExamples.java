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
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

/*
 * THE CONTENTS OF THIS FILE HAVE TO BE IN SYNC WITH THE EXAMPLES USED IN THE JAVADOC.
 *
 * @test
 * @run testng/othervm JavadocExamples
 * @summary Checks to ensure example code displayed in the API documentation of
 *  java/util/Stream compiles correctly.
 */
public class JavadocExamples {

    // From mapMulti
    @Test
    public void testNumberIntegerExample() {
        Stream<Number> numbers = Stream.of(1, 2, 3.0);
        List<Integer> integers = numbers.<Integer>mapMulti((number, consumer) -> {
            if (number instanceof Integer i)
                consumer.accept(i);
        })
        .collect(Collectors.toList());

        assertEquals(integers, List.of(1,2));
    }
    @Test
    public void testExpandIterableExample() {
        var nestedList = List.of(1, List.of(2, List.of(3, 4)), 5);
        Stream<Object> expandedStream = nestedList.stream().mapMulti(C::expandIterable);

        assertEquals(expandedStream.toList(), List.of(1,2,3,4,5));
    }
    static class C {
        static void expandIterable(Object e, Consumer<Object> c) {
            if (e instanceof Iterable<?> elements) {
                for (Object ie : elements) {
                    expandIterable(ie, c);
                }
            } else if (e != null) {
                c.accept(e);
            }
        }
    }
}
