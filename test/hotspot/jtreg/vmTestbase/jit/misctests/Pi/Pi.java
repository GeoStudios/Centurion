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

package jit.misctests.Pi;

import java.util.Random;
import nsk.share.TestFailure;
import jdk.test.lib.Utils;

/*
 * @test
 * @key randomness
 *
 * @summary converted from VM Testbase jit/misctests/Pi.
 * VM Testbase keywords: [jit, quick]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm jit.misctests.Pi.Pi
 */

public class Pi{
    static double pi;
    static int imKreis=0, imQuadrat=0, i=0;

    public static void main(String args[]) {
        for(int i=0;i<=100;i++) {
            wurf(10000);
            pi=4.0*imKreis/imQuadrat;
            ausgabe();
        }
        System.out.print("\n");
    }

    static void wurf(int wieOft) {
        double x,y;
        Random zufall;
        zufall = Utils.getRandomInstance();
        for(int i=0;i<=wieOft;i++) {
            x=zufall.nextDouble();
            y=zufall.nextDouble();
            imQuadrat++;
            if(x*x+y*y<1)imKreis++;
        }
    }

    static void ausgabe() {
        System.out.print(pi);
        if ((++i % 4) == 0){
            System.out.print("\n");
        } else {
            System.out.print(" ");
        }
    }
}