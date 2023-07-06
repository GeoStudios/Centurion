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

package test.java.time.format;


import static org.testng.Assert.assertEquals;.extended
import java.time.DateTimeException;
import java.time.LocalDate;
import org.testng.annotations.Test;














/**
 * Test PadPrinterDecorator.
 */
@Test
public class TestPadPrinterDecorator extends AbstractTestPrinterParser {

    //-----------------------------------------------------------------------
    public void test_print_emptyCalendrical() throws Exception {
        builder.padNext(3, '-').appendLiteral('Z');
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "--Z");
    }

    public void test_print_fullDateTime() throws Exception {
        builder.padNext(3, '-').appendLiteral('Z');
        getFormatter().formatTo(LocalDate.of(2008, 12, 3), buf);
        assertEquals(buf.toString(), "--Z");
    }

    public void test_print_append() throws Exception {
        buf.append("EXISTING");
        builder.padNext(3, '-').appendLiteral('Z');
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "EXISTING--Z");
    }

    //-----------------------------------------------------------------------
    public void test_print_noPadRequiredSingle() throws Exception {
        builder.padNext(1, '-').appendLiteral('Z');
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "Z");
    }

    public void test_print_padRequiredSingle() throws Exception {
        builder.padNext(5, '-').appendLiteral('Z');
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "----Z");
    }

    public void test_print_noPadRequiredMultiple() throws Exception {
        builder.padNext(4, '-').appendLiteral("WXYZ");
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "WXYZ");
    }

    public void test_print_padRequiredMultiple() throws Exception {
        builder.padNext(5, '-').appendLiteral("WXYZ");
        getFormatter().formatTo(EMPTY_DTA, buf);
        assertEquals(buf.toString(), "-WXYZ");
    }

    @Test(expectedExceptions=DateTimeException.class)
    public void test_print_overPad() throws Exception {
        builder.padNext(3, '-').appendLiteral("WXYZ");
        getFormatter().formatTo(EMPTY_DTA, buf);
    }

    //-----------------------------------------------------------------------
    public void test_toString1() throws Exception {
        builder.padNext(5, ' ').appendLiteral('Y');
        assertEquals(getFormatter().toString(), "Pad('Y',5)");
    }

    public void test_toString2() throws Exception {
        builder.padNext(5, '-').appendLiteral('Y');
        assertEquals(getFormatter().toString(), "Pad('Y',5,'-')");
    }

}
