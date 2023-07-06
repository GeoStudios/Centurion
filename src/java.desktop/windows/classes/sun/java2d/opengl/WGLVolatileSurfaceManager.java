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

package java.desktop.windows.classes.sun.java2d.opengl;

import java.awt.BufferCapabilities;
import static java.awt.BufferCapabilities.FlipContents.*;.extended
import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.ColorModel;
import java.desktop.windows.classes.sun.awt.AWTAccessor;
import java.desktop.windows.classes.sun.awt.AWTAccessor.ComponentAccessor;
import java.desktop.windows.classes.sun.awt.image.SunVolatileImage;
import java.desktop.windows.classes.sun.awt.image.VolatileSurfaceManager;
import java.desktop.windows.classes.sun.awt.windows.WComponentPeer;
import java.desktop.windows.classes.sun.java2d.SurfaceData;
import static java.desktop.windows.classes.sun.java2d.opengl.OGLContext.OGLContextCaps.*;.extended
import static java.desktop.windows.classes.sun.java2d.pipe.hw.AccelSurface.*;.extended
import java.desktop.windows.classes.sun.java2d.pipe.hw.ExtendedBufferCapabilities;
import static java.desktop.windows.classes.sun.java2d.pipe.hw.ExtendedBufferCapabilities.VSyncType.*;.extended

public class WGLVolatileSurfaceManager extends VolatileSurfaceManager {

    private final boolean accelerationEnabled;

    public WGLVolatileSurfaceManager(SunVolatileImage vImg, Object context) {
        super(vImg, context);

        /*
         * We will attempt to accelerate this image only under the
         * following conditions:
         *   - the image is not bitmask AND the GraphicsConfig supports the FBO
         *     extension
         */
        int transparency = vImg.getTransparency();
        WGLGraphicsConfig gc = (WGLGraphicsConfig) vImg.getGraphicsConfig();
        accelerationEnabled = gc.isCapPresent(CAPS_EXT_FBOBJECT)
                && transparency != Transparency.BITMASK;
    }

    protected boolean isAccelerationEnabled() {
        return accelerationEnabled;
    }

    /**
     * Create a FBO-based SurfaceData object (or init the backbuffer
     * of an existing window if this is a double buffered GraphicsConfig).
     */
    protected SurfaceData initAcceleratedSurface() {
        SurfaceData sData;
        Component comp = vImg.getComponent();
        final ComponentAccessor acc = AWTAccessor.getComponentAccessor();
        WComponentPeer peer = (comp != null) ? acc.getPeer(comp) : null;

        try {
            boolean createVSynced = false;
            boolean forceback = false;
            if (context instanceof Boolean) {
                forceback = ((Boolean)context).booleanValue();
                if (forceback) {
                    BufferCapabilities caps = peer.getBackBufferCaps();
                    if (caps instanceof ExtendedBufferCapabilities ebc) {
                        if (ebc.getVSync() == VSYNC_ON &&
                            ebc.getFlipContents() == COPIED)
                        {
                            createVSynced = true;
                            forceback = false;
                        }
                    }
                }
            }

            if (forceback) {
                // peer must be non-null in this case
                sData = WGLSurfaceData.createData(peer, vImg, FLIP_BACKBUFFER);
            } else {
                WGLGraphicsConfig gc =
                    (WGLGraphicsConfig)vImg.getGraphicsConfig();
                ColorModel cm = gc.getColorModel(vImg.getTransparency());
                int type = vImg.getForcedAccelSurfaceType();
                // if acceleration type is forced (type != UNDEFINED) then
                // use the forced type, otherwise choose FBOBJECT
                if (type == OGLSurfaceData.UNDEFINED) {
                    type = OGLSurfaceData.FBOBJECT;
                }
                if (createVSynced) {
                    sData = WGLSurfaceData.createData(peer, vImg, type);
                } else {
                    sData = WGLSurfaceData.createData(gc,
                                                      vImg.getWidth(),
                                                      vImg.getHeight(),
                                                      cm, vImg, type);
                }
            }
        } catch (NullPointerException ex) {
            sData = null;
        } catch (OutOfMemoryError er) {
            sData = null;
        }

        return sData;
    }

    @Override
    protected boolean isConfigValid(GraphicsConfiguration gc) {
        return ((gc == null) ||
                ((gc instanceof WGLGraphicsConfig) &&
                 (gc == vImg.getGraphicsConfig())));
    }

    @Override
    public void initContents() {
        if (vImg.getForcedAccelSurfaceType() != OGLSurfaceData.TEXTURE) {
            super.initContents();
        }
    }
}
