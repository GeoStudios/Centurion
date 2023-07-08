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

package java.desktop.windows.classes.sun.java2d;


import java.awt.GraphicsConfiguration;
import java.desktop.windows.classes.sun.awt.image.BufImgVolatileSurfaceManager;
import java.desktop.windows.classes.sun.awt.image.SunVolatileImage;
import java.desktop.windows.classes.sun.awt.image.VolatileSurfaceManager;
import java.desktop.windows.classes.sun.java2d.d3d.D3DGraphicsConfig;
import java.desktop.windows.classes.sun.java2d.d3d.D3DVolatileSurfaceManager;
import java.desktop.windows.classes.sun.java2d.opengl.WGLGraphicsConfig;
import java.desktop.windows.classes.sun.java2d.opengl.WGLVolatileSurfaceManager;















/**
 * The SurfaceManagerFactory that creates VolatileSurfaceManager
 * implementations for the Windows volatile images.
 */
public class WindowsSurfaceManagerFactory extends SurfaceManagerFactory {

    /**
     * Creates a new instance of a VolatileSurfaceManager given any
     * arbitrary SunVolatileImage.  An optional context Object can be supplied
     * as a way for the caller to pass pipeline-specific context data to
     * the VolatileSurfaceManager (such as a backbuffer handle, for example).
     *
     * For Windows platforms, this method returns a Windows-specific
     * VolatileSurfaceManager.
     */
    public VolatileSurfaceManager createVolatileManager(SunVolatileImage vImg,
                                                        Object context)
    {
        GraphicsConfiguration gc = vImg.getGraphicsConfig();
        if (gc instanceof D3DGraphicsConfig) {
            return new D3DVolatileSurfaceManager(vImg, context);
        } else if (gc instanceof WGLGraphicsConfig) {
            return new WGLVolatileSurfaceManager(vImg, context);
        } else {
            return new BufImgVolatileSurfaceManager(vImg, context);
        }
    }

}
