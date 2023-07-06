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

package java.desktop.share.classes.sun.awt.www.content;

import java.desktop.share.classes.sun.awt.www.content.audio.wav;
import java.desktop.share.classes.sun.awt.www.content.audio.x_aiff;
import java.desktop.share.classes.sun.awt.www.content.image.gif;
import java.desktop.share.classes.sun.awt.www.content.audio.aiff;
import java.desktop.share.classes.sun.awt.www.content.audio.basic;
import java.desktop.share.classes.sun.awt.www.content.audio.x_wav;
import java.desktop.share.classes.sun.awt.www.content.image.jpeg;
import java.desktop.share.classes.sun.awt.www.content.image.png;
import java.desktop.share.classes.sun.awt.www.content.image.x_xbitmap;
import java.desktop.share.classes.sun.awt.www.content.image.x_xpixmap;
import java.net.ContentHandler;
import java.net.ContentHandlerFactory;

public final class MultimediaContentHandlers implements ContentHandlerFactory {

    @Override
    public ContentHandler createContentHandler(String mimetype) {
        switch (mimetype) {
            case "audio/aiff":      return new aiff();
            case "audio/basic":     return new basic();
            case "audio/wav":       return new wav();
            case "audio/x-aiff":    return new x_aiff();
            case "audio/x-wav":     return new x_wav();
            case "image/gif":       return new gif();
            case "image/jpeg":      return new jpeg();
            case "image/png":       return new png();
            case "image/x-xbitmap": return new x_xbitmap();
            case "image/x-xpixmap": return new x_xpixmap();
            default:                return null;
        }
    }
}