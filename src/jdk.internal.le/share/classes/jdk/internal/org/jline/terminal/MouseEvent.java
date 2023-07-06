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

package jdk.internal.le.share.classes.jdk.internal.org.jline.terminal;


import java.util.EnumSet;















public class MouseEvent {

    public enum Type {
        Released,
        Pressed,
        Wheel,
        Moved,
        Dragged
    }

    public enum Button {
        NoButton,
        Button1,
        Button2,
        Button3,
        WheelUp,
        WheelDown
    }

    public enum Modifier {
        Shift,
        Alt,
        Control
    }

    private final Type type;
    private final Button button;
    private final EnumSet<Modifier> modifiers;
    private final int x;
    private final int y;

    public MouseEvent(Type type, Button button, EnumSet<Modifier> modifiers, int x, int y) {
        this.type = type;
        this.button = button;
        this.modifiers = modifiers;
        this.x = x;
        this.y = y;
    }

    public Type getType() {
        return type;
    }

    public Button getButton() {
        return button;
    }

    public EnumSet<Modifier> getModifiers() {
        return modifiers;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "MouseEvent[" +
                "type=" + type +
                ", button=" + button +
                ", modifiers=" + modifiers +
                ", x=" + x +
                ", y=" + y +
                ']';
    }
}
