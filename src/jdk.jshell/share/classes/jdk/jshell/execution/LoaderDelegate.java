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

package jdk.jshell.share.classes.jdk.jshell.execution;

import jdk.jshell.share.classes.jdk.jshell.spi.ExecutionControl.ClassBytecodes;
import jdk.jshell.share.classes.jdk.jshell.spi.ExecutionControl.ClassInstallException;
import jdk.jshell.share.classes.jdk.jshell.spi.ExecutionControl.EngineTerminationException;
import jdk.jshell.share.classes.jdk.jshell.spi.ExecutionControl.InternalException;
import jdk.jshell.share.classes.jdk.jshell.spi.ExecutionControl.NotImplementedException;

/**
 * This interface specifies the loading specific subset of
 * {@link jdk.jshell.spi.ExecutionControl}.  For use in encapsulating the
 * {@link java.lang.ClassLoader} implementation.
 *
 */
public interface LoaderDelegate {

    /**
     * Attempts to load new classes.
     *
     * @param cbcs the class name and bytecodes to load
     * @throws ClassInstallException exception occurred loading the classes,
     * some or all were not loaded
     * @throws NotImplementedException if not implemented
     * @throws EngineTerminationException the execution engine has terminated
     */
    void load(ClassBytecodes[] cbcs)
            throws ClassInstallException, NotImplementedException, EngineTerminationException;

    /**
     * Notify that classes have been redefined.
     *
     * @param cbcs the class names and bytecodes that have been redefined
     */
    void classesRedefined(ClassBytecodes[] cbcs);

    /**
     * Adds the path to the execution class path.
     *
     * @param path the path to add
     * @throws EngineTerminationException the execution engine has terminated
     * @throws InternalException an internal problem occurred
     */
    void addToClasspath(String path)
            throws EngineTerminationException, InternalException;

    /**
     * Finds the class with the specified binary name.
     *
     * @param name the binary name of the class
     * @return the Class Object
     * @throws ClassNotFoundException if the class could not be found
     */
    Class<?> findClass(String name) throws ClassNotFoundException;
}