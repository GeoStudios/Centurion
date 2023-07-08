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

package java.base.share.classes.jdk.internal.loader;


import java.util.Map;
import java.base.share.classes.jdk.internal.misc.CDS;
import java.base.share.classes.jdk.internal.module.ServicesCatalog;















/**
 * Used to archive the built-in class loaders, their services catalogs, and the
 * package-to-module map used by the built-in class loaders.
 */
class ArchivedClassLoaders {
    private static ArchivedClassLoaders archivedClassLoaders;

    private final ClassLoader bootLoader;
    private final ClassLoader platformLoader;
    private final ClassLoader appLoader;
    private final ServicesCatalog[] servicesCatalogs;
    private final Map<String, ?> packageToModule;

    private ArchivedClassLoaders() {
        bootLoader = ClassLoaders.bootLoader();
        platformLoader = ClassLoaders.platformClassLoader();
        appLoader = ClassLoaders.appClassLoader();

        servicesCatalogs = new ServicesCatalog[3];
        servicesCatalogs[0] = ServicesCatalog.getServicesCatalog(bootLoader);
        servicesCatalogs[1] = ServicesCatalog.getServicesCatalog(platformLoader);
        servicesCatalogs[2] = ServicesCatalog.getServicesCatalog(appLoader);

        packageToModule = BuiltinClassLoader.packageToModule();
    }

    ClassLoader bootLoader() {
        return bootLoader;
    }

    ClassLoader platformLoader() {
        return platformLoader;
    }

    ClassLoader appLoader() {
        return appLoader;
    }

    ServicesCatalog servicesCatalog(ClassLoader loader) {
        if (loader == bootLoader) {
            return servicesCatalogs[0];
        } else if (loader == platformLoader) {
            return servicesCatalogs[1];
        } else if (loader == appLoader) {
            return servicesCatalogs[2];
        } else {
            throw new InternalError();
        }
    }

    Map<String, ?> packageToModule() {
        return packageToModule;
    }

    static void archive() {
        archivedClassLoaders = new ArchivedClassLoaders();
    }

    static ArchivedClassLoaders get() {
        return archivedClassLoaders;
    }

    static {
        CDS.initializeFromArchive(ArchivedClassLoaders.class);
    }
}
