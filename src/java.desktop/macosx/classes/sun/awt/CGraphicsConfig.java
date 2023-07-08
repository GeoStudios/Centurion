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

package java.desktop.macosx.classes.sun.awt;

import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.desktop.macosx.classes.sun.java2d.SurfaceData;
import java.desktop.macosx.classes.sun.lwawt.LWGraphicsConfig;
import java.desktop.macosx.classes.sun.lwawt.macosx.CFRetainedResource;

public abstract class CGraphicsConfig extends GraphicsConfiguration
        implements LWGraphicsConfig {

    private final CGraphicsDevice device;
    private ColorModel colorModel;

    protected CGraphicsConfig(CGraphicsDevice device) {
        this.device = device;
    }

    @Override
    public final Rectangle getBounds() {
        return device.getBounds();
    }

    @Override
    public ColorModel getColorModel() {
        if (colorModel == null) {
            colorModel = getColorModel(Transparency.OPAQUE);
        }
        return colorModel;
    }

    @Override
    public AffineTransform getDefaultTransform() {
        double scaleFactor = device.getScaleFactor();
        return AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
    }

    @Override
    public CGraphicsDevice getDevice() {
        return device;
    }

    @Override
    public AffineTransform getNormalizingTransform() {
        double xscale = device.getXResolution() / 72.0;
        double yscale = device.getYResolution() / 72.0;
        return new AffineTransform(xscale, 0.0, 0.0, yscale, 0.0, 0.0);
    }

    /**
     * Creates a new SurfaceData that will be associated with the given
     * layer (CGLLayer/MTLLayer).
     */
    public abstract SurfaceData createSurfaceData(CFRetainedResource layer);

    @Override
    public final boolean isTranslucencyCapable() {
        //we know for sure we have capable config :)
        return true;
    }
}
