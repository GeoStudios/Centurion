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
        double tan(double x)
#else
        double tan(x)
        double x;
#endif
{
        double y[2],z=0.0;
        int n, ix;

    /* High word of x. */
        ix = __HI(x);

    /* |x| ~< pi/4 */
        ix &= 0x7fffffff;
        if(ix <= 0x3fe921fb) return __kernel_tan(x,z,1);

    /* tan(Inf or NaN) is NaN */
        else if (ix>=0x7ff00000) return x-x;            /* NaN */

    /* argument reduction needed */
        else {
            n = __ieee754_rem_pio2(x,y);
            return __kernel_tan(y[0],y[1],1-((n&1)<<1)); /*   1 -- n even
                                                        -1 -- n odd */
        }
}
