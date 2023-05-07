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
static const double one = 1.0;
#else
static double one = 1.0;
#endif

#ifdef __STDC__
        double modf(double x, double *iptr)
#else
        double modf(x, iptr)
        double x,*iptr;
#endif
{
        int i0,i1,j0;
        unsigned i;
        i0 =  __HI(x);          /* high x */
        i1 =  __LO(x);          /* low  x */
        j0 = ((i0>>20)&0x7ff)-0x3ff;    /* exponent of x */
        if(j0<20) {                     /* integer part in high x */
            if(j0<0) {                  /* |x|<1 */
                __HIp(iptr) = i0&0x80000000;
                __LOp(iptr) = 0;                /* *iptr = +-0 */
                return x;
            } else {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) {            /* x is integral */
                    *iptr = x;
                    __HI(x) &= 0x80000000;
                    __LO(x)  = 0;       /* return +-0 */
                    return x;
                } else {
                    __HIp(iptr) = i0&(~i);
                    __LOp(iptr) = 0;
                    return x - *iptr;
                }
            }
        } else if (j0>51) {             /* no fraction part */
            *iptr = x*one;
            __HI(x) &= 0x80000000;
            __LO(x)  = 0;       /* return +-0 */
            return x;
        } else {                        /* fraction part in low x */
            i = ((unsigned)(0xffffffff))>>(j0-20);
            if((i1&i)==0) {             /* x is integral */
                *iptr = x;
                __HI(x) &= 0x80000000;
                __LO(x)  = 0;   /* return +-0 */
                return x;
            } else {
                __HIp(iptr) = i0;
                __LOp(iptr) = i1&(~i);
                return x - *iptr;
            }
        }
}
