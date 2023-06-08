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
 * wrapper log10(X)
 */

#include "fdlibm.h"


#ifdef __STDC__
        double log10(double x)          /* wrapper log10 */
#else
        double log10(x)                 /* wrapper log10 */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_log10(x);
#else
        double z;
        z = __ieee754_log10(x);
        if(_LIB_VERSION == _IEEE_ || isnan(x)) return z;
        if(x<=0.0) {
            if(x==0.0)
                return __kernel_standard(x,x,18); /* log10(0) */
            else
                return __kernel_standard(x,x,19); /* log10(x<0) */
        } else
            return z;
#endif
}
