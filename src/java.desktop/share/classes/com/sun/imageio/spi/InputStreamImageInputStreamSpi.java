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

package java.desktop.share.classes.com.sun.imageio.spi;


import java.io.File;
import java.io.InputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Locale;
import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.FileCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;















public class InputStreamImageInputStreamSpi extends ImageInputStreamSpi {

    private static final String vendorName = "Oracle Corporation";

    private static final String version = "1.0";

    private static final Class<?> inputClass = InputStream.class;

    public InputStreamImageInputStreamSpi() {
        super(vendorName, version, inputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates a FileCacheImageInputStream or MemoryCacheImageInputStream from an InputStream";
    }

    public boolean canUseCacheFile() {
        return true;
    }

    public boolean needsCacheFile() {
        return false;
    }

    public ImageInputStream createInputStreamInstance(Object input,
                                                      boolean useCache,
                                                      File cacheDir)
        throws IOException {
        if (input instanceof InputStream is) {

            if (useCache) {
                return new FileCacheImageInputStream(is, cacheDir);
            } else {
                return new MemoryCacheImageInputStream(is);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
