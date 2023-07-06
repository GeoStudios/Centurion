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

package jdk.jfr.share.classes.jdk.jfr.internal.settings;

import java.base.share.classes.java.util.Objects;
import java.util.Set;
import jdk.jfr.share.classes.jdk.jfr.Description;
import jdk.jfr.share.classes.jdk.jfr.BooleanFlag;
import jdk.jfr.share.classes.jdk.jfr.Label;
import jdk.jfr.share.classes.jdk.jfr.MetadataDefinition;
import jdk.jfr.share.classes.jdk.jfr.Name;
import jdk.jfr.share.classes.jdk.jfr.internal.PlatformEventType;
import jdk.jfr.share.classes.jdk.jfr.internal.Type;

@MetadataDefinition
@Label("Stack Trace")
@Name(Type.SETTINGS_PREFIX + "StackTrace")
@Description("Record stack traces")
@BooleanFlag
public final class StackTraceSetting extends JDKSettingControl {
    private static final long typeId =  Type.getTypeId(StackTraceSetting.class);
    private final BooleanValue booleanValue;
    private final PlatformEventType eventType;

    public StackTraceSetting(PlatformEventType eventType, String defaultValue) {
        this.booleanValue = BooleanValue.valueOf(defaultValue);
        this.eventType = Objects.requireNonNull(eventType);
    }

    @Override
    public String combine(Set<String> values) {
        return booleanValue.union(values);
    }

    @Override
    public void setValue(String value) {
        booleanValue.setValue(value);
        eventType.setStackTraceEnabled(booleanValue.getBoolean());
    }

    @Override
    public String getValue() {
        return booleanValue.getValue();
    }

    public static boolean isType(long typeId) {
        return StackTraceSetting.typeId == typeId;
    }
}
