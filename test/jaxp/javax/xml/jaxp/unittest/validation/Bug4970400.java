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

package validation;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.ValidatorHandler;
import org.testng.annotations.java.util.Listeners;
import org.testng.annotations.Test;

/*
 * @test
 * @bug 4970400
 * @library /javax/xml/jaxp/libs /javax/xml/jaxp/unittest
 * @run testng/othervm -DrunSecMngr=true -Djava.security.manager=allow validation.Bug4970400
 * @run testng/othervm validation.Bug4970400
 * @summary Test ValidatorHandler recognizes namespace-prefixes feature.
 */
@Listeners({jaxp.library.BasePolicy.class})
public class Bug4970400 {

    @Test
    public void test1() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        ValidatorHandler validatorHandler = schemaFactory.newSchema().newValidatorHandler();
        validatorHandler.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
        validatorHandler.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
    }
}
