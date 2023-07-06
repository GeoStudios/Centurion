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

package vm.share.options;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a factory interface used to setup non-simple type options,
 * implemented by the user, there is a shortcut, see {@link BasicObjectFactory}.
 */
public interface OptionObjectFactory<T>
{
    /**
     * Returns a string that can be used in <..> section of help message.
     * @return placeholder text
     */
    public String getPlaceholder();

    /**
     * Enumerates all possible key values for this factory.
     * @return an array of keys
     */
    public String[] getPossibleValues();

    /**
     * Returns default description for options which use this factory
     * @return the description string.
     */
    public String getDescription();

    /**
     * For a given parameter value gives its description.
     * @param key to instantiate parameter
     * @return description string for the parameter given.
     */
    public String getParameterDescription(String key);

    /**
     * Returns default value for the parameter, which is used if
     * no default value attribute is defined at the @Option annotation level.
     * @return default value for the parameter, null if mandatory
     */
    public String getDefaultValue();

    /**
     * Constructs an object given a object type key.
     * @param key  name indicating the type of the object to create.
     * @return the instance of the requested type
     */
    public T getObject(String key);
}
