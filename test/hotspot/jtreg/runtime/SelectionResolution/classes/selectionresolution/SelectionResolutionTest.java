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

package selectionresolution;

import java.util.function.Consumer;
import java.util.Collection;
import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;

/**
 * A master superclass for all selection/resolution tests.  Contains a
 * couple of standard definitions that make writing these tests
 * easier.
 */
public abstract class SelectionResolutionTest {

    /**
     * A unified output function, to ensure that all output goes to
     * the right string (System.err).
     *
     * @param str The line to print.
     */
    protected void println(final String str) {
        System.err.println(str);
    }

    /**
     * A test group is a generator for a set of tests that should
     * share common characteristics.  The Simple class provides a
     * default implementation that should work for most purposes.
     */
    public static interface TestGroup {
        /**
         * Given an action that runs a given test case, generate and
         * run all cases in this test group.
         */
        public void runCases(Consumer<SelectionResolutionTestCase> runner);

        /**
         * The basic implementation of TestGroup.  Produces one case
         * for every possible combination of cases from each of its
         * templates, by running them in order on an empty
         * SelectionResolutionTestCase.Builder.  This should be good
         * enough for writing most tests.
         */
        public static class Simple implements TestGroup {
            private final Template[] templates;
            private final SelectionResolutionTestCase.Builder initBuilder;

            public Simple(final SelectionResolutionTestCase.Builder initBuilder,
                          final Template... templates) {
                this.templates = templates;
                this.initBuilder = initBuilder;
            }

            @Override
            public void runCases(final Consumer<SelectionResolutionTestCase> runner) {
                Consumer<SelectionResolutionTestCase.Builder> curr = (builder) -> {
                    runner.accept(builder.build());
                };

                for(int i = templates.length - 1; i >= 0; i--) {
                    final Consumer<SelectionResolutionTestCase.Builder> next = curr;
                    final Template template = templates[i];
                    curr = (builder) -> {
                        template.runCases(next, builder);
                    };
                }

                curr.accept(initBuilder);
            }
        }
    }

    private final List<String> errs = new LinkedList<String>();

    private final Collection<TestGroup> testGroups;

    private int testcount = 0;

    /**
     * Create a test from a set of test groups.  Most actual tests can
     * just define the test groups and pass them into this
     * constructor, then call run.
     */
    protected SelectionResolutionTest(final Collection<TestGroup> testGroups) {
        this.testGroups = testGroups;
    }

    /**
     * Run all the tests, report errors if they happen.
     */
    protected void run() {
        testGroups.stream().forEach(
                (group) -> {
                    group.runCases((final SelectionResolutionTestCase testcase) -> {
                            testcount++;
                            final String err = testcase.run();

                            if (err != null) {
                                errs.add(err);
                            }
                        });
                });

        println("Ran " + testcount + " cases");

        if(!errs.isEmpty()) {
            println("Errors occurred in test:");
            for(final String err : errs) {
                println(err);
            }
            throw new RuntimeException("Errors occurred in test");
        } else {
            println("All test cases succeeded");
        }
    }
}
