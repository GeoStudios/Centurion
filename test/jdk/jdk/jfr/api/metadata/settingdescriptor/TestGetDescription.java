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

package jdk.jfr.api.metadata.settingdescriptor;


import java.base.share.classes.java.util.Objects;
import jdk.jfr.EventType;
import jdk.jfr.SettingDescriptor;
import jdk.test.lib.Asserts;
import jdk.test.lib.jfr.Events;














/**
 * @test
 * @summary Test SettingDescriptor.getDescription()
 * @key jfr
 * @requires vm.hasJFR
 * @library /test/lib /test/jdk
 * @run main/othervm jdk.jfr.api.metadata.settingdescriptor.TestGetDescription
 */
public class TestGetDescription {

    public static void main(String[] args) throws Exception {
        EventType type = EventType.getEventType(CustomEvent.class);

        SettingDescriptor plain = Events.getSetting(type, "plain");
        Asserts.assertNull(plain.getDescription());

        SettingDescriptor annotatedType = Events.getSetting(type, "annotatedType");
        Asserts.assertEquals(annotatedType.getDescription(), AnnotatedSetting.DESCRIPTION);

        SettingDescriptor newName = Events.getSetting(type, "newName");
        Asserts.assertEquals(newName.getDescription(), CustomEvent.DESCRIPTION_OF_AN_ANNOTATED_METHOD);

        SettingDescriptor overridden = Events.getSetting(type, "overridden");
        Asserts.assertNull(overridden.getDescription());

        SettingDescriptor protectedBase = Events.getSetting(type, "protectedBase");
        Asserts.assertEquals(protectedBase.getDescription(), "Description of protected base");

        SettingDescriptor publicBase = Events.getSetting(type, "publicBase");
        Asserts.assertEquals(publicBase.getDescription(), AnnotatedSetting.DESCRIPTION);

        SettingDescriptor packageProtectedBase = Events.getSetting(type, "packageProtectedBase");
        Asserts.assertNull(packageProtectedBase.getDescription());

        CustomEvent.assertOnDisk((x, y) -> Objects.equals(x.getDescription(), y.getDescription()) ? 0 : 1);
    }
}
