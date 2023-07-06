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

package gc.stress.gcbasher;

import java.io.java.io.java.io.java.io.IOException;

/*
 * @test TestGCBasherWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient
 * @summary Stress the Shenandoah GC by trying to make old objects more likely to be garbage than young objects.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=passive
 *      -XX:+ShenandoahVerify -XX:+ShenandoahDegeneratedGC
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=passive
 *      -XX:+ShenandoahVerify -XX:-ShenandoahDegeneratedGC
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient
 * @summary Stress the Shenandoah GC by trying to make old objects more likely to be garbage than young objects.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+ShenandoahOOMDuringEvacALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+ShenandoahAllocFailureALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient
 * @summary Stress the Shenandoah GC by trying to make old objects more likely to be garbage than young objects.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=adaptive
 *      -XX:+ShenandoahVerify
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=adaptive
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=compact
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient
 * @summary Stress the Shenandoah GC by trying to make old objects more likely to be garbage than young objects.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+ShenandoahOOMDuringEvacALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+ShenandoahAllocFailureALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient
 * @summary Stress the Shenandoah GC by trying to make old objects more likely to be garbage than young objects.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu
 *      -XX:+ShenandoahVerify
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=passive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahVerify -XX:+ShenandoahDegeneratedGC
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=passive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahVerify -XX:-ShenandoahDegeneratedGC
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahOOMDuringEvacALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahAllocFailureALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=adaptive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahVerify
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=adaptive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCHeuristics=compact
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahOOMDuringEvacALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahAllocFailureALot
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu -XX:ShenandoahGCHeuristics=aggressive
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

/*
 * @test TestGCBasherDeoptWithShenandoah
 * @key stress
 * @library /
 * @requires vm.gc.Shenandoah
 * @requires vm.flavor == "server" & !vm.emulatedClient & vm.opt.ClassUnloading != false
 * @summary Stress Shenandoah GC with nmethod barrier forced deoptimization enabled.
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      -XX:+ShenandoahVerify
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 *
 * @run main/othervm/timeout=200 -Xlog:gc*=info,nmethod+barrier=trace -Xmx1g -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions
 *      -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu
 *      -XX:+DeoptimizeNMethodBarriersALot -XX:-Inline
 *      gc.stress.gcbasher.TestGCBasherWithShenandoah 120000
 */

public class TestGCBasherWithShenandoah {
    public static void main(String[] args) throws IOException {
        TestGCBasher.main(args);
    }
}