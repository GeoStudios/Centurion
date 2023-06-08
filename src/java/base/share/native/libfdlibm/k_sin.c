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
half =  5.00000000000000000000e-01, /* 0x3FE00000, 0x00000000 */
S1  = -1.66666666666666324348e-01, /* 0xBFC55555, 0x55555549 */
S2  =  8.33333333332248946124e-03, /* 0x3F811111, 0x1110F8A6 */
S3  = -1.98412698298579493134e-04, /* 0xBF2A01A0, 0x19C161D5 */
S4  =  2.75573137070700676789e-06, /* 0x3EC71DE3, 0x57B1FE7D */
S5  = -2.50507602534068634195e-08, /* 0xBE5AE5E6, 0x8A2B9CEB */
S6  =  1.58969099521155010221e-10; /* 0x3DE5D93A, 0x5ACFD57C */

#ifdef __STDC__
        double __kernel_sin(double x, double y, int iy)
#else
        double __kernel_sin(x, y, iy)
        double x,y; int iy;             /* iy=0 if y is zero */
#endif
{
        double z,r,v;
        int ix;
        ix = __HI(x)&0x7fffffff;        /* high word of x */
        if(ix<0x3e400000)                       /* |x| < 2**-27 */
           {if((int)x==0) return x;}            /* generate inexact */
        z       =  x*x;
        v       =  z*x;
        r       =  S2+z*(S3+z*(S4+z*(S5+z*S6)));
        if(iy==0) return x+v*(S1+z*r);
        else      return x-((z*(half*y-v*r)-y)-v*S1);
}
