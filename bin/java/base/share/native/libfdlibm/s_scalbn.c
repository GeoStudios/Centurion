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
two54   =  1.80143985094819840000e+16, /* 0x43500000, 0x00000000 */
twom54  =  5.55111512312578270212e-17, /* 0x3C900000, 0x00000000 */
huge   = 1.0e+300,
tiny   = 1.0e-300;

#ifdef __STDC__
        double scalbn (double x, int n)
#else
        double scalbn (x,n)
        double x; int n;
#endif
{
        int  k,hx,lx;
        hx = __HI(x);
        lx = __LO(x);
        k = (hx&0x7ff00000)>>20;                /* extract exponent */
        if (k==0) {                             /* 0 or subnormal x */
            if ((lx|(hx&0x7fffffff))==0) return x; /* +-0 */
            x *= two54;
            hx = __HI(x);
            k = ((hx&0x7ff00000)>>20) - 54;
            if (n< -50000) return tiny*x;       /*underflow*/
            }
        if (k==0x7ff) return x+x;               /* NaN or Inf */
        k = k+n;
        if (k >  0x7fe) return huge*copysign(huge,x); /* overflow  */
        if (k > 0)                              /* normal result */
            {__HI(x) = (hx&0x800fffff)|(k<<20); return x;}
        if (k <= -54) {
            if (n > 50000)      /* in case integer overflow in n+k */
                return huge*copysign(huge,x);   /*overflow*/
            else return tiny*copysign(tiny,x);  /*underflow*/
        }
        k += 54;                                /* subnormal result */
        __HI(x) = (hx&0x800fffff)|(k<<20);
        return x*twom54;
}
