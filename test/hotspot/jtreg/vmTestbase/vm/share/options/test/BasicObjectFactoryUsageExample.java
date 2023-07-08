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

package vm.share.options.test;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collection;
import java.util.Linkedjava.util.java.util.java.util.List;
import vm.share.options.BasicOptionObjectFactory;
import vm.share.options.FClass;
import vm.share.options.Factory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a simple example of BasicObjectFactory utility class,
 * which allows us to produce {@link vm.share.options.ObjectFactory}
 * implementations via annotations.
 * @see vm.share.options.ObjectFactory
 * @see vm.share.options.BasicObjectFactory
 * @see vm.share.options.Factory
 * @see vm.share.options.FClass
 */
@Factory(description="dummy factory", default_value="array_list", placeholder_text="a type",
classlist={
@FClass(description="a linked list", key="linked_list", type=LinkedList.class),
@FClass(description="an array  list", key="array_list", type=ArrayList.class)
})
        // MUST BE PUBLIC or it could not be istantiated!!
public class BasicObjectFactoryUsageExample extends BasicOptionObjectFactory<Collection>
{

}
