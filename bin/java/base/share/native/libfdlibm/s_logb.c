/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double logb(double x)
#else
        double logb(x)
        double x;
#endif
{
        int lx,ix;
        ix = (__HI(x))&0x7fffffff;      /* high |x| */
        lx = __LO(x);                   /* low x */
        if((ix|lx)==0) return -1.0/fabs(x);
        if(ix>=0x7ff00000) return x*x;
        if((ix>>=20)==0)                        /* IEEE 754 logb */
                return -1022.0;
        else
                return (double) (ix-1023);
}
