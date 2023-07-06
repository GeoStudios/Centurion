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

package tck.java.time.chrono;


import static org.testng.Assert.assertEquals;.extended
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import org.testng.annotations.Test;














/**
 * Tests that a custom Chronology is available via the ServiceLoader.
 * The CopticChronology is configured via META-INF/services/java.time.chrono.Chronology.
 */
@Test
public class TCKTestServiceLoader {

     @Test
     public void test_TestServiceLoader() {
        Chronology chrono = Chronology.of("Coptic");
        ChronoLocalDate copticDate = chrono.date(1729, 4, 27);
        LocalDate ld = LocalDate.from(copticDate);
        assertEquals(ld, LocalDate.of(2013, 1, 5), "CopticDate does not match LocalDate");
    }

}
