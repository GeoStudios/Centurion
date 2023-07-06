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


import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Frequency;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.SettingDefinition;














public abstract class BaseEvent extends Event {

    // should be shadowed by built-in setting enabled
    @SettingDefinition
    public boolean enabled(PlainSetting ps) {
        return false;
    }

    @SettingDefinition
    public boolean publicBase(AnnotatedSetting control) {
        return true;
    }

    @SettingDefinition
    private boolean privateBase(PlainSetting control) {
        return true;
    }

    @SettingDefinition
    @Name("protectedBase")
    @Label("Protected Base")
    @Description("Description of protected base")
    @Frequency
    protected boolean something(PlainSetting control) {
        return true;
    }

    @SettingDefinition
    boolean packageProtectedBase(PlainSetting control) {
        return true;
    }

    @Name("baseName")
    @Label("Base Label")
    @Description("Base description")
    @SettingDefinition
    public boolean overridden(AnnotatedSetting control) {
        return true;
    }
}
