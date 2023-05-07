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
 * wrapper sqrt(x)
 */

#include "fdlibm.h"

#ifdef __STDC__
        double sqrt(double x)           /* wrapper sqrt */
#else
        double sqrt(x)                  /* wrapper sqrt */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_sqrt(x);
#else
        double z;
        z = __ieee754_sqrt(x);
        if(_LIB_VERSION == _IEEE_ || isnan(x)) return z;
        if(x<0.0) {
            return __kernel_standard(x,x,26); /* sqrt(negative) */
        } else
            return z;
#endif
}
