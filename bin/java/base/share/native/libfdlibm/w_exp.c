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

/*
 * wrapper exp(x)
 */

#include "fdlibm.h"

#ifdef __STDC__
static const double
#else
static double
#endif
o_threshold=  7.09782712893383973096e+02,  /* 0x40862E42, 0xFEFA39EF */
u_threshold= -7.45133219101941108420e+02;  /* 0xc0874910, 0xD52D3051 */

#ifdef __STDC__
        double exp(double x)            /* wrapper exp */
#else
        double exp(x)                   /* wrapper exp */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_exp(x);
#else
        double z;
        z = __ieee754_exp(x);
        if(_LIB_VERSION == _IEEE_) return z;
        if(finite(x)) {
            if(x>o_threshold)
                return __kernel_standard(x,x,6); /* exp overflow */
            else if(x<u_threshold)
                return __kernel_standard(x,x,7); /* exp underflow */
        }
        return z;
#endif
}
