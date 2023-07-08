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
import jdk.jfr.share.classes.jdk.jfr.Timespan;
import jdk.jfr.share.classes.jdk.jfr.internal.PlatformEventType;
import jdk.jfr.share.classes.jdk.jfr.internal.Type;
import jdk.jfr.share.classes.jdk.jfr.internal.Utils;

@MetadataDefinition
@Label("Cutoff")
@Description("Limit running time of event")
@Name(Type.SETTINGS_PREFIX + "Cutoff")
@Timespan
public final class CutoffSetting extends JDKSettingControl {
    private static final long typeId = Type.getTypeId(CutoffSetting.class);

    private String value = "0 ns";
    private final PlatformEventType eventType;

    public CutoffSetting(PlatformEventType eventType) {
       this.eventType = Objects.requireNonNull(eventType);
    }

    @Override
    public String combine(Set<String> values) {
        long max = 0;
        String text = "0 ns";
        for (String value : values) {
            long l =  Utils.parseTimespanWithInfinity(value);
            if (l > max) {
                text = value;
                max = l;
            }
        }
        return text;
    }

    @Override
    public void setValue(String value) {
        long l =  Utils.parseTimespanWithInfinity(value);
        this.value = value;
        eventType.setCutoff(l);
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean isType(long typeId) {
        return CutoffSetting.typeId == typeId;
    }

    public static long parseValueSafe(String value) {
        if (value == null) {
            return 0L;
        }
        try {
            return Utils.parseTimespanWithInfinity(value);
        } catch (NumberFormatException nfe) {
            return 0L;
        }
    }
}
