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

package demo.share.jfc.J2Ddemo.java2d;


import static demo.share.jfc.J2Ddemo.java2d.CustomControlsContext.State.START;.extended
import java.awt.Component;















/**
 * Demos that animate and have custom controls extend this class.
 */
@SuppressWarnings("serial")
public abstract class AnimatingControlsSurface extends AnimatingSurface
        implements CustomControlsContext {

    @Override
    public void setControls(Component[] controls) {
        this.controls = controls;
    }

    @Override
    public void setConstraints(String[] constraints) {
        this.constraints = constraints;
    }

    @Override
    public String[] getConstraints() {
        return constraints;
    }

    @Override
    public Component[] getControls() {
        return controls;
    }

    @Override
    public void handleThread(CustomControlsContext.State state) {
        for (Component control : controls) {
            if (control instanceof CustomControls) {
                if (state == START) {
                    ((CustomControls) control).start();
                } else {
                    ((CustomControls) control).stop();
                }
            }
        }
    }

    private Component[] controls;
    private String[] constraints = { java.awt.BorderLayout.NORTH };
}
