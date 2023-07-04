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

/**
 * This class actually provides the OptionFramework API
 * via two static {@link OptionSupport#setup} methods.
 * See also "General description" section of the package level documentation.
 */
public class OptionSupport
{
    /**
     * This method parses the commandline arguments, setups the instance fields (annotated by @Option) accordingly
     * and calls method run().
     * @param test  the instance to setup fields for
     * @param args  command line arguments array
      */
    public static void setupAndRun(Runnable test, String[] args) {
        setupAndRun(test, args, null);
    }

    /**
     * This method parses the commandline arguments and setups the instance fields (annotated by @Option) accordingly
     * @param test  the instance to setup fields for
     * @param args  command line arguments array
     */
    public static void setup(Object test, String[] args)
    {
        setup(test, args, null);
    }

    /**
     * This method parses the commandline arguments, setups the instance fields (annotated by @Option) accordingly
     * and calls method run().
     * @param test  the instance to setup fields for
     * @param args  command line arguments array
     * @param unknownOptionHandler an option handler for unknown options
      */
    public static void setupAndRun(Runnable test, String[] args, OptionHandler unknownOptionHandler) {
        setup(test, args, unknownOptionHandler);
        test.run();
    }


    /**
     *  This is an extension API which allows Test author to create and
     *  process some specific options.
     * @param test  the instance to setup fields for
     * @param args  command line arguments array
     * @param unknownOptionHandler an option handler for unknown options
     *
     */
    public static void setup(Object test, String[] args, OptionHandler unknownOptionHandler) {
                new OptionsSetup(test, args, unknownOptionHandler).run();
    }
}
