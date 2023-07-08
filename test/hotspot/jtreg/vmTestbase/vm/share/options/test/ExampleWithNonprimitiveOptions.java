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

import java.util.Collection;
import vm.share.options.Option;
import vm.share.options.OptionSupport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This example uses @Option annotation with an a factory attribute,
 * to populate a field of a non-simple type.
 */
public class ExampleWithNonprimitiveOptions
{

    @Option(name = "iterations", default_value = "100", description = "Number of iterations")
    int iterations;

    @Option(description = "quiet or verbose")
    private String running_mode;

    @Option(description = "type of the list",
            factory=vm.share.options.test.BasicObjectFactoryUsageExample.class)
    private Collection list;

    public void run()
    {
    // ..do actual testing here..
         System.out.println("iterations = " + iterations);
         System.out.println("RM : " + running_mode);
         System.out.println("list is " + list.getClass());
    }

    public static void main(String[] args)
    {
        ExampleWithNonprimitiveOptions test = new ExampleWithNonprimitiveOptions();
        OptionSupport.setup(test, args); // instead of manually                       // parsing arguments
        // now test.iterations is set to 10 or 100.
        test.run();
    }
}
