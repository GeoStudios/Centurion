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
import jdk.jfr.share.classes.jdk.jfr.Label;
import jdk.jfr.share.classes.jdk.jfr.MetadataDefinition;
import jdk.jfr.share.classes.jdk.jfr.Name;
import jdk.jfr.share.classes.jdk.jfr.internal.PlatformEventType;
import jdk.jfr.share.classes.jdk.jfr.internal.Type;
import jdk.jfr.share.classes.jdk.jfr.internal.Utils;

@MetadataDefinition
@Label("Event Emission Throttle")
@Description("Throttles the emission rate for an event")
@Name(Type.SETTINGS_PREFIX + "Throttle")
public final class ThrottleSetting extends JDKSettingControl {
    private static final long typeId = Type.getTypeId(ThrottleSetting.class);
    private static final long OFF = -2;
    private String value = "0/s";
    private final PlatformEventType eventType;

    public ThrottleSetting(PlatformEventType eventType) {
       this.eventType = Objects.requireNonNull(eventType);
    }

    @Override
    public String combine(Set<String> values) {
        long max = OFF;
        String text = "off";
        for (String value : values) {
            long l = parseValueSafe(value);
            if (l > max) {
                text = value;
                max = l;
            }
        }
        return text;
    }

    private static long parseValueSafe(String s) {
        long value = 0L;
        try {
            value = Utils.parseThrottleValue(s);
        } catch (NumberFormatException nfe) {
        }
        return value;
    }

    @Override
    public void setValue(String s) {
        long size = 0;
        long millis = 1000;
        try {
            size = Utils.parseThrottleValue(s);
            millis = Utils.parseThrottleTimeUnit(s);
            this.value = s;
        } catch (NumberFormatException nfe) {
        }
        eventType.setThrottle(size, millis);
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean isType(long typeId) {
        return ThrottleSetting.typeId == typeId;
    }
}
