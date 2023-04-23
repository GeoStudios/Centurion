/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/*
 * wrapper sinh(x)
 */

#include "fdlibm.h"

#ifdef __STDC__
        double sinh(double x)           /* wrapper sinh */
#else
        double sinh(x)                  /* wrapper sinh */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_sinh(x);
#else
        double z;
        z = __ieee754_sinh(x);
        if(_LIB_VERSION == _IEEE_) return z;
        if(!finite(z)&&finite(x)) {
            return __kernel_standard(x,x,25); /* sinh overflow */
        } else
            return z;
#endif
}
