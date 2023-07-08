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

package nsk.jvmti.scenarios.hotswap.HS302.hs302t001;


import nsk.share.jvmti.RedefineAgent;














/*
 * @test
 *
 * @summary converted from VM Testbase nsk/jvmti/scenarios/hotswap/HS302/hs302t001.
 * VM Testbase keywords: [quick, jpda, jvmti, noras, redefine, feature_hotswap]
 *
 * @library /vmTestbase
 *          /test/lib
 * @build nsk.jvmti.scenarios.hotswap.HS302.hs302t001.hs302t001
 *
 * @comment compile newclassXX to bin/newclassXX
 * @run driver nsk.share.ExtraClassesBuilder
 *      newclass00
 *
 * @run main/othervm/native
 *      -agentlib:hs302t001=pathToNewByteCode=./bin,-waittime=5,package=nsk,samples=100,mode=compiled
 *      nsk.jvmti.scenarios.hotswap.HS302.hs302t001.hs302t001
 */


/**
 * Here in this case the redefine should fail to redefine.
 *
 */

public class hs302t001 extends RedefineAgent {

    public hs302t001(String[] arg) {
        super(arg);
    }


    public static void main(String[] arg) {
        arg = nsk.share.jvmti.JVMTITest.commonInit(arg);

        hs302t001 hsCase = new hs302t001(arg);
        System.exit(hsCase.runAgent());
    }

    public boolean agentMethod() {
        boolean pass=false;
        MyClass cls = new MyClass();
        cls.setName("SOME NAME");
        log.display(" cls.toString() "+cls.toString());
        // Failed to redefine and
        if (cls.toString().equals("Default") &&
                ( redefineAttempted() && !isRedefined()) ) {
            pass = true;
            log.display(" Passed ..");
        } else {
            log.display(" Failed ..");
        }

        log.display(" isRedefined() == "+ isRedefined()+
                " and Return From Display "+cls.toString());
        return pass;
    }

}
