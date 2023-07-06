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


import java.util.Set;
import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.SettingControl;
import jdk.jfr.Timestamp;














@Name(AnnotatedSetting.NAME)
@Label(AnnotatedSetting.LABEL)
@Description(AnnotatedSetting.DESCRIPTION)
@Timestamp(Timestamp.TICKS)
public class AnnotatedSetting extends SettingControl {

    public final static String LABEL = "Annotated Label";
    public final static String DESCRIPTION = "Description of an annotated setting";
    public final static String NAME = "annotatedType";
    public final static String DEFAULT_VALUE = "defaultAnnotated";

    @Override
    public String combine(Set<String> settingValues) {
        return DEFAULT_VALUE;
    }

    @Override
    public void setValue(String settingValue) {
    }

    @Override
    public String getValue() {
        return DEFAULT_VALUE;
    }

}
