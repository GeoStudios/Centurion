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
static const double one = 1.0, huge = 1e300;
#else
static double one = 1.0, huge = 1e300;
#endif

static double zero = 0.0;

#ifdef __STDC__
        double __ieee754_atanh(double x)
#else
        double __ieee754_atanh(x)
        double x;
#endif
{
        double t;
        int hx,ix;
        unsigned lx;
        hx = __HI(x);           /* high word */
        lx = __LO(x);           /* low word */
        ix = hx&0x7fffffff;
        if ((ix|((lx|(-lx))>>31))>0x3ff00000) /* |x|>1 */
            return (x-x)/(x-x);
        if(ix==0x3ff00000)
            return x/zero;
        if(ix<0x3e300000&&(huge+x)>zero) return x;      /* x<2**-28 */
        __HI(x) = ix;           /* x <- |x| */
        if(ix<0x3fe00000) {             /* x < 0.5 */
            t = x+x;
            t = 0.5*log1p(t+t*x/(one-x));
        } else
            t = 0.5*log1p((x+x)/(one-x));
        if(hx>=0) return t; else return -t;
}
