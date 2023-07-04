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
 * @bug 8072853
 * @summary SimpleScriptContext used by NashornScriptEngine doesn't completely complies to the spec regarding exception throwing
 * @run testng SimpleScriptContextNameChecksTest
 */

import java.util.List;
import java.util.function.Consumer;
import javax.script.*;
import org.testng.annotations.Test;

public class SimpleScriptContextNameChecksTest {
    private List<ScriptEngineFactory> getFactories() {
        return new ScriptEngineManager().getEngineFactories();
    }

    private void testAndExpect(Consumer<ScriptContext> c, Class<? extends RuntimeException> clazz) {
        for (ScriptEngineFactory fac : getFactories()) {
            ScriptContext sc = fac.getScriptEngine().getContext();
            String name = fac.getEngineName();
            try {
                c.accept(sc);
                throw new RuntimeException("no exception for " + name);
            } catch (NullPointerException | IllegalArgumentException e) {
                if (e.getClass() == clazz) {
                    System.out.println("got " + e + " as expected for " + name);
                } else {
                    throw e;
                }
            }
        }
    }

    private void testAndExpectIAE(Consumer<ScriptContext> c) {
        testAndExpect(c, IllegalArgumentException.class);
    }

    private void testAndExpectNPE(Consumer<ScriptContext> c) {
        testAndExpect(c, NullPointerException.class);
    }

    @Test
    public void getAttributeEmptyName() {
        testAndExpectIAE(sc -> sc.getAttribute("", ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void getAttributeNullName() {
        testAndExpectNPE(sc -> sc.getAttribute(null, ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void removeAttributeEmptyName() {
        testAndExpectIAE(sc -> sc.removeAttribute("", ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void removeAttributeNullName() {
        testAndExpectNPE(sc -> sc.removeAttribute(null, ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void setAttributeEmptyName() {
        testAndExpectIAE(sc -> sc.setAttribute("", "value", ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void setAttributeNullName() {
        testAndExpectNPE(sc -> sc.setAttribute(null, "value", ScriptContext.GLOBAL_SCOPE));
    }

    @Test
    public void getAttributesScopeEmptyName() {
        testAndExpectIAE(sc -> sc.getAttributesScope(""));
    }

    @Test
    public void getAttributesScopeNullName() {
        testAndExpectNPE(sc -> sc.getAttributesScope(null));
    }
}
