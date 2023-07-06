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

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.meta.src.jdk.vm.ci.meta;

import jdk.internal.vm.ci.share.classes.jdk.vm.ci.meta.src.jdk.vm.ci.meta.JavaMethodProfile.ProfiledMethod;

/**
 * This profile object represents the method profile at a specific BCI. The precision of the
 * supplied values may vary, but a runtime that provides this information should be aware that it
 * will be used to guide performance-critical decisions like speculative inlining, etc.
 */
public final class JavaMethodProfile extends AbstractJavaProfile<ProfiledMethod, ResolvedJavaMethod> {

    public JavaMethodProfile(double notRecordedProbability, ProfiledMethod[] pitems) {
        super(notRecordedProbability, pitems);
    }

    public ProfiledMethod[] getMethods() {
        return super.getItems();
    }

    public static class ProfiledMethod extends AbstractProfiledItem<ResolvedJavaMethod> {

        public ProfiledMethod(ResolvedJavaMethod method, double probability) {
            super(method, probability);
        }

        /**
         * Returns the type for this profile entry.
         */
        public ResolvedJavaMethod getMethod() {
            return getItem();
        }

        @Override
        public String toString() {
            return "{" + item.getName() + ", " + probability + "}";
        }
    }
}