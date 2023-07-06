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

import vm.share.options.Option;
import vm.share.options.OptionSupport;
import vm.share.options.Options;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is again a simple example which shows the power of @Options annotation.
 * @see vm.share.options.Options
 * @see vm.share.options.Option
 * @see vm.share.options.OptionSupport
 */

public class SimpleExampleWithOptionsAnnotation
{
    @Options
    StressOptions stressOptions = new StressOptions();
    @Option(name = "iterations", default_value = "100", description = "Number of iterations")
    int iterations;

    @Option(description = "quiet or verbose")
    String running_mode;

    public void run()
    {
    // ..do actual testing here..
         System.out.println("iterations = " + iterations);
         System.out.println("RM: " + running_mode);
         System.out.println("StressOptions " + stressOptions.getStressTime());
    }

    public static void main(String[] args)
    {
        SimpleExampleWithOptionsAnnotation test = new SimpleExampleWithOptionsAnnotation();
        OptionSupport.setup(test, args); // instead of manually                       // parsing arguments
        // now test.iterations is set to 10 or 100.
        test.run();
    }
}

class StressOptions
{
    @Option(default_value="30", description="Stress time")
    private long stressTime;

    public long getStressTime()
    {
        return stressTime;
    }

}
