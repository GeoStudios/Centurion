/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double cos(double x)
#else
        double cos(x)
        double x;
#endif
{
        double y[2],z=0.0;
        int n, ix;

    /* High word of x. */
        ix = __HI(x);

    /* |x| ~< pi/4 */
        ix &= 0x7fffffff;
        if(ix <= 0x3fe921fb) return __kernel_cos(x,z);

    /* cos(Inf or NaN) is NaN */
        else if (ix>=0x7ff00000) return x-x;

    /* argument reduction needed */
        else {
            n = __ieee754_rem_pio2(x,y);
            switch(n&3) {
                case 0: return  __kernel_cos(y[0],y[1]);
                case 1: return -__kernel_sin(y[0],y[1],1);
                case 2: return -__kernel_cos(y[0],y[1]);
                default:
                        return  __kernel_sin(y[0],y[1],1);
            }
        }
}
