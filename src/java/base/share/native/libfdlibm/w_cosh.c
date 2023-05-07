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
 * wrapper cosh(x)
 */

#include "fdlibm.h"

#ifdef __STDC__
        double cosh(double x)           /* wrapper cosh */
#else
        double cosh(x)                  /* wrapper cosh */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_cosh(x);
#else
        double z;
        z = __ieee754_cosh(x);
        if(_LIB_VERSION == _IEEE_ || isnan(x)) return z;
        if(fabs(x)>7.10475860073943863426e+02) {
                return __kernel_standard(x,x,5); /* cosh overflow */
        } else
            return z;
#endif
}
