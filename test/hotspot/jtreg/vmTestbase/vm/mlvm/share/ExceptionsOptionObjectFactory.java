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

package vm.mlvm.share;

import java.util.List;
import java.util.ArrayList;
import vm.share.options.OptionObjectFactory;
import java.util.regex.Pattern;

/**
 * Implementation of vm.share.options.OptionObjectFactory interface.
 * Parses the comma-separated list of exception class names.
 *
 * @see vm.mlvm.share.MlvmTest
 * @see vm.mlvm.share.MlvmTestExecutor#launch(Class<?> testClass, Object[] constructorArgs)
 *
 */

public class ExceptionsOptionObjectFactory implements OptionObjectFactory<List<Class<? extends Throwable>>> {

    private static final String DESCRIPTION = "list of exception class names separated by comma";

    @Override
    public String getPlaceholder() {
        return DESCRIPTION;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getParameterDescription(String param) {
        return "exception of type " + param;
    }

    @Override
    public String[] getPossibleValues() {
        return new String[] { Throwable.class.getName() };
    }

    @Override
    public String getDefaultValue() {
        return "";
    }

    @Override
    public List<Class<? extends Throwable>> getObject(String classNameList) {
        List<Class<? extends Throwable>> result = new ArrayList<>();
        classNameList = classNameList.trim();

        if (!classNameList.isEmpty()) {
            for (String className : classNameList.split(",")) {
                result.add(getClassFor(className.trim()));
            }
        }

        return result;
    }

    private static Class<? extends Throwable> getClassFor(String className) {
        try {
            return Class.forName(className).asSubclass(Throwable.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find class '" + className + "'", e);
        } catch (ClassCastException e) {
            throw new RuntimeException("Subclass of " + Throwable.class.getName() + " should be specified. Cannot cast '" + className + "' to the Throwable", e);
        }
    }
}
