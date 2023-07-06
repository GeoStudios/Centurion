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

package jdk.test.failurehandler;


import jdk.test.failurehandler.action.ActionSet;
import jdk.test.failurehandler.action.ActionHelper;
import java.io.PrintWriter;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.Queue;
import java.util.Deque;














public class ToolKit implements EnvironmentInfoGatherer, ProcessInfoGatherer {
    private final List<ActionSet> actions = new ArrayList<>();
    private final ActionHelper helper;

    public ToolKit(ActionHelper helper, PrintWriter log, String... names) {
        this.helper = helper;
        for (String name : names) {
            actions.add(new ActionSet(helper, log, name));
        }
    }

    @Override
    public void gatherEnvironmentInfo(HtmlSection section) {
        for (ActionSet set : actions) {
            set.gatherEnvironmentInfo(section);
        }
    }

    @Override
    public void gatherProcessInfo(HtmlSection section, long pid) {
        // as some of actions can kill a process, we need to get children of all
        // test process first, and run the actions starting from the leaves
        // and going up by the process tree
        Deque<Long> orderedPids = new LinkedList<>();
        Queue<Long> testPids = new LinkedList<>();
        testPids.add(pid);
        HtmlSection ptreeSection = section.createChildren("test_processes");
        for (Long p = testPids.poll(); p != null; p = testPids.poll()) {
            orderedPids.addFirst(p);
            List<Long> children = helper.getChildren(ptreeSection, p);
            if (!children.isEmpty()) {
                HtmlSection s = ptreeSection.createChildren("" + p);
                for (Long c : children) {
                    s.link(section, c.toString(), c.toString());
                }
                testPids.addAll(children);
            }
        }
        for (Long p : orderedPids) {
            for (ActionSet set : actions) {
                set.gatherProcessInfo(section, p);
            }
        }
    }
}
