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
static const double
#else
static double
#endif
two54 =  1.80143985094819840000e+16; /* 0x43500000, 0x00000000 */

#ifdef __STDC__
        double frexp(double x, int *eptr)
#else
        double frexp(x, eptr)
        double x; int *eptr;
#endif
{
        int  hx, ix, lx;
        hx = __HI(x);
        ix = 0x7fffffff&hx;
        lx = __LO(x);
        *eptr = 0;
        if(ix>=0x7ff00000||((ix|lx)==0)) return x;      /* 0,inf,nan */
        if (ix<0x00100000) {            /* subnormal */
            x *= two54;
            hx = __HI(x);
            ix = hx&0x7fffffff;
            *eptr = -54;
        }
        *eptr += (ix>>20)-1022;
        hx = (hx&0x800fffff)|0x3fe00000;
        __HI(x) = hx;
        return x;
}
