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

package java.desktop.share.classes.sun.java2d.cmm;

/**
 * A class to pass information about a profile to be loaded from a file to the
 * static getInstance(int cspace) method of ICC_Profile. Loading of the profile
 * data and initialization of the CMM is to be deferred as long as possible.
 */
public final class ProfileDeferralInfo {

    /**
     * Need to have this info for ICC_ColorSpace without causing a deferred
     * profile to be loaded.
     */
    public final int colorSpaceType, numComponents, profileClass;

    /**
     * The profile file name, such as "CIEXYZ.pf", "sRGB.pf", etc.
     */
    public final String filename;

    public ProfileDeferralInfo(String fn, int type, int ncomp, int pclass) {
        filename = fn;
        colorSpaceType = type;
        numComponents = ncomp;
        profileClass = pclass;
    }
}
